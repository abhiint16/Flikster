package com.flikster.HomeActivity.CommonFragments.NewsFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryFullScreen;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 13-10-2017.
 */

public class NewsBottomHorRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FeedInnerData outerHits;
    String title;
    String bannerImg;
    NewsOnClickFragment.NewsRecommendedClick newsRecommendedClick;
    ApiInterface apiInterface;
    int count=10;
    String contentType;

    public NewsBottomHorRecyclerAdapter(Context context, FeedInnerData outerHits, int Count, String title, String bannerImg,
                                        NewsOnClickFragment.NewsRecommendedClick newsRecommendedClick,
                                        String contentType) {
        this.context = context;
        this.outerHits = outerHits;
        this.title = title;
        this.bannerImg = bannerImg;
        this.newsRecommendedClick = newsRecommendedClick;
        this.contentType=contentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hor_last_item_load_more, parent, false);
            return new ViewHolder0(view);
        }
        else
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_images_recycler_item, parent, false);
            return new ViewHolder1(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==0)
        {
            loadMore();
        }
        else if (holder.getItemViewType()==1)
        {
            if (outerHits.getHits().get(position).get_source().getTitle() != null) {
                ((ViewHolder1)holder).carousel_title.setText(outerHits.getHits().get(position).get_source().getTitle());
            }
            if (outerHits.getHits().get(position).get_source().getProfilePic() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic()).into(((ViewHolder1)holder).carousel_image);
            }
        }
    }

    private void loadMore() {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/_search/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getNewsData("http://apiservice-ec.flikster.com/contents/_search?pretty=true&sort=createdAt:desc&size=10&from="+count+"&q=contentType:" + "\"" + contentType + "\"");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                count=count+30;
                outerHits.getHits().addAll(response.body().getHits().getHits());
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (outerHits.getTotal()==outerHits.getHits().size())
            return outerHits.getHits().size();
        return outerHits.getHits().size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==outerHits.getHits().size())
            return 0;
        else return 1;
    }

    public class ViewHolder0 extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout hor_last_item_load_more_container;
        public ViewHolder0(View itemView) {
            super(itemView);
            hor_last_item_load_more_container=(LinearLayout)itemView.findViewById(R.id.hor_last_item_load_more_container);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView carousel_image;
        TextView carousel_title;

        public ViewHolder1(View itemView) {
            super(itemView);
            carousel_image = (ImageView) itemView.findViewById(R.id.carousel_image);
            carousel_title = (TextView) itemView.findViewById(R.id.carousel_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                newsRecommendedClick.newsRecommendedClickMethod(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new NewsOnClickFragment(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                        "PAWANKALYAN",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId()
                );
            } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                newsRecommendedClick.newsRecommendedClickMethod(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new NewsOnClickFragment(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                        "PAWANKALYAN",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId()
                );
            } else {
                newsRecommendedClick.newsRecommendedClickMethod("",
                        "",
                        "",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new NewsOnClickFragment(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                        "PAWANKALYAN",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            }
        }
    }
}
