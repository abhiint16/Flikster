package com.flikster;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

/**
 * Created by abhishek on 05-10-2017.
 */

public class GallaryFullScreen extends AppCompatActivity implements View.OnClickListener {
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
        imageView.setImageResource(R.drawable.pooja);
        closebtn.setOnClickListener(this);
    }

    private void initializeViews() {
        videoView=(VideoView)findViewById(R.id.playVideo);
        scrollView=(ScrollView)findViewById(R.id.gallary_fullscreen_scrollimg);
        imageView=(ImageView)findViewById(R.id.gallary_fullscreen_img);
        closebtn=(Button)findViewById(R.id.closebtn);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.closebtn)
        {
            Intent intent=new Intent(this,HomeActivity.class);
            intent.putExtra("GallaryFullscreen","GallaryFullscreen");
            startActivity(intent);
        }
    }
}

