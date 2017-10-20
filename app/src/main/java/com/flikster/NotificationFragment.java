package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by abhishek on 17-10-2017.
 */

public class NotificationFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    NotificationAdapter notificationAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_feed,container,false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        notificationAdapter=new NotificationAdapter();
        recyclerView.setAdapter(notificationAdapter);
    }

    private void initializeViews() {
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
    }
}
