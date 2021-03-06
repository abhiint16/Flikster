package com.flikster.HomeActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.CheckoutActivity.CheckoutFragment.CreateUserApiPostData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.WatchOrUnWatchCheckData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.WatchStatusData;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.CreateShareYourStyleData;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.SavestyleData;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.StyleSearchData;
import com.flikster.R;
import com.flikster.Util.Common;
import com.flikster.Util.SharedPrefsUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * Created by abhishek on 24-11-2017.
 */

public class PostRetrofit {
    boolean access = false;
    ApiInterface apiInterface;

    public void postRetrofitMethod(String type, String userId, String entityId, final ImageButton ib_like, final Context context) {
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient(ApiClient.POST_STATUS_URL).create(ApiInterface.class);
        Call<ModelForPostRequest> call = apiInterface.likeItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForPostRequest>() {
            @Override
            public void onResponse(Call<ModelForPostRequest> call, Response<ModelForPostRequest> response) {
                Log.e("insied onResonse ", "insied onrespnse" + call + "bcbbc" + response + "gggg" + response.body());
                Log.e("SUCCESS_LIKE", "insied onrespnse" + call + "bcbbc" + response + "gggg" + response.body());
            }

            @Override
            public void onFailure(Call<ModelForPostRequest> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
//                ib_like.setImageResource(0);
//                ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_icon));
            }
        });
    }

    public void postWatchOrUnWatchRetrofitMethod(String type, String userId, String entityId, final ImageButton ib_like, final Context context) {
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient(ApiClient.POST_WATCH_STATUS_URL).create(ApiInterface.class);
        Call<ModelForPostRequest> call = apiInterface.likeItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForPostRequest>() {
            @Override
            public void onResponse(Call<ModelForPostRequest> call, Response<ModelForPostRequest> response) {
                Log.e("insied onResonse ", "insied onrespnse" + call + "bcbbc" + response + "gggg" + response.body());
                Log.e("SUCCESS_LIKE", "insied onrespnse" + call + "bcbbc" + response + "gggg" + response.body());
            }

            @Override
            public void onFailure(Call<ModelForPostRequest> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
//                ib_like.setImageResource(0);
//                ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_icon));
            }
        });
    }


    public void checkForLike(String type, final String userId, String entityId, final ImageButton ib_like, final Context context) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            return;
        }
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient(ApiClient.POST_STATUS_URL).create(ApiInterface.class);
        Call<ModelForIsLikedPostRequest> call = apiInterface.isLikedItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForIsLikedPostRequest>() {
            @Override
            public void onResponse(Call<ModelForIsLikedPostRequest> call, Response<ModelForIsLikedPostRequest> response) {
//                Log.e("LikeSTATUS", response.body().getData().getCount() + "SUCCEESS");
                try {
                    if (response.body().getData().getCount() != null && !response.body().getData().getCount().isEmpty()) {
                        if (response.body().getData().getCount().equals("1")) {
                            ib_like.setImageResource(0);
                            ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_pink));
                        } else {
                            access = false;
                            ib_like.setImageResource(0);
                            ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_icon));
                        }
                    }
                } catch (Exception e) {
                    Log.e("like count from sever", "null");

                }
            }

            @Override
            public void onFailure(Call<ModelForIsLikedPostRequest> call, Throwable t) {
                Log.e("LikeSTATUS", "Like Screen Failed" + call + "bcbbc" + t);
                access = false;
                ib_like.setImageResource(0);
                ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_icon));

            }
        });
    }

    public void postRetrofitFollowMethod(String type, String userId, String entityId, final Button followBtn, final Context context) {
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient(ApiClient.POST_STATUS_URL).create(ApiInterface.class);
        Call<ModelForPostRequest> call = apiInterface.likeItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForPostRequest>() {
            @Override
            public void onResponse(Call<ModelForPostRequest> call, Response<ModelForPostRequest> response) {
                Log.e("Follow Post Status", "SUCCESS" + "");
            }

            @Override
            public void onFailure(Call<ModelForPostRequest> call, Throwable t) {
                Log.e("Follow Post Status", "FAILED" + "");
            }
        });
    }

    public void checkForFollow(String type, String userId, String entityId, final Button followBtn, final Context context) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            return;
        }
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient(ApiClient.POST_STATUS_URL).create(ApiInterface.class);
        Call<ModelForIsLikedPostRequest> call = apiInterface.isLikedItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForIsLikedPostRequest>() {
            @Override
            public void onResponse(Call<ModelForIsLikedPostRequest> call, Response<ModelForIsLikedPostRequest> response) {
//                Log.e("Follow SUCCESS", response.body().getData().getCount() + "SUCCESS");
                try {
                    if (response.body().getData().getCount().equals("1")) {
                        followBtn.setBackgroundColor(context.getResources().getColor(R.color.transparent));
                        followBtn.setBackground(context.getResources().getDrawable(R.drawable.corner_rounder_pink_follow));
                        followBtn.setText("Following");
                        followBtn.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    } else {
                        followBtn.setBackgroundColor(context.getResources().getColor(R.color.black));
                        followBtn.setBackground(context.getResources().getDrawable(R.drawable.corner_rounded));
                        followBtn.setText("follow");
                        followBtn.setTextColor(context.getResources().getColor(R.color.black));
                    }
                } catch (Exception e) {
                    Log.e("follow", "error in follow count");
                }


            }

            @Override
            public void onFailure(Call<ModelForIsLikedPostRequest> call, Throwable t) {
//                Log.e("Follow failed", "Follow failed" + call + "bcbbc" + t);
                Log.e("Follow FAILED", "FAILED");
                followBtn.setBackgroundColor(context.getResources().getColor(R.color.red));
//                followBtn.setBackground(context.getResources().getDrawable(R.drawable.corner_rounded_red));
                followBtn.setText("FAILED");
                followBtn.setTextColor(context.getResources().getColor(R.color.white));
            }
        });
    }


    public void postRetrofitBookmarkMethod(String type, String userId, String entityId, final ImageButton bookmarkBtn, final Context context) {
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient(ApiClient.POST_STATUS_URL).create(ApiInterface.class);
        Call<ModelForPostRequest> call = apiInterface.likeItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForPostRequest>() {
            @Override
            public void onResponse(Call<ModelForPostRequest> call, Response<ModelForPostRequest> response) {
                //bookmarkBtn.setImageResource(0);
               // bookmarkBtn.setImageResource(R.drawable.bookmark_yellow);
            }

            @Override
            public void onFailure(Call<ModelForPostRequest> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }


    public void checkForBookmark(String type, String userId, String entityId, final ImageButton bookmarkBtn, final Context context) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            return;
        }
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient(ApiClient.POST_STATUS_URL).create(ApiInterface.class);
        Call<ModelForIsLikedPostRequest> call = apiInterface.isLikedItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForIsLikedPostRequest>() {
            @Override
            public void onResponse(Call<ModelForIsLikedPostRequest> call, Response<ModelForIsLikedPostRequest> response) {
                try {
                    if ("1".equals(response.body().getData().getCount().trim())) {
                        bookmarkBtn.setImageResource(0);
                        bookmarkBtn.setImageResource(R.drawable.bookmark_yellow);
                    } else if ("0".equals(response.body().getData().getCount().trim())) {
                        //ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_icon));
                    }
                } catch (Exception e) {
                    Log.e("bookmark from server", "null");
                }


            }

            @Override
            public void onFailure(Call<ModelForIsLikedPostRequest> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }

    public void postRetrofitCommentMethod(String userName, String userId,
                                          String entityId, String commentText, final EditText editText,
                                          final Context context) {
        if (commentText.trim().length() == 0 || commentText == null) {
            Toast.makeText(context, "First Write Something!", Toast.LENGTH_LONG).show();
            editText.setError("write something");
            return;
        }
        ModelForPostCommentRequest modelForPostRequest = new ModelForPostCommentRequest(userName, userId, entityId, commentText);
        apiInterface = ApiClient.getClient(ApiClient.POST_COMMENT_URL).create(ApiInterface.class);
        Call<ModelForPostCommentRequest> call = apiInterface.commentItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForPostCommentRequest>() {
            @Override
            public void onResponse(Call<ModelForPostCommentRequest> call, Response<ModelForPostCommentRequest> response) {
                response.body().getStatusCode();
                if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {
                    Toast.makeText(context, "Comment Successful", Toast.LENGTH_LONG).show();
                    editText.setText("");
                } else {
                    Toast.makeText(context, "Comment Failed", Toast.LENGTH_LONG).show();
                    editText.setText("");
                }


            }

            @Override
            public void onFailure(Call<ModelForPostCommentRequest> call, Throwable t) {
                Toast.makeText(context, "Comment Unsuccessful! Please try again.", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void saveYourStyleAPI(CreateShareYourStyleData allImagesdata,
                                 final String completeProfileStyle,
                                 final Context context) {
        apiInterface = ApiClient.getClient(ApiClient.CREATE_SHARE_YOUR_STYLE_URL)
                .create(ApiInterface.class);
        Call<CreateShareYourStyleData> call = apiInterface.postStyleSave(allImagesdata);
        call.enqueue(new Callback<CreateShareYourStyleData>() {
            @Override
            public void onResponse(Call<CreateShareYourStyleData> call,
                                   Response<CreateShareYourStyleData> response) {
                String responsedata = response.body().getMessage();
                Log.e("SAVED", responsedata + "");
                SharedPrefsUtil.setStringPreference(context, "ImageString", "");
                SharedPrefsUtil.setStringPreference(context, "PRODUCT_IMG", "");
                SharedPrefsUtil.setStringPreference(context, "PRODUCT_IMG_TWO", "");
                SharedPrefsUtil.setStringPreference(context, "PRODUCT_IMG_THREE", "");
                Toast.makeText(context, "Saved Successful", Toast.LENGTH_SHORT).show();
                Common.shareClick(completeProfileStyle, context);

            }

            @Override
            public void onFailure(Call<CreateShareYourStyleData> call, Throwable t) {
                Toast.makeText(context, "Save Failed", Toast.LENGTH_SHORT).show();
                Common.shareClick(completeProfileStyle, context);
            }
        });
    }


    //http://10.0.2.2:3000/api/
    public void uploadImageToServer(RequestBody reqbody, MultipartBody.Part multipart, final Context context) {
        apiInterface = ApiClient.getClient(ApiClient.CREATE_SHARE_YOUR_STYLE_URL)
                .create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.uploadPhoto(reqbody, multipart);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Toast.makeText(context, "Image Upload successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Image upload Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /////Likes Count
    public void checkForLikesCount(String type, String userId, String entityId, final TextView likescounttxt, final Context context) {
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient(ApiClient.POST_STATUS_URL).create(ApiInterface.class);
        Call<ModelForIsLikedPostRequest> call = apiInterface.isLikedItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForIsLikedPostRequest>() {
            @Override
            public void onResponse(Call<ModelForIsLikedPostRequest> call, Response<ModelForIsLikedPostRequest> response) {
                try {
                    if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {
                        if (response.body().getTotalCount() != null && response.body().getTotalCount() != 0) {
                            likescounttxt.setText(String.valueOf(response.body().getTotalCount()) + "");
                        } else {
                            likescounttxt.setText("0");
                        }
                    } else {
                        Toast.makeText(context, "" + response.body().getStatusCode(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.e("follow", "error in follow count");
                }


            }

            @Override
            public void onFailure(Call<ModelForIsLikedPostRequest> call, Throwable t) {
//                Toast.makeText()
                Log.e("Error Likes Count", "Fail to to retrieve data");
            }
        });
    }


    ////Check For Followers Count

    public void checkForFollowersCount(String type, String userId, String entityId, final TextView followercounttxt, final Context context) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            return;
        }
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient(ApiClient.POST_STATUS_URL).create(ApiInterface.class);
        Call<ModelForIsLikedPostRequest> call = apiInterface.isLikedItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForIsLikedPostRequest>() {
            @Override
            public void onResponse(Call<ModelForIsLikedPostRequest> call, Response<ModelForIsLikedPostRequest> response) {
                try {
                    if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {
                        if (response.body().getTotalCount() != null && response.body().getTotalCount() != 0) {
                            followercounttxt.setText(String.valueOf(response.body().getTotalCount()) + "");
                        } else {
                            followercounttxt.setText("0");
                        }
                    } else {
                        Toast.makeText(context, "" + response.body().getStatusCode(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("follow", "error in follow count");
                }


            }

            @Override
            public void onFailure(Call<ModelForIsLikedPostRequest> call, Throwable t) {
//                Toast.makeText()
                Log.e("Error Likes Count", "Fail to to retrieve data");
            }
        });
    }


    //Chech for All Watchs
    public void checkForAllWatchStatus(String entityId, final TextView likescounttxt, final TextView unwatchestxt, final Context context) {
        Log.e("EntityIdMovie", entityId);
        String url = ApiClient.MOVIE_WATCH_STATUS_URL + entityId + "/";
        ApiInterface apiService = ApiClient.getClientData().create(ApiInterface.class);
        Call<WatchStatusData> call = apiService.getWatchStatusdata(url);
        call.enqueue(new Callback<WatchStatusData>() {
            @Override
            public void onResponse(Call<WatchStatusData> call, Response<WatchStatusData> response) {
                try {
                    if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {
                        Log.e("Watches Count", response.body().getWatchCount() + "");
                        if (response.body().getWatchCount() != null && response.body().getWatchCount() != 0) {
                            likescounttxt.setText(String.valueOf(response.body().getWatchCount()) + "");
                        } else {
                            likescounttxt.setText("0");
                        }
                        Log.e("Watches Count", response.body().getWontWatchCount() + "");
                        if (response.body().getWontWatchCount() != null && response.body().getWontWatchCount() != 0) {
                            unwatchestxt.setText(String.valueOf(response.body().getWontWatchCount()) + "");
                        } else {
                            unwatchestxt.setText("0");
                        }
                    } else {
                        Toast.makeText(context, "" + response.body().getStatusCode(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.e("follow", "error in follow count");
                }


            }

            @Override
            public void onFailure(Call<WatchStatusData> call, Throwable t) {
//                Toast.makeText()
                Log.e("Error Likes Count", "Fail to to retrieve data");
            }
        });
    }


    //Is Watch
    public void checkIsWatchLike(final String userId, String entityId, final ImageButton ib_like,
                                 final ImageButton unLike, final Context context) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            return;
        }
        WatchOrUnWatchCheckData modelForPostRequest = new WatchOrUnWatchCheckData(userId, entityId);
        apiInterface = ApiClient.getClient(ApiClient.GET_USER_WATCH_STATUS).create(ApiInterface.class);
        Call<WatchOrUnWatchCheckData> call = apiInterface.isWatchOrNorByUser(modelForPostRequest);
        call.enqueue(new Callback<WatchOrUnWatchCheckData>() {
            @Override
            public void onResponse(Call<WatchOrUnWatchCheckData> call,
                                   Response<WatchOrUnWatchCheckData> response) {
                try {
                    Log.e("ISWATCH_STATUS", response.body().getStatusCode() + "");
                    Log.e("ISWATCH_STATUS_WATCH", response.body().getWatch() + "");
                    Log.e("ISWATCH_STATUS_NOTWATCH", response.body().getWatch() + "");
                    if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {
                        if (response.body().getWatch()) {
                            ib_like.setImageResource(0);
                            ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.likegreensmall));
                        } else {
                            if (response.body().getNotWatch()) {
                                unLike.setImageResource(0);
                                unLike.setImageDrawable(context.getResources().getDrawable(R.drawable.unlikepinksmall));
                            }
                        }
                    }

                } catch (Exception e) {
                    Log.e("like count from sever", "null");

                }
            }

            @Override
            public void onFailure(Call<WatchOrUnWatchCheckData> call, Throwable t) {
                Log.e("LikeSTATUS", "Like Screen Failed" + call + "bcbbc" + t);
                access = false;
                ib_like.setImageResource(0);
                ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_icon));

            }
        });
    }

}