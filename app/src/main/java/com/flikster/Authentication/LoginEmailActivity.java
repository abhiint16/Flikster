package com.flikster.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginEmailActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private Button register_btn;
    EditText register_first_name,register_last_name,register_mobile_no,register_password,register_confirm_password;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        initializeView();
    }

    private void initializeView() {
        register_btn = (Button)findViewById(R.id.register_btn);
        register_first_name=(EditText)findViewById(R.id.register_first_name);
        register_last_name=(EditText)findViewById(R.id.register_last_name);
        register_mobile_no=(EditText)findViewById(R.id.register_mobile_no);
        register_mobile_no.setHint("Enter Email Id");
        register_mobile_no.setInputType(View.AUTOFILL_TYPE_TEXT);
        register_mobile_no.setOnFocusChangeListener(this);
        register_password=(EditText)findViewById(R.id.register_password);
        register_confirm_password=(EditText)findViewById(R.id.register_confirm_password);
        register_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.register_btn)
        {
            if (register_first_name.getText().toString()==null||"".equals(register_first_name.getText().toString()))
            {
                register_first_name.setError("First Name Can't be empty");
                return;
            }
            else if (register_last_name.getText().toString()==null||"".equals(register_last_name.getText().toString()))
            {
                register_last_name.setError("Last Name Can't be empty");
                return;
            }
            else if (register_mobile_no.getText().toString()==null||"".equals(register_mobile_no.getText().toString()))
            {
                register_mobile_no.setError("Mobile Can't be empty");
                return;
            }
            else if (register_password.getText().toString()==null||"".equals(register_password.getText().toString()))
            {
                register_password.setError("Password Can't be empty");
                return;
            }
            else if (register_confirm_password.getText().toString()==null||"".equals(register_confirm_password.getText().toString()))
            {
                register_confirm_password.setError("Confirm Password Can't be empty");
                return;
            }
            /*else if (!register_password.equals(register_confirm_password))
            {
                register_confirm_password.setError("Confirm Password is not same is password");
                return;
            }*/

            postUserDataRetrofitInit();
        }
    }

    private void postUserDataRetrofitInit() {
        Log.e("inside lognemail","inside logingemaig");
        EmailRegisterPostData emailRegisterPostData=new EmailRegisterPostData(register_first_name.getText().toString(),
                register_last_name.getText().toString(),register_mobile_no.getText().toString(),
                register_password.getText().toString());
        apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/user-ms/registration/").create(ApiInterface.class);
        Call<MobileOrEmailRegisterCheckData> call = apiInterface.emailRegisterUserData(emailRegisterPostData);
        call.enqueue(new Callback<MobileOrEmailRegisterCheckData>() {
            @Override
            public void onResponse(Call<MobileOrEmailRegisterCheckData> call, Response<MobileOrEmailRegisterCheckData> response) {
                Toast.makeText(LoginEmailActivity.this,"Login Completed",Toast.LENGTH_LONG).show();
                SharedPrefsUtil.setStringPreference(LoginEmailActivity.this,"IS_LOGGED_IN","LOGGED_IN");
                Intent intent=new Intent(LoginEmailActivity.this,HomeActivity.class);
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
        if (view.getId()==R.id.register_mobile_no)
        {
            if (b==false)
            {
                apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/user-ms/checkEmail/").create(ApiInterface.class);
                Call<MobileOrEmailRegisterCheckData> call = apiInterface.checkForMobileOrEmailAlreadyThere("http://apiservice.flikster.com/v3/user-ms/checkEmail/"+register_mobile_no.getText().toString());
                call.enqueue(new Callback<MobileOrEmailRegisterCheckData>() {
                    @Override
                    public void onResponse(Call<MobileOrEmailRegisterCheckData> call, Response<MobileOrEmailRegisterCheckData> response) {
                       if (response.body().getCount()!=0)
                       {
                           register_mobile_no.setError("This Email is already present in the Database. Please Login.");
                           register_btn.setActivated(false);
                       }
                       else if (response.body().getCount()==0)
                       {
                           Toast.makeText(LoginEmailActivity.this,"Email not there.Register",Toast.LENGTH_LONG).show();
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
