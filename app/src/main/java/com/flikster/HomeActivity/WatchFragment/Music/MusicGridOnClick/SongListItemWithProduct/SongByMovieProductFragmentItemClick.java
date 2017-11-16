package com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongListItemWithProduct;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.flikster.R;

import java.io.IOException;

/**
 * Created by abhishek on 01-11-2017.
 */

public class SongByMovieProductFragmentItemClick extends Fragment implements View.OnClickListener {
    View view;
    FragmentManager fragmentManager;
    ImageButton ib_bookmark, toolbar_back_navigation_btn, toolbar_frag_multiicons_search, toolbar_frag_multiicons_overflow, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart;
    TextView toolbar_frag_multiicons_title;
    MediaPlayer musicplay;
    SeekBar seekBar;
    ImageButton playibtn, pauseibtn;
    ImageView closeimgv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_info, container, false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        toolbar_frag_multiicons_title.setText("Saho");
        toolbar_back_navigation_btn.setOnClickListener(this);
        toolbar_frag_multiicons_overflow.setVisibility(View.GONE);
        toolbar_frag_multiicons_search.setVisibility(View.GONE);
        toolbar_frag_multiicons_notification.setVisibility(View.GONE);
        toolbar_frag_multiicons_cart.setVisibility(View.GONE);
        playibtn.setOnClickListener(this);
        pauseibtn.setOnClickListener(this);
        closeimgv.setOnClickListener(this);
    }

    private void initializeViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_search = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_search);
        toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
        toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        toolbar_frag_multiicons_overflow = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_overflow);
        toolbar_frag_multiicons_title = (TextView) view.findViewById(R.id.toolbar_frag_multiicons_title);
        playibtn = (ImageButton) view.findViewById(R.id.playibtn);
        pauseibtn = (ImageButton) view.findViewById(R.id.pauseibtn);
        closeimgv = (ImageView) view.findViewById(R.id.closeimgv);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.playibtn) {
            playibtn.setVisibility(View.GONE);
            pauseibtn.setVisibility(View.VISIBLE);
            playAudioSong();
        } else if (view.getId() == R.id.pauseibtn) {
            playibtn.setVisibility(View.VISIBLE);
            pauseibtn.setVisibility(View.GONE);
            pauseAudioSong();
        } else if (view.getId() == R.id.closeimgv) {
            fragmentManager.popBackStackImmediate();
        } else {
            fragmentManager.popBackStackImmediate();
        }
    }


    private void pauseAudioSong() {
        if (musicplay != null) {
            musicplay.pause();
            seekBar.setProgress(musicplay.getCurrentPosition());
        }
    }

    private void playAudioSong() {
      /*  if (musicplay == null)
            musicplay = MediaPlayer.create(getContext(), R.raw.ringtone);
        musicplay.start();
        seekBar.setMax(musicplay.getDuration());
        seekBar.setProgress(musicplay.getCurrentPosition());*/
        Uri myUri = Uri.parse("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-16.mp3");
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


}
