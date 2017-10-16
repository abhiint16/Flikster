package com.flikster;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by abhishek on 05-10-2017.
 */

public class GallaryCardClickAdapter extends RecyclerView.Adapter<GallaryCardClickAdapter.ViewHolder> {
    GlobalData globalData;
    Context context;
    FragmentManager fragmentManager;
    public GallaryCardClickAdapter(Context context, FragmentManager fragmentManager) {
        Log.e("adapter cons","adapter cons");
        this.context=context;
        globalData=new GlobalData();
        this.fragmentManager=fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("oncraeteviewholder","oncraeteviewholder");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary_item_onclick_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("onbindviewholder","onbindviewholder");
        //holder.gallary_recycler_item_img.setContentDescription("aaaaaaaaaaaa");
        //Glide.with(holder.itemView.getContext()).load(globalData.imag.get(position)).into(holder.gallary_recycler_item_img);
        holder.gallary_recycler_item_img.setImageResource(globalData.pooja.get(position));

    }

    @Override
    public int getItemCount() {
        Log.e(" "+globalData.a," "+globalData.a);
        return globalData.pooja.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView gallary_recycler_item_img;
        public ViewHolder(View itemView) {
            super(itemView);
            Log.e("Viewholde","viewholder");
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
}
