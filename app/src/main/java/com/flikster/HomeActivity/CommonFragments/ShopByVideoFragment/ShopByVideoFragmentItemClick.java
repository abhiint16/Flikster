package com.flikster.HomeActivity.CommonFragments.ShopByVideoFragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.flikster.R;

/**
 * Created by abhishek on 01-11-2017.
 */

public class ShopByVideoFragmentItemClick extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManager;
    ShopByVideoFragmentItemClickAdapter shopByVideoFragmentItemClickAdapter;
    VideoView card_shopby_video_recycler_item_onclick_playVideo;
    FragmentManager fragmentManager;
    ImageButton toolbar_back_navigation_btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.card_shopby_video_recycler_item_onclick, container, false);
        initializeViews();
        initializeRest();
        playLocalVideo();
        return view;
    }

    private void initializeRest() {
        fragmentManager=getActivity().getSupportFragmentManager();
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
        shopByVideoFragmentItemClickAdapter=new ShopByVideoFragmentItemClickAdapter();
        fragment_common_recyclerview_recycler.setAdapter(shopByVideoFragmentItemClickAdapter);
        toolbar_back_navigation_btn.setOnClickListener(this);
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler=(RecyclerView)view.findViewById(R.id.fragment_common_recyclerview_recycler);
        toolbar_back_navigation_btn=(ImageButton)view.findViewById(R.id.toolbar_back_navigation_btn);
        card_shopby_video_recycler_item_onclick_playVideo=(VideoView)view.findViewById(R.id.card_shopby_video_recycler_item_onclick_playVideo);
    }

    private void playLocalVideo() {
        //Creating MediaController
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(card_shopby_video_recycler_item_onclick_playVideo);
        //specify the location of media file
        //Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.demovideo);
        //Setting MediaController and URI, then starting the videoView
        card_shopby_video_recycler_item_onclick_playVideo.setMediaController(mediaController);
        //card_shopby_video_recycler_item_onclick_playVideo.setVideoURI(uri);
        card_shopby_video_recycler_item_onclick_playVideo.requestFocus();
        card_shopby_video_recycler_item_onclick_playVideo.start();
    }

    @Override
    public void onClick(View view) {
        fragmentManager.popBackStackImmediate();
    }
}
