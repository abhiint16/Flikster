package com.flikster.HomeActivity;

import android.util.Log;

import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityData;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryRecommendedRecyclerData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.RecommendedMoviesData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.RecommendedProductData;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by abhishek on 06-11-2017.
 */

public interface ApiInterface {

    @GET("contents")
    Call<FeedData> getTopRatedMovies();

    @GET
    Call<MovieData> getMovieData(@Url String url);

    @GET
    Call<CelebrityData> getCelebrityData(@Url String url);

    @GET
    Call<NewsData> getNewsData(@Url String url);

    @GET
    Call<GalleryRecommendedRecyclerData> getGalleryData(@Url String url);

    @GET
    Call<RecommendedMoviesData> getRecommendedMoviesData(@Url String url);

    @GET
    Call<RecommendedProductData> getRecommendedProductData(@Url String url);
}
