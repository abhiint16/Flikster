package com.flikster;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
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
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by abhishek on 16-10-2017.
 */

public class NewsOnClickFragment extends Fragment implements View.OnClickListener {
    View view;
    VideoView playVideo;
    ImageView newsimg;
    Button toolbar_back_navigation_btn;
    TextView activity_my_bag_toolbar_title, titlehedertxt, recomdedtxtlabel, tv_name, tv_description;
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
        titlehedertxt.setText("Two Years Of Bahubali: Lessons Its Success Taught The Industry");
        tv_description.setText("Ranbir said: \"I have really liked Prabhas. He is amazing in Baahubali.\"\n" +
                "\n" +
                "Meanwhile, the Bollywood actor is awaiting the release of Jagga Jasoos, which will hit the screens on July 14.\n" +
                "\n" +
                "Ranbir has also co-produced Jagga Jasoos, helmed by Anurag Basu.\n" +
                "\n" +
                "Talking about his experience as a producer, he earlier said: \"I am a bad producer. I can't get things done, can't do man management. I was lucky that a lot of the work was taken care of by Anurag Basu. I can be happy that I can just collect awards for the film.\"");
        return view;
    }

    private void headerTitlesChange() {
        activity_my_bag_toolbar_title.setText("News");
        recomdedtxtlabel.setText("Recommended News");
    }

    private void initializeViews() {
        activity_my_bag_toolbar_title = (TextView) view.findViewById(R.id.activity_my_bag_toolbar_title);
        playVideo = (VideoView) view.findViewById(R.id.playVideo);
        newsimg = (ImageView) view.findViewById(R.id.newsimg);
        titlehedertxt = (TextView) view.findViewById(R.id.titlehedertxt);
        recomdedtxtlabel = (TextView) view.findViewById(R.id.recomdedtxtlabel);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_description = (TextView) view.findViewById(R.id.tv_description);
        recyclerView = (RecyclerView) view.findViewById(R.id.gallery_videos_recycler_view);
        toolbar_back_navigation_btn = (Button) view.findViewById(R.id.toolbar_back_navigation_btn);

        newsimg.setVisibility(View.VISIBLE);
        playVideo.setVisibility(View.GONE);
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
}
