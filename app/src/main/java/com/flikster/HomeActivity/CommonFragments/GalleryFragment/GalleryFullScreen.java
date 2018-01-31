package com.flikster.HomeActivity.CommonFragments.GalleryFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.FeedFragment.FeedRecyclerAdapter;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.R;
import com.flikster.Util.Common;

/**
 * Created by abhishek on 05-10-2017.
 */

public class GalleryFullScreen extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    ScrollView scrollView;
    VideoView videoView;
    Button closebtn;
    ImageButton card_footer_share, ib_like, ib_bookmark;
    ImageButton card_comment_text_send_btn;
    EditText card_comment_text_edittxt;
    TextView card_comment_text_see_more_comments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_videopage);
        initializeViews();
        initializeRest();
    }

    private void initializeRest() {
        videoView.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
        Glide.with(this).load(getIntent().getStringExtra("galleryimglink")).into(imageView);
        closebtn.setOnClickListener(this);
        new PostRetrofit().checkForLike("like",
                getIntent().getStringExtra("userId"),
                getIntent().getStringExtra("galleryimglink"),
                ib_like, getApplicationContext());
        new PostRetrofit().checkForBookmark("bookmark",
                getIntent().getStringExtra("userId"),
                getIntent().getStringExtra("galleryimglink"),
                ib_bookmark, getApplicationContext());
    }

    private void initializeViews() {
        videoView = (VideoView) findViewById(R.id.playVideo);
        scrollView = (ScrollView) findViewById(R.id.gallary_fullscreen_scrollimg);
        imageView = (ImageView) findViewById(R.id.gallary_fullscreen_img);
        closebtn = (Button) findViewById(R.id.closebtn);
        card_footer_share = (ImageButton) findViewById(R.id.card_footer_share);
        ib_like = (ImageButton) findViewById(R.id.ib_like);
        ib_bookmark = (ImageButton) findViewById(R.id.ib_bookmark);

        card_comment_text_send_btn = (ImageButton) findViewById(R.id.card_comment_text_send_btn);
        card_comment_text_send_btn.setOnClickListener(this);
        card_comment_text_edittxt = (EditText) findViewById(R.id.card_comment_text_edittxt);
        card_comment_text_see_more_comments = (TextView) findViewById(R.id.card_comment_text_see_more_comments);
        card_comment_text_see_more_comments.setOnClickListener(this);
        card_comment_text_see_more_comments.setVisibility(View.GONE);

        card_footer_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.shareClick(getIntent().getStringExtra("galleryimglink"),GalleryFullScreen.this);
            }
        });

        ib_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.likeAndUnLikeEvent(getApplicationContext(),
                        ib_like, getIntent().getStringExtra("userId"),
                        getIntent().getStringExtra("galleryimglink"));
            }
        });
        ib_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.bookmarkAndUnBookmarkeEvent(getApplicationContext(), ib_bookmark,
                        getIntent().getStringExtra("userId"),
                        getIntent().getStringExtra("galleryimglink"));
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.closebtn) {
            finish();
//            Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_SHORT).show();
           /* Intent intent = new Intent(GalleryFullScreen.this, HomeActivity.class);
            intent.putExtra("GallaryFullscreen", "GallaryFullscreen");
            startActivity(intent);*/
        } else if (view.getId() == R.id.card_comment_text_send_btn) {
            new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                    getIntent().getStringExtra("userId"),
                    getIntent().getStringExtra("galleryimglink"),
                    card_comment_text_edittxt.getText().toString(),
                    card_comment_text_edittxt, getApplicationContext());
        } else if (view.getId() == R.id.card_comment_text_send_btn) {
//            testing.seeMoreComments("Abhishek Kumar", getIntent().getStringExtra("userId"),
//                    outerHits.getHits().get(getAdapterPosition()).get_source().getId());
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

