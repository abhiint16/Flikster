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
    MovieData.MovieInnerData hits;
    public MovieInfoAdapterCrewViewHolder(Context context,MovieData.MovieInnerData hits) {
        this.context=context;
        this.size=size;
        this.hits=hits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_info_cast_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(this.hits.getHits().get(0).get_source().getCrew()!=null)
        {
            holder.card_movie_info_cast_recycler_item_name.setText(hits.getHits().get(0).get_source().getCrew().get(position).getName());
            Glide.with(context).load(hits.getHits().get(0).get_source().getCrew().get(position).getProfilePic()).asBitmap()
                    .into(holder.card_movie_info_cast_recycler_item_image);
        }
    }

    @Override
    public int getItemCount() {
        if(this.hits.getHits().get(0).get_source().getCrew()!=null)
        return this.hits.getHits().get(0).get_source().getCrew().size();
        else
            return 0;
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
