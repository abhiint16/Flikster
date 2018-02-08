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
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreData;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreInnerData;
import com.flikster.R;
import com.rohitarya.glide.facedetection.transformation.core.GlideFaceDetector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 12-10-2017.
 */

public class CelebrityFragmentStore extends Fragment {
    View view;
    RecyclerView celebrityFragmentStoreRecycler;
    RecyclerView.LayoutManager celebrityFragmentStoreLayoutManager;
    CelebrityStoreAdapter celebrityStoreAdapter;
    FragmentManager fragmentManager;
    ApiInterface apiInterface;
    AllStoreInnerData hits;

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
        GlideFaceDetector.initialize(getActivity());
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/products/_search/").create(ApiInterface.class);
        Call<AllStoreData> call = apiInterface.getCelebMovieStoreData(true, 4,0, "tags:\"" + getArguments().getString("slug") + "\"");
        call.enqueue(new Callback<AllStoreData>() {
            @Override
            public void onResponse(Call<AllStoreData> call, Response<AllStoreData> response) {
                hits = response.body().getHits();
                celebrityStoreAdapter = new CelebrityStoreAdapter(getActivity(), fragmentManager, getArguments().getString("coverpic"),
                        getArguments().getString("biography"),
                        getArguments().getString("dateOfBirth"),
                        getArguments().getStringArrayList("role"),
                        getArguments().getString("placeOfBirth"),
                        getArguments().getString("name"), hits,
                        getArguments().getString("userId"),
                        getArguments().getString("entityId"),
                        getArguments().getString("slug"));
                celebrityFragmentStoreRecycler.setAdapter(celebrityStoreAdapter);
            }

            @Override
            public void onFailure(Call<AllStoreData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initializeRest() {
        celebrityFragmentStoreLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        celebrityFragmentStoreRecycler.setLayoutManager(celebrityFragmentStoreLayoutManager);
    }

    private void initializeViews() {
        celebrityFragmentStoreRecycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GlideFaceDetector.releaseDetector();
    }
}
