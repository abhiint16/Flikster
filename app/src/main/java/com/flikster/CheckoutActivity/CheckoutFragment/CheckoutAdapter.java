package com.flikster.CheckoutActivity.CheckoutFragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.CheckoutActivity.AddressFragment.AddressFragment;
import com.flikster.R;

/**
 * Created by abhishek on 25-10-2017.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    FragmentManager fragmentManager;
    Context context;
    String name;
    String address;
    String city;String state;
    String pin;
    String mobile;
    String landmark;
    String additionMobile;
    String productId;String productSlug;String productTitle;String userId;String size;
    String color;String profilePic;String price;
    int quantity;
    public CheckoutAdapter(FragmentManager fragmentManager,Context context,String name,String address,String city,String state,
                           String pin,String mobile,String landmark,String additionMobile,String productId,
                           String productSlug,String productTitle,String userId,String size,String color,String profilePic,
                           String price,int quantity) {
        this.fragmentManager=fragmentManager;
        this.context=context;
        this.name=name;
        this.address=address;
        this.city=city;
        this.state=state;
        this.additionMobile=additionMobile;
        this.pin=pin;
        this.mobile=mobile;
        this.landmark=landmark;
        this.productId=productId;
        this.productSlug=productSlug;
        this.productTitle=productTitle;
        this.userId=userId;
        this.size=size;
        this.color=color;
        this.profilePic=profilePic;
        this.price=price;
        this.quantity=quantity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_checkout_customer_details,parent,false);
            return new ViewHolder0(view);
        }
        else if(viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mybag_recycler_item,parent,false);
            return new ViewHolder1(view);
        }
        else if(viewType==2)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_checkout_promo,parent,false);
            return new ViewHolder2(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fragment_mybag_order_summary,parent,false);
            return new ViewHolder3(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==0)
        {
            ((ViewHolder0)holder).card_checkout_customer_details_name.setText(name);
            ((ViewHolder0)holder).card_checkout_customer_details_address.setText(address);
            ((ViewHolder0)holder).card_checkout_customer_details_city.setText(city);
            ((ViewHolder0)holder).card_checkout_customer_details_state.setText(state);
            ((ViewHolder0)holder).card_checkout_customer_details_mobile.setText(mobile);
            ((ViewHolder0)holder).card_checkout_customer_details_state.setText(state);
            ((ViewHolder0)holder).card_checkout_customer_details_pin.setText(pin);
        }
        else if (holder.getItemViewType()==1)
        {
            Log.e("check checkout dat",""+profilePic+productTitle+color+size);
            Glide.with(context).load(profilePic).into(((ViewHolder1)holder).notification_item_img);
            ((ViewHolder1)holder).notification_item_title.setText(productTitle);
            ((ViewHolder1)holder).notification_item_color.setText(color);
            ((ViewHolder1)holder).notification_item_size.setText(size);
            ((ViewHolder1)holder).notification_item_price.setText(price+" /-");
            ((ViewHolder1)holder).notification_item_quantity.setText(""+quantity);
        }
        else if(holder.getItemViewType()==2)
        {
        }
        else if(holder.getItemViewType()==3)
        {
            ((ViewHolder3)holder).card_fragment_mybag_order_summary_product_cost.setText("Rs. "+price+"/-");
            ((ViewHolder3)holder).card_fragment_mybag_order_summary_total_cost.setText("Rs. "+price+"/-");
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

    public class ViewHolder0 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button card_checkout_customer_details_button;
        TextView card_checkout_customer_details_name,card_checkout_customer_details_address,card_checkout_customer_details_city
        ,card_checkout_customer_details_state,card_checkout_customer_details_pin,card_checkout_customer_details_mobile;
        public ViewHolder0(View itemView) {
            super(itemView);
            card_checkout_customer_details_button=(Button)itemView.findViewById(R.id.card_checkout_customer_details_button);
            card_checkout_customer_details_name=(TextView)itemView.findViewById(R.id.card_checkout_customer_details_name);
            card_checkout_customer_details_address=(TextView)itemView.findViewById(R.id.card_checkout_customer_details_address);
            card_checkout_customer_details_city=(TextView)itemView.findViewById(R.id.card_checkout_customer_details_city);
            card_checkout_customer_details_state=(TextView)itemView.findViewById(R.id.card_checkout_customer_details_state);
            card_checkout_customer_details_pin=(TextView)itemView.findViewById(R.id.card_checkout_customer_details_pin);
            card_checkout_customer_details_mobile=(TextView)itemView.findViewById(R.id.card_checkout_customer_details_mobile);
            card_checkout_customer_details_button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.activity_mybag_continue_onclick_container,new AddressFragment())
                    .addToBackStack("")
                    .commit();
        }
    }
    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView notification_item_title,notification_item_desc,notification_item_color,notification_item_quantity,
                notification_item_price,notification_item_size;
        ImageView notification_item_img;
        public ViewHolder1(View itemView) {
            super(itemView);
            notification_item_color=(TextView)itemView.findViewById(R.id.notification_item_color);
            notification_item_desc=(TextView)itemView.findViewById(R.id.notification_item_desc);
            notification_item_title=(TextView)itemView.findViewById(R.id.notification_item_title);
            notification_item_img=(ImageView)itemView.findViewById(R.id.notification_item_img);
            notification_item_quantity=(TextView) itemView.findViewById(R.id.notification_item_quantity);
            notification_item_price=(TextView) itemView.findViewById(R.id.notification_item_price);
            notification_item_size=(TextView) itemView.findViewById(R.id.notification_item_size);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button button;
        public ViewHolder2(View itemView) {
            super(itemView);
            button=(Button)itemView.findViewById(R.id.card_checkout_promo_btn);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_enter_promocode);
            final Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            Button dialog_enter_promocode_done_btn=(Button)dialog.findViewById(R.id.dialog_enter_promocode_done_btn);
            ImageButton dialog_enter_promocode_cancel_btn=(ImageButton)dialog.findViewById(R.id.dialog_enter_promocode_cancel_btn);
            dialog_enter_promocode_done_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog_enter_promocode_cancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            window.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.translucent)));
            dialog.show();
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {
        TextView card_fragment_mybag_order_summary_product_cost,card_fragment_mybag_order_summary_shipping_cost,
                card_fragment_mybag_order_summary_total_cost;
        public ViewHolder3(View itemView) {
            super(itemView);
            card_fragment_mybag_order_summary_product_cost=(TextView)itemView.findViewById(R.id.card_fragment_mybag_order_summary_product_cost);
            card_fragment_mybag_order_summary_shipping_cost=(TextView)itemView.findViewById(R.id.card_fragment_mybag_order_summary_shipping_cost);
            card_fragment_mybag_order_summary_total_cost=(TextView)itemView.findViewById(R.id.card_fragment_mybag_order_summary_total_cost);
        }
    }
}
