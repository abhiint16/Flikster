package com.flikster.HomeActivity;

import android.util.Log;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by abhishek on 06-11-2017.
 */

public interface ApiInterface {

    @GET("contents")
    Call<FeedData> getTopRatedMovies();
}
