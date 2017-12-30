package com.flikster.IntroSlider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.flikster.PreferenceActivity.PreferencesView;
import com.flikster.R;
import com.flikster.SharedPref.SharedPref;

public class IntroSlider extends AppCompatActivity implements SharedPrefMethods, View.OnClickListener, DotsCreationAndButtonClick, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private ImageView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private SharedPref sharedPref;
    IntroSliderPresenter introSliderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSharedPred();
        introSliderPresenter = new IntroSliderPresenter(this, this, sharedPref);
        checkForFirstTimeLaunch();
        setContentView(R.layout.into_slider);
        initializeViews();
        initializeButton();
        settingAdapter();
        createDots();
    }

    private void createDots() {
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        dotsLayout.setVisibility(View.GONE);
        dots = new ImageView[layouts.length];
        introSliderPresenter.dotsCreation(dotsLayout);

    }

    private void settingAdapter() {
        myViewPagerAdapter = new MyViewPagerAdapter(layouts, this);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setOnPageChangeListener(this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int state) {
                Toast.makeText(getApplicationContext(), "ScrollState" + state, Toast.LENGTH_SHORT).show();
                if (state == 2) {
                    launchHomeScreen();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initializeViews() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        layouts = new int[]{
                R.layout.slidetwoimage,
                R.layout.slidethreeimage,
                R.layout.slidefourimage,};
    }

    private void initializeButton() {
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnSkip.setVisibility(View.GONE);
        btnNext.setVisibility(View.GONE);
        btnNext.setOnClickListener(this);
        btnSkip.setOnClickListener(this);
    }

    private void checkForFirstTimeLaunch() {
        introSliderPresenter.checkForFirstTimeLaunch();
    }

    private void initSharedPred() {
        sharedPref = new SharedPref(getApplicationContext());
    }

    @Override
    public void launchHomeScreen() {
        sharedPref.setFirstTimeLaunch(false);
        startActivity(new Intent(IntroSlider.this, PreferencesView.class));
        finish();
    }

    @Override
    public void makeNotificationBarTrans() {
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_next) {
            int current = viewPager.getCurrentItem() + 1;
            Log.e("currentItem", current + "");
            if (current < layouts.length) {
                viewPager.setCurrentItem(current);
            } else {
                launchHomeScreen();
            }
            if (current == 4) {
                launchHomeScreen();
            }
        }
        if (view.getId() == R.id.btn_skip) {
            launchHomeScreen();
        }

    }

    @Override
    public void removeViews() {
        dotsLayout.removeAllViews();
    }

    @Override
    public void createViews(int currentPos) {
        if (dotsLayout != null)
            removeViews();
        for (int i = 0; i < layouts.length; i++) {
            dots[i] = new ImageView(this);
            if (i == currentPos) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dots_selected));
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dots_unselected));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            dotsLayout.addView(dots[i], params);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Toast.makeText(getApplicationContext(), "ScrollState" + state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageSelected(int position) {
        dots[position].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dots_selected));
        for (int i = 0; i < 3; i++) {
            if (i == position)
                continue;
            dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dots_unselected));
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }
}
