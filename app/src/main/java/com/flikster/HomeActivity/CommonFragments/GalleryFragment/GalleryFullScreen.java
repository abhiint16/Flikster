package com.flikster.HomeActivity.CommonFragments.GalleryFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;

/**
 * Created by abhishek on 05-10-2017.
 */

public class GalleryFullScreen extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    ScrollView scrollView;
    VideoView videoView;
    Button closebtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_videopage);
        initializeViews();
        initializeRest();
    }

    private void initializeRest() {
        videoView.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
        Glide.with(this).load(getIntent().getStringExtra("galleryimglink")).into(imageView);
        closebtn.setOnClickListener(this);
    }

    private void initializeViews() {
        videoView = (VideoView) findViewById(R.id.playVideo);
        scrollView = (ScrollView) findViewById(R.id.gallary_fullscreen_scrollimg);
        imageView = (ImageView) findViewById(R.id.gallary_fullscreen_img);
        closebtn = (Button) findViewById(R.id.closebtn);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.closebtn) {
            Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("GallaryFullscreen", "GallaryFullscreen");
            startActivity(intent);
        }
    }
}

