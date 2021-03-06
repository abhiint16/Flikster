package com.flikster.HomeActivity;

import com.flikster.AllCommentActivity.CommentsData;
import com.flikster.Authentication.ChangePasswordActivity.ChangePasswordData;
import com.flikster.Authentication.LoginActivity.LoginResponseData;
import com.flikster.Authentication.OtpAndResendOtpActivity.SendOTPData;
import com.flikster.Authentication.OtpAndResendOtpActivity.VerifyOTPData;
import com.flikster.Authentication.SignUpActivity.SignUpWithPhoneNo.EmailRegisterPostData;
import com.flikster.Authentication.LoginActivity.LoginData;
import com.flikster.Authentication.SignUpActivity.SignUpWithPhoneNo.MobileOrEmailRegisterCheckData;
import com.flikster.Authentication.SignUpActivity.SignUpWithPhoneNo.PhoneRegisterPostData;
import com.flikster.Authentication.SignUpActivity.SignUpWithPhoneNo.RegisterPostStatus;
import com.flikster.Authentication.SignUpActivity.SignupWithGmailOrFBData;
import com.flikster.CheckoutActivity.CheckoutFragment.CreateUserApiPostData;
import com.flikster.CheckoutActivity.CheckoutFragment.InstamojoData;
import com.flikster.CheckoutActivity.CheckoutFragment.PaymentRequest;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionPlaceBidData;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.AuctionCurrentOrUpcomingData;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.OnGoingBidData;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebBioImagesData;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityData;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryData;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryDataFromImage;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.RecommendedMoviesData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.RecommendedProductData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.WatchOrUnWatchCheckData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.WatchStatusData;
import com.flikster.HomeActivity.CommonFragments.MyAccountFragment.UserProfile.MyOrderData;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.CreateShareYourStyleData;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.StyleSearchData;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductDetailsDataToSend;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreData;
import com.flikster.HomeActivity.SearchViewFragment.SearchGalleryData;
import com.flikster.HomeActivity.SearchViewFragment.SearchProductOnClickData;
import com.flikster.MyBagActivity.MyBagData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

    @GET("http://apiservice-ec.flikster.com/contents/_search")
    Call<FeedData> getTopRatedMovies(@Query("pretty") Boolean s,
                                     @Query("sort") String d,
                                     @Query("size") Integer i,
                                     @Query("from") Integer m,
                                     @Query("q") String c);

    @GET
    Call<FeedData> getTopRatedMovies(@Url String url);

    @GET
    Call<MyOrderData> getMyOrderInAccount(@Url String url);

    @GET
    Call<MovieData> getMovieData(@Url String url);

    @GET
    Call<CelebrityData> getCelebrityData(@Url String url);

    @GET
    Call<GalleryDataFromImage> getGalleryDataFromImage(@Url String url);

    @GET
    Call<SearchGalleryData> getSearchGalleryData(@Url String url);

    @GET
    Call<SearchProductOnClickData> getProductData(@Url String url);

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
                                    @Query("from") Integer from,
                                    @Query("q") String c);

    @GET
    Call<CommentsData> getAllComments(@Url String url);

    @GET
    Call<AuctionCurrentOrUpcomingData> getAuctionData(@Url String url);

    @GET
    Call<OnGoingBidData> getOngoingBid(@Url String url);

    @GET
    Call<AllStoreData> getAllStore(@Url String url);

    @GET
    Call<WatchStatusData> getWatchStatusdata(@Url String url);

    @GET
    Call<ShopByVideoData> getShopByVideo(@Url String url);

    @GET
    Call<WidgetData> getWidgetData(@Url String url);

    @GET
    Call<MyBagData> getMyBagData(@Url String url);

    @GET("http://apiservice-ec.flikster.com/products/_search")
    Call<AllStoreData> getCelebMovieStoreData(@Query("pretty") Boolean s,
                                              @Query("size") Integer d,
                                              @Query("from") Integer from,
                                              @Query("q") String c);
    @GET
    Call<ModelForPostRequest> removeItemFromBag(@Url String url);
    //getIntent().getStringExtra("userId")
    ////////////////////////////////////////////////////////////
    //POST Request

    @POST(ApiClient.POST_CARD_STATUS_URL)
    Call<ModelForPostRequest> likeItem(@Body ModelForPostRequest modelForPostRequest);

    @POST(ApiClient.POST_STATUS_URL)
    Call<ModelForIsLikedPostRequest> isLikedItem(@Body ModelForPostRequest modelForPostRequest);

    @POST(ApiClient.GET_USER_WATCH_STATUS)
    Call<WatchOrUnWatchCheckData> isWatchOrNorByUser(@Body WatchOrUnWatchCheckData modelForPostRequest);

    @POST(ApiClient.POST_COMMENT_URL)
    Call<ModelForPostCommentRequest> commentItem(@Body ModelForPostCommentRequest modelForPostRequest);

    @GET("http://apiservice-es.flikster.com/contents/_search")
    Call<GalleryData> getAllGalleryData(@Query("pretty") Boolean s,
                                        @Query("sort") String sort,
                                        @Query("size") Integer i,
                                        @Query("q") String c);

    @GET
    Call<MobileOrEmailRegisterCheckData> checkForMobileOrEmailAlreadyThere(@Url String url);
    /*@Multipart
    @POST("http://apiv3.flikster.com/v3/cart-ms/createCart")
    Call<ProductDetailsDataToSend> postSendToCartData(@Part("productId") String productId,
                                                      @Part("size") String size, @Part("userId") String userId, @Part("productDetails") ProductDetailsDataToSend productDetailsDataToSend);*/

    @POST("http://apiservice.flikster.com/v3/cart-ms/createCart")
    Call<ProductDetailsDataToSend> postSendToCartData(@Body ProductDetailsDataToSend productDetailsDataToSend);

    @POST("http://apiservice.flikster.com/v3/orders-ms/createOrder/")
    Call<CreateUserApiPostData> postSendToCraeteUser(@Body CreateUserApiPostData createUserApiPostData);

    @GET
    Call<StyleSearchData> getStyletypeData(@Url String url);

    @POST("http://apiservice.flikster.com/v3/share-your-style-ms/createShareYourStyle")
    Call<CreateShareYourStyleData> postStyleSave(@Body CreateShareYourStyleData savestyledata);

    @POST("http://apiservice.flikster.com/v3/search-ms/collectionsByCeleb")
    Call<CelebBioImagesData> postForCelebImageBySlug(@Body CelebBioImagesData celebBioImagesData);

    @POST("http://apiservice.flikster.com/v3/user-ms/registration")
    Call<MobileOrEmailRegisterCheckData> emailRegisterUserData(@Body EmailRegisterPostData emailRegisterPostData);


    //user register
    @POST("http://apiservice.flikster.com/v3/user-ms/userReg")
    Call<RegisterPostStatus> emailOrPhoneRegisterUserData(@Body PhoneRegisterPostData emailRegisterPostData);


    @POST(ApiClient.SIGNIN_FBORGMAIL_URL)
    Call<SignupWithGmailOrFBData> signInWithFbOrGmail(@Body SignupWithGmailOrFBData emailRegisterPostData);

    @POST(ApiClient.LOGIN_URL)
    Call<LoginResponseData> loginUserData(@Body LoginData emailRegisterPostData);

    //Send OTP
    @POST(ApiClient.SEND_OTP_URL)
    Call<SendOTPData> sendOtpData(@Body SendOTPData emailRegisterPostData);

    //Check otp
    @POST(ApiClient.VERIFY_OTP_URL)
    Call<VerifyOTPData> verifyOtpData(@Body VerifyOTPData emailRegisterPostData);


    //Check otp
    @POST(ApiClient.CHANGE_PASSWORD_URL)
    Call<ChangePasswordData> changePasswordData(@Body ChangePasswordData emailRegisterPostData);

    //Check otp
    @POST("/oauth2/token/")
    Call<InstamojoData> instamojoDataCall(@Body InstamojoData emailRegisterPostData);

    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadPhoto(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part photo);

    @POST(ApiClient.PLACE_BID_URL)
    Call<AuctionPlaceBidData> auctionPlaceBidinServer(@Body AuctionPlaceBidData auctionPlaceBidData);

    @POST("http://apiservice.flikster.com/v3/search-ms/globalSearch")
    Call<GlobalSearchGetData> getGlobalSearchData(@Body GlobalSearchPostData globalSearchPostData);

    @POST(ApiClient.PAYMENT_REQ)
    Call<PaymentRequest> paymentRequestinServer(@Body PaymentRequest paymentRequest);

}
