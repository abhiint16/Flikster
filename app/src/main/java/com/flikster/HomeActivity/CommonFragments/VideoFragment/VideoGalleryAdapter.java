package com.flikster.HomeActivity.CommonFragments.VideoFragment;

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
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryFullScreen;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsData;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;
import com.rohitarya.glide.facedetection.transformation.FaceCenterCrop;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Logins on 26-10-2017.
 */

public class VideoGalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FeedInnerData outerHits;
    String title;
    VideoGalleryFragment.VideoRecommendationClick videoRecommendationClick;
    String userId = "PAWANKALYAN";
    String contentType;
    ApiInterface apiInterface;
    int count=10;
    String cardId;
    String slug;

    public VideoGalleryAdapter(Context context, FeedInnerData outerHits, Integer Count, String title,
                               VideoGalleryFragment.VideoRecommendationClick videoRecommendationClick,
                               String contentType,String cardId,String slug) {
        this.slug=slug;
        this.context = context;
        this.title = title;
        this.outerHits = outerHits;
        this.contentType=contentType;
        this.videoRecommendationClick = videoRecommendationClick;
        this.cardId=cardId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hor_last_item_load_more, parent, false);
            return new ViewHolder0(view);
        }
        else if (viewType==2)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blank_card, parent, false);
            return new ViewHolder2(view);
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
        else if(holder.getItemViewType()==1)
        {
            ((ViewHolder1)holder).carousel_title.setText(outerHits.getHits().get(position).get_source().getTitle());
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic())
                    .transform(new FaceCenterCrop())
                    .into(((ViewHolder1)holder).carousel_image);
        }
    }

    private void loadMore() {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/_search/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getNewsData("http://apiservice-ec.flikster.com/contents/_search?pretty=true&sort=createdAt:desc&size=10&from="+count+"&q=contentType:" + "\"" + contentType + "\""
                +"%20AND%20industry:\""+ SharedPrefsUtil.getStringPreference(context, "INDUSTRY_TYPE")+"\"");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
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
        else if (cardId.equals(outerHits.getHits().get(position).get_id()))
        {
            return 2;
        }
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

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder2(View itemView) {
            super(itemView);
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
                videoRecommendationClick.videoRecommendationClickMethod(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().get(0),
                        new VideoGalleryFragment(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                        userId, outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(),
                        outerHits.getHits().get(getAdapterPosition()).get_id(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug()
                );
            } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                videoRecommendationClick.videoRecommendationClickMethod(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().get(0),
                        new VideoGalleryFragment(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                        userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(),
                        outerHits.getHits().get(getAdapterPosition()).get_id(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug()
                );
            } else {
                videoRecommendationClick.videoRecommendationClickMethod("",
                        "",
                        "",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().get(0),
                        new VideoGalleryFragment(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                        userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),
                        outerHits.getHits().get(getAdapterPosition()).get_id(),"");
            }
        }
    }
}
