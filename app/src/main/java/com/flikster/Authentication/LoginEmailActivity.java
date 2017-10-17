package com.flikster.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.flikster.R;

public class LoginEmailActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnEmailOtpSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        initializeButton();
    }

    private void initializeButton() {
        btnEmailOtpSend = (Button)findViewById(R.id.btn_login_otp_send);
        btnEmailOtpSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(LoginEmailActivity.this, LoginEmailOtpActivity.class));
    }
}
