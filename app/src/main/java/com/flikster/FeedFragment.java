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
 * Created by abhishek on 04-10-2017.
 */

public class FeedFragment extends Fragment {
    View view;
    RecyclerView feed_mainRecyclerView;
    RecyclerView.LayoutManager feedLayoutManager;
    FeedRecyclerAdapter feedAdapter;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_feed,container,false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        feedLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        feed_mainRecyclerView.setLayoutManager(feedLayoutManager);
        feedAdapter = new FeedRecyclerAdapter(getActivity(),fragmentManager);
        feed_mainRecyclerView.setAdapter(feedAdapter);
    }

    private void initializeViews() {
        feed_mainRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        fragmentManager=getActivity().getSupportFragmentManager();
    }
}
