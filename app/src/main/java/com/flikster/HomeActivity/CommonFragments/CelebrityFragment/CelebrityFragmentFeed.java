package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

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

public class CelebrityFragmentFeed extends Fragment {
    View view;
    RecyclerView celebrityFragmentFeedRecycler;
    RecyclerView.LayoutManager celebrityFragmentFeedLayoutManager;
    CelebrityFeedAdapter celebrityFeedAdapter;
    FragmentManager fragmentManager;
    FeedInnerData hits;
    ApiInterface apiInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        initializeViews();
        initializeRest();
        retrofitInit();
        return view;
    }

    private void retrofitInit() {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/_search/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getMovieFeedData(true, 4,0, "slug:\"" + getArguments().getString("slug") + "\"");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                hits = response.body().getHits();
                celebrityFeedAdapter = new CelebrityFeedAdapter(getActivity(),
                        fragmentManager, getArguments().getString("coverpic"),
                        getArguments().getString("biography"),
                        getArguments().getString("dateOfBirth"),
                        getArguments().getStringArrayList("role"),
                        getArguments().getString("placeOfBirth"),
                        getArguments().getString("name"),
                        hits,
                        getArguments().getString("userId"),
                        getArguments().getString("entityId"),
                        getArguments().getString("slug"));
                celebrityFragmentFeedRecycler.setAdapter(celebrityFeedAdapter);
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("xxx", "xxx" + call + t);
            }
        });
    }

    private void initializeRest() {
        celebrityFragmentFeedLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        celebrityFragmentFeedRecycler.setLayoutManager(celebrityFragmentFeedLayoutManager);
    }

    private void initializeViews() {
        celebrityFragmentFeedRecycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager = getActivity().getSupportFragmentManager();
    }
}
