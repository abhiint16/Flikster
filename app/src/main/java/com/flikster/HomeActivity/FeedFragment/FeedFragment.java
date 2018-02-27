package com.flikster.HomeActivity.FeedFragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;
import com.rohitarya.glide.facedetection.transformation.core.GlideFaceDetector;

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
    LinearLayoutManager feedLayoutManager;
    FeedRecyclerAdapter feedAdapter;
    FragmentManager fragmentManager;
    FeedInnerData outerHits;
    Integer Count;
    Testing testing;
    SimpleArcLoader mDialog;
    String industryname = "";
    String industryCompletedata;
    Toolbar toolbar;
    int positionOfLayoutManager;
   // int c=30;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        initializeViews();
        getIndustryType();
        initializeRest();
        retrofitInit();
        return view;
    }

    private void getIndustryType() {
        industryname = SharedPrefsUtil.getStringPreference(getContext(), "INDUSTRY_TYPE");
        if (industryname != null && !industryname.isEmpty()) {
            Log.e("industryname", industryname + "\"" + "\"");
            industryCompletedata = "industry:" + "\"" + industryname + "\"" + "%20AND%20contentType:" + "*";
            Log.e("industryCompletedata", industryCompletedata);
        } else {
            industryCompletedata = "industry:" + "\"" + "Tollywood" + "\"" + "%20AND%20contentType:" + "*";
        }

    }

    private void retrofitInit() {
        GlideFaceDetector.initialize(getActivity());
        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/")
                .create(ApiInterface.class);
        Log.e("industryCompletedata",industryCompletedata);
        Call<FeedData> call = apiInterface.getTopRatedMovies(
                true,
                "createdAt:desc",
                30,0, industryCompletedata);
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                try {
                    outerHits = response.body().getHits();
                    Count = outerHits.getTotal();
                    feedAdapter = new FeedRecyclerAdapter(getActivity(), fragmentManager, outerHits, Count,industryCompletedata, testing);
                    mDialog.setVisibility(View.GONE);
                    mDialog.stop();
                    fragment_common_recyclerview_recycler.setAdapter(feedAdapter);
                } catch (Exception e) {
//                    SharedPrefsUtil.setStringPreference(getContext(), "INDUSTRY_TYPE");
                    Log.e("errorhits", "null");
                }

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
        mDialog = (SimpleArcLoader) view.findViewById(R.id.arc_loader);
        //mDialog = new SimpleArcDialog(getActivity());
        //mDialog.setConfiguration(new ArcConfiguration(MainActivity.this));
        fragment_common_recyclerview_recycler.setBackgroundColor(getActivity().getResources().getColor(R.color.recycle_color));
        /*fragment_common_recyclerview_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && toolbar.isShown()) {
                    toolbar.scrollTo(0,toolbar.getHeight());
                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                    //toolbar.scrollBy(0,toolbar.getHeight());
                    //toolbar.setVisibility(View.GONE);
                } else if (dy < 0 ) {
                    toolbar.setVisibility(View.VISIBLE);
                }
            }
        });*/
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager = getActivity().getSupportFragmentManager();
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_main);
    }

    public interface Testing {
        void test(String name, Fragment fragment, int getClass, String userId, String entityId);

        void galleryCardOnClick(List<String> galleryImgLinks, String name, String profilePic, String type, String title,
                                Fragment fragment, String userId, String entityId,String cardId,String slug);

        void newsCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle,
                             String description, Fragment fragment, String contentType, String userId, String entityId,String cardId,
                             String slug);

        void videoCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle,
                              String description, String videolink, Fragment fragment, String contentType,
                              String userId, String entityId,String cardId,String slug);

        void seeMoreComments(String userName, String userId, String entityId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        testing = (Testing) activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GlideFaceDetector.releaseDetector();
    }


    /*@Override
    public void onPause() {
        super.onPause();
        positionOfLayoutManager = ((LinearLayoutManager)fragment_common_recyclerview_recycler.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LinearLayoutManager)fragment_common_recyclerview_recycler.getLayoutManager()).scrollToPosition(positionOfLayoutManager);
    }
*/
    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (positionOfLayoutManager!=0)
        {

        }
    }*/
}
