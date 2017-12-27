package com.flikster.HomeActivity;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abhishek on 06-11-2017.
 */

public class ApiClient {

    public static final String BASE_URL = "http://apiv3.flikster.com/v3/content-ms/";
    private static Retrofit retrofit = null;

    public static final String BASE_URLL = "http://api.themoviedb.org/3/products";
    public static final String PRODUCT_DATA_URL = "http://apiservice.flikster.com/v3/product-ms/products";
    public static final String MOVIE_STORE_DATA_URL = "http://apiservice.flikster.com/v3/product-ms/products";
    public static final String CELEB_STORE_DATA_URL = "http://apiservice.flikster.com/v3/product-ms/products";
    public static final String DESIGN_DATA_URL = "http://apiservice.flikster.com/v3/designer-ms/designers";
    public static final String BRAND_DATA_URL = "http://apiservice.flikster.com/v3/brand-ms/brands";


    //Login
    public static final String LOGIN_URL = "http://apiservice.flikster.com/v3/user-ms/login/";
    //http://apiservice.flikster.com/v3/user-ms/login

    //Registe
    public static final String SIGNUP_URL = "http://apiservice.flikster.com/v3/user-ms/userReg/";

    //Send Otp
    public static final String SEND_OTP_URL = "http://apiservice.flikster.com/v3/user-ms/forgotpassword/";

    //Verify Otp
    public static final String VERIFY_OTP_URL = "http://apiservice.flikster.com/v3/user-ms/checkOtp/";


    public static Retrofit getClient(String baseURL) {
        retrofit = null;
        if (retrofit == null) {
            Log.e("insdei retrfoit", "inside retroft");
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(new OkHttpClient.Builder()
                            .connectTimeout(30L, TimeUnit.SECONDS)
                            .readTimeout(30L, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        Log.e("lewaving retrfoit", "leaving retroft");
        return retrofit;
    }

    public static Retrofit getClientData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URLL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
