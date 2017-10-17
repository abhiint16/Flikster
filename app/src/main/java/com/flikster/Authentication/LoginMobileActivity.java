package com.flikster.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.flikster.R;

public class LoginMobileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOtpSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mobile);
        initializeButton();
    }

    private void initializeButton() {
        btnOtpSend = (Button)findViewById(R.id.btn_mobile_otp_send);
        btnOtpSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_mobile_otp_send)
            gotoMobileOtp();
    }

    private void gotoMobileOtp() {
        startActivity(new Intent(LoginMobileActivity.this, LoginMobileOtpActivity.class));
    }
}
