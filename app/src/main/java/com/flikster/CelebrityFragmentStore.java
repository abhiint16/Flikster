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

public class CelebrityFragmentStore extends Fragment{
    View view;
    RecyclerView celebrityFragmentStoreRecycler;
    RecyclerView.LayoutManager celebrityFragmentStoreLayoutManager;
    CelebrityStoreAdapter celebrityStoreAdapter;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_celebrity_store,container,false);
        initializeViews();
        initializeRest();
        return  view;
    }
    private void initializeRest() {
        celebrityFragmentStoreLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        celebrityFragmentStoreRecycler.setLayoutManager(celebrityFragmentStoreLayoutManager);
        celebrityStoreAdapter = new CelebrityStoreAdapter(getActivity(),fragmentManager);
        celebrityFragmentStoreRecycler.setAdapter(celebrityStoreAdapter);
    }

    private void initializeViews() {
        celebrityFragmentStoreRecycler=(RecyclerView)view.findViewById(R.id.fragment_celebrity_store_recycler);
        fragmentManager=getActivity().getSupportFragmentManager();
    }
}
