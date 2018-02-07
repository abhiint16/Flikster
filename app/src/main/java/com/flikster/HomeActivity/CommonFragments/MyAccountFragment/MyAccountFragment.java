package com.flikster.HomeActivity.CommonFragments.MyAccountFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.HomeActivity.CommonFragments.NotificationFragment.NotificationFragment;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

/**
 * Created by abhishek on 17-10-2017.
 */

public class MyAccountFragment extends Fragment implements View.OnClickListener {
    Toolbar myprofile_toolbar;
    ViewPager viewPager;
    View view;
    TextView textView, myprofile_toolbar_title;
    TabLayout tabLayout;
    MyAccountAdapter myAccountAdapter;
    TextView fragment_my_account_name, fragment_my_account_location, fragment_my_account_no_followers;
    ImageButton myprofile_toolbar_navigation_icon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_account, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        myAccountAdapter = new MyAccountAdapter(getChildFragmentManager());
        viewPager.setAdapter(myAccountAdapter);
        tabLayout.setupWithViewPager(viewPager);
        myprofile_toolbar_title.setText("Profile");
        myprofile_toolbar_navigation_icon.setOnClickListener(this);
        //myprofile_toolbar_notification_icon.setOnClickListener(this);

        if (SharedPrefsUtil.getStringPreference(getContext(), "USER_NAME") != null
                && !SharedPrefsUtil.getStringPreference(getContext(), "USER_NAME").isEmpty()) {
            fragment_my_account_name.setText(SharedPrefsUtil.getStringPreference(getContext(), "USER_NAME"));
        } else {
            fragment_my_account_name.setText("You");
        }

    }

    private void initializeViews() {
        viewPager = (ViewPager) view.findViewById(R.id.fragment_my_account_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.fragment_my_account_tablayout);
        myprofile_toolbar = (Toolbar) view.findViewById(R.id.myprofile_toolbar);
        myprofile_toolbar_navigation_icon = (ImageButton) view.findViewById(R.id.myprofile_toolbar_navigation_icon);
        //myprofile_toolbar_notification_icon = (ImageButton) view.findViewById(R.id.myprofile_toolbar_notification_icon);
        myprofile_toolbar_title = (TextView) view.findViewById(R.id.myprofile_toolbar_title);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        tabLayout.setTabTextColors(getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.colorAccent));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));

        fragment_my_account_name = (TextView) view.findViewById(R.id.fragment_my_account_name);
        fragment_my_account_location = (TextView) view.findViewById(R.id.fragment_my_account_location);
        //fragment_my_account_no_followers = (TextView) view.findViewById(R.id.fragment_my_account_no_followers);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
            getFragmentManager().popBackStackImmediate();
        } /*else if (view.getId() == R.id.toolbar_frag_multiicons_notification) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new NotificationFragment())
                    .addToBackStack("")
                    .commit();
        }*/else if (view.getId() ==R.id.myprofile_toolbar_navigation_icon)
        {
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_container,new FeedFragment())
                    .commit();
        }
    }
}
