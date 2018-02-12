package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryFullScreen;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class CelebrityBioAdapterImagesViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CelebBioImagesData.CelebBioImagesDataInner> celebALlImages=new ArrayList<>();
    Context context;
    public CelebrityBioAdapterImagesViewHolder(Context context,List<CelebBioImagesData.CelebBioImagesDataInner> celebALlImages) {
        this.context=context;
        this.celebALlImages=celebALlImages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_not_available_layout,parent,false);
            return new ViewHolder1(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_images_recycler_item,parent,false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==1)
        {

        }
        else if (holder.getItemViewType()==2)
        {
            Log.e("check for link",""+celebALlImages.get(position).getGallery());
                Glide.with(context).load(celebALlImages.get(position).getGallery())
                        .into(((ViewHolder2)holder).carousel_image);
            ((ViewHolder2)holder).carousel_title.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount()
    {
        if (celebALlImages==null||celebALlImages.size()==0)
            return 1;
        else
            return celebALlImages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (celebALlImages==null||celebALlImages.size()==0)
            return 1;
        else
            return 2;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView carousel_image;
        TextView carousel_title;
        public ViewHolder2(View itemView) {
            super(itemView);
            carousel_image=(ImageView)itemView.findViewById(R.id.carousel_image);
            carousel_title=(TextView)itemView.findViewById(R.id.carousel_title);
        }
    }
}
