package com.flikster;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by abhishek on 05-10-2017.
 */

public class GallaryFullScreen extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_gallary_fullscreen);
        imageView=(ImageView)findViewById(R.id.gallary_fullscreen_img);
    }
}

