package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by abhishek on 17-10-2017.
 */

public class MyAccountFragment extends Fragment implements View.OnClickListener {
    Toolbar toolbar;
    ViewPager viewPager;
    View view;
    TextView textView;
    TabLayout tabLayout;
    MyAccountAdapter myAccountAdapter;
    ImageButton toolbar_navigation_icon;
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
        myAccountAdapter = new MyAccountAdapter(getChildFragmentManager());
        viewPager.setAdapter(myAccountAdapter);
        tabLayout.setupWithViewPager(viewPager);
        toolbar_navigation_icon.setOnClickListener(this);
    }

    private void initializeViews() {
        viewPager=(ViewPager)view.findViewById(R.id.fragment_my_account_pager);
        tabLayout=(TabLayout)view.findViewById(R.id.fragment_my_account_tablayout);
        toolbar=(Toolbar)view.findViewById(R.id.fragment_my_account_toolbar);
        toolbar_navigation_icon=(ImageButton)view.findViewById(R.id.toolbar_navigation_icon);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        tabLayout.setTabTextColors(getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.colorAccent));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.toolbar_navigation_icon)
        {
            getFragmentManager().popBackStackImmediate();
        }
    }
}
