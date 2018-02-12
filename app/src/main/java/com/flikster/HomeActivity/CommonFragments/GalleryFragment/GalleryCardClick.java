package com.flikster.HomeActivity.CommonFragments.GalleryFragment;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.R;
import com.flikster.Util.Common;
import com.rohitarya.glide.facedetection.transformation.core.GlideFaceDetector;

import java.util.List;

/**
 * Created by abhishek on 05-10-2017.
 */

public class GalleryCardClick extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_with_tv_recycler;
    RecyclerView.LayoutManager gallaryLayoutManager;
    GalleryCardClickAdapter galleryCardClickAdapter;
    FragmentManager fragmentManager;
    TextView fragment_common_recyclerview_with_tv_title, tv_tag_name, tv_tag_desc;
    ImageView profile_image;
    List<String> galleryImgLinks;
    String name, profilepic, type, title, userId, entityId;
    GalleryRecommendationItemClick galleryRecommendationItemClick;
    ImageButton toolbar_back_navigation_btn;
    Button followbtn;
    String cardId;

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
        GlideFaceDetector.releaseDetector();
    }

    private void initializeRest() {
        GlideFaceDetector.initialize(getActivity());
        Glide.with(getActivity()).load(profilepic).asBitmap().into(profile_image);
        fragment_common_recyclerview_with_tv_title.setText(title);
        fragment_common_recyclerview_with_tv_title.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        tv_tag_name.setText(name);
        tv_tag_desc.setText(type);
        gallaryLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_with_tv_recycler.setLayoutManager(gallaryLayoutManager);
        galleryCardClickAdapter = new GalleryCardClickAdapter(getActivity(), fragmentManager,
                galleryImgLinks, galleryRecommendationItemClick, userId,cardId);
        fragment_common_recyclerview_with_tv_recycler.setAdapter(galleryCardClickAdapter);
        toolbar_back_navigation_btn.setOnClickListener(this);
        //Log.e("cehc for followe",""+userId+"AND"+entityId);
        new PostRetrofit().checkForFollow("follow", userId, entityId, followbtn, getContext());
        followbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.followOrUnFollow(getContext(), followbtn, userId, entityId);
            }
        });//41015e26-d119-4110-9f27-6205d0561606AND78c2279e-57c1-4b3c-8d92-a643f91f1661
    }

    private void initializeViews() {
        fragment_common_recyclerview_with_tv_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        profile_image = (ImageView) view.findViewById(R.id.profile_image);
        tv_tag_name = (TextView) view.findViewById(R.id.tv_tag_name);
        tv_tag_desc = (TextView) view.findViewById(R.id.tv_tag_desc);
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
        fragmentManager = getActivity().getSupportFragmentManager();
        followbtn = (Button) view.findViewById(R.id.followbtn);

    }

    public void updateImage(List<String> galleryImgLinks, String name,
                            String profilePic, String type, String title, String userId, String entityId,String cardId) {
        this.galleryImgLinks = galleryImgLinks;
        this.name = name;
        this.profilepic = profilePic;
        this.type = type;
        this.title = title;
        this.userId = userId;
        this.entityId = entityId;
        this.cardId=cardId;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_back_navigation_btn) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .commit();
        }
    }

    public interface GalleryRecommendationItemClick {
        void galleryRecommendationItemClickMethod(List<String> galleryImgLinks,
                                                  String name, String profilePic, String type,
                                                  String title, Fragment fragment, String userId, String entityId,String cardId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        galleryRecommendationItemClick = (GalleryRecommendationItemClick) activity;
    }



}
