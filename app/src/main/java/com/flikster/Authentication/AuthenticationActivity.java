package com.flikster.Authentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.R;

public class AuthenticationActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLoginPhone, btnLoginEmail;
    private Button btnLoginGoogle, btnLoginFacebook;
    private TextView tvLoginTermsCond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        initializeView();
    }

    private void initializeView() {
        btnLoginPhone = (Button)findViewById(R.id.btn_login_phone);
        btnLoginEmail = (Button)findViewById(R.id.btn_login_mail);
        btnLoginGoogle = (Button)findViewById(R.id.btn_login_google);
        btnLoginFacebook = (Button)findViewById(R.id.btn_login_facebook);
        tvLoginTermsCond = (TextView)findViewById(R.id.tv_login_terms);

        btnLoginPhone.setOnClickListener(this);
        btnLoginEmail.setOnClickListener(this);
        btnLoginGoogle.setOnClickListener(this);
        btnLoginFacebook.setOnClickListener(this);
        tvLoginTermsCond.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login_phone)
            gotoPhoneLogin();
        else if (view.getId() == R.id.btn_login_mail)
            gotoEmailLogin();
        else if (view.getId() == R.id.btn_login_google)
            gotoGoogleLogin();
        else if (view.getId() == R.id.btn_login_facebook)
            gotoFacebookLogin();
        else if (view.getId() == R.id.tv_login_terms)
            showTermsConditions();
    }

    private void gotoPhoneLogin() {

    }

    private void gotoEmailLogin() {

    }

    private void gotoGoogleLogin() {

    }

    private void gotoFacebookLogin() {

    }

    private void showTermsConditions() {
        Toast.makeText(AuthenticationActivity.this, "Terms and Conditions will apply", Toast.LENGTH_LONG).show();
    }
}
