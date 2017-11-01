package com.flikster.HomeActivity.CommonFragments.MovieFragment;

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
import android.widget.TextView;

import com.flikster.R;

/**
 * Created by abhishek on 16-10-2017.
 */

public class MovieFragment extends Fragment implements View.OnClickListener {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    MovieAdapter movieAdapter;
    FragmentManager fragmentManager;
    TextView toolbar_frag_title;
    ImageButton toolbar_back_navigation_btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_celebrity,container,false);
        initializeViews();
        initializeRest();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    private void initializeViews() {
        viewPager=(ViewPager)view.findViewById(R.id.celebrity_pager);
        tabLayout=(TabLayout)view.findViewById(R.id.celebrity_tablayout);
        toolbar_frag_title=(TextView)view.findViewById(R.id.toolbar_frag_title);
        toolbar_back_navigation_btn=(ImageButton)view.findViewById(R.id.toolbar_back_navigation_btn);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        tabLayout.setTabTextColors(getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.black));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
    }

    private void initializeRest() {
        movieAdapter = new MovieAdapter(getChildFragmentManager());
        viewPager.setAdapter(movieAdapter);
        tabLayout.setupWithViewPager(viewPager);
        toolbar_frag_title.setText("Movies");
        toolbar_back_navigation_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.toolbar_back_navigation_btn)
        {
            getFragmentManager().popBackStackImmediate();
        }
    }
}
