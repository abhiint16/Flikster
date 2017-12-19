package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ShopByVideoData;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.SongByMovieFragmentItemClick;
import com.flikster.R;
import com.flikster.HomeActivity.CommonFragments.ShopByVideoFragment.ShopByVideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class CelebrityBioShopByVideoViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    FragmentManager fragmentManager;
    ShopByVideoData.ShopByVideoInnerData shopByVideoInnerData;
    Context context;
    CelebrityFragmentBio.CelebToShopByVideoInterface celebToShopByVideoInterface;

    public CelebrityBioShopByVideoViewHolder(Context context, FragmentManager fragmentManager, ShopByVideoData.ShopByVideoInnerData shopByVideoInnerData,
                                             CelebrityFragmentBio.CelebToShopByVideoInterface celebToShopByVideoInterface) {
        this.fragmentManager=fragmentManager;
        this.shopByVideoInnerData=shopByVideoInnerData;
        this.context=context;
        this.celebToShopByVideoInterface=celebToShopByVideoInterface;
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
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_videos_carousel_recycler_item,parent,false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==1)
        {

        }
        else if(holder.getItemViewType()==2)
        {
            if (shopByVideoInnerData.getHits().get(position).get_source().getThumbnail()!=null)
            {
                Glide.with(context).load(shopByVideoInnerData.getHits().get(position).get_source().getThumbnail()).into(((ViewHolder2)holder).imageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (shopByVideoInnerData.getHits()==null||shopByVideoInnerData.getHits().size()==0)
            return 1;
        else
            return shopByVideoInnerData.getHits().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (shopByVideoInnerData.getHits()==null||shopByVideoInnerData.getHits().size()==0)
            return 1;
        else
            return 2;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        public ViewHolder2(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.celeb_shopbyvideo_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            celebToShopByVideoInterface.playShopByVideoMethod(shopByVideoInnerData.getHits().get(getAdapterPosition()).get_source()
                    .getVideoUrl(),new SongByMovieFragmentItemClick(),shopByVideoInnerData.getHits().get(getAdapterPosition()).get_source()
                    .getThumbnail(),"video",shopByVideoInnerData.getHits().get(getAdapterPosition()).get_source().getProducts());
        }
    }
}
