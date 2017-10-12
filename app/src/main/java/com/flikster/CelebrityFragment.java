package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by abhishek on 04-10-2017.
 */

public class CelebrityFragment extends Fragment {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    CelebrityAdapter celebrityAdapter;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_celebrity,container,false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        celebrityAdapter = new CelebrityAdapter(getChildFragmentManager());
        viewPager.setAdapter(celebrityAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initializeViews() {
        viewPager=(ViewPager)view.findViewById(R.id.celebrity_pager);
        tabLayout=(TabLayout)view.findViewById(R.id.celebrity_tablayout);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorImageBackgroundGrey), getResources().getColor(R.color.white));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
    }
}
