package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flikster.R;

/**
 * Created by abhishek on 12-10-2017.
 */

public class CelebrityFragmentBio extends Fragment{
    View view;
    RecyclerView celebrityFragmentBioRecycler;
    RecyclerView.LayoutManager celebrityFragmentBioLayoutManager;
    CelebrityBioAdapter celebrityBioAdapter;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_common_recyclerview,container,false);
        initializeViews();
        initializeRest();
        return  view;
    }
    private void initializeRest() {
        celebrityFragmentBioLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        celebrityFragmentBioRecycler.setLayoutManager(celebrityFragmentBioLayoutManager);
        celebrityBioAdapter = new CelebrityBioAdapter(getActivity(),fragmentManager);
        celebrityFragmentBioRecycler.setAdapter(celebrityBioAdapter);
    }

    private void initializeViews() {
        celebrityFragmentBioRecycler=(RecyclerView)view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager=getActivity().getSupportFragmentManager();
    }
}
