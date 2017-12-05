package com.flikster.HomeActivity.CommonFragments.NewsFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryFullScreen;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.R;

import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class NewsBottomHorRecyclerAdapter extends RecyclerView.Adapter<NewsBottomHorRecyclerAdapter.ViewHolder> {
    Context context;
    FeedInnerData outerHits;
    int Count;
    String title;
    String bannerImg;
    NewsOnClickFragment.NewsRecommendedClick newsRecommendedClick;

    public NewsBottomHorRecyclerAdapter(Context context, FeedInnerData outerHits, int Count, String title, String bannerImg,
                                        NewsOnClickFragment.NewsRecommendedClick newsRecommendedClick) {
        this.context = context;
        this.outerHits = outerHits;
        this.Count = Count;
        this.title = title;
        this.bannerImg = bannerImg;
        this.newsRecommendedClick=newsRecommendedClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_images_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //if(!bannerImg.equals(outerHits.getHits().get(position).get_source().getProfilePic()))
        if (outerHits.getHits().get(position).get_source().getTitle() != null) {
            holder.carousel_title.setText(outerHits.getHits().get(position).get_source().getTitle());
        }
        if (outerHits.getHits().get(position).get_source().getProfilePic() != null) {
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic()).into(holder.carousel_image);
        }
    }

    @Override
    public int getItemCount() {
        return outerHits.getHits().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView carousel_image;
        TextView carousel_title;

        public ViewHolder(View itemView) {
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
                        outerHits.getHits().get(getAdapterPosition()).get_source().getContentType()
                );
            } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                newsRecommendedClick.newsRecommendedClickMethod(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new NewsOnClickFragment(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getContentType()
                );
            } else {
                newsRecommendedClick.newsRecommendedClickMethod("",
                        "",
                        "",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new NewsOnClickFragment(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getContentType());
            }
        }
    }
}
