package com.flikster.HomeActivity.WatchFragment;

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
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieInfoAdapter;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.FeedFragment.FeedRecyclerAdapter;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 12-10-2017.
 */

public class WatchFragment extends Fragment {
    View view;
    RecyclerView movieFragmentInfoRecycler;
    RecyclerView.LayoutManager movieFragmentInfoLayoutManager;
    WatchAdapter watchAdapter;
    FragmentManager fragmentManager;
    ApiInterface apiInterface;
    FeedInnerData outerHits;
    Integer Count;
    FeedFragment.Testing testing;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        retrofitInit();
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        movieFragmentInfoLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        movieFragmentInfoRecycler.setLayoutManager(movieFragmentInfoLayoutManager);
//        watchAdapter = new WatchAdapter(getActivity(), fragmentManager);
//        movieFragmentInfoRecycler.setAdapter(watchAdapter);
    }

    private void initializeViews() {
        movieFragmentInfoRecycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager = getActivity().getSupportFragmentManager();
    }


    private void retrofitInit() {
        apiInterface = ApiClient.getClient("http://apiv3-es.flikster.com/contents/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies(true,"*");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                outerHits = response.body().getHits();
                Count = outerHits.getTotal();
                watchAdapter = new WatchAdapter(getActivity(), fragmentManager, outerHits, Count, testing);
                movieFragmentInfoRecycler.setAdapter(watchAdapter);
//                feedAdapter = new FeedRecyclerAdapter(getActivity(), fragmentManager, items, Count, testing);

            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }
}
