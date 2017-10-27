package com.flikster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Logins on 26-10-2017.
 */

public class VideoPlayerActivity extends AppCompatActivity implements View.OnClickListener {
    View view;
    VideoView playVideo;
    Button closebtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videopage);
        initializeViews();
        playLocalVideo();
        closebtn.setOnClickListener(this);
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
        closebtn = (Button) findViewById(R.id.closebtn);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.closebtn) {
//            getFragmentManager().popBackStackImmediate();
            Intent in = new Intent(VideoPlayerActivity.this, VideoGalleryActivity.class);
            startActivity(in);
        }
    }
}