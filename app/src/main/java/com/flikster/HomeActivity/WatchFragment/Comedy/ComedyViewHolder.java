package com.flikster.HomeActivity.WatchFragment.Comedy;

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
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.SongByMovieFragmentItemClick;
import com.flikster.HomeActivity.WatchFragment.WatchFragment;
import com.flikster.R;
import com.flikster.VideoFullScreenActivity.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class ComedyViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> imag=new ArrayList<>();
    Context context;
    List<String> comedyImg=new ArrayList<>();
    List<String> comedyTitle=new ArrayList<>();
    FragmentManager fragmentManager;
    WatchFragment.WatchFragCommInterface watchFragCommInterface;
    public ComedyViewHolder(Context context, List<String> comedyTitle, List<String> comedyImg, FragmentManager fragmentManager,
                            WatchFragment.WatchFragCommInterface watchFragCommInterface) {
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
        this.context=context;
        this.comedyImg=comedyImg;
        this.comedyTitle=comedyTitle;
        this.fragmentManager = fragmentManager;
        this.watchFragCommInterface=watchFragCommInterface;
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
            Glide.with(context).load(comedyImg.get(position)).into(((ViewHolder2)holder).carousel_image);
            ((ViewHolder2)holder).carousel_title.setText(comedyTitle.get(position));
        }
    }

    @Override
    public int getItemCount() {
        Log.e("size check","size check"+comedyImg.size());
        if(comedyImg.size()==0)
            return 1;
        else
        return comedyImg.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(comedyImg.size()==0)
            return 0;
        else
            return 1;
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView carousel_image;
        TextView carousel_title;
        public ViewHolder2(View itemView) {
            super(itemView);
            carousel_image=(ImageView)itemView.findViewById(R.id.carousel_image);
            carousel_title=(TextView)itemView.findViewById(R.id.carousel_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //watchFragCommInterface.carouselItemClick(comedyTitle.get(getAdapterPosition()),comedyImg,comedyTitle,new MovieSongsListFragment());
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }
}
