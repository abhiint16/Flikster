package com.flikster;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by abhishek on 01-11-2017.
 */

public class ShopByVideoFragmentAdapter extends RecyclerView.Adapter<ShopByVideoFragmentAdapter.ViewHolder> {
    Context context;
    GlobalData globalData=new GlobalData();
    public ShopByVideoFragmentAdapter(Context context) {
        this.context=context;
    }

    @Override
    public ShopByVideoFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_shopby_cideo_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopByVideoFragmentAdapter.ViewHolder holder, int position) {
        holder.card_shopby_video_recycler_image.setImageResource(globalData.style.get(position));
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_shopby_video_recycler_image;
        public ViewHolder(View itemView) {
            super(itemView);
            card_shopby_video_recycler_image=(ImageView)itemView.findViewById(R.id.card_shopby_video_recycler_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
