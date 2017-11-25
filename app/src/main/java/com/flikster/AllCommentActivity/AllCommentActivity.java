package com.flikster.AllCommentActivity;

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

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.R;

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
    TextView textView;
    ImageButton card_comment_text_send_btn;
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comment);
        initializeViews();
        initializeRest();
        retrofitInit();
    }

    private void retrofitInit() {
        http://apiv3-es.flikster.com/comments/_search?pretty=true&sort=createdAt:desc&size=1000&q=entityId:%22749cb9be-29ba-4d50-ae45-d811b7069961%22
        apiInterface = ApiClient.getClient("http://apiv3-es.flikster.com/comments/_search?pretty=true&sort=createdAt:desc&size=1000&q="+"entityId:\""+getIntent().getStringExtra("entityId")+"\"").create(ApiInterface.class);
        Call<CommentsData> call = apiInterface.getAllComments("http://apiv3-es.flikster.com/comments/_search?pretty=true&sort=createdAt:desc&size=1000&q="+"entityId:\""+getIntent().getStringExtra("entityId")+"\"");
        call.enqueue(new Callback<CommentsData>() {
            @Override
            public void onResponse(Call<CommentsData> call, Response<CommentsData> response) {
                hits=response.body().getHits();
                allCommentActivityAdapter=new AllCommentActivityAdapter(hits);
                fragment_common_recyclerview_recycler.setAdapter(allCommentActivityAdapter);
            }

            @Override
            public void onFailure(Call<CommentsData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initializeRest() {
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
        textView.setVisibility(View.GONE);
        card_comment_text_send_btn.setOnClickListener(this);
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler=(RecyclerView)findViewById(R.id.fragment_common_recyclerview_recycler);
        textView=(TextView)findViewById(R.id.card_comment_text_see_more_comments);
        card_comment_text_send_btn=(ImageButton)findViewById(R.id.card_comment_text_send_btn);
        editText=(EditText)findViewById(R.id.card_comment_text_edittxt);
    }

    @Override
    public void onClick(View view) {
        new PostRetrofit().postRetrofitCommentMethod(getIntent().getStringExtra("userName"),getIntent().getStringExtra("userId"),
                getIntent().getStringExtra("entityId"),editText.getText().toString(),editText,this);
    }
}
