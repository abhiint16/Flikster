package com.flikster.HomeActivity.CommonFragments.MyStyleFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flikster.R;
import com.flikster.Util.Common;
import com.flikster.Util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MyStyleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    List<String> imag = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    MyStyleAdapterViewHolder myStyleAdapterViewHolder;


    public MyStyleAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        type.add(1);
        type.add(2);
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");
        imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");
        imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_custom_style, parent, false);
            return new MyStyleAdapter.ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_my_style_bottom, parent, false);
            return new MyStyleAdapter.ViewHolder2(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview, parent, false);
            return new MyStyleAdapter.ViewHolder1(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
            String imgStr = SharedPrefsUtil.getStringPreference(context, "ImageString");
            Bitmap image = Common.StringToBitMap(imgStr);
            ((ViewHolder1) holder).captureimg.setImageBitmap(image);
        } else if (holder.getItemViewType() == 2) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
            myStyleAdapterViewHolder = new MyStyleAdapterViewHolder(context, fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_recycler.setAdapter(myStyleAdapterViewHolder);
        } else {
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView captureimg;

        public ViewHolder1(View itemView) {

            super(itemView);
            captureimg = (ImageView) itemView.findViewById(R.id.captureimg);

        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        RecyclerView fragment_common_recyclerview_recycler;

        public ViewHolder2(View itemView) {
            super(itemView);
            fragment_common_recyclerview_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_recycler);
        }
    }

}
