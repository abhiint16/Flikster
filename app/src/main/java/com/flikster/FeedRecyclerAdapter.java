package com.flikster;

import android.content.Context;
import android.media.Image;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 04-10-2017.
 */

public class FeedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    GlobalData globalData=new GlobalData();
    Context context;
    FragmentManager fragmentManager;
    RecyclerView.LayoutManager stealStyleLayoutManager,profileCollectionRecyclerLayoutManager;
    StealStyleViewHolder stealStyleViewHolder;
    ProfileCollectionRecyclerItemAdapter profileCollectionRecyclerItemAdapter;
    public FeedRecyclerAdapter(Context context, FragmentManager fragmentManager) {
        this.context=context;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1,parent,false);
            return  new ViewHolder1(view);
        }
        else if(viewType==2)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_steal_style_carousel,parent,false);
            return  new ViewHolder2(view);
        }
        else if(viewType==3)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary3_1,parent,false);
            return  new ViewHolder3(view);
        }
        else if(viewType==6)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile_collection,parent,false);
            return new ViewHolder6(view);
        }
        else if(viewType==4)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary4_1,parent,false);
            return  new ViewHolder4(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary_4plus,parent,false);
            return  new ViewHolder5(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==1)
        {
                Glide.with(((ViewHolder1)holder).itemView.getContext()).load(globalData.imag.get(0))
                .into(((ViewHolder1)holder).card_gallary1_img1);

        }
        else if(holder.getItemViewType()==2)
        {
            stealStyleLayoutManager=new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2)holder).card_steal_style_recycler.setLayoutManager(stealStyleLayoutManager);
            stealStyleViewHolder=new StealStyleViewHolder(((ViewHolder2)holder).itemView.getContext());
            ((ViewHolder2)holder).card_steal_style_recycler.setAdapter(stealStyleViewHolder);
        }
        else if(holder.getItemViewType()==3)
        {
            Glide.with(((ViewHolder3)holder).itemView.getContext()).load(globalData.imag.get(2))
                    .into(((ViewHolder3)holder).card_gallary3_img1);
            Glide.with(((ViewHolder3)holder).itemView.getContext()).load(globalData.imag.get(3))
                    .into(((ViewHolder3)holder).card_gallary3_img2);
            Glide.with(((ViewHolder3)holder).itemView.getContext()).load(globalData.imag.get(4))
                    .into(((ViewHolder3)holder).card_gallary3_img3);
        }
        else if(holder.getItemViewType()==4)
        {
            Glide.with(((ViewHolder4)holder).itemView.getContext()).load(globalData.imag.get(4))
                    .into(((ViewHolder4)holder).card_gallary4_img1);
            Glide.with(((ViewHolder4)holder).itemView.getContext()).load(globalData.imag.get(5))
                    .into(((ViewHolder4)holder).card_gallary4_img2);
            Glide.with(((ViewHolder4)holder).itemView.getContext()).load(globalData.imag.get(6))
                    .into(((ViewHolder4)holder).card_gallary4_img3);
            Glide.with(((ViewHolder4)holder).itemView.getContext()).load(globalData.imag.get(7))
                    .into(((ViewHolder4)holder).card_gallary4_img4);
        }
        else if(holder.getItemViewType()==6)
        {
            profileCollectionRecyclerLayoutManager=new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6)holder).recyclerView.setLayoutManager(profileCollectionRecyclerLayoutManager);
            profileCollectionRecyclerItemAdapter=new ProfileCollectionRecyclerItemAdapter(((ViewHolder6)holder).itemView.getContext());
            ((ViewHolder6)holder).recyclerView.setAdapter(profileCollectionRecyclerItemAdapter);
        }
        else
        {
            Glide.with(((ViewHolder5)holder).itemView.getContext()).load(globalData.imag.get(5))
                    .into(((ViewHolder5)holder).card_gallary5_img1);
            Glide.with(((ViewHolder5)holder).itemView.getContext()).load(globalData.imag.get(2))
                    .into(((ViewHolder5)holder).card_gallary5_img2);
            Glide.with(((ViewHolder5)holder).itemView.getContext()).load(globalData.imag.get(8))
                    .into(((ViewHolder5)holder).card_gallary5_img3);
            Glide.with(((ViewHolder5)holder).itemView.getContext()).load(globalData.imag.get(4))
                    .into(((ViewHolder5)holder).card_gallary5_img4);
            ((ViewHolder5)holder).card_gallary5_text.setText("+"+(globalData.type.get(position)-4)+"More");
        }
    }

    @Override
    public int getItemCount() {
        return globalData.type.size();
    }

    @Override
    public int getItemViewType(int position) {
        return  globalData.type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1;
        public ViewHolder1(View itemView) {
            super(itemView);
            card_gallary1_img1=(ImageView)itemView.findViewById(R.id.card_gallary1_img1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a=1;
            fragmentManager.beginTransaction()
                    .add(R.id.main_container,new CelebrityFragment())
                    .addToBackStack("")
                    .commit();
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView card_steal_style_recycler;
        public ViewHolder2(View itemView) {
            super(itemView);
            card_steal_style_recycler=(RecyclerView)itemView.findViewById(R.id.card_steal_style_recycler);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a=2;
            fragmentManager.beginTransaction()
                    .add(R.id.main_container,new GallaryCardClick())
                    .addToBackStack("")
                    .commit();
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary3_img1,card_gallary3_img2,card_gallary3_img3;
        public ViewHolder3(View itemView) {
            super(itemView);
            card_gallary3_img1=(ImageView)itemView.findViewById(R.id.card_gallary3_img1);
            card_gallary3_img2=(ImageView)itemView.findViewById(R.id.card_gallary3_img2);
            card_gallary3_img3=(ImageView)itemView.findViewById(R.id.card_gallary3_img3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a=3;
            fragmentManager.beginTransaction()
                    .add(R.id.main_container,new GallaryCardClick())
                    .addToBackStack("")
                    .commit();
        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary4_img1,card_gallary4_img2,card_gallary4_img3,card_gallary4_img4;
        public ViewHolder4(View itemView) {
            super(itemView);
            card_gallary4_img1=(ImageView)itemView.findViewById(R.id.card_gallary4_img1);
            card_gallary4_img2=(ImageView)itemView.findViewById(R.id.card_gallary4_img2);
            card_gallary4_img3=(ImageView)itemView.findViewById(R.id.card_gallary4_img3);
            card_gallary4_img4=(ImageView)itemView.findViewById(R.id.card_gallary4_img4);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a=4;
            fragmentManager.beginTransaction()
                    .add(R.id.main_container,new GallaryCardClick())
                    .addToBackStack("")
                    .commit();
        }
    }
    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary5_img1,card_gallary5_img2,card_gallary5_img3,card_gallary5_img4;
        TextView card_gallary5_text;
        public ViewHolder5(View itemView) {
            super(itemView);
            card_gallary5_img1=(ImageView)itemView.findViewById(R.id.card_gallary5_img1);
            card_gallary5_img2=(ImageView)itemView.findViewById(R.id.card_gallary5_img2);
            card_gallary5_img3=(ImageView)itemView.findViewById(R.id.card_gallary5_img3);
            card_gallary5_img4=(ImageView)itemView.findViewById(R.id.card_gallary5_img4);
            card_gallary5_text=(TextView)itemView.findViewById(R.id.card_gallary5_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a=globalData.type.get(getAdapterPosition());
            fragmentManager.beginTransaction()
                    .add(R.id.main_container,new GallaryCardClick())
                    .addToBackStack("")
                    .commit();
        }
    }
    public class ViewHolder6 extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecyclerView recyclerView;
        public ViewHolder6(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.rc);
        }

        @Override
        public void onClick(View view) {
            globalData.a=globalData.type.get(getAdapterPosition());
            fragmentManager.beginTransaction()
                    .add(R.id.main_container,new GallaryCardClick())
                    .addToBackStack("")
                    .commit();
        }
    }
}
