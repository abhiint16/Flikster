package com.flikster.AllCommentActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.ModelForPostCommentRequest;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 25-11-2017.
 */

public class AllCommentActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManager;
    AllCommentActivityAdapter allCommentActivityAdapter;
    ApiInterface apiInterface;
    CommentsData.CommentsInnerData hits;
    TextView textView, toolbar_frag_title;
    ImageButton card_comment_text_send_btn, toolbar_back_navigation_btn;
    EditText editText;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comment);
        context=this;
        initializeViews();
        initializeRest();
        retrofitInit();
    }

    private void retrofitInit() {
        Log.e("AllCommentEntityId", getIntent().getStringExtra("entityId"));
        // http://apiv3-es.flikster.com/comments/_search?pretty=true&sort=createdAt:desc&size=1000&q=entityId:%22749cb9be-29ba-4d50-ae45-d811b7069961%22
        apiInterface = ApiClient.getClient(ApiClient.ELASTIC_URL + "comments/_search/").create(ApiInterface.class);
        Call<CommentsData> call = apiInterface.getAllComments(ApiClient.ELASTIC_URL +
                "comments/_search?pretty=true&sort=createdAt:desc&size=1000&q="
                + "entityId:\"" + getIntent().getStringExtra("entityId") + "\"");
        call.enqueue(new Callback<CommentsData>() {
            @Override
            public void onResponse(Call<CommentsData> call, Response<CommentsData> response) {
                hits = response.body().getHits();
                Log.e("hits", hits.getTotal());
                allCommentActivityAdapter = new AllCommentActivityAdapter(hits);
                fragment_common_recyclerview_recycler.setAdapter(allCommentActivityAdapter);
            }

            @Override
            public void onFailure(Call<CommentsData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initializeRest() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
        textView.setVisibility(View.GONE);
        card_comment_text_send_btn.setOnClickListener(this);
        toolbar_frag_title.setText("All Comments");
        //toolbar_more_icon.setVisibility(View.GONE);
        toolbar_back_navigation_btn.setOnClickListener(this);
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) findViewById(R.id.fragment_common_recyclerview_recycler);
        textView = (TextView) findViewById(R.id.card_comment_text_see_more_comments);
        card_comment_text_send_btn = (ImageButton) findViewById(R.id.card_comment_text_send_btn);
        editText = (EditText) findViewById(R.id.card_comment_text_edittxt);
        toolbar_frag_title = (TextView) findViewById(R.id.toolbar_frag_title);
        //toolbar_more_icon = (ImageButton) findViewById(R.id.toolbar_more_icon);
        toolbar_back_navigation_btn = (ImageButton) findViewById(R.id.toolbar_back_navigation_btn);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_back_navigation_btn) {
            finish();
        } else {
            if (SharedPrefsUtil.getStringPreference(this, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                Toast.makeText(this, "You need to first Login.", Toast.LENGTH_SHORT).show();
                return;
            }
            String USERID = SharedPrefsUtil.getStringPreference(getApplicationContext(), "USER_ID");
            if (editText.getText().toString().trim().length() == 0 || editText.getText().toString() == null) {
                    Toast.makeText(this, "First Write Something!", Toast.LENGTH_LONG).show();
                    editText.setError("write something");
                    return;
                }
                ModelForPostCommentRequest modelForPostRequest = new ModelForPostCommentRequest(getIntent().getStringExtra("userName"),
                        USERID, getIntent().getStringExtra("entityId"), editText.getText().toString());
                apiInterface = ApiClient.getClient(ApiClient.POST_COMMENT_URL).create(ApiInterface.class);
                Call<ModelForPostCommentRequest> call = apiInterface.commentItem(modelForPostRequest);
                call.enqueue(new Callback<ModelForPostCommentRequest>() {
                    @Override
                    public void onResponse(Call<ModelForPostCommentRequest> call, Response<ModelForPostCommentRequest> response) {
                        response.body().getStatusCode();
                        if (response.body().getStatusCode() != null && response.body().getStatusCode() == 200) {
                            Toast.makeText(context, "Comment Successful", Toast.LENGTH_LONG).show();
                            editText.setText("");
                            getListOfCommentsAndNotify();
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
        }

    private void getListOfCommentsAndNotify() {
        apiInterface = ApiClient.getClient(ApiClient.ELASTIC_URL + "comments/_search/").create(ApiInterface.class);
        Call<CommentsData> call = apiInterface.getAllComments(ApiClient.ELASTIC_URL +
                "comments/_search?pretty=true&sort=createdAt:desc&size=1000&q="
                + "entityId:\"" + getIntent().getStringExtra("entityId") + "\"");
        call.enqueue(new Callback<CommentsData>() {
            @Override
            public void onResponse(Call<CommentsData> call, Response<CommentsData> response) {
                hits = response.body().getHits();
                allCommentActivityAdapter.updateList(hits.getHits());
            }

            @Override
            public void onFailure(Call<CommentsData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }


}

