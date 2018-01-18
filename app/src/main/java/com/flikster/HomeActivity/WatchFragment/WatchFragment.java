package com.flikster.HomeActivity.WatchFragment;

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
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieInfoAdapter;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.FeedFragment.FeedRecyclerAdapter;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.R;
import com.leo.simplearcloader.SimpleArcLoader;

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
    SimpleArcLoader simpleArcLoader;
    WatchFragCommInterface watchFragCommInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        initializeViews();
        initializeRest();
        //retrofitInit();
        return view;
    }

    private void initializeRest() {
        movieFragmentInfoLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        movieFragmentInfoRecycler.setLayoutManager(movieFragmentInfoLayoutManager);
        watchAdapter = new WatchAdapter(getActivity(), fragmentManager, outerHits, Count, watchFragCommInterface);
        movieFragmentInfoRecycler.setAdapter(watchAdapter);
//        watchAdapter = new WatchAdapter(getActivity(), fragmentManager);
//        movieFragmentInfoRecycler.setAdapter(watchAdapter);
    }

    private void initializeViews() {
        movieFragmentInfoRecycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager = getActivity().getSupportFragmentManager();
        simpleArcLoader=(SimpleArcLoader)view.findViewById(R.id.arc_loader);
    }


    private void retrofitInit() {
        simpleArcLoader.setVisibility(View.VISIBLE);
        simpleArcLoader.start();
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies(true,"createdAt:desc",100,"status:Active");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                outerHits = response.body().getHits();
                Count = outerHits.getTotal();
                watchAdapter = new WatchAdapter(getActivity(), fragmentManager, outerHits, Count, watchFragCommInterface);
                simpleArcLoader.setVisibility(View.GONE);
                simpleArcLoader.stop();
                movieFragmentInfoRecycler.setAdapter(watchAdapter);
//                feedAdapter = new FeedRecyclerAdapter(getActivity(), fragmentManager, items, Count, testing);

            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }


    public interface WatchFragCommInterface {
        void carouselContainerClick(String toolbarTitle,String url,Fragment fragment);
        void carouselItemClick(String toolbarTitle,String img,String title,String audio,String type,Fragment fragment);
        void carouselItemToMovie(String slug,Fragment fragment);
        void carouselItemToGallery(List<String> galleryImgLinks,String name,String profilePic,String type,String title,Fragment fragment);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        watchFragCommInterface = (WatchFragCommInterface) activity;
    }
}
