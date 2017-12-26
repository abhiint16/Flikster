package com.flikster.Authentication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.widget.LoginButton;
import com.flikster.Authentication.LoginActivity.LoginWithEmailActivity;
import com.flikster.Authentication.SignUpActivity.SignUpWithEmailActivity;
import com.flikster.Authentication.SignUpActivity.SignUpWithPhoneActivity;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;
import com.flikster.SharedPref.SharedPref;
import com.flikster.Util.SharedPrefsUtil;
import com.google.android.gms.common.SignInButton;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class AuthenticationActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private Button btnLoginPhone, btnLoginEmail;
    private Button keycloak, without_keycloak;
    private TextView tvLoginTermsCond, headertxt;
    SharedPref sharedPref;
    private ImageButton back_btn;
    SignInButton btnLoginGoogle;
    String comingPage = "";
    Button btnLoginFacebook;


    //Gmail Login
    private static final String TAG = AuthenticationActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    private Button btnSignOut, btnRevokeAccess, homeacess;
    private LinearLayout llProfileLayout;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        initializeView();
        gmailView();
    }

    private void gmailView() {
//        btnSignOut = (Button) findViewById(R.id.btn_sign_out);
        btnRevokeAccess = (Button) findViewById(R.id.btn_revoke_access);
        llProfileLayout = (LinearLayout) findViewById(R.id.llProfile);
        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        btnRevokeAccess.setOnClickListener(this);
        // Customizing G+ button
        btnLoginGoogle.setSize(SignInButton.SIZE_STANDARD);
        btnLoginGoogle.setScopes(gso.getScopeArray());


    }

    private void initializeView() {
        sharedPref = new SharedPref(getApplicationContext());
        btnLoginPhone = (Button) findViewById(R.id.btn_login_phone);
        btnLoginEmail = (Button) findViewById(R.id.btn_login_mail);
        btnLoginGoogle = (SignInButton) findViewById(R.id.btn_login_google);
        btnLoginFacebook = (Button) findViewById(R.id.btn_login_facebook);
        tvLoginTermsCond = (TextView) findViewById(R.id.tv_login_terms);
        keycloak = (Button) findViewById(R.id.keycloak);
        headertxt = (TextView) findViewById(R.id.headertxt);
        back_btn = (ImageButton) findViewById(R.id.back_btn);
        without_keycloak = (Button) findViewById(R.id.without_keycloak);
        without_keycloak.setOnClickListener(this);
        keycloak.setOnClickListener(this);
        btnLoginPhone.setOnClickListener(this);
        btnLoginEmail.setOnClickListener(this);
        btnLoginGoogle.setOnClickListener(this);
        btnLoginFacebook.setOnClickListener(this);
        tvLoginTermsCond.setOnClickListener(this);
        back_btn.setOnClickListener(this);

        comingPage = SharedPrefsUtil.getStringPreference(getApplicationContext(), "COMING_PAGE");
        if (comingPage != null && !comingPage.isEmpty()) {
            if (comingPage.equals("SIGNUP")) {
                headertxt.setText("REGISTER");
            } else {
                headertxt.setText("LOGIN");
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login_phone) {
            if (comingPage != null && !comingPage.isEmpty()) {
                if (comingPage.equals("SIGNUP")) {
                    gotoEmailSignUp("phone");
                } else {
                    gotoEmailLogin("phone");
                }
            }

        } else if (view.getId() == R.id.without_keycloak) {
            SharedPrefsUtil.setStringPreference(AuthenticationActivity.this, "IS_LOGGED_IN", "NOT_LOGGED_IN");
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn_login_mail) {
            if (comingPage != null && !comingPage.isEmpty()) {
                if (comingPage.equals("SIGNUP")) {
                    gotoEmailSignUp("email");
                } else {
                    gotoEmailLogin("email");
                }
            }
        } else if (view.getId() == R.id.btn_login_google) {
            gotoGoogleLogin();
        } else if (view.getId() == R.id.btn_login_facebook) {
            gotoFacebookLogin();
        } else if (view.getId() == R.id.tv_login_terms) {
            showTermsConditions();
        } else if (view.getId() == R.id.keycloak) {
            SharedPrefsUtil.setStringPreference(AuthenticationActivity.this, "IS_LOGGED_IN", "LOGGED_IN");
            Toast.makeText(getApplicationContext(), "You're now LoggedIn.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AuthenticationActivity.this, HomeActivity.class);
            startActivity(intent);
            /*if (!KeycloakHelper.isConnected()) {
                Log.e("inside if not cnnect","inside if not cnnect");
                KeycloakHelper.connect(AuthenticationActivity.this, new Callback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("inside inSuccess","insde onsuccess"+data);
                        SharedPrefsUtil.setStringPreference(AuthenticationActivity.this,"IS_LOGGED_IN","LOGGED_IN");
                        Log.e("ckeckforsharedinif",""+sharedPref.isLoggedIn());
                        Toast.makeText(getApplicationContext(), "heaven begins here", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(AuthenticationActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.e("insde onFailure","insied onfilaure"+e);
                        SharedPrefsUtil.setStringPreference(AuthenticationActivity.this,"IS_LOGGED_IN","NOT_LOGGED_IN");
                        Toast.makeText(getApplicationContext(), "hell begins here! Couldn't connect. Try again", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(AuthenticationActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }
                });
            }
            else if(KeycloakHelper.isConnected())
            {
                Intent intent=new Intent(AuthenticationActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        }*/
        } else if (view.getId() == R.id.back_btn) {
            Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AuthenticationActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    /*private void gotoPhoneLogin() {
        startActivity(new Intent(AuthenticationActivity.this, LoginWithMobileNoActivity.class));
    }*/

    private void gotoPhoneSignup() {
        startActivity(new Intent(AuthenticationActivity.this, SignUpWithPhoneActivity.class));
    }

    private void gotoEmailLogin(String type) {
        Intent i = new Intent(AuthenticationActivity.this, LoginWithEmailActivity.class);
        i.putExtra("TYPE", type);
        startActivity(i);
    }

    private void gotoEmailSignUp(String type) {
        Intent i = new Intent(AuthenticationActivity.this, SignUpWithEmailActivity.class);
        i.putExtra("TYPE", type);
        startActivity(i);
    }

    private void gotoGoogleLogin() {
        signIn();
//        Toast.makeText(AuthenticationActivity.this, "Coming Soon", Toast.LENGTH_LONG).show();
    }

    private void gotoFacebookLogin() {
        Toast.makeText(AuthenticationActivity.this, "Coming Soon", Toast.LENGTH_LONG).show();
    }

    private void showTermsConditions() {
        Toast.makeText(AuthenticationActivity.this, "Terms and Conditions will apply", Toast.LENGTH_LONG).show();
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);

            /*txtName.setText(personName);
            txtEmail.setText(email);
            Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);*/

            updateUI(true, email);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false, "");
        }
    }

    private void updateUI(boolean isSignedIn, String email) {
        if (isSignedIn) {
//            btnSignIn.setVisibility(View.GONE);
//            btnSignOut.setVisibility(View.VISIBLE);
            btnRevokeAccess.setVisibility(View.GONE);
            llProfileLayout.setVisibility(View.VISIBLE);
            Intent i = new Intent(AuthenticationActivity.this, HomeActivity.class);
            i.putExtra("GAMIL_ID", email);
            startActivity(i);
//            homeacess.setVisibility(View.VISIBLE);
        } else {
//            btnSignIn.setVisibility(View.VISIBLE);
//            btnSignOut.setVisibility(View.GONE);
//            btnRevokeAccess.setVisibility(View.GONE);
//            llProfileLayout.setVisibility(View.GONE);
//            homeacess.setVisibility(View.GONE);
        }
    }

   /* @Override
    public void onStart()
    {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone())
        {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        }
        else
        {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>()
            {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult)
                {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }*/


}
