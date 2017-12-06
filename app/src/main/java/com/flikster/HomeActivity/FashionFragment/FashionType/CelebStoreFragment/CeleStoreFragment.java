package com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.HomeActivity.CommonFragments.NotificationFragment.NotificationFragment;
import com.flikster.HomeActivity.FashionFragment.FashionFragmentAdapter;
import com.flikster.HomeActivity.FashionFragment.FashionLandingFragment.FashionLandingFragment;
import com.flikster.HomeActivity.FashionFragment.FashionLandingFragment.ViewPagerAdapter;
import com.flikster.HomeActivity.FashionFragment.FashionType.MenFashionFragment.MenFashionFirstTypeFragment;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.MyBagActivity.MyBagActivity;
import com.flikster.R;
import com.flikster.Util.ScrollableViewPager;
import com.flikster.Util.SharedPrefsUtil;

/**
 * Created by abhishek on 17-10-2017.
 */

public class CeleStoreFragment extends Fragment implements View.OnClickListener, TabLayout.OnTabSelectedListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManagerFashionFragment;
    FashionFragmentAdapter fashionFragmentAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    ImageButton toolbar_frag_multiicons_back_navigation, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart;
    TabLayout tabLayout;
    TextView toolbar_frag_multiicons_title;
    String storeType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_coommon_tablayout, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "HEADER_NAME").equals("MOVIE_STORE")) {
            this.storeType="Movie";
            toolbar_frag_multiicons_title.setText("Movie Store");
        } else {
            this.storeType="Celeb";
            toolbar_frag_multiicons_title.setText("Celeb Store");
        }
        /*createViewPager(viewPage);
        tabLayout.setupWithViewPager(viewPage);*/
        createTabIcons();
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        tabLayout.addOnTabSelectedListener(this);
        firstTimeLaunchTab();
    }

    private void firstTimeLaunchTab() {
        SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "CELEB_STORE", storeType);
        SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "TAB_NO", "ALL");
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout_of_tablayout, new CelebStoreFirstTypeFragment())
                .commit();
    }

    private void initializeViews() {
        toolbar_frag_multiicons_title = (TextView) view.findViewById(R.id.toolbar_frag_title);
        toolbar_frag_multiicons_toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_toolbar);
        toolbar_frag_multiicons_back_navigation = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(getContext().getResources().getColor(R.color.horizontal_line));
    }

    private void createTabIcons() {
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("CLOTHING"));
        tabLayout.addTab(tabLayout.newTab().setText("EYEWEAR"));
        tabLayout.addTab(tabLayout.newTab().setText("FOOTWEAR"));
        tabLayout.addTab(tabLayout.newTab().setText("ACCESSORIES"));


        /*TextView tabfive = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtabtext, null);
        tabfive.setText("All");
        tabLayout.getTabAt(0).setCustomView(tabfive);

        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtabtext, null);
        tabOne.setText("Clothing");
        tabLayout.getTabAt(1).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtabtext, null);
        tabTwo.setText("Eye Wear");
        tabLayout.getTabAt(2).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtabtext, null);
        tabThree.setText("Foot Wear");
        tabLayout.getTabAt(3).setCustomView(tabThree);

        TextView tabfour = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtabtext, null);
        tabfour.setText("Accessories");
        tabLayout.getTabAt(4).setCustomView(tabfour);*/
    }

    /*private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new CelebStoreFirstTypeFragment(), "All");
        adapter.addFrag(new CelebStoreFirstTypeFragment(), "Clothing");
        adapter.addFrag(new CelebStoreFirstTypeFragment(), "Eye Wear");
        adapter.addFrag(new CelebStoreFirstTypeFragment(), "Foot Wear");
        adapter.addFrag(new CelebStoreFirstTypeFragment(), "Accessories");
        viewPager.setAdapter(adapter);
        Log.e("inseid createviewpager","inseid createviewpager");
        SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "CELEB_STORE", storeType);
        SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "TAB_NO", "ALL");
        //viewPager.setOffscreenPageLimit(0);
        //viewPage.setCurrentItem(0);
    }*/

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_back_navigation_btn) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FashionLandingFragment())
                    .addToBackStack("")
                    .commit();
        } /*else if (view.getId() == R.id.toolbar_frag_multiicons_notification) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new NotificationFragment())
                    .addToBackStack("")
                    .commit();
        } else if (view.getId() == R.id.toolbar_frag_multiicons_cart) {
            Intent intent = new Intent(getActivity(), MyBagActivity.class);
            startActivity(intent);
        }*/
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() == 0) {
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "CELEB_STORE", storeType);
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "TAB_NO", "ALL");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_of_tablayout, new CelebStoreFirstTypeFragment())
                    .commit();
        } else if (tab.getPosition() == 1) {
            Log.e("clikcing clothing","clikcing cllothing");
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "CELEB_STORE", storeType);
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "TAB_NO", "CLOTHING");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_of_tablayout, new CelebStoreFirstTypeFragment())
                    .commit();
        }
        else if (tab.getPosition() == 2) {
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "CELEB_STORE", storeType);
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "TAB_NO", "EYEWEAR");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_of_tablayout, new CelebStoreFirstTypeFragment())
                    .commit();
        }
        else if (tab.getPosition() == 3) {
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "CELEB_STORE", storeType);
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "TAB_NO", "FOOTWEAR");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_of_tablayout, new CelebStoreFirstTypeFragment())
                    .commit();
        }
        else if (tab.getPosition() == 4) {
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "CELEB_STORE", storeType);
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "TAB_NO", "ACCESSORIES");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_of_tablayout, new CelebStoreFirstTypeFragment())
                    .commit();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

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
}
