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
import com.flikster.R;
import com.flikster.Util.GlobalData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 01-11-2017.
 */

public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<String> img=new ArrayList<>();
    List<String> title=new ArrayList<>();

    public MusicGridAdapter(Context context, FragmentManager fragmentManager, List<String> img,List<String> title) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.img=img;
        this.title=title;
    }

    @Override
    public MusicGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_music_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicGridAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(img.get(position).trim()).into(holder.card_music_recycler_item_img);
        holder.card_music_recycler_item_title.setText(title.get(position));
    }

    @Override
    public int getItemCount() {
        return img.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_music_recycler_item_img;
        TextView card_music_recycler_item_title;

        public ViewHolder(View itemView) {
            super(itemView);
            card_music_recycler_item_img = (ImageView) itemView.findViewById(R.id.card_music_recycler_item_img);
            card_music_recycler_item_title=(TextView)itemView.findViewById(R.id.card_music_recycler_item_title);
            card_music_recycler_item_img.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new MovieSongsListFragment())
                    .addToBackStack("")
                    .commit();


        }
    }
}
