package com.flikster;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity implements  FragmentChangeInterface, View.OnClickListener {

    LinearLayout feed,rating,plus,fashion,store;
    FragmentManager fragmentManager;
    ImageButton menu_notification;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_home);
        initializeViews();
        initializeRest();
        firstTimeLaunch(new FeedFragment());
    }

    private void firstTimeLaunch(Fragment fragment) {
        fragmentManager.beginTransaction()
                .add(R.id.main_container, fragment)
                .commit();
    }

    private void initializeRest() {
        feed.setOnClickListener(this);
        fashion.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        toolbar.setWillNotCacheDrawing(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        menu_notification.setOnClickListener(this);

    }

    private void initializeViews() {
        feed=(LinearLayout)findViewById(R.id.feed_button);
        fashion=(LinearLayout)findViewById(R.id.fashion_button);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menu_notification=(ImageButton)toolbar.findViewById(R.id.toolbar_notification_icon);
    }

    @Override
    public void beginTransact(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
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
            beginTransact(new FeedFragment());
        }
        else if(viewId==R.id.fashion_button)
        {
            beginTransact(new FashionFragment());
        }
        else if(viewId==R.id.toolbar_notification_icon)
        {
            beginTransact(new NotificationFragment());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_profile :
            {
                beginTransact(new ProfileFragment());
                break;
            }
            case R.id.menu_search :
            {
                beginTransact(new ProfileFragment());
                break;
            }
        }
        return true;
    }

}
