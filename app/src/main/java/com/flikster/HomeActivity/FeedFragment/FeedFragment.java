package com.flikster.HomeActivity.FeedFragment;

import android.app.Activity;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 04-10-2017.
 */

public class FeedFragment extends Fragment {
    ApiInterface apiInterface;
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager feedLayoutManager;
    FeedRecyclerAdapter feedAdapter;
    FragmentManager fragmentManager;
    FeedInnerData outerHits;
    Integer Count;
    Testing testing;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        retrofitInit();
        initializeViews();
        initializeRest();
        return view;
    }

    private void retrofitInit() {
        apiInterface = ApiClient.getClient("http://apiv3-es.flikster.com/contents/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies(true,100,"*");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                outerHits = response.body().getHits();
                Count = outerHits.getTotal();
                feedAdapter = new FeedRecyclerAdapter(getActivity(), fragmentManager, outerHits, Count, testing);
                fragment_common_recyclerview_recycler.setAdapter(feedAdapter);
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initializeRest() {
        feedLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_recycler.setLayoutManager(feedLayoutManager);
        fragment_common_recyclerview_recycler.setBackgroundColor(getActivity().getResources().getColor(R.color.colorImageBackgroundGrey));
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    public interface Testing {
        void test(String name, Fragment fragment, int getClass);

        void galleryCardOnClick(List<String> galleryImgLinks, String name, String profilePic, String type, String title, Fragment fragment);

        void newsCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle, String description,Fragment fragment,String contentType);

        void videoCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle, String description,String videolink,Fragment fragment,String contentType);

        void seeMoreComments(String userName,String userId,String entityId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        testing = (Testing) activity;
    }
}
