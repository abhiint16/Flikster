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
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.SongByMovieFragmentItemClick;
import com.flikster.R;
import com.flikster.Util.GlobalData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 01-11-2017.
 */

public class MusicGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<String> img=new ArrayList<>();
    List<String> title=new ArrayList<>();
    List<String> audioVideoLink=new ArrayList<>();
    MusicGridFragment.WatchAudioVideoSendFromGridFrag watchAudioVideoSendFromGridFrag;

    public MusicGridAdapter(Context context, FragmentManager fragmentManager, List<String> img, List<String> title,
                            List<String> audioVideoLink, MusicGridFragment.WatchAudioVideoSendFromGridFrag watchAudioVideoSendFromGridFrag) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.img=img;
        this.title=title;
        this.audioVideoLink=audioVideoLink;
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
            Glide.with(context).load(img.get(position).trim()).into(((ViewHolder2)holder).card_music_recycler_item_img);
            ((ViewHolder2)holder).card_music_recycler_item_title.setText(title.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(img.size()==0)
            return 1;
        else
        return img.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(img.size()==0)
            return 0;
        else
            return 1;
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
            watchAudioVideoSendFromGridFrag.sendAudioVideoLink(title.get(getAdapterPosition()),img.get(getAdapterPosition()),title.get(getAdapterPosition()),
                    audioVideoLink.get(getAdapterPosition()),new SongByMovieFragmentItemClick());
        }
    }
}