package com.flikster.Authentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flikster.R;

public class LoginEmailOtpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnEmailOtpVerify;
    private TextView tvSendOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
    }

    private void initializeView() {
        btnEmailOtpVerify = (Button)findViewById(R.id.btn_email_verify);
        btnEmailOtpVerify.setOnClickListener(this);
        tvSendOtp = (TextView)findViewById(R.id.tv_send_new_otp);
        tvSendOtp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
