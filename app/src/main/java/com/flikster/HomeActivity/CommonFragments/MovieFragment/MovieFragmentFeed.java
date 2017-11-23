package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MovieFragmentFeed extends Fragment{
    View view;
    RecyclerView movieFragmentFeedRecycler;
    RecyclerView.LayoutManager movieFragmentFeedLayoutManager;
    MovieFeedAdapter movieFeedAdapter;
    FragmentManager fragmentManager;
    ApiInterface apiInterface;
    FeedInnerData hits;
    String contentType;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_common_recyclerview,container,false);
        initializeViews();
        retrofitInit();
        initializeRest();
        return  view;
    }

    private void retrofitInit() {
        apiInterface = ApiClient.getClient("http://apiv3-es.flikster.com/contents/_search/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getMovieFeedData(true,"tags:mayabazar");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                hits = response.body().getHits();
                movieFeedAdapter = new MovieFeedAdapter(getActivity(),fragmentManager,getArguments().getString("coverpic"),
                        getArguments().getString("censor"),getArguments().getString("dor"),getArguments().getStringArrayList("genre"),
                        getArguments().getString("duration"),getArguments().getString("title"),getArguments().getString("slug"),hits);
                movieFragmentFeedRecycler.setAdapter(movieFeedAdapter);
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("xxx", "xxx" + call + t);
            }
        });
    }

    private void initializeRest() {
        movieFragmentFeedLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        movieFragmentFeedRecycler.setLayoutManager(movieFragmentFeedLayoutManager);
    }

    private void initializeViews() {
        movieFragmentFeedRecycler=(RecyclerView)view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager=getActivity().getSupportFragmentManager();
    }
}
