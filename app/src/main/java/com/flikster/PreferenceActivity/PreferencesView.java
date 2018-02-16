package com.flikster.PreferenceActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.flikster.Authentication.AuthenticationActivity;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;
import com.flikster.SharedPref.SharedPref;
import com.flikster.Util.SharedPrefsUtil;

/**
 * author Bheema 18-10-2017
 */
public class PreferencesView extends AppCompatActivity implements View.OnClickListener {

    private Button btnPreferencesNext, btnSignup;
    private TextView tvMenFashon, tvWomenFashon;/*skip_preference_txt*/;
    RadioButton radioTollywood, radioBollywood, radioTamil, radioKannada, radioMalayalam;
    private SharedPref sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSharedPred();
        if (!sharedPref.isFirstTimeLaunch2())
            launchHomeScreen();
        setContentView(R.layout.activity_preferences);
        initializeView();
        initializeRest();
    }

    private void initSharedPred() {
        sharedPref = new SharedPref(getApplicationContext());
    }

    private void initializeRest() {
        radioBollywood.setOnClickListener(this);
        radioTollywood.setOnClickListener(this);
        radioTamil.setOnClickListener(this);
        radioKannada.setOnClickListener(this);
        radioMalayalam.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        btnPreferencesNext.setOnClickListener(this);
        tvMenFashon.setOnClickListener(this);
        tvWomenFashon.setOnClickListener(this);
        //skip_preference_txt.setOnClickListener(this);
    }

    private void initializeView() {
        btnPreferencesNext = (Button) findViewById(R.id.preference_next_btn);
        btnSignup = (Button) findViewById(R.id.goto_signup);
        radioBollywood = (RadioButton) findViewById(R.id.ind_bollywood);
        radioTollywood = (RadioButton) findViewById(R.id.ind_tollywood);
        radioKannada = (RadioButton) findViewById(R.id.ind_kannada);
        radioMalayalam = (RadioButton) findViewById(R.id.ind_malayalam);
        radioTamil = (RadioButton) findViewById(R.id.ind_tamil);
        tvMenFashon = (TextView) findViewById(R.id.tv_menFashion);
        tvWomenFashon = (TextView) findViewById(R.id.tv_womenFashion);
        //skip_preference_txt = (TextView) findViewById(R.id.skip_preference_txt);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.preference_next_btn) {
            nextButtonClick();
        } else if (view.getId() == R.id.goto_signup) {
            nextButtonClick();
        } else if (view.getId() == R.id.ind_bollywood) {
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "INDUSTRY_TYPE", "Bollywood");
            radioTollywood.setChecked(false);
            radioKannada.setChecked(false);
            radioMalayalam.setChecked(false);
            radioTamil.setChecked(false);
        } else if (view.getId() == R.id.ind_tollywood) {
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "INDUSTRY_TYPE", "Tollywood");
            radioBollywood.setChecked(false);
            radioKannada.setChecked(false);
            radioMalayalam.setChecked(false);
            radioTamil.setChecked(false);
        } else if (view.getId() == R.id.ind_kannada) {
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "INDUSTRY_TYPE", "Mollywood");
            radioTollywood.setChecked(false);
            radioBollywood.setChecked(false);
            radioMalayalam.setChecked(false);
            radioTamil.setChecked(false);
        } else if (view.getId() == R.id.ind_tamil) {
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "INDUSTRY_TYPE", "Kollywood");
            radioTollywood.setChecked(false);
            radioKannada.setChecked(false);
            radioMalayalam.setChecked(false);
            radioBollywood.setChecked(false);
        } else if (view.getId() == R.id.ind_malayalam) {
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "INDUSTRY_TYPE", "Sandalwood");
            //Sandalwood
            radioTollywood.setChecked(false);
            radioKannada.setChecked(false);
            radioBollywood.setChecked(false);
            radioTamil.setChecked(false);
        } else if (view.getId() == R.id.tv_menFashion) {
//            tvMenFashon.setAlpha((float) 0.5);
//            tvWomenFashon.setAlpha((float) 1.0);
            tvMenFashon.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            tvMenFashon.setTextColor(getResources().getColor(R.color.white));
            tvWomenFashon.setBackgroundColor(getResources().getColor(R.color.transparent));
            tvWomenFashon.setBackgroundDrawable(getResources().getDrawable(R.drawable.gray_rectange_border));
            tvWomenFashon.setTextColor(getResources().getColor(R.color.grey_txt_label));
        } else if (view.getId() == R.id.tv_womenFashion) {
//            tvMenFashon.setAlpha((float) 1.0);
//            tvWomenFashon.setAlpha((float) 0.5);
            tvWomenFashon.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            tvWomenFashon.setTextColor(getResources().getColor(R.color.white));
            tvMenFashon.setBackgroundColor(getResources().getColor(R.color.transparent));
            tvMenFashon.setBackgroundDrawable(getResources().getDrawable(R.drawable.gray_rectange_border));
            tvMenFashon.setTextColor(getResources().getColor(R.color.grey_txt_label));
        } /*else if (view.getId() == R.id.skip_preference_txt) {

        }*/

    }

    private void goToSignUp() {
        SharedPrefsUtil.setStringPreference(getApplicationContext(), "COMING_PAGE", "SIGNUP");
        sharedPref.setFirstTimeLaunch2(false);
        startActivity(new Intent(PreferencesView.this, AuthenticationActivity.class));
        finish();
    }

    private void launchHomeScreen() {
        sharedPref.setFirstTimeLaunch2(false);
        startActivity(new Intent(PreferencesView.this, HomeActivity.class));
        finish();
    }

    private void nextButtonClick() {
        if (SharedPrefsUtil.getStringPreference(getApplicationContext(), "INDUSTRY_TYPE") != null
                && !SharedPrefsUtil.getStringPreference(getApplicationContext(),
                "INDUSTRY_TYPE").isEmpty()) {
            lunchHomeScreen();
        } else {
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "INDUSTRY_TYPE", "Bollywood");
            lunchHomeScreen();
        }

    }

    private void lunchHomeScreen() {
        sharedPref.setFirstTimeLaunch2(false);
        SharedPrefsUtil.setStringPreference(getApplicationContext(), "COMING_PAGE", "SIGNUP");
        SharedPrefsUtil.setStringPreference(PreferencesView.this, "IS_LOGGED_IN", "NOT_LOGGED_IN");
        startActivity(new Intent(PreferencesView.this, AuthenticationActivity.class));
        finish();
    }

    /*@Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if(compoundButton.getId()==R.id.ind_bollywood)
        {
            radioTollywood.setChecked(false);
            radioKannada.setChecked(false);
            radioMalayalam.setChecked(false);
            radioTamil.setChecked(false);
        }

        else if(compoundButton.getId()==R.id.ind_kannada)
        {
            radioTollywood.setChecked(false);
            radioBollywood.setChecked(false);
            radioMalayalam.setChecked(false);
            radioTamil.setChecked(false);
        }
        else if(compoundButton.getId()==R.id.ind_tollywood)
        {
            radioBollywood.setChecked(false);
            radioKannada.setChecked(false);
            radioMalayalam.setChecked(false);
            radioTamil.setChecked(false);
        }
        else if(compoundButton.getId()==R.id.ind_tamil)
        {
            radioTollywood.setChecked(false);
            radioKannada.setChecked(false);
            radioMalayalam.setChecked(false);
            radioBollywood.setChecked(false);
        }
        else if(compoundButton.getId()==R.id.ind_malayalam)
        {
            radioTollywood.setChecked(false);
            radioKannada.setChecked(false);
            radioBollywood.setChecked(false);
            radioTamil.setChecked(false);
        }

    } */
}
