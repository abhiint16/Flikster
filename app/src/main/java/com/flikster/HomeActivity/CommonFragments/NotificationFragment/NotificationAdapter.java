package com.flikster.HomeActivity.CommonFragments.NotificationFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flikster.R;

/**
 * Created by abhishek on 20-10-2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    Context context;

    public NotificationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_no_data_available, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.ViewHolder holder, int position) {
        ((ViewHolder) holder).nodataavailtxt.setText("No Notification Available");
        ((ViewHolder) holder).backhomebtn.setVisibility(View.GONE);
        ((ViewHolder) holder).notifcationimg.setImageDrawable(context.getResources().getDrawable(R.drawable.notificationicon));

        /*if(position==0)
        {
            holder.notification_item_img.setImageResource(R.drawable.shoes);
        }
        else if(position==1)
        {
            holder.notification_item_img.setImageResource(R.drawable.shades);
            holder.notification_item_desc.setText("Your blue sunglasses have been sent. Thanks for shopping!");
            holder.notification_item_time.setText("20 days");
        }*/
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nodataavailtxt;
        Button backhomebtn;
        ImageView notifcationimg;

        public ViewHolder(View itemView) {
            super(itemView);
            nodataavailtxt = (TextView) itemView.findViewById(R.id.nodataavailtxt);
            backhomebtn = (Button) itemView.findViewById(R.id.backhomebtn);
            notifcationimg = (ImageView) itemView.findViewById(R.id.notifcationimg);
        }
    }
}
