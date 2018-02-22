package com.flikster.Authentication.NewDesignRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;

/**
 * Created by abhishek on 21-02-2018.
 */

public class RegisterCreateProfile extends AppCompatActivity implements View.OnClickListener {

    Button register_btn;
    TextView register_create_profile_skip;
    ImageButton back_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_create_profile);
        initializeView();
        initializeRest();
    }

    private void initializeRest() {
        register_btn.setOnClickListener(this);
        register_create_profile_skip.setOnClickListener(this);
        back_btn.setOnClickListener(this);
    }

    private void initializeView() {
        register_btn=(Button)findViewById(R.id.register_btn);
        back_btn=(ImageButton)findViewById(R.id.back_btn);
        register_create_profile_skip=(TextView)findViewById(R.id.register_create_profile_skip);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.register_btn)
        {
            Intent intent=new Intent(RegisterCreateProfile.this, HomeActivity.class);
            startActivity(intent);
        }else if (view.getId()==R.id.register_create_profile_skip)
        {
            Intent intent=new Intent(RegisterCreateProfile.this, HomeActivity.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.back_btn)
        {
            Intent intent=new Intent(RegisterCreateProfile.this,RegisterSetPasswordActivity.class);
            intent.putExtra("TYPE",getIntent().getStringExtra("TYPE"));
            intent.putExtra("PhoneEmail",getIntent().getStringExtra("PhoneEmail"));
            startActivity(intent);
        }
    }
}
