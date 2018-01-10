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
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.SongByMovieFragmentItemClick;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 01-11-2017.
 */

public class MusicGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    MusicGridFragment.WatchAudioVideoSendFromGridFrag watchAudioVideoSendFromGridFrag;
    FeedInnerData feedInnerData;
    String audioVideoLink;

    public MusicGridAdapter(Context context, FragmentManager fragmentManager, FeedInnerData feedInnerData, MusicGridFragment.WatchAudioVideoSendFromGridFrag watchAudioVideoSendFromGridFrag) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.feedInnerData=feedInnerData;
        this.watchAudioVideoSendFromGridFrag=watchAudioVideoSendFromGridFrag;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_no_comments,parent,false);
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
            ((ViewHolder1)holder).activity_no_comments_tv.setText("No Item Available");
        }
        else if(holder.getItemViewType()==1)
        {
            if (feedInnerData.getHits().get(position).get_source().getProfilePic()!=null)
            Glide.with(context).load(feedInnerData.getHits().get(position).get_source().getProfilePic().trim()).into(((ViewHolder2)holder).card_music_recycler_item_img);
            if (feedInnerData.getHits().get(position).get_source().getTitle()!=null)
            ((ViewHolder2)holder).card_music_recycler_item_title.setText(feedInnerData.getHits().get(position).get_source().getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if (feedInnerData.getHits()!=null&&feedInnerData.getHits().size()!=0)
            return feedInnerData.getHits().size();
        else return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (feedInnerData.getHits()!=null&&feedInnerData.getHits().size()!=0)
            return 1;
        else return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView activity_no_comments_tv;
        public ViewHolder1(View itemView)
        {
            super(itemView);
            activity_no_comments_tv=(TextView)itemView.findViewById(R.id.activity_no_comments_tv);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_music_recycler_item_img;
        TextView card_music_recycler_item_title;

        public ViewHolder2(View itemView) {
            super(itemView);
            card_music_recycler_item_img = (ImageView) itemView.findViewById(R.id.card_music_recycler_item_img);
            card_music_recycler_item_title=(TextView)itemView.findViewById(R.id.card_music_recycler_item_title);
            card_music_recycler_item_img.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia()!=null)
            {
                if (feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getAudio()!=null&&
                        feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getAudio().size()!=0)
                {
                    audioVideoLink=feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getAudio().get(0);
                }
                else if (feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo()!=null&&
                        feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().size()!=0)
                {
                    audioVideoLink=feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().get(0);
                }
            }
            watchAudioVideoSendFromGridFrag.sendAudioVideoLink(feedInnerData.getHits().get(getAdapterPosition()).get_source().getTitle(),
                    feedInnerData.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                    feedInnerData.getHits().get(getAdapterPosition()).get_source().getTitle(),
                    audioVideoLink,new SongByMovieFragmentItemClick());
        }
    }
}