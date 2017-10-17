package com.flikster.Authentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.flikster.R;

public class LoginCreateAccountActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_create_account);
        initializeView();
    }

    private void initializeView() {
        btnSignup = (Button)findViewById(R.id.btn_createac_signup);
        btnSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
