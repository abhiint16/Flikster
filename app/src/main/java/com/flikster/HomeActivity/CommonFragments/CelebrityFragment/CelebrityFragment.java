package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

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

import com.flikster.GlobalDataStorage;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieAdapter;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 04-10-2017.
 */

public class CelebrityFragment extends Fragment implements View.OnClickListener {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    CelebrityAdapter celebrityAdapter;
    FragmentManager fragmentManager;
    TextView toolbar_frag_title;
    ApiInterface apiInterface;
    ImageButton toolbar_back_navigation_btn;
    String slug;
    CelebrityData.CelebrityInnerData hits;
    Bundle arguments = new Bundle();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_celebrity, container, false);
        initializeViews();
        tempMethod();
        initializeRest();
        return view;
    }

    private void tempMethod() {
        apiInterface = ApiClient.getClient("http://apiv3-es.flikster.com/celebrity/_search?pretty=true&q=slug:").create(ApiInterface.class);
        Call<CelebrityData> call = apiInterface.getCelebrityData("http://apiv3-es.flikster.com/celebrity/_search?pretty=true&q=slug:" + slug);
        call.enqueue(new Callback<CelebrityData>() {
            @Override
            public void onResponse(Call<CelebrityData> call, Response<CelebrityData> response) {
                hits = response.body().getHits();
                if (hits.getHits().size() != 0){
                    arguments.putString("coverpic", hits.getHits().get(0).get_source().getCoverPic());
                    arguments.putString("biography", hits.getHits().get(0).get_source().getBiography());
                    arguments.putString("dateOfBirth", hits.getHits().get(0).get_source().getDateOfBirth());
                    arguments.putStringArrayList("role", (ArrayList<String>) hits.getHits().get(0).get_source().getRole());
                    arguments.putString("placeOfBirth",hits.getHits().get(0).get_source().getPlaceOfBirth());
                    arguments.putString("name",hits.getHits().get(0).get_source().getName());
                    arguments.putString("slug",slug);
                    celebrityAdapter = new CelebrityAdapter(getChildFragmentManager(), arguments);
                    viewPager.setOffscreenPageLimit(2);
                    viewPager.setAdapter(celebrityAdapter);
                    viewPager.setCurrentItem(1);
                }else {
                    arguments.putString("coverpic", "");
                    arguments.putString("biography", "");
                    arguments.putString("dateOfBirth", "");
                    arguments.putStringArrayList("role", new  ArrayList<String>(){{add("");add("");}});
                    arguments.putString("placeOfBirth", "");
                    arguments.putString("name", "");
                    arguments.putString("slug",slug);
                    celebrityAdapter = new CelebrityAdapter(getChildFragmentManager(), arguments);
                    viewPager.setAdapter(celebrityAdapter);
                    viewPager.setOffscreenPageLimit(2);
                    viewPager.setCurrentItem(1);
                }

            }

            @Override
            public void onFailure(Call<CelebrityData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    private void initializeRest() {
        arguments.putString("slug", slug);
        tabLayout.setupWithViewPager(viewPager);
//        toolbar_frag_title.setText("Celebrity");
        toolbar_back_navigation_btn.setOnClickListener(this);
    }

    private void initializeViews() {
        viewPager = (ViewPager) view.findViewById(R.id.celebrity_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.celebrity_tablayout);
        toolbar_frag_title = (TextView) view.findViewById(R.id.toolbar_frag_title);
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        tabLayout.setTabTextColors(getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.black));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_back_navigation_btn) {
            getFragmentManager().popBackStackImmediate();
        }
    }

    public void updateInfo(String slug) {
        this.slug = slug;
    }
}
