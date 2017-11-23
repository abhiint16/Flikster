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

import com.flikster.GlobalDataStorage;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieInfoAdapter;
import com.flikster.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 12-10-2017.
 */

public class CelebrityFragmentBio extends Fragment{
    View view;
    RecyclerView celebrityFragmentBioRecycler;
    RecyclerView.LayoutManager celebrityFragmentBioLayoutManager;
    CelebrityBioAdapter celebrityBioAdapter;
    FragmentManager fragmentManager;
    Bundle bundle;
    ApiInterface apiInterface;
    List<CelebrityData.CelebrityInnerData> items;
    String slug;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_common_recyclerview,container,false);
        initializeViews();
        bundle=getArguments();
        //this.slug=bundle.getString("slug");
       // retrofitInit();
        initializeRest();
        return  view;
    }

    /*private void retrofitInit() {
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/celeb-ms/getCelebBySlug/").create(ApiInterface.class);
        Call<CelebrityData> call = apiInterface.getCelebrityData("http://apiv3.flikster.com/v3/celeb-ms/getCelebBySlug/"+slug);
        call.enqueue(new Callback<CelebrityData>() {
            @Override
            public void onResponse(Call<CelebrityData> call, Response<CelebrityData> response) {
                items = response.body().getItems();
                celebrityBioAdapter = new CelebrityBioAdapter(getActivity(), fragmentManager,items);
                celebrityFragmentBioRecycler.setAdapter(celebrityBioAdapter);
            }

            @Override
            public void onFailure(Call<CelebrityData> call, Throwable t) {
                Log.e("vvvvvvvvvv","vv"+call+t);
            }
        });
    }*/

    private void initializeRest() {
        celebrityFragmentBioLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        celebrityFragmentBioRecycler.setLayoutManager(celebrityFragmentBioLayoutManager);
        celebrityBioAdapter = new CelebrityBioAdapter(getActivity(), fragmentManager,getArguments().getString("coverpic"),
                getArguments().getString("biography"),getArguments().getString("dateOfBirth"),getArguments().getStringArrayList("role"),
                getArguments().getString("placeOfBirth"),
                getArguments().getString("name"));
        celebrityFragmentBioRecycler.setAdapter(celebrityBioAdapter);
    }

    private void initializeViews() {
        celebrityFragmentBioRecycler=(RecyclerView)view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager=getActivity().getSupportFragmentManager();
    }
}
