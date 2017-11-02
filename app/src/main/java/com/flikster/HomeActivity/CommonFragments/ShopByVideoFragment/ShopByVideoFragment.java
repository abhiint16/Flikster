package com.flikster.HomeActivity.CommonFragments.ShopByVideoFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.flikster.R;

/**
 * Created by abhishek on 01-11-2017.
 */

public class ShopByVideoFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView recyclerViewShopByVideoFragment;
    RecyclerView.LayoutManager layoutManagerShopByVideoFragment;
    ShopByVideoFragmentAdapter shopByVideoFragmentAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    ImageButton toolbar_frag_multiicons_back_navigation,toolbar_frag_multiicons_notification,toolbar_frag_multiicons_cart;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_fashion,container,false);
        initializeViews();
        initializeRest();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    private void initializeRest() {
        fragmentManager=getActivity().getSupportFragmentManager();
        layoutManagerShopByVideoFragment = new GridLayoutManager(getActivity(),2);
        recyclerViewShopByVideoFragment.setLayoutManager(layoutManagerShopByVideoFragment);
        shopByVideoFragmentAdapter = new ShopByVideoFragmentAdapter(getActivity(),fragmentManager);
        recyclerViewShopByVideoFragment.setAdapter(shopByVideoFragmentAdapter);
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        toolbar_frag_multiicons_notification.setVisibility(View.GONE);
        toolbar_frag_multiicons_cart.setVisibility(View.GONE);
    }

    private void initializeViews() {
        recyclerViewShopByVideoFragment = (RecyclerView) view.findViewById(R.id.fashion_recyclerview);
        toolbar_frag_multiicons_toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_back_navigation=(ImageButton)view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_notification=(ImageButton)view.findViewById(R.id.toolbar_frag_multiicons_notification);
        toolbar_frag_multiicons_cart=(ImageButton)view.findViewById(R.id.toolbar_frag_multiicons_cart);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.toolbar_frag_multiicons_notification)
        {
            fragmentManager.popBackStackImmediate();
        }
    }
}
