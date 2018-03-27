package com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedFragment.FeedRecyclerAdapter;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.HomeActivity.ShopByVideoData;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongListItemWithProduct.SongByMovieFragmentItemPlayClickAdapter;
import com.flikster.HomeActivity.WatchFragment.WatchFragment;
import com.flikster.R;
import com.flikster.Util.Common;
import com.flikster.Util.SharedPrefsUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.io.IOException;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 01-11-2017.
 */

public class SongByMovieFragmentItemClick extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    GridLayoutManager layoutManager;
    SongByMovieFragmentItemPlayClickAdapter shopByVideoFragmentItemClickAdapter;
    FragmentManager fragmentManager;
    ImageButton ib_bookmark, card_footer_share, ib_like, toolbar_back_navigation_btn;
    TextView fragment_common_recyclerview_with_tv_title, toolbar_frag_multiicons_title;
    MediaPlayer musicplay;
    SeekBar seekBar;
    ImageButton playibtn;
    String audioLink, audioImg, type, itemType;
    Handler han = new Handler();
    Boolean playClick = false;
    FeedInnerData feedInnerData;
    ImageView audio_frame_image;
    LinearLayout audio_frame;
    String url;
    SongByMovieFragClick songByMovieFragClick;
    ApiInterface apiInterface;
    YouTubePlayerSupportFragment youTubePlayerFragment;
    List<ShopByVideoData.ShopByVideoInnerData.ShopByVideoInnerInnerData.ShopByVideoInnerMostData.ShopByVideoAllProduct> listOfProducts;
    YouTubePlayer yPlayer;
    FrameLayout youtube_container;
    final String API_KEY = "AIzaSyAB-5qUbSkM629ZcB0jCBK-WGGWPS5zZ90";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_song_with_products, container, false);
        initializeViews();
        hideAudioOrVideo();
        initializeRest();
        //musicplay = MediaPlayer.create(getContext(), R.raw.ringtone);
        /*seekBar.setMax(musicplay.getDuration());
        SeekUpdation();
        seekBar.setOnSeekBarChangeListener(this);*/
        return view;
    }

    private void hideAudioOrVideo() {
        if (type == "video")
            audio_frame.setVisibility(View.GONE);
        else if (type == "audio") {
            youtube_container.setVisibility(View.GONE);
            audio_frame.setVisibility(View.VISIBLE);
        }
    }

    private void initializeRest() {
        if (type.equals("video")) {
            toolbar_frag_multiicons_title.setText("Video");
        } else if (type.equals("audio")) {
            toolbar_frag_multiicons_title.setText("Audio");
        }
        fragmentManager = getActivity().getSupportFragmentManager();
        layoutManager = new GridLayoutManager(getActivity(), 2);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
        if (listOfProducts == null || listOfProducts.size() == 0) {
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if ((position > feedInnerData.getHits().size() - 1))
                        return 2;
                    else return 1;
                }
            });
            loadRelatedVideos();
        } else if (listOfProducts != null || listOfProducts.size() != 0) {
            if (listOfProducts != null && listOfProducts.size() != 0)
                fragment_common_recyclerview_with_tv_title.setText(listOfProducts.size() + "Styles tagged");
            shopByVideoFragmentItemClickAdapter = new SongByMovieFragmentItemPlayClickAdapter(getActivity(), fragmentManager, listOfProducts);
            fragment_common_recyclerview_recycler.setAdapter(shopByVideoFragmentItemClickAdapter);
        }
        toolbar_back_navigation_btn.setOnClickListener(this);
        //toolbar_frag_multiicons_overflow.setVisibility(View.GONE);
        //toolbar_frag_multiicons_search.setVisibility(View.GONE);
        //toolbar_frag_multiicons_notification.setVisibility(View.GONE);
        //toolbar_frag_multiicons_cart.setVisibility(View.GONE);
//        ib_bookmark.setBackgroundDrawable(null);
//        ib_bookmark.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.playlist));
        Glide.with(getActivity()).load(audioImg).into(audio_frame_image);
        playibtn.setOnClickListener(this);
        youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                yPlayer = youTubePlayer;
                if (audioLink.contains("https://www.youtube.com/embed/"))
                    yPlayer.loadVideo(audioLink.substring(30));
                else if (audioLink.contains("https://youtu.be/"))
                    yPlayer.loadVideo(audioLink.substring(17));
                else if (audioLink.contains("https://www.youtube.com/"))
                    yPlayer.loadVideo(audioLink.substring(24));
                else if (audioLink.contains("https://www.youtube.com/watch?v="))
                    yPlayer.loadVideo(audioLink.substring(32));
                yPlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    private void loadRelatedVideos() {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies(
                "http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=10&from=0" + url);
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                feedInnerData = response.body().getHits();
                fragment_common_recyclerview_with_tv_title.setText(feedInnerData.getTotal() + " Related Videos");
                shopByVideoFragmentItemClickAdapter = new SongByMovieFragmentItemPlayClickAdapter(getActivity(), fragmentManager, feedInnerData,url,songByMovieFragClick,itemType,
                        audioLink);
                fragment_common_recyclerview_recycler.setAdapter(shopByVideoFragmentItemClickAdapter);
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        //toolbar_frag_multiicons_search = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_search);
        //toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
        //toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        audio_frame_image = (ImageView) view.findViewById(R.id.audio_frame_image);
        youtube_container = (FrameLayout) view.findViewById(R.id.youtube_fragment);

        youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.youtube_fragment, youTubePlayerFragment).commit();

        audio_frame = (LinearLayout) view.findViewById(R.id.audio_frame);
         /*youTubePlayerFragment= (YouTubePlayerSupportFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.youtube_fragment);*/
        // toolbar_frag_multiicons_overflow = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_overflow);
        toolbar_frag_multiicons_title = (TextView) view.findViewById(R.id.toolbar_frag_multiicons_title);

        ib_like = (ImageButton) view.findViewById(R.id.ib_like);
        card_footer_share = (ImageButton) view.findViewById(R.id.card_footer_share);
        ib_bookmark = (ImageButton) view.findViewById(R.id.ib_bookmark);
        playibtn = (ImageButton) view.findViewById(R.id.playibtn);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);

        if (SharedPrefsUtil.getStringPreference(getContext(), "USER_ID") != null && !SharedPrefsUtil.getStringPreference(getContext(), "USER_ID").isEmpty()) {
            new PostRetrofit().checkForBookmark("like",
                    SharedPrefsUtil.getStringPreference(getContext(), "USER_ID"), audioLink, ib_bookmark, getContext());
            new PostRetrofit().checkForBookmark("bookmark",
                    SharedPrefsUtil.getStringPreference(getContext(), "USER_ID"), audioLink, ib_bookmark, getContext());
        }


        ib_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.likeAndUnLikeEvent(getContext(), ib_like, SharedPrefsUtil.getStringPreference(getContext(), "USER_ID"), audioLink);
            }
        });
        ib_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.bookmarkAndUnBookmarkeEvent(getContext(), ib_bookmark, SharedPrefsUtil.getStringPreference(getContext(), "USER_ID"), audioLink);
            }
        });
        card_footer_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("audioLink", audioLink);
                Common.shareClick(audioLink, getContext());
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.playibtn) {
            if (playClick == false) {
                playAudioSong();
                playibtn.setImageResource(R.drawable.pausebtn);
                playClick = true;
            } else if (playClick == true) {
                pauseAudioSong();
                playibtn.setImageResource(R.drawable.playicon);
                playClick = false;
            }
        } else if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new WatchFragment())
                    .commit();
        }
    }

    private void pauseAudioSong() {
        if (musicplay != null) {
            musicplay.pause();
            seekBar.setProgress(musicplay.getCurrentPosition());
        }
    }

    private void playAudioSong() {
        Log.e("audiolinkcheck", "" + audioLink);
        Uri myUri = Uri.parse(audioLink);
        try {
            musicplay = new MediaPlayer();
            musicplay.setDataSource(getContext(), myUri);
            musicplay.setAudioStreamType(AudioManager.STREAM_MUSIC);
            musicplay.prepare(); //don't use prepareAsync for mp3 playback
            musicplay.start();
            seekBar.setMax(musicplay.getDuration());
            seekBar.setProgress(musicplay.getCurrentPosition());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (musicplay != null) {
            musicplay.stop();
        }
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    public void SeekUpdation() {
        if (musicplay != null) {
            seekBar.setProgress(musicplay.getCurrentPosition());
            han.postDelayed(run, 1000);
        }
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            SeekUpdation();
        }
    };


    public void getAudioLink(String audioLink, String audioImg, String type, String itemType) {
        this.audioLink = audioLink;
        this.audioImg = audioImg;
        this.type = type;
        this.itemType = itemType;
        if ("comedy".equals(itemType)) {
            url = "&pretty=true&q=contentType:%22comedy-clip%22";
        } else if ("music".equals(itemType)) {
            this.url = "&pretty=true&q=contentType:%22audio-song%22%20OR%20contentType:%22dialouge%22";
        } else if ("trailer".equals(itemType)) {
            this.url = "&pretty=true&q=contentType:%22trailer%22%20OR%20contentType:%22promo%22";
        } else if ("social".equals(itemType)) {
            this.url = "&pretty=true&q=contentType:%22social-buzz%22%20OR%20contentType:%22interview%22";
        }
    }

    public void getShopByVideo(String audioLink, String audioImg, String type,
                               List<ShopByVideoData.ShopByVideoInnerData.ShopByVideoInnerInnerData.ShopByVideoInnerMostData.ShopByVideoAllProduct> listOfProducts) {
        this.audioLink = audioLink;
        this.audioImg = audioImg;
        this.type = type;
        this.listOfProducts = listOfProducts;
    }

    public interface SongByMovieFragClick {
        void playAudioOrVideoPage(String audioLink,Fragment fragment,String audioImg,String type,String itemType);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        songByMovieFragClick = (SongByMovieFragClick) activity;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (playibtn.getVisibility() == View.GONE) {
            if (musicplay != null) {
                musicplay.pause();
            }
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (playibtn.getVisibility() == View.GONE) {
            if (musicplay != null) {
                musicplay.seekTo(seekBar.getProgress());
                musicplay.start();
            }
        }
    }
}
