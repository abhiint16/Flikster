package com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flikster.R;
import com.flikster.Util.GlobalData;

/**
 * Created by abhishek on 01-11-2017.
 */

public class MovieSongsListAdapter extends RecyclerView.Adapter<MovieSongsListAdapter.ViewHolder> {
    Context context;
    GlobalData globalData = new GlobalData();
    FragmentManager fragmentManager;

    public MovieSongsListAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public MovieSongsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_music_with_tv_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieSongsListAdapter.ViewHolder holder, int position) {
//        holder.card_shopby_video_recycler_image.setImageResource(globalData.style.get(position));
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_shopby_video_recycler_image;

        public ViewHolder(View itemView) {
            super(itemView);
            card_shopby_video_recycler_image = (ImageView) itemView.findViewById(R.id.movieimg);
            card_shopby_video_recycler_image.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new SongByMovieFragmentItemClick())
                    .addToBackStack("")
                    .commit();
        }
    }
}
