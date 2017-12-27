package com.flikster.Authentication.ResendOtpActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.R;
import com.flikster.Util.Common;
import com.flikster.Util.SharedPrefsUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendOtpWithMobileNoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login_btn;
    EditText et_mobile_no, passwordEt, et_emailid, register_mobile_no, register_password, register_confirm_password;
    ApiInterface apiInterface;
    String type = "";
    LinearLayout moblienolayout, emaillayout;
    String mobileOrNEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_mobile_no);
        initializeView();
    }

    private void initializeView() {
        login_btn = (Button) findViewById(R.id.btn_mobile_otp_send);
        passwordEt = (EditText) findViewById(R.id.passwordEt);
        et_emailid = (EditText) findViewById(R.id.et_emailid);
        emaillayout = (LinearLayout) findViewById(R.id.emaillayout);
        login_btn.setOnClickListener(this);
        type = getIntent().getStringExtra("TYPE").toString();
        if (type.equals("email")) {
            et_emailid.setVisibility(View.VISIBLE);
        } else {
            moblienolayout = (LinearLayout) findViewById(R.id.moblienolayout);
            et_mobile_no = (EditText) findViewById(R.id.et_mobile_no);
            moblienolayout.setVisibility(View.VISIBLE);
            et_emailid.setVisibility(View.GONE);
        }


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_mobile_otp_send) {
            if (type.equals("email")) {
                if (et_emailid.getText().toString() == null || "".equals(et_emailid.getText().toString())) {
                    et_emailid.setError("Emailid Can't be empty");
                    return;
                } else if (!Common.emailValidator(et_emailid.getText().toString())) {
                    et_emailid.setError("Enter valid Emailid");
                    return;
                }
            } else {
                if (et_mobile_no.getText().toString() == null || "".equals(et_mobile_no.getText().toString())) {
                    et_mobile_no.setError("Mobile Can't be empty");
                    return;
                } else {
                    if (et_mobile_no.getText().toString().length() != 10) {
                        et_mobile_no.setError("Enter Valid mobile No");
                        return;
                    }
                }
            }
            try {
                /*if (passwordEt.getText().toString() != null || "".equals(passwordEt.getText().toString())) {
                    passwordEt.setError("Password Can't be empty");
                    return;
                } else {

                }*/


            } catch (Exception e) {

            }
            postUserDataRetrofitInit();


        }
    }

    private void postUserDataRetrofitInit() {

        if (type.equals("email")) {
            mobileOrNEmail = et_emailid.getText().toString();
        } else {
            mobileOrNEmail = et_mobile_no.getText().toString();
        }
        Log.e("Params:SEND_OTP", mobileOrNEmail + type + "");

        SendOTPData sendotp = new SendOTPData
                (mobileOrNEmail, type);
        apiInterface = ApiClient.getClient
                (ApiClient.SEND_OTP_URL)
                .create(ApiInterface.class);
        Call<SendOTPData> call = apiInterface.sendOtpData(sendotp);
        call.enqueue(new Callback<SendOTPData>() {
            @Override
            public void onResponse(Call<SendOTPData> call, Response<SendOTPData> response) {
                try {

                }catch (Exception e){

                }
                Log.e("StatusCode:", response.body().getStatusCode() + "");
//                Log.e("")
                if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {
                    if (response.body().isOtpStatus()) {
                        Toast.makeText(SendOtpWithMobileNoActivity.this, "OTP sent..",
                                Toast.LENGTH_LONG).show();
                        SharedPrefsUtil.setStringPreference(getApplicationContext(),
                                "OTP_ID", response.body().getId().toString());
                        Intent i = new Intent(SendOtpWithMobileNoActivity.this, LoginMobileOtpActivity.class);
                        if (type.equals("email")) {
                            i.putExtra("TYPE", "email");
                            i.putExtra("TYPE_DATA", et_emailid.getText().toString());
                            i.putExtra("OTP_ID", response.body().getId().toString());
                        } else {
                            i.putExtra("TYPE", "mobile");
                            i.putExtra("TYPE_DATA", et_mobile_no.getText().toString());
                            i.putExtra("OTP_ID", response.body().getId().toString());
                        }
                        startActivity(i);
                    } else {
                        Toast.makeText(SendOtpWithMobileNoActivity.this, "Invalid details..", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(SendOtpWithMobileNoActivity.this, "Invalid details..", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<SendOTPData> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }
}
