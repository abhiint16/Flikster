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
    List<CelebrityData.CelebrityInnerData> items;
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
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/celeb-ms/getCelebBySlug/").create(ApiInterface.class);
        Call<CelebrityData> call = apiInterface.getCelebrityData("http://apiv3.flikster.com/v3/celeb-ms/getCelebBySlug/" + slug);
        call.enqueue(new Callback<CelebrityData>() {
            @Override
            public void onResponse(Call<CelebrityData> call, Response<CelebrityData> response) {
                items = response.body().getItems();
                Log.e("CeleprofilePic", items.size()+"Shiv");
                if (items.size() != 0){
                    arguments.putString("profilepic", items.get(0).getProfilePic());
                    arguments.putString("coverpic", items.get(0).getCoverPic());
                    arguments.putString("name", items.get(0).getName());
                    arguments.putStringArrayList("role", (ArrayList<String>) items.get(0).getRole());
                    celebrityAdapter = new CelebrityAdapter(getChildFragmentManager(), arguments);
                    viewPager.setAdapter(celebrityAdapter);
                }else {
                    arguments.putString("profilepic", "");
                    arguments.putString("coverpic", "");
                    arguments.putString("name", "");
                    arguments.putStringArrayList("role", new  ArrayList<String>(){{add("");add("");}});
                    celebrityAdapter = new CelebrityAdapter(getChildFragmentManager(), arguments);
                    viewPager.setAdapter(celebrityAdapter);
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
        toolbar_frag_title.setText("Celebrity");
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
