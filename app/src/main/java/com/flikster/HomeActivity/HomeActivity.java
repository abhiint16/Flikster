package com.flikster.HomeActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.flikster.HomeActivity.FashionFragment.FashionFragment;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.MenuFragments.FliksterCreditFragment;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GallaryCardClick;
import com.flikster.MenuFragments.LogoutFragment;
import com.flikster.HomeActivity.CommonFragments.MyAccountFragment.MyAccountFragment;
import com.flikster.HomeActivity.CommonFragments.NotificationFragment.NotificationFragment;
import com.flikster.MenuFragments.OrdersFragment;
import com.flikster.R;
import com.flikster.HomeActivity.RatingFragment.RatingFragment;
import com.flikster.MenuFragments.ReferFragment;
import com.flikster.MenuFragments.RewardsFragment;
import com.flikster.MenuFragments.SavedPostsFragment;
import com.flikster.MenuFragments.SearchFragment;
import com.flikster.MenuFragments.SettingsFragment;
import com.flikster.HomeActivity.StoreFragment.StoreFragment;
import com.flikster.HomeActivity.CommonFragments.VideoFragment.VideoGalleryFragment;
import com.flikster.MenuFragments.WishListFragment;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements FragmentChangeInterface, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    LinearLayout feed, rating, plus, fashion, store;
    FragmentManager fragmentManager;
    ImageButton menu_notification;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    Context mContext;
    FloatingActionButton camera_fab;

    ///Image Capture
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";

    //Bottom Navigation
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_home);
        initializeViews();
        initializeRest();
        checkForLaunch();
        bottomnavigationBar();
    }

    private void bottomnavigationBar() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.feed, R.drawable.home, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.rating, R.drawable.rating, R.color.color_tab_1);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("", R.drawable.addsquare, R.color.color_tab_1);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.fashion, R.drawable.usericon, R.color.color_tab_1);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.store, R.drawable.feed_icon, R.color.color_tab_1);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);

        bottomNavigation.addItems(bottomNavigationItems);

// Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));

// Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

// Enable the translation of the FloatingActionButton
//        bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);

// Change colors
        bottomNavigation.setAccentColor(R.color.black);
        bottomNavigation.setInactiveColor(R.color.colorPrimary);

// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

// Display color under navigation bar (API 21+)
// Don't forget these lines in your style-v21
// <item name="android:windowTranslucentNavigation">true</item>
// <item name="android:fitsSystemWindows">true</item>
        bottomNavigation.setTranslucentNavigationEnabled(true);


// Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(false);

// Set current item programmatically
//        bottomNavigation.setCurrentItem(0);

// Customize notification (title, background, typeface)
//        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

// Add or remove notification for each item
//        bottomNavigation.setNotification("1", 3);
// OR
        /*AHNotification notification = new AHNotification.Builder()
                .setText("1")
                .setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.dark_grey))
                .setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.colorRectangleBorderGrey))
                .build();
        bottomNavigation.setNotification(notification, 1);*/

// Enable / disable item & set disable color
//        bottomNavigation.enableItemAtPosition(0);
//        bottomNavigation.disableItemAtPosition(0);
        bottomNavigation.setItemDisableColor(Color.parseColor("#949494"));

// Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(final int position, boolean wasSelected) {
//                Toast.makeText(getApplicationContext(), )
                // Do something cool here...

                if (position == 0) {
                    beginTransact(new FeedFragment());
                } else if (position == 1) {
                    beginTransact(new RatingFragment());
                } else if (position == 2) {
                    //cameraAccessPermission();
                } else if (position == 3) {
                    beginTransact(new FashionFragment());
                } else if (position == 4) {
                    beginTransact(new StoreFragment());
                } /*else if (position == 5) {
                    beginTransact(new RatingFragment());
                }*/

                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
    }

    private void checkForLaunch() {
        if ("MyBag".equals(getIntent().getStringExtra("MyBag"))) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new FashionFragment())
                    .commit();
        } else if ("VideoPlayer".equals(getIntent().getStringExtra("VideoPlayer"))) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new VideoGalleryFragment())
                    .commit();
        } else if ("GallaryFullscreen".equals(getIntent().getStringExtra("GallaryFullscreen"))) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new GallaryCardClick())
                    .commit();
        } else
            firstTimeLaunch(new FeedFragment());

    }

    private void firstTimeLaunch(Fragment fragment) {
        fragmentManager.beginTransaction()
                .add(R.id.main_container, fragment)
                .commit();
    }

    private void initializeRest() {
        mContext = HomeActivity.this;
        feed.setOnClickListener(this);
        fashion.setOnClickListener(this);
        store.setOnClickListener(this);
        rating.setOnClickListener(this);
        plus.setOnClickListener(this);
        camera_fab.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        toolbar.setWillNotCacheDrawing(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        menu_notification.setOnClickListener(this);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        /*actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);*/
    }

    private void initializeViews() {
        feed = (LinearLayout) findViewById(R.id.feed_button);
        fashion = (LinearLayout) findViewById(R.id.fashion_button);
        rating = (LinearLayout) findViewById(R.id.rating_button);
        store = (LinearLayout) findViewById(R.id.store_button);
        plus = (LinearLayout) findViewById(R.id.plus_button);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        camera_fab=(FloatingActionButton)findViewById(R.id.camera_fab);
        setSupportActionBar(toolbar);
        menu_notification = (ImageButton) toolbar.findViewById(R.id.toolbar_notification_icon);
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

    private void callBeginTrasact(int viewId) {
        if (viewId == R.id.feed_button) {
            beginTransact(new FeedFragment());
        } else if (viewId == R.id.fashion_button) {
            beginTransact(new FashionFragment());
        } else if (viewId == R.id.toolbar_notification_icon) {
            beginTransact(new NotificationFragment());
        } else if (viewId == R.id.menu_search) {
            beginTransact(new SearchFragment());
        } else if (viewId == R.id.store_button) {
            beginTransact(new StoreFragment());
        } else if (viewId == R.id.rating_button) {
            beginTransact(new RatingFragment());
        } else if (viewId == R.id.plus_button) {
//            cameraAccessPermission();
        }
        else if(viewId==R.id.camera_fab)
        {
            openCameraClickDialog();
        }
    }

    private void openCameraClickDialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_camera_click);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        LinearLayout dialog_camera_click_click_photo=(LinearLayout) dialog.findViewById(R.id.dialog_camera_click_click_photo);
        LinearLayout dialog_camera_click_select_gallery=(LinearLayout)dialog.findViewById(R.id.dialog_camera_click_select_gallery);
        dialog_camera_click_select_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog_camera_click_click_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraAccessPermission();
                dialog.dismiss();
            }
        });
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.translucent)));
        dialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnClickListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_profile: {
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else if (!drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_my_account:
                beginTransact(new MyAccountFragment());
                break;
            case R.id.menu_orders:
                beginTransact(new OrdersFragment());
                break;
            case R.id.menu_credits:
                beginTransact(new FliksterCreditFragment());
                break;
            case R.id.menu_logout:
                beginTransact(new LogoutFragment());
                break;
            case R.id.menu_refer:
                beginTransact(new ReferFragment());
                break;
            case R.id.menu_saved_posts:
                beginTransact(new SavedPostsFragment());
                break;
            case R.id.menu_setting:
                beginTransact(new SettingsFragment());
                break;
            case R.id.menu_wish_list:
                beginTransact(new WishListFragment());
                break;
            case R.id.menu_rewards:
                beginTransact(new RewardsFragment());
                break;
        }
        return false;
    }


    //Camera request
    private void cameraAccessPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (getFromPref(this, ALLOW_KEY)) {
                showSettingsAlert();
            } else if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA)) {
                    showAlert();
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }
        } else {
            openCamera();
        }
    }

    public static void saveToPreferences(Context context, String key,
                                         Boolean allowed) {
        SharedPreferences myPrefs = context.getSharedPreferences
                (CAMERA_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
    }

    public static Boolean getFromPref(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences
                (CAMERA_PREF, Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }

    private void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Flikster needs to access the Camera.");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
//                        finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(HomeActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);

                    }
                });
        alertDialog.show();
    }

    private void showSettingsAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startInstalledAppDetailsActivity(HomeActivity.this);

                    }
                });
        alertDialog.show();
    }


    @Override
    public void onRequestPermissionsResult
            (int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale =
                                ActivityCompat.shouldShowRequestPermissionRationale
                                        (this, permission);
                        if (showRationale) {
                            showAlert();
                        } else if (!showRationale) {
                            // user denied flagging NEVER ASK AGAIN
                            // you can either enable some fall back,
                            // disable features of your app
                            // or open another dialog explaining
                            // again the permission and directing to
                            // the app setting
                            saveToPreferences(HomeActivity.this, ALLOW_KEY, true);
                        }
                    }
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request

        }
    }

    public static void startInstalledAppDetailsActivity(final Activity context) {
        if (context == null) {
            return;
        }
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }

    private void openCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }


}
