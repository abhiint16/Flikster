package com.flikster;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by abhishek on 04-10-2017.
 */

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder>{
    
    Context context;
    FragmentManager fragmentManager;
    public FeedRecyclerAdapter(Context context, FragmentManager fragmentManager) {
        this.context=context;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
