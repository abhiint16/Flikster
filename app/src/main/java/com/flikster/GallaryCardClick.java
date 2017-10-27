package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by abhishek on 05-10-2017.
 */

public class GallaryCardClick extends Fragment {
    View view;
    RecyclerView gallary_recyclerview;
    RecyclerView.LayoutManager gallaryLayoutManager;
    GallaryCardClickAdapter gallaryCardClickAdapter;
    FragmentManager fragmentManager;
    TextView tv_tag_name,tv_tag_desc;
    ImageView profile_image;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.card_gallary_item_onclick,container,false);
        initializeViews();
        initializeRest();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    private void initializeRest() {
        profile_image.setImageResource(R.drawable.pooja2);
        tv_tag_name.setText("Pooja Hegde");
        tv_tag_desc.setText("#Actress");
        gallaryLayoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        gallary_recyclerview.setLayoutManager(gallaryLayoutManager);
        gallaryCardClickAdapter=new GallaryCardClickAdapter(getActivity(),fragmentManager);
        gallary_recyclerview.setAdapter(gallaryCardClickAdapter);
    }

    private void initializeViews() {
        gallary_recyclerview=(RecyclerView)view.findViewById(R.id.gallary_recyclerview);
        profile_image=(ImageView)view.findViewById(R.id.profile_image);
        tv_tag_name=(TextView)view.findViewById(R.id.tv_tag_name);
        tv_tag_desc=(TextView)view.findViewById(R.id.tv_tag_desc);
        fragmentManager=getActivity().getSupportFragmentManager();
    }
}
