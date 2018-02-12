package com.flikster.Authentication.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.Authentication.AuthenticationActivity;
import com.flikster.Authentication.OtpAndResendOtpActivity.SendOtpWithMobileNoActivityOrEmail;
import com.flikster.Authentication.SignUpActivity.SignUpWithEmail.SignUpWithEmailActivity;
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

public class LoginWithEmailOrMobileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login_btn;
    EditText et_emailid, et_mobile_no, passwordEt, register_mobile_no, register_password, register_confirm_password;
    ApiInterface apiInterface;
    TextView forgot_txt;
    LinearLayout moblienolayout, emaillayout;
    SimpleArcLoader mDialog;
    String CLICK_EVENT = "";
    ImageButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_emailid);
        initializeView();
    }

    private void initializeView() {
        login_btn = (Button) findViewById(R.id.btn_mobile_otp_send);
        passwordEt = (EditText) findViewById(R.id.passwordEt);
        forgot_txt = (TextView) findViewById(R.id.forgot_txt);
        emaillayout = (LinearLayout) findViewById(R.id.emaillayout);
        et_emailid = (EditText) findViewById(R.id.et_emailid);
        back_btn = (ImageButton) findViewById(R.id.back_btn);

        CLICK_EVENT = getIntent().getStringExtra("TYPE");
        Log.e("CLICK_EVENT", CLICK_EVENT + "Data");
        if (CLICK_EVENT.equals("email")) {
            et_emailid.setVisibility(View.VISIBLE);
        } else {
            moblienolayout = (LinearLayout) findViewById(R.id.moblienolayout);
            et_mobile_no = (EditText) findViewById(R.id.et_mobile_no);
            moblienolayout.setVisibility(View.VISIBLE);
            et_emailid.setVisibility(View.GONE);
            emaillayout.setVisibility(View.GONE);
        }

        login_btn.setOnClickListener(this);
        forgot_txt.setOnClickListener(this);
        mDialog = (SimpleArcLoader) findViewById(R.id.arc_loader);
        back_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_mobile_otp_send) {
            if (CLICK_EVENT.equals("email")) {
                if (et_emailid.getText().toString() == null || "".equals(et_emailid.getText().toString())) {
                    et_emailid.setError("Emailid Can't be empty");
                    return;
                } else if (!Common.emailValidator(et_emailid.getText().toString())) {
                    et_emailid.setError("Enter valid Emailid");
                    return;
                }
            } else if (!CLICK_EVENT.equals("email")) {
                if (et_mobile_no.getText().toString() == null || "".equals(et_mobile_no.getText().toString())) {
                    et_mobile_no.setError("Mobile Can't be empty");
                    return;
                } else {
                    if (et_mobile_no.getText().toString().length() != 10) {
                        et_mobile_no.setError("Enter Valid mobile No");
                        return;
                    }
                }
            } else if (passwordEt.getText().toString() == null || "".equals(passwordEt.getText().toString())) {
                passwordEt.setError("Password Can't be empty");
                return;
            }
            postUserDataRetrofitInit();
        } else if (view.getId() == R.id.forgot_txt) {
            Intent i = new Intent(LoginWithEmailOrMobileActivity.this, SendOtpWithMobileNoActivityOrEmail.class);
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "PERFORM_FORGOT", "ACCESS");
            if (CLICK_EVENT.equals("email")) {
                i.putExtra("TYPE", "email");
                i.putExtra("MOBILE_NO", et_emailid.getText().toString());
            } else {
                i.putExtra("TYPE", "mobile");
                i.putExtra("MOBILE_NO", et_mobile_no.getText().toString());
            }
            startActivity(i);
        }else if (view.getId() == R.id.back_btn){
//            Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginWithEmailOrMobileActivity.this, AuthenticationActivity.class);
            startActivity(intent);
        }
    }

    private void postUserDataRetrofitInit() {
        String nameOrMobile = "";
        if (CLICK_EVENT.equals("email")) {
            nameOrMobile = et_emailid.getText().toString();
        } else {
            nameOrMobile = et_mobile_no.getText().toString();
        }
        Log.e("paramsLogin", nameOrMobile + passwordEt.getText().toString());
        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        LoginData emailRegisterPostData = new LoginData
                (nameOrMobile, passwordEt.getText().toString());
        apiInterface = ApiClient.getClient
                (ApiClient.LOGIN_URL)
                .create(ApiInterface.class);
        Call<LoginResponseData> call = apiInterface.loginUserData(emailRegisterPostData);
        call.enqueue(new Callback<LoginResponseData>() {
            @Override
            public void onResponse(Call<LoginResponseData> call, Response<LoginResponseData> response) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {

                    Toast.makeText(LoginWithEmailOrMobileActivity.this, "Successfully Login", Toast.LENGTH_LONG).show();
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "IS_LOGGED_IN",
                            "LOGGED_IN");
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "USER_ID",
                            response.body().getData().getId());
                    Log.e("UserIDformServer", response.body().getData().getId());
                    Log.e("UserNameformServer", response.body().getData().getFirstname());

                    if (response.body().getData().getFirstname().toString() != null
                            && !response.body().getData().getFirstname().toString().isEmpty()) {
                        SharedPrefsUtil.setStringPreference(getApplicationContext(), "USER_NAME",
                                response.body().getData().getFirstname());
                    }
                    if (response.body().getData().getLastname()!= null
                            && !response.body().getData().getLastname().toString().isEmpty()) {
                        SharedPrefsUtil.setStringPreference(getApplicationContext(), "USER_NAME",
                                response.body().getData().getFirstname() +
                                        " " + response.body().getData().getLastname()
                        );
                    }

                    if (response.body().getData().getEmail() != null
                            && !response.body().getData().getEmail().isEmpty()) {
                        SharedPrefsUtil.setStringPreference(getApplicationContext(), "EMAIL_ID",
                                response.body().getData().getEmail()
                        );
                    }

                    if (response.body().getData().getMobile() != null
                            && !response.body().getData().getMobile().isEmpty()) {
                        SharedPrefsUtil.setStringPreference(getApplicationContext(), "MOBILE_NO",
                                response.body().getData().getMobile()
                        );
                    }

                    Log.e("FirstName", response.body().getData().getFirstname());
                    Toast.makeText(LoginWithEmailOrMobileActivity.this, "" +
                            response.body().getData().getFirstname(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginWithEmailOrMobileActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    if (CLICK_EVENT.equals("email")) {
                        Toast.makeText(LoginWithEmailOrMobileActivity.this, "Invalid Emaild or Password", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginWithEmailOrMobileActivity.this, "Invalid Mobile No or Password", Toast.LENGTH_LONG).show();
                    }

                }


            }

            @Override
            public void onFailure(Call<LoginResponseData> call, Throwable t) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }
}
