package com.flikster.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.HomeActivity.HomeActivity;
import com.flikster.KeyCloak.KeycloakHelper;
import com.flikster.R;
import com.flikster.SharedPref.SharedPref;
import com.flikster.Util.SharedPrefsUtil;

import org.jboss.aerogear.android.core.Callback;

public class AuthenticationActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLoginPhone, btnLoginEmail;
    private Button btnLoginGoogle, btnLoginFacebook,keycloak,without_keycloak;
    private TextView tvLoginTermsCond;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        initializeView();
    }

    private void initializeView() {
        sharedPref=new SharedPref(getApplicationContext());
        btnLoginPhone = (Button)findViewById(R.id.btn_login_phone);
        btnLoginEmail = (Button)findViewById(R.id.btn_login_mail);
        btnLoginGoogle = (Button)findViewById(R.id.btn_login_google);
        btnLoginFacebook = (Button)findViewById(R.id.btn_login_facebook);
        tvLoginTermsCond = (TextView)findViewById(R.id.tv_login_terms);
        keycloak=(Button)findViewById(R.id.keycloak);
        without_keycloak=(Button)findViewById(R.id.without_keycloak);
        without_keycloak.setOnClickListener(this);
        keycloak.setOnClickListener(this);
        btnLoginPhone.setOnClickListener(this);
        btnLoginEmail.setOnClickListener(this);
        btnLoginGoogle.setOnClickListener(this);
        btnLoginFacebook.setOnClickListener(this);
        tvLoginTermsCond.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login_phone) {
            Log.e("inside onclick", "insidde onclikc");
            gotoPhoneLogin();
        } else if (view.getId() == R.id.without_keycloak) {
            SharedPrefsUtil.setStringPreference(AuthenticationActivity.this, "IS_LOGGED_IN", "NOT_LOGGED_IN");
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn_login_mail)
            gotoEmailLogin();
        else if (view.getId() == R.id.btn_login_google)
            gotoGoogleLogin();
        else if (view.getId() == R.id.btn_login_facebook)
            gotoFacebookLogin();
        else if (view.getId() == R.id.tv_login_terms)
            showTermsConditions();
        else if (view.getId() == R.id.keycloak) {
            SharedPrefsUtil.setStringPreference(AuthenticationActivity.this, "IS_LOGGED_IN", "LOGGED_IN");
            Toast.makeText(getApplicationContext(), "You're now LoggedIn.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AuthenticationActivity.this, HomeActivity.class);
            startActivity(intent);
            /*if (!KeycloakHelper.isConnected()) {
                Log.e("inside if not cnnect","inside if not cnnect");
                KeycloakHelper.connect(AuthenticationActivity.this, new Callback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("inside inSuccess","insde onsuccess"+data);
                        SharedPrefsUtil.setStringPreference(AuthenticationActivity.this,"IS_LOGGED_IN","LOGGED_IN");
                        Log.e("ckeckforsharedinif",""+sharedPref.isLoggedIn());
                        Toast.makeText(getApplicationContext(), "heaven begins here", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(AuthenticationActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.e("insde onFailure","insied onfilaure"+e);
                        SharedPrefsUtil.setStringPreference(AuthenticationActivity.this,"IS_LOGGED_IN","NOT_LOGGED_IN");
                        Toast.makeText(getApplicationContext(), "hell begins here! Couldn't connect. Try again", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(AuthenticationActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }
                });
            }
            else if(KeycloakHelper.isConnected())
            {
                Intent intent=new Intent(AuthenticationActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        }*/
        }
    }

    private void gotoPhoneLogin() {
        startActivity(new Intent(AuthenticationActivity.this, LoginMobileActivity.class));
    }

    private void gotoEmailLogin() {
        startActivity(new Intent(AuthenticationActivity.this, LoginEmailActivity.class));
    }

    private void gotoGoogleLogin() {
        Toast.makeText(AuthenticationActivity.this, "Coming Soon", Toast.LENGTH_LONG).show();
    }

    private void gotoFacebookLogin() {
        Toast.makeText(AuthenticationActivity.this, "Coming Soon", Toast.LENGTH_LONG).show();
    }

    private void showTermsConditions() {
        Toast.makeText(AuthenticationActivity.this, "Terms and Conditions will apply", Toast.LENGTH_LONG).show();
    }
}
