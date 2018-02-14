package com.flikster.HomeActivity.CommonFragments.VideoFragment;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragment;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsData;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.FeedFragment.FeedRecyclerAdapter;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.R;
import com.flikster.Util.Common;
import com.flikster.Util.SharedPrefsUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.rohitarya.glide.facedetection.transformation.core.GlideFaceDetector;

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
    String profilePic, title, type, bannerImg, headertitle, description, contentType, videolink, userId, entityId,cardId,
    slug;
    CelebrityBioAdapterImagesViewHolder myCeleAdapter;
    ApiInterface apiInterface;
    FeedInnerData outerHits;
    VideoGalleryAdapter videoGalleryAdapter;
    Integer Count;
    Button followbtn;
    TextView tv_tag_desc, tv_tag_name;
    YouTubePlayerSupportFragment youTubePlayerFragment;
    YouTubePlayer yPlayer;
    final String API_KEY = "AIzaSyAB-5qUbSkM629ZcB0jCBK-WGGWPS5zZ90";
    VideoRecommendationClick videoRecommendationClick;
    ImageButton card_footer_share, ib_like, ib_bookmark;
    ImageView profile_image;
    ImageButton card_comment_text_send_btn;
    EditText card_comment_text_edittxt;
    TextView card_comment_text_see_more_comments;
    LinearLayout header_linear;

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
        fragment_common_recyclerview_with_tv_title.setText("Recommended " + contentType);
    }

    private void initializeViews() {
        toolbar_frag_title = (TextView) view.findViewById(R.id.toolbar_frag_title);
        titlehedertxt = (TextView) view.findViewById(R.id.titlehedertxt);
        profile_image=(ImageView)view.findViewById(R.id.profile_image);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        header_linear=(LinearLayout)view.findViewById(R.id.header_linear);
        tv_description = (TextView) view.findViewById(R.id.tv_description);
        tv_tag_name = (TextView) view.findViewById(R.id.tv_tag_name);
        tv_tag_desc = (TextView) view.findViewById(R.id.tv_tag_desc);
        followbtn = (Button) view.findViewById(R.id.followbtn);
        card_footer_share = (ImageButton) view.findViewById(R.id.card_footer_share);
        ib_like = (ImageButton) view.findViewById(R.id.ib_like);
        ib_bookmark = (ImageButton) view.findViewById(R.id.ib_bookmark);
        youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_video_gallery_youtube_container, youTubePlayerFragment).commit();
        fragment_common_recyclerview_with_tv_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
        //playVideo.setVisibility(View.VISIBLE);
        tv_name.setVisibility(View.GONE);
        new PostRetrofit().checkForFollow("follow", userId, entityId, followbtn, getContext());
        new PostRetrofit().checkForLike("like", userId, entityId, ib_like, getContext());
        new PostRetrofit().checkForBookmark("bookmark", userId, entityId, ib_bookmark, getContext());

        card_comment_text_send_btn = (ImageButton) view.findViewById(R.id.card_comment_text_send_btn);
        card_comment_text_send_btn.setOnClickListener(this);
        card_comment_text_edittxt = (EditText) view.findViewById(R.id.card_comment_text_edittxt);
        card_comment_text_see_more_comments = (TextView) view.findViewById(R.id.card_comment_text_see_more_comments);
        card_comment_text_see_more_comments.setOnClickListener(this);
        card_comment_text_see_more_comments.setVisibility(View.GONE);

        followbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.followOrUnFollow(getContext(), followbtn, userId, entityId);
            }
        });
        card_footer_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.shareClick(profilePic, getContext());
            }
        });

        ib_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.likeAndUnLikeEvent(getContext(), ib_like, userId, entityId);
            }
        });
        ib_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.bookmarkAndUnBookmarkeEvent(getContext(), ib_bookmark, userId, entityId);
            }
        });

        card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                        userId,
                        entityId,
                        card_comment_text_edittxt.getText().toString(),
                        card_comment_text_edittxt, getContext());
            }
        });


        if (!type.isEmpty()) {
            tv_tag_desc.setText(type + "");
        }
        if (!title.isEmpty()) {
            tv_tag_name.setText(title + "");
        }


        if (!profilePic.isEmpty()) {
            Glide.with(getContext()).load(profilePic).asBitmap().into(profile_image);
        }

        toolbar_back_navigation_btn.setOnClickListener(this);

    }


    private void initializeRest() {
        profile_image.setOnClickListener(this);
        header_linear.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
        toolbar_back_navigation_btn.setOnClickListener(this);
        youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                yPlayer = youTubePlayer;
                if (videolink.contains("https://www.youtube.com/embed/"))
                    yPlayer.loadVideo(videolink.substring(30));
                else if (videolink.contains("https://youtu.be/"))
                    yPlayer.loadVideo(videolink.substring(17));
                else if (videolink.contains("https://www.youtube.com/"))
                    yPlayer.loadVideo(videolink.substring(24));
                yPlayer.play();
                yPlayer.setShowFullscreenButton(false);
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
        }else if (view.getId()==R.id.header_linear||view.getId()==R.id.profile_image)
        {
            if ("movie".equals(type)) {
                videoRecommendationClick.test(slug,
                        new MovieFragment(), 1, userId, entityId);
            } else if ("celeb".equals(type)) {
                videoRecommendationClick.test(slug,
                        new CelebrityFragment(), 2, userId, entityId);
            }
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
        GlideFaceDetector.releaseDetector();
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
        GlideFaceDetector.initialize(getActivity());
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/_search/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getNewsData("http://apiservice-ec.flikster.com/contents/_search?pretty=true&sort=createdAt:desc&size=10&from=0&q=contentType:" + "\"" + contentType + "\""
                +"%20AND%20industry:\""+ SharedPrefsUtil.getStringPreference(getContext(), "INDUSTRY_TYPE")+"\"");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                outerHits = response.body().getHits();
                Count = outerHits.getTotal();
                videoGalleryAdapter = new VideoGalleryAdapter(getActivity(), outerHits, Count, title, videoRecommendationClick,contentType,cardId,slug);
                fragment_common_recyclerview_with_tv_recycler.setAdapter(videoGalleryAdapter);
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    public void updateImage(String profilePic, String title, String type, String bannerImg,
                            String headertitle, String description, String contentType,
                            String videolink, String userId, String entityId,String cardId,String slug) {
        this.profilePic = profilePic;
        this.title = title;
        this.type = type;
        this.bannerImg = bannerImg;
        this.headertitle = headertitle;
        this.description = description;
        this.contentType = contentType;
        this.videolink = videolink;
        this.userId = userId;
        this.entityId = entityId;
        this.entityId = entityId;
        this.cardId=cardId;
        this.slug=slug;
    }

    public interface VideoRecommendationClick {
        void videoRecommendationClickMethod(String profilePic,
                                            String title, String type, String bannerImg,
                                            String headertitle, String description, String videolink,
                                            Fragment fragment, String contentType, String userId, String entityId,String cardId,String slug);
        void test(String name, Fragment fragment, int getClass, String userId, String entityId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        videoRecommendationClick = (VideoRecommendationClick) activity;
    }
}
