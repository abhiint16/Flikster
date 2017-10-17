package com.flikster;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity implements  FragmentChangeInterface, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    LinearLayout feed,rating,plus,fashion,store;
    FragmentManager fragmentManager;
    ImageButton menu_notification;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
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
        store.setOnClickListener(this);
        rating.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        toolbar.setWillNotCacheDrawing(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        menu_notification.setOnClickListener(this);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void initializeViews() {
        feed=(LinearLayout)findViewById(R.id.feed_button);
        fashion=(LinearLayout)findViewById(R.id.fashion_button);
        rating=(LinearLayout)findViewById(R.id.rating_button);
        store=(LinearLayout)findViewById(R.id.store_button);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menu_notification=(ImageButton)toolbar.findViewById(R.id.toolbar_notification_icon);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.drawer_navview);
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
        else if(viewId==R.id.menu_search)
        {
            beginTransact(new SearchFragment());
        }
        else if(viewId==R.id.store_button)
        {
            beginTransact(new StoreFragment());
        }
        else if(viewId==R.id.rating_button)
        {
            beginTransact(new RatingFragment());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        MenuItem menuItem=menu.findItem(R.id.menu_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnClickListener(this);
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
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_my_account:

                break;
            case R.id.menu_feedback:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                String[] s = {"abhishekint16@gmail.com"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, s);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Suggestion | Flikster");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                emailIntent.setType("message/rfc822");//message/rfc822   text/plain
                startActivity(Intent.createChooser(emailIntent, "Send EMAIL using...."));
                break;
        }
        return false;
    }
}
