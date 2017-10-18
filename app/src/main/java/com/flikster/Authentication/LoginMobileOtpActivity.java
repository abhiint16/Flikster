package com.flikster.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.HomeActivity;
import com.flikster.R;

import org.w3c.dom.Text;

public class LoginMobileOtpActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnMobileOtpVerify, btnMobileNoEdit;
    private TextView tvOtpAgain;
    private EditText etMobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_verify_mobile_otp);
        initializeView();
    }

    private void initializeView() {
        etMobileNo = (EditText)findViewById(R.id.tv_verify_mobile);
        btnMobileNoEdit = (Button)findViewById(R.id.btn_verify_mobileno_edit);
        btnMobileNoEdit.setOnClickListener(this);
        tvOtpAgain = (TextView)findViewById(R.id.tv_new_otp);
        tvOtpAgain.setOnClickListener(this);
        btnMobileOtpVerify = (Button)findViewById(R.id.btn_mob_verify);
        btnMobileOtpVerify.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_mob_verify)
            gotoHome();
        else if (view.getId() == R.id.tv_new_otp)
            sendOtpAgain();
        else if (view.getId() == R.id.btn_verify_mobileno_edit)
            modifyMobileNo();
    }

    private void modifyMobileNo() {
        etMobileNo.setEnabled(true);
    }

    private void sendOtpAgain() {
        Toast.makeText(LoginMobileOtpActivity.this, "OTP sent", Toast.LENGTH_LONG).show();
    }

    private void gotoHome() {
        startActivity(new Intent(LoginMobileOtpActivity.this, HomeActivity.class));
        finish();
    }
}
