package com.flikster.HomeActivity.FashionFragment;

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

import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.MyBagActivity.MyBagActivity;
import com.flikster.HomeActivity.CommonFragments.NotificationFragment.NotificationFragment;
import com.flikster.R;

/**
 * Created by abhishek on 17-10-2017.
 */

public class FashionFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView recyclerViewFashionFragment;
    RecyclerView.LayoutManager layoutManagerFashionFragment;
    FashionFragmentAdapter fashionFragmentAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    ImageButton toolbar_frag_multiicons_back_navigation,toolbar_frag_multiicons_notification,toolbar_frag_multiicons_cart;

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
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        toolbar_frag_multiicons_notification.setOnClickListener(this);
        toolbar_frag_multiicons_cart.setOnClickListener(this);
    }

    private void initializeViews() {
        recyclerViewFashionFragment = (RecyclerView) view.findViewById(R.id.fashion_recyclerview);
        toolbar_frag_multiicons_toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_back_navigation=(ImageButton)view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_notification=(ImageButton)view.findViewById(R.id.toolbar_frag_multiicons_notification);
        toolbar_frag_multiicons_cart=(ImageButton)view.findViewById(R.id.toolbar_frag_multiicons_cart);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.toolbar_frag_multiicons_back_navigation)
        {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container,new FeedFragment())
                    .addToBackStack("")
                    .commit();
        }
        else if(view.getId()==R.id.toolbar_frag_multiicons_notification)
        {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container,new NotificationFragment())
                    .addToBackStack("")
                    .commit();
        }
        else if(view.getId()==R.id.toolbar_frag_multiicons_cart)
        {
            Intent intent=new Intent(getActivity(),MyBagActivity.class);
            startActivity(intent);
        }
    }
}
