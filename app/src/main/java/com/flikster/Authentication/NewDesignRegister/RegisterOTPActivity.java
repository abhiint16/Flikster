package com.flikster.Authentication.NewDesignRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flikster.R;

/**
 * Created by abhishek on 21-02-2018.
 */

public class RegisterOTPActivity extends AppCompatActivity implements View.OnClickListener {

    TextView activity_login_verify_mobile_otp_header;
    ImageView activity_login_verify_mobile_otp_underline;
    EditText tv_verify_mobile;
    Button btn_verify_mobileno_edit,btn_mob_verify;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_verify_mobile_otp);
        initializeView();
        initializeRest();
    }

    private void initializeRest() {
        activity_login_verify_mobile_otp_header.setText("Register");
        if ("email".equals(getIntent().getStringExtra("TYPE")))
        {
            tv_verify_mobile.setText("<"+getIntent().getStringExtra("PhoneEmail")+">");
            btn_verify_mobileno_edit.setVisibility(View.GONE);
        }
        else
        {
            tv_verify_mobile.setText(getIntent().getStringExtra("PhoneEmail"));
            btn_verify_mobileno_edit.setOnClickListener(this);
        }
        btn_mob_verify.setOnClickListener(this);
    }

    private void initializeView() {
        btn_verify_mobileno_edit=(Button)findViewById(R.id.btn_verify_mobileno_edit);
        activity_login_verify_mobile_otp_header=(TextView)findViewById(R.id.activity_login_verify_mobile_otp_header);
        activity_login_verify_mobile_otp_underline=(ImageView)findViewById(R.id.activity_login_verify_mobile_otp_underline);
        tv_verify_mobile=(EditText)findViewById(R.id.tv_verify_mobile);
        btn_mob_verify=(Button)findViewById(R.id.btn_mob_verify);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_verify_mobileno_edit)
        {
            Intent intent=new Intent(RegisterOTPActivity.this,RegisterPhoneEmail.class);
            intent.putExtra("TYPE",getIntent().getStringExtra("TYPE"));
            startActivity(intent);
        }else if (view.getId()==R.id.btn_mob_verify)
        {
            Intent intent=new Intent(RegisterOTPActivity.this,RegisterSetPasswordActivity.class);
            intent.putExtra("TYPE",getIntent().getStringExtra("TYPE"));
            startActivity(intent);
        }
    }
}
