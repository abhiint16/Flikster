package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryFullScreen;
import com.flikster.R;
import com.rohitarya.glide.facedetection.transformation.FaceCenterCrop;

import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class MovieInfoAdapterCastViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    int size;
    MovieData.MovieInnerData hits;
    MovieFragmentInfo.MovieToShopByVideoInterface movieToShopByVideoInterface;
    String userId;

    public MovieInfoAdapterCastViewHolder(Context context, MovieData.MovieInnerData hits,MovieFragmentInfo.MovieToShopByVideoInterface movieToShopByVideoInterface,
                                          String userId) {
        this.context = context;
        this.hits = hits;
        this.userId=userId;
        this.movieToShopByVideoInterface=movieToShopByVideoInterface;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_not_available_layout, parent, false);
            return new ViewHolder1(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_info_cast_recycler_item, parent, false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {

        } else if (holder.getItemViewType() == 2) {
            if (hits.getHits().get(0).get_source().getCast().get(position).getName()!=null)
            ((ViewHolder2) holder).card_movie_info_cast_recycler_item_name.setText(hits.getHits().get(0).get_source().getCast().get(position).getName());
            if (hits.getHits().get(0).get_source().getCast().get(position).getProfilePic()!=null)
            Glide.with(context).load(hits.getHits().get(0).get_source().getCast().get(position).getProfilePic()).asBitmap()
                    .transform(new FaceCenterCrop())
                    .into(((ViewHolder2) holder).card_movie_info_cast_recycler_item_image);
        }
    }

    @Override
    public int getItemCount() {
        if (hits.getHits() == null || hits.getHits().size() == 0)
            return 1;
        else if (hits.getHits().get(0).get_source().getCast() == null || hits.getHits().get(0).get_source().getCast().size() == 0)
            return 1;
        else
            return hits.getHits().get(0).get_source().getCast().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (hits.getHits() == null || hits.getHits().size() == 0)
            return 1;
        else if (hits.getHits().get(0).get_source().getCast() == null || hits.getHits().get(0).get_source().getCast().size() == 0)
            return 1;
        else
            return 2;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_movie_info_cast_recycler_item_image;
        TextView card_movie_info_cast_recycler_item_name;

        public ViewHolder2(View itemView) {
            super(itemView);
            card_movie_info_cast_recycler_item_image = (ImageView) itemView.findViewById(R.id.card_movie_info_cast_recycler_item_image);
            card_movie_info_cast_recycler_item_name = (TextView) itemView.findViewById(R.id.card_movie_info_cast_recycler_item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            movieToShopByVideoInterface.test(hits.getHits().get(0).get_source().getCast().get(getAdapterPosition()).getSlug(), new CelebrityFragment(), 2, userId, hits.getHits().get(0).get_source().getCast().get(getAdapterPosition()).getId());
        }
    }
}
