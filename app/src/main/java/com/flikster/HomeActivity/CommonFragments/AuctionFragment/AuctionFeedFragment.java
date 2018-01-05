package com.flikster.HomeActivity.CommonFragments.AuctionFragment;

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

import com.flikster.HomeActivity.FashionFragment.FashionLandingFragment.FashionLandingFragment;
import com.flikster.HomeActivity.RatingFragment.RatingAdapter;
import com.flikster.R;

/**
 * Created by abhishek on 17-10-2017.
 */

public class AuctionFeedFragment extends Fragment implements View.OnClickListener {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    AuctionFeedViewPagerAdapter auctionFeedViewPagerAdapter;
    FragmentManager fragmentManager;
    TextView toolbar_frag_title;
    ImageButton toolbar_back_navigation_btn;
    Toolbar toolbar_frag_multiicons_toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_auction_feed, container, false);
        initializeViews();
        initializeRest();
        return view;
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

    private void initializeRest() {
        auctionFeedViewPagerAdapter = new AuctionFeedViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(auctionFeedViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        toolbar_frag_multiicons_toolbar.setBackgroundColor(getResources().getColor(R.color.ligth_blue_shades));

        toolbar_frag_title.setText("Auction");
        toolbar_frag_title.setVisibility(View.GONE);
        toolbar_back_navigation_btn.setOnClickListener(this);
    }

    private void initializeViews() {
        toolbar_frag_multiicons_toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_multiicons_toolbar);
        viewPager = (ViewPager) view.findViewById(R.id.celebrity_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.celebrity_tablayout);
        toolbar_frag_title = (TextView) view.findViewById(R.id.toolbar_frag_multiicons_title);
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        tabLayout.setTabTextColors(getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.black));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
            // getFragmentManager().popBackStackImmediate();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FashionLandingFragment())
                    .addToBackStack("")
                    .commit();
        }
    }
}
