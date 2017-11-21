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

/**
 * Created by abhishek on 17-10-2017.
 */

public class CeleStoreFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManagerFashionFragment;
    FashionFragmentAdapter fashionFragmentAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    ImageButton toolbar_frag_multiicons_back_navigation, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart;
    TabLayout tabLayout;
    private ScrollableViewPager viewPage;
    TextView toolbar_frag_multiicons_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fashion_landing, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        toolbar_frag_multiicons_title.setText("Men Fashion");
        createViewPager(viewPage);
        tabLayout.setupWithViewPager(viewPage);
        createTabIcons();
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        toolbar_frag_multiicons_notification.setOnClickListener(this);
        toolbar_frag_multiicons_cart.setOnClickListener(this);
    }

    private void initializeViews() {
        toolbar_frag_multiicons_title = (TextView) view.findViewById(R.id.toolbar_frag_multiicons_title);
        toolbar_frag_multiicons_toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_back_navigation = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
        toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        viewPage = (ScrollableViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
//        toolbar_frag_multiicons_toolbar.setVisibility(View.GONE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    private void createTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtabtext, null);
        tabOne.setText("Clothing");
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtabtext, null);
        tabTwo.setText("Eye Wear");
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtabtext, null);
        tabThree.setText("Foot Wear");
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabfour = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtabtext, null);
        tabfour.setText("Accessories");
        tabLayout.getTabAt(3).setCustomView(tabfour);

        TextView tabfive = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtabtext, null);
        tabfive.setText("Other");
        tabLayout.getTabAt(4).setCustomView(tabfive);

    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new CelebStoreFirstTypeFragment(), "Clothing");
        adapter.addFrag(new CelebStoreFirstTypeFragment(), "Eye Wear");
        adapter.addFrag(new CelebStoreFirstTypeFragment(), "Foot Wear");
        adapter.addFrag(new CelebStoreFirstTypeFragment(), "Accessories");
        adapter.addFrag(new CelebStoreFirstTypeFragment(), "Other");
        viewPager.setAdapter(adapter);
        viewPage.setCurrentItem(0);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FashionLandingFragment())
                    .addToBackStack("")
                    .commit();
        } else if (view.getId() == R.id.toolbar_frag_multiicons_notification) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new NotificationFragment())
                    .addToBackStack("")
                    .commit();
        } else if (view.getId() == R.id.toolbar_frag_multiicons_cart) {
            Intent intent = new Intent(getActivity(), MyBagActivity.class);
            startActivity(intent);
        }
    }
}
