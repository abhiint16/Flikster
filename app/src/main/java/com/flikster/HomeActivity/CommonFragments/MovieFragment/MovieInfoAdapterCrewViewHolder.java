package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class MovieInfoAdapterCrewViewHolder extends RecyclerView.Adapter<MovieInfoAdapterCrewViewHolder.ViewHolder> {
    Context context;
    int size;
    List<MovieData.MovieInnerData> items;
    public MovieInfoAdapterCrewViewHolder(Context context, int size, List<MovieData.MovieInnerData> items) {
        this.context=context;
        this.size=size;
        this.items=items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_info_cast_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.card_movie_info_cast_recycler_item_name.setText(items.get(0).getCrew().get(position).getName());
        Glide.with(context).load(items.get(0).getCrew().get(position).getProfilePic()).asBitmap()
                .into(holder.card_movie_info_cast_recycler_item_image);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView card_movie_info_cast_recycler_item_image;
        TextView card_movie_info_cast_recycler_item_name;
        public ViewHolder(View itemView) {
            super(itemView);
            card_movie_info_cast_recycler_item_image=(ImageView)itemView.findViewById(R.id.card_movie_info_cast_recycler_item_image);
            card_movie_info_cast_recycler_item_name=(TextView)itemView.findViewById(R.id.card_movie_info_cast_recycler_item_name);
        }
    }
}
