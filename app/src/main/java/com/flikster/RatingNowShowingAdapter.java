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

public class RatingNowShowingAdapter extends RecyclerView.Adapter<RatingNowShowingAdapter.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    public RatingNowShowingAdapter(Context context,FragmentManager fragmentManager) {
        this.context=context;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public RatingNowShowingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_now_showing_frag_recycler_item,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RatingNowShowingAdapter.ViewHolder holder, int position) {

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
