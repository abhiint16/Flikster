package com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongListItemWithProduct;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ShopByVideoData;
import com.flikster.R;
import com.flikster.Util.GlobalData;

import java.util.List;

/**
 * Created by abhishek on 01-11-2017.
 */

public class SongByMovieFragmentItemPlayClickAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    GlobalData globalData = new GlobalData();
    FragmentManager fragmentManager;
    Context context;
    List<ShopByVideoData.ShopByVideoInnerData.ShopByVideoInnerInnerData.ShopByVideoInnerMostData.ShopByVideoAllProduct> listOfProducts;
    public SongByMovieFragmentItemPlayClickAdapter(Context context, FragmentManager fragmentManager,
                                                   List<ShopByVideoData.ShopByVideoInnerData.ShopByVideoInnerInnerData.ShopByVideoInnerMostData.ShopByVideoAllProduct> listOfProducts)
    {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.listOfProducts=listOfProducts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_product_item, parent, false);
            return new ViewHolder1(view);
        }
        else
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_no_comments, parent, false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==1)
        {
            if (listOfProducts.get(position).getProductProfilePic()!=null)
            Glide.with(context).load(listOfProducts.get(position).getProductProfilePic()).into(((ViewHolder1)holder).card_fashion_men_item_img);
            if (listOfProducts.get(position).getProductTitle()!=null)
                ((ViewHolder1)holder).card_fashion_men_item_title.setText(listOfProducts.get(position).getProductTitle());
            if (listOfProducts.get(position).getProductBrand()!=null)
                ((ViewHolder1)holder).card_fashion_men_item_price.setText(listOfProducts.get(position).getProductBrand());
        }
        else
        {
            ((ViewHolder2)holder).activity_no_comments_tv.setText("No Product Available");
        }
    }

    @Override
    public int getItemCount() {
        if (listOfProducts!=null&&listOfProducts.size()!=0)
        return listOfProducts.size();
        else
            return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (listOfProducts!=null&&listOfProducts.size()!=0)
            return 1;
        else
            return 2;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_fashion_men_item_img;
        TextView card_fashion_men_item_title,card_fashion_men_item_price;

        public ViewHolder1(View itemView) {
            super(itemView);
            card_fashion_men_item_img = (ImageView) itemView.findViewById(R.id.card_fashion_men_item_img);
            card_fashion_men_item_title = (TextView) itemView.findViewById(R.id.card_fashion_men_item_title);
            card_fashion_men_item_price = (TextView) itemView.findViewById(R.id.card_fashion_men_item_price);
        }

        @Override
        public void onClick(View v) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new SongByMovieProductFragmentItemClick())
                    .addToBackStack("")
                    .commit();
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder
    {
        TextView activity_no_comments_tv;
        public ViewHolder2(View itemView)
        {
            super(itemView);
            activity_no_comments_tv=(TextView)itemView.findViewById(R.id.activity_no_comments_tv);
        }
    }

}
