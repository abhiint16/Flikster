package com.flikster.CheckoutActivity.CheckoutFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.AuctionCurrentFragmentAdapter;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.AuctionCurrentOrUpcomingData;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.MyBagActivity.MyBagActivity;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;
import com.leo.simplearcloader.SimpleArcLoader;

import java.util.List;

import in.juspay.godel.ui.JuspayWebView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 12-10-2017.
 */

public class InstamojoWebView extends AppCompatActivity implements View.OnClickListener {
    View view;
    RecyclerView ratingNowShowingRecycler;
    RecyclerView.LayoutManager ratingNowShowingLayoutManager;
    AuctionCurrentFragmentAdapter auctionCurrentFragmentAdapter;
    FragmentManager fragmentManager;
    TextView toolbar_frag_title;
    ImageButton toolbar_back_navigation_btn;

    int Count;
    TextView nodataavailtxt;
    SimpleArcLoader mDialog;
    ApiInterface apiInterface;

    PaymentRequest paymentRequestdata;
    RelativeLayout alldatalayout;
    JuspayWebView instamojoweb;
    PaymentRequest.PaymentRequestSuccesdata PaymentRequestsuccess;
    String UserId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewinstamojo);
        initializeViews();
        initializeRes();
        placeOrderinServer();
    }

    private void initializeRes() {
        toolbar_frag_title.setText("Payment Mode");
        toolbar_back_navigation_btn.setOnClickListener(this);
        //toolbar_frag_multiicons_notification.setVisibility(View.GONE);
        //toolbar_frag_multiicons_cart.setVisibility(View.GONE);
        //toolbar_frag_multiicons_search.setVisibility(View.GONE);
        //toolbar_frag_multiicons_overflow.setVisibility(View.GONE);
    }


    private void initializeViews() {
        toolbar_frag_title = (TextView) findViewById(R.id.toolbar_frag_multiicons_title);
        mDialog = (SimpleArcLoader) findViewById(R.id.arc_loader);
        instamojoweb = (JuspayWebView) findViewById(R.id.juspay_browser_view);

        toolbar_back_navigation_btn = (ImageButton) findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        //toolbar_frag_multiicons_search = (ImageButton) findViewById(R.id.toolbar_frag_multiicons_search);
        //toolbar_frag_multiicons_notification = (ImageButton) findViewById(R.id.toolbar_frag_multiicons_notification);
        //toolbar_frag_multiicons_cart = (ImageButton) findViewById(R.id.toolbar_frag_multiicons_cart);
        //toolbar_frag_multiicons_overflow = (ImageButton) findViewById(R.id.toolbar_frag_multiicons_overflow);
    }


    private void placeOrderinServer() {
        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
       /* String phone = "";
        String email = "";
        String orderId = SharedPrefsUtil.getStringPreference(getApplicationContext(), "ORDER_ID");

        if (SharedPrefsUtil.getStringPreference(getApplicationContext(), "MOBILE_NO")!=null && !SharedPrefsUtil.getStringPreference(getApplicationContext(), "MOBILE_NO").isEmpty()){
            phone = SharedPrefsUtil.getStringPreference(getApplicationContext(), "MOBILE_NO");
        }else {
            phone = "";
        }

        if (SharedPrefsUtil.getStringPreference(getApplicationContext(), "EMAIL_ID")!=null && !SharedPrefsUtil.getStringPreference(getApplicationContext(), "EMAIL_ID").isEmpty()){
            email = SharedPrefsUtil.getStringPreference(getApplicationContext(), "EMAIL_ID");
        }else {
            email = "";
        }
        String buyer = SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_ID");
        String buyer_name = SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_NAME");
        String amount = SharedPrefsUtil.getStringPreference(getApplicationContext(), "PRODUCT_PRICE");
        String purpose = "FLIKSTER";
        String status = "j";
        String send_sms = "false";
        String send_email = "false";
        String sms_status = "Pending";
        String email_status = "Pending";
        String shorturl = null;
        String redirect_url = "http://flikster.com/checkout/"
                + SharedPrefsUtil.getStringPreference(getApplicationContext(), "ORDER_ID");
        ;
        String webhook = "http://www.example.com/webhook/";
        String allow_repeated_payments = "false";*/

        Log.e("priceAmt", SharedPrefsUtil.getStringPreference(getApplicationContext(), "PRODUCT_PRICE") + "Rs");
        Log.e("orderidd", SharedPrefsUtil.getStringPreference(getApplicationContext(), "ORDER_ID") + "ID");
        Log.e("USERNAME", SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_NAME").trim() + "name");
        String orderIdtemp = SharedPrefsUtil.getStringPreference(getApplicationContext(), "ORDER_ID");


        String orderId = orderIdtemp;//"61a404aa-5b8f-43c9-ba5e-43eb24de00fa";// SharedPrefsUtil.getStringPreference(getApplicationContext(), "ORDER_ID");
        String phone = "";
        if (SharedPrefsUtil.getStringPreference(getApplicationContext(), "MOBILE_NO") != null && !SharedPrefsUtil.getStringPreference(getApplicationContext(), "MOBILE_NO").isEmpty()) {
            phone = SharedPrefsUtil.getStringPreference(getApplicationContext(), "MOBILE_NO");
            Log.e("phoneNo", phone);
        } else {
            phone = "";
            Log.e("phoneNo", phone);
        }

        String email = "";
        if (SharedPrefsUtil.getStringPreference(getApplicationContext(), "EMAIL_ID") != null && !SharedPrefsUtil.getStringPreference(getApplicationContext(), "EMAIL_ID").isEmpty()) {
            if (!SharedPrefsUtil.getStringPreference(getApplicationContext(), "EMAIL_ID").equals("undefined")){
                email = SharedPrefsUtil.getStringPreference(getApplicationContext(), "EMAIL_ID");
            }else {
                email = "";
            }

            Log.e("emailId", email);
        } else {
            email = "";
            Log.e("emailId", email);
        }

        String buyer = "Test";
        String buyer_name = SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_NAME").trim();
        String amount = SharedPrefsUtil.getStringPreference(getApplicationContext(), "PRODUCT_PRICE"); //SharedPrefsUtil.getStringPreference(getApplicationContext(), "PRODUCT_PRICE");
        String purpose = "FLIKSTER";
        String status = "j";
        String send_sms = "false";
        String send_email = "false";
        String sms_status = "Pending";
        String email_status = "Pending";
        String shorturl = null;
        String redirect_url = "http://flikster.com/checkout/" + orderIdtemp;
//                + SharedPrefsUtil.getStringPreference(getApplicationContext(), "ORDER_ID");
        String webhook = "http://www.example.com/webhook/";
        String allow_repeated_payments = "false";


        paymentRequestdata = new PaymentRequest(new
                PaymentRequest.PaymentRequestInnerdata(orderId, phone, email, buyer,
                buyer_name, amount, purpose, status,
                send_sms, send_email, sms_status, email_status, shorturl,
                redirect_url, webhook, allow_repeated_payments));
        Log.e("paramsreq", paymentRequestdata.toString());
        apiInterface = ApiClient.getClient(ApiClient.PAYMENT_REQ)
                .create(ApiInterface.class);
        Call<PaymentRequest> call = apiInterface.paymentRequestinServer(paymentRequestdata);
        call.enqueue(new Callback<PaymentRequest>() {
            @Override
            public void onResponse(Call<PaymentRequest> call,
                                   Response<PaymentRequest> response) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Log.e("ResponsePayment", response.body().isSuccess() + "");
                if (response.body().isSuccess()) {
                    PaymentRequestsuccess = response.body().getPayment_request();
                    instamojoweb.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Order Requested", Toast.LENGTH_LONG).show();
                    instamojoweb.getSettings().setJavaScriptEnabled(true);
                    instamojoweb.loadUrl(PaymentRequestsuccess.getLongurl() + "");

                    //instamojoweb

                } else {
                    Toast.makeText(getApplicationContext(), "Order Requested failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PaymentRequest> call, Throwable t) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Toast.makeText(getApplicationContext(), "Order Requested failed", Toast.LENGTH_LONG).show();
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar_back_navigation_btn) {
            finish();
        } else {
            Intent intent = new Intent(getApplicationContext(), MyBagActivity.class);
            if (SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_ID") != null
                    && !SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_ID").isEmpty()) {
                UserId = SharedPrefsUtil.getStringPreference(getApplicationContext(),
                        "USER_ID");
                Log.e("LoginUserId", UserId);
                intent.putExtra("userId", UserId);
            } else {
                intent.putExtra("userId", "abhiint");
            }
            startActivity(intent);
        }
    }
}
