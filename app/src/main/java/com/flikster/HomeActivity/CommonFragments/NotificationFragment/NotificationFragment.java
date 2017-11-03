package com.flikster.HomeActivity.CommonFragments.NotificationFragment;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flikster.R;

/**
 * Created by abhishek on 17-10-2017.
 */

public class NotificationFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManager;
    NotificationAdapter notificationAdapter;
    Button button, backhomebtn;
    Toolbar toolbar_frag_multiicons_toolbar;
    ImageButton toolbar_frag_multiicons_back_navigation;
    TextView toolbar_frag_multiicons_title, nodataavailtxt;
    LinearLayout nodatalayout;
    ImageView notifcationimg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_my_bag, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        button.setVisibility(View.GONE);
        fragment_common_recyclerview_recycler.setVisibility(View.VISIBLE);
        nodatalayout.setVisibility(View.GONE);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
        notificationAdapter = new NotificationAdapter();
        fragment_common_recyclerview_recycler.setAdapter(notificationAdapter);
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        backhomebtn.setOnClickListener(this);
        toolbar_frag_multiicons_title.setText("Notifications");
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        button = (Button) view.findViewById(R.id.activity_my_bag_bottom_continue_btn);
        toolbar_frag_multiicons_toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_back_navigation = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_title = (TextView) view.findViewById(R.id.toolbar_frag_multiicons_title);

        nodatalayout = (LinearLayout) view.findViewById(R.id.nodatalayout);
        notifcationimg = (ImageView) view.findViewById(R.id.notifcationimg);
        nodataavailtxt = (TextView) view.findViewById(R.id.nodataavailtxt);
        backhomebtn = (Button) view.findViewById(R.id.backhomebtn);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
            getFragmentManager().popBackStackImmediate();
        } else if (view.getId() == R.id.backhomebtn) {
            getFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}
