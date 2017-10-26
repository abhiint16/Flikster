package com.flikster;

import android.content.Intent;
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

public class FashionFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView recyclerViewFashionFragment;
    RecyclerView.LayoutManager layoutManagerFashionFragment;
    FashionFragmentAdapter fashionFragmentAdapter;
    Toolbar toolbar;
    ImageButton toolbar_navigation_icon,toolbar_notification_icon,toolbar_cart_icon;

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
        toolbar_navigation_icon.setOnClickListener(this);
        toolbar_notification_icon.setOnClickListener(this);
        toolbar_cart_icon.setOnClickListener(this);
    }

    private void initializeViews() {
        recyclerViewFashionFragment = (RecyclerView) view.findViewById(R.id.fashion_recyclerview);
        toolbar = (Toolbar) view.findViewById(R.id.fragment_fashion_toolbar);
        toolbar_navigation_icon=(ImageButton)view.findViewById(R.id.toolbar_navigation_icon);
        toolbar_notification_icon=(ImageButton)view.findViewById(R.id.toolbar_notification_icon);
        toolbar_cart_icon=(ImageButton)view.findViewById(R.id.toolbar_cart_icon);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.toolbar_navigation_icon)
        {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container,new FeedFragment())
                    .addToBackStack("")
                    .commit();
        }
        else if(view.getId()==R.id.toolbar_notification_icon)
        {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container,new NotificationFragment())
                    .addToBackStack("")
                    .commit();
        }
        else if(view.getId()==R.id.toolbar_cart_icon)
        {
            Intent intent=new Intent(getActivity(),MyBagActivity.class);
            startActivity(intent);
        }
    }
}
