package com.flikster;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by abhishek on 16-10-2017.
 */

public class VideoGalleryFragment extends Fragment implements View.OnClickListener {
    View view;
    VideoView playVideo;
    ImageView newsimg;
    ImageButton toolbar_back_navigation_btn;
    TextView toolbar_frag_title, titlehedertxt, recomdedtxtlabel, tv_name, tv_description;
    Context mContext;
    RecyclerView recyclerView;
    CelebrityBioAdapterImagesViewHolder myCeleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_onclick, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        headerTitlesChange();
        initializeRest();
        playLocalVideo();
        titlehedertxt.setText("Two Years Of Bahubali: Lessons Its Success Taught The Industry");
        tv_description.setText("Ranbir said: \"I have really liked Prabhas. He is amazing in Baahubali");
        return view;
    }

    private void headerTitlesChange() {
        toolbar_frag_title.setText("Videos");
        recomdedtxtlabel.setText("Recommended Videos");
    }

    private void initializeViews() {
        toolbar_frag_title = (TextView) view.findViewById(R.id.toolbar_frag_title);
        playVideo = (VideoView) view.findViewById(R.id.playVideo);
        newsimg = (ImageView) view.findViewById(R.id.newsimg);
        titlehedertxt = (TextView) view.findViewById(R.id.titlehedertxt);
        recomdedtxtlabel = (TextView) view.findViewById(R.id.recomdedtxtlabel);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_description = (TextView) view.findViewById(R.id.tv_description);
        recyclerView = (RecyclerView) view.findViewById(R.id.gallery_videos_recycler_view);
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);

        newsimg.setVisibility(View.GONE);
        playVideo.setVisibility(View.VISIBLE);
        tv_name.setVisibility(View.GONE);
    }


    private void initializeRest() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        myCeleAdapter = new CelebrityBioAdapterImagesViewHolder();
        recyclerView.setAdapter(myCeleAdapter);
        toolbar_back_navigation_btn.setOnClickListener(this);
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
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    private void playLocalVideo() {
        //Creating MediaController
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(playVideo);
        //specify the location of media file
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.demovideo);
        //Setting MediaController and URI, then starting the videoView
        playVideo.setMediaController(mediaController);
        playVideo.setVideoURI(uri);
        playVideo.requestFocus();
        playVideo.start();
    }
}
