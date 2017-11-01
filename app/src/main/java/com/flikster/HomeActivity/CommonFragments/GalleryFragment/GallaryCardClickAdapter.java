package com.flikster.HomeActivity.CommonFragments.GalleryFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flikster.Util.GlobalData;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.R;

/**
 * Created by abhishek on 05-10-2017.
 */

public class GallaryCardClickAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    GlobalData globalData;
    Context context;
    FragmentManager fragmentManager;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CelebrityBioAdapterImagesViewHolder celebrityBioAdapterImagesViewHolder=new CelebrityBioAdapterImagesViewHolder(context);
    public GallaryCardClickAdapter(Context context, FragmentManager fragmentManager) {
        this.context=context;
        globalData=new GlobalData();
        this.fragmentManager=fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary_item_onclick_recycler_item,parent,false);
            return new ViewHolder1(view);
        }
        else
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_videos_carousel, parent, false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //holder.gallary_recycler_item_img.setContentDescription("aaaaaaaaaaaa");
        //Glide.with(holder.itemView.getContext()).load(globalData.imag.get(position)).into(holder.gallary_recycler_item_img);
        if(holder.getItemViewType()==0)
        {
            ((ViewHolder1)holder).gallary_recycler_item_img.setImageResource(globalData.pooja.get(position));
        }
        else
        {
            ((ViewHolder2)holder).textView.setText("Recommended Gallary");
            layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            ((ViewHolder2)holder).recyclerView.setLayoutManager(layoutManager);
            ((ViewHolder2)holder).recyclerView.setAdapter(celebrityBioAdapterImagesViewHolder);
        }
    }

    @Override
    public int getItemCount() {
        return globalData.pooja.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if((position)==globalData.pooja.size())
            return 1;
        else
            return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView gallary_recycler_item_img;
        public ViewHolder1(View itemView) {
            super(itemView);
            gallary_recycler_item_img=(ImageView)itemView.findViewById(R.id.gallary_recycler_item_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context,GallaryFullScreen.class);
            context.startActivity(intent);
        }
    }

    public  class ViewHolder2 extends RecyclerView.ViewHolder
    {
        TextView textView;
        RecyclerView recyclerView;
        public ViewHolder2(View itemView)
        {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.txt);
            recyclerView=(RecyclerView) itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);
        }
    }
}
