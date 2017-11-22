package com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.UpComing;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.AuctionRelatedProductsViewHolder;
import com.flikster.HomeActivity.FashionFragment.FashionFragmentAdapterJustArrivedViewHolder;
import com.flikster.HomeActivity.FashionFragment.FashionFragmentAdapterRecommenedViewHolder;
import com.flikster.R;

/**
 * Created by abhishek on 30-10-2017.
 */

public class AuctionUpComingFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RecyclerView.LayoutManager layoutManager;
    FashionFragmentAdapterJustArrivedViewHolder fashionFragmentAdapterJustArrivedViewHolder;
    FashionFragmentAdapterRecommenedViewHolder fashionFragmentAdapterRecommenedViewHolder;
    Context context;
    FragmentManager fragmentManager;
    AuctionupComingProductsViewHolder auctionupComingProductsViewHolder;

    public AuctionUpComingFragmentAdapter(Context context, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary3_1, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv_one, parent, false);
            return new ViewHolder2(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_auction, parent, false);
            return new ViewHolder3(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
        } else if (holder.getItemViewType() == 1) {
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setVisibility(View.GONE);
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
            auctionupComingProductsViewHolder = new AuctionupComingProductsViewHolder(context, fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_recycler.setAdapter(auctionupComingProductsViewHolder);
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;

        public ViewHolder1(View itemView) {
            super(itemView);
//            followbtn = (Button) itemView.findViewById(R.id.followbtn);
//            followbtn.setText("BUY");
//            followbtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();

            }
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView fragment_common_recyclerview_recycler;
        TextView fragment_common_recyclerview_with_tv_title;

        public ViewHolder2(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }

        @Override
        public void onClick(View v) {
//            if (v.getId() == R.id.followbtn) {
//                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();
//
//            }

        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;

        public ViewHolder3(View itemView) {
            super(itemView);
//            followbtn = (Button) itemView.findViewById(R.id.followbtn);
//            followbtn.setText("BUY");
//            followbtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            if (v.getId() == R.id.followbtn) {
//                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();
//
//            }
        }
    }
}
