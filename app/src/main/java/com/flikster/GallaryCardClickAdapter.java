package com.flikster;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by abhishek on 05-10-2017.
 */

public class GallaryCardClickAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    GlobalData globalData;
    Context context;
    FragmentManager fragmentManager;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CelebrityBioAdapterImagesViewHolder celebrityBioAdapterImagesViewHolder=new CelebrityBioAdapterImagesViewHolder();
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
            /*Intent intent=new Intent(context,GallaryFullScreen.class);
            context.startActivity(intent);*/
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.card_gallary_fullscreen);
            final Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }

    public  class ViewHolder2 extends RecyclerView.ViewHolder
    {
        TextView textView;
        RecyclerView recyclerView;
        public ViewHolder2(View itemView)
        {
            super(itemView);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.rc);
            recyclerView=(RecyclerView) itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);
        }
    }
}
