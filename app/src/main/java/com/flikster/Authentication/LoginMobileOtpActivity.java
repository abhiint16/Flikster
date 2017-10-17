package com.flikster.Authentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.flikster.R;

public class LoginMobileOtpActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnMobileOtpVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_verify_mobile_otp);
        initializeView();
    }

    private void initializeView() {
        btnMobileOtpVerify = (Button)findViewById(R.id.btn_mob_verify);
        btnMobileOtpVerify.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
