package com.flikster.HomeActivity.WatchFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieInfoAdapter;
import com.flikster.R;

/**
 * Created by abhishek on 12-10-2017.
 */

public class WatchFragment extends Fragment {
    View view;
    RecyclerView movieFragmentInfoRecycler;
    RecyclerView.LayoutManager movieFragmentInfoLayoutManager;
    WatchAdapter watchAdapter;
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        movieFragmentInfoLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        movieFragmentInfoRecycler.setLayoutManager(movieFragmentInfoLayoutManager);
        watchAdapter = new WatchAdapter(getActivity(), fragmentManager);
        movieFragmentInfoRecycler.setAdapter(watchAdapter);
    }

    private void initializeViews() {
        movieFragmentInfoRecycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager = getActivity().getSupportFragmentManager();
    }
}
