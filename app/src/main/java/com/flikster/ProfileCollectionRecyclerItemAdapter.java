package com.flikster;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by abhishek on 11-10-2017.
 */

public class ProfileCollectionRecyclerItemAdapter extends RecyclerView.Adapter<ProfileCollectionRecyclerItemAdapter.ViewHolder> {
    Context context;
    int a;
    public ProfileCollectionRecyclerItemAdapter(Context context,int a) {
        this.context=context;
        this.a=a;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile_collection_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(a==1)
        {
            holder.imageView.setImageResource(R.drawable.pooja2);
        }
        else if(a==2)
        {
            holder.imageView.setImageResource(R.drawable.ranveer2);
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView3);
        }
    }
}
