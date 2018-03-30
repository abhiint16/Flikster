package com.flikster.HomeActivity.FashionFragment.FashionNewDesign;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductOnClick;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreData;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreInnerData;
import com.flikster.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 07-03-2018.
 */

public class FashionHorRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    AllStoreInnerData hits;
    Context context;
    FashionFragmentNew.FashionOnClick fashionOnClick;
    ApiInterface apiInterface;
    String fashionURL;
    int count=5;

    public FashionHorRecyclerAdapter(Context context, AllStoreInnerData hits, FashionFragmentNew.FashionOnClick fashionOnClick,
                                     String fashionURL) {
        this.hits = hits;
        this.context = context;
        this.fashionOnClick=fashionOnClick;
        this.fashionURL=fashionURL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blank_page, parent, false);
            return new ViewHolder0(view);
        }
        else if (viewType==1)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_fashion_hor_recycler_item, parent, false);
            return new ViewHolder1(view);
        }
        else if (viewType==2)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hor_last_item_load_more, parent, false);
            return new ViewHolder2(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==1)
        {
            if (hits.getHits().get(position-1).get_source().getImageGallery()!=null&&
                    hits.getHits().get(position-1).get_source().getImageGallery().size()!=0)
            {
                Glide.with(context).load(hits.getHits().get(position-1).get_source().getImageGallery().get(0))
                        .into(((ViewHolder1) holder).fragment_fashion_hor_recycler_item_imageview);
            }
            else if (hits.getHits().get(position-1).get_source().getProfilePic()!=null)
            {
                Glide.with(context).load(hits.getHits().get(position-1).get_source().getProfilePic())
                        .into(((ViewHolder1) holder).fragment_fashion_hor_recycler_item_imageview);
            }
            if (hits.getHits().get(position-1).get_source().getProductInfo()!=null)
            {
                ((ViewHolder1) holder).fragment_fashion_hor_recycler_item_title.setText(hits.getHits().get(position-1).get_source().getProductInfo());
            }
            if (hits.getHits().get(position-1).get_source().getCeleb()!=null)
            {
                if (hits.getHits().get(position-1).get_source().getCeleb().get(0).getName()!=null)
                {
                    ((ViewHolder1) holder).fragment_fashion_hor_recycler_item_tag.setText("#"+hits.getHits().get(position-1).get_source().getCeleb().get(0).getName());
                }
            }
        }
        else if (holder.getItemViewType()==2)
        {
            String finalURL;
            String lastSubString;
            finalURL=fashionURL.substring(0,95);
            lastSubString=fashionURL.substring(96);
            Log.e("checkf or url","check for url"+finalURL);
            Log.e("checkf or url","check for url"+lastSubString);
            loadMore(finalURL+count+lastSubString);
        }
    }

    private void loadMore(String urlString) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/products/_search/").create(ApiInterface.class);
        Call<AllStoreData> call = apiInterface.getAllStore(urlString);
        call.enqueue(new Callback<AllStoreData>() {
            @Override
            public void onResponse(Call<AllStoreData> call, Response<AllStoreData> response) {
                count=count+5;
                hits.getHits().addAll(response.body().getHits().getHits());
                notifyItemRangeChanged(count-4,5);
            }

            @Override
            public void onFailure(Call<AllStoreData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (hits.getHits().size()!=0)
        {
            if (hits.getHits().size()!=hits.getTotal())
                return hits.getHits().size()+2;
            else return hits.getHits().size()+1;
        }
        else
            return 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (position==0)
            return 0;
        else if (position>hits.getHits().size())
            return 2;
        else
            return 1;
    }

    public class ViewHolder0 extends RecyclerView.ViewHolder
    {

        public ViewHolder0(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView fragment_fashion_hor_recycler_item_imageview;
        LinearLayout fashion_hor_recycler_adapter_item_container;
        TextView fragment_fashion_hor_recycler_item_title,fragment_fashion_hor_recycler_item_tag;
        public ViewHolder1(View itemView) {
            super(itemView);
            fashion_hor_recycler_adapter_item_container=(LinearLayout)itemView.findViewById(R.id.fashion_hor_recycler_adapter_item_container);
            fragment_fashion_hor_recycler_item_imageview=(ImageView)itemView.findViewById(R.id.fragment_fashion_hor_recycler_item_imageview);
            fragment_fashion_hor_recycler_item_tag=(TextView)itemView.findViewById(R.id.fragment_fashion_hor_recycler_item_tag);
            fragment_fashion_hor_recycler_item_title=(TextView)itemView.findViewById(R.id.fragment_fashion_hor_recycler_item_title);
            fashion_hor_recycler_adapter_item_container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fashionOnClick.onBuyClick(hits.getHits().get(getAdapterPosition()-1).get_source().getId(),
                    hits.getHits().get(getAdapterPosition()-1).get_source().getSize(), "abhiint",
                    hits.getHits().get(getAdapterPosition()-1).get_source().getPrice(),
                    hits.getHits().get(getAdapterPosition()-1).get_source().getProfilePic(),
                    hits.getHits().get(getAdapterPosition()-1).get_source().getName(),
                    hits.getHits().get(getAdapterPosition()-1).get_source().getSlug(),
                    hits.getHits().get(getAdapterPosition()-1).get_source().getImageGallery(),
                    hits.getHits().get(getAdapterPosition()-1).get_source().getProductDescription(),
                    hits.getHits().get(getAdapterPosition()-1).get_source().getProductInfo(),new ProductOnClick());
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView hor_last_item_load_more_txt;
        public ViewHolder2(View itemView) {
            super(itemView);
            //hor_last_item_load_more_txt=(TextView)itemView.findViewById(R.id.hor_last_item_load_more_txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
