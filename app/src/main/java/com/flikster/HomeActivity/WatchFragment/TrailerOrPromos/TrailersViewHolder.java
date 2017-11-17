package com.flikster.HomeActivity.WatchFragment.TrailerOrPromos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryFullScreen;
import com.flikster.R;
import com.flikster.VideoFullScreenActivity.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class TrailersViewHolder extends RecyclerView.Adapter<TrailersViewHolder.ViewHolder> {
    List<String> imag=new ArrayList<>();
    Context context;
    List<String> trailerPromoImg=new ArrayList<>();
    List<String> trailerPromoTitle=new ArrayList<>();
    public TrailersViewHolder(Context context,List<String> trailerPromoImg,List<String> trailerPromoTitle) {
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
        this.context=context;
        this.trailerPromoImg=trailerPromoImg;
        this.trailerPromoTitle=trailerPromoTitle;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_images_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(trailerPromoImg.get(position)).into(holder.imageView);
        holder.carousel_title.setText(trailerPromoTitle.get(position));
    }

    @Override
    public int getItemCount() {
        return trailerPromoImg.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView carousel_title;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.carousel_image);
            carousel_title=(TextView)itemView.findViewById(R.id.carousel_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context,VideoPlayerActivity.class);
            context.startActivity(intent);
        }
    }
}
