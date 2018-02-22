package com.flikster.Authentication.NewDesignRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.R;

/**
 * Created by abhishek on 21-02-2018.
 */

public class RegisterSetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    TextView register_set_password_header;
    Button conform_btn;
    ImageButton back_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initializeView();
        initializeRest();
    }

    private void initializeRest() {
        register_set_password_header.setText("Set Password");
        conform_btn.setOnClickListener(this);
        back_btn.setOnClickListener(this);
    }

    private void initializeView() {
        back_btn=(ImageButton)findViewById(R.id.back_btn);
        register_set_password_header=(TextView)findViewById(R.id.register_set_password_header);
        conform_btn=(Button)findViewById(R.id.conform_btn);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.conform_btn)
        {
            Intent intent=new Intent(RegisterSetPasswordActivity.this,RegisterCreateProfile.class);
            intent.putExtra("TYPE",getIntent().getStringExtra("TYPE"));
            intent.putExtra("PhoneEmail",getIntent().getStringExtra("PhoneEmail"));
            startActivity(intent);
        }
        else if(view.getId()==R.id.back_btn)
        {
            Intent intent=new Intent(RegisterSetPasswordActivity.this,RegisterOTPActivity.class);
            intent.putExtra("TYPE",getIntent().getStringExtra("TYPE"));
            intent.putExtra("PhoneEmail",getIntent().getStringExtra("PhoneEmail"));
            startActivity(intent);
        }
    }
}
