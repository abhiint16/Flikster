package com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreInnerItems;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductOnClick;
import com.flikster.HomeActivity.WidgetData;
import com.flikster.R;

/**
 * Created by abhishek on 11-10-2017.
 */

public class CeleStoreRecyclerItemAdapter extends RecyclerView.Adapter<CeleStoreRecyclerItemAdapter.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    Boolean b=true;
    WidgetData.WidgetInnerData widgetData;
    Profile_collection_inner_recycler_adapter profile_collection_inner_recycler_adapter;
    RecyclerView.LayoutManager layoutManager;

    public CeleStoreRecyclerItemAdapter(Context context,FragmentManager fragmentManager, WidgetData.WidgetInnerData widgetData) {
        this.context=context;
        this.fragmentManager=fragmentManager;
        this.widgetData=widgetData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile_collection,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(widgetData.getHits().get(position).get_source().getImage()!=null)
        Glide.with(context).load(widgetData.getHits().get(position).get_source().getImage().trim()).into(holder.card_profile_collection_main_img);
        if(widgetData.getHits().get(position).get_source().getTitle()!=null)
            holder.card_profile_collection_main_title.setText(widgetData.getHits().get(position).get_source().getTitle());
        if(widgetData.getHits().get(position).get_source().getItem()!=null)
        {
            if(widgetData.getHits().get(position).get_source().getItem().getName()!=null)
                holder.card_profile_collection_celeb_name.setText(widgetData.getHits().get(position).get_source().getItem().getName());
            if(widgetData.getHits().get(position).get_source().getItem().getProfilePic()!=null)
                Glide.with(context).load(widgetData.getHits().get(position).get_source().getItem().getProfilePic().trim()).
                        asBitmap().
                        into(holder.profile_collection_dp);
            if(widgetData.getHits().get(position).get_source().getItem().getRole()!=null&&
                    widgetData.getHits().get(position).get_source().getItem().getRole().size()!=0)
                holder.card_profile_collection_role.setText(widgetData.getHits().get(position).get_source().getItem().getRole().get(0));
        }
        profile_collection_inner_recycler_adapter =new Profile_collection_inner_recycler_adapter(context,fragmentManager,widgetData,position);
        layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
        holder.fragment_common_recyclerview_recycler.setAdapter(profile_collection_inner_recycler_adapter);
    }

    @Override
    public int getItemCount() {
        return widgetData.getHits().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView fragment_common_recyclerview_recycler;
        ImageView card_profile_collection_main_img,profile_collection_dp;
        TextView card_profile_collection_main_title,card_profile_collection_celeb_name,card_profile_collection_role;
        public ViewHolder(View itemView) {
            super(itemView);
            fragment_common_recyclerview_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_recycler);
            card_profile_collection_main_img=(ImageView)itemView.findViewById(R.id.card_profile_collection_main_img);
            profile_collection_dp=(ImageView)itemView.findViewById(R.id.profile_collection_dp);
            card_profile_collection_main_title=(TextView)itemView.findViewById(R.id.card_profile_collection_main_title);
            card_profile_collection_celeb_name=(TextView)itemView.findViewById(R.id.card_profile_collection_celeb_name);
            card_profile_collection_role=(TextView)itemView.findViewById(R.id.card_profile_collection_role);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container,new ProductOnClick())
                    .addToBackStack("")
                    .commit();
        }
    }
}
