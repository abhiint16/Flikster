package com.flikster.HomeActivity.FashionFragment.FashionType.CommonAllProductPage;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flikster.R;

import java.util.List;

/**
 * Created by abhishek on 14-12-2017.
 */

public class CommonAllProductPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    FragmentManager fragmentManager;
    List<String> imageGallery;
    public CommonAllProductPageAdapter(Context context, FragmentManager fragmentManager, List<String> imageGallery) {
        this.context=context;
        this.fragmentManager=fragmentManager;
        this.imageGallery=imageGallery;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_common_all_product_page_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(imageGallery.get(position))
                .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                .into(((ViewHolder)holder).card_common_all_product_item_imgview);
    }

    @Override
    public int getItemCount() {
        return imageGallery.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView card_common_all_product_item_imgview;

        public ViewHolder(View itemView) {
            super(itemView);
            card_common_all_product_item_imgview=(ImageView)itemView.findViewById(R.id.card_common_all_product_item_imgview);
        }
    }
}
