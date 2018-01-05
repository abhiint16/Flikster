package com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsBottomHorRecyclerAdapter;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.RatingFragment.RatingNowShowingAdapter;
import com.flikster.R;
import com.google.android.gms.common.api.Api;
import com.leo.simplearcloader.SimpleArcLoader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 12-10-2017.
 */

public class AuctionCurrentFragment extends Fragment {
    View view;
    RecyclerView ratingNowShowingRecycler;
    RecyclerView.LayoutManager ratingNowShowingLayoutManager;
    AuctionCurrentFragmentAdapter auctionCurrentFragmentAdapter;
    FragmentManager fragmentManager;

    int Count;
    List<AuctionCurrentOrUpcomingData.AuctionInnerData> auctionCurrentOrUpcomingData;
    TextView nodataavailtxt;
    SimpleArcLoader mDialog;
    ApiInterface apiInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        initializeViews();
        currentAuctionServerData();
        return view;
    }


    private void initializeRest(List<AuctionCurrentOrUpcomingData.AuctionInnerData> auctionCurrentOrUpcomingData) {
        ratingNowShowingLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        ratingNowShowingRecycler.setLayoutManager(ratingNowShowingLayoutManager);
        auctionCurrentFragmentAdapter = new AuctionCurrentFragmentAdapter(getContext(), fragmentManager, auctionCurrentOrUpcomingData);
        ratingNowShowingRecycler.setAdapter(auctionCurrentFragmentAdapter);
    }

    private void initializeViews() {
        ratingNowShowingRecycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager = getActivity().getSupportFragmentManager();
        nodataavailtxt = (TextView) view.findViewById(R.id.nodataavailtxt);
        mDialog = (SimpleArcLoader) view.findViewById(R.id.arc_loader);
    }

    private void currentAuctionServerData() {
        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        apiInterface = ApiClient.getClient(ApiClient.AUCTION_URL).create(ApiInterface.class);
        Call<AuctionCurrentOrUpcomingData> call = apiInterface.getAuctionData(ApiClient.AUCTION_URL);
        call.enqueue(new Callback<AuctionCurrentOrUpcomingData>() {
            @Override
            public void onResponse(Call<AuctionCurrentOrUpcomingData> call,
                                   Response<AuctionCurrentOrUpcomingData> response) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                auctionCurrentOrUpcomingData = response.body().getCurrentAuctions();
                if (auctionCurrentOrUpcomingData.size() != 0 && !auctionCurrentOrUpcomingData.isEmpty()) {
                    initializeRest(auctionCurrentOrUpcomingData);
                    Log.e("AuctionCount", Count + "");
                } else {
                    nodataavailtxt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<AuctionCurrentOrUpcomingData> call, Throwable t) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }


}
