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

    Button register_btn,btn_account_male,btn_account_female;
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
        btn_account_male.setOnClickListener(this);
        btn_account_female.setOnClickListener(this);
    }

    private void initializeView() {
        register_btn=(Button)findViewById(R.id.register_btn);
        back_btn=(ImageButton)findViewById(R.id.back_btn);
        btn_account_female=(Button)findViewById(R.id.btn_account_female);
        btn_account_male=(Button)findViewById(R.id.btn_account_male);
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
        else if (view.getId()==R.id.btn_account_female)
        {
            btn_account_female.setCompoundDrawablesWithIntrinsicBounds( R.drawable.register_create_account_female_white, 0, 0, 0);
            btn_account_male.setCompoundDrawablesWithIntrinsicBounds( R.drawable.register_create_account_male_grey, 0, 0, 0);
            btn_account_female.setTextColor(getResources().getColor(R.color.white));
            btn_account_male.setTextColor(getResources().getColor(R.color.grey_mid));
            btn_account_female.setBackgroundColor(getResources().getColor(R.color.gender_active_color));
            btn_account_male.setBackground(getResources().getDrawable(R.drawable.gray_rectange_border));
        }
        else if (view.getId()==R.id.btn_account_male)
        {
            btn_account_female.setCompoundDrawablesWithIntrinsicBounds( R.drawable.register_create_account_female_grey, 0, 0, 0);
            btn_account_male.setCompoundDrawablesWithIntrinsicBounds( R.drawable.register_create_account_male_white, 0, 0, 0);
            btn_account_female.setTextColor(getResources().getColor(R.color.grey_mid));
            btn_account_male.setTextColor(getResources().getColor(R.color.white));
            btn_account_female.setBackground(getResources().getDrawable(R.drawable.gray_rectange_border));
            btn_account_male.setBackgroundColor(getResources().getColor(R.color.gender_active_color));
        }
    }
}
