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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by abhishek on 17-10-2017.
 */

public class NotificationFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    NotificationAdapter notificationAdapter;
    Button button;
    Toolbar toolbar;
    ImageButton backButton;
    TextView titleToolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_my_bag,container,false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        button.setVisibility(View.GONE);
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        notificationAdapter=new NotificationAdapter();
        recyclerView.setAdapter(notificationAdapter);
        backButton.setOnClickListener(this);
        titleToolbar.setText("Notifications");
    }

    private void initializeViews() {
        recyclerView=(RecyclerView)view.findViewById(R.id.activity_my_bag_recycler_view);
        button=(Button)view.findViewById(R.id.activity_my_bag_bottom_continue_btn);
        toolbar=(Toolbar)view.findViewById(R.id.activity_my_bag_toolbar);
        backButton=(ImageButton)view.findViewById(R.id.activity_my_bag_toolbar_back_navigation_btn);
        titleToolbar=(TextView)view.findViewById(R.id.activity_my_bag_toolbar_title);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.activity_my_bag_toolbar_back_navigation_btn)
        {
            getFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}
