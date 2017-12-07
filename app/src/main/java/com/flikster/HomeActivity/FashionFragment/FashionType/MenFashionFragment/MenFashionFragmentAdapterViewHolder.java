package com.flikster.HomeActivity.FashionFragment.FashionType.MenFashionFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreInnerData;
import com.flikster.HomeActivity.FashionFragment.FashionType.BuyFashionTypeProductFragment.BuyFashionTypeProductFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class MenFashionFragmentAdapterViewHolder extends RecyclerView.Adapter<MenFashionFragmentAdapterViewHolder.ViewHolder> {
    List<String> imag = new ArrayList<>();
    FragmentManager fragmentManager;
    Context context;
    AllStoreInnerData.AllStoreInnerMostData _source;

    public MenFashionFragmentAdapterViewHolder(Context context, FragmentManager fragmentManager,AllStoreInnerData.AllStoreInnerMostData _source) {
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");
        imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");
        imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
        this.fragmentManager = fragmentManager;
        this.context = context;
        this._source=_source;
        Log.e("checkbit",""+_source.getPrice());
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (_source.getImageGallery()!=null&&_source.getImageGallery().size()!=0)
        Glide.with(context).load(_source.getImageGallery().get(position)).into(holder.card_fashion_men_item_img);
        if (_source.getName()!=null)
        holder.card_fashion_men_item_title.setText(_source.getName());
        if (_source.getPrice()!=null)
        holder.card_fashion_men_item_price.setText(_source.getPrice()+" /-");
    }

    @Override
    public int getItemCount() {
        if (_source.getImageGallery()!=null&&_source.getImageGallery().size()!=0)
        return _source.getImageGallery().size();
        else return 0;
    }

    /*@Override
    public int getItemViewType(int position) {
        return 4;
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_fashion_men_item_img;
        TextView card_fashion_men_item_title,card_fashion_men_item_price;
        Button buybtn;
        public ViewHolder(View itemView) {
            super(itemView);
            card_fashion_men_item_img = (ImageView) itemView.findViewById(R.id.card_fashion_men_item_img);
            card_fashion_men_item_price=(TextView)itemView.findViewById(R.id.card_fashion_men_item_price);
            card_fashion_men_item_title=(TextView)itemView.findViewById(R.id.card_fashion_men_item_title);
            buybtn = (Button) itemView.findViewById(R.id.buybtn);
//            movieimg.setOnClickListener(this);
            buybtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new BuyFashionTypeProductFragment())
                    .addToBackStack("")
                    .commit();
        }
    }
}
