package com.flikster.HomeActivity.CommonFragments.MyAccountFragment.UserProfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.R;
import com.leo.simplearcloader.SimpleArcLoader;

/**
 * Created by abhishek on 21-10-2017.
 */

public class MyAccountFragmentMyFeeds extends Fragment {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManagerMyPost;
    MyAccountFragmentMyFeedsAdapter myAccountFragmentMyFeedsAdapter;
    TextView nodataavailtxt;
    SimpleArcLoader mDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        layoutManagerMyPost = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManagerMyPost);
        myAccountFragmentMyFeedsAdapter = new MyAccountFragmentMyFeedsAdapter();
        fragment_common_recyclerview_recycler.setAdapter(myAccountFragmentMyFeedsAdapter);
        fragment_common_recyclerview_recycler.setBackgroundColor(getActivity().getResources().getColor(R.color.backprofilescreen));
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        nodataavailtxt = (TextView) view.findViewById(R.id.nodataavailtxt);
        mDialog = (SimpleArcLoader) view.findViewById(R.id.arc_loader);
    }
}
