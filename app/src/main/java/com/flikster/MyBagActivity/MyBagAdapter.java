package com.flikster.MyBagActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.Authentication.AuthenticationActivity;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.HomeActivity.ModelForPostRequest;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 24-10-2017.
 */

public class MyBagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Integer> color=new ArrayList<>();
    List<MyBagData.MyBagInnerData> myBagInnerData;
    Context context;
    int price;
    ApiInterface apiInterface;
    Button activity_my_bag_bottom_continue_btn;

    public MyBagAdapter(Context context, List<MyBagData.MyBagInnerData> myBagInnerData,Button activity_my_bag_bottom_continue_btn) {
        color.add(R.color.colorAccent);color.add(R.color.colorPrimary);color.add(R.color.colorCreateAccountSelected);
        color.add(R.color.colorAuthenticationHeader);
        this.myBagInnerData=myBagInnerData;
        this.context=context;
        this.activity_my_bag_bottom_continue_btn=activity_my_bag_bottom_continue_btn;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mybag_recycler_item,parent,false);
            return new ViewHolder1(view);
        }
        else if (viewType==3)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_no_data_available,parent,false);
            return new ViewHolder3(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fragment_mybag_order_summary,parent,false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==0)
        {

            if (myBagInnerData.get(position).getProductDetails().getProductPic()!=null)
                Glide.with(context).load(myBagInnerData.get(position).getProductDetails().getProductPic()).into(((ViewHolder1)holder).notification_item_img);
            if (myBagInnerData.get(position).getProductDetails().getProductTitle()!=null)
                ((ViewHolder1)holder).notification_item_title.setText(myBagInnerData.get(position).getProductDetails().getProductTitle());
            else if (myBagInnerData.get(position).getProductDetails().getProductSlug()!=null)
                ((ViewHolder1)holder).notification_item_title.setText(myBagInnerData.get(position).getProductDetails().getProductSlug());
            if (myBagInnerData.get(position).getColor()!=null)
                ((ViewHolder1)holder).notification_item_color.setText(myBagInnerData.get(position).getColor());
            if (myBagInnerData.get(position).getSize()!=null)
                ((ViewHolder1)holder).notification_item_size.setText(myBagInnerData.get(position).getSize());
            if (myBagInnerData.get(position).getProductDetails().getQuantity()!=null)
                ((ViewHolder1)holder).notification_item_quantity.setText(myBagInnerData.get(position).getProductDetails().getQuantity());
            if (myBagInnerData.get(position).getProductDetails().getPrice()!=null)
            {
                price= price+Integer.parseInt(myBagInnerData.get(position).getProductDetails().getPrice());
                ((ViewHolder1)holder).notification_item_price.setText(myBagInnerData.get(position).getProductDetails().getPrice()+" /-");
            }
        }
        else if (holder.getItemViewType()==3)
        {
            if (SharedPrefsUtil.getStringPreference(context.getApplicationContext(), "USER_ID") != null && !SharedPrefsUtil.getStringPreference(context.getApplicationContext(), "USER_ID").isEmpty())
            {
                activity_my_bag_bottom_continue_btn.setVisibility(View.GONE);
                ((ViewHolder3)holder).nodataavailtxt.setText("No Item Available");
                ((ViewHolder3)holder).backhomebtn.setText("Buy Now!");
            }
            else
            {
                activity_my_bag_bottom_continue_btn.setVisibility(View.GONE);
                ((ViewHolder3)holder).nodataavailtxt.setText("You're not loggedIn");
                ((ViewHolder3)holder).backhomebtn.setText("Login Now!");
            }
        }
        else {
            ((ViewHolder2)holder).card_fragment_mybag_order_summary_product_cost.setText("Rs. "+price+"/-");
            ((ViewHolder2)holder).card_fragment_mybag_order_summary_total_cost.setText("Rs. "+price+"/-");
        }
    }

    @Override
    public int getItemCount() {
        if(myBagInnerData!=null&&myBagInnerData.size()!=0)
            return myBagInnerData.size()+1;
        else return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(myBagInnerData==null||myBagInnerData.size()==0)
            return 3;
        if (position==myBagInnerData.size())
            return 2;
        else return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            buy_click_product_quantity_plus_btn.setOnClickListener(this);
            buy_click_product_quantity_minus_btn.setOnClickListener(this);
            notification_item_cancel_btn.setOnClickListener(this);
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });*/
        }

        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.buy_click_product_quantity_minus_btn)
            {
                if (Integer.parseInt((String)notification_item_quantity.getText()) > 1)
                {
                    notification_item_quantity.setText("" + (Integer.parseInt((String)notification_item_quantity.getText()) - 1));
                    notification_item_price.setText(""+Integer.parseInt(myBagInnerData.get(getAdapterPosition()).getProductDetails().getPrice())*
                            (Integer.parseInt((String)notification_item_quantity.getText()))+" /-");
                    price=price-Integer.parseInt(myBagInnerData.get(getAdapterPosition()).getProductDetails().getPrice());
                    notifyItemChanged(myBagInnerData.size());
                }
            }else if (view.getId()==R.id.buy_click_product_quantity_plus_btn)
            {
                notification_item_quantity.setText(""+(Integer.parseInt((String)notification_item_quantity.getText()) + 1));
                notification_item_price.setText(""+Integer.parseInt(myBagInnerData.get(getAdapterPosition()).getProductDetails().getPrice())*
                        (Integer.parseInt((String)notification_item_quantity.getText()))+" /-");
                price=price+Integer.parseInt(myBagInnerData.get(getAdapterPosition()).getProductDetails().getPrice());
                notifyItemChanged(myBagInnerData.size());
            }
            else if (view.getId()==R.id.notification_item_cancel_btn)
            {
                apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/cart-ms/removeItemFromCart/").create(ApiInterface.class);
                Call<ModelForPostRequest> call = apiInterface.removeItemFromBag("http://apiservice.flikster.com/v3/cart-ms/removeItemFromCart/"+myBagInnerData.get(getAdapterPosition()).getId());
                call.enqueue(new Callback<ModelForPostRequest>() {
                    @Override
                    public void onResponse(Call<ModelForPostRequest> call, Response<ModelForPostRequest> response) {
                     myBagInnerData.remove(getAdapterPosition());
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ModelForPostRequest> call, Throwable t) {
                        Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
                    }
                });
            }
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder{
        TextView card_fragment_mybag_order_summary_product_cost,card_fragment_mybag_order_summary_shipping_cost,
                card_fragment_mybag_order_summary_total_cost;
        public ViewHolder2(View itemView)
        {
            super(itemView);
            card_fragment_mybag_order_summary_product_cost=(TextView)itemView.findViewById(R.id.card_fragment_mybag_order_summary_product_cost);
            card_fragment_mybag_order_summary_shipping_cost=(TextView)itemView.findViewById(R.id.card_fragment_mybag_order_summary_shipping_cost);
            card_fragment_mybag_order_summary_total_cost=(TextView)itemView.findViewById(R.id.card_fragment_mybag_order_summary_total_cost);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nodataavailtxt;
        Button backhomebtn;
        public ViewHolder3(View itemView)
        {
            super(itemView);
            nodataavailtxt=(TextView)itemView.findViewById(R.id.nodataavailtxt);
            backhomebtn=(Button) itemView.findViewById(R.id.backhomebtn);
            backhomebtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (SharedPrefsUtil.getStringPreference(context.getApplicationContext(), "USER_ID") != null && !SharedPrefsUtil.getStringPreference(context.getApplicationContext(), "USER_ID").isEmpty())
            {
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra("MyBag", "MyBag");
                context.startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(context, AuthenticationActivity.class);
                intent.putExtra("MyBag", "MyBag");
                context.startActivity(intent);
            }
        }
    }
}
