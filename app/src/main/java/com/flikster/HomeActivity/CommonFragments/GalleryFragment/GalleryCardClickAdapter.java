package com.flikster.HomeActivity.CommonFragments.GalleryFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.Util.Common;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 05-10-2017.
 */

public class GalleryCardClickAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<String> galleryImgLinks;
    GalleryBottomHorRecyclerAdapter galleryBottomHorRecyclerAdapter;
    ApiInterface apiInterface;
    FeedInnerData hits;
    Integer Count;
    String userId;
    GalleryCardClick.GalleryRecommendationItemClick galleryRecommendationItemClick;
    String cardId;

    public GalleryCardClickAdapter(Context context, FragmentManager fragmentManager, List<String> galleryImgLinks,
                                   GalleryCardClick.GalleryRecommendationItemClick galleryRecommendationItemClick, String userId,String cardId) {
        this.context = context;
        this.userId = userId;
        this.fragmentManager = fragmentManager;
        this.galleryImgLinks = galleryImgLinks;
        this.galleryRecommendationItemClick = galleryRecommendationItemClick;
        this.cardId=cardId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary_item_onclick_recycler_item, parent, false);
            return new ViewHolder1(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            Glide.with(context).load(galleryImgLinks.get(position))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder1) holder).gallary_recycler_item_img);
            new PostRetrofit().checkForLike("like", userId, galleryImgLinks.get(position), ((ViewHolder1) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, galleryImgLinks.get(position), ((ViewHolder1) holder).ib_bookmark, context);
        } else {
            galleryBottomHorRecyclerRetrofitInit(holder);
        }
    }

    private void galleryBottomHorRecyclerRetrofitInit(final RecyclerView.ViewHolder viewholder) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/_search/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getGalleryData("http://apiservice-ec.flikster.com/contents/_search?pretty=true&sort=createdAt:desc&size=10&from=0&q=contentType:" + "\"" + "gallery" + "\""
                +"%20AND%20industry:\""+ SharedPrefsUtil.getStringPreference(context, "INDUSTRY_TYPE")+"\"");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                hits = response.body().getHits();
                ((ViewHolder2) viewholder).fragment_common_recyclerview_with_tv_title.setText("Recommended Gallary");
                layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                ((ViewHolder2) viewholder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
                galleryBottomHorRecyclerAdapter = new GalleryBottomHorRecyclerAdapter(context, hits, galleryRecommendationItemClick, userId,cardId);
                ((ViewHolder2) viewholder).fragment_common_recyclerview_with_tv_recycler.setAdapter(galleryBottomHorRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryImgLinks.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if ((position) == galleryImgLinks.size())
            return 1;
        else
            return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView gallary_recycler_item_img;
        ImageButton card_footer_share, ib_like, ib_bookmark;
        Button followbtn;
        ImageButton card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        TextView card_comment_text_see_more_comments;

        public ViewHolder1(View view) {
            super(view);
            gallary_recycler_item_img = (ImageView) view.findViewById(R.id.gallary_recycler_item_img);
            card_footer_share = (ImageButton) view.findViewById(R.id.card_footer_share);
            ib_like = (ImageButton) view.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) view.findViewById(R.id.ib_bookmark);

            card_comment_text_send_btn = (ImageButton) view.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) view.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) view.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            card_comment_text_see_more_comments.setVisibility(View.GONE);

            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(galleryImgLinks.get(getAdapterPosition()), context);
                }
            });

            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, galleryImgLinks.get(getAdapterPosition()));
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, galleryImgLinks.get(getAdapterPosition()));
                }
            });



            gallary_recycler_item_img.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.gallary_recycler_item_img) {
                Intent intent = new Intent(context, GalleryFullScreen.class);
                intent.putExtra("galleryimglink", galleryImgLinks.get(getAdapterPosition()));
                intent.putExtra("userId", userId);
                context.startActivity(intent);
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                        userId,
                        galleryImgLinks.get(getAdapterPosition()),
                        card_comment_text_edittxt.getText().toString(),
                        card_comment_text_edittxt, context);
            }

        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder2(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }
    }
}
