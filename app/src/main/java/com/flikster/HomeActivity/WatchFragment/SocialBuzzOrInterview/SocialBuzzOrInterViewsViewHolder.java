package com.flikster.HomeActivity.WatchFragment.SocialBuzzOrInterview;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryFullScreen;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.SongByMovieFragmentItemClick;
import com.flikster.R;
import com.flikster.VideoFullScreenActivity.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class SocialBuzzOrInterViewsViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> imag=new ArrayList<>();
    Context context;
    List<String> socialInterviewImg=new ArrayList<>();
    List<String> socialInterviewTitle=new ArrayList<>();
    FragmentManager fragmentManager;
    public SocialBuzzOrInterViewsViewHolder(Context context, List<String> socialInterviewTitle, List<String> socialInterviewImg, FragmentManager fragmentManager) {
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
        this.context=context;
        this.socialInterviewImg=socialInterviewImg;
        this.socialInterviewTitle=socialInterviewTitle;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_not_available_layout,parent,false);
            return new ViewHolder1(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_images_recycler_item,parent,false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==0)
        {

        }
        else
        {
            Glide.with(context).load(socialInterviewImg.get(position)).into(((ViewHolder2)holder).imageView);
            ((ViewHolder2)holder).carousel_title.setText(socialInterviewTitle.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(socialInterviewImg.size()==0)
            return 1;
        else
            return socialInterviewImg.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(socialInterviewImg.size()==0)
            return 0;
        else
            return 1;
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView carousel_title;
        public ViewHolder2(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.carousel_image);
            carousel_title=(TextView)itemView.findViewById(R.id.carousel_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new MovieSongsListFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }
}
