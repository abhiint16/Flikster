package com.flikster.HomeActivity.CommonFragments.NewsFragment;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragment;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.FeedFragment.FeedRecyclerAdapter;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.R;
import com.flikster.Util.Common;
import com.flikster.Util.RoundedImageView;
import com.flikster.Util.SharedPrefsUtil;
import com.rohitarya.glide.facedetection.transformation.core.GlideFaceDetector;

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
    String profilePic, title, type, bannerImg, headertitle, description, contentType, userId, entityId,cardId,slug;
    RoundedImageView profile_image;
    ApiInterface apiInterface;
    FeedInnerData outerHits;
    int Count;
    NewsRecommendedClick newsRecommendedClick;
    ImageButton card_footer_share, ib_like, ib_bookmark;
    Button followbtn;
    ImageButton card_comment_text_send_btn;
    EditText card_comment_text_edittxt;
    TextView card_comment_text_see_more_comments;
    LinearLayout header_linear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_onclick, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        headerTitlesChange();
        initializeRest();
        bottomHorRecyclerRetrofitInit();
        if (!headertitle.isEmpty()) {
            titlehedertxt.setText(Html.fromHtml(headertitle) + "");
        }
        if (!description.isEmpty()) {
            tv_description.setText(Html.fromHtml(description) + "");
        }
        if (description.trim().length() == 0)
            tv_description.setVisibility(View.GONE);
        return view;
    }

    private void bottomHorRecyclerRetrofitInit() {
        GlideFaceDetector.initialize(getActivity());
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/_search/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getNewsData("http://apiservice-ec.flikster.com/contents/_search?pretty=true&sort=createdAt:desc&size=10&from=0&q=contentType:" + "\"" + contentType + "\""
                +"%20AND%20industry:\""+ SharedPrefsUtil.getStringPreference(getContext(), "INDUSTRY_TYPE")+"\"");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                outerHits = response.body().getHits();
                Count = outerHits.getTotal();
                newsBottomHorRecyclerAdapter = new NewsBottomHorRecyclerAdapter(getActivity(), outerHits, Count, title, bannerImg, newsRecommendedClick,contentType,userId,cardId,slug);
                fragment_common_recyclerview_with_tv_recycler.setAdapter(newsBottomHorRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void headerTitlesChange() {
        toolbar_frag_title.setText(contentType);
        fragment_common_recyclerview_with_tv_title.setText("Recommended " + contentType);
    }

    private void initializeViews() {
        toolbar_frag_title = (TextView) view.findViewById(R.id.toolbar_frag_title);
        newsimg = (ImageView) view.findViewById(R.id.newsimg);
        titlehedertxt = (TextView) view.findViewById(R.id.titlehedertxt);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_description = (TextView) view.findViewById(R.id.tv_description);
        tv_description.setMaxLines(20);
        profile_image = (RoundedImageView) view.findViewById(R.id.profile_image);
        tv_tag_desc = (TextView) view.findViewById(R.id.tv_tag_desc);
        tv_tag_name = (TextView) view.findViewById(R.id.tv_tag_name);
        fragment_common_recyclerview_with_tv_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
        newsimg.setVisibility(View.VISIBLE);
        tv_name.setVisibility(View.GONE);
        header_linear=(LinearLayout)view.findViewById(R.id.header_linear);
        followbtn = (Button) view.findViewById(R.id.followbtn);
        card_footer_share = (ImageButton) view.findViewById(R.id.card_footer_share);
        ib_like = (ImageButton) view.findViewById(R.id.ib_like);
        ib_bookmark = (ImageButton) view.findViewById(R.id.ib_bookmark);

        card_comment_text_send_btn = (ImageButton) view.findViewById(R.id.card_comment_text_send_btn);
        card_comment_text_send_btn.setOnClickListener(this);
        card_comment_text_edittxt = (EditText) view.findViewById(R.id.card_comment_text_edittxt);
        card_comment_text_see_more_comments = (TextView) view.findViewById(R.id.card_comment_text_see_more_comments);
        card_comment_text_see_more_comments.setOnClickListener(this);

        new PostRetrofit().checkForFollow("follow", userId, entityId, followbtn, getContext());
        new PostRetrofit().checkForLike("like", userId, entityId, ib_like, getContext());
        new PostRetrofit().checkForBookmark("bookmark", userId, entityId, ib_bookmark, getContext());

        followbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.followOrUnFollow(getContext(), followbtn, userId, entityId);
            }
        });
        card_footer_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.shareClick(profilePic, getContext());
            }
        });

        ib_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.likeAndUnLikeEvent(getContext(), ib_like, userId, entityId);
            }
        });
        ib_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.bookmarkAndUnBookmarkeEvent(getContext(), ib_bookmark, userId, entityId);
            }
        });
        card_comment_text_see_more_comments.setVisibility(View.GONE);
        card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                        userId,
                        entityId,
                        card_comment_text_edittxt.getText().toString(),
                        card_comment_text_edittxt, getContext());
            }
        });
    }


    private void initializeRest() {
        header_linear.setOnClickListener(this);
        profile_image.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
        toolbar_back_navigation_btn.setOnClickListener(this);

        if (!type.isEmpty()) {
            tv_tag_desc.setText(type + "");
        }
        if (!title.isEmpty()) {
            tv_tag_name.setText(title + "");
        }


        if (!profilePic.isEmpty()) {
            Glide.with(getContext()).load(profilePic).asBitmap().into(profile_image);
        }
        if (bannerImg != null && !bannerImg.isEmpty()) {
            Glide.with(getContext()).load(bannerImg)
            .thumbnail(Glide.with(getActivity()).load(R.drawable.loading_gif3))
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            newsimg.setLayoutParams(params);
                            newsimg.setScaleType(ImageView.ScaleType.FIT_XY);
                            return false;
                        }
                    }).into(newsimg);
        }


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_back_navigation_btn) {
            newsRecommendedClick.voidMethod(new FeedFragment());
            /*getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .addToBackStack("")
                    .commit();*/
        }else if (view.getId()==R.id.header_linear||view.getId()==R.id.profile_image)
        {
            if ("movie".equals(type)) {
                newsRecommendedClick.test(slug,
                        new MovieFragment(), 1, userId, entityId);
            } else if ("celeb".equals(type)) {
                newsRecommendedClick.test(slug,
                        new CelebrityFragment(), 2, userId, entityId);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        GlideFaceDetector.releaseDetector();
    }

    public void updateImage(String profilePic, String title, String type,
                            String bannerImg, String headertitle,
                            String description, String contentType,
                            String userId, String entityId,String cardId,String slug
    ) {

        this.profilePic = profilePic;
        this.title = title;
        this.type = type;
        this.slug=slug;
        this.bannerImg = bannerImg;
        this.headertitle = headertitle;
        this.description = description;
        this.contentType = contentType;
        this.userId = userId;
        this.entityId = entityId;
        this.cardId=cardId;
    }

    public interface NewsRecommendedClick {
        void newsRecommendedClickMethod(String profilePic, String title, String type,
                                        String bannerImg, String headertitle, String description,
                                        Fragment fragment, String contentType,String userId,String entityId,String cardId,String slug);
        void test(String name, Fragment fragment, int getClass, String userId, String entityId);
        void voidMethod(Fragment fragment);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsRecommendedClick = (NewsRecommendedClick) activity;
    }

}

