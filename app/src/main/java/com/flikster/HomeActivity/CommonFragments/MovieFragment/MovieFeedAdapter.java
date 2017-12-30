package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
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
import com.flikster.FullScreenYoutubeView.FullScreenYoutubeView;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFeedAdapter;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryCardClick;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryCardClickAdapter;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.R;
import com.flikster.HomeActivity.CommonFragments.VideoFragment.VideoGalleryFragment;
import com.flikster.Util.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MovieFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String userId;
    String entityId;
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    List<String> imag = new ArrayList<>();
    MovieFeedRecommendedMoviesViewHolder movieFeedRecommendedMoviesViewHolder;
    RecyclerView.LayoutManager layoutManager2;
    ApiInterface apiInterface;
    List<RecommendedMoviesData.RecommendedMoviesInnerData> items;
    Integer Count;
    MovieFragment.MovieItemClickInterface movieItemClickInterface;
    String censor;
    String coverpic;
    String dor;
    String duration;
    String title;
    ArrayList<String> genre = new ArrayList<>();
    String slug;
    FeedInnerData hits;

    public MovieFeedAdapter(Context context, FragmentManager fragmentManager, String coverpic, String censor,
                            String dor, ArrayList<String> genre, String duration,
                            String title, String slug, FeedInnerData hits,
                            String userId, String entityId) {
        this.userId = userId;
        this.entityId = entityId;
        this.context = context;
        this.movieItemClickInterface = (MovieFragment.MovieItemClickInterface) context;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.fragmentManager = fragmentManager;
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        type.add(5);
        type.add(6);
        type.add(7);
        type.add(8);
        type.add(9);
        type.add(3);
        type.add(4);
        type.add(3);
        type.add(4);
        type.add(7);
        this.genre = genre;
        this.coverpic = coverpic;
        this.title = title;
        this.dor = dor;
        this.censor = censor;
        this.duration = duration;
        this.slug = slug;
        this.hits = hits;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_feed_profile, parent, false);
            return new ViewHolder0(view);
        } else if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_critic_review, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
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
        } else if (viewType == 100) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_no_comments, parent, false);
            return new ViewHolder100(view);
        }
        return null;
    }

    public String formatDate(String dateOfRelease) {
        String subString = dateOfRelease.substring(3, dateOfRelease.indexOf("GMT") - 9);
        return subString;
    }

    public String formatGenre() {
        String genre = "";
        for (int i = 0; i < this.genre.size(); i++) {
            if (i < genre.length() - 1)
                genre = genre + this.genre.get(i) + " | ";
            else
                genre = genre + this.genre.get(i);
        }
        return genre;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            if (title != null && !title.isEmpty()) {
                ((ViewHolder0) holder).card_movie_feed_profile_moviename.setText(title);
                new PostRetrofit().checkForFollow("follow", userId, entityId, ((ViewHolder0) holder).followbtn, context);
            }
            if (coverpic != null && !coverpic.isEmpty()) {
                Glide.with(context).load(coverpic).asBitmap()
                        .into(((ViewHolder0) holder).card_movie_feed_profile_image);
            }
            if (censor != null && !censor.isEmpty()) {
                ((ViewHolder0) holder).card_movie_feed_profile_censor.setText(censor);
            }
            if (dor != null && !dor.isEmpty()) {
                ((ViewHolder0) holder).card_movie_feed_profile_dor.setText(dor);
            }
            if (duration != null && !duration.isEmpty()) {
                ((ViewHolder0) holder).card_movie_feed_profile_dur.setText(duration);
            }
            if (genre != null && !genre.isEmpty()) {
                ((ViewHolder0) holder).card_movie_feed_profile_genre.setText(formatGenre());
            }
            ((ViewHolder0) holder).card_movie_feed_profile_storyline.setVisibility(View.GONE);
        } else if (holder.getItemViewType() == 1) {
            ((ViewHolder1) holder).card_header_container.setVisibility(View.GONE);
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getProfilePic()).into(((ViewHolder1) holder).card_critic_review_main_image);
            if (hits.getHits().get(position - 1).get_source().getMovie() != null) {
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder1) holder).profile_image));
                ((ViewHolder1) holder).tv_tag_desc.setText(hits.getHits().get(position - 1).get_source().getMovie().get(0).getType());
                ((ViewHolder1) holder).tv_tag_name.setText(hits.getHits().get(position - 1).get_source().getMovie().get(0).getName());
                ((ViewHolder1) holder).card_critic_review_moviename.setText(hits.getHits().get(position - 1).get_source().getMovie().get(0).getName());
            } else if (hits.getHits().get(position - 1).get_source().getCeleb() != null) {
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder1) holder).profile_image));
                ((ViewHolder1) holder).tv_tag_desc.setText(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getType());
                ((ViewHolder1) holder).tv_tag_name.setText(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getName());
            }
            ((ViewHolder1) holder).card_movie_review_bottom_header_criticrating.setText(hits.getHits().get(position - 1).get_source().getRating());
            if (hits.getHits().get(position - 1).get_source().getText() == null)
                ((ViewHolder1) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position - 1).get_source().getText() != null)
                ((ViewHolder1) holder).tv_description.setText(Html.fromHtml(Common.formatString(hits.getHits().get(position - 1).get_source().getText())));
            ((ViewHolder1) holder).tv_name.setText(hits.getHits().get(position - 1).get_source().getTitle());
            ((ViewHolder1) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position - 1).get_source().getTitle());
        } else if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).card_header_container.setVisibility(View.GONE);
            Log.e("inside bindview", "" + position);
            if (hits.getHits().get(position - 1).get_source().getText() == null)
                ((ViewHolder2) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position - 1).get_source().getText() != null)
                ((ViewHolder2) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position - 1).get_source().getText()));
            ((ViewHolder2) holder).tv_name.setText(hits.getHits().get(position - 1).get_source().getTitle());
            ((ViewHolder2) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position - 1).get_source().getTitle());
        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getText() == null)
                ((ViewHolder3) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position - 1).get_source().getText() != null)
                ((ViewHolder3) holder).tv_description.setText(Html.fromHtml(Common.formatString(hits.getHits().get(position - 1).get_source().getText())));
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getProfilePic())
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder3) holder).card_gallary1_img1);
            ((ViewHolder3) holder).tv_name.setText(hits.getHits().get(position - 1).get_source().getTitle());
            ((ViewHolder3) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position - 1).get_source().getTitle());
            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder3) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder3) holder).ib_bookmark, context);
        } else if (holder.getItemViewType() == 4) {
            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder4) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder4) holder).ib_bookmark, context);
            ((ViewHolder4) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getText() == null)
                ((ViewHolder4) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position - 1).get_source().getText() != null)
                ((ViewHolder4) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position - 1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getProfilePic())
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder4) holder).news_img);
            ((ViewHolder4) holder).tv_name.setText(hits.getHits().get(position - 1).get_source().getTitle());
            ((ViewHolder4) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position - 1).get_source().getTitle());
        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getText() == null)
                ((ViewHolder5) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position - 1).get_source().getText() != null)
                ((ViewHolder5) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position - 1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getProfilePic())
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder5) holder).card_gallary1_img1);
            ((ViewHolder5) holder).tv_name.setText(hits.getHits().get(position - 1).get_source().getTitle());
            ((ViewHolder5) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position - 1).get_source().getTitle());
            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder5) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder5) holder).ib_bookmark, context);
        } else if (holder.getItemViewType() == 6) {
            ((ViewHolder6) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getText() == null)
                ((ViewHolder6) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position - 1).get_source().getText() != null)
                ((ViewHolder6) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position - 1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getProfilePic())
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder6) holder).card_gallary1_img1);
            ((ViewHolder6) holder).tv_name.setText(hits.getHits().get(position - 1).get_source().getTitle());
            ((ViewHolder6) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position - 1).get_source().getTitle());
            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder6) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder6) holder).ib_bookmark, context);
        } else if (holder.getItemViewType() == 10) {
            ((ViewHolder7) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getText() == null)
                ((ViewHolder7) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position - 1).get_source().getText() != null)
                ((ViewHolder7) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position - 1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(0))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder7) holder).card_gallary1_img1);
            ((ViewHolder7) holder).tv_name.setText(hits.getHits().get(position - 1).get_source().getTitle());
            ((ViewHolder7) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position - 1).get_source().getTitle());
            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder7) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder7) holder).ib_bookmark, context);
        } else if (holder.getItemViewType() == 8) {
            ((ViewHolder8) holder).card_header_container.setVisibility(View.GONE);
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getProfilePic()).into(((ViewHolder8) holder).card_audio_jukebox_imageview);
            ((ViewHolder8) holder).card_footer_container.setVisibility(View.GONE);
        } else if (holder.getItemViewType() == 11) {
            ((ViewHolder11) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getText() == null)
                ((ViewHolder11) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position - 1).get_source().getText() != null)
                ((ViewHolder11) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position - 1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(0))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder11) holder).card_gallary2_img1);
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(1))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder11) holder).card_gallary2_img2);
            ((ViewHolder11) holder).tv_name.setText(hits.getHits().get(position - 1).get_source().getTitle());
            ((ViewHolder11) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position - 1).get_source().getTitle());
            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder11) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder11) holder).ib_bookmark, context);
        } else if (holder.getItemViewType() == 12) {
            ((ViewHolder12) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getText() == null)
                ((ViewHolder12) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position - 1).get_source().getText() != null)
                ((ViewHolder12) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position - 1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(0))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder12) holder).card_gallary3_img1);
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(1))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder12) holder).card_gallary3_img2);
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(2))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder12) holder).card_gallary3_img3);
            ((ViewHolder12) holder).tv_name.setText(hits.getHits().get(position - 1).get_source().getTitle());
            ((ViewHolder12) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position - 1).get_source().getTitle());
            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder12) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder12) holder).ib_bookmark, context);
        } else if (holder.getItemViewType() == 13) {
            ((ViewHolder13) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getText() == null)
                ((ViewHolder13) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position - 1).get_source().getText() != null)
                ((ViewHolder13) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position - 1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(0))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder13) holder).card_gallary4_img1);
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(1))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder13) holder).card_gallary4_img2);
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(2))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder13) holder).card_gallary4_img3);
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(3))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder13) holder).card_gallary4_img4);
            ((ViewHolder13) holder).tv_name.setText(hits.getHits().get(position - 1).get_source().getTitle());
            ((ViewHolder13) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position - 1).get_source().getTitle());
            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder13) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder13) holder).ib_bookmark, context);
        } else if (holder.getItemViewType() == 14) {
            ((ViewHolder14) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getText() == null)
                ((ViewHolder14) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position - 1).get_source().getText() != null)
                ((ViewHolder14) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position - 1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(0))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder14) holder).card_gallary5_img1);
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(1))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder14) holder).card_gallary5_img2);
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(2))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder14) holder).card_gallary5_img3);
            Glide.with(context).load(hits.getHits().get(position - 1).get_source().getMedia().getGallery().get(3))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                    .into(((ViewHolder14) holder).card_gallary5_img4);
            ((ViewHolder14) holder).tv_name.setText(hits.getHits().get(position - 1).get_source().getTitle());
            ((ViewHolder14) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position - 1).get_source().getTitle());
            ((ViewHolder14) holder).card_gallary5_text.setText("+" + ((hits.getHits().get(position - 1).get_source().getMedia().getGallery().size()) - 4));
            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder14) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position - 1).get_source().getProfilePic(), ((ViewHolder14) holder).ib_bookmark, context);
        } else if (holder.getItemViewType() == 100) {
            ((ViewHolder100) holder).activity_no_comments_tv.setText("No Contents Available!");
        }

    }

    private void recommendedMoviesRetrofitInit(final RecyclerView.ViewHolder holder) {
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/movie-ms/movies/").create(ApiInterface.class);
        Call<RecommendedMoviesData> call = apiInterface.getRecommendedMoviesData("http://apiv3.flikster.com/v3/movie-ms/movies");
        call.enqueue(new Callback<RecommendedMoviesData>() {
            @Override
            public void onResponse(Call<RecommendedMoviesData> call, Response<RecommendedMoviesData> response) {
                items = response.body().getItems();
                //Count = response.body().getCount();
                /*((ViewHolder7) holder).card_steal_style_carousel_title.setText("Recommended Movies");
                ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_title.setVisibility(View.GONE);
                layoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager2);
                movieFeedRecommendedMoviesViewHolder = new MovieFeedRecommendedMoviesViewHolder(items,context);
                ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(movieFeedRecommendedMoviesViewHolder);*/
            }

            @Override
            public void onFailure(Call<RecommendedMoviesData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (hits.getHits().size() != 0 && hits.getHits() != null) {
            return hits.getHits().size() + 1;
        } else
            return 2;

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        else {
            if (hits.getHits().size() != 0 && hits.getHits() != null) {
                if (hits.getHits().get(position - 1).get_source().getContentType() != null) {
                    switch (hits.getHits().get(position - 1).get_source().getContentType().toLowerCase()) {
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
                        case "tweet":
                            return 6;
                        case "gallery": {
                            if (hits.getHits().get(position - 1).get_source().getMedia().getGallery() != null
                                    && hits.getHits().get(position - 1).get_source().getMedia().getGallery().size() == 1)
                                return 10;
                            else if (hits.getHits().get(position - 1).get_source().getMedia().getGallery() != null
                                    && hits.getHits().get(position - 1).get_source().getMedia().getGallery().size() == 2)
                                return 11;
                            else if (hits.getHits().get(position - 1).get_source().getMedia().getGallery() != null
                                    && hits.getHits().get(position - 1).get_source().getMedia().getGallery().size() == 3)
                                return 12;
                            else if (hits.getHits().get(position - 1).get_source().getMedia().getGallery() != null
                                    && hits.getHits().get(position - 1).get_source().getMedia().getGallery().size() == 4)
                                return 13;
                            else if (hits.getHits().get(position - 1).get_source().getMedia().getGallery() != null
                                    && hits.getHits().get(position - 1).get_source().getMedia().getGallery().size() > 4)
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
                            return 4;
                        case "interview":
                            return 4;
                        case "trailer":
                            return 4;
                        case "teasers-promos":
                            return 4;
                    }
                }

            }
            return 100;
        }
    }

    public class ViewHolder0 extends RecyclerView.ViewHolder {
        TextView card_movie_feed_profile_moviename, card_movie_feed_profile_censor, card_movie_feed_profile_dor,
                card_movie_feed_profile_dur, card_movie_feed_profile_genre, card_movie_feed_profile_storyline;
        ImageView card_movie_feed_profile_image;
        Button followbtn;
        ImageButton ib_like, unlike;
        LinearLayout willwatchLayout, wontwatchLayout;
        TextView likeCounttxt, unlikeCounttxt;

        public ViewHolder0(View itemView) {
            super(itemView);
            card_movie_feed_profile_moviename = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_moviename);
            card_movie_feed_profile_censor = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_censor);
            card_movie_feed_profile_image = (ImageView) itemView.findViewById(R.id.card_movie_feed_profile_image);
            card_movie_feed_profile_dor = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_dor);
            card_movie_feed_profile_dur = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_dur);
            card_movie_feed_profile_genre = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_genre);
            card_movie_feed_profile_storyline = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_storyline);

            willwatchLayout = (LinearLayout) itemView.findViewById(R.id.willwatchLayout);
            wontwatchLayout = (LinearLayout) itemView.findViewById(R.id.wontwatchLayout);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            unlike = (ImageButton) itemView.findViewById(R.id.unlike);
            likeCounttxt = (TextView) itemView.findViewById(R.id.likeCounttxt);
            unlikeCounttxt = (TextView) itemView.findViewById(R.id.unlikeCounttxt);

            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Will Watch", Toast.LENGTH_SHORT).show();
                    Common.willWatchOrNot(context, ib_like, userId, entityId);
                    unlike.setImageResource(R.drawable.unlikesmallicon);
                    likeCounttxt.setText("1");
                    unlikeCounttxt.setText("0");
                }
            });
            unlike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Wont Watch", Toast.LENGTH_SHORT).show();
                    Common.wantWatchHit(context, unlike, userId, entityId);
                    ib_like.setImageResource(R.drawable.likesmallicon);
                    likeCounttxt.setText("0");
                    unlikeCounttxt.setText("1");
                }
            });


            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.followOrUnFollow(context, followbtn, userId, entityId);
                }
            });
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView card_critic_review_main_image, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_critic_review_moviename,
                card_movie_review_bottom_header_criticrating, card_celebrity_feed_gallery1_title,
                card_comment_text_see_more_comments;
        ImageButton card_footer_share, ib_like, ib_bookmark, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        LinearLayout header_linear, card_description_linear;
        RelativeLayout card_header_container;
        Button followbtn;

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
            card_critic_review_moviename = (TextView) itemView.findViewById(R.id.card_critic_review_moviename);
            card_movie_review_bottom_header_criticrating = (TextView) itemView.findViewById(R.id.card_movie_review_bottom_header_criticrating);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n", context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });

            card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                            userId,
                            hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(),
                            card_comment_text_edittxt.getText().toString(),
                            card_comment_text_edittxt, context);
                }
            });
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_quote_tv, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        LinearLayout card_description_linear, header_linear;
        Button followbtn;
        ImageButton card_footer_share, ib_like, ib_bookmark, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        RelativeLayout card_header_container;

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
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_description_linear.setOnClickListener(this);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n", context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                            userId,
                            hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(),
                            card_comment_text_edittxt.getText().toString(),
                            card_comment_text_edittxt, context);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_description_linear) {
                cardDescLinearClick(getAdapterPosition() - 1);
            }
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        ImageButton card_footer_share, ib_like, ib_bookmark;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton video_btn, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        LinearLayout header_linear;
        LinearLayout card_description_linear;
        RelativeLayout card_header_container;

        public ViewHolder3(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_description_linear.setOnClickListener(this);
            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n", context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                            userId,
                            hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(),
                            card_comment_text_edittxt.getText().toString(),
                            card_comment_text_edittxt, context);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_description_linear) {
                cardDescLinearClick(getAdapterPosition() - 1);
            }
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView news_img, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton card_footer_share, ib_like, ib_bookmark, card_comment_text_send_btn, video_btn;
        EditText card_comment_text_edittxt;
        RelativeLayout card_header_container;
        LinearLayout header_linear, card_description_linear;

        public ViewHolder4(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            news_img = (ImageView) itemView.findViewById(R.id.news_img);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            video_btn = (ImageButton) itemView.findViewById(R.id.video_btn);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_description_linear.setOnClickListener(this);
            video_btn.setOnClickListener(this);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n", context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                            userId,
                            hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(),
                            card_comment_text_edittxt.getText().toString(),
                            card_comment_text_edittxt, context);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_description_linear) {
                cardVideoCardDescClick(getAdapterPosition() - 1);
            } else if (view.getId() == R.id.video_btn) {
                cardVideoButtonClick(getAdapterPosition() - 1);
            }
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton card_footer_share, ib_like, ib_bookmark;
        ImageButton video_btn, card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        LinearLayout header_linear;
        RelativeLayout card_header_container;
        LinearLayout card_description_linear;

        public ViewHolder5(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_description_linear.setOnClickListener(this);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n", context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                            userId,
                            hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(),
                            card_comment_text_edittxt.getText().toString(),
                            card_comment_text_edittxt, context);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_description_linear) {
                cardDescLinearClick(getAdapterPosition() - 1);
            }
        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton card_footer_share, ib_like, ib_bookmark;
        ImageButton card_comment_text_send_btn;
        EditText card_comment_text_edittxt;

        LinearLayout card_description_linear;
        RelativeLayout card_header_container;

        public ViewHolder6(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_description_linear.setOnClickListener(this);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n", context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                            userId,
                            hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(),
                            card_comment_text_edittxt.getText().toString(),
                            card_comment_text_edittxt, context);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_description_linear) {
                cardDescLinearClick(getAdapterPosition() - 1);
            }
        }
    }


    public class ViewHolder7 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        ImageButton card_footer_share, ib_like, ib_bookmark;
        LinearLayout header_linear;
        LinearLayout card_description_linear;
        RelativeLayout card_header_container;

        public ViewHolder7(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_description_linear.setOnClickListener(this);
            card_gallary1_img1.setOnClickListener(this);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n", context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                            userId,
                            hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(),
                            card_comment_text_edittxt.getText().toString(),
                            card_comment_text_edittxt, context);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_description_linear) {
                //cardDescLinearClick(getAdapterPosition()-1);
            } else if (view.getId() == R.id.card_gallary1_img1) {
                cardGalleryContainerClick(getAdapterPosition() - 1);
            }
        }
    }

    public class ViewHolder8 extends RecyclerView.ViewHolder {
        ImageView card_audio_jukebox_imageview, profile_image;
        TextView tv_tag_name, tv_tag_desc, card_comment_text_see_more_comments, card_celebrity_feed_gallery1_title;
        ImageButton card_comment_text_send_btn;
        ImageButton card_footer_share, ib_like, ib_bookmark;
        EditText card_comment_text_edittxt;
        LinearLayout header_linear;
        RecyclerView fragment_common_recyclerview_recycler;
        RelativeLayout card_footer_container;
        RelativeLayout card_header_container;

        public ViewHolder8(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_audio_jukebox_imageview = (ImageView) itemView.findViewById(R.id.card_audio_jukebox_imageview);
            fragment_common_recyclerview_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_recycler);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_footer_container = (RelativeLayout) itemView.findViewById(R.id.card_footer_container);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n", context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                            userId,
                            hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(),
                            card_comment_text_edittxt.getText().toString(),
                            card_comment_text_edittxt, context);
                }
            });
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
        ImageButton card_comment_text_send_btn;
        ImageButton card_footer_share, ib_like, ib_bookmark;
        EditText card_comment_text_edittxt;
        LinearLayout header_linear, card_gallery2_img_container;
        LinearLayout card_description_linear;
        RelativeLayout card_header_container;

        public ViewHolder11(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary2_img1 = (ImageView) itemView.findViewById(R.id.card_gallary2_img1);
            card_gallary2_img2 = (ImageView) itemView.findViewById(R.id.card_gallary2_img2);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_gallery2_img_container = (LinearLayout) itemView.findViewById(R.id.card_gallery2_img_container);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_gallery2_img_container.setOnClickListener(this);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n", context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                            userId,
                            hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(),
                            card_comment_text_edittxt.getText().toString(),
                            card_comment_text_edittxt, context);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_gallery2_img_container) {
                cardGalleryContainerClick(getAdapterPosition() - 1);
            }
        }
    }


    public class ViewHolder12 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary3_img1, card_gallary3_img2, card_gallary3_img3, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton card_comment_text_send_btn;
        ImageButton card_footer_share, ib_like, ib_bookmark;
        EditText card_comment_text_edittxt;
        LinearLayout header_linear, card_gallery3_1_img_container;
        RelativeLayout card_header_container;
        LinearLayout card_description_linear;

        public ViewHolder12(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary3_img1 = (ImageView) itemView.findViewById(R.id.card_gallary3_img1);
            card_gallary3_img2 = (ImageView) itemView.findViewById(R.id.card_gallary3_img2);
            card_gallary3_img3 = (ImageView) itemView.findViewById(R.id.card_gallary3_img3);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_gallery3_1_img_container = (LinearLayout) itemView.findViewById(R.id.card_gallery3_1_img_container);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_gallery3_1_img_container.setOnClickListener(this);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n", context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                            userId,
                            hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(),
                            card_comment_text_edittxt.getText().toString(),
                            card_comment_text_edittxt, context);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_gallery3_1_img_container) {
                cardGalleryContainerClick(getAdapterPosition() - 1);
            }
        }
    }


    public class ViewHolder13 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary4_img1, card_gallary4_img2, card_gallary4_img3, card_gallary4_img4, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        ImageButton card_comment_text_send_btn;
        ImageButton card_footer_share, ib_like, ib_bookmark;
        EditText card_comment_text_edittxt;
        LinearLayout header_linear, card_gallery4_img_container;
        RelativeLayout card_header_container;
        LinearLayout card_description_linear;

        public ViewHolder13(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary4_img1 = (ImageView) itemView.findViewById(R.id.card_gallary4_img1);
            card_gallary4_img2 = (ImageView) itemView.findViewById(R.id.card_gallary4_img2);
            card_gallary4_img3 = (ImageView) itemView.findViewById(R.id.card_gallary4_img3);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            card_gallary4_img4 = (ImageView) itemView.findViewById(R.id.card_gallary4_img4);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_gallery4_img_container = (LinearLayout) itemView.findViewById(R.id.card_gallery4_img_container);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_gallery4_img_container.setOnClickListener(this);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n", context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                            userId,
                            hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(),
                            card_comment_text_edittxt.getText().toString(),
                            card_comment_text_edittxt, context);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_gallery4_img_container) {
                cardGalleryContainerClick(getAdapterPosition() - 1);
            }
        }
    }


    public class ViewHolder14 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary5_img1, card_gallary5_img2, card_gallary5_img3, card_gallary5_img4, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title, card_gallary5_text;
        ImageButton card_comment_text_send_btn;
        EditText card_comment_text_edittxt;
        ImageButton card_footer_share, ib_like, ib_bookmark;
        RelativeLayout card_gallery5_img_container, card_header_container;
        LinearLayout card_description_linear;

        public ViewHolder14(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary5_img1 = (ImageView) itemView.findViewById(R.id.card_gallary5_img1);
            card_gallary5_img2 = (ImageView) itemView.findViewById(R.id.card_gallary5_img2);
            card_gallary5_img3 = (ImageView) itemView.findViewById(R.id.card_gallary5_img3);
            card_gallary5_img4 = (ImageView) itemView.findViewById(R.id.card_gallary5_img4);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            card_gallary5_text = (TextView) itemView.findViewById(R.id.card_gallary5_text);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            card_gallery5_img_container = (RelativeLayout) itemView.findViewById(R.id.card_gallery5_img_container);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_comment_text_send_btn = (ImageButton) itemView.findViewById(R.id.card_comment_text_send_btn);
            card_comment_text_edittxt = (EditText) itemView.findViewById(R.id.card_comment_text_edittxt);
            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_gallery5_img_container.setOnClickListener(this);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic() + "\n\n\n" + "Download **Flikster** and don't miss anything from movie industry. Stay connected to the world of Illusion.\n", context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic());
                }
            });
            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_comment_text_send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostRetrofit().postRetrofitCommentMethod("Abhishek Kumar",
                            userId,
                            hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(),
                            card_comment_text_edittxt.getText().toString(),
                            card_comment_text_edittxt, context);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_gallery5_img_container) {
                cardGalleryContainerClick(getAdapterPosition() - 1);
            }
        }
    }

    public class ViewHolder100 extends RecyclerView.ViewHolder {
        TextView activity_no_comments_tv;

        public ViewHolder100(View itemView) {
            super(itemView);
            activity_no_comments_tv = (TextView) itemView.findViewById(R.id.activity_no_comments_tv);
        }
    }


    public void cardDescLinearClick(int pos) {
        if (hits.getHits().get(pos).get_source().getMovie() != null && hits.getHits().get(pos).get_source().getMovie().size() != 0) {
            movieItemClickInterface.newsCardOnClick(hits.getHits().get(pos).get_source().getMovie().get(0).getProfilePic(),
                    hits.getHits().get(pos).get_source().getMovie().get(0).getName(),
                    hits.getHits().get(pos).get_source().getMovie().get(0).getType(),
                    hits.getHits().get(pos).get_source().getProfilePic(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    hits.getHits().get(pos).get_source().getTitle(), new NewsOnClickFragment(),
                    hits.getHits().get(pos).get_source().getContentType(),
                    userId,
                    hits.getHits().get(pos).get_source().getId()
            );
        } else if (hits.getHits().get(pos).get_source().getCeleb() != null && hits.getHits().get(pos).get_source().getCeleb().size() != 0) {
            movieItemClickInterface.newsCardOnClick(hits.getHits().get(pos).get_source().getCeleb().get(0).getProfilePic(),
                    hits.getHits().get(pos).get_source().getCeleb().get(0).getName(),
                    hits.getHits().get(pos).get_source().getCeleb().get(0).getType(),
                    hits.getHits().get(pos).get_source().getProfilePic(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    new NewsOnClickFragment(),
                    hits.getHits().get(pos).get_source().getContentType(),
                    userId,
                    hits.getHits().get(pos).get_source().getId()
            );
        } else {
            movieItemClickInterface.newsCardOnClick("",
                    "",
                    "",
                    hits.getHits().get(pos).get_source().getProfilePic(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    new NewsOnClickFragment(),
                    hits.getHits().get(pos).get_source().getContentType(),
                    userId,
                    hits.getHits().get(pos).get_source().getId());
        }
    }

    public void cardGalleryContainerClick(int pos) {
        if (hits.getHits().get(pos).get_source().getMovie() != null) {
            movieItemClickInterface.galleryCardOnClick(hits.getHits().get(pos).get_source().getMedia().getGallery(),
                    hits.getHits().get(pos).get_source().getMovie().get(0).getName(),
                    hits.getHits().get(pos).get_source().getMovie().get(0).getProfilePic(),
                    hits.getHits().get(pos).get_source().getMovie().get(0).getType(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    new GalleryCardClick(), userId,
                    hits.getHits().get(pos).get_source().getId());
        } else if (hits.getHits().get(pos).get_source().getCeleb() != null) {
            movieItemClickInterface.galleryCardOnClick(hits.getHits().get(pos).get_source().getMedia().getGallery(),
                    hits.getHits().get(pos).get_source().getCeleb().get(0).getName(),
                    hits.getHits().get(pos).get_source().getCeleb().get(0).getProfilePic(),
                    hits.getHits().get(pos).get_source().getCeleb().get(0).getType(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    new GalleryCardClick(), userId,
                    hits.getHits().get(pos).get_source().getId());
        } else {
            movieItemClickInterface.galleryCardOnClick(hits.getHits().get(pos).get_source().getMedia().getGallery(),
                    "",
                    "", "", hits.getHits().get(pos).get_source().getTitle(),
                    new GalleryCardClick(), userId,
                    hits.getHits().get(pos).get_source().getId());

        }
    }

    public void cardVideoButtonClick(int pos) {
        Intent intent = new Intent(context, FullScreenYoutubeView.class);
        if (hits.getHits().get(pos).get_source().getMedia().getVideo() != null &&
                hits.getHits().get(pos).get_source().getMedia().getVideo().size() != 0) {
            intent.putExtra("video_link",
                    hits.getHits().get(pos).get_source().getMedia().getVideo().get(0));
        } else {
            intent.putExtra("video_link", " ");
        }
        context.startActivity(intent);
    }

    public void cardVideoCardDescClick(int pos) {
        if (hits.getHits().get(pos).get_source().getMovie() != null && hits.getHits().get(pos).get_source().getMovie().size() != 0) {
            movieItemClickInterface.videoCardOnClick(hits.getHits().get(pos).get_source().getMovie().get(0).getProfilePic(),
                    hits.getHits().get(pos).get_source().getMovie().get(0).getName(),
                    hits.getHits().get(pos).get_source().getMovie().get(0).getType(),
                    hits.getHits().get(pos).get_source().getProfilePic(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    hits.getHits().get(pos).get_source().getMedia().getVideo().get(0),
                    new VideoGalleryFragment(),
                    hits.getHits().get(pos).get_source().getContentType(),
                    userId,
                    hits.getHits().get(pos).get_source().getId()

            );
        } else if (hits.getHits().get(pos).get_source().getCeleb() != null && hits.getHits().get(pos).get_source().getCeleb().size() != 0) {
            movieItemClickInterface.videoCardOnClick(hits.getHits().get(pos).get_source().getCeleb().get(0).getProfilePic(),
                    hits.getHits().get(pos).get_source().getCeleb().get(0).getName(),
                    hits.getHits().get(pos).get_source().getCeleb().get(0).getType(),
                    hits.getHits().get(pos).get_source().getProfilePic(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    hits.getHits().get(pos).get_source().getMedia().getVideo().get(0),
                    new VideoGalleryFragment(),
                    hits.getHits().get(pos).get_source().getContentType(),
                    userId,
                    hits.getHits().get(pos).get_source().getId()
            );
        } else {
            movieItemClickInterface.videoCardOnClick("",
                    "",
                    "",
                    hits.getHits().get(pos).get_source().getProfilePic(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    hits.getHits().get(pos).get_source().getTitle(),
                    hits.getHits().get(pos).get_source().getMedia().getVideo().get(0),
                    new VideoGalleryFragment(),
                    hits.getHits().get(pos).get_source().getContentType(),
                    userId,
                    hits.getHits().get(pos).get_source().getId());
        }
    }
}
