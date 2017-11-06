package com.flikster.HomeActivity.WatchFragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.flikster.HomeActivity.CommonFragments.ShopByVideoFragment.ShopByVideoFragmentItemClickAdapter;
import com.flikster.R;

/**
 * Created by abhishek on 01-11-2017.
 */

public class SongByMovieFragmentItemClick extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManager;
    SongByMovieFragmentItemPlayClickAdapter shopByVideoFragmentItemClickAdapter;
    VideoView card_shopby_video_recycler_item_onclick_playVideo;
    FragmentManager fragmentManager;
    ImageButton ib_bookmark, toolbar_back_navigation_btn, toolbar_frag_multiicons_search, toolbar_frag_multiicons_overflow, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart;
    TextView fragment_common_recyclerview_with_tv_title, toolbar_frag_multiicons_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_song_with_products, container, false);
        initializeViews();
        initializeRest();
        playLocalVideo();
        return view;
    }

    private void initializeRest() {
        toolbar_frag_multiicons_title.setText("Saho");
        fragment_common_recyclerview_with_tv_title.setText("10 Styles tagged");

        fragmentManager = getActivity().getSupportFragmentManager();
        layoutManager = new GridLayoutManager(getActivity(), 2);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);

        shopByVideoFragmentItemClickAdapter = new SongByMovieFragmentItemPlayClickAdapter(getContext(), fragmentManager);
        fragment_common_recyclerview_recycler.setAdapter(shopByVideoFragmentItemClickAdapter);
        toolbar_back_navigation_btn.setOnClickListener(this);

        toolbar_frag_multiicons_overflow.setVisibility(View.GONE);
        toolbar_frag_multiicons_search.setVisibility(View.GONE);
        toolbar_frag_multiicons_notification.setVisibility(View.GONE);
        toolbar_frag_multiicons_cart.setVisibility(View.GONE);
        ib_bookmark.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.playlist));
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_search = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_search);
        toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
        toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        toolbar_frag_multiicons_overflow = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_overflow);
        toolbar_frag_multiicons_title = (TextView) view.findViewById(R.id.toolbar_frag_multiicons_title);

        card_shopby_video_recycler_item_onclick_playVideo = (VideoView) view.findViewById(R.id.card_shopby_video_recycler_item_onclick_playVideo);
        ib_bookmark = (ImageButton) view.findViewById(R.id.ib_bookmark);
    }

    private void playLocalVideo() {
        //Creating MediaController
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(card_shopby_video_recycler_item_onclick_playVideo);
        //specify the location of media file
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.demovideo);
        //Setting MediaController and URI, then starting the videoView
        card_shopby_video_recycler_item_onclick_playVideo.setMediaController(mediaController);
        card_shopby_video_recycler_item_onclick_playVideo.setVideoURI(uri);
        card_shopby_video_recycler_item_onclick_playVideo.requestFocus();
        card_shopby_video_recycler_item_onclick_playVideo.start();
    }

    @Override
    public void onClick(View view) {
        fragmentManager.popBackStackImmediate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }



}
