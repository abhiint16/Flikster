package com.flikster.HomeActivity.FeedFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionDetailFragment;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryCardClick;
import com.flikster.HomeActivity.CommonFragments.VideoFragment.VideoGalleryFragment;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.Util.Common;
import com.flikster.Util.GlobalData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragment;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.HomeActivity.ProfileCollectionRecyclerItemAdapter;
import com.flikster.R;
import com.flikster.HomeActivity.StealStyleViewHolder;
import com.flikster.VideoFullScreenActivity.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.List;

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

    public FeedRecyclerAdapter(Context context, FragmentManager fragmentManager, FeedInnerData outerHits, Integer Count, FeedFragment.Testing testing) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.outerHits = outerHits;
        this.Count = Count;
        this.testing = testing;
        audio.add("http://content.flikster.com/audio/legendd1.mp3");
        setHasStableIds(true);
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
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1, parent, false);
            return new ViewHolder7(view);
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
        if (holder.getItemViewType() == 1) {
            new PostRetrofit().checkForLike("like", "hell", outerHits.getHits().get(position).get_source().getId(), ((ViewHolder1) holder).ib_like, context);
                if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                    new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder1) holder).followbtn, context);
                else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                    new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder1) holder).followbtn, context);
                if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                    new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder1) holder).ib_bookmark, context);
                else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                    new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder1) holder).ib_bookmark, context);
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
            new PostRetrofit().checkForLike("like", "hell", outerHits.getHits().get(position).get_source().getId(), ((ViewHolder2) holder).ib_like, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder2) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder2) holder).followbtn, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder2) holder).ib_bookmark, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder2) holder).ib_bookmark, context);
            /*((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setText("You won't believe these");
            stealStyleLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(stealStyleLayoutManager);
            stealStyleViewHolder = new StealStyleViewHolder(((ViewHolder2) holder).itemView.getContext(), fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(stealStyleViewHolder);*/
            //((ViewHolder2) holder).card_quote_tv.setText(items.get(position).getText());
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
            new PostRetrofit().checkForLike("like", "hell", outerHits.getHits().get(position).get_source().getId(), ((ViewHolder3) holder).ib_like, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder3) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder3) holder).followbtn, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder3) holder).ib_bookmark, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder3) holder).ib_bookmark, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder3) holder).profile_image));
                ((ViewHolder3) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                ((ViewHolder3) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder3) holder).profile_image));
                ((ViewHolder3) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder3) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder3) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder3) holder).tv_description.setText(Html.fromHtml(Common.formatString(outerHits.getHits().get(position).get_source().getText())));
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic()).into(((ViewHolder3) holder).card_gallary1_img1);
            ((ViewHolder3) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
            // Common.makeTextViewResizable(((ViewHolder3) holder).tv_description, 3, "View More", true);

        } else if (holder.getItemViewType() == 4) {
            new PostRetrofit().checkForLike("like", "hell", outerHits.getHits().get(position).get_source().getId(), ((ViewHolder4) holder).ib_like, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder4) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder4) holder).followbtn, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder4) holder).ib_bookmark, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder4) holder).ib_bookmark, context);
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
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic()).into(((ViewHolder4) holder).news_img);
            ((ViewHolder4) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
        } else if (holder.getItemViewType() == 5) {
            new PostRetrofit().checkForLike("like", "hell", outerHits.getHits().get(position).get_source().getId(), ((ViewHolder5) holder).ib_like, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder5) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder5) holder).followbtn, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder5) holder).ib_bookmark, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder5) holder).ib_bookmark, context);
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
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic()).into(((ViewHolder5) holder).card_gallary1_img1);
            ((ViewHolder5) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
        } else if (holder.getItemViewType() == 6) {
            new PostRetrofit().checkForLike("like", "hell", outerHits.getHits().get(position).get_source().getId(), ((ViewHolder6) holder).ib_like, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder6) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder6) holder).followbtn, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder6) holder).ib_bookmark, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder6) holder).ib_bookmark, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder6) holder).profile_image));
                ((ViewHolder6) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getType());
                ((ViewHolder6) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
            } else if (outerHits.getHits().get(position).get_source().getCeleb() != null) {
                Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder6) holder).profile_image));
                ((ViewHolder6) holder).tv_tag_desc.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getType());
                ((ViewHolder6) holder).tv_tag_name.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
            }
            if (outerHits.getHits().get(position).get_source().getText() == null)
                ((ViewHolder6) holder).tv_description.setVisibility(View.GONE);
            else if (outerHits.getHits().get(position).get_source().getText() != null)
                ((ViewHolder6) holder).tv_description.setText(Html.fromHtml(outerHits.getHits().get(position).get_source().getText()));
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic()).into(((ViewHolder6) holder).card_gallary1_img1);
            ((ViewHolder6) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
        } else if (holder.getItemViewType() == 7) {
            new PostRetrofit().checkForLike("like", "hell", outerHits.getHits().get(position).get_source().getId(), ((ViewHolder7) holder).ib_like, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder7) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder7) holder).followbtn, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder7) holder).ib_bookmark, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder7) holder).ib_bookmark, context);
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
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getProfilePic()).into(((ViewHolder7) holder).card_gallary1_img1);
            ((ViewHolder7) holder).tv_name.setText(outerHits.getHits().get(position).get_source().getTitle());
        } else if (holder.getItemViewType() == 8) {
            new PostRetrofit().checkForLike("like", "hell", outerHits.getHits().get(position).get_source().getId(), ((ViewHolder8) holder).ib_like, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder8) holder).followbtn, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForFollow("follow", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder8) holder).followbtn, context);
            if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getMovie().get(0).getId(), ((ViewHolder8) holder).ib_bookmark, context);
            else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0)
                new PostRetrofit().checkForBookmark("bookmark", "hell", outerHits.getHits().get(position).get_source().getCeleb().get(0).getId(), ((ViewHolder8) holder).ib_bookmark, context);
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
                    return 7;
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
                    return 4;
                case "interview":
                    return 4;
                case "trailer":
                    return 4;
                case "teasers-promos":
                    return 4;
            }
            return 100;
        }
        return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_critic_review_main_image, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_critic_review_moviename,
                card_movie_review_bottom_header_criticrating,
                card_comment_text_see_more_comments;
        ImageButton ib_like,ib_bookmark,card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        LinearLayout header_linear, card_description_linear;
        Button followbtn;

        public ViewHolder1(View itemView) {
            super(itemView);

            card_critic_review_main_image = (ImageView) itemView.findViewById(R.id.card_critic_review_main_image);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            followbtn.setOnClickListener(this);
            card_critic_review_moviename = (TextView) itemView.findViewById(R.id.card_critic_review_moviename);
            card_movie_review_bottom_header_criticrating = (TextView) itemView.findViewById(R.id.card_movie_review_bottom_header_criticrating);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark=(ImageButton)itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn=(ImageButton)itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt=(EditText)itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments=(TextView)itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            ib_like.setOnClickListener(this);
            header_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if ((view.getId() == R.id.profile_image) || (view.getId() == R.id.header_linear)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            } else if (view.getId() == R.id.ib_like) {
                Log.e("inside like clikc", "inside like click");
                ib_like.setImageResource(R.drawable.like_pink);
                new PostRetrofit().postRetrofitMethod("like", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getId(), ib_like, context);
            } else if (view.getId() == R.id.followbtn) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), followbtn, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), followbtn, context);
                else
                    Toast.makeText(context,"Movie & Celeb is not there in the JSON File",Toast.LENGTH_LONG).show();
            }
            else if(view.getId()==R.id.card_comment_text_send_btn)
            {
                new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar","abhiint",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),card_comment_text_edittxt.getText().toString(),card_comment_text_edittxt,context);
            }
            else if(view.getId()==R.id.card_comment_text_see_more_comments)
            {
                testing.seeMoreComments(outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            }
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_quote_tv,card_comment_text_see_more_comments;
        LinearLayout card_description_linear, header_linear;
        Button followbtn;
        ImageButton ib_like,ib_bookmark,card_comment_text_send_btn;
        EditText card_comment_text_edittxt;

        public ViewHolder2(View itemView) {
            super(itemView);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_quote_tv = (TextView) itemView.findViewById(R.id.card_quote_tv);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            followbtn.setOnClickListener(this);
            ib_bookmark=(ImageButton)itemView.findViewById(R.id.ib_bookmark);
            ib_bookmark.setOnClickListener(this);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            card_comment_text_send_btn=(ImageButton)itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt=(EditText)itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments=(TextView)itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            ib_like.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.header_linear) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            } else if (view.getId() == R.id.ib_like) {
                Log.e("inside like clikc", "inside like click");
                ib_like.setImageResource(R.drawable.like_pink);
                new PostRetrofit().postRetrofitMethod("like", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getId(), ib_like, context);
            }
            else if (view.getId() == R.id.followbtn) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), followbtn, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), followbtn, context);
                else
                    Toast.makeText(context,"Movie & Celeb is not there in the JSON File",Toast.LENGTH_LONG).show();
            }
            else if (view.getId() == R.id.ib_bookmark) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), ib_bookmark, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), ib_bookmark, context);
            }
            else if(view.getId()==R.id.card_comment_text_send_btn)
            {
                new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar","abhiint",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),card_comment_text_edittxt.getText().toString(),card_comment_text_edittxt,context);
            }
            else if(view.getId()==R.id.card_comment_text_see_more_comments)
            {
                testing.seeMoreComments(outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            }
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        ImageButton ib_like,ib_bookmark;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description,card_comment_text_see_more_comments;
        ImageButton video_btn,card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        Button followbtn;
        LinearLayout header_linear;
        LinearLayout card_description_linear;

        public ViewHolder3(View itemView) {
            super(itemView);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            //video_btn = (ImageButton) itemView.findViewById(R.id.video_btn);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_comment_text_send_btn=(ImageButton)itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt=(EditText)itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments=(TextView)itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            followbtn.setOnClickListener(this);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_like.setOnClickListener(this);
            ib_bookmark=(ImageButton)itemView.findViewById(R.id.ib_bookmark);
            ib_bookmark.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            //video_btn.setOnClickListener(this);

            card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.video_btn) {
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                context.startActivity(intent);
            } else if ((view.getId() == R.id.profile_image) || (view.getId() == R.id.header_linear)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            } else if (view.getId() == R.id.card_description_linear) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new NewsOnClickFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType()
                    );
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new NewsOnClickFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType()
                    );
                } else {
                    testing.newsCardOnClick("",
                            "",
                            "",
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new NewsOnClickFragment(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType());
                }
            } else if (view.getId() == R.id.ib_like) {
                Log.e("inside like clikc", "inside like click");
                ib_like.setImageResource(R.drawable.like_pink);
                new PostRetrofit().postRetrofitMethod("like", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getId(), ib_like, context);
            }
            else if (view.getId() == R.id.followbtn) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), followbtn, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), followbtn, context);
                else
                    Toast.makeText(context,"Movie & Celeb is not there in the JSON File",Toast.LENGTH_LONG).show();
            }
            else if (view.getId() == R.id.ib_bookmark) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), ib_bookmark, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), ib_bookmark, context);
            }
            else if(view.getId()==R.id.card_comment_text_send_btn)
            {
                new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar","abhiint",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),card_comment_text_edittxt.getText().toString(),card_comment_text_edittxt,context);
            }
            else if(view.getId()==R.id.card_comment_text_see_more_comments)
            {
                testing.seeMoreComments(outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            }
        }

    }

    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView news_img, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description,card_comment_text_see_more_comments;
        ImageButton ib_like,ib_bookmark,card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        Button followbtn;
        LinearLayout header_linear, card_description_linear;

        public ViewHolder4(View itemView) {
            super(itemView);
            news_img = (ImageView) itemView.findViewById(R.id.news_img);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            followbtn.setOnClickListener(this);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            card_comment_text_send_btn=(ImageButton)itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt=(EditText)itemView.findViewById(R.id.card_comment_text_edittxt);
            ib_like.setOnClickListener(this);
            ib_bookmark=(ImageButton)itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_see_more_comments=(TextView)itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            ib_bookmark.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            header_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_gallary4_img_container) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new GalleryCardClick())
                        .addToBackStack("")
                        .commit();
            } else if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
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
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType()
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
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType()
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
                            outerHits.getHits().get(getAdapterPosition()).get_source().getContentType());
                }
            } else if (view.getId() == R.id.ib_like) {
                Log.e("inside like clikc", "inside like click");
                ib_like.setImageResource(R.drawable.like_pink);
                new PostRetrofit().postRetrofitMethod("like", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getId(), ib_like, context);
            }
            else if (view.getId() == R.id.followbtn) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), followbtn, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), followbtn, context);
                else
                    Toast.makeText(context,"Movie & Celeb is not there in the JSON File",Toast.LENGTH_LONG).show();
            }
            else if (view.getId() == R.id.ib_bookmark) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), ib_bookmark, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), ib_bookmark, context);
            }
            else if(view.getId()==R.id.card_comment_text_send_btn)
            {
                new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar","abhiint",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),card_comment_text_edittxt.getText().toString(),card_comment_text_edittxt,context);
            }
            else if(view.getId()==R.id.card_comment_text_see_more_comments)
            {
                testing.seeMoreComments(outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            }

        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description,card_comment_text_see_more_comments;
        ImageButton ib_like,ib_bookmark;
        Button followbtn;
        ImageButton video_btn,card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        LinearLayout header_linear;
        LinearLayout card_description_linear;

        public ViewHolder5(View itemView) {
            super(itemView);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            followbtn.setOnClickListener(this);
            ib_bookmark=(ImageButton)itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn=(ImageButton)itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt=(EditText)itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments=(TextView)itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            ib_bookmark.setOnClickListener(this);
            ib_like.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            } else if (view.getId() == R.id.card_description_linear) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            " ", new NewsOnClickFragment(), outerHits.getHits().get(getAdapterPosition()).get_source().getContentType()
                    );
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            " ", new NewsOnClickFragment(), outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic()
                    );
                } else {
                    testing.newsCardOnClick("",
                            "",
                            "",
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            " ", new NewsOnClickFragment(), outerHits.getHits().get(getAdapterPosition()).get_source().getContentType());
                }
            } else if (view.getId() == R.id.ib_like) {
                Log.e("inside like clikc", "inside like click");
                ib_like.setImageResource(R.drawable.like_pink);
                new PostRetrofit().postRetrofitMethod("like", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getId(), ib_like, context);
            }
            else if (view.getId() == R.id.followbtn) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), followbtn, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), followbtn, context);
                else
                    Toast.makeText(context,"Movie & Celeb is not there in the JSON File",Toast.LENGTH_LONG).show();
            }
            else if (view.getId() == R.id.ib_bookmark) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), ib_bookmark, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), ib_bookmark, context);
            }
            else if(view.getId()==R.id.card_comment_text_send_btn)
            {
                new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar","abhiint",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),card_comment_text_edittxt.getText().toString(),card_comment_text_edittxt,context);
            }
            else if(view.getId()==R.id.card_comment_text_see_more_comments)
            {
                testing.seeMoreComments(outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            }
        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description,card_comment_text_see_more_comments;
        ImageButton ib_like,ib_bookmark;
        ImageButton video_btn,card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        Button followbtn;
        LinearLayout header_linear;
        LinearLayout card_description_linear;

        public ViewHolder6(View itemView) {
            super(itemView);
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
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_comment_text_send_btn=(ImageButton)itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt=(EditText)itemView.findViewById(R.id.card_comment_text_edittxt);
            followbtn.setOnClickListener(this);
            ib_bookmark=(ImageButton)itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_see_more_comments=(TextView)itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            ib_bookmark.setOnClickListener(this);
            ib_like.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            //video_btn.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() == null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new MovieFragment(), 1);
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() == null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            } else if (view.getId() == R.id.card_description_linear) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            " ", new NewsOnClickFragment(), outerHits.getHits().get(getAdapterPosition()).get_source().getContentType()
                    );
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            " ", new NewsOnClickFragment(), outerHits.getHits().get(getAdapterPosition()).get_source().getContentType()
                    );
                } else {
                    testing.newsCardOnClick("",
                            "",
                            "",
                            outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                            " ", new NewsOnClickFragment(), outerHits.getHits().get(getAdapterPosition()).get_source().getContentType());
                }
            } else if (view.getId() == R.id.ib_like) {
                Log.e("inside like clikc", "inside like click");
                ib_like.setImageResource(R.drawable.like_pink);
                new PostRetrofit().postRetrofitMethod("like", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getId(), ib_like, context);
            }
            else if (view.getId() == R.id.followbtn) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), followbtn, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), followbtn, context);
                else
                    Toast.makeText(context,"Movie & Celeb is not there in the JSON File",Toast.LENGTH_LONG).show();
            }
            else if (view.getId() == R.id.ib_bookmark) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), ib_bookmark, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), ib_bookmark, context);
            }
            else if(view.getId()==R.id.card_comment_text_send_btn)
            {
                new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar","abhiint",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),card_comment_text_edittxt.getText().toString(),card_comment_text_edittxt,context);
            }
            else if(view.getId()==R.id.card_comment_text_see_more_comments)
            {
                testing.seeMoreComments(outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            }
        }
    }


    public class ViewHolder7 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description,card_comment_text_see_more_comments;
        ImageButton video_btn;
        ImageButton ib_like,ib_bookmark,card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        Button followbtn;
        LinearLayout header_linear;
        LinearLayout card_description_linear;

        public ViewHolder7(View itemView) {
            super(itemView);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            followbtn.setOnClickListener(this);
            ib_bookmark=(ImageButton)itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn=(ImageButton)itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt=(EditText)itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments=(TextView)itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            ib_bookmark.setOnClickListener(this);
            ib_like.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
            card_gallary1_img1.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a = 1;
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            } else if (view.getId() == R.id.card_description_linear) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new AuctionDetailFragment())
                        .addToBackStack("")
                        .commit();
            } else if (view.getId() == R.id.card_gallary1_img1) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick());
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick());
                } else {
                    testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                            "",
                            "", "", outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick());

                }
            } else if (view.getId() == R.id.ib_like) {
                Log.e("inside like clikc", "inside like click");
                ib_like.setImageResource(R.drawable.like_pink);
                new PostRetrofit().postRetrofitMethod("like", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getId(), ib_like, context);
            }
            else if (view.getId() == R.id.followbtn) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), followbtn, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), followbtn, context);
                else
                    Toast.makeText(context,"Movie & Celeb is not there in the JSON File",Toast.LENGTH_LONG).show();
            }
            else if (view.getId() == R.id.ib_bookmark) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), ib_bookmark, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), ib_bookmark, context);
            }
            else if(view.getId()==R.id.card_comment_text_send_btn)
            {
                new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar","abhiint",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),card_comment_text_edittxt.getText().toString(),card_comment_text_edittxt,context);
            }
            else if(view.getId()==R.id.card_comment_text_see_more_comments)
            {
                testing.seeMoreComments(outerHits.getHits().get(getAdapterPosition()).get_source().getId());
            }
        }
    }

    public class ViewHolder8 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_audio_jukebox_imageview, profile_image;
        TextView tv_tag_name, tv_tag_desc,card_comment_text_see_more_comments;
        ImageButton ib_like,ib_bookmark,card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        ImageButton video_btn;
        LinearLayout header_linear;
        LinearLayout card_description_linear;
        RecyclerView fragment_common_recyclerview_recycler;
        RelativeLayout card_footer_container;
        Button followbtn;

        public ViewHolder8(View itemView) {
            super(itemView);
            card_audio_jukebox_imageview = (ImageView) itemView.findViewById(R.id.card_audio_jukebox_imageview);
            fragment_common_recyclerview_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_recycler);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_footer_container = (RelativeLayout) itemView.findViewById(R.id.card_footer_container);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            followbtn.setOnClickListener(this);
            card_comment_text_send_btn=(ImageButton)itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_send_btn.setOnClickListener(this);
            card_comment_text_edittxt=(EditText)itemView.findViewById(R.id.card_comment_text_edittxt);
            ib_bookmark=(ImageButton)itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_see_more_comments=(TextView)itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setOnClickListener(this);
            ib_bookmark.setOnClickListener(this);
            ib_like.setOnClickListener(this);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            //card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() == null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() == null) {
                    testing.test(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            } else if (view.getId() == R.id.ib_like) {
                Log.e("inside like clikc", "inside like click");
                ib_like.setImageResource(R.drawable.like_pink);
                new PostRetrofit().postRetrofitMethod("like", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getId(), ib_like, context);
            }
            else if (view.getId() == R.id.followbtn) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), followbtn, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitFollowMethod("follow", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), followbtn, context);
                else
                    Toast.makeText(context,"Movie & Celeb is not there in the JSON File",Toast.LENGTH_LONG).show();
            }
            else if (view.getId() == R.id.ib_bookmark) {
                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getId(), ib_bookmark, context);
                else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0)
                    new PostRetrofit().postRetrofitBookmarkMethod("bookmark", "hell", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getId(), ib_bookmark, context);
            }
            else if(view.getId()==R.id.card_comment_text_send_btn)
            {
                new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar","abhiint",
                        outerHits.getHits().get(getAdapterPosition()).get_source().getId(),card_comment_text_edittxt.getText().toString(),card_comment_text_edittxt,context);
            }
            else if(view.getId()==R.id.card_comment_text_see_more_comments)
            {
                testing.seeMoreComments(outerHits.getHits().get(getAdapterPosition()).get_source().getId());
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

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


}
