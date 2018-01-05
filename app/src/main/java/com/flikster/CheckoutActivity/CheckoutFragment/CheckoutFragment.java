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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.CheckoutActivity.AddressFragment.AddressFragment;
import com.flikster.CheckoutActivity.PaymentFragment.PaymentFragment;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductDetailsDataToSend;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;
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
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_checkout, container, false);
        initializeViews();
        initializeRest();
        return view;
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
            Log.e("inside onclick bototbtn", "inside bototn ctn clk");
            //hitCreateUserApi();
            instaMojoInit();
            /*getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_mybag_continue_onclick_container,new PaymentFragment())
                    .addToBackStack("")
                    .commit();*/
        }
    }

    private void hitCreateUserApi() {
        Log.e("inside onclick bototbtn", "inside hitcreateuserapi");
        List<CreateUserApiPostData.ProductData> productDatas = new ArrayList<CreateUserApiPostData.ProductData>();
        productDatas.add(new CreateUserApiPostData.ProductData(productId, productTitle, productSlug,
                profilePic, color,
                price, size, quantity));

        //51dd3ee9-7481-477f-adaa-08264d2d3c1e
//        Log.e("params","productId" + productId, "productTitle"+ productTitle+"productSlug"+ productTitle);

        CreateUserApiPostData createUserApiPostData = new CreateUserApiPostData(SharedPrefsUtil.getStringPreference(getContext(),"USER_ID"),
                productDatas,
                new CreateUserApiPostData.ShippingAddress(name, mobile, address, city, state, pin, landmark));

        apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/orders-ms/createOrder/")
                .create(ApiInterface.class);
        Call<CreateUserApiPostData> call = apiInterface.postSendToCraeteUser(createUserApiPostData);
        call.enqueue(new Callback<CreateUserApiPostData>() {
            @Override
            public void onResponse(Call<CreateUserApiPostData> call, Response<CreateUserApiPostData> response) {
                if (response.body().getStatusCode() == 200){
                    Log.e("success", "insied onrespnse" + call + "bcbbc" + response + "gggg" + response.body().getStatusCode());
                    Log.e("success", "insied onrespnse" + call + "bcbbc" + response + "gggg" + response.body().getMessage());
                    Toast.makeText(getActivity(), "Order has been created", Toast.LENGTH_SHORT).show();
                    instaMojoInit();
                }else {
                    Toast.makeText(getActivity(), "Error creating Order!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CreateUserApiPostData> call, Throwable t) {
                Toast.makeText(getActivity(), "Error creating Order!", Toast.LENGTH_SHORT).show();
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t.getCause() + "" + t.getMessage() + "" + t.getLocalizedMessage());
            }
        });
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
        /*RequestBody client_id = new FormBody.Builder()
                .add("client_id", "TIIVMSyhXS6OTc4SQbXRdqeyZZIfTs3FTe5q0ITF")
                .build();
        client_id = new FormBody.Builder()
                .add("client_id", "TIIVMSyhXS6OTc4SQbXRdqeyZZIfTs3FTe5q0ITF")
                .build();*/

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

}
