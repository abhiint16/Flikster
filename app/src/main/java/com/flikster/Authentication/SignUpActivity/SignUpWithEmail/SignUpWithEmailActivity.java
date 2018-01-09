package com.flikster.Authentication.SignUpActivity.SignUpWithEmail;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.Authentication.AuthenticationActivity;
import com.flikster.Authentication.OtpAndResendOtpActivity.OtpActivity;
import com.flikster.Authentication.SignUpActivity.SignUpWithPhoneNo.EmailRegisterPostData;
import com.flikster.Authentication.SignUpActivity.SignUpWithPhoneNo.MobileOrEmailRegisterCheckData;
import com.flikster.Authentication.SignUpActivity.SignUpWithPhoneNo.PhoneRegisterPostData;
import com.flikster.Authentication.SignUpActivity.SignUpWithPhoneNo.RegisterPostStatus;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;
import com.flikster.Util.Common;
import com.flikster.Util.SharedPrefsUtil;
import com.flikster.permission.DangerousPermResponseCallBack;
import com.flikster.permission.DangerousPermissionResponse;
import com.flikster.permission.DangerousPermissionUtils;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpWithEmailActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private Button register_btn;
    EditText register_first_name, register_last_name, et_emailid, et_mobile_no, register_password, register_confirm_password;
    ApiInterface apiInterface;
    TextView register_main_title;
    private ImageButton back_btn;
    private Button btn_account_male, btn_account_female;
    String genderstr;
    String CLICK_EVENT;
    LinearLayout emaillayout, moblienolayout;
    String emailOrMobile;
    String undefinetext;
    PhoneRegisterPostData emailRegisterPostData;

    //Camera thing
    private static int CAMERA_REQUES_CODEE = 101;
    boolean cameracaptured = false;
    final int ACTIVITY_SELECT_IMAGE = 2;
    private static final int IMG_SELECT = 777;
    Activity activity;

    ImageView iv_user_pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        initializeView();
        initializeRest();
    }

    private void initializeRest() {
        CLICK_EVENT = getIntent().getStringExtra("TYPE");
        Log.e("CLICK_EVENT", CLICK_EVENT + "Data");
        if (CLICK_EVENT.equals("email")) {
            et_emailid.setVisibility(View.VISIBLE);
        } else {
            moblienolayout.setVisibility(View.VISIBLE);
            et_emailid.setVisibility(View.GONE);
            emaillayout.setVisibility(View.GONE);
        }

        if (SharedPrefsUtil.getStringPreference(getApplicationContext(), "ProfileImage")
                != null && !SharedPrefsUtil.getStringPreference(getApplicationContext(), "ProfileImage").isEmpty()) {
            Bitmap profilebitmap = Common.StringToBitMap(SharedPrefsUtil.getStringPreference(getApplicationContext(), "ProfileImage"));
            iv_user_pic.setScaleType(ImageView.ScaleType.FIT_XY);
            iv_user_pic.setImageBitmap(profilebitmap);
        }
    }

    private void initializeView() {
        register_btn = (Button) findViewById(R.id.register_btn);
        register_first_name = (EditText) findViewById(R.id.register_first_name);
        register_last_name = (EditText) findViewById(R.id.register_last_name);

        emaillayout = (LinearLayout) findViewById(R.id.emaillayout);
        et_emailid = (EditText) findViewById(R.id.et_emailid);

        moblienolayout = (LinearLayout) findViewById(R.id.moblienolayout);
        et_mobile_no = (EditText) findViewById(R.id.et_mobile_no);

        et_emailid.setInputType(View.AUTOFILL_TYPE_TEXT);

        register_password = (EditText) findViewById(R.id.register_password);
        register_main_title = (TextView) findViewById(R.id.register_main_title);
//        register_main_title.setText("Register");
        register_confirm_password = (EditText) findViewById(R.id.register_confirm_password);
        register_btn.setOnClickListener(this);

        iv_user_pic = (ImageView) findViewById(R.id.iv_user_pic);

        btn_account_male = (Button) findViewById(R.id.btn_account_male);
        btn_account_female = (Button) findViewById(R.id.btn_account_female);

        btn_account_male.setOnClickListener(this);
        btn_account_female.setOnClickListener(this);
        back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(this);
        iv_user_pic.setOnClickListener(this);
        //SharedPrefsUtil.getStringPreference(getApplicationContext(), "PERFORM_FORGOT");
        SharedPrefsUtil.setStringPreference(getApplicationContext(), "PERFORM_FORGOT", null);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.register_btn) {

            if (register_first_name.getText().toString() == null || "".equals(register_first_name.getText().toString())) {
                register_first_name.setError("First Name Can't be empty");
                return;
            }
//            else if (register_last_name.getText().toString() == null || "".equals(register_last_name.getText().toString())) {
//                register_last_name.setError("Last Name Can't be empty");
//                return;
//            }
            else if (CLICK_EVENT.equals("email")) {
                if (et_emailid.getText().toString() == null || "".equals(et_emailid.getText().toString())) {
                    et_emailid.setError("Email Can't be empty");
                    return;
                } else if (!Common.emailValidator(et_emailid.getText().toString())) {
                    et_emailid.setError("Enter valid Emailid");
                    return;
                }
            } else if (!CLICK_EVENT.equals("email")) {
                if (et_mobile_no.getText().toString() == null || "".equals(et_mobile_no.getText().toString())) {
                    et_mobile_no.setError("Mobile Can't be empty");
                    return;
                } else {
                    if (et_mobile_no.getText().toString().length() != 10) {
                        et_mobile_no.setError("Enter Valid mobile No");
                        return;
                    }
                }
            } else if (register_password.getText().toString() == null || "".equals(register_password.getText().toString())) {
                register_password.setError("Password Can't be empty");
                return;
            } else if (register_confirm_password.getText().toString() == null || "".equals(register_confirm_password.getText().toString())) {
                register_confirm_password.setError("Confirm Password Can't be empty");
                return;
            } else if (!register_password.getText().toString().equals(register_confirm_password.getText().toString())) {
                register_confirm_password.setError("Confirm Password is not same is password");
                return;
            }
//            postUserDataRetrofitInit();
            postUserDataServerInit();
        } else if (view.getId() == R.id.btn_account_male) {
            genderstr = "male";
            btn_account_male.setTextColor(getResources().getColor(R.color.white));
            btn_account_male.setBackgroundColor(getResources().getColor(R.color.gender_active_color));

            Drawable img = getApplicationContext().getResources().getDrawable( R.drawable.maleactivesmall);
            Drawable imgWoman = getApplicationContext().getResources().getDrawable( R.drawable.femaleunactivesmall);
            btn_account_male.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
            btn_account_female.setCompoundDrawablesWithIntrinsicBounds( imgWoman, null, null, null);

            btn_account_female.setTextColor(getResources().getColor(R.color.grey_txt_label));

//            btn_account_female.setBackgroundColor(getResources().getColor(R.color.colorImageBackgroundGrey));
            btn_account_female.setBackground(getResources().getDrawable(R.drawable.gray_rectange_border));
        } else if (view.getId() == R.id.btn_account_female) {
            genderstr = "female";
            btn_account_male.setTextColor(getResources().getColor(R.color.grey_txt_label));
//            btn_account_male.setBackgroundColor(getResources().getColor(R.color.colorImageBackgroundGrey));
            btn_account_male.setBackground(getResources().getDrawable(R.drawable.gray_rectange_border));
            btn_account_female.setTextColor(getResources().getColor(R.color.white));
            btn_account_female.setCompoundDrawables(getResources().getDrawable(R.drawable.womanpic), null, null, null);
            btn_account_female.setBackgroundColor(getResources().getColor(R.color.gender_active_color));

            Drawable img = getApplicationContext().getResources().getDrawable( R.drawable.maleunactivesmall);
            Drawable imgWoman = getApplicationContext().getResources().getDrawable( R.drawable.femaleactivesmall);
            btn_account_male.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
            btn_account_female.setCompoundDrawablesWithIntrinsicBounds( imgWoman, null, null, null);

        } else if (view.getId() == R.id.back_btn) {
            Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUpWithEmailActivity.this, AuthenticationActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.iv_user_pic) {
            openCameraClickDialog();
        }
    }

    private void postUserDataServerInit() {
        if (CLICK_EVENT.equals("email")) {
            emailOrMobile = et_emailid.getText().toString();
            emailRegisterPostData = new PhoneRegisterPostData(register_first_name.getText().toString(),
                    emailOrMobile,
                    "undefined",
                    register_password.getText().toString(),
                    "user", genderstr);
        } else {
            emailOrMobile = et_mobile_no.getText().toString();
            emailRegisterPostData = new PhoneRegisterPostData(register_first_name.getText().toString(),
                    "undefined", emailOrMobile,
                    register_password.getText().toString(),
                    "user", genderstr);
        }
        apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/user-ms/userReg/")
                .create(ApiInterface.class);
        Call<RegisterPostStatus> call = apiInterface.emailOrPhoneRegisterUserData(emailRegisterPostData);
        call.enqueue(new Callback<RegisterPostStatus>() {
            @Override
            public void onResponse(Call<RegisterPostStatus> call,
                                   Response<RegisterPostStatus> response) {
                Log.e("StatusCode", response.body().getStatusCode() + "");
                Log.e("otpStatus", response.body().getOtpStatus() + "");
                if (CLICK_EVENT.equals("email")) {
                    if (response.body().getStatusCode() == 200) {
                        Toast.makeText(SignUpWithEmailActivity.this, "Register Successfully", Toast.LENGTH_LONG).show();
                        Toast.makeText(SignUpWithEmailActivity.this, "OTP sent to register email", Toast.LENGTH_LONG).show();
                        SharedPrefsUtil.setStringPreference(SignUpWithEmailActivity.this, "IS_LOGGED_IN", "LOGGED_IN");
                        Intent intent = new Intent(SignUpWithEmailActivity.this, OtpActivity.class);
                        intent.putExtra("TYPE", "email");
                        intent.putExtra("TYPE_DATA", et_emailid.getText().toString());
                        intent.putExtra("OTP_ID", response.body().getId());
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignUpWithEmailActivity.this, "Email already exists", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (response.body().getStatusCode() == 200) {
                        Toast.makeText(SignUpWithEmailActivity.this, "Register Successfully", Toast.LENGTH_LONG).show();
                        Toast.makeText(SignUpWithEmailActivity.this, "OTP sent to register mobile", Toast.LENGTH_LONG).show();
                        SharedPrefsUtil.setStringPreference(getApplicationContext(), "IS_LOGGED_IN", "LOGGED_IN");
                        Intent intent = new Intent(SignUpWithEmailActivity.this,
                                OtpActivity.class);
                        intent.putExtra("TYPE", "mobile");
                        intent.putExtra("TYPE_DATA", et_mobile_no.getText().toString());
                        intent.putExtra("OTP_ID", response.body().getId());
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignUpWithEmailActivity.this, "Mobile Number already exists" + "Please login ", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<RegisterPostStatus> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }

    private void postUserDataRetrofitInit() {
        Log.e("inside lognemail", "inside logingemaig");
        EmailRegisterPostData emailRegisterPostData = new EmailRegisterPostData(register_first_name.getText().toString(),
                register_last_name.getText().toString(), et_emailid.getText().toString(),
                register_password.getText().toString());
        apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/user-ms/registration/").create(ApiInterface.class);
        Call<MobileOrEmailRegisterCheckData> call = apiInterface.emailRegisterUserData(emailRegisterPostData);
        call.enqueue(new Callback<MobileOrEmailRegisterCheckData>() {
            @Override
            public void onResponse(Call<MobileOrEmailRegisterCheckData> call,
                                   Response<MobileOrEmailRegisterCheckData> response) {
                Toast.makeText(SignUpWithEmailActivity.this, "Login Completed", Toast.LENGTH_LONG).show();
                SharedPrefsUtil.setStringPreference(SignUpWithEmailActivity.this, "IS_LOGGED_IN", "LOGGED_IN");
                Intent intent = new Intent(SignUpWithEmailActivity.this, HomeActivity.class);
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
        if (view.getId() == R.id.register_mobile_no) {
            if (b == false) {
                apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/user-ms/checkEmail/").create(ApiInterface.class);
                Call<MobileOrEmailRegisterCheckData> call = apiInterface.checkForMobileOrEmailAlreadyThere("http://apiservice.flikster.com/v3/user-ms/checkEmail/"
                        + et_mobile_no.getText().toString());
                call.enqueue(new Callback<MobileOrEmailRegisterCheckData>() {
                    @Override
                    public void onResponse(Call<MobileOrEmailRegisterCheckData> call, Response<MobileOrEmailRegisterCheckData> response) {
                        if (response.body().getCount() != 0) {
                            et_mobile_no.setError("Email Id already present." +
                                    " Either login or change EmailId");
                            Toast.makeText(SignUpWithEmailActivity.this, "Email Id already present. Either login or change EmailId", Toast.LENGTH_SHORT).show();
                            register_btn.setEnabled(false);
                        } else if (response.body().getCount() == 0) {
                            Toast.makeText(SignUpWithEmailActivity.this, "New Email Found.Carry on.", Toast.LENGTH_LONG).show();
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

    private void openCameraClickDialog() {
        final Dialog dialog = new Dialog(SignUpWithEmailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_camera_click);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        LinearLayout dialog_camera_click_click_photo = (LinearLayout) dialog.findViewById(R.id.dialog_camera_click_click_photo);
        LinearLayout dialog_camera_click_select_gallery = (LinearLayout) dialog.findViewById(R.id.dialog_camera_click_select_gallery);
        dialog_camera_click_select_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);*/
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, IMG_SELECT);
                dialog.dismiss();
            }
        });
        dialog_camera_click_click_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraAccessPermission(CAMERA_REQUES_CODEE);
                dialog.dismiss();
            }
        });
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.translucent)));
        dialog.show();
    }

    //Camera Access
    private void cameraAccessPermission(int requestCode) {
        DangerousPermissionUtils.getPermission(getApplicationContext(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, requestCode)
                .enqueue(new DangerousPermResponseCallBack() {
                    @Override
                    public void onComplete(final DangerousPermissionResponse permissionResponse) {
                        if (permissionResponse.isGranted()) {
                            if (permissionResponse.getRequestCode() == CAMERA_REQUES_CODEE) {
                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat
                                        .checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                cameracaptured = true;
                                openCamera();
                            }
                        }
                    }
                });
    }

    private void openCamera() {
        SharedPrefsUtil.setStringPreference(getApplicationContext(), "ACCESS_FRAGMENT_CAPTURE", "ENABLE");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1235);
    }


    ///////////Camera related Code
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1235) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                profileImageSet(bitmap);
            } else {
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == IMG_SELECT && resultCode == RESULT_OK && data != null) {
            try {
                Uri path = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                profileImageSet(bitmap);
            } catch (Exception e) {
            }
        }
    }

    private void profileImageSet(Bitmap bitmap) {
        SharedPrefsUtil.setStringPreference(getApplicationContext(), "ProfileImage", Common.BitMapToString(bitmap));
        iv_user_pic.setScaleType(ImageView.ScaleType.FIT_XY);
        iv_user_pic.setImageBitmap(bitmap);
    }
}
