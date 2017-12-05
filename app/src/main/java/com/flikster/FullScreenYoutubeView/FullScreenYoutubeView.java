package com.flikster.FullScreenYoutubeView;

import android.os.Bundle;
import android.util.Log;

import com.flikster.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by abhishek on 05-12-2017.
 */

public class FullScreenYoutubeView extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    YouTubePlayerView youTubePlayerView;
    final String API_KEY="AIzaSyAB-5qUbSkM629ZcB0jCBK-WGGWPS5zZ90";
    String video_id;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fullscreen_youtube_view);
        initializeView();
        formatVideoLink();
        initializeRest();
    }

    private void formatVideoLink() {
        if(getIntent().getStringExtra("video_link").contains("https://www.youtube.com/embed/"))
            video_id=getIntent().getStringExtra("video_link").substring(30);
        else if(getIntent().getStringExtra("video_link").contains("https://youtu.be/"))
            video_id=getIntent().getStringExtra("video_link").substring(17);
        else if(getIntent().getStringExtra("video_link").contains("https://www.youtube.com/"))
            video_id=getIntent().getStringExtra("video_link").substring(24);
        else if(getIntent().getStringExtra("video_link").contains("https://www.youtube.com/watch?v="))
            video_id=getIntent().getStringExtra("video_link").substring(32);
    }

    private void initializeRest() {
        youTubePlayerView.initialize(API_KEY,this);
    }

    private void initializeView() {
        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.fullscreen_youtube_view);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(video_id);
        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        youTubePlayer.setShowFullscreenButton(false);
        youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
