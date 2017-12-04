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
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieInfoAdapter;
import com.flikster.HomeActivity.FeedFragment.FeedRecyclerAdapter;
import com.flikster.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MovieFragmentInfo extends Fragment {
    View view;
    RecyclerView movieFragmentInfoRecycler;
    RecyclerView.LayoutManager movieFragmentInfoLayoutManager;
    MovieInfoAdapter movieInfoAdapter;
    FragmentManager fragmentManager;
    Bundle bundle;
    ApiInterface apiInterface;
    String slug;
    MovieData.MovieInnerData hits;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        initializeViews();
        bundle=getArguments();
        this.slug=bundle.getString("slug");
        Log.e("slugMovieInfo1", "SSS");
        Log.e("slugMovieInfo1", slug + "Shiva");
        initializeRest();
        retrofitInit();
        return view;
    }

    private void retrofitInit() {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/movies/_search?pretty=true&q=slug:").create(ApiInterface.class);
        Call<MovieData> call = apiInterface.getMovieData("http://apiservice-ec.flikster.com/movies/_search?pretty=true&q=slug:"+"fidaa");
        call.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                hits = response.body().getHits();
                movieInfoAdapter = new MovieInfoAdapter(getActivity(), fragmentManager,getArguments().getString("coverpic"),
                        getArguments().getString("censor"),getArguments().getString("dor"),getArguments().getStringArrayList("genre"),
                        getArguments().getString("duration"),getArguments().getString("title"),getArguments().getString("storyline"),
                        getArguments().getString("slug"),hits);
                movieFragmentInfoRecycler.setAdapter(movieInfoAdapter);
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Log.e("vvvvvvvvvv","vv"+call+t);
            }
        });
    }

    private void initializeRest() {
        movieFragmentInfoLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        movieFragmentInfoRecycler.setLayoutManager(movieFragmentInfoLayoutManager);
    }

    private void initializeViews() {
        movieFragmentInfoRecycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager = getActivity().getSupportFragmentManager();
    }
}
