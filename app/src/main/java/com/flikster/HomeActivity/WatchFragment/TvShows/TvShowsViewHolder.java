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
    FragmentManager fragmentManager;
    Context context;
    WatchFragment.WatchFragCommInterface watchFragCommInterface;
    FeedInnerData outerHits;

    public TvShowsViewHolder(Context context, FragmentManager fragmentManager,
            FeedInnerData feedInnerData, WatchFragment.WatchFragCommInterface watchFragCommInterface) {
        this.fragmentManager = fragmentManager;
        this.context=context;
        this.watchFragCommInterface=watchFragCommInterface;
        this.outerHits=feedInnerData;
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
            if (outerHits.getHits().get(position).get_source().getMedia()!=null)
            {
                if (outerHits.getHits().get(position).get_source().getMedia().getGallery()!=null&&
                        outerHits.getHits().get(position).get_source().getMedia().getGallery().size()!=0)
                {
                    Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(0)).into(((ViewHolder2)holder).carousel_image);
                }
            }
            if (outerHits.getHits().get(position).get_source().getTitle()!=null)
            ((ViewHolder2)holder).carousel_title.setText(outerHits.getHits().get(position).get_source().getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if (outerHits.getHits()!=null&&outerHits.getHits().size()!=0)
            return outerHits.getHits().size();
        else return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(outerHits.getHits()!=null&&outerHits.getHits().size()!=0)
            return 1;
        else
            return 0;
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
            if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                watchFragCommInterface.carouselItemToGallery(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick());
            } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                watchFragCommInterface.carouselItemToGallery(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
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
