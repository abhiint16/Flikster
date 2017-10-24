package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by abhishek on 24-10-2017.
 */

public class MyBagContinueOnClickFragment extends Fragment implements View.OnClickListener {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    MyBagContinueOnClickFragmentAdapter myBagContinueOnClickFragmentAdapter;
    ImageButton fragment_mybag_continue_onclick_toolbar_back_navigation;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_mybag_continue_onclick,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        initializeView();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        myBagContinueOnClickFragmentAdapter=new MyBagContinueOnClickFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(myBagContinueOnClickFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        fragment_mybag_continue_onclick_toolbar_back_navigation.setOnClickListener(this);
    }

    private void initializeView() {
        viewPager=(ViewPager)view.findViewById(R.id.fragment_mybag_continue_onclick_viewpager);
        tabLayout=(TabLayout)view.findViewById(R.id.fragment_mybag_continue_onclick_tab);
        fragment_mybag_continue_onclick_toolbar_back_navigation=(ImageButton)view.findViewById(R.id.fragment_mybag_continue_onclick_toolbar_back_navigation);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        tabLayout.setTabTextColors(getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.black));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fragment_mybag_continue_onclick_toolbar_back_navigation)
        {
            getFragmentManager().popBackStackImmediate();
        }
    }
}
