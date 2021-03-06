package com.flikster.HomeActivity.CommonFragments.ProductFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class ProductImagesAdapter extends RecyclerView.Adapter<ProductImagesAdapter.ViewHolder> {
    List<String> imag = new ArrayList<>();
    FragmentManager fragmentManager;
    Context context;
    List<String> imageGallery;

    public ProductImagesAdapter(Context context, FragmentManager fragmentManager,
                                List<String> imageGallery) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");
        imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");
        imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
        this.imageGallery=imageGallery;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(imageGallery.get(position))
                .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                .into(holder.card_image_item_img);
    }

    @Override
    public int getItemCount() {
        Log.e("checke img",""+imageGallery.size()+imageGallery);
        return imageGallery.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_image_item_img;

        public ViewHolder(View itemView) {
            super(itemView);
            card_image_item_img = (ImageView) itemView.findViewById(R.id.card_image_item_img);
            //card_image_item_img.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new ProductGallaryFragment())
                    .addToBackStack("")
                    .commit();
        }
    }
}
