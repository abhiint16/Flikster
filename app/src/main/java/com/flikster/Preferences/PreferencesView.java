package com.flikster.Preferences;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.Authentication.AuthenticationActivity;
import com.flikster.HomeActivity;
import com.flikster.R;

/**
 * author Bheema 18-10-2017
 */
public class PreferencesView extends AppCompatActivity implements View.OnClickListener{

    private Button btnPreferencesNext, btnSignup;
    private TextView tvMenFashon, tvWomenFashon;
    RadioButton radioTollywood, radioBollywood, radioTamil, radioKannada, radioMalayalam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        initializeView();
    }

    private void initializeView() {
        btnPreferencesNext = (Button) findViewById(R.id.goto_home);
        btnSignup=(Button) findViewById(R.id.goto_signup);
        radioBollywood=(RadioButton)findViewById(R.id.ind_bollywood);
        radioTollywood=(RadioButton)findViewById(R.id.ind_tollywood);
        radioKannada=(RadioButton)findViewById(R.id.ind_kannada);
        radioMalayalam=(RadioButton)findViewById(R.id.ind_malayalam);
        radioTamil=(RadioButton)findViewById(R.id.ind_tamil);

        radioBollywood.setOnClickListener(this);
        radioTollywood.setOnClickListener(this);
        radioTamil.setOnClickListener(this);
        radioKannada.setOnClickListener(this);
        radioMalayalam.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        btnPreferencesNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.goto_home)
            launchHomeScreen();
        else if(view.getId()== R.id.goto_signup)
            goToSignUp();
        else if(view.getId()== R.id.ind_bollywood){
            radioTollywood.setChecked(false);
            radioKannada.setChecked(false);
            radioMalayalam.setChecked(false);
            radioTamil.setChecked(false);
        }
        else if(view.getId()== R.id.ind_tollywood){
            radioBollywood.setChecked(false);
            radioKannada.setChecked(false);
            radioMalayalam.setChecked(false);
            radioTamil.setChecked(false);
        }
        else if(view.getId()== R.id.ind_kannada){
            radioTollywood.setChecked(false);
            radioBollywood.setChecked(false);
            radioMalayalam.setChecked(false);
            radioTamil.setChecked(false);
        }
        else if(view.getId()== R.id.ind_tamil){
            radioTollywood.setChecked(false);
            radioKannada.setChecked(false);
            radioMalayalam.setChecked(false);
            radioBollywood.setChecked(false);
        }
        else if(view.getId()== R.id.ind_malayalam){
            radioTollywood.setChecked(false);
            radioKannada.setChecked(false);
            radioBollywood.setChecked(false);
            radioTamil.setChecked(false);
        }

    }

    private void goToSignUp() {
        startActivity(new Intent(PreferencesView.this, AuthenticationActivity.class));
    }

    private void launchHomeScreen() {
        startActivity(new Intent(PreferencesView.this, HomeActivity.class));
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