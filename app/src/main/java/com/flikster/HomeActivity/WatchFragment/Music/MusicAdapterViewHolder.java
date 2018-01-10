package com.flikster.HomeActivity.WatchFragment.Music;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.HomeActivity.WatchFragment.WatchFragment;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class MusicAdapterViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    FragmentManager fragmentManager;
    Context context;
    WatchFragment.WatchFragCommInterface watchFragCommInterface;
    FeedInnerData feedInnerData;

    public MusicAdapterViewHolder(Context context, FeedInnerData hits, FragmentManager fragmentManager,
                                  WatchFragment.WatchFragCommInterface watchFragCommInterface) {
        this.fragmentManager = fragmentManager;
        this.feedInnerData=hits;
        this.context=context;
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_images_recycler_item, parent, false);
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
            if (feedInnerData.getHits().get(position).get_source()!=null)
            {
                if (feedInnerData.getHits().get(position).get_source().getProfilePic()!=null)
                    Glide.with(context).load(feedInnerData.getHits().get(position).get_source().getProfilePic()).into(((ViewHolder2)holder).carousel_image);
                if (feedInnerData.getHits().get(position).get_source().getTitle()!=null)
                    ((ViewHolder2)holder).carousel_title.setText(feedInnerData.getHits().get(position).get_source().getTitle());
            }
        }
    }

    @Override
    public int getItemCount() {
        /*if(musicImg.size()==0)
            return 1;
        else
            return musicImg.size();*/
        if (feedInnerData.getHits()!=null&&feedInnerData.getHits().size()!=0)
            return feedInnerData.getHits().size();
        else return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(feedInnerData.getHits()!=null&&feedInnerData.getHits().size()!=0)
            return 1;
        else
            return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        public ViewHolder1(View itemView) {
            super(itemView);
        }
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
            watchFragCommInterface.carouselItemClick(feedInnerData.getHits().get(getAdapterPosition()).get_source().getTitle(),
                    feedInnerData.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                    feedInnerData.getHits().get(getAdapterPosition()).get_source().getTitle(),
                    feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getAudio().get(0),"audio",new MovieSongsListFragment());
        }
    }
}
