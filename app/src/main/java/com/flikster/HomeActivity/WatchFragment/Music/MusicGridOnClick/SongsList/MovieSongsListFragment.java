package com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.HomeActivity.WatchFragment.WatchFragment;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 01-11-2017.
 */

public class MovieSongsListFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManagerShopByVideoFragment;
    MovieSongsListAdapter shopByVideoFragmentAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    ImageButton toolbar_frag_multiicons_back_navigation, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart, toolbar_frag_multiicons_search, toolbar_frag_multiicons_overflow;
    FragmentManager fragmentManager;
    TextView toolbar_frag_multiicons_title;
    String toolbarTitle;
    String img;
    String title;
    String audio;
    WatchPlayAudioOrVideoInterafce watchPlayAudioOrVideoInterafce;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_music, container, false);
        initializeViews();
        initializeRest();
        return view;
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

    private void initializeRest() {
        fragmentManager = getActivity().getSupportFragmentManager();
        toolbar_frag_multiicons_title.setText(toolbarTitle);
        layoutManagerShopByVideoFragment = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManagerShopByVideoFragment);
        shopByVideoFragmentAdapter = new MovieSongsListAdapter(getActivity(), fragmentManager,img,title,audio,watchPlayAudioOrVideoInterafce);
        fragment_common_recyclerview_recycler.setAdapter(shopByVideoFragmentAdapter);
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        toolbar_frag_multiicons_notification.setVisibility(View.GONE);
        toolbar_frag_multiicons_cart.setVisibility(View.GONE);
        toolbar_frag_multiicons_overflow.setVisibility(View.GONE);
        toolbar_frag_multiicons_search.setVisibility(View.GONE);
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        toolbar_frag_multiicons_toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_title = (TextView) view.findViewById(R.id.toolbar_frag_multiicons_title);
        toolbar_frag_multiicons_back_navigation = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
        toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        toolbar_frag_multiicons_overflow = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_overflow);
        toolbar_frag_multiicons_search = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_search);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
            fragmentManager.popBackStackImmediate();
        }
    }

    public void getAllData(String toolbarTitle, String img, String title, String audio)
    {
        this.toolbarTitle=toolbarTitle;
        this.title=title;
        this.img=img;
        this.audio=audio;
    }

    public interface WatchPlayAudioOrVideoInterafce {
        void playAudioOrVideoPage(String audioLink,Fragment fragment,String audioImg);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        watchPlayAudioOrVideoInterafce = (WatchPlayAudioOrVideoInterafce) activity;
    }
}
