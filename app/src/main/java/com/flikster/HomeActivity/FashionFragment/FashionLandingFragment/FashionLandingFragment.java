package com.flikster.HomeActivity.FashionFragment.FashionLandingFragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.HomeActivity.CommonFragments.NotificationFragment.NotificationFragment;
import com.flikster.HomeActivity.FashionFragment.FashionFragment;
import com.flikster.HomeActivity.FashionFragment.FashionFragmentAdapter;
import com.flikster.HomeActivity.FashionFragment.FashionType.MovieStoreFragment;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.MyBagActivity.MyBagActivity;
import com.flikster.R;

/**
 * Created by abhishek on 17-10-2017.
 */

public class FashionLandingFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManagerFashionFragment;
    FashionFragmentAdapter fashionFragmentAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    ImageButton toolbar_frag_multiicons_back_navigation, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart;
    TabLayout tabLayout;
    private ViewPager viewPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fashion_landing, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        try {
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setNavigationMode((ActionBar.NAVIGATION_MODE_TABS));
        } catch (Exception e) {

        }

        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        createViewPager(viewPage);
        tabLayout.setupWithViewPager(viewPage);

        createTabIcons();
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        toolbar_frag_multiicons_notification.setOnClickListener(this);
        toolbar_frag_multiicons_cart.setOnClickListener(this);

       /* for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, 50, 0);
            tab.requestLayout();

        }
        View tab = ((ViewGroup) tabLayout.getChildAt(0));*/
    }

    private void initializeViews() {
        toolbar_frag_multiicons_toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_back_navigation = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
        toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        viewPage = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    private void createTabIcons() {


        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtab, null);
        tabOne.setText("Movie Store");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabOne.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_tab_2)));
        }
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingcart, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtab, null);
        tabTwo.setText("Celeb Store");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabTwo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
        }
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingcart, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtab, null);
        tabThree.setText("Mens Store");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabThree.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellowthink)));
        }
//        tabThree.setBackgroundColor(getResources().getColor(R.color.yellowthink));
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingcart, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabfour = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtab, null);
        tabfour.setText("Womens Store");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabfour.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        }
        tabfour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingcart, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabfour);

        TextView tabfive = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.customtab, null);
        tabfive.setText("All Store");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabfive.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
        tabfive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingcart, 0, 0);
        tabLayout.getTabAt(4).setCustomView(tabfive);

    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new MovieStoreFragment(), "Movie Store");
        adapter.addFrag(new MovieStoreFragment(), "Celeb Store");
        adapter.addFrag(new MovieStoreFragment(), "Mens Store");
        adapter.addFrag(new MovieStoreFragment(), "Womens Store");
        adapter.addFrag(new MovieStoreFragment(), "All Store");
        viewPager.setAdapter(adapter);
//        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
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
