package com.flikster.HomeActivity.CommonFragments.NewsFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.FeedFragment.FeedRecyclerAdapter;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.R;
import com.flikster.Util.RoundedImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 16-10-2017.
 */

public class NewsOnClickFragment extends Fragment implements View.OnClickListener {
    View view;
    ImageView newsimg;
    ImageButton toolbar_back_navigation_btn;
    TextView toolbar_frag_title, titlehedertxt, fragment_common_recyclerview_with_tv_title, tv_name, tv_description;
    Context mContext;
    TextView tv_tag_name, tv_tag_desc;
    RecyclerView fragment_common_recyclerview_with_tv_recycler;
    NewsBottomHorRecyclerAdapter newsBottomHorRecyclerAdapter;
    String profilePic, title, type, bannerImg, headertitle, description,contentType;
    RoundedImageView profile_image;
    ApiInterface apiInterface;
    NewsData.NewsInnerData outerHits;
    int Count;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_onclick, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        headerTitlesChange();
        initializeRest();
        bottomHorRecyclerRetrofitInit();
        if (!headertitle.isEmpty()){
            titlehedertxt.setText(Html.fromHtml(headertitle) + "");
        }
        if (!description.isEmpty()){
            tv_description.setText(Html.fromHtml(description)+ "");
        }
        if(description.trim().length()==0)
            tv_description.setVisibility(View.GONE);
        Log.e("Picimag2", profilePic + "");
        return view;
    }

    private void bottomHorRecyclerRetrofitInit() {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/_search?pretty=true&sort=createdAt:desc&size=100&q=contentType:"+"\""+contentType+"\"").create(ApiInterface.class);
        Call<NewsData> call = apiInterface.getNewsData("http://apiservice-ec.flikster.com/contents/_search?pretty=true&sort=createdAt:desc&size=100&q=contentType:"+"\""+contentType+"\"");
        call.enqueue(new Callback<NewsData>() {
            @Override
            public void onResponse(Call<NewsData> call, Response<NewsData> response) {
                outerHits = response.body().getHits();
                Count = outerHits.getTotal();
                newsBottomHorRecyclerAdapter = new NewsBottomHorRecyclerAdapter(getActivity(),outerHits,Count,title,bannerImg);
                fragment_common_recyclerview_with_tv_recycler.setAdapter(newsBottomHorRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<NewsData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void headerTitlesChange() {
        toolbar_frag_title.setText(contentType);
        fragment_common_recyclerview_with_tv_title.setText("Recommended "+contentType);
    }

    private void initializeViews() {
        toolbar_frag_title = (TextView) view.findViewById(R.id.toolbar_frag_title);
        newsimg = (ImageView) view.findViewById(R.id.newsimg);
        titlehedertxt = (TextView) view.findViewById(R.id.titlehedertxt);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_description = (TextView) view.findViewById(R.id.tv_description);
        profile_image = (RoundedImageView) view.findViewById(R.id.profile_image);
        tv_tag_desc = (TextView) view.findViewById(R.id.tv_tag_desc);
        tv_tag_name = (TextView) view.findViewById(R.id.tv_tag_name);
        fragment_common_recyclerview_with_tv_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
        newsimg.setVisibility(View.VISIBLE);
        tv_name.setVisibility(View.GONE);
    }


    private void initializeRest() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
        toolbar_back_navigation_btn.setOnClickListener(this);

        if (!type.isEmpty()){
            tv_tag_desc.setText(type  + "");
        }
        if (!title.isEmpty()){
            tv_tag_name.setText(title+ "");
        }


        if (!profilePic.isEmpty()){
            Glide.with(getContext()).load(profilePic).asBitmap().into(profile_image);
        }
        if (bannerImg!=null && !bannerImg.isEmpty()){
            Glide.with(getContext()).load(bannerImg).asBitmap().into(newsimg);
        }


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_back_navigation_btn) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    public void updateImage(String profilePic, String title, String type, String bannerImg, String headertitle, String description,String contentType) {

        this.profilePic = profilePic;
        this.title = title;
        this.type = type;
        this.bannerImg = bannerImg;
        this.headertitle = headertitle;
        this.description = description;
        this.contentType=contentType;
    }
}
