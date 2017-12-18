package com.flikster.HomeActivity.WatchFragment.TvShows;

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
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryCardClick;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryFullScreen;
import com.flikster.HomeActivity.FeedInnerData;
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

public class TvShowsViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> imag = new ArrayList<>();
    FragmentManager fragmentManager;
    List<String> tvShowsImg=new ArrayList<>();
    List<String> tvShowsTitle=new ArrayList<>();
    List<ArrayList<String>> tvShowsVideo;
    Context context;
    WatchFragment.WatchFragCommInterface watchFragCommInterface;
    FeedInnerData outerHits;

    public TvShowsViewHolder(Context context, FragmentManager fragmentManager, List<String> tvShowsImg, List<String> tvShowsTitle,
                             List<ArrayList<String>> tvShowsVideo, WatchFragment.WatchFragCommInterface watchFragCommInterface,
                             FeedInnerData outerHits) {
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");
        imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");
        imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
        this.fragmentManager = fragmentManager;
        this.context=context;
        this.tvShowsImg=tvShowsImg;
        this.tvShowsTitle=tvShowsTitle;
        this.tvShowsVideo=tvShowsVideo;
        this.watchFragCommInterface=watchFragCommInterface;
        this.outerHits=outerHits;
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
            Glide.with(context).load(tvShowsVideo.get(position).get(0)).into(((ViewHolder2)holder).carousel_image);
            ((ViewHolder2)holder).carousel_title.setText(tvShowsTitle.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(tvShowsImg.size()==0)
            return 1;
        else
            return tvShowsImg.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(tvShowsImg.size()==0)
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
            Log.e("check adapter pos","check adapter pos"+getAdapterPosition());
            Log.e("check adapter pos","check adapter pos"+outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery());
            Log.e("check adapter pos","check adapter pos"+tvShowsVideo.get(getAdapterPosition()));
            if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                watchFragCommInterface.carouselItemToGallery(tvShowsVideo.get(getAdapterPosition()),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick());
            } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                watchFragCommInterface.carouselItemToGallery(tvShowsVideo.get(getAdapterPosition()),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick());
            } else {
                watchFragCommInterface.carouselItemToGallery(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                        "",
                        "", "", outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick());
            }
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }
}
