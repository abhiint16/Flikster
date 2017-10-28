package com.flikster.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.HomeActivity;
import com.flikster.R;

public class LoginEmailOtpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnEmailOtpVerify;
    private TextView tvSendOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_verify_email_otp);
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
        if (view.getId() == R.id.tv_send_new_otp)
            sendOtpAgain();
        else if (view.getId() == R.id.btn_email_verify)
            gotoHomeActivity();

    }

    private void sendOtpAgain() {
        Toast.makeText(LoginEmailOtpActivity.this, "OTP sent", Toast.LENGTH_LONG).show();
    }

    private void gotoHomeActivity() {
        startActivity(new Intent(LoginEmailOtpActivity.this, HomeActivity.class));
        finish();
    }
}
