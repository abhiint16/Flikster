//package com.flikster.Authentication.LoginActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.flikster.Authentication.ResendOtpActivity.LoginMobileOtpActivity;
//import com.flikster.Authentication.ResendOtpActivity.SendOtpWithMobileNoActivity;
//import com.flikster.HomeActivity.ApiClient;
//import com.flikster.HomeActivity.ApiInterface;
//import com.flikster.HomeActivity.HomeActivity;
//import com.flikster.R;
//import com.flikster.Util.SharedPrefsUtil;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class LoginWithMobileNoActivity extends AppCompatActivity implements View.OnClickListener {
//    private Button login_btn;
//    EditText et_mobile_no, passwordEt;
//    ApiInterface apiInterface;
//    TextView forgot_txt;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login_mobile);
//        initializeView();
//    }
//
//    private void initializeView() {
//        login_btn = (Button) findViewById(R.id.btn_mobile_otp_send);
//        et_mobile_no = (EditText) findViewById(R.id.et_mobile_no);
//        passwordEt = (EditText) findViewById(R.id.passwordEt);
//        forgot_txt = (TextView) findViewById(R.id.forgot_txt);
//        login_btn.setOnClickListener(this);
//        forgot_txt.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.btn_mobile_otp_send) {
//            if (et_mobile_no.getText().toString() == null || "".equals(et_mobile_no.getText().toString())) {
//                et_mobile_no.setError("Mobile Can't be empty");
//                return;
//            } else if (passwordEt.getText().toString() == null || "".equals(passwordEt.getText().toString())) {
//                passwordEt.setError("Password Can't be empty");
//                return;
//            }
//            postUserDataRetrofitInit();
//        } else if (view.getId() == R.id.forgot_txt) {
//            Intent i = new Intent(LoginWithMobileNoActivity.this, SendOtpWithMobileNoActivity.class);
//            i.putExtra("MOBILE_NO", et_mobile_no.getText().toString());
////                    bundle.putString("MY_STYLE_TYPE", type);
//            startActivity(i);
//
//            /*if (et_mobile_no.getText().toString() != null && !et_mobile_no.getText().toString().isEmpty()) {
//                if (et_mobile_no.getText().toString().length() == 10) {
//                    Intent i = new Intent(LoginWithMobileNoActivity.this, SendOtpWithMobileNoActivity.class);
//                    i.putExtra("MOBILE_NO", et_mobile_no.getText().toString());
//                    startActivity(i);
//                } else {
//                    et_mobile_no.setError("Enter valid mobile no");
//                    return;
//                }
//            } else {
//                et_mobile_no.setError("Mobile Can't be empty");
//                return;
//            }*/
//
//
//        }
//    }
//
//    private void postUserDataRetrofitInit() {
//        Log.e("inside lognemail", "inside logingemaig");
//        LoginData emailRegisterPostData = new LoginData
//                (et_mobile_no.getText().toString(), passwordEt.getText().toString());
//        apiInterface = ApiClient.getClient
//                (ApiClient.LOGIN_URL)
//                .create(ApiInterface.class);
//        Call<LoginData> call = apiInterface.loginUserData(emailRegisterPostData);
//        call.enqueue(new Callback<LoginData>() {
//            @Override
//            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
//                if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {
//                    Toast.makeText(LoginWithMobileNoActivity.this, "Successfully Login", Toast.LENGTH_LONG).show();
//                    SharedPrefsUtil.setStringPreference(LoginWithMobileNoActivity.this,
//                            "IS_LOGGED_IN", "LOGGED_IN");
//                    Intent intent = new Intent(LoginWithMobileNoActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(LoginWithMobileNoActivity.this, "Login failed", Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginData> call, Throwable t) {
//                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
//            }
//        });
//    }
//}
