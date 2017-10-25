package com.flikster;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by abhishek on 25-10-2017.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_checkout_customer_details,parent,false);
            return new ViewHolder1(view);
        }
        else if(viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mybag_recycler_item,parent,false);
            return new ViewHolder2(view);
        }
        else if(viewType==2)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_checkout_promo,parent,false);
            return new ViewHolder3(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fragment_mybag_order_summary,parent,false);
            return new ViewHolder4(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==1)
        {
            ((ViewHolder2)holder).notification_item_cancel_btn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageButton notification_item_cancel_btn;
        public ViewHolder2(View itemView) {
            super(itemView);
            notification_item_cancel_btn=(ImageButton)itemView.findViewById(R.id.notification_item_cancel_btn);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {
        public ViewHolder3(View itemView) {
            super(itemView);
        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder {
        public ViewHolder4(View itemView) {
            super(itemView);
        }
    }
}
