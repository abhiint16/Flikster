package com.flikster.HomeActivity;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductOnClick;
import com.flikster.R;

/**
 * Created by abhishek on 11-10-2017.
 */

public class ProfileCollectionRecyclerItemAdapter extends RecyclerView.Adapter<ProfileCollectionRecyclerItemAdapter.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    int a;
    Boolean b=true;
    public ProfileCollectionRecyclerItemAdapter(Context context,int a,FragmentManager fragmentManager) {
        this.context=context;
        this.a=a;
        this.fragmentManager=fragmentManager;
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
            holder.imageView.setImageResource(R.drawable.no_image);
        }
        else if(a==2)
        {
            //holder.imageView.setImageResource(R.drawable.ranveer2);
        }
        else if(a==3)
        {
            if(b==true)
            {
                //holder.imageView.setImageResource(R.drawable.legging1);
                b=false;
            }
            else if(b==false)
            {
                //holder.imageView.setImageResource(R.drawable.legging2);
                b=true;
            }
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            /*fragmentManager.beginTransaction()
                    .replace(R.id.main_container,new ProductOnClick())
                    .addToBackStack("")
                    .commit();*/
        }
    }
}
