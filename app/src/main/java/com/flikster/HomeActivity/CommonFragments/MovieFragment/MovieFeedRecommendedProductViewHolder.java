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

import java.util.List;

/**
 * Created by abhishek on 11-10-2017.
 */

public class MovieFeedRecommendedProductViewHolder extends RecyclerView.Adapter<MovieFeedRecommendedProductViewHolder.ViewHolder> {
    Context context;
    List<RecommendedProductData.RecommendedProductInnerData> items;

    public MovieFeedRecommendedProductViewHolder(List<RecommendedProductData.RecommendedProductInnerData> items, Context context) {
       this.items=items;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_steal_style_carousel_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(items.get(position).getProfilePic()!=null)
        Glide.with(context).load(items.get(position).getProfilePic())
                .into(holder.card_steal_style_recycler_item_imageview);
        holder.card_steal_style_recycler_item_desc.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_steal_style_recycler_item_imageview;
        TextView card_steal_style_recycler_item_desc;
        public ViewHolder(View itemView) {
            super(itemView);
            card_steal_style_recycler_item_imageview = (ImageView) itemView.findViewById(R.id.card_steal_style_recycler_item_imageview);
            card_steal_style_recycler_item_desc=(TextView)itemView.findViewById(R.id.card_steal_style_recycler_item_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
