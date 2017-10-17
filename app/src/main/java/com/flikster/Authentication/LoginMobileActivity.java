package com.flikster.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.flikster.R;

public class LoginMobileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOtpSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("inside oncreate","inside oncreate");
        setContentView(R.layout.activity_login_mobile);
        Log.e("inside oncreateafter","inside oncreateafter");
        initializeButton();
    }

    private void initializeButton() {
        Log.e("inside initiazebutn","inside initiazebutn");
        btnOtpSend = (Button)findViewById(R.id.btn_mobile_otp_send);
        Log.e("after initiaze","after initiaze");
        btnOtpSend.setOnClickListener(this);
        Log.e("after","after");
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
