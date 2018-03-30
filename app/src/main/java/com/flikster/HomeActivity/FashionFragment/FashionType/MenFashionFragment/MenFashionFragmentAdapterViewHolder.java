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
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductOnClick;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreInnerData;
import com.flikster.HomeActivity.FashionFragment.FashionType.BuyFashionTypeProductFragment.BuyFashionTypeProductFragment;
import com.flikster.HomeActivity.FashionFragment.FashionType.CommonAllProductPage.CommonAllProductPage;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

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
    MenFashionFirstTypeFragment.ShopByVideoMenInterafce shopByVideoMenInterafce;
    String profilePic="";
    String name="";
    String title="";
    List<String> role=new ArrayList<>();
    String price="";

    public MenFashionFragmentAdapterViewHolder(Context context, FragmentManager fragmentManager, AllStoreInnerData.AllStoreInnerMostData _source,
                                               MenFashionFirstTypeFragment.ShopByVideoMenInterafce shopByVideoMenInterafce) {
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");
        imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");
        imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
        this.fragmentManager = fragmentManager;
        this.context = context;
        this._source=_source;
        this.shopByVideoMenInterafce=shopByVideoMenInterafce;
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (_source.getCeleb().get(0).getProfilePic()!=null)
                        profilePic=_source.getCeleb().get(0).getProfilePic();
                    if(_source.getCeleb().get(0).getName()!=null)
                        name=_source.getCeleb().get(0).getName();
                    if (_source.getPrice()!=null)
                        price=_source.getPrice();
                    if (_source.getName()!=null)
                        title=_source.getName();
                    if (_source.getCeleb()!=null)
                    {
                        if (_source.getCeleb().get(0).getRole() != null &&
                                _source.getCeleb().get(0).getRole().size() != 0)
                            role.addAll(_source.getCeleb().get(0).getRole());
                    }
                    shopByVideoMenInterafce.onGalleryContainerClick(_source.getId(),_source.getSize(),"abhiint",_source.getPrice(),
                            _source.getProfilePic(),_source.getProductInfo(),_source.getSlug(),_source.getImageGallery(),
                            profilePic,role,name,title,
                            new CommonAllProductPage());
                }
            });
            buybtn = (Button) itemView.findViewById(R.id.buybtn);
            buybtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            checkForLoggedIn();
        }
    }

    public void checkForLoggedIn()
    {
        if (SharedPrefsUtil.getStringPreference(context,"IS_LOGGED_IN").equals("NOT_LOGGED_IN"))
        {
            Toast.makeText(context, "You need to first Login", Toast.LENGTH_SHORT).show();
            return;
        }
        shopByVideoMenInterafce.onBuyClick(_source.getId(),_source.getSize(),"abhiint",_source.getPrice(),
                _source.getProfilePic(),_source.getName(),_source.getSlug(),_source.getImageGallery(),
                _source.getProductDescription(),_source.getProductInfo(),new ProductOnClick());
    }
}
