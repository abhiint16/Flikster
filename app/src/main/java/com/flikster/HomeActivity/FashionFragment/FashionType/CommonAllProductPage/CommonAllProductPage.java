package com.flikster.HomeActivity.FashionFragment.FashionType.CommonAllProductPage;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.R;

import java.util.List;

/**
 * Created by abhishek on 14-12-2017.
 */

public class CommonAllProductPage extends Fragment {
    View view;
    RecyclerView fragment_common_recyclerview_with_tv_recycler;
    RecyclerView.LayoutManager gallaryLayoutManager;
    FragmentManager fragmentManager;
    TextView fragment_common_recyclerview_with_tv_title, tv_tag_name, tv_tag_desc;
    ImageView profile_image;
    ImageButton toolbar_back_navigation_btn;
    CommonAllProductPageAdapter commonAllProductPageAdapter;
    String productId;
    List<String> size;
    String userId;
    String price;
    String profilePic;
    String productTitle;String productSlug;List<String> imageGallery;
    String celebProfilePic;List<String> celebRole;String celebName;String celebTitle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.card_gallary_item_onclick,container,false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        Glide.with(getActivity()).load(celebProfilePic).asBitmap().into(profile_image);
        fragment_common_recyclerview_with_tv_title.setText(celebTitle);
        fragment_common_recyclerview_with_tv_title.setBackgroundColor(getActivity().getResources().getColor(R.color.dark_grey));
        fragment_common_recyclerview_with_tv_title.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        tv_tag_name.setText(celebName);
        Log.e("check role",""+celebRole);
//        tv_tag_desc.setText(celebRole.get(0));
        gallaryLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_with_tv_recycler.setLayoutManager(gallaryLayoutManager);
        commonAllProductPageAdapter = new CommonAllProductPageAdapter(getActivity(), fragmentManager,imageGallery);
        fragment_common_recyclerview_with_tv_recycler.setAdapter(commonAllProductPageAdapter);
        //toolbar_back_navigation_btn.setOnClickListener(this);
    }

    private void initializeViews() {
        fragment_common_recyclerview_with_tv_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        profile_image = (ImageView) view.findViewById(R.id.profile_image);
        tv_tag_name = (TextView) view.findViewById(R.id.tv_tag_name);
        tv_tag_desc = (TextView) view.findViewById(R.id.tv_tag_desc);
        toolbar_back_navigation_btn=(ImageButton)view.findViewById(R.id.toolbar_back_navigation_btn);
        fragmentManager = getActivity().getSupportFragmentManager();
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

    public void getProductData(String productId, List<String> size,String userId,String price,String profilePic,
                               String productTitle,String productSlug,List<String> imageGallery,
                               String celebProfilePic,List<String> celebRole,String celebName,String celebTitle)
    {
        this.productId=productId;
        this.size=size;
        this.userId=userId;
        this.price=price;
        this.profilePic=profilePic;
        this.productTitle=productTitle;
        this.productSlug=productSlug;
        this.imageGallery=imageGallery;
        this.celebProfilePic=celebProfilePic;
        this.celebRole=celebRole;
        this.celebName=celebName;
        this.celebTitle=celebTitle;
    }
}
