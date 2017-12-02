package com.flikster.HomeActivity;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 24-11-2017.
 */

public class PostRetrofit {
    boolean access = false;
    ApiInterface apiInterface;

    public void postRetrofitMethod(String type, String userId, String entityId, final ImageButton ib_like, final Context context) {
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/likes-ms/postCardStatus").create(ApiInterface.class);
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
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/likes-ms/isPostStatus").create(ApiInterface.class);
        Call<ModelForIsLikedPostRequest> call = apiInterface.isLikedItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForIsLikedPostRequest>() {
            @Override
            public void onResponse(Call<ModelForIsLikedPostRequest> call, Response<ModelForIsLikedPostRequest> response) {
                Log.e("LikeSTATUS", response.body().getData().getCount() + "SUCCEESS");
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
            }

            @Override
            public void onFailure(Call<ModelForIsLikedPostRequest> call, Throwable t) {
                Log.e("LikeSTATUS", "Like Screen Failed" + call + "bcbbc" + t);
                access = false;
                ib_like.setImageResource(0);
                ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.warning));

            }
        });
    }

    public void postRetrofitFollowMethod(String type, String userId, String entityId, final Button followBtn, final Context context) {
        Log.e("type", type);
        Log.e("entityId", entityId);
        Log.e("type", userId);

        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/likes-ms/postCardStatus").create(ApiInterface.class);
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
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/likes-ms/isPostStatus").create(ApiInterface.class);
        Call<ModelForIsLikedPostRequest> call = apiInterface.isLikedItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForIsLikedPostRequest>() {
            @Override
            public void onResponse(Call<ModelForIsLikedPostRequest> call, Response<ModelForIsLikedPostRequest> response) {
                Log.e("Follow SUCCESS", response.body().getData().getCount() + "SUCCESS");
                if (response.body().getData().getCount().equals("1")) {
                    followBtn.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                    followBtn.setBackground(context.getResources().getDrawable(R.drawable.corner_rounded_pink));
                    followBtn.setText("Following");
                    followBtn.setTextColor(context.getResources().getColor(R.color.white));
                } else {
                    followBtn.setBackgroundColor(context.getResources().getColor(R.color.black));
                    followBtn.setBackground(context.getResources().getDrawable(R.drawable.corner_rounded));
                    followBtn.setText("follow");
                    followBtn.setTextColor(context.getResources().getColor(R.color.black));
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
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/likes-ms/postCardStatus").create(ApiInterface.class);
        Call<ModelForPostRequest> call = apiInterface.likeItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForPostRequest>() {
            @Override
            public void onResponse(Call<ModelForPostRequest> call, Response<ModelForPostRequest> response) {
                bookmarkBtn.setImageResource(0);
                bookmarkBtn.setImageResource(R.drawable.bookmark_yellow);
            }

            @Override
            public void onFailure(Call<ModelForPostRequest> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }


    public void checkForBookmark(String type, String userId, String entityId, final ImageButton bookmarkBtn, final Context context) {
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/likes-ms/isPostStatus").create(ApiInterface.class);
        Call<ModelForIsLikedPostRequest> call = apiInterface.isLikedItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForIsLikedPostRequest>() {
            @Override
            public void onResponse(Call<ModelForIsLikedPostRequest> call, Response<ModelForIsLikedPostRequest> response) {
                if ("1".equals(response.body().getData().getCount().trim())) {
                    bookmarkBtn.setImageResource(0);
                    bookmarkBtn.setImageResource(R.drawable.bookmark_yellow);
                } else if ("0".equals(response.body().getData().getCount().trim())) {
                    //ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_icon));
                }
            }

            @Override
            public void onFailure(Call<ModelForIsLikedPostRequest> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }

    public void postRetrofitCommentMethod(String userName, String userId, String entityId, String commentText, final EditText editText, final Context context) {
        if (commentText.trim().length() == 0 || commentText == null) {
            Toast.makeText(context, "First Write Something!", Toast.LENGTH_LONG).show();
            editText.setError("write something");
            return;
        }
        ModelForPostCommentRequest modelForPostRequest = new ModelForPostCommentRequest(userName, userId, entityId, commentText);
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/comments-ms/postComment").create(ApiInterface.class);
        Call<ModelForPostCommentRequest> call = apiInterface.commentItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForPostCommentRequest>() {
            @Override
            public void onResponse(Call<ModelForPostCommentRequest> call, Response<ModelForPostCommentRequest> response) {
                Toast.makeText(context, "Comment Successful", Toast.LENGTH_LONG).show();
                editText.setText("");
            }

            @Override
            public void onFailure(Call<ModelForPostCommentRequest> call, Throwable t) {
                Toast.makeText(context, "Comment Unsuccessful! Please try again.", Toast.LENGTH_LONG).show();
            }
        });
    }

}