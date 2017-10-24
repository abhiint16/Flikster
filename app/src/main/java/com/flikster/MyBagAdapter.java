package com.flikster;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 24-10-2017.
 */

public class MyBagAdapter extends RecyclerView.Adapter<MyBagAdapter.ViewHolder> {
    List<Integer> color=new ArrayList<>();
    List<String> title=new ArrayList<>();
    List<String> desc=new ArrayList<>();
    List<Integer> img=new ArrayList<>();

    public MyBagAdapter() {
        color.add(R.color.colorAccent);color.add(R.color.colorPrimary);color.add(R.color.colorCreateAccountSelected);
        color.add(R.color.colorAuthenticationHeader);
        title.add("Men's Capri Athletic Shoes");title.add("Arjun Reddy Glasses");title.add("Men's Capri Athletic Shoes");
        title.add("Arjun Reddy Glasses");
        desc.add("Sperry Shoes");desc.add("Round Glasses");desc.add("Sperry Shoes");desc.add("Round Glasses");
        img.add(R.drawable.shoes);img.add(R.drawable.shades);img.add(R.drawable.shoes);img.add(R.drawable.shades);
    }

    @Override
    public MyBagAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mybag_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyBagAdapter.ViewHolder holder, int position) {
        holder.notification_item_img.setImageResource(img.get(position));
        holder.notification_item_title.setText(title.get(position));
        holder.notification_item_desc.setText(desc.get(position));
        holder.notification_item_color.setBackgroundColor(color.get(position));
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notification_item_title,notification_item_desc;
        ImageView notification_item_img;
        Button notification_item_color;
        public ViewHolder(View itemView) {
            super(itemView);
            notification_item_color=(Button)itemView.findViewById(R.id.notification_item_color);
            notification_item_desc=(TextView)itemView.findViewById(R.id.notification_item_desc);
            notification_item_title=(TextView)itemView.findViewById(R.id.notification_item_title);
            notification_item_img=(ImageView)itemView.findViewById(R.id.notification_item_img);
        }
    }
}
