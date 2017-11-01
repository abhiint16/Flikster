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
 * Created by abhishek on 21-10-2017.
 */

public class MyAccountFragmentMyPosts extends Fragment{
    View view;
    RecyclerView recyclerViewMyPost;
    RecyclerView.LayoutManager layoutManagerMyPost;
    MyAccountFragmentMyPostsAdapter myAccountFragmentMyPostsAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_feed,container,false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        layoutManagerMyPost=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerViewMyPost.setLayoutManager(layoutManagerMyPost);
        myAccountFragmentMyPostsAdapter=new MyAccountFragmentMyPostsAdapter();
        recyclerViewMyPost.setAdapter(myAccountFragmentMyPostsAdapter);
    }

    private void initializeViews() {
        recyclerViewMyPost=(RecyclerView)view.findViewById(R.id.recyclerview);
    }
}
