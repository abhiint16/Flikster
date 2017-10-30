package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by abhishek on 12-10-2017.
 */

public class RatingNowShowingFragment extends Fragment{
    View view;
    RecyclerView ratingNowShowingRecycler;
    RecyclerView.LayoutManager ratingNowShowingLayoutManager;
    RatingNowShowingAdapter ratingNowShowingAdapter;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_celebrity_feed,container,false);
        initializeViews();
        initializeRest();
        return  view;
    }

    private void initializeRest() {
        ratingNowShowingLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ratingNowShowingRecycler.setLayoutManager(ratingNowShowingLayoutManager);
        ratingNowShowingAdapter = new RatingNowShowingAdapter(getActivity(),fragmentManager);
        ratingNowShowingRecycler.setAdapter(ratingNowShowingAdapter);
    }

    private void initializeViews() {
        ratingNowShowingRecycler=(RecyclerView)view.findViewById(R.id.fragment_celebrity_feed_recycler);
        fragmentManager=getActivity().getSupportFragmentManager();
    }
}
