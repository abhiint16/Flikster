package com.flikster.HomeActivity.WatchFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterFamilyViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterFilmographyViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterPeersViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterVideoViewHolder;
import com.flikster.HomeActivity.FeedFragment.FeedCelebrityRecyclerItemAdapter;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.WatchFragment.Comedy.ComedyViewHolder;
import com.flikster.HomeActivity.WatchFragment.LatestMovieTrailers.WatchTrailerViewHolder;
import com.flikster.HomeActivity.WatchFragment.Movies.MoviesViewHolder;
import com.flikster.HomeActivity.WatchFragment.Music.MusicAdapterViewHolder;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.HomeActivity.WatchFragment.SocialBuzzOrInterview.SocialBuzzOrInterViewsViewHolder;
import com.flikster.HomeActivity.WatchFragment.TrailerOrPromos.TrailersViewHolder;
import com.flikster.HomeActivity.WatchFragment.TvShows.TvShowsViewHolder;
import com.flikster.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-10-2017.
 */

public class WatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    List<String> imag = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    MusicAdapterViewHolder musicAdapterViewHolder;
    SocialBuzzOrInterViewsViewHolder msocialBuzzOrInterViewsViewHolder;
    TrailersViewHolder trailersViewHolder;
    TvShowsViewHolder tvShowsViewHolder;
    MoviesViewHolder moviesViewHolder;
    ComedyViewHolder comedyViewHolder;
    List<String> socialInterviewImg=new ArrayList<>();
    List<String> socialInterviewTitle=new ArrayList<>();
    List<String> trailerPromoImg=new ArrayList<>();
    List<String> trailerPromoTitle=new ArrayList<>();
    List<String> comedyImg=new ArrayList<>();
    List<String> comedyTitle=new ArrayList<>();
    List<String> musicImg=new ArrayList<>();
    List<String> musicTitle=new ArrayList<>();
    List<String> tvShowsImg=new ArrayList<>();
    List<String> tvShowsTitle=new ArrayList<>();
    List<String> moviesImg=new ArrayList<>();
    List<String> moviesTitle=new ArrayList<>();
    FeedInnerData outerHits;
    Integer Count;
    FeedCelebrityRecyclerItemAdapter feedCelebrityRecyclerItemAdapter;
    WatchFragment.WatchFragCommInterface watchFragCommInterface;

    int[] sampleImages = {R.drawable.rakulpreetred, R.drawable.prabha, R.drawable.rakulpreetred, R.drawable.prabha, R.drawable.rakulpreetred};

    public WatchAdapter(Context context, FragmentManager fragmentManager, FeedInnerData outerHits,Integer Count, WatchFragment.WatchFragCommInterface watchFragCommInterface) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        type.add(5);
        type.add(6);
        type.add(7);
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");
        imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");
        imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");

        this.outerHits = outerHits;
        this.Count = Count;
        this.watchFragCommInterface = watchFragCommInterface;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carouselview, parent, false);
            return new WatchAdapter.ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
//            if
            return new WatchAdapter.ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder6(view);
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder7(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder5(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {

            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            ((ViewHolder1) holder).fragment_common_recyclerview_with_tv_title.setVisibility(View.GONE);
//            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//            ((ViewHolder1) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
//            watchTrailerViewHolder = new WatchTrailerViewHolder();
//            ((ViewHolder1) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(watchTrailerViewHolder);
            ImageListener imageListeners;
            imageListeners = new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    imageView.setImageResource(sampleImages[position]);
                }
            };
            //
            ((ViewHolder1) holder).carouselView.setImageListener(imageListeners);
        } else if (holder.getItemViewType() == 2) {
            for (int i=0;i<Count;i++)
            {
                if("audio-song".equals(outerHits.getHits().get(i).get_source().getContentType()))
                {
                    musicImg.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    musicTitle.add(outerHits.getHits().get(i).get_source().getTitle());
                }
            }
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setText("Music");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            musicAdapterViewHolder = new MusicAdapterViewHolder(context,musicTitle,musicImg,fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(musicAdapterViewHolder);
        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_title.setText("Tv Shows");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
//            tvShowsViewHolder = new TvShowsViewHolder(context);
            tvShowsViewHolder = new TvShowsViewHolder(fragmentManager);
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(tvShowsViewHolder);
        } else if (holder.getItemViewType() == 4) {
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_title.setText("Social Buzz/Interviews");
            /*socialInterviewImg.clear();
            socialInterviewTitle.clear();*/
            for (int i=0;i<Count;i++)
            {
                if("social-buzz".equals(outerHits.getHits().get(i).get_source().getContentType()) || "interview".equals(outerHits.getHits().get(i).get_source().getContentType()))
                {
                    socialInterviewImg.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    socialInterviewTitle.add(outerHits.getHits().get(i).get_source().getTitle());
                }
            }
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            msocialBuzzOrInterViewsViewHolder = new SocialBuzzOrInterViewsViewHolder(context, socialInterviewTitle, socialInterviewImg,fragmentManager);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(msocialBuzzOrInterViewsViewHolder);

        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_title.setText("Movies");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            moviesViewHolder = new MoviesViewHolder(context,fragmentManager);
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(moviesViewHolder);
        } else if (holder.getItemViewType() == 6) {
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_title.setText("Trailers & Promos");
            trailerPromoImg.clear();
            trailerPromoTitle.clear();
            for (int i=0;i<Count;i++)
            {
                if("trailer".equals(outerHits.getHits().get(i).get_source().getContentType()) || "promo".equals(outerHits.getHits().get(i).get_source().getContentType()))
                {
                    trailerPromoImg.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    trailerPromoTitle.add(outerHits.getHits().get(i).get_source().getTitle());
                }
            }
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            trailersViewHolder = new TrailersViewHolder(context, trailerPromoImg, trailerPromoTitle,fragmentManager);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(trailersViewHolder);
        } else if (holder.getItemViewType() == 7) {
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_title.setText("Comedy");
            comedyImg.clear();
            comedyTitle.clear();
            for (int i=0;i<Count;i++)
            {
                if("comedy-clip".equals(outerHits.getHits().get(i).get_source().getContentType()))
                {
                    comedyImg.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    comedyTitle.add(outerHits.getHits().get(i).get_source().getTitle());
                }
            }
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            comedyViewHolder = new ComedyViewHolder(context, comedyImg, comedyTitle,fragmentManager);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(comedyViewHolder);
        } else {
        }
    }

    @Override
    public int getItemCount() {
        Log.e("print hits",""+outerHits.getHits().size());
        return 7;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;
        CarouselView carouselView;
        View imgview;


        public ViewHolder1(View itemView) {
            super(itemView);
//            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
//            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            carouselView = (CarouselView) itemView.findViewById(R.id.carouselView);
            carouselView.setPageCount(sampleImages.length);

        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView fragment_common_recyclerview_with_tv_title;
        public RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder2(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            fragment_common_recyclerview_with_tv_title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            innerTitleClick(v,"Music",musicImg,musicTitle,new MusicGridFragment());
        }
        //508001
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder3(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            fragment_common_recyclerview_with_tv_title.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            innerTitleClick(v,"TV Shows",tvShowsImg,tvShowsTitle,new MusicGridFragment());
        }
    }


    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder4(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            fragment_common_recyclerview_with_tv_title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            innerTitleClick(v,"Social Buzz/Interviews",socialInterviewImg,socialInterviewTitle,new MusicGridFragment());
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder5(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            fragment_common_recyclerview_with_tv_title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            innerTitleClick(v,"Movies",moviesImg,moviesTitle,new MusicGridFragment());
        }
    }


    public class ViewHolder6 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder6(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            fragment_common_recyclerview_with_tv_title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            innerTitleClick(v,"Trailer & Promos",trailerPromoImg,trailerPromoTitle,new MusicGridFragment());
        }
    }

    public class ViewHolder7 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder7(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            fragment_common_recyclerview_with_tv_title.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            innerTitleClick(v,"Comedy",comedyImg,comedyTitle,new MusicGridFragment());
        }
    }

    private void innerTitleClick(View view,String toolbarTitle,List<String> img,List<String> title,Fragment fragment) {
        watchFragCommInterface.carouselContainerClick(toolbarTitle,img,title,fragment);
        }
}

