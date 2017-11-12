package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

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
import com.flikster.HomeActivity.ProfileCollectionRecyclerItemAdapter;
import com.flikster.R;
import com.flikster.HomeActivity.StealStyleViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-10-2017.
 */

public class CelebrityStoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type=new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    ProfileCollectionRecyclerItemAdapter profileCollectionRecyclerItemAdapter;
    CelebrityBioAdapterVideoViewHolder celebrityBioAdapterVideoViewHolder;
    StealStyleViewHolder stealStyleViewHolder;
    RecyclerView.LayoutManager layoutManager2;
    String profilepic;
    String coverpic;
    String name;
    ArrayList<String> role=new ArrayList<>();
    public CelebrityStoreAdapter(Context context, FragmentManager fragmentManager,String profilepic,String coverpic,
                                 String name,ArrayList<String> role) {
        this.context=context;
        this.fragmentManager=fragmentManager;
        type.add(1);
        type.add(2);type.add(3);type.add(4);type.add(5);type.add(6);type.add(7);type.add(8);type.add(9);type.add(3);type.add(4);
        type.add(3);type.add(4);type.add(7);
        this.profilepic=profilepic;
        this.coverpic=coverpic;
        this.name=name;
        this.role=role;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_profile,parent,false);
            return new CelebrityStoreAdapter.ViewHolder1(view);
        }
        else if(viewType==2)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_store_gallary4_1,parent,false);
            return new CelebrityStoreAdapter.ViewHolder2(view);
        }
        else if(viewType==3)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_store_profile_collection,parent,false);
            return new CelebrityStoreAdapter.ViewHolder3(view);
        }
        else if(viewType==4)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv,parent,false);
            return new CelebrityStoreAdapter.ViewHolder4(view);
        }
        else if(viewType==5)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_store_gallary4_1,parent,false);
            return new CelebrityStoreAdapter.ViewHolder5(view);
        }
        else
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_steal_style_carousel,parent,false);
            return new CelebrityStoreAdapter.ViewHolder6(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==1)
        {
            ((CelebrityStoreAdapter.ViewHolder1)holder).card_celebrity_feed_profile_name.setText(name);
            ((CelebrityStoreAdapter.ViewHolder1)holder).card_celebrity_feed_profile_role.setText(role.get(0));
            Glide.with(context).load(profilepic).asBitmap()
                    .into(((CelebrityStoreAdapter.ViewHolder1)holder).card_celebrity_feed_profile_image);
            Glide.with(context).load(coverpic).asBitmap()
                    .into(((CelebrityStoreAdapter.ViewHolder1)holder).card_celebrity_feed_profile_coverpic);
        }
        else if(holder.getItemViewType()==2)
        {

        }
        else if(holder.getItemViewType()==3)
        {
            layoutManager=new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder3)holder).fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
            profileCollectionRecyclerItemAdapter =new ProfileCollectionRecyclerItemAdapter(context,3,fragmentManager);
            ((ViewHolder3)holder).fragment_common_recyclerview_recycler.setAdapter(profileCollectionRecyclerItemAdapter);
        }
        else if(holder.getItemViewType()==4)
        {
            ((ViewHolder4)holder).fragment_common_recyclerview_with_tv_title.setText("Shop By videos");
            layoutManager=new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder4)holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterVideoViewHolder =new CelebrityBioAdapterVideoViewHolder(fragmentManager);
            ((ViewHolder4)holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterVideoViewHolder);
        }

        else if(holder.getItemViewType()==5)
        {
            ((ViewHolder5)holder).card_store_gallary4_img1.setImageResource(R.drawable.top1);
            ((ViewHolder5)holder).card_store_gallary4_img2.setImageResource(R.drawable.top2);
            ((ViewHolder5)holder).card_store_gallary4_img3.setImageResource(R.drawable.jhumka);
            ((ViewHolder5)holder).card_store_gallary4_img4.setImageResource(R.drawable.sandal);
            ((ViewHolder5)holder).card_store_product_gallary_title.setText("Latest trend");
            ((ViewHolder5)holder).card_store_product_gallary_cost.setText("7500/-");
        }
        else if(holder.getItemViewType()==6)
        {
            ((ViewHolder6)holder).card_steal_style_carousel_title.setText("Recommended Products");
            ((ViewHolder6)holder).fragment_common_recyclerview_with_tv_title.setVisibility(View.GONE);
            layoutManager2=new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6)holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager2);
            stealStyleViewHolder=new StealStyleViewHolder("celebrity_store");
            ((ViewHolder6)holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(stealStyleViewHolder);
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView card_celebrity_feed_profile_image,card_celebrity_feed_profile_coverpic;
        TextView card_celebrity_feed_profile_name,card_celebrity_feed_profile_role;
        public ViewHolder1(View itemView) {
            super(itemView);
            card_celebrity_feed_profile_image=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_profile_image);
            card_celebrity_feed_profile_coverpic=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_profile_coverpic);
            card_celebrity_feed_profile_name=(TextView)itemView.findViewById(R.id.card_celebrity_feed_profile_name);
            card_celebrity_feed_profile_role=(TextView)itemView.findViewById(R.id.card_celebrity_feed_profile_role);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
        public ViewHolder2(View itemView) {
            super(itemView);
        /*    textView=(TextView)itemView.findViewById(R.id.txt);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);*/
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView fragment_common_recyclerview_recycler;
        public ViewHolder3(View itemView) {
            super(itemView);
            //textView=(TextView)itemView.findViewById(R.id.txt);
            fragment_common_recyclerview_recycler=(RecyclerView)itemView.findViewById(R.id.fragment_common_recyclerview_recycler);
        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;
        public ViewHolder4(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title=(TextView)itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler=(RecyclerView)itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }
    }
    public class ViewHolder5 extends RecyclerView.ViewHolder {
        TextView card_store_product_gallary_title,card_store_product_gallary_cost;
        ImageView card_store_gallary4_img1,card_store_gallary4_img2,card_store_gallary4_img3,card_store_gallary4_img4;
        public ViewHolder5(View itemView) {
            super(itemView);
            card_store_product_gallary_title=(TextView)itemView.findViewById(R.id.card_store_product_gallary_title);
            card_store_product_gallary_cost=(TextView)itemView.findViewById(R.id.card_store_product_gallary_cost);
            card_store_gallary4_img1=(ImageView)itemView.findViewById(R.id.card_store_gallary4_img1);
            card_store_gallary4_img2=(ImageView)itemView.findViewById(R.id.card_store_gallary4_img2);
            card_store_gallary4_img3=(ImageView)itemView.findViewById(R.id.card_store_gallary4_img3);
            card_store_gallary4_img4=(ImageView)itemView.findViewById(R.id.card_store_gallary4_img4);
        }
    }
    public class ViewHolder6 extends RecyclerView.ViewHolder {
        TextView card_steal_style_carousel_title,fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;
        public ViewHolder6(View itemView) {
            super(itemView);
            card_steal_style_carousel_title=(TextView)itemView.findViewById(R.id.card_steal_style_carousel_title);
            fragment_common_recyclerview_with_tv_title=(TextView)itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler=(RecyclerView)itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }
    }


}
