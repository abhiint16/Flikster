package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by abhishek on 17-10-2017.
 */

public class FashionFragment extends Fragment {
    View view;
    RecyclerView recyclerViewFashionFragment;
    RecyclerView.LayoutManager layoutManagerFashionFragment;
    FashionFragmentAdapter fashionFragmentAdapter;
    Toolbar toolbar;
    ImageButton toolbar_navigation_icon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fashion, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        layoutManagerFashionFragment = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewFashionFragment.setLayoutManager(layoutManagerFashionFragment);
        fashionFragmentAdapter = new FashionFragmentAdapter(getActivity());
        recyclerViewFashionFragment.setAdapter(fashionFragmentAdapter);
        toolbar_navigation_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }

    private void initializeViews() {
        recyclerViewFashionFragment = (RecyclerView) view.findViewById(R.id.fashion_recyclerview);
        toolbar = (Toolbar) view.findViewById(R.id.fragment_fashion_toolbar);
        toolbar_navigation_icon=(ImageButton)view.findViewById(R.id.toolbar_navigation_icon);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}
