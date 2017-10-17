package com.flikster.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.flikster.R;

public class LoginCreateAccountActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSignup;
    private ImageView ivOpenCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_create_account);
        initializeView();
    }

    private void initializeView() {
        btnSignup = (Button)findViewById(R.id.btn_createac_signup);
        btnSignup.setOnClickListener(this);
        ivOpenCamera = (ImageView)findViewById(R.id.iv_user_pic);
        ivOpenCamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_createac_signup)
            createSignup();
        else if (view.getId() == R.id.iv_user_pic)
            takeCameraPic();
    }

    private void takeCameraPic() {
        Toast.makeText(LoginCreateAccountActivity.this, "Camera initializing...", Toast.LENGTH_LONG).show();
    }

    private void createSignup() {
        startActivity(new Intent(LoginCreateAccountActivity.this, LoginMobileActivity.class));
    }
}
