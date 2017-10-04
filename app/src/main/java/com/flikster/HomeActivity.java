package com.flikster;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity implements  FragmentChangeInterface, View.OnClickListener {

    LinearLayout feed,rating,plus,fashion,store;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeViews();
        initializeRest();
    }

    private void initializeRest() {
        feed.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
    }

    private void initializeViews() {
        feed=(LinearLayout)findViewById(R.id.feed_button);
    }

    @Override
    public void beginTransact(Fragment fragment) {
        fragmentManager.beginTransaction()
                .add(R.id.main_container, fragment)
                .addToBackStack("")
                .commit();
    }

    @Override
    public void onClick(View view) {
        callBeginTrasact(view.getId());
    }

    private void callBeginTrasact(int viewId)
    {
        if(viewId==R.id.feed_button)
        {
            beginTransact();
        }

    }

}
