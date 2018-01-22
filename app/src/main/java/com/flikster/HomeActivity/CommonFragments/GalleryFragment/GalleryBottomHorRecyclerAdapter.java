package com.flikster.HomeActivity.CommonFragments.GalleryFragment;

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
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsData;
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

public class GalleryBottomHorRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FeedInnerData hits;
    String userId;
    GalleryCardClick.GalleryRecommendationItemClick galleryRecommendationItemClick;
    ApiInterface apiInterface;
    int count=10;

    public GalleryBottomHorRecyclerAdapter(Context context,
                                           FeedInnerData hits,
                                           GalleryCardClick.GalleryRecommendationItemClick
                                                   galleryRecommendationItemClick, String userId) {
        this.context = context;
        this.hits = hits;
        this.galleryRecommendationItemClick = galleryRecommendationItemClick;
        this.userId = userId;
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
            if (hits.getHits().get(position).get_source().getProfilePic() != null)
                Glide.with(context).load(hits.getHits().get(position).get_source().getProfilePic()).into(((ViewHolder1)holder).carousel_image);
            else if (hits.getHits().get(position).get_source().getProfilePic() == null) {
                if (hits.getHits().get(position).get_source().getMedia() != null) {
                    if (hits.getHits().get(position).get_source().getMedia().getGallery() != null && hits.getHits().get(position).get_source().getMedia().getGallery().size() != 0)
                        Glide.with(context).load(hits.getHits().get(position).get_source().getMedia().getGallery().get(0)).into(((ViewHolder1)holder).carousel_image);
                }
            }
            if (hits.getHits().get(position).get_source().getTitle() != null)
                ((ViewHolder1)holder).carousel_title.setText(hits.getHits().get(position).get_source().getTitle());

        }
    }

    private void loadMore() {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/_search/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getGalleryData("http://apiservice-ec.flikster.com/contents/_search?pretty=true&sort=createdAt:desc&size=10&from="+count+"&q=contentType:" + "\"" + "gallery" + "\"");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                hits.getHits().addAll(response.body().getHits().getHits());
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
        if (hits.getTotal()==hits.getHits().size())
            return hits.getHits().size();
        return hits.getHits().size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==hits.getHits().size())
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
            if (hits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                galleryRecommendationItemClick.galleryRecommendationItemClickMethod(hits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                        hits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                        hits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(), hits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                        hits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        new GalleryCardClick(), userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                galleryRecommendationItemClick.galleryRecommendationItemClickMethod(hits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                        hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                        hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(), hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                        hits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        new GalleryCardClick(), userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
            } else {
                galleryRecommendationItemClick.galleryRecommendationItemClickMethod(hits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                        "",
                        "", "", hits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        new GalleryCardClick(), userId, hits.getHits().get(getAdapterPosition()).get_source().getId());

            }
        }
    }
}
