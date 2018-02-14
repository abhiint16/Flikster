package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import android.app.Activity;
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
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieAdapter;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
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
    String slug, userId, entityId;
    CelebrityData.CelebrityInnerData hits;
    Bundle arguments = new Bundle();
    CelebItemClickInterface celebItemClickInterface;


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
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/celebrity/_search/").create(ApiInterface.class);
        Call<CelebrityData> call = apiInterface.getCelebrityData("http://apiservice-ec.flikster.com/celebrity/_search?pretty=true&q=slug:" + slug);
        call.enqueue(new Callback<CelebrityData>() {
            @Override
            public void onResponse(Call<CelebrityData> call, Response<CelebrityData> response) {
                hits = response.body().getHits();
                if (hits.getHits().size() != 0) {
                    if (hits.getHits().get(0).get_source().getCoverPic()!=null)
                    arguments.putString("coverpic", hits.getHits().get(0).get_source().getCoverPic());
                    else
                    arguments.putString("coverpic", "");
                    if (hits.getHits().get(0).get_source().getBiography()!=null)
                    arguments.putString("biography", hits.getHits().get(0).get_source().getBiography());
                    else
                        arguments.putString("biography","");
                    if (hits.getHits().get(0).get_source().getDateOfBirth()!=null)
                    arguments.putString("dateOfBirth", hits.getHits().get(0).get_source().getDateOfBirth());
                    else
                        arguments.putString("dateOfBirth", "");
                    if (hits.getHits().get(0).get_source().getRole()!=null&&hits.getHits().get(0).get_source().getRole().size()!=0)
                    arguments.putStringArrayList("role", (ArrayList<String>) hits.getHits().get(0).get_source().getRole());
                    if (hits.getHits().get(0).get_source().getPlaceOfBirth()!=null)
                    arguments.putString("placeOfBirth", hits.getHits().get(0).get_source().getPlaceOfBirth());
                    else
                        arguments.putString("placeOfBirth", "");
                    if (hits.getHits().get(0).get_source().getName()!=null)
                    arguments.putString("name", hits.getHits().get(0).get_source().getName());
                    else
                        arguments.putString("name", "");
                    arguments.putString("slug", slug);
                    arguments.putString("userId", userId);
                    arguments.putString("entityId", entityId);
                    celebrityAdapter = new CelebrityAdapter(getChildFragmentManager(), arguments);
                    viewPager.setOffscreenPageLimit(2);
                    viewPager.setAdapter(celebrityAdapter);
                    viewPager.setCurrentItem(1);
                } else {
                    arguments.putString("coverpic", "");
                    arguments.putString("biography", "");
                    arguments.putString("dateOfBirth", "");
                    arguments.putStringArrayList("role", new ArrayList<String>() {{
                        add("");
                        add("");
                    }});
                    arguments.putString("placeOfBirth", "");
                    arguments.putString("name", "");
                    arguments.putString("slug", slug);
                    arguments.putString("userId", userId);
                    arguments.putString("entityId", entityId);
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
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_back_navigation_btn) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .commit();
        }
    }

    public void updateInfo(String slug, String userId, String entityId) {
        this.userId = userId;
        this.entityId = entityId;
        this.slug = slug;
    }

    public interface CelebItemClickInterface {
        void galleryCardOnClick(List<String> galleryImgLinks, String name, String profilePic, String type, String title,
                                Fragment fragment,String userId,String entityId,String cardId,String slug);

        void newsCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle,
                             String description, Fragment fragment, String contentType,String userId, String entityId,String cardId);

        void videoCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle,
                              String description, String videolink, Fragment fragment, String contentType,
                              String userId, String entityId,String cardId);
        void onGalleryContainerClick(String productId, List<String> size, String userId, String price, String profilePic, String productTitle,
                                     String productSlug, List<String> imageGallery,
                                     String profilepic,List<String> role,String name,String title,
                                     Fragment fragment);
        void seeMoreComments(String userName, String userId, String entityId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        celebItemClickInterface = (CelebItemClickInterface) activity;
    }
}
