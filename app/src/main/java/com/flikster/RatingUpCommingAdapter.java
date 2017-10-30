package com.flikster;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by abhishek on 30-10-2017.
 */

public class RatingUpCommingAdapter extends RecyclerView.Adapter<RatingUpCommingAdapter.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    public RatingUpCommingAdapter(Context context, FragmentManager fragmentManager) {
        this.context=context;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public RatingUpCommingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_up_comming_frag_recycler_item,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RatingUpCommingAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
