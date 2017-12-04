package com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongListItemWithProduct.SongByMovieFragmentItemPlayClickAdapter;
import com.flikster.R;

import java.io.IOException;

/**
 * Created by abhishek on 01-11-2017.
 */

public class SongByMovieFragmentItemClick extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManager;
    SongByMovieFragmentItemPlayClickAdapter shopByVideoFragmentItemClickAdapter;
    FragmentManager fragmentManager;
    ImageButton ib_bookmark, toolbar_back_navigation_btn, toolbar_frag_multiicons_search, toolbar_frag_multiicons_overflow, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart;
    TextView fragment_common_recyclerview_with_tv_title, toolbar_frag_multiicons_title;
    MediaPlayer musicplay;
    SeekBar seekBar;
    ImageButton playibtn;
    String audioLink,audioImg;
    Handler han = new Handler();
    Boolean playClick=false;
    ImageView audio_frame_image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_song_with_products, container, false);
        initializeViews();
        initializeRest();
//        playLocalVideo();
        musicplay = MediaPlayer.create(getContext(), R.raw.ringtone);
        seekBar.setMax(musicplay.getDuration());
        SeekUpdation();
        seekBar.setOnSeekBarChangeListener(this);
        return view;
    }

    private void initializeRest() {
        toolbar_frag_multiicons_title.setText("Saho");
        fragment_common_recyclerview_with_tv_title.setText("10 Styles tagged");
        fragmentManager = getActivity().getSupportFragmentManager();
        layoutManager = new GridLayoutManager(getActivity(), 2);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
        shopByVideoFragmentItemClickAdapter = new SongByMovieFragmentItemPlayClickAdapter(getActivity(), fragmentManager);
        fragment_common_recyclerview_recycler.setAdapter(shopByVideoFragmentItemClickAdapter);
        toolbar_back_navigation_btn.setOnClickListener(this);
        toolbar_frag_multiicons_overflow.setVisibility(View.GONE);
        toolbar_frag_multiicons_search.setVisibility(View.GONE);
        toolbar_frag_multiicons_notification.setVisibility(View.GONE);
        toolbar_frag_multiicons_cart.setVisibility(View.GONE);
        ib_bookmark.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.playlist));
        Glide.with(getActivity()).load(audioImg).into(audio_frame_image);
        playibtn.setOnClickListener(this);
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_search = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_search);
        toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
        toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        audio_frame_image=(ImageView)view.findViewById(R.id.audio_frame_image);
        toolbar_frag_multiicons_overflow = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_overflow);
        toolbar_frag_multiicons_title = (TextView) view.findViewById(R.id.toolbar_frag_multiicons_title);
        ib_bookmark = (ImageButton) view.findViewById(R.id.ib_bookmark);
        playibtn = (ImageButton) view.findViewById(R.id.playibtn);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.playibtn) {
            if(playClick==false)
            {
                playAudioSong();
                playibtn.setImageResource(R.drawable.pausebtn);
                playClick=true;
            }
            else if (playClick==true)
            {
                pauseAudioSong();
                playibtn.setImageResource(R.drawable.playicon);
                playClick=false;
            }
        }
    }

    private void pauseAudioSong() {
        if (musicplay != null) {
            musicplay.pause();
            seekBar.setProgress(musicplay.getCurrentPosition());
        }
    }

    private void playAudioSong() {
        Log.e("audiolinkcheck",""+audioLink);
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
        if (musicplay!=null){
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


    public void getAudioLink(String audioLink,String audioImg)
    {
        this.audioLink=audioLink;
        this.audioImg=audioImg;
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
