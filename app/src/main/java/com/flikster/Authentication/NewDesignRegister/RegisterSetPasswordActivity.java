package com.flikster.Authentication.NewDesignRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.R;

/**
 * Created by abhishek on 21-02-2018.
 */

public class RegisterSetPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    TextView register_set_password_header;
    Button conform_btn;
    ImageButton back_btn;
    EditText passwordEt,confirm_password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initializeView();
        initializeRest();
    }

    private void initializeRest() {
        passwordEt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD| InputType.TYPE_CLASS_TEXT);
        confirm_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD| InputType.TYPE_CLASS_TEXT);
        register_set_password_header.setText("Set Password");
        conform_btn.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        passwordEt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        if(motionEvent.getRawX() >= (passwordEt.getRight() - passwordEt.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            if (passwordEt.getText().toString().length()!=0)
                            {
                                if (passwordEt.getInputType()==(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT))
                                {
                                    passwordEt.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
                                    passwordEt.setSelection(passwordEt.getText().length());//for cursor to be at the end point when inputtype chnages
                                    passwordEt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye, 0);
                                }
                                else if (passwordEt.getInputType()==(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL))
                                {
                                    passwordEt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                                    passwordEt.setSelection(passwordEt.getText().length());//for cursor to be at the end point when inputtype chnages
                                    passwordEt.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.eye_not, 0);
                                }
                            }
                            return true;
                        }
                    }return false;
                }
            });
        confirm_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if(motionEvent.getRawX() >= (confirm_password.getRight() - confirm_password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if (confirm_password.getText().toString().length()!=0)
                        {
                            if (confirm_password.getInputType()==(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT))
                            {
                                confirm_password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
                                confirm_password.setSelection(confirm_password.getText().length());//for cursor to be at the end point when inputtype chnages
                                confirm_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye, 0);
                            }
                            else if (confirm_password.getInputType()==(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL))
                            {
                                confirm_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                                confirm_password.setSelection(confirm_password.getText().length());//for cursor to be at the end point when inputtype chnages
                                confirm_password.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.eye_not, 0);
                            }
                        }
                        return true;
                    }
                }return false;
            }
            });
        }


    private void initializeView() {
        back_btn=(ImageButton)findViewById(R.id.back_btn);
        register_set_password_header=(TextView)findViewById(R.id.register_set_password_header);
        conform_btn=(Button)findViewById(R.id.conform_btn);
        passwordEt=(EditText)findViewById(R.id.passwordEt);
        confirm_password=(EditText)findViewById(R.id.confirm_password);
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
