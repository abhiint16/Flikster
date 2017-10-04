package com.flikster;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity implements  FragmentChangeInterface, View.OnClickListener {

    LinearLayout feed,rating,plus,fashion,store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeViews();
        initializeRest();
    }

    private void initializeRest() {
        feed.setOnClickListener(this);
    }

    private void initializeViews() {
        feed=(LinearLayout)findViewById(R.id.feed_button);
    }

    @Override
    public void beginTransaction1(Fragment fragment) {

    }

    @Override
    public void onClick(View view) {
        
    }
}
