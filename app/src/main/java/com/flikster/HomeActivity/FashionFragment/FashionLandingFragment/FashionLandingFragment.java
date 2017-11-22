package com.flikster.HomeActivity.FashionFragment.FashionLandingFragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
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
import com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CeleStoreFragment;
import com.flikster.HomeActivity.FashionFragment.FashionType.MenFashionFragment.MenFashionLandingFragment;
import com.flikster.HomeActivity.FashionFragment.FashionType.MovieStoreFragment.MovieStoreFragment;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.MyBagActivity.MyBagActivity;
import com.flikster.R;
import com.flikster.Util.ScrollableViewPager;
import com.flikster.Util.SharedPrefsUtil;

/**
 * Created by abhishek on 17-10-2017.
 */

public class FashionLandingFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManagerFashionFragment;
    FashionFragmentAdapter fashionFragmentAdapter;
    Toolbar toolbar_frag_toolbar;
    ImageButton toolbar_frag_multiicons_back_navigation, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart;
    TabLayout tabLayout;
    private ScrollableViewPager viewPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fashion_landing, container, false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        createViewPager(viewPage);
        tabLayout.setupWithViewPager(viewPage);
        createTabIcons();
//        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
//        toolbar_frag_multiicons_notification.setOnClickListener(this);
//        toolbar_frag_multiicons_cart.setOnClickListener(this);
    }

    private void initializeViews() {
        toolbar_frag_toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_toolbar);
//        toolbar_frag_multiicons_back_navigation = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
//        toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
//        toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        toolbar_frag_toolbar.setVisibility(View.GONE);
        viewPage = (ScrollableViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
//    }

    private void createTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtab, null);
        tabOne.setText("All Store");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabOne.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_tab_2)));
        }
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingcart, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtab, null);
        tabTwo.setText("Celeb Store");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabTwo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.horizontal_line)));
        }
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingcart, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtab, null);
        tabThree.setText("Men Fashion");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabThree.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellowthink)));
        }
//        tabThree.setBackgroundColor(getResources().getColor(R.color.yellowthink));
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingcart, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabfour = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtab, null);
        tabfour.setText("Women Fashion");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabfour.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark_blue)));
        }
        tabfour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingcart, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabfour);

        TextView tabfive = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtab, null);
        tabfive.setText("Movie Store");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabfive.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.ligth_blue_grey)));
        }
        tabfive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingcart, 0, 0);
        tabLayout.getTabAt(4).setCustomView(tabfive);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                viewPage.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new MovieStoreFragment())
                            .addToBackStack("")
                            .commit();
                } else if (tab.getPosition() == 1) {
                    SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(),"HEADER_NAME","CELEB_STORE");
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new CeleStoreFragment())
                            .addToBackStack("")
                            .commit();
                } else if (tab.getPosition() == 2) {
                    SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(),"HEADER_NAME","MEN");
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new MenFashionLandingFragment())
                            .addToBackStack("")
                            .commit();
                } else if (tab.getPosition() == 3) {
                    SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(),"HEADER_NAME","WOMEN");
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new MenFashionLandingFragment())
                            .addToBackStack("")
                            .commit();
                } else if (tab.getPosition() == 4) {
                    SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(),"HEADER_NAME","MOVIE_STORE");
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new CeleStoreFragment())
                            .addToBackStack("")
                            .commit();
                } else if (tab.getPosition() == 5) {

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFrag(new MovieStoreFragment(), "Movie Store");
        adapter.addFrag(new MovieStoreFragment(), "Celeb Store");
        adapter.addFrag(new MovieStoreFragment(), "Mens Store");
        adapter.addFrag(new MovieStoreFragment(), "Womens Store");
        adapter.addFrag(new MovieStoreFragment(), "All Store");
        viewPager.setAdapter(adapter);
//        viewPage.setCurrentItem(0);
    }


    @Override
    public void onClick(View view) {
       /* if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .addToBackStack("")
                    .commit();
        }*//* else if (view.getId() == R.id.toolbar_frag_multiicons_notification) {
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
}
