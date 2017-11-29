package com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.R;
import com.flikster.Util.GlobalData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 01-11-2017.
 */

public class MovieSongsListAdapter extends RecyclerView.Adapter<MovieSongsListAdapter.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<String> img=new ArrayList<>();
    List<String> title=new ArrayList<>();

    public MovieSongsListAdapter(Context context, FragmentManager fragmentManager, List<String> img,List<String> title) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.img=img;
        this.title=title;
    }

    @Override
    public MovieSongsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_music_with_tv_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieSongsListAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(img.get(0).trim()).into(holder.movieimg);
        holder.movietitle.setText("Song No. "+position);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView movieimg;
        TextView movietitle;

        public ViewHolder(View itemView) {
            super(itemView);
            movieimg = (ImageView) itemView.findViewById(R.id.movieimg);
            movietitle=(TextView)itemView.findViewById(R.id.movietitle);
            movieimg.setOnClickListener(this);
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
