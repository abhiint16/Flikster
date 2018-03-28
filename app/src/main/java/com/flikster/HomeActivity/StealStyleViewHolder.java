package com.flikster.HomeActivity;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductOnClick;
import com.flikster.R;

/**
 * Created by abhishek on 11-10-2017.
 */

public class StealStyleViewHolder extends RecyclerView.Adapter<StealStyleViewHolder.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    String a;

    public StealStyleViewHolder(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    public StealStyleViewHolder(String a) {
        this.a = a;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_steal_style_carousel_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /*Glide.with(context).load("http://img.youtube.com/vi/MeH346YHUIE/0.jpg")
                .into(holder.card_steal_style_recycler_item_imageview);*//*
        if ("celebrity_store".equals(a)) {
            holder.card_steal_style_recycler_item_imageview.setImageResource(globalData.product.get(position));
        } else if ("movie_feed".equals(a)) {
            holder.card_steal_style_recycler_item_imageview.setImageResource(globalData.movie.get(position));
        } else
            holder.card_steal_style_recycler_item_imageview.setImageResource(globalData.style.get(position));*/
    }

    @Override
    public int getItemCount() {
        /*if ("celebrity_store".equals(a))
            return globalData.product.size();
        else if ("movie_feed".equals(a)) {
            return globalData.movie.size();
        } else
            return globalData.style.size();*/
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_steal_style_recycler_item_imageview;

        public ViewHolder(View itemView) {
            super(itemView);
            card_steal_style_recycler_item_imageview = (ImageView) itemView.findViewById(R.id.card_steal_style_recycler_item_imageview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           /* fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new ProductOnClick())
//                    .replace(R.id.main_container, new AuctionDetailFragment())
                    .addToBackStack("")
                    .commit();*/
        }
    }
}
