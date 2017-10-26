package com.flikster;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Logins on 26-10-2017.
 */

public class VideoGalleryActivity extends AppCompatActivity implements View.OnClickListener {
    View view;
    VideoView playVideo;
    Button activity_my_bag_toolbar_back_navigation_btn;
    Context mContext;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CelebrityBioAdapterImagesViewHolder myCeleAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_gallery);
        mContext = VideoGalleryActivity.this;
        initializeViews();
        playLocalVideo();
        initializeRest();
        activity_my_bag_toolbar_back_navigation_btn.setOnClickListener(this);
    }

    private void playLocalVideo() {
        //Creating MediaController
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(playVideo);
        //specify the location of media file
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.demovideo);
        //Setting MediaController and URI, then starting the videoView
        playVideo.setMediaController(mediaController);
        playVideo.setVideoURI(uri);
        playVideo.requestFocus();
        playVideo.start();
    }


    private void initializeViews() {
        playVideo = (VideoView) findViewById(R.id.playVideo);
        recyclerView = (RecyclerView) findViewById(R.id.gallery_videos_recycler_view);
        activity_my_bag_toolbar_back_navigation_btn = (Button) findViewById(R.id.activity_my_bag_toolbar_back_navigation_btn);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activity_my_bag_toolbar_back_navigation_btn) {
            getFragmentManager().popBackStackImmediate();
        }
    }

    private void initializeRest() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        myCeleAdapter = new CelebrityBioAdapterImagesViewHolder();
        recyclerView.setAdapter(myCeleAdapter);
        activity_my_bag_toolbar_back_navigation_btn.setOnClickListener(this);
    }
}
