package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by abhishek on 17-10-2017.
 */

public class MyAccountFragment extends Fragment {
    Toolbar toolbar;
    ViewPager viewPager;
    View view;
    TextView textView;
    TabLayout tabLayout;
    CelebrityAdapter celebrityAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my_account,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
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
        viewPager=(ViewPager)view.findViewById(R.id.fragment_my_account_pager);
        tabLayout=(TabLayout)view.findViewById(R.id.fragment_my_account_tablayout);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        tabLayout.setTabTextColors(getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.black));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));

    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}
