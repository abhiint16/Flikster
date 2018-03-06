package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityAdapter;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityData;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.R;
import com.rohitarya.glide.facedetection.transformation.FaceCenterCrop;
import com.rohitarya.glide.facedetection.transformation.core.GlideFaceDetector;

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
    TextView toolbar_frag_title,card_movie_feed_profile_moviename,card_movie_feed_profile_censor,card_movie_feed_profile_dor,
            card_movie_feed_profile_genre,card_movie_feed_profile_dur;
    ApiInterface apiInterface;
    ImageButton toolbar_back_navigation_btn;
    Bundle arguments = new Bundle();
    MovieData.MovieInnerData hits;
    String slug, userId, entityId;
    MovieItemClickInterface movieItemClickInterface;
    ImageView card_movie_common_profile_coverpic;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    Toolbar toolbar_main;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movie_feed_container, container, false);
        initializeViews();
        tempMethod();
        initializeRest();
        return view;
    }

    private void tempMethod() {
        //Log.e("slug1", "" + slug);
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/movies/_search/").create(ApiInterface.class);
        Call<MovieData> call = apiInterface.getMovieData("http://apiservice-ec.flikster.com/movies/_search?pretty=true&q=slug:" + slug);
        call.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                hits = response.body().getHits();
                Log.e("cehck for coverpic","cehkc for cover pic"+hits.getHits().get(0).get_source().getCoverPic()+"AND"+slug);
                Glide.with(getActivity()).load(hits.getHits().get(0).get_source().getCoverPic()).asBitmap()
                        .transform(new FaceCenterCrop())
                        .into(card_movie_common_profile_coverpic);
                card_movie_feed_profile_moviename.setText(hits.getHits().get(0).get_source().getTitle());
                card_movie_feed_profile_censor.setText(hits.getHits().get(0).get_source().getCensorCertificate());
                card_movie_feed_profile_dor.setText(hits.getHits().get(0).get_source().getDateOfRelease());
                card_movie_feed_profile_genre.setText(formatGenre((ArrayList<String>) hits.getHits().get(0).get_source().getGenre()));
                card_movie_feed_profile_dur.setText(hits.getHits().get(0).get_source().getDuration());
                /*collapsingToolbarLayout.setTitleEnabled(true);
                collapsingToolbarLayout.setTitle(hits.getHits().get(0).get_source().getTitle());
                collapsingToolbarLayout.setExpandedTitleColor(getActivity().getResources().getColor(R.color.black));
                collapsingToolbarLayout.setCollapsedTitleTextColor(getActivity().getResources().getColor(R.color.white));*/

                arguments.putString("coverpic", hits.getHits().get(0).get_source().getCoverPic());
                arguments.putString("censor", hits.getHits().get(0).get_source().getCensorCertificate());
                arguments.putString("dor", hits.getHits().get(0).get_source().getDateOfRelease());
                arguments.putStringArrayList("genre", (ArrayList<String>) hits.getHits().get(0).get_source().getGenre());
                arguments.putString("duration", hits.getHits().get(0).get_source().getDuration());
                arguments.putString("title", hits.getHits().get(0).get_source().getTitle());
                arguments.putString("storyline", hits.getHits().get(0).get_source().getStoryLine());
                arguments.putString("slug", slug);
                arguments.putString("userId", userId);
                arguments.putString("entityId", entityId);
                Log.e("moviepage", "access" + slug + " " + arguments);
                viewPager.setOffscreenPageLimit(2);
                movieAdapter = new MovieAdapter(getChildFragmentManager(), arguments);
                viewPager.setAdapter(movieAdapter);
                viewPager.setCurrentItem(1);
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Log.e("xxx", "xxx" + call + t);
            }
        });
    }

    public String formatGenre(ArrayList<String> genre1) {
        String genre = "";
        for (int i = 0; i < genre1.size(); i++) {
            if (i < genre1.size()- 1)
                genre = genre + genre1.get(i) + " | ";
            else
                genre = genre + genre1.get(i);
        }
        return genre;
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
        GlideFaceDetector.releaseDetector();
    }

    private void initializeViews() {
        toolbar_main=(Toolbar)view.findViewById(R.id.toolbar_main);
        viewPager = (ViewPager) view.findViewById(R.id.celebrity_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.celebrity_tablayout);
        collapsingToolbarLayout=(CollapsingToolbarLayout)view.findViewById(R.id.main_collapsing);
        appBarLayout=(AppBarLayout)view.findViewById(R.id.main_appbar);
        toolbar_frag_title = (TextView) view.findViewById(R.id.toolbar_frag_title);
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        tabLayout.setTabTextColors(getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.black));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        fragmentManager = getActivity().getSupportFragmentManager();
        card_movie_feed_profile_moviename=(TextView)view.findViewById(R.id.card_movie_feed_profile_moviename);
        card_movie_feed_profile_censor=(TextView)view.findViewById(R.id.card_movie_feed_profile_censor);
        card_movie_feed_profile_dor=(TextView)view.findViewById(R.id.card_movie_feed_profile_dor);
        card_movie_feed_profile_genre=(TextView)view.findViewById(R.id.card_movie_feed_profile_genre);
        card_movie_feed_profile_dur=(TextView)view.findViewById(R.id.card_movie_feed_profile_dur);
        card_movie_common_profile_coverpic=(ImageView)view.findViewById(R.id.card_movie_common_profile_coverpic);
    }

    private void initializeRest() {
        GlideFaceDetector.initialize(getActivity());
        arguments.putString("slug", slug);
        tabLayout.setupWithViewPager(viewPager);
//        toolbar_frag_title.setText("Movies");
        toolbar_back_navigation_btn.setOnClickListener(this);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset < 40) {
                    collapsingToolbarLayout.setTitle(hits.getHits().get(0).get_source().getTitle());
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getActivity().getResources().getColor(R.color.white));
                    toolbar_main.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
                    isShow = true;
                } else if(isShow) {
                    toolbar_main.setBackground(getActivity().getResources().getDrawable(R.drawable.testing));
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

    public void updateInfo(String slug, String userId, String entityId) {
        this.userId = userId;
        this.entityId = entityId;
        this.slug = slug;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_back_navigation_btn) {
            movieItemClickInterface.voidMethod(new FeedFragment());
            /*fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .commit();*/
        }
    }

    public interface MovieItemClickInterface {
        void galleryCardOnClick(List<String> galleryImgLinks, String name, String profilePic, String type, String title,
                                Fragment fragment,String userId,String entityId,String cardId,String slug);

        void newsCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle,
                             String description, Fragment fragment, String contentType,String userId, String entityId,String cardId,
                             String slug);

        void videoCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle,
                              String description, String videolink, Fragment fragment, String contentType,
                              String userId, String entityId,String cardId,String slug);
        void onGalleryContainerClick(String productId, List<String> size, String userId, String price, String profilePic, String productTitle,
                                     String productSlug, List<String> imageGallery,
                                     String profilepic,List<String> role,String name,String title,
                                     Fragment fragment);
        void voidMethod(Fragment fragment);
        void seeMoreComments(String userName, String userId, String entityId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        movieItemClickInterface = (MovieItemClickInterface) activity;
    }
}
