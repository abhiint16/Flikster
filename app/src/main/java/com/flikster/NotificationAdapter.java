package com.flikster;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by abhishek on 20-10-2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.ViewHolder holder, int position) {
        if(position==0)
        {
            holder.notification_item_img.setImageResource(R.drawable.shoes);
        }
        else if(position==1)
        {
            holder.notification_item_img.setImageResource(R.drawable.shades);
            holder.notification_item_desc.setText("Your blue sunglasses have been sent. Thanks for shopping!");
            holder.notification_item_time.setText("20 days");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView notification_item_img;
        TextView notification_item_title,notification_item_desc,notification_item_time;
        public ViewHolder(View itemView) {
            super(itemView);
            notification_item_img=(ImageView)itemView.findViewById(R.id.notification_item_img);
            notification_item_title=(TextView)itemView.findViewById(R.id.notification_item_title);
            notification_item_desc=(TextView)itemView.findViewById(R.id.notification_item_desc);
            notification_item_time=(TextView)itemView.findViewById(R.id.notification_item_time);
        }
    }
}
