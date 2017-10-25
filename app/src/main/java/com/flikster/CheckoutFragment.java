package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by abhishek on 25-10-2017.
 */

public class CheckoutFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CheckoutAdapter checkoutAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_checkout,container,false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        checkoutAdapter=new  CheckoutAdapter();
        recyclerView.setAdapter(checkoutAdapter);
    }

    private void initializeViews() {
        recyclerView=(RecyclerView)view.findViewById(R.id.fragment_checkout_recyclerview);
    }
}
