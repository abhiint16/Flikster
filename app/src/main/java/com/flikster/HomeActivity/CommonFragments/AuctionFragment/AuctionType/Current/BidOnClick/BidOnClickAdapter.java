package com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.BidOnClick;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.AuctionCurrentOrUpcomingData;

import java.util.List;

/**
 * Created by abhishek on 02-04-2018.
 */

public class BidOnClickAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String name;
    String celebname;
    List<String> colorlist;
    List<AuctionCurrentOrUpcomingData.Bids> listOfBidObjects;
    String maxprice;
    Context context;

    public BidOnClickAdapter(Context context,String name, String celebname, List<String> colorlist,String maxprice,
                             List<AuctionCurrentOrUpcomingData.Bids> listOfBidObjects) {
        this.name=name;
        this.celebname=celebname;
        this.listOfBidObjects=listOfBidObjects;
        this.colorlist=colorlist;
        this.maxprice=maxprice;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
