package com.flikster.HomeActivity.WatchFragment.Music;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.WatchFragment.WatchFragment;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 01-11-2017.
 */

public class MusicGridFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    GridLayoutManager layoutManagerShopByVideoFragment;
    MusicGridAdapter musicGridAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    TextView toolbar_frag_multiicons_title;
    ImageButton toolbar_frag_multiicons_back_navigation;
    FragmentManager fragmentManager;
    String tootbarTitle;
    WatchAudioVideoSendFromGridFrag watchAudioVideoSendFromGridFrag;
    String url;
    String itemType;
    int count=0;
    ApiInterface apiInterface;
    FeedInnerData feedData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_music, container, false);
        initializeViews();
        initializeRest();
        initRetrofit();
        return view;
    }

    private void initRetrofit() {
        Log.e("check url","check url"+"http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=10&from="+count+url);
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/")
                .create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies("http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=10&from="+count+url);
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                feedData=response.body().getHits();
                musicGridAdapter = new MusicGridAdapter(getActivity(), fragmentManager,feedData,watchAudioVideoSendFromGridFrag,itemType,url);
                fragment_common_recyclerview_recycler.setAdapter(musicGridAdapter);
            }
            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
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
        toolbar_frag_multiicons_title.setText(tootbarTitle);
        fragmentManager = getActivity().getSupportFragmentManager();
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        layoutManagerShopByVideoFragment = new GridLayoutManager(getActivity(), 2);
        layoutManagerShopByVideoFragment.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if ((position>feedData.getHits().size()-1))
                    return 2;
                else return 1;
            }
        });
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManagerShopByVideoFragment);
        //toolbar_frag_multiicons_notification.setVisibility(View.GONE);
        //toolbar_frag_multiicons_cart.setVisibility(View.GONE);
        //toolbar_frag_multiicons_overflow.setVisibility(View.GONE);
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        toolbar_frag_multiicons_toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_title = (TextView) view.findViewById(R.id.toolbar_frag_multiicons_title);
        toolbar_frag_multiicons_back_navigation = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        //toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
        ///toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        //toolbar_frag_multiicons_overflow = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_overflow);
    }

    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.toolbar_frag_multiicons_notification) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_container,new WatchFragment())
                .commit();
//        }
    }

    public void getAllData(String tootbarTitle,String url,String itemType)
    {
        this.tootbarTitle=tootbarTitle;
        this.url=url;
        this.itemType=itemType;
    }

    public interface WatchAudioVideoSendFromGridFrag {
        void sendAudioVideoLink(String toolbarTitle,String img,String title,String audioVideoLink,Fragment fragment,String itemType);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        watchAudioVideoSendFromGridFrag = (WatchAudioVideoSendFromGridFrag) activity;
    }

}
