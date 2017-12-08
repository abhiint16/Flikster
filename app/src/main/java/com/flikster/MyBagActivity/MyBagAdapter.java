package com.flikster.MyBagActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 24-10-2017.
 */

public class MyBagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Integer> color=new ArrayList<>();
    List<String> title=new ArrayList<>();
    List<String> desc=new ArrayList<>();
    List<Integer> img=new ArrayList<>();
    MyBagData.MyBagInnerData myBagInnerData;
    Context context;

    public MyBagAdapter(Context context, MyBagData.MyBagInnerData myBagInnerData) {
        color.add(R.color.colorAccent);color.add(R.color.colorPrimary);color.add(R.color.colorCreateAccountSelected);
        color.add(R.color.colorAuthenticationHeader);
        title.add("Men's Capri Athletic Shoes");title.add("Arjun Reddy Glasses");title.add("Men's Capri Athletic Shoes");
        title.add("Arjun Reddy Glasses");
        desc.add("Sperry Shoes");desc.add("Round Glasses");desc.add("Sperry Shoes");desc.add("Round Glasses");
        img.add(R.drawable.shoes);img.add(R.drawable.shades);img.add(R.drawable.shoes);img.add(R.drawable.shades);
        this.myBagInnerData=myBagInnerData;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*if (viewType==0)
        {*/
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mybag_recycler_item,parent,false);
            return new ViewHolder1(view);
        //}
        /*else
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fragment_mybag_order_summary,parent,false);
            return new ViewHolder2(view);
        }*/
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*if (holder.getItemViewType()==0)
        {*/
        Glide.with(context).load(myBagInnerData.getHits().get(position).get_source().getProductDetails().getProductPic()).into(((ViewHolder1)holder).notification_item_img);
            ((ViewHolder1)holder).notification_item_title.setText(myBagInnerData.getHits().get(position).get_source().getProductDetails().getProductTitle());
            ((ViewHolder1)holder).notification_item_color.setText(myBagInnerData.getHits().get(position).get_source().getColor());
        ((ViewHolder1)holder).notification_item_size.setText(myBagInnerData.getHits().get(position).get_source().getProductDetails().getSize());
        //}
    }

    @Override
    public int getItemCount() {
        if(myBagInnerData.getHits()!=null&&myBagInnerData.getHits().size()!=0)
            return myBagInnerData.getHits().size();
        else return 0;
    }

    /*@Override
    public int getItemViewType(int position) {
        if (position!=3)
            return 0;
        else
            return 1;
    }*/

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
    public class ViewHolder2 extends RecyclerView.ViewHolder{
        public ViewHolder2(View itemView)
        {
            super(itemView);
        }
    }
}
