package com.flikster.HomeActivity;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 24-11-2017.
 */

public class PostRetrofit {
    ApiInterface apiInterface;

    public String postRetrofitMethod(String type, String userId, String entityId, final ImageButton ib_like, final Context context) {
        final String[] s = new String[1];
        Log.e("insied postRetrofitMtd","insied postRetrofitMtd"+type+userId+entityId);
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/likes-ms/postCardStatus").create(ApiInterface.class);
        Call<ModelForPostRequest> call=apiInterface.likeItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForPostRequest>() {
            @Override
            public void onResponse(Call<ModelForPostRequest> call, Response<ModelForPostRequest> response) {
                Log.e("insied onResonse","insied onrespnse"+call+"bcbbc"+response+"gggg"+response.body());
                //ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_pink));
                s[0] ="success";
            }

            @Override
            public void onFailure(Call<ModelForPostRequest> call, Throwable t) {
                Log.e("insied onfailure","insied onfailre"+call+"bcbbc"+t);
                ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_icon));
                s[0] ="failure";
            }
        });

        return s[0];
    }

    public  void checkForLike(String type, String userId, String entityId,final ImageButton ib_like, final Context context)
    {
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/likes-ms/isPostStatus").create(ApiInterface.class);
        Call<ModelForIsLikedPostRequest> call=apiInterface.isLikedItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForIsLikedPostRequest>() {
            @Override
            public void onResponse(Call<ModelForIsLikedPostRequest> call, Response<ModelForIsLikedPostRequest> response) {
                Log.e("geting respne","  "+response.body().toString());
                if("1".equals(response.body().getData().getCount().trim()))
                {
                    ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_pink));
                }
                else if("0".equals(response.body().getData().getCount().trim()))
                {
                    ib_like.setImageDrawable(context.getResources().getDrawable(R.drawable.like_icon));
                }
            }

            @Override
            public void onFailure(Call<ModelForIsLikedPostRequest> call, Throwable t) {
                Log.e("insied onfailure","insied onfailre"+call+"bcbbc"+t);
            }
        });
    }

    public String postRetrofitFollowMethod(String type, String userId, String entityId, final Button followBtn, final Context context) {
        final String[] s = new String[1];
        Log.e("insied postRetrofitMtd","insied postRetrofitMtd"+type+userId+entityId);
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/likes-ms/postCardStatus").create(ApiInterface.class);
        Call<ModelForPostRequest> call=apiInterface.likeItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForPostRequest>() {
            @Override
            public void onResponse(Call<ModelForPostRequest> call, Response<ModelForPostRequest> response) {
                Log.e("insied onResonse","insied onrespnse"+call+"bcbbc"+response+"gggg"+response.body());
                followBtn.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                followBtn.setText("Followed");
                followBtn.setTextColor(context.getResources().getColor(R.color.white));
                s[0] ="success";
            }

            @Override
            public void onFailure(Call<ModelForPostRequest> call, Throwable t) {
                Log.e("insied onfailure","insied onfailre"+call+"bcbbc"+t);
                //followBtn.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                s[0] ="failure";
            }
        });

        return s[0];
    }


    public String postRetrofitBookmarkMethod(String type, String userId, String entityId, final ImageButton bookmarkBtn, final Context context) {
        final String[] s = new String[1];
        Log.e("insied postRetrofitMtd","insied postRetrofitMtd"+type+userId+entityId);
        ModelForPostRequest modelForPostRequest = new ModelForPostRequest(type, userId, entityId);
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/likes-ms/postCardStatus").create(ApiInterface.class);
        Call<ModelForPostRequest> call=apiInterface.likeItem(modelForPostRequest);
        call.enqueue(new Callback<ModelForPostRequest>() {
            @Override
            public void onResponse(Call<ModelForPostRequest> call, Response<ModelForPostRequest> response) {
                Log.e("insied onResonse","insied onrespnse"+call+"bcbbc"+response+"gggg"+response.body());
                bookmarkBtn.setImageResource(R.drawable.bookmark_yellow);
                s[0] ="success";
            }

            @Override
            public void onFailure(Call<ModelForPostRequest> call, Throwable t) {
                Log.e("insied onfailure","insied onfailre"+call+"bcbbc"+t);
                //followBtn.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                s[0] ="failure";
            }
        });

        return s[0];
    }

}
