package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by abhishek on 16-10-2017.
 */

public class MovieFragment extends Fragment {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    MovieAdapter movieAdapter;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_celebrity,container,false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeViews() {
        viewPager=(ViewPager)view.findViewById(R.id.celebrity_pager);
        tabLayout=(TabLayout)view.findViewById(R.id.celebrity_tablayout);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorImageBackgroundGrey), getResources().getColor(R.color.white));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
    }

    private void initializeRest() {
        movieAdapter = new MovieAdapter(getChildFragmentManager());
        viewPager.setAdapter(movieAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
