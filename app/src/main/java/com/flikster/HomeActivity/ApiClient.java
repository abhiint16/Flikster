package com.flikster.HomeActivity;

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


    public static Retrofit getClient(String baseURL) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
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
