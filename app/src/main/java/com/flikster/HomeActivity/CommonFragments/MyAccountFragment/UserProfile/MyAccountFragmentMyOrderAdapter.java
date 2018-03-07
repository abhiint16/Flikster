package com.flikster.HomeActivity.CommonFragments.MyAccountFragment.UserProfile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.R;

import retrofit2.Response;

/**
 * Created by abhishek on 21-10-2017.
 */

public class MyAccountFragmentMyOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Response<MyOrderData> response;
    Context context;
    public MyAccountFragmentMyOrderAdapter(Response<MyOrderData> response, Context context) {
        this.response=response;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mybag_recycler_item,parent,false);
            return new ViewHolder1(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_no_comments,parent,false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==1)
        {
            ((ViewHolder1)holder).buy_click_product_quantity_minus_btn.setVisibility(View.GONE);
            ((ViewHolder1)holder).buy_click_product_quantity_plus_btn.setVisibility(View.GONE);
            ((ViewHolder1)holder).notification_item_cancel_btn.setVisibility(View.GONE);
            if (response.body().getHits().getHits().get(position).get_source().getProduct().get(0).getProductPic()!=null)
                Glide.with(((ViewHolder1)holder).itemView.getContext()).load(response.body().getHits().getHits().get(position).get_source().getProduct().get(0).getProductPic())
                        .asBitmap().into(((ViewHolder1)holder).notification_item_img);
            if (response.body().getHits().getHits().get(position).get_source().getProduct().get(0).getProductTitle()!=null)
                ((ViewHolder1)holder).notification_item_title.setText(
                        response.body().getHits().getHits().get(position).get_source().getProduct().get(0).getProductTitle()
                );
            if (response.body().getHits().getHits().get(position).get_source().getProduct().get(0).getColor()!=null)
                ((ViewHolder1)holder).notification_item_color.setText(
                        response.body().getHits().getHits().get(position).get_source().getProduct().get(0).getColor()
                );
            if (response.body().getHits().getHits().get(position).get_source().getProduct().get(0).getPrice()!=null)
                ((ViewHolder1)holder).notification_item_price.setText(
                        response.body().getHits().getHits().get(position).get_source().getProduct().get(0).getPrice()+" /-"
                );
            if (response.body().getHits().getHits().get(position).get_source().getProduct().get(0).getSize()!=null)
                ((ViewHolder1)holder).notification_item_size.setText(
                        response.body().getHits().getHits().get(position).get_source().getProduct().get(0).getSize()
                );
            if (response.body().getHits().getHits().get(position).get_source().getProduct().get(0).getQuantity()!=null)
                ((ViewHolder1)holder).notification_item_quantity.setText(
                        response.body().getHits().getHits().get(position).get_source().getProduct().get(0).getQuantity()
                );
        }
        else if (holder.getItemViewType()==2)
        {
            ((ViewHolder2)holder).fragment_my_account_container.setGravity(Gravity.CENTER_HORIZONTAL);
            ((ViewHolder2)holder).activity_no_comments_tv.setText("No Orders");
            //holder.activity_no_comments_tv.setText(""+Calendar.getInstance().getTime());
        }
    }

    @Override
    public int getItemCount() {
        if (response.body().getHits()!=null)
        {
            if (response.body().getHits().getHits()!=null&&response.body().getHits().getHits().size()!=0)
            {
                return response.body().getHits().getHits().size();
            }
        }
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (response.body().getHits()!=null)
        {
            if (response.body().getHits().getHits()!=null&&response.body().getHits().getHits().size()!=0)
            {
                return 1;
            }
        }
        return 2;
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView activity_no_comments_tv;
        LinearLayout fragment_my_account_container;

        public ViewHolder2(View itemView) {
            super(itemView);
            activity_no_comments_tv = (TextView) itemView.findViewById(R.id.activity_no_comments_tv);
            fragment_my_account_container=(LinearLayout)itemView.findViewById(R.id.fragment_my_account_container);
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView notification_item_title,notification_item_desc,notification_item_color,notification_item_quantity,
                notification_item_price,notification_item_size,buy_click_product_quantity_minus_btn,
                buy_click_product_quantity_plus_btn;
        ImageView notification_item_img;
        ImageButton notification_item_cancel_btn;

        public ViewHolder1(View itemView) {
            super(itemView);
            notification_item_color=(TextView)itemView.findViewById(R.id.notification_item_color);
            notification_item_title=(TextView)itemView.findViewById(R.id.notification_item_title);
            notification_item_img=(ImageView)itemView.findViewById(R.id.notification_item_img);
            notification_item_quantity=(TextView) itemView.findViewById(R.id.notification_item_quantity);
            notification_item_price=(TextView) itemView.findViewById(R.id.notification_item_price);
            notification_item_size=(TextView) itemView.findViewById(R.id.notification_item_size);
            notification_item_cancel_btn=(ImageButton)itemView.findViewById(R.id.notification_item_cancel_btn);
            buy_click_product_quantity_plus_btn=(TextView)itemView.findViewById(R.id.buy_click_product_quantity_plus_btn);
            buy_click_product_quantity_minus_btn=(TextView)itemView.findViewById(R.id.buy_click_product_quantity_minus_btn);
            /*buy_click_product_quantity_plus_btn.setOnClickListener(this);
            buy_click_product_quantity_minus_btn.setOnClickListener(this);
            notification_item_cancel_btn.setOnClickListener(this);*/
        }
    }
}
