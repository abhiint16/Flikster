package com.flikster.HomeActivity.FashionFragment.FashionNewDesign.FashionFragContainerClick;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreInnerData;
import com.flikster.R;

/**
 * Created by abhishek on 07-03-2018.
 */

public class GridReyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    AllStoreInnerData hits;
    public GridReyclerAdapter(Context context,AllStoreInnerData hits) {
        this.context=context;
        this.hits=hits;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_fashion_hor_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (hits.getHits().get(position).get_source().getImageGallery()!=null&&
                hits.getHits().get(position).get_source().getImageGallery().size()!=0)
        {
            Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(0))
                    .into(((ViewHolder) holder).fragment_fashion_hor_recycler_item_imageview);
        }
        else if (hits.getHits().get(position).get_source().getProfilePic()!=null)
        {
            Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(0))
                    .into(((ViewHolder) holder).fragment_fashion_hor_recycler_item_imageview);
        }
        if (hits.getHits().get(position).get_source().getProductInfo()!=null)
        {
            ((ViewHolder) holder).fragment_fashion_hor_recycler_item_title.setText(hits.getHits().get(position).get_source().getProductInfo());
        }
        if (hits.getHits().get(position).get_source().getCeleb()!=null)
        {
            if (hits.getHits().get(position).get_source().getCeleb().get(0).getName()!=null)
            {
                ((ViewHolder) holder).fragment_fashion_hor_recycler_item_tag.setText("#"+hits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
        }
    }

    @Override
    public int getItemCount() {
        return hits.getHits().size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView fragment_fashion_hor_recycler_item_imageview;
        TextView fragment_fashion_hor_recycler_item_tag,fragment_fashion_hor_recycler_item_title;
        public ViewHolder(View itemView) {
            super(itemView);
            fragment_fashion_hor_recycler_item_imageview=(ImageView)itemView.findViewById(R.id.fragment_fashion_hor_recycler_item_imageview);
            fragment_fashion_hor_recycler_item_tag=(TextView)itemView.findViewById(R.id.fragment_fashion_hor_recycler_item_tag);
            fragment_fashion_hor_recycler_item_title=(TextView)itemView.findViewById(R.id.fragment_fashion_hor_recycler_item_title);
        }
    }
}
