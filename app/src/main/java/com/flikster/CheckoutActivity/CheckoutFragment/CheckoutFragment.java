package com.flikster.CheckoutActivity.CheckoutFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.Authentication.ChangePasswordActivity.ChangePasswordActivity;
import com.flikster.Authentication.ChangePasswordActivity.ChangePasswordData;
import com.flikster.CheckoutActivity.AddressFragment.AddressFragment;
import com.flikster.CheckoutActivity.PaymentFragment.PaymentFragment;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionPlaceBidData;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductDetailsDataToSend;
import com.flikster.HomeActivity.FashionFragment.FashionLandingFragment.FashionLandingFragment;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;
import com.google.gson.Gson;
import com.instamojo.android.activities.PaymentDetailsActivity;
import com.instamojo.android.callbacks.OrderRequestCallBack;
import com.instamojo.android.helpers.Constants;
import com.instamojo.android.models.Errors;
import com.instamojo.android.models.Order;
import com.instamojo.android.network.Request;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import in.juspay.godel.ui.JuspayWebView;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by abhishek on 25-10-2017.
 */

public class CheckoutFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManager;
    CheckoutAdapter checkoutAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    ImageButton toolbar_frag_multiicons_back_navigation, addressTabIcon, checkoutTabIcon/*,paymentTabIcon*/;
    TextView toolbar_frag_multiicons_title, addressTabText, checkoutTabText/*,paymentTabText*/;
    Button fragment_checkout_bottom_btn;
    String name, mobile, address, city, pin, state, landmark, additionalMobile;
    String productId;
    String productSlug;
    String productTitle;
    String userId;
    String size;
    String color;
    String profilePic;
    String price;
    String quantity;
    AddressUserData addressUserData;
    ApiInterface apiInterface;
    private String accessToken = null;
    String instaMojoURL = "https://test.instamojo.com/";
    PaymentRequest paymentRequestdata;
    RelativeLayout alldatalayout;
    JuspayWebView instamojoweb;
    SimpleArcLoader mDialog;
    PaymentRequest.PaymentRequestSuccesdata PaymentRequestsuccess;
    CreateUserApiPostData createUserApiPostData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_checkout, container, false);
        initializeViews();
        initializeRest();
        dataAccess();
//        placeOrderinServer();
        return view;
    }

    private void dataAccess() {
        Log.e("inside onclick bototbtn", "inside hitcreateuserapi");
        List<CreateUserApiPostData.ProductData> productDatas = new ArrayList<CreateUserApiPostData.ProductData>();
        productDatas.add(new CreateUserApiPostData.ProductData(productId, productTitle, productSlug,
                profilePic, color,
                price, size, quantity));
        SharedPrefsUtil.setStringPreference(getContext(), "ORDER_ID", productId);
        SharedPrefsUtil.setStringPreference(getContext(), "PRODUCT_PRICE", price);
        Gson gson = new Gson();
        String orderJson = gson.toJson(productDatas);
        Log.e("orderJson", orderJson.toString());
        String productorderJson = gson.toJson(productDatas);
        Log.e("Jsondata", productorderJson);
        List<CreateUserApiPostData.ShippingAddress> shippingAddress = new ArrayList<CreateUserApiPostData.ShippingAddress>();
        shippingAddress.add(new CreateUserApiPostData.ShippingAddress(name,
                mobile, address, city, state, pin, landmark));

        String postShippingAddrsdata = gson.toJson(productDatas);
        Log.e("Jsondata", postShippingAddrsdata);

        CreateUserApiPostData createUserApiPostData = new CreateUserApiPostData(
                SharedPrefsUtil.getStringPreference(getContext(), "USER_ID"),
                productDatas,
                new CreateUserApiPostData.ShippingAddress(name, mobile, address,
                        city, state, pin, landmark));
        apiInterface = ApiClient.getClient(ApiClient.BASE_URL)
                .create(ApiInterface.class);
    }

    private void initializeRest() {
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
        checkoutAdapter = new CheckoutAdapter(getFragmentManager(), getActivity(), name, address, city, state, pin, mobile, landmark, additionalMobile,
                productId, productSlug, productTitle, userId, size, color, profilePic, price, quantity, addressUserData);
        fragment_common_recyclerview_recycler.setAdapter(checkoutAdapter);
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        fragment_checkout_bottom_btn.setOnClickListener(this);
        toolbar_frag_multiicons_title.setText("Order Details");
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        toolbar_frag_multiicons_toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_back_navigation = (ImageButton) toolbar_frag_multiicons_toolbar.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_title = (TextView) toolbar_frag_multiicons_toolbar.findViewById(R.id.toolbar_frag_multiicons_title);
        fragment_checkout_bottom_btn = (Button) view.findViewById(R.id.fragment_checkout_bottom_btn);
        addressTabIcon = (ImageButton) getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_address_linear_imgbtn);
        checkoutTabIcon = (ImageButton) getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_checkout_linear_imgbtn);
        // paymentTabIcon=(ImageButton)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_payment_linear_imgbtn);
        addressTabText = (TextView) getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_address_linear_name);
        checkoutTabText = (TextView) getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_checkout_linear_name);
        //paymentTabText=(TextView)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_payment_linear_name);
        alldatalayout = (RelativeLayout) view.findViewById(R.id.alldatalayout);
        instamojoweb = (JuspayWebView) view.findViewById(R.id.juspay_browser_view);
        mDialog = (SimpleArcLoader) view.findViewById(R.id.arc_loader);
    }

    @Override
    public void onResume() {
        super.onResume();
        addressTabIcon.setImageResource(R.drawable.address);
        addressTabText.setTextColor(getActivity().getResources().getColor(R.color.black));
        checkoutTabIcon.setImageResource(R.drawable.checkout_pink);
        checkoutTabText.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
        /*paymentTabIcon.setImageResource(R.drawable.payment);
        paymentTabText.setTextColor(getActivity().getResources().getColor(R.color.black));*/
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
            addressUserData.checkoutToAddress(name, mobile, address, city, pin, state, landmark, additionalMobile, new AddressFragment());
        } else if (view.getId() == R.id.fragment_checkout_bottom_btn) {
            if (SharedPrefsUtil.getStringPreference(getContext(), "USER_ID")!=null && !SharedPrefsUtil.getStringPreference(getContext(), "USER_ID").isEmpty()){
                hitCreateUserApi();
            }else {
                Toast.makeText(getContext(), "Please login", Toast.LENGTH_SHORT).show();
            }

//            instaMojoInit();
            /*getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_mybag_continue_onclick_container,new PaymentFragment())
                    .addToBackStack("")
                    .commit();*/
        }
    }

    private void hitCreateUserApi() {
        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        /*Log.e("inside onclick bototbtn", "inside hitcreateuserapi");
        List<CreateUserApiPostData.ProductData> productDatas = new ArrayList<CreateUserApiPostData.ProductData>();
        productDatas.add(new CreateUserApiPostData.ProductData(productId, productTitle, productSlug,
                profilePic, color,
                price, size, quantity));
        SharedPrefsUtil.setStringPreference(getContext(), "ORDER_ID", productId);
        SharedPrefsUtil.setStringPreference(getContext(), "PRODUCT_PRICE", price);
        Gson gson = new Gson();
        String orderJson = gson.toJson(productDatas);
        Log.e("orderJson", orderJson.toString());
        String productorderJson = gson.toJson(productDatas);
        Log.e("Jsondata", productorderJson);
        List<CreateUserApiPostData.ShippingAddress> shippingAddress = new ArrayList<CreateUserApiPostData.ShippingAddress>();
        shippingAddress.add(new CreateUserApiPostData.ShippingAddress(name,
                mobile, address, city, state, pin, landmark));

        String postShippingAddrsdata = gson.toJson(productDatas);
        Log.e("Jsondata", postShippingAddrsdata);

        CreateUserApiPostData createUserApiPostData = new CreateUserApiPostData(
                SharedPrefsUtil.getStringPreference(getContext(), "USER_ID"),
                productDatas,
                new CreateUserApiPostData.ShippingAddress(name, mobile, address,
                        city, state, pin, landmark));
        apiInterface = ApiClient.getClient(ApiClient.BASE_URL)
                .create(ApiInterface.class);*/
        Call<CreateUserApiPostData> call = apiInterface.postSendToCraeteUser(createUserApiPostData);
        call.enqueue(new Callback<CreateUserApiPostData>() {
            @Override
            public void onResponse(Call<CreateUserApiPostData> call, Response<CreateUserApiPostData> response) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                if (response.body().getStatusCode() == 200) {
                    Toast.makeText(getActivity(), "Order has been created", Toast.LENGTH_SHORT).show();
                    instamojoPage();
                } else {
                    Toast.makeText(getActivity(), "Error creating Order!", Toast.LENGTH_SHORT).show();
                    instamojoPage();
                }

            }

            @Override
            public void onFailure(Call<CreateUserApiPostData> call, Throwable t) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Toast.makeText(getActivity(), "Error creating Order!", Toast.LENGTH_SHORT).show();
                instamojoPage();
            }
        });
    }

    private void instamojoPage() {
        Intent i = new Intent(getContext(), InstamojoWebView.class);
        startActivity(i);
    }

    private void instaMojoInit() {
        fetchTokenAndTransactionID();
    }


    private void fetchTokenAndTransactionID() {
        Toast.makeText(getActivity(), "Wait....fetching for you", Toast.LENGTH_LONG).show();
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = getHttpURLBuilder()
                .addPathSegment("create")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("env", instaMojoURL.toLowerCase())
                .build();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Failed to fetch the Order Tokens", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String responseString;
                String errorMessage = null;
                String transactionID = null;
                responseString = response.body().string();
                response.body().close();
                try {
                    JSONObject responseObject = new JSONObject(responseString);
                    if (responseObject.has("error")) {
                        errorMessage = responseObject.getString("error");
                    } else {
                        accessToken = responseObject.getString("access_token");
                        Log.e("AccessToken", accessToken + "");
                        transactionID = responseObject.getString("transaction_id");
                    }
                } catch (JSONException e) {
                    errorMessage = "Failed to fetch Order tokens";
                }

                final String finalErrorMessage = errorMessage;
                final String finalTransactionID = transactionID;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (finalErrorMessage != null) {
                            Toast.makeText(getActivity(), finalErrorMessage, Toast.LENGTH_SHORT).show();
                            return;
                        }

                        createOrder(accessToken, finalTransactionID);
                    }
                });

            }
        });

    }


    private void createOrder(String accessToken, String transactionID) {
        String name = this.name;
        final String email = "abhishekint16@gmail.com";
        String phone = this.mobile;
        String amount = this.price;
        String description = this.productTitle;

        //Create the Order
        Order order = new Order(accessToken, transactionID, name, email, phone, amount, description);

        //set webhook
        order.setWebhook("http://your.server.com/webhook/");

        //Validate the Order
        /*if (!order.isValid()) {
            //oops order validation failed. Pinpoint the issue(s).

            if (!order.isValidName()) {
                nameBox.setError("Buyer name is invalid");
            }

            if (!order.isValidEmail()) {
                emailBox.setError("Buyer email is invalid");
            }

            if (!order.isValidPhone()) {
                phoneBox.setError("Buyer phone is invalid");
            }

            if (!order.isValidAmount()) {
                amountBox.setError("Amount is invalid or has more than two decimal places");
            }

            if (!order.isValidDescription()) {
                descriptionBox.setError("Description is invalid");
            }

            if (!order.isValidTransactionID()) {
                showToast("Transaction is Invalid");
            }

            if (!order.isValidRedirectURL()) {
                showToast("Redirection URL is invalid");
            }

            if (!order.isValidWebhook()) {
                showToast("Webhook URL is invalid");
            }

            return;
        }*/

        //Validation is successful. Proceed
        //dialog.show();
        Toast.makeText(getActivity(), "creating order...wait", Toast.LENGTH_SHORT).show();
        Request request = new Request(order, new OrderRequestCallBack() {
            @Override
            public void onFinish(final Order order, final Exception error) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //dialog.dismiss();
                        if (error != null) {
                            if (error instanceof Errors.ConnectionError) {
                                Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                            } else if (error instanceof Errors.ServerError) {
                                Toast.makeText(getActivity(), "Server Error. Try again", Toast.LENGTH_SHORT).show();
                            } else if (error instanceof Errors.AuthenticationError) {
                                Toast.makeText(getActivity(), "Access token is invalid or expired. Please Update the token!!", Toast.LENGTH_SHORT).show();
                            } else if (error instanceof Errors.ValidationError) {
                                // Cast object to validation to pinpoint the issue
                                Errors.ValidationError validationError = (Errors.ValidationError) error;

                                if (!validationError.isValidTransactionID()) {
                                    Toast.makeText(getActivity(), "Transaction ID is not Unique", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if (!validationError.isValidRedirectURL()) {
                                    Toast.makeText(getActivity(), "Redirect url is invalid", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if (!validationError.isValidWebhook()) {
                                    Toast.makeText(getActivity(), "Webhook url is invalid", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if (!validationError.isValidPhone()) {
                                    Toast.makeText(getActivity(), "Buyer's Phone Number is invalid/empty", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if (!validationError.isValidEmail()) {
                                    Toast.makeText(getActivity(), "Buyer's Email is invalid/empty", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if (!validationError.isValidAmount()) {
                                    Toast.makeText(getActivity(), "Amount is either less than Rs.9 or has more than two decimal places", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if (!validationError.isValidName()) {
                                    Toast.makeText(getActivity(), "Buyer's Name is required", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else {
                                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                        startPreCreatedUI(order);
                    }
                });
            }
        });

        request.execute();
    }

    private void startPreCreatedUI(Order order) {
        //Using Pre created UI
        Intent intent = new Intent(getActivity(), PaymentDetailsActivity.class);
        intent.putExtra(Constants.ORDER, order);
        startActivityForResult(intent, Constants.REQUEST_CODE);
    }

    private HttpUrl.Builder getHttpURLBuilder() {
        return new HttpUrl.Builder()
                .scheme("https")
                .host("sample-sdk-server.instamojo.com");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE && data != null) {
            String orderID = data.getStringExtra(Constants.ORDER_ID);
            String transactionID = data.getStringExtra(Constants.TRANSACTION_ID);
            String paymentID = data.getStringExtra(Constants.PAYMENT_ID);

            // Check transactionID, orderID, and orderID for null before using them to check the Payment status.
            if (transactionID != null || paymentID != null) {
                checkPaymentStatus(transactionID, orderID);
            } else {
                Toast.makeText(getActivity(), "Oops!! Payment was cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Will check for the transaction status of a particular Transaction
     *
     * @param transactionID Unique identifier of a transaction ID
     */
    private void checkPaymentStatus(final String transactionID, final String orderID) {
        if (accessToken == null || (transactionID == null && orderID == null)) {
            return;
        }

        Toast.makeText(getActivity(), "checking transaction status", Toast.LENGTH_SHORT).show();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = getHttpURLBuilder();
        builder.addPathSegment("status");
        if (transactionID != null) {
            builder.addQueryParameter("transaction_id", transactionID);
        } else {
            builder.addQueryParameter("id", orderID);
        }
        builder.addQueryParameter("env", instaMojoURL.toLowerCase());
        HttpUrl url = builder.build();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Failed to fetch the Transaction status", Toast.LENGTH_SHORT);
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String responseString = response.body().string();
                response.body().close();
                String status = null;
                String paymentID = null;
                String amount = null;
                String errorMessage = null;

                try {
                    JSONObject responseObject = new JSONObject(responseString);
                    JSONObject payment = responseObject.getJSONArray("payments").getJSONObject(0);
                    status = payment.getString("status");
                    paymentID = payment.getString("id");
                    amount = responseObject.getString("amount");

                } catch (JSONException e) {
                    errorMessage = "Failed to fetch the Transaction status";
                }

                final String finalStatus = status;
                final String finalErrorMessage = errorMessage;
                final String finalPaymentID = paymentID;
                final String finalAmount = amount;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (finalStatus == null) {
                            Toast.makeText(getActivity(), finalErrorMessage, Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (!finalStatus.equalsIgnoreCase("successful")) {
                            Toast.makeText(getActivity(), "Transaction still pending", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(getActivity(), "Transaction Successful for id - " + finalPaymentID, Toast.LENGTH_SHORT).show();
                        //refundTheAmount(transactionID, finalAmount);
                    }
                });
            }
        });

    }


    public void userData(String name, String mobile, String address, String city, String pin, String state, String landmark,
                         String additionalMobile) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.city = city;
        this.pin = pin;
        this.state = state;
        this.landmark = landmark;
        this.additionalMobile = additionalMobile;
    }

    public void productData(String productId, String productSlug, String productTitle, String userId, String size,
                            String color, String profilePic, String price, String quantity) {
        this.productId = productId;
        this.productSlug = productSlug;
        this.productTitle = productTitle;
        this.userId = userId;
        this.size = size;
        this.color = color;
        this.profilePic = profilePic;
        this.price = price;
        this.quantity = quantity;
    }

    public interface AddressUserData {
        void checkoutToAddress(String name, String mobileNo, String address, String city, String pinCode, String state, String landmark, String additionMobile, Fragment fragment);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        addressUserData = (AddressUserData) activity;
    }


    private void instamojoProductionUrlAccess() {
        InstamojoData sendotp = new InstamojoData("client_credentials", "TIIVMSyhXS6OTc4SQbXRdqeyZZIfTs3FTe5q0ITF", "lhE3pt2ZDxYqH8OGwa4l1KkRixwSihSpavLKowVux3hJRli7QUYH0MJm86gCWjM0YmwDdenRLQlQRt9Nsn3tdUegQAxAQdx2CZKVr8Rq8aMyKN5IAVFPAUYCrRIfDr2w");
        apiInterface = ApiClient.getClient(ApiClient.CHANGE_PASSWORD_URL).create(ApiInterface.class);
        Call<InstamojoData> call = apiInterface.instamojoDataCall(sendotp);
        call.enqueue(new Callback<InstamojoData>() {
            @Override
            public void onResponse(Call<InstamojoData> call, Response<InstamojoData> response) {
//                Log.e("StatusCode:", response.body().getStatusCode() + "");
                if (response.body().getAccess_token() != null) {
                    Toast.makeText(getApplicationContext(),
                            response.body().getAccess_token(),
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "failed to get token",
                            Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<InstamojoData> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }


    private void placeOrderinServer() {
        String orderId = "61a404aa-5b8f-43c9-ba5e-43eb24de00fa";
        String phone = "999999999";
        String email = "rohan@flikster.com";
        String buyer = "Test";
        String buyer_name = "Test";
        String amount = "4599.00";
        String purpose = "FLIKSTER";
        String status = "jj";
        String send_sms = "false";
        String send_email = "false";
        String sms_status = "Pending";
        String email_status = "Pending";
        String shorturl = null;
        String redirect_url = "http://flikster.com/checkout/61a404aa-5b8f-43c9-ba5e-43eb24de00fa";
        String webhook = "http://www.example.com/webhook/";
        String allow_repeated_payments = "false";


        paymentRequestdata = new PaymentRequest(new
                PaymentRequest.PaymentRequestInnerdata(orderId, phone, email, buyer, buyer_name, amount, purpose, status,
                send_sms, send_email, sms_status, email_status, shorturl,
                redirect_url, webhook, allow_repeated_payments));
        apiInterface = ApiClient.getClient(ApiClient.PAYMENT_REQ)
                .create(ApiInterface.class);
        Call<PaymentRequest> call = apiInterface.paymentRequestinServer(paymentRequestdata);
        call.enqueue(new Callback<PaymentRequest>() {
            @Override
            public void onResponse(Call<PaymentRequest> call,
                                   Response<PaymentRequest> response) {
                Log.e("ResponsePayment", response.body().isSuccess() + "");
                if (response.body().isSuccess()) {
                    PaymentRequestsuccess = response.body().getPayment_request();
                    alldatalayout.setVisibility(View.GONE);
                    instamojoweb.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Order Requested", Toast.LENGTH_LONG).show();
                    instamojoweb.getSettings().setJavaScriptEnabled(true);
                    instamojoweb.loadUrl(PaymentRequestsuccess.getLongurl() + "");

                    //instamojoweb

                } else {
                    Toast.makeText(getContext(), "Order Requested failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PaymentRequest> call, Throwable t) {
                Toast.makeText(getContext(), "Order Requested failed", Toast.LENGTH_LONG).show();
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }

}
