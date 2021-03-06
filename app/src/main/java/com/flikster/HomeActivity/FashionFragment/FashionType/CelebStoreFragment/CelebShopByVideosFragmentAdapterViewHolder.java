package com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ShopByVideoData;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.SongByMovieFragmentItemClick;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class CelebShopByVideosFragmentAdapterViewHolder extends RecyclerView.Adapter<CelebShopByVideosFragmentAdapterViewHolder.ViewHolder> {
    List<String> imag = new ArrayList<>();
    FragmentManager fragmentManager;
    int a;
    Context context;
    ShopByVideoData.ShopByVideoInnerData outerHits;
    CelebStoreFirstTypeFragment.ShopByVideoInterafce shopByVideoInterafce;
    public CelebShopByVideosFragmentAdapterViewHolder(Context context, FragmentManager fragmentManager,
                                                      ShopByVideoData.ShopByVideoInnerData outerHits,
                                                      CelebStoreFirstTypeFragment.ShopByVideoInterafce shopByVideoInterafce) {
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");
        imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");
        imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.outerHits=outerHits;
        this.shopByVideoInterafce=shopByVideoInterafce;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video_item_with_bottom_title, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.card_video_item_desc.setVisibility(View.GONE);
        Glide.with(context).load(outerHits.getHits().get(position).get_source().getThumbnail().trim()).into(holder.card_video_item_image);
        holder.card_video_item_title.setText(outerHits.getHits().get(position).get_source().getTitle());
    }

    @Override
    public int getItemCount() {
        return outerHits.getHits().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_video_item_image;
        TextView card_video_item_title,card_video_item_desc;
        public ViewHolder(View itemView) {
            super(itemView);
            card_video_item_image = (ImageView) itemView.findViewById(R.id.card_video_item_image);
            card_video_item_title=(TextView)itemView.findViewById(R.id.card_video_item_title);
            card_video_item_desc=(TextView)itemView.findViewById(R.id.card_video_item_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            shopByVideoInterafce.playShopByVideoMethod(outerHits.getHits().get(getAdapterPosition()).get_source()
            .getVideoUrl(),new SongByMovieFragmentItemClick(),outerHits.getHits().get(getAdapterPosition()).get_source()
            .getThumbnail(),"video",outerHits.getHits().get(getAdapterPosition()).get_source().getProducts());
        }
    }
}
