package com.flikster.HomeActivity.CommonFragments.MyAccountFragment.CelebProfile;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.HomeActivity.CommonFragments.MyAccountFragment.UserProfile.MyAccountAdapter;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

/**
 * Created by abhishek on 17-10-2017.
 */

public class CelebProfileFragment extends Fragment implements View.OnClickListener {
    ViewPager viewPager;
    View view;
    //TextView textView, myprofile_toolbar_title;
    TabLayout tabLayout;
    CelebProfileAdapter celebProfileAdapter;
    TextView fragment_my_account_name, fragment_my_account_location, fragment_my_account_no_followers;
    ImageButton myprofile_toolbar_navigation_icon;
    String toolbar_Text="Username";
    AppBarLayout main_appbar;
    Toolbar toolbar_main;
    CollapsingToolbarLayout collapsingToolbarLayout;

    //MyAccountItemClick myAccountItemClick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_celeb_profile, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        celebProfileAdapter = new CelebProfileAdapter(getChildFragmentManager());
        viewPager.setAdapter(celebProfileAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //myprofile_toolbar_title.setText("Profile");
        myprofile_toolbar_navigation_icon.setOnClickListener(this);
        //myprofile_toolbar_notification_icon.setOnClickListener(this);

        main_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                /*Log.e("appbarscrolling" ,""+verticalOffset+"AND"+appBarLayout.getTotalScrollRange()+"AND"+appBarLayout.getTransitionName()+
                "AND"+(scrollRange+verticalOffset));*/
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset < 40) {
                    collapsingToolbarLayout.setTitleEnabled(true);
                    collapsingToolbarLayout.setTitle(toolbar_Text);
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getActivity().getResources().getColor(R.color.white));
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

    private void initializeViews() {
        viewPager = (ViewPager) view.findViewById(R.id.fragment_my_account_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.fragment_my_account_tablayout);
        toolbar_main = (Toolbar) view.findViewById(R.id.myprofile_toolbar);
        main_appbar=(AppBarLayout)view.findViewById(R.id.fragment_my_account_appbar);
        toolbar_main=(Toolbar)view.findViewById(R.id.myprofile_toolbar);
        collapsingToolbarLayout=(CollapsingToolbarLayout)view.findViewById(R.id.main_collapsing);
        myprofile_toolbar_navigation_icon = (ImageButton) view.findViewById(R.id.myprofile_toolbar_navigation_icon);
        //myprofile_toolbar_notification_icon = (ImageButton) view.findViewById(R.id.myprofile_toolbar_notification_icon);
        //myprofile_toolbar_title = (TextView) view.findViewById(R.id.myprofile_toolbar_title);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        tabLayout.setTabTextColors(getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.colorAccent));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        fragment_my_account_name = (TextView) view.findViewById(R.id.fragment_my_account_name);
        fragment_my_account_location = (TextView) view.findViewById(R.id.fragment_my_account_location);
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
        }*/ else if (view.getId() == R.id.myprofile_toolbar_navigation_icon) {
            /*getFragmentManager().beginTransaction()
                    .replace(R.id.main_container,new FeedFragment())
                    .commit();*/
            //myAccountItemClick.voidMethod(new FeedFragment());
        }
    }

    /*public interface MyAccountItemClick {
        void voidMethod(Fragment fragment);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myAccountItemClick = (MyAccountItemClick) activity;
    }*/
}
