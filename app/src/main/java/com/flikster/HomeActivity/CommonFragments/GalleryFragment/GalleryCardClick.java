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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 05-10-2017.
 */

public class GalleryCardClick extends Fragment {
    View view;
    RecyclerView fragment_common_recyclerview_with_tv_recycler;
    RecyclerView.LayoutManager gallaryLayoutManager;
    GalleryCardClickAdapter galleryCardClickAdapter;
    FragmentManager fragmentManager;
    TextView fragment_common_recyclerview_with_tv_title, tv_tag_name, tv_tag_desc;
    ImageView profile_image;
    List<String> galleryImgLinks = new ArrayList<String>();
    String name, profilepic, type, title;
    Bundle bundle = new Bundle();
    String relatedGalleryImgClick = "";
    int clickImgposition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            bundle = getArguments();
            fragmentManager = getActivity().getSupportFragmentManager();
            relatedGalleryImgClick = bundle.getString("RELATED_GALLERY_IMG");
            if (relatedGalleryImgClick.equals("ENABLE")) {
                relatedgalleryItemdata();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        view = inflater.inflate(R.layout.card_gallary_item_onclick, container, false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void relatedgalleryItemdata() {
        clickImgposition = bundle.getInt("IMG_POSITION_VALUE");
        Log.e("clickImgposition", clickImgposition + "");
        Log.e("relatedGalleryImgClick", relatedGalleryImgClick + "");
        GalleryInnerData transactionList = (GalleryInnerData) getArguments().getSerializable("GALLERY_DATA");
        title = transactionList.getHits().get(clickImgposition).get_source().getTitle();
        if (transactionList.getHits().get(clickImgposition).get_source().getMovie() != null && transactionList.getHits().get(clickImgposition).get_source().getMovie().size() != 0) {
            name = transactionList.getHits().get(clickImgposition).get_source().getMovie().get(0).getName();
            profilepic = transactionList.getHits().get(clickImgposition).get_source().getMovie().get(0).getProfilePic();
            type = transactionList.getHits().get(clickImgposition).get_source().getMovie().get(0).getType();
        } else if (transactionList.getHits().get(clickImgposition).get_source().getCeleb() != null && transactionList.getHits().get(clickImgposition).get_source().getCeleb().size() != 0) {
            name = transactionList.getHits().get(clickImgposition).get_source().getCeleb().get(0).getName();
            profilepic = transactionList.getHits().get(clickImgposition).get_source().getCeleb().get(0).getProfilePic();
            type = transactionList.getHits().get(clickImgposition).get_source().getCeleb().get(0).getType();
        }
        if (transactionList.getHits().get(clickImgposition).get_source().getMedia().getGallery().size() != 0) {
            for (int i = 0; i < transactionList.getHits().get(clickImgposition).get_source().getMedia().getGallery().size(); i++) {
                galleryImgLinks.add(transactionList.getHits().get(clickImgposition).get_source().getMedia().getGallery().get(i));
            }
        }
        Log.e("relatedGalleryImgDATA", galleryImgLinks.size() + "");
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
        galleryCardClickAdapter = new GalleryCardClickAdapter(getActivity(), fragmentManager, galleryImgLinks);
        fragment_common_recyclerview_with_tv_recycler.setAdapter(galleryCardClickAdapter);
    }

    private void initializeViews() {
        fragment_common_recyclerview_with_tv_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        profile_image = (ImageView) view.findViewById(R.id.profile_image);
        tv_tag_name = (TextView) view.findViewById(R.id.tv_tag_name);
        tv_tag_desc = (TextView) view.findViewById(R.id.tv_tag_desc);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    public void updateImage(List<String> galleryImgLinks, String name, String profilePic, String type, String title) {
        this.galleryImgLinks = galleryImgLinks;
        this.name = name;
        this.profilepic = profilePic;
        this.type = type;
        this.title = title;
    }

    /*public interface Galleryimageclick {
        void galleryCardOnItemEvent(List<String> galleryImgLinks, String name, String profilePic, String type, String title, Fragment fragment);

    }

    public void onClickupdateImage(List<String> galleryImgLinks, String name, String profilePic, String type, String title) {
        this.galleryImgLinks = galleryImgLinks;
        this.name = name;
        Log.e("name", this.name);
        this.profilepic = profilePic;
        this.type = type;
        this.title = title;
    }*/
}
