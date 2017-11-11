package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

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

import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieAdapter;
import com.flikster.R;

/**
 * Created by abhishek on 04-10-2017.
 */

public class CelebrityFragment extends Fragment implements View.OnClickListener {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    CelebrityAdapter celebrityAdapter;
    FragmentManager fragmentManager;
    TextView toolbar_frag_title;
    ImageButton toolbar_back_navigation_btn;
    String slug;
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

    private void initializeRest() {
        Bundle arguments=new Bundle();
        arguments.putString("slug",slug);
        celebrityAdapter = new CelebrityAdapter(getChildFragmentManager(),arguments);
        viewPager.setAdapter(celebrityAdapter);
        tabLayout.setupWithViewPager(viewPager);
        toolbar_frag_title.setText("Celebrity");
        toolbar_back_navigation_btn.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.toolbar_back_navigation_btn)
        {
            getFragmentManager().popBackStackImmediate();
        }
    }

    public  void updateInfo(String slug)
    {
        this.slug=slug;
    }
}
