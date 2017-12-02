package com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreInnerItems;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.WidgetData;
import com.flikster.R;

import static com.flikster.R.layout.card_profile_collection_recycler_item;

/**
 * Created by abhishek on 02-12-2017.
 */

public class Profile_collection_inner_recycler_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    FragmentManager fragmentManager;
    WidgetData.WidgetInnerData widgetHits;
    Integer pos;
    public Profile_collection_inner_recycler_adapter(Context context, FragmentManager fragmentManager, WidgetData.WidgetInnerData widgetHits,
                                                     Integer pos) {
        this.context=context;
        this.fragmentManager=fragmentManager;
        this.widgetHits=widgetHits;
        this.pos=pos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile_collection_recycler_item,parent,false);
            return new ViewHolder1(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_not_available_layout,parent,false);
            return new ViewHolder2(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==1)
        {
            if(widgetHits.getHits().get(pos).get_source().getProducts().get(position).get_source().getName()!=null)
                ((ViewHolder1)holder).card_profile_collection_recycler_item_tv.setText(widgetHits.getHits().get(pos).get_source().getProducts().get(position).get_source().getName());
            if(widgetHits.getHits().get(pos).get_source().getProducts().get(position).get_source().getProfilePic()!=null)
                Glide.with(context).load(widgetHits.getHits().get(pos).get_source().getProducts().get(position).get_source().getProfilePic()).into(((ViewHolder1)holder).imageView3);
        }

    }

    @Override
    public int getItemCount() {
        if(widgetHits.getHits().get(pos).get_source().getProducts()!=null&&
                widgetHits.getHits().get(pos).get_source().getProducts().size()!=0)
        {
            return widgetHits.getHits().get(pos).get_source().getProducts().size();
        }
        else return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(widgetHits.getHits().get(pos).get_source().getProducts()!=null&&
                widgetHits.getHits().get(pos).get_source().getProducts().size()!=0)
        {
            return 1;
        }
        else return 2;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView imageView3;
        TextView card_profile_collection_recycler_item_tv;
        public ViewHolder1(View itemView) {
            super(itemView);
            imageView3=(ImageView)itemView.findViewById(R.id.imageView3);
            card_profile_collection_recycler_item_tv=(TextView)itemView.findViewById(R.id.card_profile_collection_recycler_item_tv);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        public ViewHolder2(View itemView) {
            super(itemView);
        }
    }
}
