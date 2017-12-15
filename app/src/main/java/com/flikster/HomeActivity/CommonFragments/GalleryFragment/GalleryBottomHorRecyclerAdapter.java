package com.flikster.HomeActivity.CommonFragments.GalleryFragment;

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
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsData;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.R;

import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class GalleryBottomHorRecyclerAdapter extends RecyclerView.Adapter<GalleryBottomHorRecyclerAdapter.ViewHolder> {
    Context context;
    FeedInnerData hits;
    String userId;
    GalleryCardClick.GalleryRecommendationItemClick galleryRecommendationItemClick;

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_images_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (hits.getHits().get(position).get_source().getProfilePic() != null)
            Glide.with(context).load(hits.getHits().get(position).get_source().getProfilePic()).into(holder.carousel_image);
        else if (hits.getHits().get(position).get_source().getProfilePic() == null) {
            if (hits.getHits().get(position).get_source().getMedia() != null) {
                if (hits.getHits().get(position).get_source().getMedia().getGallery() != null && hits.getHits().get(position).get_source().getMedia().getGallery().size() != 0)
                    Glide.with(context).load(hits.getHits().get(position).get_source().getMedia().getGallery().get(0)).into(holder.carousel_image);
            }
        }
        if (hits.getHits().get(position).get_source().getTitle() != null)
            holder.carousel_title.setText(hits.getHits().get(position).get_source().getTitle());

    }

    @Override
    public int getItemCount() {
        return hits.getHits().size();
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
