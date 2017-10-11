package com.flikster;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by abhishek on 11-10-2017.
 */

public class StealStyleViewHolder extends RecyclerView.Adapter<StealStyleViewHolder.ViewHolder> {
    Context context;
    GlobalData globalData=new GlobalData();
    public StealStyleViewHolder(Context context) {
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_steal_style_carousel_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /*Glide.with(context).load("http://img.youtube.com/vi/MeH346YHUIE/0.jpg")
                .into(holder.card_steal_style_recycler_item_imageview);*/
        holder.card_steal_style_recycler_item_imageview.setImageResource(R.drawable.bahubai);
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView card_steal_style_recycler_item_imageview;
        public ViewHolder(View itemView) {
            super(itemView);
            card_steal_style_recycler_item_imageview=(ImageView)itemView.findViewById(R.id.card_steal_style_recycler_item_imageview);
        }
    }
}
