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
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class MenFashionHorAdapterViewHolder extends RecyclerView.Adapter<MenFashionHorAdapterViewHolder.ViewHolder> {
    FragmentManager fragmentManager;
    Context context;
    AllStoreInnerData.AllStoreInnerMostData _source;
    MenFashionFirstTypeFragment.ShopByVideoMenInterafce shopByVideoMenInterafce;
    String profilePic="";
    String name="";
    String title="";
    List<String> role=new ArrayList<>();
    String price="";

    public MenFashionHorAdapterViewHolder(Context context, FragmentManager fragmentManager, AllStoreInnerData.AllStoreInnerMostData _source,
                                          MenFashionFirstTypeFragment.ShopByVideoMenInterafce shopByVideoMenInterafce) {
        this.fragmentManager = fragmentManager;
        this.context = context;
        this._source=_source;
        this.shopByVideoMenInterafce=shopByVideoMenInterafce;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_product_singleimg, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (_source.getImageGallery()!=null&&_source.getImageGallery().size()!=0)
        Glide.with(context).load(_source.getImageGallery().get(position)).into(holder.card_fashion_product_singleimg_img);
        if (_source.getName()!=null)
        holder.card_fashion_product_singleimg_title.setText(_source.getName());
        if (_source.getBrand()!=null)
        holder.card_fashion_product_singleimg_brandname.setText(_source.getBrand());
    }

    @Override
    public int getItemCount() {
        if (_source.getImageGallery()!=null&&_source.getImageGallery().size()!=0)
        return _source.getImageGallery().size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_fashion_product_singleimg_img;
        TextView card_fashion_product_singleimg_title,card_fashion_product_singleimg_brandname;
        Button buybtn_hor_fashion_men;
        public ViewHolder(View itemView) {
            super(itemView);
            card_fashion_product_singleimg_img = (ImageView) itemView.findViewById(R.id.card_fashion_product_singleimg_img);
            card_fashion_product_singleimg_title=(TextView)itemView.findViewById(R.id.card_fashion_product_singleimg_title);
            card_fashion_product_singleimg_brandname=(TextView)itemView.findViewById(R.id.card_fashion_product_singleimg_brandname);
            buybtn_hor_fashion_men=(Button)itemView.findViewById(R.id.buybtn_hor_fashion_men);
            buybtn_hor_fashion_men.setOnClickListener(this);
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
