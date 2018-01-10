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
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.flikster.AllCommentActivity.AllCommentActivity;
import com.flikster.Authentication.AuthenticationActivity;
import com.flikster.BuildConfig;
import com.flikster.CheckoutActivity.CheckoutFragment.InstamojoData;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionFeedFragment;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragmentBio;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragment;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragmentInfo;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.CustomStyleTypes.MyStyleFragmentOne;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.MyStyleFragment;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductOnClick;
import com.flikster.HomeActivity.FashionFragment.FashionFragment;
import com.flikster.HomeActivity.FashionFragment.FashionLandingFragment.FashionLandingFragment;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreFragment;
import com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreFirstTypeFragment;
import com.flikster.HomeActivity.FashionFragment.FashionType.CommonAllProductPage.CommonAllProductPage;
import com.flikster.HomeActivity.FashionFragment.FashionType.MenFashionFragment.MenFashionFirstTypeFragment;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.SearchViewFragment.SearchViewFragment;
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
import com.flikster.MyBagActivity.MyBagActivity;
import com.flikster.PreferenceActivity.PreferencesView;
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
import com.google.android.gms.vision.text.Line;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeActivity extends AppCompatActivity implements FragmentChangeInterface, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, FeedFragment.Testing, WatchFragment.WatchFragCommInterface
        , MovieSongsListFragment.WatchPlayAudioOrVideoInterafce, MusicGridFragment.WatchAudioVideoSendFromGridFrag,
        NewsOnClickFragment.NewsRecommendedClick, VideoGalleryFragment.VideoRecommendationClick, GalleryCardClick.GalleryRecommendationItemClick
        , CelebStoreFirstTypeFragment.ShopByVideoInterafce, MenFashionFirstTypeFragment.ShopByVideoMenInterafce,
        AllStoreFragment.AllStoreInterafce, CommonAllProductPage.CommonAllProductPageBuyClick,
        CelebrityFragmentBio.CelebToShopByVideoInterface, MovieFragmentInfo.MovieToShopByVideoInterface,
        CelebrityFragment.CelebItemClickInterface, MovieFragment.MovieItemClickInterface,SearchViewFragment.SearchViewToFrag {
    LinearLayout feed, rating, plus, fashion, store,toolbar_flikter_text_container;
    FragmentManager fragmentManager;
    ApiInterface apiInterface;
    LinearLayout toolbar_cart_btn,toolbar_main_notification,toolbar_navigation_view_open_btn;
    SearchView toolbar_search_btn;
    Toolbar toolbar_main;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    Context mContext;
    FloatingActionButton camera_fab;
    SharedPref sharedPref;
    GlobalSearchGetData globalSearchGetData;
    Spinner toolbar_pref_spinner;
    Button right_navigation_bar_non_loggedin_login_btn, right_navigation_bar_non_loggedin_create_account_btn;
    ScrollView right_navigation_bar_logged_in_container, right_navigation_bar_non_logged_in_container;
    TextView right_navigation_bar_my_account, right_navigation_bar_orders, right_navigation_bar_wishlist,
            right_navigation_bar_liked_posts, right_navigation_bar_refer, right_navigation_bar_rewards,
            right_navigation_bar_logout, right_navigation_bar_non_loggedin_aboutflikster,
            right_navigation_bar_non_loggedin_careers, right_navigation_bar_non_loggedin_contact,
            right_navigation_bar_non_loggedin_help, right_navigation_bar_non_loggedin_privacy,
            right_navigation_bar_non_loggedin_return_policy, right_navigation_bar_non_loggedin_terms, right_navigation_bar_non_loggedin_auction;

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

    Bundle bundle = new Bundle();
    List<String> pref = new ArrayList<>();
    boolean industrySpinnerSelection = false;
    TextView toolbar_main_title;
    TextView header_drawer_layout_username;

    String UserId = "";
    String Username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_home);
        initializeViews();
        setPrefIfNull();
        setNavigationBar();
        initializeRest();
        checkForLaunch();
        bottomnavigationBar();
//        gettimeinfo();
//        instamojoProductionUrlAccess();

    }

    private void setPrefIfNull() {
        String data = SharedPrefsUtil.getStringPreference(HomeActivity.this, "IS_LOGGED_IN");
        if (data == null) {
            Log.e("inside homesetpref", "inside homesetpref");
            SharedPrefsUtil.setStringPreference(HomeActivity.this, "IS_LOGGED_IN", "NOT_LOGGED_IN");
        }
    }

    private void setNavigationBar() {
       /* if (SharedPrefsUtil.getStringPreference(HomeActivity.this, "IS_LOGGED_IN").equals("LOGGED_IN")) {
            right_navigation_bar_logged_in_container.setVisibility(View.VISIBLE);
            right_navigation_bar_non_logged_in_container.setVisibility(View.GONE);
        } else if (SharedPrefsUtil.getStringPreference(HomeActivity.this, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            right_navigation_bar_logged_in_container.setVisibility(View.GONE);
            right_navigation_bar_non_logged_in_container.setVisibility(View.VISIBLE);
        }*/
        UserId = SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_ID");
        Username = SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_NAME");
        if (UserId != null && !UserId.isEmpty()) {
            right_navigation_bar_logged_in_container.setVisibility(View.VISIBLE);
            right_navigation_bar_non_logged_in_container.setVisibility(View.GONE);
            if (Username != null && !Username.isEmpty()) {
                header_drawer_layout_username.setText(Username + "");
            } else {

            }

        } else {
            right_navigation_bar_logged_in_container.setVisibility(View.GONE);
            right_navigation_bar_non_logged_in_container.setVisibility(View.VISIBLE);
        }
    }

    private void bottomnavigationBar() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.feed, R.drawable.homeunselectedbottom, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.watch, R.drawable.watchunselected, R.color.color_tab_1);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("", R.drawable.addsquare, R.color.color_tab_1);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.fashion, R.drawable.fashionunselectedbottom, R.color.color_tab_1);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("My Account", R.drawable.account_icon, R.color.color_tab_1);

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
                    beginTransact(new MyAccountFragment());
                    /*beginTransact(new MyStyleFragment());*/
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
        } else if ("SEARCH_ITEM_CLICK".equals(getIntent().getStringExtra("SEARCH_ITEM_CLICK"))) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new MyStyleFragment())
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
        right_navigation_bar_my_account.setOnClickListener(this);
        right_navigation_bar_orders.setOnClickListener(this);
        right_navigation_bar_wishlist.setOnClickListener(this);
        right_navigation_bar_liked_posts.setOnClickListener(this);
        right_navigation_bar_refer.setOnClickListener(this);
        right_navigation_bar_rewards.setOnClickListener(this);
        right_navigation_bar_logout.setOnClickListener(this);
        right_navigation_bar_non_loggedin_aboutflikster.setOnClickListener(this);
        right_navigation_bar_non_loggedin_careers.setOnClickListener(this);
        right_navigation_bar_non_loggedin_contact.setOnClickListener(this);
        right_navigation_bar_non_loggedin_help.setOnClickListener(this);
        right_navigation_bar_non_loggedin_privacy.setOnClickListener(this);
        right_navigation_bar_non_loggedin_return_policy.setOnClickListener(this);
        right_navigation_bar_non_loggedin_terms.setOnClickListener(this);
        right_navigation_bar_non_loggedin_login_btn.setOnClickListener(this);
        right_navigation_bar_non_loggedin_auction.setOnClickListener(this);
        toolbar_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MyBagActivity.class);
                if (SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_ID") != null && !SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_ID").isEmpty()) {
                    UserId = SharedPrefsUtil.getStringPreference(getApplicationContext(),
                            "USER_ID");
                    Log.e("LoginUserId", UserId);
                    intent.putExtra("userId", "abhiint");
                } else {
                    intent.putExtra("userId", "abhiint");
                }
                startActivity(intent);
            }
        });
        right_navigation_bar_non_loggedin_create_account_btn.setOnClickListener(this);
        toolbar_search_btn.setQueryHint("type min 3 characters...");
        toolbar_search_btn.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar_flikter_text_container.setVisibility(View.GONE);
                searchViewFragmentLaunch(new SearchViewFragment(),globalSearchGetData);
            }
        });
        toolbar_search_btn.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length()>=3)
                {
                    getSearchedQueryData(newText);
                }
                return false;
            }
        });
        toolbar_search_btn.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                toolbar_flikter_text_container.setVisibility(View.VISIBLE);
                toolbar_search_btn.setIconifiedByDefault(true);
                firstTimeLaunch(new FeedFragment());
                return false;
            }
        });
        toolbar_navigation_view_open_btn.setOnClickListener(this);
        toolbarPrefSpinner();

        toolbar_main_title.setOnClickListener(this);
        toolbar_pref_spinner.setVisibility(View.GONE);

        try {
            if (getIntent().getStringArrayExtra("GAMIL_ID").toString() != null && !getIntent().getStringArrayExtra("GAMIL_ID").toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), getIntent().getStringArrayExtra("GAMIL_ID").toString()
                        + "Success", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }


        /*actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);*/


    }

    private void getSearchedQueryData(String newText) {
        GlobalSearchPostData globalSearchPostData = new GlobalSearchPostData(newText);
        apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/search-ms/").create(ApiInterface.class);
        Call<GlobalSearchGetData> call = apiInterface.getGlobalSearchData(globalSearchPostData);
        call.enqueue(new Callback<GlobalSearchGetData>() {
            @Override
            public void onResponse(Call<GlobalSearchGetData> call, Response<GlobalSearchGetData> response) {
                Log.e("inside onResopbke",""+response.body().getCeleb().size());
                globalSearchGetData=response.body();
                searchViewFragmentLaunch(new SearchViewFragment(),globalSearchGetData);
            }

            @Override
            public void onFailure(Call<GlobalSearchGetData> call, Throwable t) {
            }
        });
    }

    private void industrySelectionrefreshActivity(String industrytype) {
        SharedPrefsUtil.setStringPreference(getApplicationContext(), "INDUSTRY_TYPE", industrytype);
        Intent i = new Intent(HomeActivity.this, HomeActivity.class);
        startActivity(i);
//        finish();
    }

    private void toolbarPrefSpinner() {
        pref = new ArrayList<>();
        pref.add("Bollywood");
        pref.add("Tollywood");
        pref.add("Kollywood");
        pref.add("Mollywood");
        pref.add("Sandalwood");
        ArrayAdapter<String> prefArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, pref);
        prefArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        toolbar_pref_spinner.setAdapter(prefArrayAdapter);

        try {
            String industryavailble = SharedPrefsUtil.getStringPreference(getApplicationContext(), "INDUSTRY_TYPE");
            if (industryavailble != null && !industryavailble.isEmpty()) {
                if (industryavailble.equals("Bollywood")) {
//                    toolbar_pref_spinner.setSelection(0);
                } else if (industryavailble.equals("Tollywood")) {
//                    toolbar_pref_spinner.setSelection(1);
                } else if (industryavailble.equals("Kollywood")) {
//                    toolbar_pref_spinner.setSelection(2);
                } else if (industryavailble.equals("Mollywood")) {
//                    toolbar_pref_spinner.setSelection(3);
                } else if (industryavailble.equals("Sandalwood")) {
//                    toolbar_pref_spinner.setSelection(4);
                }
            }
        } catch (Exception e) {

        }


        toolbar_pref_spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                industrySpinnerSelection = true;
                return false;
            }
        });

        toolbar_pref_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    if (industrySpinnerSelection) {
//                    toolbar_pref_spinner.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), pref.get(position), Toast.LENGTH_LONG).show();
                        if (pref.get(position).equals("Bollywood")) {
                            industrySelectionrefreshActivity("Bollywood");
                        } else if (pref.get(position).equals("Tollywood")) {
                            industrySelectionrefreshActivity("Tollywood");
                        } else if (pref.get(position).equals("Kollywood")) {
                            industrySelectionrefreshActivity("Kollywood");
                        } else if (pref.get(position).equals("Mollywood")) {
                            industrySelectionrefreshActivity("Mollywood");
                        } else if (pref.get(position).equals("Sandalwood")) {
                            industrySelectionrefreshActivity("Sandalwood");
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initializeViews() {
        sharedPref = new SharedPref(getApplicationContext());
        feed = (LinearLayout) findViewById(R.id.feed_button);
        fashion = (LinearLayout) findViewById(R.id.fashion_button);
        rating = (LinearLayout) findViewById(R.id.rating_button);
        store = (LinearLayout) findViewById(R.id.store_button);
        plus = (LinearLayout) findViewById(R.id.plus_button);
        toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar_cart_btn=(LinearLayout)findViewById(R.id.toolbar_cart_btn);
        toolbar_main_title = (TextView) findViewById(R.id.toolbar_main_title);
        toolbar_search_btn=(SearchView)findViewById(R.id.toolbar_search_btn);
        toolbar_navigation_view_open_btn=(LinearLayout)findViewById(R.id.toolbar_navigation_view_open_btn);
        toolbar_pref_spinner = (Spinner) findViewById(R.id.toolbar_pref_spinner);
        camera_fab = (FloatingActionButton) findViewById(R.id.camera_fab);
        header_drawer_layout_username = (TextView) findViewById(R.id.header_drawer_layout_username);
        right_navigation_bar_logged_in_container = (ScrollView) findViewById(R.id.right_navigation_bar_logged_in_container);
        right_navigation_bar_non_logged_in_container = (ScrollView) findViewById(R.id.right_navigation_bar_non_logged_in_container);
        right_navigation_bar_my_account = (TextView) findViewById(R.id.right_navigation_bar_my_account);
        right_navigation_bar_orders = (TextView) findViewById(R.id.right_navigation_bar_orders);
        right_navigation_bar_wishlist = (TextView) findViewById(R.id.right_navigation_bar_wish_list);
        right_navigation_bar_liked_posts = (TextView) findViewById(R.id.right_navigation_bar_liked_posts);
        right_navigation_bar_refer = (TextView) findViewById(R.id.right_navigation_bar_refer);
        right_navigation_bar_rewards = (TextView) findViewById(R.id.right_navigation_bar_rewards);
        right_navigation_bar_logout = (TextView) findViewById(R.id.right_navigation_bar_logout);
        toolbar_flikter_text_container=(LinearLayout)findViewById(R.id.toolbar_flikter_text_container);
        right_navigation_bar_non_loggedin_aboutflikster = (TextView) findViewById(R.id.right_navigation_bar_non_loggedin_aboutflikster);
        right_navigation_bar_non_loggedin_careers = (TextView) findViewById(R.id.right_navigation_bar_non_loggedin_careers);
        right_navigation_bar_non_loggedin_contact = (TextView) findViewById(R.id.right_navigation_bar_non_loggedin_contact);
        right_navigation_bar_non_loggedin_help = (TextView) findViewById(R.id.right_navigation_bar_non_loggedin_help);
        right_navigation_bar_non_loggedin_privacy = (TextView) findViewById(R.id.right_navigation_bar_non_loggedin_privacy);
        right_navigation_bar_non_loggedin_return_policy = (TextView) findViewById(R.id.right_navigation_bar_non_loggedin_return_policy);
        right_navigation_bar_non_loggedin_terms = (TextView) findViewById(R.id.right_navigation_bar_non_loggedin_terms);
        right_navigation_bar_non_loggedin_login_btn = (Button) findViewById(R.id.right_navigation_bar_non_loggedin_login_btn);
        right_navigation_bar_non_loggedin_auction = (TextView) findViewById(R.id.right_navigation_bar_non_loggedin_auction);
        right_navigation_bar_non_loggedin_create_account_btn = (Button) findViewById(R.id.right_navigation_bar_non_loggedin_create_account_btn);
        setSupportActionBar(toolbar_main);
        toolbar_main_notification = (LinearLayout) toolbar_main.findViewById(R.id.toolbar_main_notification);
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
            // openCameraClickDialog();
            beginTransact(new MyStyleFragment());
        } else if (viewId == R.id.right_navigation_bar_my_account) {
            beginTransact(new MyAccountFragment());
        } else if (viewId == R.id.right_navigation_bar_orders) {
            navigationMenuitemsAction("Flikster", "Orders");
        } else if (viewId == R.id.right_navigation_bar_wish_list) {
            navigationMenuitemsAction("Flikster", "Wishlist");
        } else if (viewId == R.id.right_navigation_bar_liked_posts) {
            navigationMenuitemsAction("Flikster", "Liked Posts");
        } else if (viewId == R.id.right_navigation_bar_refer) {
            navigationMenuitemsAction("Flikster", "Refer");
        } else if (viewId == R.id.right_navigation_bar_rewards) {
            navigationMenuitemsAction("Flikster", "Rewards");
        } else if (viewId == R.id.right_navigation_bar_logout) {
            SharedPrefsUtil.setStringPreference(HomeActivity.this,
                    "USER_ID", null);
            SharedPrefsUtil.getBooleanPreference(getApplicationContext(), "FACEBOOK_LOGIN", true);
            if (SharedPrefsUtil.getBooleanPreference(getApplicationContext(), "FACEBOOK_LOGIN", true)) {
                fbLogout(getApplicationContext());
            }
            SharedPrefsUtil.setStringPreference(HomeActivity.this, "IS_LOGGED_IN", "NOT_LOGGED_IN");
            Intent intent = new Intent(this, AuthenticationActivity.class);
            startActivity(intent);
        } else if (viewId == R.id.right_navigation_bar_non_loggedin_aboutflikster) {
            navigationMenuitemsAction("Flikster", "About");
        } else if (viewId == R.id.right_navigation_bar_non_loggedin_careers) {
            navigationMenuitemsAction("Flikster", "Careers");
        } else if (viewId == R.id.right_navigation_bar_non_loggedin_contact) {
            navigationMenuitemsAction("Flikster", "Contact");
        } else if (viewId == R.id.right_navigation_bar_non_loggedin_help) {
            navigationMenuitemsAction("Flikster", "Help");
        } else if (viewId == R.id.right_navigation_bar_non_loggedin_privacy) {
            navigationMenuitemsAction("Flikster", "Privacy");
        } else if (viewId == R.id.right_navigation_bar_non_loggedin_return_policy) {
            navigationMenuitemsAction("Flikster", "Return Policy");
        } else if (viewId == R.id.right_navigation_bar_non_loggedin_terms) {
            navigationMenuitemsAction("Flikster", "Terms");
        } else if (viewId == R.id.right_navigation_bar_non_loggedin_login_btn) {
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "COMING_PAGE", "LOGIN");
            Intent intent = new Intent(this, AuthenticationActivity.class);
            startActivity(intent);
        } else if (viewId == R.id.right_navigation_bar_non_loggedin_auction) {
//            SharedPrefsUtil.setStringPreference(getApplicationContext(), "COMING_PAGE", "LOGIN");
            Toast.makeText(getApplicationContext(), "Auction Click", Toast.LENGTH_SHORT).show();
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
            beginTransact(new AuctionFeedFragment());
        } else if (viewId == R.id.right_navigation_bar_non_loggedin_create_account_btn) {
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "COMING_PAGE", "SIGNUP");
            Intent intent = new Intent(this, AuthenticationActivity.class);
            startActivity(intent);
        } else if (viewId == R.id.toolbar_main_title) {
            Toast.makeText(getApplicationContext(), "Flikster", Toast.LENGTH_SHORT).show();
            toolbar_pref_spinner.setVisibility(View.VISIBLE);
            toolbar_pref_spinner.setEnabled(false);
            toolbar_pref_spinner.performClick();
            industrySpinnerSelection = true;
//            toolbar_pref_spinner.setSelection(0, false);
        }
        else if (viewId==R.id.toolbar_navigation_view_open_btn)
        {
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            } else if (!drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        }
    }

    private void navigationMenuitemsAction(String contentddata, String headerStr) {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        }
        Common.openCommonDialog(HomeActivity.this, contentddata, headerStr);
    }

   /* private void openCameraClickDialog() {
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
               *//* Intent intent = new Intent();
                intent.setType("image*//**//*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivity(intent);*//*

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
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnClickListener(this);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()) {
            case R.id.menu_profile: {
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else if (!drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
            case R.id.menu_search: {
                firstTimeLaunch(new WatchFragment());
            }
        }*/
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
            /*case R.id.menu_credits:
                beginTransact(new FliksterCreditFragment());
                break;*/
            case R.id.menu_logout:
                beginTransact(new LogoutFragment());
                break;
            case R.id.menu_refer:
                beginTransact(new ReferFragment());
                break;
            case R.id.menu_saved_posts:
                beginTransact(new SavedPostsFragment());
                break;
            /*case R.id.menu_setting:
                beginTransact(new SettingsFragment());
                break;*/
            case R.id.menu_wish_list:
                beginTransact(new WishListFragment());
                break;
            case R.id.menu_rewards:
                beginTransact(new RewardsFragment());
                break;
            /*case R.id.menu_rating:
                beginTransact(new RatingFragment());
                break;
            //menu_auction
            case R.id.menu_auction:
                beginTransact(new AuctionFeedFragment());
                break;*/


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
    public void test(String name, Fragment fragment, int getClass, String userId, String entityId) {
        if (getClass == 1) {
            MovieFragment movieFragment = (MovieFragment) fragment;
            movieFragment.updateInfo(name, userId, entityId);
            Log.e("inside home movie", "indies home movie" + name);
            firstTimeLaunch(fragment);
        } else if (getClass == 2) {
            CelebrityFragment celebrityFragment = (CelebrityFragment) fragment;
            celebrityFragment.updateInfo(name, userId, entityId);
            Log.e("inside home celeb", "indie home celbe");
            firstTimeLaunch(fragment);
        }
    }

    @Override
    public void galleryCardOnClick(List<String> galleryImgLinks, String name, String profilePic, String type,
                                   String title, Fragment fragment, String userId, String entityId) {
        GalleryCardClick galleryCardClick = (GalleryCardClick) fragment;
        galleryCardClick.updateImage(galleryImgLinks, name, profilePic, type, title, userId, entityId);
        firstTimeLaunch(fragment);
    }

    @Override
    public void newsCardOnClick(String profilePic, String title, String type, String bannerImg,
                                String headertitle, String description, Fragment fragment,
                                String contentType, String userId, String entityId) {
        NewsOnClickFragment gallaryCardClick = (NewsOnClickFragment) fragment;
        gallaryCardClick.updateImage(profilePic, title, type, bannerImg, headertitle, description, contentType, userId, entityId);
        firstTimeLaunch(fragment);
    }


    @Override
    public void videoCardOnClick(String profilePic, String title, String type, String bannerImg,
                                 String headertitle,
                                 String description, String videolink, Fragment fragment,
                                 String contentType, String userId, String entityId) {
        VideoGalleryFragment videoGalleryFragment = (VideoGalleryFragment) fragment;
        videoGalleryFragment.updateImage(profilePic, title, type, bannerImg, headertitle, description, contentType, videolink, userId, entityId);
        firstTimeLaunch(fragment);
    }

    @Override
    public void seeMoreComments(String userName, String userId, String entityId) {
        Intent intent = new Intent(HomeActivity.this, AllCommentActivity.class);
        intent.putExtra("entityId", entityId);
        intent.putExtra("userName", userName);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    /*@Override
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

    }*/

    public void switchContent(int id, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(id, fragment, fragment.toString());
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void carouselContainerClick(String toolbarTitle,
                                       String url, Fragment fragment) {
        MusicGridFragment musicGridFragment = (MusicGridFragment) fragment;
        musicGridFragment.getAllData(toolbarTitle,url);
        firstTimeLaunch(fragment);
    }

    @Override
    public void playAudioOrVideoPage(String audioLink, Fragment fragment, String audioImg, String type) {
        SongByMovieFragmentItemClick songByMovieFragmentItemClick = (SongByMovieFragmentItemClick) fragment;
        songByMovieFragmentItemClick.getAudioLink(audioLink, audioImg, type);
        firstTimeLaunch(fragment);
    }

    @Override
    public void carouselItemClick(String toolbarTitle, String img, String title,
                                  String audio, String type, Fragment fragment) {
        MovieSongsListFragment movieSongsListFragment = (MovieSongsListFragment) fragment;
        movieSongsListFragment.getAllData(toolbarTitle, img, title, audio, type);
        firstTimeLaunch(fragment);
    }

    @Override
    public void carouselItemToMovie(String slug, Fragment fragment) {
        MovieFragment movieFragment = (MovieFragment) fragment;
        movieFragment.updateInfo(slug, "", "");
        firstTimeLaunch(fragment);
    }

    @Override
    public void carouselItemToGallery(List<String> galleryImgLinks, String name,
                                      String profilePic, String type, String title, Fragment fragment) {
        GalleryCardClick galleryCardClick = (GalleryCardClick) fragment;
        galleryCardClick.updateImage(galleryImgLinks, name, profilePic, type, title, "", "");
        firstTimeLaunch(fragment);
    }


    @Override
    public void sendAudioVideoLink(String toolbarTitle, String img, String title, String audioVideoLink, Fragment fragment) {
        SongByMovieFragmentItemClick songByMovieFragmentItemClick = (SongByMovieFragmentItemClick) fragment;
        songByMovieFragmentItemClick.getAudioLink(audioVideoLink, img, "video");
        firstTimeLaunch(fragment);
    }


    @Override
    public void videoRecommendationClickMethod(String profilePic, String title, String type, String bannerImg, String headertitle, String description, String videolink,
                                               Fragment fragment, String contentType, String userId, String entityId) {
        VideoGalleryFragment videoGalleryFragment = (VideoGalleryFragment) fragment;
        videoGalleryFragment.updateImage(profilePic, title, type, bannerImg, headertitle, description, contentType, videolink, userId, entityId);
        firstTimeLaunch(fragment);
    }

    @Override
    public void galleryRecommendationItemClickMethod(List<String> galleryImgLinks,
                                                     String name, String profilePic, String type,
                                                     String title, Fragment fragment, String userId, String entityId) {
        GalleryCardClick galleryCardClick = (GalleryCardClick) fragment;
        galleryCardClick.updateImage(galleryImgLinks, name, profilePic, type, title, userId, entityId);
        firstTimeLaunch(fragment);
    }

    @Override
    public void playShopByVideoMethod(String audioLink, Fragment fragment, String audioImg, String type, List<ShopByVideoData.ShopByVideoInnerData.ShopByVideoInnerInnerData.ShopByVideoInnerMostData.ShopByVideoAllProduct> listOfProducts) {
        SongByMovieFragmentItemClick songByMovieFragmentItemClick = (SongByMovieFragmentItemClick) fragment;
        songByMovieFragmentItemClick.getShopByVideo(audioLink, audioImg, type, listOfProducts);
        firstTimeLaunch(fragment);
    }

    @Override
    public void playShopByVideoMenMethod(String audioLink, Fragment fragment, String audioImg, String type, List<ShopByVideoData.ShopByVideoInnerData.ShopByVideoInnerInnerData.ShopByVideoInnerMostData.ShopByVideoAllProduct> listOfProducts) {
        SongByMovieFragmentItemClick songByMovieFragmentItemClick = (SongByMovieFragmentItemClick) fragment;
        songByMovieFragmentItemClick.getShopByVideo(audioLink, audioImg, type, listOfProducts);
        firstTimeLaunch(fragment);
    }

    @Override
    public void onBuyClick(String productId, List<String> size, String userId, String price, String profilePic, String productTitle, String productSlug, List<String> imageGallery, Fragment fragment) {
        ProductOnClick productOnClick = (ProductOnClick) fragment;
        productOnClick.getProductData(productId, size, userId, price, profilePic, productTitle, productSlug, imageGallery);
        firstTimeLaunch(fragment);
    }

    @Override
    public void onGalleryContainerClick(String productId, List<String> size, String userId, String price, String profilePic, String productTitle, String productSlug, List<String> imageGallery, String profilepic, List<String> role, String name, String title, Fragment fragment) {
        CommonAllProductPage commonAllProductPage = (CommonAllProductPage) fragment;
        commonAllProductPage.getProductData(productId, size, userId, price, profilePic, productTitle, productSlug, imageGallery,
                profilePic, role, name, title);
        firstTimeLaunch(fragment);
    }

    @Override
    public void fromCelebToCelebPage(String name, Fragment fragment, String userId, String entityId) {
        CelebrityFragment celebrityFragment = (CelebrityFragment) fragment;
        celebrityFragment.updateInfo(name, userId, entityId);
        firstTimeLaunch(fragment);
    }

    public void searchViewFragmentLaunch(Fragment fragment,GlobalSearchGetData globalSearchGetData)
    {
        SearchViewFragment searchViewFragment=(SearchViewFragment)fragment;
        searchViewFragment.getSearchQueryData(globalSearchGetData);
        firstTimeLaunch(fragment);
    }


    @Override
    public void toCelebPage(String name, Fragment fragment, String userId, String entityId) {
        CelebrityFragment celebrityFragment = (CelebrityFragment) fragment;
        celebrityFragment.updateInfo(name, userId, entityId);
        firstTimeLaunch(fragment);
    }

    @Override
    public void newsRecommendedClickMethod(String profilePic, String title,
                                           String type, String bannerImg, String headertitle,
                                           String description, Fragment fragment, String contentType,
                                           String useriId, String entityId) {
        NewsOnClickFragment gallaryCardClick = (NewsOnClickFragment) fragment;
        gallaryCardClick.updateImage(profilePic, title, type, bannerImg, headertitle,
                description, contentType, useriId, entityId);
        firstTimeLaunch(fragment);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    public static void fbLogout(Context context) {
        FacebookSdk.sdkInitialize(context);
        LoginManager.getInstance().logOut();


    }

    public void gettimeinfo() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
            try {
                date = dateFormat.parse("2017-12-29T11:12:41.899Z");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"); //If you need time just put specific format for time like 'HH:mm:ss'
            String dateStr = formatter.format(date);

//            Toast.makeText(getApplicationContext(), "Days " + dateStr, Toast.LENGTH_SHORT).show();

            String dateserver = DateUtil.serverSentDateChange("2017-12-29T11:12:41.899Z");
            Toast.makeText(getApplicationContext(), "Server Date" + dateserver, Toast.LENGTH_SHORT).show();

            //serverSentTimeChange
            String changesTime = DateUtil.serverSentTimeChange("2017-12-29T11:12:41.899Z");

            Log.e("chnagestime", changesTime);
//            Log.e("currentDateTime", currentDateTime);
            Toast.makeText(getApplicationContext(), "Server Time" + changesTime, Toast.LENGTH_SHORT).show();


            SimpleDateFormat simDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String currentDateTime = DateUtil.currentdateandTime();
            Date currentDateTimedate = simDf.parse(currentDateTime);
            long currentmillSec = currentDateTimedate.getTime();

            Date yourDate = simDf.parse(changesTime);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(yourDate);
            int month = calendar.get(Calendar.MONTH) + 1;
            String changeFormatDate = calendar.get(Calendar.DATE)
                    + "-" + month
                    + "-" + calendar.get(Calendar.YEAR);
            Log.e("changedate", changeFormatDate + "");

            Log.e("ChangeTimeCon", calendar.get(Calendar.HOUR) + calendar.get(Calendar.MINUTE) + calendar.get(Calendar.SECOND) + "");


            long timeInMilliseconds = yourDate.getTime();

            long differenceres = currentmillSec - timeInMilliseconds;
            Log.e("time in millisecond", "" + timeInMilliseconds);
            Log.e("currentmillSec", "" + currentmillSec);
            Log.e("differenceres", "" + differenceres);

            String exactTimeDiffe = DateUtil.getTimeAgo(timeInMilliseconds);
            Log.e("exactTimeDiffe", exactTimeDiffe + "");
            Toast.makeText(getApplicationContext(), "time left" + exactTimeDiffe, Toast.LENGTH_SHORT).show();
            /*int dateDifference = (int) DateUtil.getDateDiff(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"),
                    "2017-12-29T11:12:41.899Z", "2017-12-30T11:12:41.899Z");
            System.out.println("dateDifference: " + dateDifference);
            Toast.makeText(getApplicationContext(), "dateDifference" + dateDifference, Toast.LENGTH_SHORT).show();*/

            DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG).format(date);

        } catch (Exception e) {

        }
    }


    private void instamojoProductionUrlAccess() {
        InstamojoData senddata = new InstamojoData("client_credentials",
                "TIIVMSyhXS6OTc4SQbXRdqeyZZIfTs3FTe5q0ITF",
                "lhE3pt2ZDxYqH8OGwa4l1KkRixwSihSpavLKowVux3hJRli7QUYH0MJm86gCWjM0YmwDdenRLQlQRt9Nsn3tdUegQAxAQdx2CZKVr8Rq8aMyKN5IAVFPAUYCrRIfDr2w");
        apiInterface = ApiClient.getClient("http://api.instamojo.com/").create(ApiInterface.class);
        Call<InstamojoData> call = apiInterface.instamojoDataCall(senddata);
        call.enqueue(new Callback<InstamojoData>() {
            @Override
            public void onResponse(Call<InstamojoData> call, Response<InstamojoData> response) {
//                Log.e("StatusCode:", response.body().getStatusCode() + "");
                if (response.body().getAccess_token() != null) {
                    Toast.makeText(getApplicationContext(),
                            response.body().getAccess_token(),
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "failed to get token",
                            Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<InstamojoData> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }


}
