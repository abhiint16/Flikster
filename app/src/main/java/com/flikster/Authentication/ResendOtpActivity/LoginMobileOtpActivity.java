package com.flikster.Authentication.ResendOtpActivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.Authentication.ChangePasswordActivity.ChangePasswordActivity;
import com.flikster.Authentication.ChangePasswordActivity.ChangePasswordData;
import com.flikster.Authentication.LoginActivity.LoginData;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginMobileOtpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMobileOtpVerify, btnMobileNoEdit;
    private TextView tvOtpAgain;
    private EditText etMobileNo;
    private EditText otp_no1, otp_no2, otp_no3, otp_no4, otp_no5, otp_no6;
    String otpStr = "";
    ApiInterface apiInterface;
    String typeStr;
    String PEFORM_FORGET = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_verify_mobile_otp);
        initializeView();
        initializeRest();
    }

    private void initializeRest() {
        try {
            Log.e("TYPE_DATA", getIntent().getStringExtra("TYPE_DATA").toString());
            if (getIntent().getStringExtra("TYPE_DATA").toString() != null && !getIntent().getStringExtra("TYPE_DATA").toString().isEmpty()) {
                typeStr = getIntent().getStringExtra("TYPE").toString();
                btnMobileNoEdit.setText("Edit Number");
                etMobileNo.setText("+91 " + getIntent().getStringExtra("TYPE_DATA").toString());
            } else {
                typeStr = getIntent().getStringExtra("TYPE").toString();
                btnMobileNoEdit.setText("Edit Number");
                etMobileNo.setText("" + getIntent().getStringExtra("TYPE_DATA").toString());
            }
        } catch (Exception e) {

        }

    }

    private void initializeView() {
        etMobileNo = (EditText) findViewById(R.id.tv_verify_mobile);
        btnMobileNoEdit = (Button) findViewById(R.id.btn_verify_mobileno_edit);
        otp_no1 = (EditText) findViewById(R.id.otp_no1);
        otp_no2 = (EditText) findViewById(R.id.otp_no2);
        otp_no3 = (EditText) findViewById(R.id.otp_no3);
        otp_no4 = (EditText) findViewById(R.id.otp_no4);
        otp_no5 = (EditText) findViewById(R.id.otp_no5);
        otp_no6 = (EditText) findViewById(R.id.otp_no6);

        btnMobileNoEdit.setOnClickListener(this);
        tvOtpAgain = (TextView) findViewById(R.id.tv_new_otp);
        tvOtpAgain.setOnClickListener(this);
        btnMobileOtpVerify = (Button) findViewById(R.id.btn_mob_verify);
        btnMobileOtpVerify.setOnClickListener(this);


        otp_no1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

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
            public void afterTextChanged(Editable s) {

            }
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
            public void afterTextChanged(Editable s) {

            }
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
//                    otp_no1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_mob_verify)
            if (otpStr.length() == 6) {
                verifyOTPDataRetrofitInit(otpStr);
//                gotoHome();
            } else {
                Toast.makeText(getApplicationContext(), "Please enter OTP", Toast.LENGTH_SHORT).show();
            }

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


    /*private void postResendOTPDataRetrofitInit(String otpStr) {
        Log.e("inside lognemail", "inside logingemaig");
        LoginData emailRegisterPostData = new LoginData
                (et_mobile_no.getText().toString(),
                        otpStr);
        apiInterface = ApiClient.getClient
                (ApiClient.LOGIN_URL)
                .create(ApiInterface.class);
        Call<LoginData> call = apiInterface.loginUserData(emailRegisterPostData);
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {
                    Toast.makeText(LoginMobileOtpActivity.this, "Successfully Login", Toast.LENGTH_LONG).show();
                    SharedPrefsUtil.setStringPreference(LoginMobileOtpActivity.this,
                            "IS_LOGGED_IN", "LOGGED_IN");
                    Intent intent = new Intent(LoginMobileOtpActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginMobileOtpActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }*/


    private void verifyOTPDataRetrofitInit(String otpStr) {
        Log.e("Params : OTP:DATA", typeStr +
                getIntent().getStringExtra(
                        "OTP_ID") + otpStr + "");
        Log.e("otp_type", typeStr);
        Log.e("otp_id", getIntent().getStringExtra(
                "OTP_ID"));
        Log.e("otp_no", otpStr);
        VerifyOTPData emailRegisterPostData = new VerifyOTPData
                (getIntent().getStringExtra("OTP_ID"),
                        typeStr,
                        otpStr);
        apiInterface = ApiClient.getClient
                (ApiClient.VERIFY_OTP_URL)
                .create(ApiInterface.class);
        Call<VerifyOTPData> call = apiInterface.verifyOtpData(emailRegisterPostData);
        call.enqueue(new Callback<VerifyOTPData>() {
            @Override
            public void onResponse(Call<VerifyOTPData> call, Response<VerifyOTPData> response) {
                if (response.body().getFirstname().toString() != null && !response.body().getFirstname().toString().isEmpty()) {
                    Log.e("USER_NAME", response.body().getFirstname().toString());
                    Log.e("USER_ID", response.body().getId().toString());
                    Toast.makeText(LoginMobileOtpActivity.this, "Successfully Login",
                            Toast.LENGTH_LONG).show();
                    SharedPrefsUtil.setStringPreference(LoginMobileOtpActivity.this,
                            "USER_NAME", response.body().getFirstname().toString());
                    SharedPrefsUtil.setStringPreference(LoginMobileOtpActivity.this,
                            "USER_ID", response.body().getId().toString());
                    SharedPrefsUtil.setStringPreference(LoginMobileOtpActivity.this,
                            "USER_ROLE", response.body().getFirstname().toString());
                    Toast.makeText(getApplicationContext(), "Verified.", Toast.LENGTH_SHORT).show();

                    PEFORM_FORGET = SharedPrefsUtil.getStringPreference(getApplicationContext(), "PERFORM_FORGOT");
                    Log.e("PEFORM_FORGET", PEFORM_FORGET + "");
                    if (PEFORM_FORGET != null && !PEFORM_FORGET.isEmpty()) {
                        Intent intent = new Intent(LoginMobileOtpActivity.this, ChangePasswordActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(LoginMobileOtpActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }


                }

               /* if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {
                    Toast.makeText(LoginMobileOtpActivity.this,
                            "Successfully Login",
                            Toast.LENGTH_LONG).show();
                    SharedPrefsUtil.setStringPreference(LoginMobileOtpActivity.this,
                            "IS_LOGGED_IN", "LOGGED_IN");
                    Intent intent = new Intent(LoginMobileOtpActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginMobileOtpActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                }*/


            }

            @Override
            public void onFailure(Call<VerifyOTPData> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }


}
