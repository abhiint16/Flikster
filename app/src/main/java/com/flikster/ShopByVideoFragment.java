package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by abhishek on 01-11-2017.
 */

public class ShopByVideoFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView recyclerViewShopByVideoFragment;
    RecyclerView.LayoutManager layoutManagerShopByVideoFragment;
    ShopByVideoFragmentAdapter shopByVideoFragmentAdapter;
    Toolbar toolbar;
    ImageButton toolbar_navigation_icon,toolbar_notification_icon,toolbar_cart_icon;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_fashion,container,false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        layoutManagerShopByVideoFragment = new GridLayoutManager(getActivity(),2);
        recyclerViewShopByVideoFragment.setLayoutManager(layoutManagerShopByVideoFragment);
        shopByVideoFragmentAdapter = new ShopByVideoFragmentAdapter(getActivity());
        recyclerViewShopByVideoFragment.setAdapter(shopByVideoFragmentAdapter);
        toolbar_navigation_icon.setOnClickListener(this);
        toolbar_notification_icon.setOnClickListener(this);
        toolbar_cart_icon.setOnClickListener(this);
    }

    private void initializeViews() {
        recyclerViewShopByVideoFragment = (RecyclerView) view.findViewById(R.id.fashion_recyclerview);
        toolbar = (Toolbar) view.findViewById(R.id.fragment_fashion_toolbar);
        toolbar_navigation_icon=(ImageButton)view.findViewById(R.id.toolbar_navigation_icon);
        toolbar_notification_icon=(ImageButton)view.findViewById(R.id.toolbar_notification_icon);
        toolbar_cart_icon=(ImageButton)view.findViewById(R.id.toolbar_cart_icon);
    }

    @Override
    public void onClick(View view) {
    }
}
