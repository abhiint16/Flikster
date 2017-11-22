package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityAdapter;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityData;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 16-10-2017.
 */

public class MovieFragment extends Fragment implements View.OnClickListener {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    MovieAdapter movieAdapter;
    FragmentManager fragmentManager;
    TextView toolbar_frag_title;
    ApiInterface apiInterface;
    ImageButton toolbar_back_navigation_btn;
    Bundle arguments = new Bundle();
    MovieData.MovieInnerData hits;
    String slug;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_celebrity,container,false);
        initializeViews();
        Log.e("slug0101001",""+slug);
        tempMethod();
        initializeRest();
        return view;
    }

    private void tempMethod() {
        Log.e("slug1",""+slug);
        apiInterface = ApiClient.getClient("http://apiv3-es.flikster.com/movies/_search?pretty=true&q=slug:").create(ApiInterface.class);
        Call<MovieData> call = apiInterface.getMovieData("http://apiv3-es.flikster.com/movies/_search?pretty=true&q=slug:" +"fidaa");
        call.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                hits = response.body().getHits();
                arguments.putString("coverpic",hits.getHits().get(0).get_source().getCoverPic());
                arguments.putString("censor",hits.getHits().get(0).get_source().getCensorCertificate());
                arguments.putString("dor",hits.getHits().get(0).get_source().getDateOfRelease());
                arguments.putStringArrayList("genre", (ArrayList<String>) hits.getHits().get(0).get_source().getGenre());
                arguments.putString("duration",hits.getHits().get(0).get_source().getDuration());
                arguments.putString("title",hits.getHits().get(0).get_source().getTitle());
                Log.e("slugjsjjsjsj1","ajja"+slug+" "+arguments);
                movieAdapter = new MovieAdapter(getChildFragmentManager(),arguments);
                viewPager.setAdapter(movieAdapter);
                viewPager.setCurrentItem(1);
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    private void initializeViews() {
        viewPager=(ViewPager)view.findViewById(R.id.celebrity_pager);
        tabLayout=(TabLayout)view.findViewById(R.id.celebrity_tablayout);
        toolbar_frag_title=(TextView)view.findViewById(R.id.toolbar_frag_title);
        toolbar_back_navigation_btn=(ImageButton)view.findViewById(R.id.toolbar_back_navigation_btn);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        tabLayout.setTabTextColors(getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.black));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
    }

    private void initializeRest() {
        arguments.putString("slug",slug);
        Log.e("slugjsjjsjsj","ajja"+slug+" "+arguments);
        tabLayout.setupWithViewPager(viewPager);
        toolbar_frag_title.setText("Movies");
        toolbar_back_navigation_btn.setOnClickListener(this);
    }

    public  void updateInfo(String slug)
    {
        this.slug=slug;
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.toolbar_back_navigation_btn)
        {
            getFragmentManager().popBackStackImmediate();
        }
    }
}
