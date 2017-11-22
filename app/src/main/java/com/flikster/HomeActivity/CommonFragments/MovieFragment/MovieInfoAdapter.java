package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterFamilyViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterFilmographyViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterPeersViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterVideoViewHolder;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MovieInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    CelebrityBioAdapterVideoViewHolder celebrityBioAdapterVideoViewHolder;
    CelebrityBioAdapterFamilyViewHolder celebrityBioAdapterFamilyViewHolder;
    MovieInfoAdapterCrewViewHolder movieInfoAdapterCrewViewHolder;
    CelebrityBioAdapterPeersViewHolder celebrityBioAdapterPeersViewHolder;
    MovieInfoAdapterCastViewHolder movieInfoAdapterCastViewHolder;
    List<MovieData.MovieInnerData> items;
    Boolean storyLineBoolean=true;
    String censor;
    String coverpic;
    String dor;
    String duration;
    String title;
    ArrayList<String> genre = new ArrayList<>();

    public MovieInfoAdapter(Context context, FragmentManager fragmentManager,String coverpic, String censor,
                            String dor, ArrayList<String> genre,String duration,String title) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.items=items;
        type.add(1);type.add(2);type.add(3);
        type.add(4);type.add(5);type.add(6);
        type.add(7);type.add(8);type.add(9);
        type.add(3);type.add(4);type.add(3);
        type.add(4);type.add(7);
        this.genre = genre;
        this.coverpic = coverpic;
        this.title = title;
        this.dor = dor;
        this.censor=censor;
        this.duration=duration;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_feed_profile, parent, false);
            return new MovieInfoAdapter.ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new MovieInfoAdapter.ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new MovieInfoAdapter.ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new MovieInfoAdapter.ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_news, parent, false);
            return new MovieInfoAdapter.ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new MovieInfoAdapter.ViewHolder6(view);
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_review, parent, false);
            return new MovieInfoAdapter.ViewHolder7(view);
        } else if (viewType == 8) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_user_movie_review, parent, false);
            return new MovieInfoAdapter.ViewHolder8(view);
        } else if (viewType == 9) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new MovieInfoAdapter.ViewHolder9(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_news, parent, false);
            return new MovieInfoAdapter.ViewHolder9(view);
        }
        /*else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_news, parent, false);
            return new MovieInfoAdapter.ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_filmography, parent, false);
            return new MovieInfoAdapter.ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_peers, parent, false);
            return new MovieInfoAdapter.ViewHolder6(view);
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_family, parent, false);
            return new MovieInfoAdapter.ViewHolder7(view);
        } else if (viewType == 8) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_awards, parent, false);
            return new MovieInfoAdapter.ViewHolder8(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_upcoming, parent, false);
            return new MovieInfoAdapter.ViewHolder9(view);
        }*/

    }

    public  String formatDate(String dateOfRelease)
    {
        String subString=dateOfRelease.substring(3,dateOfRelease.indexOf("GMT")-9);
        return subString;
    }
    /*public String formatGenre()
    {
        String genre="";
        for(int i=0;i<items.get(0).genre.size();i++)
        {
            if(i<items.get(0).genre.size()-1)
                genre=genre+items.get(0).getGenre().get(i)+" | ";
            else
                genre=genre+items.get(0).getGenre().get(i);
        }
        return genre;
    }

    public Spannable formatStoryLine()
    {
        String storyLine=items.get(0).getStoryLine();
        Spannable spannable = null;
        if(storyLine.length()>200)
        {
            storyLine=storyLine.substring(0,190)+" ...Click To Expand";
            spannable = new SpannableString(storyLine);
            spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorAccent)), 190
                    , storyLine.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        return spannable;
    }
    public void storyLineOnClick(ViewHolder1 holder)
    {
        String storyLine=items.get(0).getStoryLine();
        if(storyLine.length()>200&&storyLineBoolean==true)
        {
            holder.card_movie_info_profile_storyline.setText(items.get(0).getStoryLine());
            storyLineBoolean=false;
        }
        else if(storyLine.length()>200&&storyLineBoolean==false)
        {
            holder.card_movie_info_profile_storyline.setText(formatStoryLine());
            storyLineBoolean=true;
        }
    }*/

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
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
        }
        /*else if (holder.getItemViewType() == 2) {
//            ((ViewHolder2) holder).textView.setText("Videos");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterVideoViewHolder = new CelebrityBioAdapterVideoViewHolder(fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterVideoViewHolder);
        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3)holder).fragment_common_recyclerview_with_tv_title.setText("Cast");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            movieInfoAdapterCastViewHolder = new MovieInfoAdapterCastViewHolder(context,items.get(0).getCast().size(),items);
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(movieInfoAdapterCastViewHolder);
        } else if (holder.getItemViewType() == 4) {
            ((ViewHolder4)holder).fragment_common_recyclerview_with_tv_title.setText("Crew");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            movieInfoAdapterCrewViewHolder = new MovieInfoAdapterCrewViewHolder(context,items.get(0).getCrew().size(),items);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(movieInfoAdapterCrewViewHolder);
        } else if (holder.getItemViewType() == 6) {
            //((ViewHolder6)holder).textView.setText("videos");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterPeersViewHolder = new CelebrityBioAdapterPeersViewHolder();
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterPeersViewHolder);
        } else if (holder.getItemViewType() == 9) {
//            ((ViewHolder3) holder).textView.setText("Cast");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder9) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            movieInfoAdapterCastViewHolder = new MovieInfoAdapterCastViewHolder(context,items.get(0).getCast().size(),items);
            ((ViewHolder9) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(movieInfoAdapterCastViewHolder);
        }*/
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView card_movie_feed_profile_moviename, card_movie_feed_profile_censor,card_movie_feed_profile_dor,
                card_movie_feed_profile_dur,card_movie_feed_profile_genre;
        ImageView card_movie_feed_profile_image;

        public ViewHolder1(View itemView) {
            super(itemView);
            card_movie_feed_profile_moviename = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_moviename);
            card_movie_feed_profile_censor = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_censor);
            card_movie_feed_profile_image = (ImageView) itemView.findViewById(R.id.card_movie_feed_profile_image);
            card_movie_feed_profile_dor = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_dor);
            card_movie_feed_profile_dur = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_dur);
            card_movie_feed_profile_genre = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_genre);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder2(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            fragment_common_recyclerview_with_tv_title.setText("Videos");
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder3(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder4(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder {
        public ViewHolder5(View itemView) {
            super(itemView);
        }
    }

    /*public class ViewHolder5 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        public ViewHolder5(View itemView) {
            super(itemView);
            //textView=(TextView)itemView.findViewById(R.id.txt);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.card_celebrity_bio_filmography_recycler);
        }
    }*/

    public class ViewHolder6 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder6(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            fragment_common_recyclerview_with_tv_title.setText("Images");
        }
    }

    public class ViewHolder7 extends RecyclerView.ViewHolder {
        public ViewHolder7(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder8 extends RecyclerView.ViewHolder {
        public ViewHolder8(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder9 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder9(View itemView) {

            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            fragment_common_recyclerview_with_tv_title.setText("Recommended Movies");
        }
    }

}
