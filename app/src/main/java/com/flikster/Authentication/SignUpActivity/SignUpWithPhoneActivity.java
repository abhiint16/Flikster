package com.flikster.Authentication.SignUpActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.Authentication.AuthenticationActivity;
import com.flikster.Authentication.LoginActivity.LoginWithEmailActivity;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;
import com.leo.simplearcloader.SimpleArcLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpWithPhoneActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private Button register_btn;
    EditText register_first_name, register_last_name, register_mobile_no, register_password, register_confirm_password;
    ApiInterface apiInterface;
    TextView register_main_title;
    private Button btn_account_male, btn_account_female;
    private ImageButton back_btn;
    SimpleArcLoader mDialog;
    String genderstr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        initializeView();
    }

    private void initializeView() {
        register_btn = (Button) findViewById(R.id.register_btn);
        register_first_name = (EditText) findViewById(R.id.register_first_name);
        register_last_name = (EditText) findViewById(R.id.register_last_name);
        register_mobile_no = (EditText) findViewById(R.id.register_mobile_no);


//        register_mobile_no.setInputType(View.AUTOFILL_TYPE_TEXT);
//        register_mobile_no.setOnFocusChangeListener(this);
        register_password = (EditText) findViewById(R.id.register_password);
        register_main_title = (TextView) findViewById(R.id.register_main_title);
//        register_main_title.setText("Register");
        register_confirm_password = (EditText) findViewById(R.id.register_confirm_password);
        register_btn.setOnClickListener(this);
        btn_account_male = (Button) findViewById(R.id.btn_account_male);
        btn_account_female = (Button) findViewById(R.id.btn_account_female);
        btn_account_male.setOnClickListener(this);
        btn_account_female.setOnClickListener(this);
        back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(this);

        mDialog = (SimpleArcLoader) findViewById(R.id.arc_loader);

        genderstr = "male";
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.register_btn) {
            if (register_first_name.getText().toString() == null
                    || "".equals(register_first_name.getText().toString())) {
                register_first_name.setError("First Name Can't be empty");
                return;
            } /*else if (register_last_name.getText().toString() == null
                    || "".equals(register_last_name.getText().toString())) {
                register_last_name.setError("Last Name Can't be empty");
                return;
            }*/ else if (register_mobile_no.getText().toString() == null || "".equals(register_mobile_no.getText().toString())) {
                register_mobile_no.setError("Email Can't be empty");
                return;
            } else if (register_password.getText().toString() == null || "".equals(register_password.getText().toString())) {
                register_password.setError("Password Can't be empty");
                return;
            } else if (register_confirm_password.getText().toString() == null || "".equals(register_confirm_password.getText().toString())) {
                register_confirm_password.setError("Confirm Password Can't be empty");
                return;
            } else if (!register_password.getText().toString().equals(register_confirm_password.getText().toString())) {
                register_confirm_password.setError("Confirm Password is not same is password");
                return;
            }
//            postUserDataRetrofitInit();
            postUserDataServerInit();
        } else if (view.getId() == R.id.btn_account_male) {
            genderstr = "male";
            btn_account_male.setTextColor(getResources().getColor(R.color.white));
            btn_account_male.setBackgroundColor(getResources().getColor(R.color.colorCreateAccountSelected));
            btn_account_female.setTextColor(getResources().getColor(R.color.black));
            btn_account_female.setBackgroundColor(getResources().getColor(R.color.colorImageBackgroundGrey));
        } else if (view.getId() == R.id.btn_account_female) {
            genderstr = "female";
            btn_account_male.setTextColor(getResources().getColor(R.color.black));
            btn_account_male.setBackgroundColor(getResources().getColor(R.color.colorImageBackgroundGrey));
            btn_account_female.setTextColor(getResources().getColor(R.color.white));
            btn_account_female.setBackgroundColor(getResources().getColor(R.color.colorCreateAccountSelected));
        } else if (view.getId() == R.id.back_btn) {
            Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUpWithPhoneActivity.this, AuthenticationActivity.class);
            startActivity(intent);
        }
    }

    private void postUserDataServerInit() {
        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        Log.e("inside lognemail", "inside logingemaig");
        PhoneRegisterPostData emailRegisterPostData =
                new PhoneRegisterPostData(
                        register_first_name.getText().toString(),
                        register_last_name.getText().toString(),
                        "undefined",
                        register_mobile_no.getText().toString(),
                        register_password.getText().toString(),
                        "user", genderstr);
        apiInterface = ApiClient.getClient(ApiClient.SIGNUP_URL)
                .create(ApiInterface.class);
        Call<RegisterPostStatus> call = apiInterface.emailOrPhoneRegisterUserData(emailRegisterPostData);
        call.enqueue(new Callback<RegisterPostStatus>() {
            @Override
            public void onResponse(Call<RegisterPostStatus> call,
                                   Response<RegisterPostStatus> response) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                if (response.body().getStatusCode() == 200) {
                    Toast.makeText(SignUpWithPhoneActivity.this, "Register Successfully", Toast.LENGTH_LONG).show();
                    Toast.makeText(SignUpWithPhoneActivity.this, "OTP sent to register mobile no", Toast.LENGTH_LONG).show();
                    SharedPrefsUtil.setStringPreference(SignUpWithPhoneActivity.this, "IS_LOGGED_IN", "LOGGED_IN");
                    Intent intent = new Intent(SignUpWithPhoneActivity.this, LoginWithEmailActivity.class);
                    startActivity(intent);
                } else {
                    //Mobile number already exists
                    Toast.makeText(SignUpWithPhoneActivity.this, "Mobile number already exists", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<RegisterPostStatus> call, Throwable t) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }

    private void postUserDataRetrofitInit() {
        Log.e("inside lognemail", "inside logingemaig");
        EmailRegisterPostData emailRegisterPostData = new EmailRegisterPostData(register_first_name.getText().toString(),
                register_last_name.getText().toString(), register_mobile_no.getText().toString(),
                register_password.getText().toString());
        apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/user-ms/registration/").create(ApiInterface.class);
        Call<MobileOrEmailRegisterCheckData> call = apiInterface.emailRegisterUserData(emailRegisterPostData);
        call.enqueue(new Callback<MobileOrEmailRegisterCheckData>() {
            @Override
            public void onResponse(Call<MobileOrEmailRegisterCheckData> call,
                                   Response<MobileOrEmailRegisterCheckData> response) {
                Toast.makeText(SignUpWithPhoneActivity.this, "Login Completed", Toast.LENGTH_LONG).show();
                SharedPrefsUtil.setStringPreference(SignUpWithPhoneActivity.this, "IS_LOGGED_IN", "LOGGED_IN");
                Intent intent = new Intent(SignUpWithPhoneActivity.this, HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<MobileOrEmailRegisterCheckData> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view.getId() == R.id.register_mobile_no) {
            if (b == false) {
                apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/user-ms/checkEmail/").create(ApiInterface.class);
                Call<MobileOrEmailRegisterCheckData> call = apiInterface.checkForMobileOrEmailAlreadyThere("http://apiservice.flikster.com/v3/user-ms/checkEmail/" + register_mobile_no.getText().toString());
                call.enqueue(new Callback<MobileOrEmailRegisterCheckData>() {
                    @Override
                    public void onResponse(Call<MobileOrEmailRegisterCheckData> call, Response<MobileOrEmailRegisterCheckData> response) {
                        if (response.body().getCount() != 0) {
                            register_mobile_no.setError("Email Id already present. Either login or change EmailId");
                            Toast.makeText(SignUpWithPhoneActivity.this, "Email Id already present. Either login or change EmailId", Toast.LENGTH_SHORT).show();
                            register_btn.setEnabled(false);
                        } else if (response.body().getCount() == 0) {
                            Toast.makeText(SignUpWithPhoneActivity.this, "New Email Found.Carry on.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MobileOrEmailRegisterCheckData> call, Throwable t) {
                        Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
//                ib_like.setImageResource(0);
//                ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_icon));
                    }
                });
            }
        }
    }
}
