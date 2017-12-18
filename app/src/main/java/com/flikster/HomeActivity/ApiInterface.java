package com.flikster.HomeActivity;

import android.util.Log;

import com.flikster.AllCommentActivity.CommentsData;
import com.flikster.CheckoutActivity.CheckoutFragment.CreateUserApiPostData;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityData;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryData;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryRecommendedRecyclerData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.RecommendedMoviesData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.RecommendedProductData;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.CreateShareYourStyleData;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.MyStyleAdapter;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.SavestyleData;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.StyleSearchData;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsData;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductDetailsDataToSend;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreData;
import com.flikster.MyBagActivity.MyBagData;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by abhishek on 06-11-2017.
 */

public interface ApiInterface {

    @GET("http://apiservice-ec.flikster.com/contents/_search")
    Call<FeedData> getTopRatedMovies(@Query("pretty") Boolean s,
                                     @Query("sort") String d,
                                     @Query("size") Integer i,
                                     @Query("q") String c);

    @GET
    Call<MovieData> getMovieData(@Url String url);

    @GET
    Call<CelebrityData> getCelebrityData(@Url String url);

    @GET
    Call<FeedData> getNewsData(@Url String url);

    @GET
    Call<FeedData> getGalleryData(@Url String url);

    @GET
    Call<RecommendedMoviesData> getRecommendedMoviesData(@Url String url);

    @GET
    Call<RecommendedProductData> getRecommendedProductData(@Url String url);

    @GET("http://apiservice-ec.flikster.com/contents/_search")
    Call<FeedData> getMovieFeedData(@Query("pretty") Boolean s,
                                    @Query("size") Integer d,
                                    @Query("q") String c);

    @GET
    Call<CommentsData> getAllComments(@Url String url);

    @GET
    Call<AllStoreData> getAllStore(@Url String url);

    @GET
    Call<ShopByVideoData> getShopByVideo(@Url String url);

    @GET
    Call<WidgetData> getWidgetData(@Url String url);

    @GET
    Call<MyBagData> getMyBagData(@Url String url);

    @GET("http://apiservice-ec.flikster.com/products/_search")
    Call<AllStoreData> getCelebMovieStoreData(@Query("pretty") Boolean s,
                                              @Query("size") Integer d,
                                              @Query("q") String c);
    ////////////////////////////////////////////////////////////
    //POST Request

    @POST("http://apiv3.flikster.com/v3/likes-ms/postCardStatus")
    Call<ModelForPostRequest> likeItem(@Body ModelForPostRequest modelForPostRequest);

    @POST("http://apiv3.flikster.com/v3/likes-ms/isPostStatus")
    Call<ModelForIsLikedPostRequest> isLikedItem(@Body ModelForPostRequest modelForPostRequest);

    @POST("http://apiv3.flikster.com/v3/comments-ms/postComment")
    Call<ModelForPostCommentRequest> commentItem(@Body ModelForPostCommentRequest modelForPostRequest);

    @GET("http://apiv3-es.flikster.com/contents/_search")
    Call<GalleryData> getAllGalleryData(@Query("pretty") Boolean s,
                                        @Query("sort") String sort,
                                        @Query("size") Integer i,
                                        @Query("q") String c);

    /*@Multipart
    @POST("http://apiv3.flikster.com/v3/cart-ms/createCart")
    Call<ProductDetailsDataToSend> postSendToCartData(@Part("productId") String productId,
                                                      @Part("size") String size, @Part("userId") String userId, @Part("productDetails") ProductDetailsDataToSend productDetailsDataToSend);*/

    @POST("http://apiv3.flikster.com/v3/cart-ms/createCart")
    Call<ProductDetailsDataToSend> postSendToCartData(@Body ProductDetailsDataToSend productDetailsDataToSend);

    @POST("http://apiv3.flikster.com/v3/orders-ms/createOrder")
    Call<CreateUserApiPostData> postSendToCraeteUser(@Body CreateUserApiPostData createUserApiPostData);

    @GET
    Call<StyleSearchData> getStyletypeData(@Url String url);

    @POST("http://apiv3.flikster.com/v3/share-your-style-ms/createShareYourStyle")
    Call<CreateShareYourStyleData> postStyleSave(@Body CreateShareYourStyleData savestyledata);
}
