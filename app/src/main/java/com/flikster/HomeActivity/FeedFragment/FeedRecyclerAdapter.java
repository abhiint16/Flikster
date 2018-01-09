package com.flikster.HomeActivity.FeedFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.FullScreenYoutubeView.FullScreenYoutubeView;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionDetailFragment;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryCardClick;
import com.flikster.HomeActivity.CommonFragments.VideoFragment.VideoGalleryFragment;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.Util.Common;
import com.flikster.Util.GlobalData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragment;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.HomeActivity.ProfileCollectionRecyclerItemAdapter;
import com.flikster.R;
import com.flikster.HomeActivity.StealStyleViewHolder;
import com.flikster.Util.SharedPrefsUtil;
import com.flikster.VideoFullScreenActivity.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 04-10-2017.
 */

public class FeedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    GlobalData globalData = new GlobalData();
    Context context;
    FragmentManager fragmentManager;
    FeedInnerData outerHits;
    Integer Count;
    FeedFragment.Testing testing;
    JukeBoxRecyclerViewHolder jukeBoxRecyclerViewHolder;
    List<String> audio = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    String userId = "null";
    String userName = "null";
    ApiInterface apiInterface;
    int listSize;


    public FeedRecyclerAdapter(Context context, FragmentManager fragmentManager, FeedInnerData outerHits, Integer Count, FeedFragment.Testing testing) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.outerHits = outerHits;
        this.Count = Count;
        this.testing = testing;
        audio.add("http://content.flikster.com/audio/legendd1.mp3");
        listSize = outerHits.getHits().size();
        setHasStableIds(true);
    }

    public void updateDataPagination(List<FeedInnerData.FeedInnerMoreData> a)
    {
        this.outerHits.getHits().addAll(a);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_critic_review, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
            //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_steal_style_carousel, parent, false);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_quote, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1, parent, false);
            return new ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video, parent, false);
            return new ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1, parent, false);
            return new ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1, parent, false);
            return new ViewHolder6(view);
        } else if (viewType == 10) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1, parent, false);
            return new ViewHolder7(view);
        } else if (viewType == 11) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary2, parent, false);
            return new ViewHolder11(view);
        } else if (viewType == 12) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary3_1, parent, false);
            return new ViewHolder12(view);
        } else if (viewType == 13) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary4_1, parent, false);
            return new ViewHolder13(view);
        } else if (viewType == 14) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary_4plus, parent, false);
            return new ViewHolder14(view);
        } else if (viewType == 8) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_audio_jukebox, parent, false);
            return new ViewHolder8(view);
        } else if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testingnull, parent, false);
            return new ViewHolder9(view);
        } else if (viewType == 100) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testingnocard, parent, false);
            return new ViewHolder9(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        holder.itemView.setTag(outerHits.getClass().);

        if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null && !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
            userId = SharedPrefsUtil.getStringPreference(context, "USER_ID");
            Log.e("LoginUserId", userId);
        }
        if (SharedPrefsUtil.getStringPreference(context, "USER_NAME") != null && SharedPrefsUtil.getStringPreference(context, "USER_NAME") != null) {
            userName = SharedPrefsUtil.getStringPreference(context, "USER_NAME");
            Log.e("LoginUserName", userName);
        }

        if (holder.getItemViewType() == 1) {
            new PostRetrofit().checkForLike("like", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder1) holder).ib_like, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder1) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder1) holder).followbtn, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder1) holder).ib_bookmark, context);
            ((ViewHolder1) holder).card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic()).into(((ViewHolder1) holder).card_critic_review_main_image);
            if (outerHits.getHits().get(position).get_source().getMovie() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder1) holder).profile_image));
                ((ViewHolder1) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                ((ViewHolder1) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
                ((ViewHolder1) holder).card_critic_review_moviename.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder1) holder).profile_image));
                ((ViewHolder1) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder1) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            ((ViewHolder1) holder).card_movie_review_bottom_header_criticrating.setText(outerHits.getHits().get(position).get_source().getRating());
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder1) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder1) holder).tv_description.setText(Html.fromHtml(Common.formatString(outerHits.getHits().get(position).get_source().getText())));
            ((ViewHolder1) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
        } else if (holder.getItemViewType() == 2) {
            new PostRetrofit().checkForLike("like", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder2) holder).ib_like, context);
            ((ViewHolder2) holder).card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder2) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder2) holder).followbtn, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder2) holder).ib_bookmark, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder2) holder).profile_image));
                ((ViewHolder2) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                ((ViewHolder2) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder2) holder).profile_image));
                ((ViewHolder2) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder2) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder2) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder2) holder).tv_description.setText(Html.fromHtml(outerHits.getHits().get(position).get_source().getText()));
            ((ViewHolder2) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
        } else if (holder.getItemViewType() == 3) {
            new PostRetrofit().checkForLike("like", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder3) holder).ib_like, context);
            ((ViewHolder3) holder).card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder3) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder3) holder).followbtn, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder3) holder).ib_bookmark, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder3) holder).profile_image));
                ((ViewHolder3) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                Log.e("profilename", outerHits.getHits().get(position).get_source().getMovie().get(0).getName() + "");
                ((ViewHolder3) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder3) holder).profile_image));
                ((ViewHolder3) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder3) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder3) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder3) holder).tv_description.setText(Html.fromHtml(Common.formatString
                        (outerHits.getHits().get(position).get_source().getText())));
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic())
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder3) holder).card_gallary1_img1);
            ((ViewHolder3) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
            ((ViewHolder3) holder).card_gallary1_img1.forceLayout();
        } else if (holder.getItemViewType() == 4) {
            new PostRetrofit().checkForLike("like", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder4) holder).ib_like, context);
            ((ViewHolder4) holder).card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder4) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder4) holder).followbtn, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder4) holder).ib_bookmark, context);
            Log.e("videosonglink", "videosonglink " + outerHits.getHits().get(position).get_source().getMedia().getVideo());
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder4) holder).profile_image));
                ((ViewHolder4) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                ((ViewHolder4) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder4) holder).profile_image));
                ((ViewHolder4) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder4) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder4) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder4) holder).tv_description.setText(Html.fromHtml(outerHits.getHits().get(position).get_source().getText()));
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic())
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder4) holder).news_img);
            ((ViewHolder4) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
            ((ViewHolder4) holder).news_img.forceLayout();
        } else if (holder.getItemViewType() == 5) {
            new PostRetrofit().checkForLike("like", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder5) holder).ib_like, context);
            ((ViewHolder5) holder).card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder5) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder5) holder).followbtn, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder5) holder).ib_bookmark, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder5) holder).profile_image));
                ((ViewHolder5) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                ((ViewHolder5) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder5) holder).profile_image));
                ((ViewHolder5) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder5) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder5) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder5) holder).tv_description.setText(Html.fromHtml(outerHits.getHits().get(position).get_source().getText()));
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic())
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder5) holder).card_gallary1_img1);
            ((ViewHolder5) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
            ((ViewHolder5) holder).card_gallary1_img1.forceLayout();
        } else if (holder.getItemViewType() == 6) {
            new PostRetrofit().checkForLike("like", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder6) holder).ib_like, context);
            ((ViewHolder6) holder).card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder6) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder6) holder).followbtn, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder6) holder).ib_bookmark, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null) {
                try {
                    Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder6) holder).profile_image));
                    ((ViewHolder6) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                    ((ViewHolder6) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
                }catch (Exception e){
                }

            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder6) holder).profile_image));
                ((ViewHolder6) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder6) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder6) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder6) holder).tv_description.setText(Html.fromHtml(outerHits.getHits().get(position).get_source().getText()));
            //LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Glide.with(context)
                    .load(outerHits.getHits().get(position).get_source().getProfilePic())
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder6) holder).card_gallary1_img1);
            //((ViewHolder6) holder).card_gallary1_img1.setLayoutParams(params);
            ((ViewHolder6) holder).card_gallary1_img1.forceLayout();
            ((ViewHolder6) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
        } else if (holder.getItemViewType() == 10) {
            new PostRetrofit().checkForLike("like", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder7) holder).ib_like, context);
            ((ViewHolder7) holder).card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder7) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder7) holder).followbtn, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder7) holder).ib_bookmark, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder7) holder).profile_image));
                ((ViewHolder7) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                ((ViewHolder7) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder7) holder).profile_image));
                ((ViewHolder7) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder7) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder7) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder7) holder).tv_description.setText(Html.fromHtml(outerHits.getHits().get(position).get_source().getText()));
            if (outerHits.getHits().get(position).get_source().getMedia().getGallery() != null && outerHits.getHits().get(position).get_source().getMedia().getGallery().size() != 0)
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(0))
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder7) holder).card_gallary1_img1);
            ((ViewHolder7) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
        } else if (holder.getItemViewType() == 8) {
            new PostRetrofit().checkForLike("like", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder8) holder).ib_like, context);
            ((ViewHolder8) holder).card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder8) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder8) holder).followbtn, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder8) holder).ib_bookmark, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder8) holder).profile_image));
                ((ViewHolder8) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                ((ViewHolder8) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder8) holder).profile_image));
                ((ViewHolder8) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder8) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic()).into(((ViewHolder8) holder).card_audio_jukebox_imageview);
            jukeBoxRecyclerViewHolder = new JukeBoxRecyclerViewHolder(context, audio);
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            ((ViewHolder8) holder).fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
            ((ViewHolder8) holder).fragment_common_recyclerview_recycler.setAdapter(jukeBoxRecyclerViewHolder);
            ((ViewHolder8) holder).card_footer_container.setVisibility(View.GONE);
        } else if (holder.getItemViewType() == 11) {
            new PostRetrofit().checkForLike("like", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder11) holder).ib_like, context);
            ((ViewHolder11) holder).card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder11) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder11) holder).followbtn, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder11) holder).ib_bookmark, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder11) holder).profile_image));
                ((ViewHolder11) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                ((ViewHolder11) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder11) holder).profile_image));
                ((ViewHolder11) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder11) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder11) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder11) holder).tv_description.setText(Html.fromHtml(outerHits.getHits().get(position).get_source().getText()));
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(0))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder11) holder).card_gallary2_img1);
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(1))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder11) holder).card_gallary2_img2);
            ((ViewHolder11) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
        } else if (holder.getItemViewType() == 12) {
            new PostRetrofit().checkForLike("like", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder12) holder).ib_like, context);
            ((ViewHolder12) holder).card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder12) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder12) holder).followbtn, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder12) holder).ib_bookmark, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder12) holder).profile_image));
                ((ViewHolder12) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                ((ViewHolder12) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder12) holder).profile_image));
                ((ViewHolder12) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder12) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder12) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder12) holder).tv_description.setText(Html.fromHtml(outerHits.getHits().get(position).get_source().getText()));
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(0))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder12) holder).card_gallary3_img1);
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(1))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder12) holder).card_gallary3_img2);
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(2))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder12) holder).card_gallary3_img3);
            ((ViewHolder12) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
        } else if (holder.getItemViewType() == 13) {
            new PostRetrofit().checkForLike("like", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder13) holder).ib_like, context);
            ((ViewHolder13) holder).card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder13) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder13) holder).followbtn, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder13) holder).ib_bookmark, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder13) holder).profile_image));
                ((ViewHolder13) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                ((ViewHolder13) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder13) holder).profile_image));
                ((ViewHolder13) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder13) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder13) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder13) holder).tv_description.setText(Html.fromHtml(outerHits.getHits().get(position).get_source().getText()));
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(0))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder13) holder).card_gallary4_img1);
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(1))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder13) holder).card_gallary4_img2);
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(2))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder13) holder).card_gallary4_img3);
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(3))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder13) holder).card_gallary4_img4);
            ((ViewHolder13) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
        } else if (holder.getItemViewType() == 14) {
            new PostRetrofit().checkForLike("like", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder14) holder).ib_like, context);
            ((ViewHolder14) holder).card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder14) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", userId, outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder14) holder).followbtn, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, outerHits.getHits().get(position).get_source().getId(), ((ViewHolder14) holder).ib_bookmark, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder14) holder).profile_image));
                ((ViewHolder14) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                ((ViewHolder14) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder14) holder).profile_image));
                ((ViewHolder14) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder14) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder14) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder14) holder).tv_description.setText(Html.fromHtml(outerHits.getHits().get(position).get_source().getText()));
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(0))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder14) holder).card_gallary5_img1);
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(1))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder14) holder).card_gallary5_img2);
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(2))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder14) holder).card_gallary5_img3);
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(3))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder14) holder).card_gallary5_img4);
            ((ViewHolder14) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
            ((ViewHolder14) holder).card_gallary5_text.setText("+" + ((outerHits.getHits().get(position).get_source().getMedia().getGallery().size()) - 4));
        }
    }

    @Override
    public int getItemCount() {
        Log.e("size", "size" + outerHits.getHits().size());
        return outerHits.getHits().size();
    }


    @Override
    public int getItemViewType(int position) {
        if (outerHits.getHits().get(position).get_source().getContentType() != null) {
            switch (outerHits.getHits().get(position).get_source().getContentType().toLowerCase()) {
                case "comedy-clip":
                    return 4;
                case "quote":
                    return 2;
                case "news":
                    return 3;
                case "video-song":
                    return 4;
                case "first-look":
                    return 5;
                case "poster":
                    return 6;
                case "write-up":
                    return 6;
                case "gallery": {
                    if (outerHits.getHits().get(position).get_source().getMedia().getGallery() != null
                            && outerHits.getHits().get(position).get_source().getMedia().getGallery().size() == 1)
                        return 10;
                    else if (outerHits.getHits().get(position).get_source().getMedia().getGallery() != null
                            && outerHits.getHits().get(position).get_source().getMedia().getGallery().size() == 2)
                        return 11;
                    else if (outerHits.getHits().get(position).get_source().getMedia().getGallery() != null
                            && outerHits.getHits().get(position).get_source().getMedia().getGallery().size() == 3)
                        return 12;
                    else if (outerHits.getHits().get(position).get_source().getMedia().getGallery() != null
                            && outerHits.getHits().get(position).get_source().getMedia().getGallery().size() == 4)
                        return 13;
                    else if (outerHits.getHits().get(position).get_source().getMedia().getGallery() != null
                            && outerHits.getHits().get(position).get_source().getMedia().getGallery().size() > 4)
                        return 14;
                }
                case "movie-making":
                    return 4;
                case "audio-song":
                    return 8;
                case "dialouge":
                    return 8;
                case "critic-review":
                    return 1;
                case "social-buzz":
                    return 3;
                case "interview":
                    return 4;
                case "trailer":
                    return 4;
                case "teasers-promos":
                    return 4;
                case "tweet":
                    return 3;
            }
            return 100;
        }
        return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_critic_review_main_image, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_critic_review_moviename,
                card_movie_review_bottom_header_criticrating, card_celebrity_feed_gallery1_title,
                card_comment_text_see_more_comments;
        ImageButton ib_like, ib_bookmark, card_comment_text_send_btn, card_footer_share;
        EditText card_comment_text_edittxt;
        LinearLayout header_linear, card_description_linear;
        Button followbtn;
        boolean actionLike = true;
        boolean actionFollow = true;
        boolean actionbookmark = true;

        public ViewHolder1(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_critic_review_main_image = (ImageView) itemView.findViewById(R.id.card_critic_review_main_image);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_critic_review_moviename = (TextView) itemView.findViewById(R.id.card_critic_review_moviename);
            card_movie_review_bottom_header_criticrating = (TextView) itemView.findViewById(R.id.card_movie_review_bottom_header_criticrating);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(this);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeAndUnLikeEvent(ib_like, getAdapterPosition(), actionLike);
                }
            });
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (actionFollow) {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = false;
                    } else {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = true;
                    }

                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkAndUnBookmarkeEvent(ib_bookmark, getAdapterPosition(), actionbookmark);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if ((view.getId() == R.id.profile_image) || (view.getId() == R.id.header_linear)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(), new MovieFragment(), 1, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), 2, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                    Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostRetrofit().postRetrofitCommentMethod(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),
                        card_comment_text_edittxt.getText().toString(), card_comment_text_edittxt, context);
            } else if (view.getId() == R.id.card_comment_text_see_more_comments) {
                testing.seeMoreComments(userName, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (view.getId() == R.id.card_footer_share) {
                shareClick(outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic()
                        + "https://play.google.com/store/apps/details?id=com.flikster&hl=en" + "\n\n\n"
                        + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
            }
        }
    }


    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_quote_tv, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        LinearLayout card_description_linear, header_linear;
        Button followbtn;
        ImageButton ib_like, ib_bookmark, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        ImageButton card_footer_share;
        boolean actionLike = true;
        boolean actionFollow = true;
        boolean actionbookmark = true;

        public ViewHolder2(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_quote_tv = (TextView) itemView.findViewById(R.id.card_quote_tv);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(this);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeAndUnLikeEvent(ib_like, getAdapterPosition(), actionLike);
                }
            });
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionFollow) {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = false;
                    } else {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = true;
                    }
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkAndUnBookmarkeEvent(ib_bookmark, getAdapterPosition(), actionbookmark);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.header_linear) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(),
                            new MovieFragment(), 1, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(),
                            new CelebrityFragment(), 2,
                            userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                    Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostRetrofit().postRetrofitCommentMethod(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),
                        card_comment_text_edittxt.getText().toString(), card_comment_text_edittxt, context);
            } else if (view.getId() == R.id.card_comment_text_see_more_comments) {
                testing.seeMoreComments(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (view.getId() == R.id.card_footer_share) {
                shareClick(outerHits.getHits().get(getAdapterPosition()).get_source()
                        .getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
            }
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        ImageButton ib_like, ib_bookmark;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton video_btn, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        private Button followbtn;
        ImageButton card_footer_share;
        LinearLayout header_linear;
        LinearLayout card_description_linear;

        boolean actionLike = true;
        boolean actionFollow = true;
        boolean actionbookmark = true;

        public ViewHolder3(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(this);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeAndUnLikeEvent(ib_like, getAdapterPosition(), actionLike);
                }
            });
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionFollow) {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = false;
                    } else {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = true;
                    }
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkAndUnBookmarkeEvent(ib_bookmark, getAdapterPosition(), actionbookmark);
                }
            });


            card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.video_btn) {
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                context.startActivity(intent);
            } else if ((view.getId() == R.id.profile_image) || (view.getId() == R.id.header_linear)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(),
                            new MovieFragment(), 1, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), 2, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_description_linear) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new NewsOnClickFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId()
                    );
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new NewsOnClickFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId()
                    );
                } else {
                    testing.newsCardOnClick("",
                            "",
                            "",
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new NewsOnClickFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                    Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostRetrofit().postRetrofitCommentMethod(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(), card_comment_text_edittxt.getText().toString(), card_comment_text_edittxt, context);
            } else if (view.getId() == R.id.card_comment_text_see_more_comments) {
                testing.seeMoreComments(userName, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (view.getId() == R.id.card_footer_share) {
                shareClick(outerHits.getHits().get(getAdapterPosition()).get_source()
                        .getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
            }
        }

    }


    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView news_img, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton ib_like, ib_bookmark, card_comment_text_send_btn, video_btn;
        EditText card_comment_text_edittxt;
        Button followbtn;
        LinearLayout header_linear, card_description_linear;
        boolean actionLike = true;
        ImageButton card_footer_share;
        boolean actionFollow = true;
        boolean actionbookmark = true;

        public ViewHolder4(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            news_img = (ImageView) itemView.findViewById(R.id.news_img);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            video_btn = (ImageButton) itemView.findViewById(R.id.video_btn);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(this);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            video_btn.setOnClickListener(this);
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeAndUnLikeEvent(ib_like, getAdapterPosition(), actionLike);
                }
            });
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionFollow) {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = false;
                    } else {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = true;
                    }
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkAndUnBookmarkeEvent(ib_bookmark, getAdapterPosition(), actionbookmark);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_gallary4_img_container) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new GalleryCardClick())
                        .addToBackStack("")
                        .commit();
            } else if (view.getId() == R.id.video_btn) {
                Intent intent = new Intent(context, FullScreenYoutubeView.class);
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo() != null &&
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().size() != 0) {
                    intent.putExtra("video_link",
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().get(0));
                } else {
                    intent.putExtra("video_link", " ");
                }
                context.startActivity(intent);

            } else if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(),
                            new MovieFragment(), 1, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new CelebrityFragment(), 2, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_description_linear) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.videoCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().get(0),
                            new VideoGalleryFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId()

                    );
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.videoCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().get(0),
                            new VideoGalleryFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId()
                    );
                } else {
                    testing.videoCardOnClick("",
                            "",
                            "",
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().get(0),
                            new VideoGalleryFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                    Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostRetrofit().postRetrofitCommentMethod(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),
                        card_comment_text_edittxt.getText().toString(),
                        card_comment_text_edittxt, context);
            } else if (view.getId() == R.id.card_comment_text_see_more_comments) {
                testing.seeMoreComments(userName,
                        userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (view.getId() == R.id.card_footer_share) {
                shareClick(outerHits.getHits().get(getAdapterPosition()).get_source()
                        .getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
            }

        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton ib_like, ib_bookmark;
        Button followbtn;
        ImageButton video_btn, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        LinearLayout header_linear;
        LinearLayout card_description_linear;
        ImageButton card_footer_share;
        boolean actionLike = true;
        boolean actionFollow = true;
        boolean actionbookmark = true;


        public ViewHolder5(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(this);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeAndUnLikeEvent(ib_like, getAdapterPosition(), actionLike);
                }
            });
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionFollow) {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = false;
                    } else {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = true;
                    }
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkAndUnBookmarkeEvent(ib_bookmark, getAdapterPosition(), actionbookmark);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(),
                            new MovieFragment(), 1,
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), 2,
                            userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_description_linear) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            " ",
                            new NewsOnClickFragment(), outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId()
                    );
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            " ", new NewsOnClickFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId()
                    );
                } else {
                    testing.newsCardOnClick("",
                            "",
                            "",
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            " ", new NewsOnClickFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                    Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostRetrofit().postRetrofitCommentMethod(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(), card_comment_text_edittxt.getText().toString(), card_comment_text_edittxt, context);
            } else if (view.getId() == R.id.card_comment_text_see_more_comments) {
                testing.seeMoreComments(userName, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (view.getId() == R.id.card_footer_share) {
                shareClick(outerHits.getHits().get(getAdapterPosition()).get_source()
                        .getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
            }
        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton ib_like, ib_bookmark;
        ImageButton video_btn, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        Button followbtn;
        ImageButton card_footer_share;
        LinearLayout header_linear;
        LinearLayout card_description_linear;
        boolean actionLike = true;
        boolean actionFollow = true;
        boolean actionbookmark = true;

        public ViewHolder6(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            //video_btn = (ImageButton) itemView.findViewById(R.id.video_btn);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(this);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            //video_btn.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeAndUnLikeEvent(ib_like, getAdapterPosition(), actionLike);
                }
            });
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionFollow) {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = false;
                    } else {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = true;
                    }
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkAndUnBookmarkeEvent(ib_bookmark, getAdapterPosition(), actionbookmark);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), 2, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(), new MovieFragment(), 1,
                            userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_description_linear) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            " ", new NewsOnClickFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId()
                    );
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            " ", new NewsOnClickFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId()
                    );
                } else {
                    testing.newsCardOnClick("",
                            "",
                            "",
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            " ", new NewsOnClickFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                            userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                    Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostRetrofit().postRetrofitCommentMethod(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(), card_comment_text_edittxt.getText().toString(), card_comment_text_edittxt, context);
            } else if (view.getId() == R.id.card_comment_text_see_more_comments) {
                testing.seeMoreComments(userName, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (view.getId() == R.id.card_footer_share) {
                shareClick(outerHits.getHits().get(getAdapterPosition()).get_source()
                        .getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
            }
        }
    }


    public class ViewHolder7 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton video_btn;
        ImageButton ib_like, ib_bookmark, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        Button followbtn;
        LinearLayout header_linear;
        LinearLayout card_description_linear;
        ImageButton card_footer_share;
        boolean actionLike = true;
        boolean actionFollow = true;
        boolean actionbookmark = true;


        public ViewHolder7(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(this);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
            card_gallary1_img1.setOnClickListener(this);
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeAndUnLikeEvent(ib_like, getAdapterPosition(), actionLike);
                }
            });
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionFollow) {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = false;
                    } else {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = true;
                    }
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkAndUnBookmarkeEvent(ib_bookmark, getAdapterPosition(), actionbookmark);
                }
            });
        }

        @Override
        public void onClick(View view) {
            globalData.a = 1;
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(),
                            new MovieFragment(), 1, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new CelebrityFragment(), 2,
                            userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_description_linear) {
//                fragmentManager.beginTransaction()
//                        .replace(R.id.main_container, new AuctionDetailFragment())
//                        .addToBackStack("")
//                        .commit();
            } else if (view.getId() == R.id.card_gallary1_img1) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            "",
                            "", "", outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());

                }
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                    Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostRetrofit().postRetrofitCommentMethod(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),
                        card_comment_text_edittxt.getText().toString(),
                        card_comment_text_edittxt, context);
            } else if (view.getId() == R.id.card_comment_text_see_more_comments) {
                testing.seeMoreComments(userName,
                        userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (view.getId() == R.id.card_footer_share) {
                shareClick(outerHits.getHits().get(getAdapterPosition()).get_source()
                        .getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
            }
        }
    }

    public class ViewHolder8 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_audio_jukebox_imageview, profile_image;
        TextView tv_tag_name, tv_tag_desc, card_comment_text_see_more_comments, card_celebrity_feed_gallery1_title;
        ImageButton ib_like, ib_bookmark, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        ImageButton video_btn;
        LinearLayout header_linear;
        LinearLayout card_description_linear;
        RecyclerView fragment_common_recyclerview_recycler;
        RelativeLayout card_footer_container;
        ImageButton card_footer_share;
        Button followbtn;
        boolean actionLike = true;
        boolean actionFollow = true;
        boolean actionbookmark = true;

        public ViewHolder8(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_audio_jukebox_imageview = (ImageView) itemView.findViewById(R.id.card_audio_jukebox_imageview);
            fragment_common_recyclerview_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_recycler);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_footer_container = (RelativeLayout) itemView.findViewById(R.id.card_footer_container);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(this);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeAndUnLikeEvent(ib_like, getAdapterPosition(), actionLike);
                }
            });
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionFollow) {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = false;
                    } else {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = true;
                    }
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkAndUnBookmarkeEvent(ib_bookmark, getAdapterPosition(), actionbookmark);
                }
            });
            //card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() == null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(),
                            new MovieFragment(), 1, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() == null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new CelebrityFragment(), 2, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                    Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostRetrofit().postRetrofitCommentMethod(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(), card_comment_text_edittxt.getText().toString(), card_comment_text_edittxt, context);
            } else if (view.getId() == R.id.card_comment_text_see_more_comments) {
                testing.seeMoreComments(userName, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (view.getId() == R.id.card_footer_share) {
                shareClick(outerHits.getHits().get(getAdapterPosition()).get_source()
                        .getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
            }
            /*else if (view.getId() == R.id.card_description_linear) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new AuctionDetailFragment())
                        .addToBackStack("")
                        .commit();
            } else if (view.getId() == R.id.card_gallary1_img1) {
                testing.galleryCardOnClick(items.get(getAdapterPosition()).getMedia().getGallery(),
                        items.get(getAdapterPosition()).getCeleb().get(0).getName(),
                        items.get(getAdapterPosition()).getCeleb().get(0).getProfilePic(), items.get(getAdapterPosition()).getCeleb().get(0).getType(),
                        items.get(getAdapterPosition()).getTitle(), new GalleryCardClick());
            }
*/
        }
    }

    public class ViewHolder9 extends RecyclerView.ViewHolder {

        public ViewHolder9(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder11 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary2_img1, card_gallary2_img2, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton video_btn;
        ImageButton ib_like, ib_bookmark, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        Button followbtn;
        LinearLayout header_linear, card_gallery2_img_container;
        LinearLayout card_description_linear;
        ImageButton card_footer_share;
        boolean actionLike = true;
        boolean actionFollow = true;
        boolean actionbookmark = true;

        public ViewHolder11(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary2_img1 = (ImageView) itemView.findViewById(R.id.card_gallary2_img1);
            card_gallary2_img2 = (ImageView) itemView.findViewById(R.id.card_gallary2_img2);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(this);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_gallery2_img_container = (LinearLayout) itemView.findViewById(R.id.card_gallery2_img_container);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeAndUnLikeEvent(ib_like, getAdapterPosition(), actionLike);
                }
            });
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionFollow) {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = false;
                    } else {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = true;
                    }
                }
            });

            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkAndUnBookmarkeEvent(ib_bookmark, getAdapterPosition(), actionbookmark);
                }
            });

            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
            card_gallery2_img_container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a = 1;
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(),
                            new MovieFragment(), 1, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), 2, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_description_linear) {
//                fragmentManager.beginTransaction()
//                        .replace(R.id.main_container, new AuctionDetailFragment())
//                        .addToBackStack("")
//                        .commit();
            } else if (view.getId() == R.id.card_gallery2_img_container) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            "",
                            "", "", outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());

                }
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                    Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostRetrofit().postRetrofitCommentMethod(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(), card_comment_text_edittxt.getText().toString(), card_comment_text_edittxt, context);
            } else if (view.getId() == R.id.card_comment_text_see_more_comments) {
                testing.seeMoreComments(userName, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (view.getId() == R.id.card_footer_share) {
                shareClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery().get(0) + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
            }
        }
    }


    public class ViewHolder12 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary3_img1, card_gallary3_img2, card_gallary3_img3, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton video_btn;
        ImageButton ib_like, ib_bookmark, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        Button followbtn;
        LinearLayout header_linear, card_gallery3_1_img_container;
        LinearLayout card_description_linear;
        ImageButton card_footer_share;
        boolean actionLike = true;
        boolean actionFollow = true;
        boolean actionbookmark = true;


        public ViewHolder12(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary3_img1 = (ImageView) itemView.findViewById(R.id.card_gallary3_img1);
            card_gallary3_img2 = (ImageView) itemView.findViewById(R.id.card_gallary3_img2);
            card_gallary3_img3 = (ImageView) itemView.findViewById(R.id.card_gallary3_img3);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_gallery3_1_img_container = (LinearLayout) itemView.findViewById(R.id.card_gallery3_1_img_container);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(this);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
            card_gallery3_1_img_container.setOnClickListener(this);
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeAndUnLikeEvent(ib_like, getAdapterPosition(), actionLike);
                }
            });
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionFollow) {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = false;
                    } else {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = true;
                    }
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkAndUnBookmarkeEvent(ib_bookmark, getAdapterPosition(), actionbookmark);
                }
            });
        }

        @Override
        public void onClick(View view) {
            globalData.a = 1;
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(),
                            new MovieFragment(), 1, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new CelebrityFragment(), 2, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_description_linear) {
//                fragmentManager.beginTransaction()
//                        .replace(R.id.main_container, new AuctionDetailFragment())
//                        .addToBackStack("")
//                        .commit();
            } else if (view.getId() == R.id.card_gallery3_1_img_container) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            "",
                            "", "",
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());

                }
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                    Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostRetrofit().postRetrofitCommentMethod(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(), card_comment_text_edittxt.getText().toString(), card_comment_text_edittxt, context);
            } else if (view.getId() == R.id.card_comment_text_see_more_comments) {
                testing.seeMoreComments(userName, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (view.getId() == R.id.card_footer_share) {
                shareClick(
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery().get(0)
                                + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
            }
        }
    }


    public class ViewHolder13 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary4_img1, card_gallary4_img2, card_gallary4_img3, card_gallary4_img4, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton video_btn;
        ImageButton ib_like, ib_bookmark, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        Button followbtn;
        LinearLayout header_linear, card_gallery4_img_container;
        LinearLayout card_description_linear;
        ImageButton card_footer_share;
        boolean actionLike = true;
        boolean actionFollow = true;
        boolean actionbookmark = true;

        public ViewHolder13(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary4_img1 = (ImageView) itemView.findViewById(R.id.card_gallary4_img1);
            card_gallary4_img2 = (ImageView) itemView.findViewById(R.id.card_gallary4_img2);
            card_gallary4_img3 = (ImageView) itemView.findViewById(R.id.card_gallary4_img3);
            card_gallary4_img4 = (ImageView) itemView.findViewById(R.id.card_gallary4_img4);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(this);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_gallery4_img_container = (LinearLayout) itemView.findViewById(R.id.card_gallery4_img_container);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
            card_gallery4_img_container.setOnClickListener(this);
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeAndUnLikeEvent(ib_like, getAdapterPosition(), actionLike);
                }
            });
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionFollow) {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = false;
                    } else {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = true;
                    }
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkAndUnBookmarkeEvent(ib_bookmark, getAdapterPosition(), actionbookmark);
                }
            });
        }

        @Override
        public void onClick(View view) {
            globalData.a = 1;
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(), new MovieFragment(), 1, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new CelebrityFragment(), 2, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_description_linear) {
//                fragmentManager.beginTransaction()
//                        .replace(R.id.main_container, new AuctionDetailFragment())
//                        .addToBackStack("")
//                        .commit();
            } else if (view.getId() == R.id.card_gallery4_img_container) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            "",
                            "", "", outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());

                }
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                    Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostRetrofit().postRetrofitCommentMethod(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(), card_comment_text_edittxt.getText().toString(), card_comment_text_edittxt, context);
            } else if (view.getId() == R.id.card_comment_text_see_more_comments) {
                testing.seeMoreComments(userName, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (view.getId() == R.id.card_footer_share) {
                shareClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery().get(0) + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
            }
        }
    }


    public class ViewHolder14 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary5_img1, card_gallary5_img2, card_gallary5_img3, card_gallary5_img4, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title, card_gallary5_text;
        ImageButton video_btn;
        ImageButton ib_like, ib_bookmark, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        Button followbtn;
        LinearLayout header_linear;
        RelativeLayout card_gallery5_img_container;
        LinearLayout card_description_linear;
        ImageButton card_footer_share;
        boolean actionLike = true;
        boolean actionFollow = true;
        boolean actionbookmark = true;

        public ViewHolder14(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary5_img1 = (ImageView) itemView.findViewById(R.id.card_gallary5_img1);
            card_gallary5_img2 = (ImageView) itemView.findViewById(R.id.card_gallary5_img2);
            card_gallary5_img3 = (ImageView) itemView.findViewById(R.id.card_gallary5_img3);
            card_gallary5_img4 = (ImageView) itemView.findViewById(R.id.card_gallary5_img4);
            card_gallary5_text = (TextView) itemView.findViewById(R.id.card_gallary5_text);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(this);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_gallery5_img_container = (RelativeLayout) itemView.findViewById(R.id.card_gallery5_img_container);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
            card_gallery5_img_container.setOnClickListener(this);
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeAndUnLikeEvent(ib_like, getAdapterPosition(), actionLike);
                }
            });
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionFollow) {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = false;
                    } else {
                        followOrUnFollow(actionFollow, followbtn, getAdapterPosition());
                        actionFollow = true;
                    }
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkAndUnBookmarkeEvent(ib_bookmark, getAdapterPosition(), actionbookmark);
                }
            });
        }

        @Override
        public void onClick(View view) {
            globalData.a = 1;
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(),
                            new MovieFragment(), 1, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), 2, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_description_linear) {
//                fragmentManager.beginTransaction()
//                        .replace(R.id.main_container, new AuctionDetailFragment())
//                        .addToBackStack("")
//                        .commit();
            } else if (view.getId() == R.id.card_gallery5_img_container) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                } else {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            "",
                            "", "", outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            new GalleryCardClick(), userId,
                            outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (view.getId() == R.id.card_comment_text_send_btn) {
                if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
                    Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostRetrofit().postRetrofitCommentMethod(userName, userId,
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(), card_comment_text_edittxt.getText().toString(), card_comment_text_edittxt, context);
            } else if (view.getId() == R.id.card_comment_text_see_more_comments) {
                testing.seeMoreComments(userName, userId, outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            } else if (view.getId() == R.id.card_footer_share) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null &&
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    shareClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic()
                            + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. " +
                            "Stay connected to the world of Illusion.\n");
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    shareClick(outerHits.getHits().get(getAdapterPosition())
                            .get_source().getCeleb().get(0).getProfilePic()
                            + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. " +
                            "Stay connected to the world of Illusion.\n");
                } else {
                    shareClick(outerHits.getHits().get(getAdapterPosition()).get_source()
                            .getProfilePic()
                            + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. " +
                            "Stay connected to the world of Illusion.\n");
                }


            }
        }
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    //Like or UnLike Action
    private void likeAndUnLikeEvent(final ImageButton ib_like, final int getAdapterPositionValue, boolean actionLikee) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ib_like.getDrawable().getConstantState().equals
                (context.getResources().getDrawable(R.drawable.like_pink).getConstantState())) {
            Log.e("LikeEvent", "PINK_COLOR");
            actionLikee = false;
        } else {
            Log.e("LikeEvent", "NORMAL_COLOR");
            actionLikee = true;
        }
        if (actionLikee) {
            ib_like.setImageResource(0);
            ib_like.setImageResource(R.drawable.like_pink);
            new PostRetrofit().postRetrofitMethod("like", userId, outerHits.getHits().get(getAdapterPositionValue).get_source().getId(), ib_like, context);
            actionLikee = false;
            Toast.makeText(context, "You Like " + getAdapterPositionValue, Toast.LENGTH_SHORT).show();
            Log.e("inside like clikc", "news or Movie inside like click");
            Log.e("actionLike", "" + actionLikee);
        } else {
            Toast.makeText(context, "You UnLike " + getAdapterPositionValue, Toast.LENGTH_SHORT).show();
            ib_like.setImageResource(0);
            ib_like.setImageResource(R.drawable.like_icon);
            new PostRetrofit().postRetrofitMethod("like", userId, outerHits.getHits().get(getAdapterPositionValue).get_source().getId(), ib_like, context);
            actionLikee = true;
            Log.e("inside like clikc", "news or Movie inside like click");
            Log.e("actionLike", "" + actionLikee);
        }
    }

    //Follow or UnFollow action
    private void followOrUnFollow(boolean actionFollow, final Button followbtn, int adapterPositionValue) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.e("TextData", followbtn.getText().toString() + "");
        if (followbtn.getText().toString().equals("follow")) {
            if (outerHits.getHits().get(adapterPositionValue).get_source().getMovie() != null && outerHits.getHits().get(adapterPositionValue).get_source().getMovie().size() != 0) {
                followcolorChange(followbtn);
                new PostRetrofit().postRetrofitFollowMethod("follow", userId, outerHits.getHits().get(adapterPositionValue).get_source().getMovie().get(0).getId(), followbtn, context);
            } else if (outerHits.getHits().get(adapterPositionValue).get_source().getCeleb() != null && outerHits.getHits().get(adapterPositionValue).get_source().getCeleb().size() != 0) {
                followcolorChange(followbtn);
                new PostRetrofit().postRetrofitFollowMethod("follow", userId, outerHits.getHits().get(adapterPositionValue).get_source().getCeleb().get(0).getId(), followbtn, context);
            } else {
                Toast.makeText(context, "Movie & Celeb is not there in the JSON File", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "You Unfollowing", Toast.LENGTH_LONG).show();
            if (outerHits.getHits().get(adapterPositionValue).get_source().getMovie() != null && outerHits.getHits().get(adapterPositionValue).get_source().getMovie().size() != 0) {
                unfollowcolorChange(followbtn);
                new PostRetrofit().postRetrofitFollowMethod("follow", userId, outerHits.getHits().get(adapterPositionValue).get_source().getMovie().get(0).getId(), followbtn, context);
            } else if (outerHits.getHits().get(adapterPositionValue).get_source().getCeleb() != null && outerHits.getHits().get(adapterPositionValue).get_source().getCeleb().size() != 0) {
                unfollowcolorChange(followbtn);
                new PostRetrofit().postRetrofitFollowMethod("follow", userId, outerHits.getHits().get(adapterPositionValue).get_source().getCeleb().get(0).getId(), followbtn, context);
            } else {
                Toast.makeText(context, "Movie & Celeb is not there in the JSON File", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void followcolorChange(Button followbtn) {
        followbtn.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        followbtn.setBackground(context.getResources().getDrawable(R.drawable.corner_rounded_pink));
        followbtn.setText("Following");
        followbtn.setTextColor(context.getResources().getColor(R.color.white));
    }

    private void unfollowcolorChange(Button followbtn) {
        followbtn.setBackgroundColor(context.getResources().getColor(R.color.black));
        followbtn.setBackground(context.getResources().getDrawable(R.drawable.corner_rounded));
        followbtn.setText("follow");
        followbtn.setTextColor(context.getResources().getColor(R.color.black));
    }

    //Book mark in Action Event
    private void bookmarkAndUnBookmarkeEvent(final ImageButton bookmark, final int getAdapterPositionValue, boolean bookmarkAction) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bookmark.getDrawable().getConstantState().equals
                (context.getResources().getDrawable(R.drawable.bookmark_yellow).getConstantState())) {
            Log.e("BookmarkEvent", "YELLOW_COLOR");
            bookmarkAction = false;
        } else {
            Log.e("BookmarkEvent", "NORMAL_COLOR");
            bookmarkAction = true;
        }
        if (bookmarkAction) {
            bookmark.setImageResource(0);
            bookmark.setImageResource(R.drawable.bookmark_yellow);
            new PostRetrofit().postRetrofitBookmarkMethod("bookmark", userId, outerHits.getHits().get(getAdapterPositionValue).get_source().getId(), bookmark, context);
            bookmarkAction = false;
            Toast.makeText(context, "You Like " + getAdapterPositionValue, Toast.LENGTH_SHORT).show();
            Log.e("inside like clikc", "news or Movie inside like click");
            Log.e("actionLike", "" + bookmarkAction);
        } else {
            Toast.makeText(context, "You UnLike " + getAdapterPositionValue, Toast.LENGTH_SHORT).show();
            bookmark.setImageResource(0);
            bookmark.setImageResource(R.drawable.bookmark_icon);
            new PostRetrofit().postRetrofitBookmarkMethod("bookmark", userId, outerHits.getHits().get(getAdapterPositionValue).get_source().getId(), bookmark, context);
            bookmarkAction = true;
            Log.e("inside like clikc", "news or Movie inside like click");
            Log.e("actionLike", "" + bookmarkAction);
        }
    }

    public void shareClick(String shareableLink) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Flikster");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareableLink);
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent, "Complete action using ...."));
    }

    private void checkForLoggedIn() {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}
