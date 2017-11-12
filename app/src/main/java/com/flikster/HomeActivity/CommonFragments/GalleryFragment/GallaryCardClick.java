package com.flikster.HomeActivity.CommonFragments.GalleryFragment;

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

import com.bumptech.glide.Glide;
import com.flikster.R;

import java.util.List;

/**
 * Created by abhishek on 05-10-2017.
 */

public class GallaryCardClick extends Fragment {
    View view;
    RecyclerView fragment_common_recyclerview_with_tv_recycler;
    RecyclerView.LayoutManager gallaryLayoutManager;
    GallaryCardClickAdapter gallaryCardClickAdapter;
    FragmentManager fragmentManager;
    TextView fragment_common_recyclerview_with_tv_title, tv_tag_name, tv_tag_desc;
    ImageView profile_image;
    List<String> galleryImgLinks;
    String name,profilepic,type,title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.card_gallary_item_onclick, container, false);
        initializeViews();
        initializeRest();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    private void initializeRest() {
        Glide.with(getActivity()).load(profilepic).asBitmap().into(profile_image);
        fragment_common_recyclerview_with_tv_title.setText(title);
        fragment_common_recyclerview_with_tv_title.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        tv_tag_name.setText(name);
        tv_tag_desc.setText(type);
        gallaryLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_with_tv_recycler.setLayoutManager(gallaryLayoutManager);
        gallaryCardClickAdapter = new GallaryCardClickAdapter(getActivity(), fragmentManager,galleryImgLinks);
        fragment_common_recyclerview_with_tv_recycler.setAdapter(gallaryCardClickAdapter);
    }

    private void initializeViews() {
        fragment_common_recyclerview_with_tv_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        profile_image = (ImageView) view.findViewById(R.id.profile_image);
        tv_tag_name = (TextView) view.findViewById(R.id.tv_tag_name);
        tv_tag_desc = (TextView) view.findViewById(R.id.tv_tag_desc);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    public void updateImage(List<String> galleryImgLinks,String name,String profilePic,String type,String title)
    {
        this.galleryImgLinks=galleryImgLinks;
        this.name=name;
        this.profilepic=profilePic;
        this.type=type;
        this.title=title;
    }

}
