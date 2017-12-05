package com.flikster.HomeActivity.CommonFragments.VideoFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsData;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 16-10-2017.
 */

public class VideoGalleryFragment extends Fragment implements View.OnClickListener {
    View view;
    ImageButton toolbar_back_navigation_btn;
    TextView toolbar_frag_title, titlehedertxt, fragment_common_recyclerview_with_tv_title, tv_name, tv_description;
    Context mContext;
    RecyclerView fragment_common_recyclerview_with_tv_recycler;
    String profilePic, title, type, bannerImg, headertitle, description,contentType,videolink;
    CelebrityBioAdapterImagesViewHolder myCeleAdapter;
    ApiInterface apiInterface;
    NewsData.NewsInnerData outerHits;
    VideoGalleryAdapter videoGalleryAdapter;
    Integer Count;
    TextView tv_tag_desc,tv_tag_name;
    YouTubePlayerSupportFragment youTubePlayerFragment;
    YouTubePlayer yPlayer;
    final String API_KEY="AIzaSyAB-5qUbSkM629ZcB0jCBK-WGGWPS5zZ90";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video_gallery, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        bottomHorRecyclerRetrofitInit();
        headerTitlesChange();
        initializeRest();
        //playLocalVideo();
        titlehedertxt.setText(title);
        tv_description.setText(description);
        return view;
    }

    private void headerTitlesChange() {
        toolbar_frag_title.setText(contentType);
        fragment_common_recyclerview_with_tv_title.setText("Recommended "+contentType);
    }

    private void initializeViews() {
        toolbar_frag_title = (TextView) view.findViewById(R.id.toolbar_frag_title);
        titlehedertxt = (TextView) view.findViewById(R.id.titlehedertxt);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_description = (TextView) view.findViewById(R.id.tv_description);
        tv_tag_name = (TextView) view.findViewById(R.id.tv_tag_name);
        tv_tag_desc = (TextView) view.findViewById(R.id.tv_tag_desc);
        youTubePlayerFragment=YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_video_gallery_youtube_container,youTubePlayerFragment).commit();
        fragment_common_recyclerview_with_tv_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
        //playVideo.setVisibility(View.VISIBLE);
        tv_name.setVisibility(View.GONE);
    }


    private void initializeRest() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
        toolbar_back_navigation_btn.setOnClickListener(this);
        youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                yPlayer=youTubePlayer;
                if(videolink.contains("https://www.youtube.com/embed/"))
                    yPlayer.loadVideo(videolink.substring(30));
                else if(videolink.contains("https://youtu.be/"))
                    yPlayer.loadVideo(videolink.substring(17));
                else if(videolink.contains("https://www.youtube.com/"))
                    yPlayer.loadVideo(videolink.substring(24));
                yPlayer.play();
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_back_navigation_btn) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    /*private void playLocalVideo() {
        //Creating MediaController
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(playVideo);
        //specify the location of media file
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.demovideo);
        //Setting MediaController and URI, then starting the videoView
//        Uri uri = Uri.parse("https://www.youtube.com/watch?v=JXIiQFSj8Yg");
        playVideo.setMediaController(mediaController);
        playVideo.setVideoURI(uri);
        playVideo.requestFocus();
        playVideo.start();
    }*/


    private void bottomHorRecyclerRetrofitInit() {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/_search?pretty=true&sort=createdAt:desc&size=100&q=contentType:"+"\""+contentType+"\"").create(ApiInterface.class);
        Call<NewsData> call = apiInterface.getNewsData("http://apiservice-ec.flikster.com/contents/_search?pretty=true&sort=createdAt:desc&size=100&q=contentType:"+"\""+contentType+"\"");
        call.enqueue(new Callback<NewsData>() {
            @Override
            public void onResponse(Call<NewsData> call, Response<NewsData> response) {
                outerHits = response.body().getHits();
                Count = outerHits.getTotal();
                videoGalleryAdapter = new VideoGalleryAdapter(getActivity(),outerHits,Count,title);
                fragment_common_recyclerview_with_tv_recycler.setAdapter(videoGalleryAdapter);
            }

            @Override
            public void onFailure(Call<NewsData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    public void updateImage(String profilePic, String title, String type, String bannerImg, String headertitle, String description,String contentType,
                            String videolink) {
        this.profilePic = profilePic;
        this.title = title;
        this.type = type;
        this.bannerImg = bannerImg;
        this.headertitle = headertitle;
        this.description = description;
        this.contentType=contentType;
        this.videolink=videolink;
    }
}
