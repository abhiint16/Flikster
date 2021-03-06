package com.flikster.Authentication.ChangePasswordActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.flikster.Authentication.AuthenticationActivity;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;
import com.leo.simplearcloader.SimpleArcLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Logins on 27-12-2017.
 */

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login_btn;
    EditText et_mobile_no, passwordEt, confirm_password;
    ApiInterface apiInterface;
    String type = "";
    LinearLayout moblienolayout, emaillayout;
    String mobileOrNEmail = "";
    Button conform_btn;


    SimpleArcLoader mDialog;
    ImageButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initializeView();
    }

    private void initializeView() {
        passwordEt = (EditText) findViewById(R.id.passwordEt);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        conform_btn = (Button) findViewById(R.id.conform_btn);
        mDialog = (SimpleArcLoader) findViewById(R.id.arc_loader);
        back_btn = (ImageButton) findViewById(R.id.back_btn);
        conform_btn.setOnClickListener(this);
//        type = getIntent().getStringExtra("TYPE").toString();
        back_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.conform_btn) {
            try {
                if (passwordEt.getText().toString() == null || "".equals(passwordEt.getText().toString())) {
                    passwordEt.setError("Password Can't be empty");
                    return;
                } else if (confirm_password.getText().toString() == null || "".equals(confirm_password.getText().toString())) {
                    confirm_password.setError("Cofirm Password Can't be empty");
                    return;
                } else if (!confirm_password.getText().toString().equals(passwordEt.getText().toString())) {
                    Toast.makeText(getApplicationContext(),
                            "Confirm Password is not same is password", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    postUserDataRetrofitInit();
                }


            } catch (Exception e) {

            }


        } else if (view.getId() == R.id.back_btn) {
            Intent i = new Intent(ChangePasswordActivity.this, AuthenticationActivity.class);
            startActivity(i);
//            finish();
        }
    }

    private void postUserDataRetrofitInit() {

        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();

        Log.e("Params:SEND_OTP", passwordEt.getText().toString()
                + SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_ID") + "");
        ChangePasswordData sendotp = new ChangePasswordData(
                SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_ID"),
                passwordEt.getText().toString());
        apiInterface = ApiClient.getClient(ApiClient.CHANGE_PASSWORD_URL).create(ApiInterface.class);
        Call<ChangePasswordData> call = apiInterface.changePasswordData(sendotp);
        call.enqueue(new Callback<ChangePasswordData>() {
            @Override
            public void onResponse(Call<ChangePasswordData> call, Response<ChangePasswordData> response) {

                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Log.e("StatusCode:", response.body().getStatusCode() + "");
                if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {
                    Intent i = new Intent(ChangePasswordActivity.this,
                            HomeActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(ChangePasswordActivity.this,
                            response.body().getMessage(),
                            Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<ChangePasswordData> call, Throwable t) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ChangePasswordActivity.this, AuthenticationActivity.class);
        startActivity(i);
    }
}
