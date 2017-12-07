package com.flikster.HomeActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
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
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.flikster.AllCommentActivity.AllCommentActivity;
import com.flikster.BuildConfig;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionFeedFragment;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragment;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.MyStyleFragment;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.HomeActivity.FashionFragment.FashionFragment;
import com.flikster.HomeActivity.FashionFragment.FashionLandingFragment.FashionLandingFragment;
import com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreFirstTypeFragment;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.SongByMovieFragmentItemClick;
import com.flikster.HomeActivity.WatchFragment.WatchFragment;
import com.flikster.MenuFragments.FliksterCreditFragment;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryCardClick;
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
import com.flikster.SharedPref.SharedPref;
import com.flikster.Util.Common;
import com.flikster.Util.DateUtil;
import com.flikster.Util.SharedPrefsUtil;
import com.flikster.permission.DangerousPermResponseCallBack;
import com.flikster.permission.DangerousPermissionResponse;
import com.flikster.permission.DangerousPermissionUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class HomeActivity extends AppCompatActivity implements FragmentChangeInterface, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, FeedFragment.Testing,WatchFragment.WatchFragCommInterface
        ,MovieSongsListFragment.WatchPlayAudioOrVideoInterafce ,MusicGridFragment.WatchAudioVideoSendFromGridFrag,
        NewsOnClickFragment.NewsRecommendedClick,VideoGalleryFragment.VideoRecommendationClick,GalleryCardClick.GalleryRecommendationItemClick
        ,CelebStoreFirstTypeFragment.ShopByVideoInterafce{

    LinearLayout feed, rating, plus, fashion, store;
    FragmentManager fragmentManager;
    ImageButton toolbar_main_notification;
    Toolbar toolbar_main;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    Context mContext;
    FloatingActionButton camera_fab;

    ///Image Capture
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";
    ///Image Capture
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    private File mCapturedImageFile;
    private Bitmap capturedImage = null;
    private static int RESULT_LOAD_IMAGE = 1;
    ArrayList<String> capturelist = new ArrayList<String>();
    private static int CAMERA_REQUES_CODE = 101;
    String captured_img_str;
    boolean cameracaptured = false;
//    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    //Bottom Navigation
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private AHBottomNavigation bottomNavigation;

    static int TAKE_PICTURE = 1;
    final int ACTIVITY_SELECT_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_home);
        initializeViews();
        initializeRest();
        checkForLaunch();
        bottomnavigationBar();

        Log.e("Currentdate", DateUtil.currentDate());
        Log.e("MinutesToHr", String.format("%.2f", DateUtil.mintsTohoursCovert("165")));
        Log.e("Movietime", DateUtil.mintsToCoverthoursMin("125"));
    }

    private void bottomnavigationBar() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.feed, R.drawable.home, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.watch, R.drawable.watch, R.color.color_tab_1);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("", R.drawable.addsquare, R.color.color_tab_1);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.fashion, R.drawable.fashionicon, R.color.color_tab_1);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.store, R.drawable.storeicon, R.color.color_tab_1);

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
//                    beginTransact(new RatingFragment());
                    beginTransact(new WatchFragment());
                    //
                } else if (position == 2) {
                    //cameraAccessPermission();
                } else if (position == 3) {
//                    beginTransact(new FashionFragment());
                    beginTransact(new FashionLandingFragment());
                } else if (position == 4) {
                    beginTransact(new MyStyleFragment());
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
                    .replace(R.id.main_container, new FashionLandingFragment())
                    .commit();
        } else if ("VideoPlayer".equals(getIntent().getStringExtra("VideoPlayer"))) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new VideoGalleryFragment())
                    .commit();
        } else if ("GallaryFullscreen".equals(getIntent().getStringExtra("GallaryFullscreen"))) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new GalleryCardClick())
                    .commit();
        } else
            firstTimeLaunch(new FeedFragment());

    }

    private void firstTimeLaunch(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
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
        toolbar_main.setWillNotCacheDrawing(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_main_notification.setOnClickListener(this);
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
        toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
        camera_fab = (FloatingActionButton) findViewById(R.id.camera_fab);
        setSupportActionBar(toolbar_main);
        toolbar_main_notification = (ImageButton) toolbar_main.findViewById(R.id.toolbar_main_notification);
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
            beginTransact(new FashionLandingFragment());
        } else if (viewId == R.id.toolbar_main_notification) {
            beginTransact(new NotificationFragment());
        } else if (viewId == R.id.menu_search) {
            beginTransact(new SearchFragment());
        } else if (viewId == R.id.store_button) {
            beginTransact(new StoreFragment());
        } else if (viewId == R.id.rating_button) {
            beginTransact(new RatingFragment());
        } else if (viewId == R.id.plus_button) {
//            cameraAccessPermission();
        } else if (viewId == R.id.camera_fab) {
            openCameraClickDialog();
        }
    }

    private void openCameraClickDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_camera_click);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        LinearLayout dialog_camera_click_click_photo = (LinearLayout) dialog.findViewById(R.id.dialog_camera_click_click_photo);
        LinearLayout dialog_camera_click_select_gallery = (LinearLayout) dialog.findViewById(R.id.dialog_camera_click_select_gallery);
        dialog_camera_click_select_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent();
                intent.setType("image*//*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivity(intent);*/

                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);

                dialog.dismiss();
            }
        });
        dialog_camera_click_click_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraAccessPermission(CAMERA_REQUES_CODE);
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
            case R.id.menu_rating:
                beginTransact(new RatingFragment());
                break;
            //menu_auction
            case R.id.menu_auction:
                beginTransact(new AuctionFeedFragment());
                break;


        }
        return false;
    }

    private void cameraAccessPermission(int requestCode) {
        DangerousPermissionUtils.getPermission(mContext, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, requestCode)
                .enqueue(new DangerousPermResponseCallBack() {
                    @Override
                    public void onComplete(final DangerousPermissionResponse permissionResponse) {
                        if (permissionResponse.isGranted()) {
                            if (permissionResponse.getRequestCode() == CAMERA_REQUES_CODE) {
                                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat
                                        .checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                cameracaptured = true;
                                openCamera();
                            }
                        }
                    }
                });
    }

    public static void saveToPreferences(Context context, String key,
                                         Boolean allowed) {
        SharedPreferences myPrefs = context.getSharedPreferences
                (CAMERA_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
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
                            saveToPreferences(HomeActivity.this, ALLOW_KEY, true);
                        }
                    }
                }
            }
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
        SharedPrefsUtil.setStringPreference(getApplicationContext(), "ACCESS_FRAGMENT_CAPTURE", "DISENBLE");
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, TAKE_PICTURE);
    }


    @Override
    public void test(String name, Fragment fragment, int getClass) {
        if (getClass == 1) {
            MovieFragment movieFragment = (MovieFragment) fragment;
            movieFragment.updateInfo(name);
            Log.e("inside home movie", "indies home movie" + name);
            firstTimeLaunch(fragment);
        } else if (getClass == 2) {
            CelebrityFragment celebrityFragment = (CelebrityFragment) fragment;
            celebrityFragment.updateInfo(name);
            Log.e("inside home celeb", "indie home celbe");
            firstTimeLaunch(fragment);
        }
    }

    @Override
    public void galleryCardOnClick(List<String> galleryImgLinks, String name, String profilePic, String type,
                                   String title, Fragment fragment) {
        GalleryCardClick galleryCardClick = (GalleryCardClick) fragment;
        galleryCardClick.updateImage(galleryImgLinks, name, profilePic, type, title);
        firstTimeLaunch(fragment);
    }

    @Override
    public void newsCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle, String description, Fragment fragment, String contentType) {
        NewsOnClickFragment gallaryCardClick = (NewsOnClickFragment) fragment;
        gallaryCardClick.updateImage(profilePic, title, type, bannerImg, headertitle, description, contentType);
        firstTimeLaunch(fragment);
    }

    @Override
    public void videoCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle, String description, String videolink, Fragment fragment, String contentType) {
        VideoGalleryFragment videoGalleryFragment = (VideoGalleryFragment) fragment;
        videoGalleryFragment.updateImage(profilePic, title, type, bannerImg, headertitle, description, contentType, videolink);
        firstTimeLaunch(fragment);
    }

    @Override
    public void seeMoreComments(String userName,String userId,String entityId) {
        Intent intent=new Intent(HomeActivity.this, AllCommentActivity.class);
        intent.putExtra("entityId",entityId);
        intent.putExtra("userName",userName);
        intent.putExtra("userId",userId);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        String enable = SharedPrefsUtil.getStringPreference(getApplicationContext(), "ACCESS_FRAGMENT_CAPTURE");
        if (!enable.equals("ENABLE")) {
            if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Captured successfully.", Toast.LENGTH_SHORT).show();
                Bitmap photoBitmap = (Bitmap) intent.getExtras().get("data");
//            String captureStr = Common.BitMapToString(photoBitmap);
                SharedPrefsUtil.setStringPreference(getApplicationContext(), "ImageString", Common.BitMapToString(photoBitmap));
            } else if (requestCode == ACTIVITY_SELECT_IMAGE) {
                //Toast.makeText(getApplicationContext(), "Gallery Image Picked successfully.", Toast.LENGTH_SHORT).show();
                Uri selectedImage = intent.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                SharedPrefsUtil.setStringPreference(getApplicationContext(), "ImageString", Common.BitMapToString(thumbnail));
//            Drawable drawable = new BitmapDrawable(thumbnail);
//            backGroundImageLinearLayout.setBackgroundDrawable(drawable);
            } else {
                Toast.makeText(getApplicationContext(), "User cancelled image capture.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void switchContent(int id, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(id, fragment, fragment.toString());
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void carouselContainerClick(String toolbarTitle, List<String> img, List<String> title, List<String> audioVideoLink,Fragment fragment) {
        MusicGridFragment musicGridFragment=(MusicGridFragment)fragment;
        musicGridFragment.getAllData(toolbarTitle,img,title,audioVideoLink);
        firstTimeLaunch(fragment);
    }

    @Override
    public void playAudioOrVideoPage(String audioLink, Fragment fragment,String audioImg,String type) {
        SongByMovieFragmentItemClick songByMovieFragmentItemClick=(SongByMovieFragmentItemClick)fragment;
        songByMovieFragmentItemClick.getAudioLink(audioLink,audioImg,type);
        firstTimeLaunch(fragment);
    }

    @Override
    public void carouselItemClick(String toolbarTitle, String img, String title, String audio,String type, Fragment fragment) {
        MovieSongsListFragment movieSongsListFragment=(MovieSongsListFragment)fragment;
        movieSongsListFragment.getAllData(toolbarTitle,img,title,audio,type);
        firstTimeLaunch(fragment);
    }

    @Override
    public void sendAudioVideoLink(String toolbarTitle, String img, String title, String audioVideoLink, Fragment fragment) {
        SongByMovieFragmentItemClick songByMovieFragmentItemClick=(SongByMovieFragmentItemClick)fragment;
        songByMovieFragmentItemClick.getAudioLink(audioVideoLink,img,"video");
        firstTimeLaunch(fragment);
    }

    @Override
    public void newsRecommendedClickMethod(String profilePic, String title, String type, String bannerImg, String headertitle, String description, Fragment fragment, String contentType) {
        NewsOnClickFragment gallaryCardClick = (NewsOnClickFragment) fragment;
        gallaryCardClick.updateImage(profilePic, title, type, bannerImg, headertitle, description, contentType);
        firstTimeLaunch(fragment);
    }

    @Override
    public void videoRecommendationClickMethod(String profilePic, String title, String type, String bannerImg, String headertitle, String description, String videolink, Fragment fragment, String contentType) {
        VideoGalleryFragment videoGalleryFragment = (VideoGalleryFragment) fragment;
        videoGalleryFragment.updateImage(profilePic, title, type, bannerImg, headertitle, description, contentType, videolink);
        firstTimeLaunch(fragment);
    }

    @Override
    public void galleryRecommendationItemClickMethod(List<String> galleryImgLinks, String name, String profilePic, String type, String title, Fragment fragment) {
        GalleryCardClick galleryCardClick = (GalleryCardClick) fragment;
        galleryCardClick.updateImage(galleryImgLinks, name, profilePic, type, title);
        firstTimeLaunch(fragment);
    }

    @Override
    public void playShopByVideoMethod(String audioLink, Fragment fragment, String audioImg, String type, List<ShopByVideoData.ShopByVideoInnerData.ShopByVideoInnerInnerData.ShopByVideoInnerMostData.ShopByVideoAllProduct> listOfProducts) {
        SongByMovieFragmentItemClick songByMovieFragmentItemClick=(SongByMovieFragmentItemClick)fragment;
        songByMovieFragmentItemClick.getShopByVideo(audioLink,audioImg,type,listOfProducts);
        firstTimeLaunch(fragment);
    }
}
