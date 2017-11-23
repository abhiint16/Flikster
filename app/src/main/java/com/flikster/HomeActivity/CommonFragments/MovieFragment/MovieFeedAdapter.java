package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryCardClick;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.R;
import com.flikster.HomeActivity.CommonFragments.VideoFragment.VideoGalleryFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MovieFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    List<String> imag = new ArrayList<>();
    MovieFeedRecommendedMoviesViewHolder movieFeedRecommendedMoviesViewHolder;
    RecyclerView.LayoutManager layoutManager2;
    ApiInterface apiInterface;
    List<RecommendedMoviesData.RecommendedMoviesInnerData> items;
    Integer Count;

    String censor;
    String coverpic;
    String dor;
    String duration;
    String title;
    ArrayList<String> genre = new ArrayList<>();
    String slug;
    FeedInnerData hits;

    public MovieFeedAdapter(Context context, FragmentManager fragmentManager, String coverpic, String censor,
                            String dor, ArrayList<String> genre, String duration, String title, String slug, FeedInnerData hits) {
        this.context = context;
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
        this.censor=censor;
        this.duration=duration;
        this.slug=slug;
        this.hits=hits;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_feed_profile, parent, false);
            return new ViewHolder0(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_common_feed_quote, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_common_feed_gallary1, parent, false);
            return new ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_common_feed_gallary1, parent, false);
            return new ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_common_feed_gallary1, parent, false);
            return new ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_common_feed_gallary1, parent, false);
            return new ViewHolder6(view);
        } else if(viewType==7){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_common_feed_gallary1, parent, false);
            return new ViewHolder7(view);
        }
        return null;
    }

    public  String formatDate(String dateOfRelease)
    {
        String subString=dateOfRelease.substring(3,dateOfRelease.indexOf("GMT")-9);
        return subString;
    }
    public String formatGenre()
    {
        String genre="";
        for(int i=0;i<this.genre.size();i++)
        {
            if(i<genre.length()-1)
                genre=genre+this.genre.get(i)+" | ";
            else
                genre=genre+this.genre.get(i);
        }
        return genre;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*if (holder.getItemViewType() == 1) {
            if (title != null && !title.isEmpty()) {
                ((ViewHolder1) holder).card_movie_feed_profile_moviename.setText(title);
            }
            if (coverpic != null && !coverpic.isEmpty()) {
                Glide.with(context).load(coverpic).asBitmap()
                        .into(((ViewHolder1) holder).card_movie_feed_profile_image);
            }
            if (censor != null && !censor.isEmpty()) {
                ((ViewHolder1) holder).card_movie_feed_profile_censor.setText(censor);
            }
            if(dor != null && !dor.isEmpty()){
                ((ViewHolder1) holder).card_movie_feed_profile_dor.setText(dor);
            }
            if(duration != null && !duration.isEmpty()){
                ((ViewHolder1) holder).card_movie_feed_profile_dur.setText(duration);
            }
            if(genre != null && !genre.isEmpty())
            {
                ((ViewHolder1) holder).card_movie_feed_profile_genre.setText(genre.get(0));
            }
            ((ViewHolder1) holder).card_movie_feed_profile_storyline.setVisibility(View.GONE);
        }*/
         if(holder.getItemViewType()==0)
        {
            Log.e("contenttypechecc","assdsa"+hits.getHits().get(0).get_source().getContentType());
            if (title != null && !title.isEmpty()) {
                ((ViewHolder0) holder).card_movie_feed_profile_moviename.setText(title);
            }
            if (coverpic != null && !coverpic.isEmpty()) {
                Glide.with(context).load(coverpic).asBitmap()
                        .into(((ViewHolder0) holder).card_movie_feed_profile_image);
            }
            if (censor != null && !censor.isEmpty()) {
                ((ViewHolder0) holder).card_movie_feed_profile_censor.setText(censor);
            }
            if(dor != null && !dor.isEmpty()){
                ((ViewHolder0) holder).card_movie_feed_profile_dor.setText(formatDate(dor));
            }
            if(duration != null && !duration.isEmpty()){
                ((ViewHolder0) holder).card_movie_feed_profile_dur.setText(duration);
            }
            if(genre != null && !genre.isEmpty())
            {
                ((ViewHolder0) holder).card_movie_feed_profile_genre.setText(formatGenre());
            }
            ((ViewHolder0)holder).card_movie_feed_profile_storyline.setVisibility(View.GONE);
        }

        else if (holder.getItemViewType() == 2) {
            if (hits.getHits().get(position-1).get_source().getText() == null)
                ((ViewHolder7) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position-1).get_source().getText() != null)
                ((ViewHolder7) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position-1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position-1).get_source().getProfilePic()).into(((ViewHolder7) holder).card_celebrity_feed_gallery1_img);
            ((ViewHolder7) holder).tv_name.setText(hits.getHits().get(position-1).get_source().getTitle());
            ((ViewHolder7) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position-1).get_source().getTitle());
        } else if (holder.getItemViewType() == 3) {
            if (hits.getHits().get(position-1).get_source().getText() == null)
                ((ViewHolder7) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position-1).get_source().getText() != null)
                ((ViewHolder7) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position-1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position-1).get_source().getProfilePic()).into(((ViewHolder7) holder).card_celebrity_feed_gallery1_img);
            ((ViewHolder7) holder).tv_name.setText(hits.getHits().get(position-1).get_source().getTitle());
            ((ViewHolder7) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position-1).get_source().getTitle());
        } else if (holder.getItemViewType() == 4) {
            if (hits.getHits().get(position-1).get_source().getText() == null)
                ((ViewHolder7) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position-1).get_source().getText() != null)
                ((ViewHolder7) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position-1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position-1).get_source().getProfilePic()).into(((ViewHolder7) holder).card_celebrity_feed_gallery1_img);
            ((ViewHolder7) holder).tv_name.setText(hits.getHits().get(position-1).get_source().getTitle());
            ((ViewHolder7) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position-1).get_source().getTitle());
        } else if (holder.getItemViewType() == 5) {
            if (hits.getHits().get(position-1).get_source().getText() == null)
                ((ViewHolder7) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position-1).get_source().getText() != null)
                ((ViewHolder7) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position-1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position-1).get_source().getProfilePic()).into(((ViewHolder7) holder).card_celebrity_feed_gallery1_img);
            ((ViewHolder7) holder).tv_name.setText(hits.getHits().get(position-1).get_source().getTitle());
            ((ViewHolder7) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position-1).get_source().getTitle());
        } else if (holder.getItemViewType() == 6) {
            if (hits.getHits().get(position-1).get_source().getText() == null)
                ((ViewHolder7) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position-1).get_source().getText() != null)
                ((ViewHolder7) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position-1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position-1).get_source().getProfilePic()).into(((ViewHolder7) holder).card_celebrity_feed_gallery1_img);
            ((ViewHolder7) holder).tv_name.setText(hits.getHits().get(position-1).get_source().getTitle());
            ((ViewHolder7) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position-1).get_source().getTitle());
        } else if (holder.getItemViewType() == 7) {
            Log.e("contenttypecheccbind","assdsa"+hits.getHits().get(0).get_source().getContentType());
            if (hits.getHits().get(position-1).get_source().getText() == null)
                ((ViewHolder7) holder).tv_description.setVisibility(View.GONE);
            else if (hits.getHits().get(position-1).get_source().getText() != null)
                ((ViewHolder7) holder).tv_description.setText(Html.fromHtml(hits.getHits().get(position-1).get_source().getText()));
            Glide.with(context).load(hits.getHits().get(position-1).get_source().getProfilePic()).into(((ViewHolder7) holder).card_celebrity_feed_gallery1_img);
            ((ViewHolder7) holder).tv_name.setText(hits.getHits().get(position-1).get_source().getTitle());
            ((ViewHolder7) holder).card_celebrity_feed_gallery1_title.setText(hits.getHits().get(position-1).get_source().getTitle());
        }

    }

    private void recommendedMoviesRetrofitInit(final RecyclerView.ViewHolder holder) {
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/movie-ms/movies").create(ApiInterface.class);
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
        return hits.getHits().size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0)
            return 0;
        else
        {
            switch(hits.getHits().get(position-1).get_source().getContentType()) {
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
            }
        }
        return super.getItemViewType(position);
    }

    public class ViewHolder0 extends RecyclerView.ViewHolder {
        TextView card_movie_feed_profile_moviename, card_movie_feed_profile_censor,card_movie_feed_profile_dor,
                card_movie_feed_profile_dur,card_movie_feed_profile_genre,card_movie_feed_profile_storyline;
        ImageView card_movie_feed_profile_image;

        public ViewHolder0(View itemView) {
            super(itemView);
            card_movie_feed_profile_moviename = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_moviename);
            card_movie_feed_profile_censor = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_censor);
            card_movie_feed_profile_image = (ImageView) itemView.findViewById(R.id.card_movie_feed_profile_image);
            card_movie_feed_profile_dor = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_dor);
            card_movie_feed_profile_dur = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_dur);
            card_movie_feed_profile_genre = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_genre);
            card_movie_feed_profile_storyline = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_storyline);
        }
    }


    /*public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView card_movie_feed_profile_moviename, card_movie_feed_profile_censor,card_movie_feed_profile_dor,
                card_movie_feed_profile_dur,card_movie_feed_profile_genre,card_movie_feed_profile_storyline;
        ImageView card_movie_feed_profile_image;

        public ViewHolder1(View itemView) {
            super(itemView);
            card_movie_feed_profile_moviename = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_moviename);
            card_movie_feed_profile_censor = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_censor);
            card_movie_feed_profile_image = (ImageView) itemView.findViewById(R.id.card_movie_feed_profile_image);
            card_movie_feed_profile_dor = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_dor);
            card_movie_feed_profile_dur = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_dur);
            card_movie_feed_profile_genre = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_genre);
            card_movie_feed_profile_storyline = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_storyline);
        }
    }*/

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_celebrity_feed_gallery1_img;
        TextView tv_name, tv_description,card_celebrity_feed_gallery1_title;

        public ViewHolder2(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_img=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_gallery1_img);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_description=(TextView)itemView.findViewById(R.id.tv_description);
            card_celebrity_feed_gallery1_title=(TextView)itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new GalleryCardClick())
                    .addToBackStack("")
                    .commit();
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_celebrity_feed_gallery1_img;
        TextView tv_name, tv_description,card_celebrity_feed_gallery1_title;

        public ViewHolder3(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_img=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_gallery1_img);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_description=(TextView)itemView.findViewById(R.id.tv_description);
            card_celebrity_feed_gallery1_title=(TextView)itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new NewsOnClickFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder {
        ImageView card_celebrity_feed_gallery1_img;
        TextView tv_name, tv_description,card_celebrity_feed_gallery1_title;

        public ViewHolder4(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_img=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_gallery1_img);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_description=(TextView)itemView.findViewById(R.id.tv_description);
            card_celebrity_feed_gallery1_title=(TextView)itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_celebrity_feed_gallery1_img;
        TextView tv_name, tv_description,card_celebrity_feed_gallery1_title;

        public ViewHolder5(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_img=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_gallery1_img);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_description=(TextView)itemView.findViewById(R.id.tv_description);
            card_celebrity_feed_gallery1_title=(TextView)itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new VideoGalleryFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder {
        ImageView card_celebrity_feed_gallery1_img;
        TextView tv_name, tv_description,card_celebrity_feed_gallery1_title;
        public ViewHolder6(View itemView) {
            super(itemView);
            card_celebrity_feed_gallery1_img=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_gallery1_img);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_description=(TextView)itemView.findViewById(R.id.tv_description);
            card_celebrity_feed_gallery1_title=(TextView)itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
        }
    }

    public class ViewHolder7 extends RecyclerView.ViewHolder {
        ImageView card_celebrity_feed_gallery1_img;
        TextView tv_name, tv_description,card_celebrity_feed_gallery1_title;
        ImageButton video_btn;

        public ViewHolder7(View itemView) {
            super(itemView);
            /*card_steal_style_carousel_title = (TextView) itemView.findViewById(R.id.card_steal_style_carousel_title);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);*/
            card_celebrity_feed_gallery1_img=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_gallery1_img);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_description=(TextView)itemView.findViewById(R.id.tv_description);
            card_celebrity_feed_gallery1_title=(TextView)itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
        }
    }
}
