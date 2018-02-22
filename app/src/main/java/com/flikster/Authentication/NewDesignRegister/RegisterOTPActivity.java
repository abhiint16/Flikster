package com.flikster.Authentication.NewDesignRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    ImageButton back_btn;
    private EditText otp_no1, otp_no2, otp_no3, otp_no4, otp_no5, otp_no6;
    String otpStr = "";
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
        back_btn.setOnClickListener(this);
        otpFieldMethod();
    }

    private void otpFieldMethod() {
        otp_no1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (otp_no1.getText().toString().length() == 1) {
                    otpStr = otp_no1.getText().toString();
                    otp_no1.setBackgroundColor(getResources().getColor(R.color.colorImageBackgroundGrey));
                    otp_no1.clearFocus();
                    otp_no2.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        otp_no2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (otp_no2.getText().toString().length() == 1) {
                    otpStr = otpStr + otp_no2.getText().toString();
                    otp_no2.setBackgroundColor(getResources().getColor(R.color.colorImageBackgroundGrey));
                    otp_no2.clearFocus();
                    otp_no3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        otp_no3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (otp_no3.getText().toString().length() == 1) {
                    otpStr = otpStr + otp_no3.getText().toString();
                    otp_no3.setBackgroundColor(getResources().getColor(R.color.colorImageBackgroundGrey));
                    otp_no3.clearFocus();
                    otp_no4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp_no4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (otp_no4.getText().toString().length() == 1) {
                    otpStr = otpStr + otp_no4.getText().toString();
                    otp_no4.setBackgroundColor(getResources().getColor(R.color.colorImageBackgroundGrey));
                    otp_no4.clearFocus();
                    otp_no5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp_no5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (otp_no5.getText().toString().length() == 1) {
                    otpStr = otpStr + otp_no5.getText().toString();
                    otp_no5.setBackgroundColor(getResources().getColor(R.color.colorImageBackgroundGrey));
                    otp_no5.clearFocus();
                    otp_no6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp_no6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (otp_no6.getText().toString().length() == 1) {
                    otpStr = otpStr + otp_no6.getText().toString();
                    otp_no6.setBackgroundColor(getResources().getColor(R.color.colorImageBackgroundGrey));
                    otp_no6.clearFocus();
                    if (otpStr.length() == 6) {
                        //verifyOTPDataRetrofitInit(otpStr);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initializeView() {
        btn_verify_mobileno_edit=(Button)findViewById(R.id.btn_verify_mobileno_edit);
        back_btn=(ImageButton)findViewById(R.id.back_btn);
        activity_login_verify_mobile_otp_header=(TextView)findViewById(R.id.activity_login_verify_mobile_otp_header);
        activity_login_verify_mobile_otp_underline=(ImageView)findViewById(R.id.activity_login_verify_mobile_otp_underline);
        tv_verify_mobile=(EditText)findViewById(R.id.tv_verify_mobile);
        btn_mob_verify=(Button)findViewById(R.id.btn_mob_verify);
        otp_no1 = (EditText) findViewById(R.id.otp_no1);
        otp_no2 = (EditText) findViewById(R.id.otp_no2);
        otp_no3 = (EditText) findViewById(R.id.otp_no3);
        otp_no4 = (EditText) findViewById(R.id.otp_no4);
        otp_no5 = (EditText) findViewById(R.id.otp_no5);
        otp_no6 = (EditText) findViewById(R.id.otp_no6);
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
            intent.putExtra("PhoneEmail",getIntent().getStringExtra("PhoneEmail"));
            startActivity(intent);
        }
        else if (view.getId()==R.id.back_btn)
        {
            Intent intent=new Intent(RegisterOTPActivity.this,RegisterPhoneEmail.class);
            intent.putExtra("TYPE",getIntent().getStringExtra("TYPE"));
            startActivity(intent);
        }
    }
}
