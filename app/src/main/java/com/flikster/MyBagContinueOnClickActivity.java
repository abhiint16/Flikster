package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by abhishek on 24-10-2017.
 */

public class MyBagContinueOnClickActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton activity_mybag_continue_onclick_toolbar_back_navigation_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybag_continue_onclick);
        addressFragmentLaunch();
        initializeView();
        initializeRest();
    }

    private void initializeRest() {
        activity_mybag_continue_onclick_toolbar_back_navigation_btn.setOnClickListener(this);
    }

    private void initializeView() {
        activity_mybag_continue_onclick_toolbar_back_navigation_btn = (ImageButton) findViewById(R.id.activity_mybag_continue_onclick_toolbar_back_navigation_btn);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activity_mybag_continue_onclick_toolbar_back_navigation_btn) {
            getFragmentManager().popBackStackImmediate();
        }
    }

    private void addressFragmentLaunch() {
    }
}
