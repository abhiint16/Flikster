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
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.HomeActivity.WatchFragment.WatchFragment;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class MusicAdapterViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> musicImg = new ArrayList<>();
    List<String> musicTitle=new ArrayList<>();
    FragmentManager fragmentManager;
    Context context;
    WatchFragment.WatchFragCommInterface watchFragCommInterface;

    public MusicAdapterViewHolder(Context context, List<String> musicTitle, List<String> musicImg, FragmentManager fragmentManager,
                                  WatchFragment.WatchFragCommInterface watchFragCommInterface) {
        this.fragmentManager = fragmentManager;
        this.musicImg=musicImg;
        this.musicTitle=musicTitle;
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_music_recycler_item, parent, false);
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
            Glide.with(context).load(musicImg.get(position)).into(((ViewHolder2)holder).card_music_recycler_item_img);
            ((ViewHolder2)holder).card_music_recycler_item_title.setText(musicTitle.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(musicImg.size()==0)
            return 1;
        else
            return musicImg.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(musicImg.size()==0)
            return 0;
        else
            return 1;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_music_recycler_item_img;
        TextView card_music_recycler_item_title;
        public ViewHolder2(View itemView) {
            super(itemView);
            card_music_recycler_item_img=(ImageView)itemView.findViewById(R.id.card_music_recycler_item_img);
            card_music_recycler_item_title=(TextView)itemView.findViewById(R.id.card_music_recycler_item_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            watchFragCommInterface.carouselItemClick(musicTitle.get(getAdapterPosition()),musicImg,musicTitle,new MovieSongsListFragment());
        }
    }
}
