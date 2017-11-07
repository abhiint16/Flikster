package com.flikster.HomeActivity;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abhishek on 06-11-2017.
 */

public class ApiClient {

    public static final String BASE_URL = "http://apiv3.flikster.com/v3/content-ms/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        Log.e("dddddd","ddddddddddd");
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
