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

public class CelebrityFragmentInfo extends Fragment{
    View view;
    RecyclerView movieFragmentInfoRecycler;
    RecyclerView.LayoutManager movieFragmentInfoLayoutManager;
    MovieInfoAdapter movieInfoAdapter;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_celebrity_bio,container,false);
        initializeViews();
        initializeRest();
        return  view;
    }
    private void initializeRest() {
        movieFragmentInfoLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        movieFragmentInfoRecycler.setLayoutManager(movieFragmentInfoLayoutManager);
        movieInfoAdapter = new MovieInfoAdapter(getActivity(),fragmentManager);
        movieFragmentInfoRecycler.setAdapter(movieInfoAdapter);
    }

    private void initializeViews() {
        movieFragmentInfoRecycler=(RecyclerView)view.findViewById(R.id.fragment_celebrity_bio_recycler);
        fragmentManager=getActivity().getSupportFragmentManager();
    }
}
