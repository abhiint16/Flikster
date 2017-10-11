package com.flikster;

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

/**
 * Created by abhishek on 05-10-2017.
 */

public class GallaryCardClick extends Fragment {
    View view;
    RecyclerView gallary_recyclerview;
    RecyclerView.LayoutManager gallaryLayoutManager;
    GallaryCardClickAdapter gallaryCardClickAdapter;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.card_gallary_item_onclick,container,false);
        initializeViews();
        initializeRest();
        Log.e("leavng onCreatview","leavng onCreatview");
        return view;
    }

    private void initializeRest() {
        Log.e("rest","REST");
        gallaryLayoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        gallary_recyclerview.setLayoutManager(gallaryLayoutManager);
        gallaryCardClickAdapter=new GallaryCardClickAdapter(getActivity(),fragmentManager);
        gallary_recyclerview.setAdapter(gallaryCardClickAdapter);
    }

    private void initializeViews() {
        Log.e("views","views");
        gallary_recyclerview=(RecyclerView)view.findViewById(R.id.gallary_recyclerview);
        fragmentManager=getActivity().getSupportFragmentManager();    }
}
