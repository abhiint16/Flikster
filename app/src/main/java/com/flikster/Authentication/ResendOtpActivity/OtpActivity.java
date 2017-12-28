package com.flikster.Authentication.ResendOtpActivity;

import android.content.Intent;
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
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;
import com.flikster.Util.Common;
import com.flikster.Util.SharedPrefsUtil;
import com.leo.simplearcloader.SimpleArcLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMobileOtpVerify, btnMobileNoEdit, send_otp;
    private TextView tvOtpAgain;
    private EditText etMobileNo;
    private EditText otp_no1, otp_no2, otp_no3, otp_no4, otp_no5, otp_no6;
    String otpStr = "";
    ApiInterface apiInterface;
    String typeStr, OtpId;
    String PEFORM_FORGET = "";
    String emailOrMobilestr;


    SimpleArcLoader mDialog;


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
                typeStr = getIntent().getStringExtra("TYPE");
                OtpId = getIntent().getStringExtra("OTP_ID");

                Log.e("TYPE_CMG", typeStr);
                if (typeStr.equals("email")) {
                    btnMobileNoEdit.setText("Edit");
                    etMobileNo.setText("" + getIntent().getStringExtra("TYPE_DATA").toString());
                } else {
                    btnMobileNoEdit.setText("Edit Number");
                    etMobileNo.setText("+91 " + getIntent().getStringExtra("TYPE_DATA").toString());
                }
            } else {
                Toast.makeText(getApplicationContext(), "Failed to send..", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {

        }

    }

    private void initializeView() {
        etMobileNo = (EditText) findViewById(R.id.tv_verify_mobile);
        btnMobileNoEdit = (Button) findViewById(R.id.btn_verify_mobileno_edit);

        mDialog = (SimpleArcLoader) findViewById(R.id.arc_loader);

        send_otp = (Button) findViewById(R.id.send_otp);
        otp_no1 = (EditText) findViewById(R.id.otp_no1);
        otp_no2 = (EditText) findViewById(R.id.otp_no2);
        otp_no3 = (EditText) findViewById(R.id.otp_no3);
        otp_no4 = (EditText) findViewById(R.id.otp_no4);
        otp_no5 = (EditText) findViewById(R.id.otp_no5);
        otp_no6 = (EditText) findViewById(R.id.otp_no6);

        btnMobileNoEdit.setOnClickListener(this);
        send_otp.setOnClickListener(this);
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
                    if (otpStr.length() == 6) {
                        verifyOTPDataRetrofitInit(otpStr);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_mob_verify) {
            String otp_noStr1 = otp_no1.getText().toString();
            String otp_noStr2 = otp_no2.getText().toString();
            String otp_noStr3 = otp_no3.getText().toString();
            String otp_noStr4 = otp_no4.getText().toString();
            String otp_noStr5 = otp_no5.getText().toString();
            String otp_noStr6 = otp_no6.getText().toString();

            if (otp_noStr1.length() == 0) {
                Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_SHORT).show();
                return;
            } else if (otp_noStr2.length() == 0) {
                Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_SHORT).show();
                return;
            } else if (otp_noStr3.length() == 0) {
                Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_SHORT).show();
                return;
            } else if (otp_noStr4.length() == 0) {
                Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_SHORT).show();
                return;
            } else if (otp_noStr5.length() == 0) {
                Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_SHORT).show();
                return;
            } else if (otp_noStr6.length() == 0) {
                Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_SHORT).show();
                return;
            } else {
                otpStr = otp_noStr1 + otp_noStr2 + otp_noStr3 + otp_noStr4 + otp_noStr5 + otp_noStr6;
            }
            if (otpStr.length() == 6) {
                verifyOTPDataRetrofitInit(otpStr);
            } else {
                Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (view.getId() == R.id.tv_new_otp) {
            sendOtpAgain();
        } else if (view.getId() == R.id.btn_verify_mobileno_edit) {
            btnMobileNoEdit.setVisibility(View.GONE);
            send_otp.setVisibility(View.VISIBLE);
            modifyMobileNo();
        } else if (view.getId() == R.id.send_otp) {
            send_otp.setVisibility(View.GONE);
            btnMobileNoEdit.setVisibility(View.VISIBLE);
            modifyMobileDisable();
            sendOtpAgain();
        }
    }

    private void modifyMobileNo() {
        etMobileNo.setEnabled(true);
    }

    private void modifyMobileDisable() {
        etMobileNo.setEnabled(false);
    }

    private void sendOtpAgain() {
        if (typeStr.equals("email")) {
            emailOrMobilestr = etMobileNo.getText().toString();
            if (emailOrMobilestr.length() != 0) {
                if (!Common.emailValidator(emailOrMobilestr)) {
                    Toast.makeText(getApplicationContext(), "Please enter valid emailid", Toast.LENGTH_SHORT).show();
                } else {
                    resendOTPRetrofitInit();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please enter emaild", Toast.LENGTH_SHORT).show();
            }
        } else {
            emailOrMobilestr = etMobileNo.getText().toString();
            if (emailOrMobilestr.length() != 0) {
                if (emailOrMobilestr.length() != 10) {
                    Log.e("mobileLendth", emailOrMobilestr.length() + "");
                    if (emailOrMobilestr.length() == 14) {
                        Log.e("mobileLendthbefore", emailOrMobilestr + "");
                        emailOrMobilestr = emailOrMobilestr.substring(4);
                        Log.e("mobileLendthafter", emailOrMobilestr + "");
                        resendOTPRetrofitInit();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter valid Mobile No", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    resendOTPRetrofitInit();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please enter emaild", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void verifyOTPDataRetrofitInit(String otpStr) {
        Log.e("Params : OTP:DATA", typeStr + OtpId + otpStr + "");


        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();


        VerifyOTPData emailRegisterPostData = new VerifyOTPData(OtpId, typeStr, otpStr);
        apiInterface = ApiClient.getClient(ApiClient.VERIFY_OTP_URL).create(ApiInterface.class);
        Call<VerifyOTPData> call = apiInterface.verifyOtpData(emailRegisterPostData);
        call.enqueue(new Callback<VerifyOTPData>() {
            @Override
            public void onResponse(Call<VerifyOTPData> call, Response<VerifyOTPData> response) {

                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                if (response.body().getFirstname().toString() != null && !response.body().getFirstname().toString().isEmpty()) {
                    Log.e("USER_NAME", response.body().getFirstname().toString());
                    Log.e("USER_ID", response.body().getId().toString());
                    SharedPrefsUtil.setStringPreference(OtpActivity.this, "USER_NAME", response.body().getFirstname().toString());
                    SharedPrefsUtil.setStringPreference(OtpActivity.this, "USER_ID", response.body().getId().toString());
                    SharedPrefsUtil.setStringPreference(OtpActivity.this, "USER_ROLE", response.body().getFirstname().toString());

                    Toast.makeText(getApplicationContext(), "Verified.", Toast.LENGTH_SHORT).show();

                    PEFORM_FORGET = SharedPrefsUtil.getStringPreference(getApplicationContext(), "PERFORM_FORGOT");
                    Log.e("PEFORM_FORGET", PEFORM_FORGET + "");
                    if (PEFORM_FORGET != null && !PEFORM_FORGET.isEmpty()) {
//                        Toast.makeText(OtpActivity.this, "Successfully Login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(OtpActivity.this, ChangePasswordActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(OtpActivity.this, "Successfully Login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(OtpActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerifyOTPData> call, Throwable t) {

                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Toast.makeText(getApplicationContext(), "Please " +
                        "try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }


    ///OTP Resend
    private void resendOTPRetrofitInit() {

        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        Log.e("Params:SEND_OTP", emailOrMobilestr + typeStr + "");
//        String removeCurrency = amount.getText().toString().substring(3);

        SendOTPData sendotp = new SendOTPData(emailOrMobilestr, typeStr);
        apiInterface = ApiClient.getClient(ApiClient.SEND_OTP_URL).create(ApiInterface.class);
        Call<SendOTPData> call = apiInterface.sendOtpData(sendotp);
        call.enqueue(new Callback<SendOTPData>() {
            @Override
            public void onResponse(Call<SendOTPData> call, Response<SendOTPData> response) {
                Log.e("StatusCode:", response.body().getStatusCode() + "");
                if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {

                    mDialog.setVisibility(View.GONE);
                    mDialog.stop();
                    if (response.body().isOtpStatus()) {
                        Toast.makeText(OtpActivity.this, "OTP sent..", Toast.LENGTH_LONG).show();
                        otp_no1.setText("");
                        otp_no2.setText("");
                        otp_no3.setText("");
                        otp_no4.setText("");
                        otp_no5.setText("");
                        otp_no6.setText("");
                    } else {
                        Toast.makeText(OtpActivity.this, "Failed to sent..", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(OtpActivity.this, "Failed to sent..", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SendOTPData> call, Throwable t) {

                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }


}
