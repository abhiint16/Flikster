package com.flikster.HomeActivity.CommonFragments.ShopByVideoFragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flikster.Util.GlobalData;
import com.flikster.R;

/**
 * Created by abhishek on 01-11-2017.
 */

public class ShopByVideoFragmentItemClickAdapter extends RecyclerView.Adapter<ShopByVideoFragmentItemClickAdapter.ViewHolder> {
    GlobalData globalData=new GlobalData();
    @Override
    public ShopByVideoFragmentItemClickAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_shopby_video_recycler_itemclick_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopByVideoFragmentItemClickAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return globalData.style.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
