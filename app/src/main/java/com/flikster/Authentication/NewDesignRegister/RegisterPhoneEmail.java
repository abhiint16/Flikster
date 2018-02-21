package com.flikster.Authentication.NewDesignRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.flikster.R;

/**
 * Created by abhishek on 21-02-2018.
 */

public class RegisterPhoneEmail extends AppCompatActivity implements View.OnClickListener {

    Button register_send_otp_phone;
    EditText register_phone_email;
    TextView register_terms_condition;
    String type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //See SignUpWithEmailActivity for details
        setContentView(R.layout.register_phone_email);
        initializeView();
        initializeRest();
    }

    private void initializeRest() {
        register_terms_condition.setOnClickListener(this);
        register_send_otp_phone.setOnClickListener(this);
        register_phone_email.setOnClickListener(this);
        if ("phone".equals(type))
        {
            register_phone_email.setHint("Enter Phone Number");
            register_phone_email.setInputType(InputType.TYPE_CLASS_PHONE);
        }else if ("email".equals(type))
        {
            register_phone_email.setHint("Enter E-mail");
        }
    }

    private void initializeView() {
        type=getIntent().getStringExtra("TYPE");
        register_send_otp_phone=(Button)findViewById(R.id.register_send_otp_phone);
        register_phone_email=(EditText)findViewById(R.id.register_phone_email);
        register_terms_condition=(TextView)findViewById(R.id.register_terms_condition);
        register_terms_condition.setText(Html.fromHtml(" <u>Terms & Condition</u>"));
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.register_send_otp_phone)
        {
            if (register_phone_email.getText().toString() == null || "".equals(register_phone_email.getText().toString()))
            {
                register_phone_email.setError(type+" can't be empty");
                return;
            }
            Intent intent=new Intent(RegisterPhoneEmail.this,RegisterOTPActivity.class);
            intent.putExtra("TYPE",type);
            intent.putExtra("PhoneEmail",register_phone_email.getText().toString());
            startActivity(intent);
        }else if (view.getId()==R.id.register_terms_condition)
        {

        }
    }
}
