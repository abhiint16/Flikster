package com.flikster.HomeActivity.CommonFragments.VideoFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryFullScreen;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsData;
import com.flikster.R;

import java.util.List;

/**
 * Created by Logins on 26-10-2017.
 */

public class VideoGalleryAdapter extends RecyclerView.Adapter<VideoGalleryAdapter.ViewHolder> {
    Context context;
    NewsData.NewsInnerData outerHits;
    String title;
    Integer Count;
    public VideoGalleryAdapter(Context context,NewsData.NewsInnerData outerHits,Integer Count,String title) {
        this.context=context;
        this.title=title;
        this.outerHits=outerHits;
        this.Count=Count;
    }

    @Override
    public VideoGalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_images_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoGalleryAdapter.ViewHolder holder, int position) {
        holder.carousel_title.setText(outerHits.getHits().get(position).get_source().getTitle());
        Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic()).into(holder.carousel_image);
    }

    @Override
    public int getItemCount() {
        return Count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView carousel_image;
        TextView carousel_title;
        public ViewHolder(View itemView) {
            super(itemView);
            carousel_image=(ImageView)itemView.findViewById(R.id.carousel_image);
            carousel_title=(TextView)itemView.findViewById(R.id.carousel_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context,GalleryFullScreen.class);
            context.startActivity(intent);
        }
    }
}
