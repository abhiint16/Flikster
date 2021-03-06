package com.flikster.HomeActivity.FashionFragment.FashionLandingFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionFeedFragment;
import com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CeleStoreFragment;
import com.flikster.HomeActivity.FashionFragment.FashionType.MenFashionFragment.MenFashionLandingFragment;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreFragment;
import com.flikster.R;
import com.flikster.Util.ScrollableViewPager;
import com.flikster.Util.SharedPrefsUtil;

/**
 * Created by abhishek on 17-10-2017.
 */

public class FashionLandingFragment extends Fragment implements View.OnClickListener {
    View view;
    Toolbar toolbar_frag_toolbar;
    TabLayout tabLayout;
    private ScrollableViewPager viewPage;
    Bundle bundle;
    String flagEnable = "";

    int[] tabImages = {R.drawable.allstore, R.drawable.celebritystore, R.drawable.menstoreicon, R.drawable.moviestrore,
            R.drawable.fashion_womenstore_tab,R.drawable.auctionslide};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fashion_landing, container, false);
        checkcliclickLayout();
        initializeViews();
        initializeRest();

        return view;
    }

    private void checkcliclickLayout() {
        bundle = getArguments();
        try {
            if (bundle.getString("BUY_FASHION_PAGE") != null && !bundle.getString("BUY_FASHION_PAGE").isEmpty()) {
                flagEnable = bundle.getString("BUY_FASHION_PAGE");
            }
        } catch (Exception e) {

        }
    }

    private void initializeRest() {
        createViewPager(viewPage);
        tabLayout.setupWithViewPager(viewPage);
        createTabIcons();
    }

    private void initializeViews() {
        toolbar_frag_toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_toolbar);
        toolbar_frag_toolbar.setVisibility(View.GONE);
        viewPage = (ScrollableViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

    }

    private void createTabIcons() {
       /* TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtab, null);
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
        tabLayout.getTabAt(4).setCustomView(tabfive);*/

        /*mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setIcon(R.drawable.your_icon);
        }*/
//        tabLayout.setCo

        /*View view1 = getContext().getLayoutInflater().inflate(R.layout.customtab, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.my1);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view1));


        View view2 = getLayoutInflater().inflate(R.layout.customtab, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.my2);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view2));


        View view3 = getLayoutInflater().inflate(R.layout.customtab, null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.my3);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view3));
        tabLayout.setupWithViewPager(viewPage);*/
        for (int j = 0; j < tabLayout.getTabCount(); j++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setMinimumHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setMaxHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//            imageView.set
            imageView.setImageResource(tabImages[j]);
            tabLayout.getTabAt(j).setCustomView(imageView);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new AllStoreFragment())
                            .addToBackStack("")
                            .commit();
                } else if (tab.getPosition() == 1) {
                    SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "HEADER_NAME", "CELEB_STORE");
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new CeleStoreFragment())
                            .addToBackStack("")
                            .commit();
                } else if (tab.getPosition() == 2) {
                    SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "HEADER_NAME", "MEN");
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new MenFashionLandingFragment())
                            .addToBackStack("")
                            .commit();
                } else if (tab.getPosition() == 3) {
                    SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "HEADER_NAME", "MOVIE_STORE");
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new CeleStoreFragment())
                            .addToBackStack("")
                            .commit();
                } else if (tab.getPosition() == 4) {
                    SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "HEADER_NAME", "WOMEN");
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new MenFashionLandingFragment())
                            .addToBackStack("")
                            .commit();
                } else if (tab.getPosition() == 5) {
                    SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "HEADER_NAME", "WOMEN");
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new AuctionFeedFragment())
                            .addToBackStack("")
                            .commit();
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

    private void allotEachTabWithEqualWidth() {

        ViewGroup slidingTabStrip = (ViewGroup) tabLayout.getChildAt(0);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View tab = slidingTabStrip.getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tab.getLayoutParams();
            layoutParams.weight = 1;
            tab.setLayoutParams(layoutParams);
        }

    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFrag(new AllStoreFragment(), null);
        adapter.addFrag(new AllStoreFragment(), null);
        adapter.addFrag(new AllStoreFragment(), null);
        adapter.addFrag(new AllStoreFragment(), null);
        adapter.addFrag(new AllStoreFragment(), null);
        adapter.addFrag(new AllStoreFragment(), null);
        viewPager.setAdapter(adapter);
//        viewPage.setCurrentItem(0);
        if (flagEnable != null && !flagEnable.isEmpty()) {
            viewPage.setCurrentItem(2);
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "HEADER_NAME", "MEN");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new MenFashionLandingFragment())
                    .addToBackStack("")
                    .commit();
        } else {
//            viewPage.setCurrentItem(0);
        }
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
