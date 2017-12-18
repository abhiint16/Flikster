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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterFamilyViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterFilmographyViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterPeersViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterVideoViewHolder;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieData;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    List<String> socialInterviewVideo=new ArrayList<>();
    List<String> trailerPromoImg=new ArrayList<>();
    List<String> trailerPromoTitle=new ArrayList<>();
    List<String> trailerPromoVideo=new ArrayList<>();
    List<String> comedyImg=new ArrayList<>();
    List<String> comedyTitle=new ArrayList<>();
    List<String> comedyVideo=new ArrayList<>();
    List<String> musicImg=new ArrayList<>();
    List<String> musicTitle=new ArrayList<>();
    List<String> musicAudio=new ArrayList<>();
    List<String> tvShowsImg=new ArrayList<>();
    List<String> tvShowsTitle=new ArrayList<>();
    List<ArrayList<String>> tvShowsVideo=new ArrayList<ArrayList<String>>();
    List<String> moviesImg=new ArrayList<>();
    List<String> moviesTitle=new ArrayList<>();
    List<String> moviesSlug=new ArrayList<>();
    FeedInnerData outerHits;
    Integer Count;
    ApiInterface apiInterface;
    FeedCelebrityRecyclerItemAdapter feedCelebrityRecyclerItemAdapter;
    WatchFragment.WatchFragCommInterface watchFragCommInterface;
    List<String> carouselImgWatch=new ArrayList<>();
    MovieData.MovieInnerData moviehits;
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

    ImageListener imageListeners = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(context).load(carouselImgWatch.get(position).trim()).into(imageView);
        }
    };
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
            //final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            carouselImgWatch.clear();
            Boolean audio=false,tv=false,social=false,movie=false,trailer=false,comedy=false;
            for (int i=0;i<outerHits.getHits().size();i++)
            {
                if ("audio-song".equals(outerHits.getHits().get(i).get_source().getContentType())&&audio==false)
                {
                    carouselImgWatch.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    audio=true;
                }
                if ("gallery".equals(outerHits.getHits().get(i).get_source().getContentType())&&tv==false)
                {
                    carouselImgWatch.add(outerHits.getHits().get(i).get_source().getMedia().getGallery().get(0));
                    tv=true;
                }
                if (("social-buzz".equals(outerHits.getHits().get(i).get_source().getContentType())||
                        "interview".equals(outerHits.getHits().get(i).get_source().getContentType()))&&social==false)
                {
                    carouselImgWatch.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    social=true;
                }
                if ("movies".equals(outerHits.getHits().get(i).get_source().getContentType())&&movie==false)
                {
                    carouselImgWatch.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    movie=true;
                }
                if (("trailer".equals(outerHits.getHits().get(i).get_source().getContentType())||
                        "prome".equals(outerHits.getHits().get(i).get_source().getContentType()))&&trailer==false)
                {
                    carouselImgWatch.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    trailer=true;
                }
                if ("comedy-clip".equals(outerHits.getHits().get(i).get_source().getContentType())&&comedy==false)
                {
                    carouselImgWatch.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    comedy=true;
                }
            }
            ((ViewHolder1) holder).carouselView.setPageCount(carouselImgWatch.size());
            ((ViewHolder1) holder).carouselView.setImageListener(imageListeners);
        } else if (holder.getItemViewType() == 2) {
            for (int i=0;i<outerHits.getHits().size();i++)
            {
                Log.e("check count",""+Count);
                if("audio-song".equals(outerHits.getHits().get(i).get_source().getContentType())||"dialouge".equals(outerHits.getHits().get(i).get_source().getContentType()))
                {
                    Log.e("chck music size",""+musicImg.size());
                    musicImg.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    musicTitle.add(outerHits.getHits().get(i).get_source().getTitle());
                    if(outerHits.getHits().get(i).get_source().getMedia().getAudio()!=null&&outerHits.getHits().get(i).get_source().getMedia().getAudio().size()!=0)
                        musicAudio.add(outerHits.getHits().get(i).get_source().getMedia().getAudio().get(0));
                }
            }
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setText("Music");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            musicAdapterViewHolder = new MusicAdapterViewHolder(context,musicTitle,musicImg,musicAudio,fragmentManager,watchFragCommInterface);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(musicAdapterViewHolder);
        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_title.setText("Gallery");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
//            tvShowsViewHolder = new TvShowsViewHolder(context);
            for (int i=0;i<outerHits.getHits().size();i++)
            {
                Log.e("cehck gallery",""+tvShowsVideo);
                if("gallery".equals(outerHits.getHits().get(i).get_source().getContentType()))
                {
                    tvShowsImg.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    tvShowsTitle.add(outerHits.getHits().get(i).get_source().getTitle());
                    if(outerHits.getHits().get(i).get_source().getMedia().getGallery()!=null&&outerHits.getHits().get(i).get_source().getMedia().getGallery().size()!=0)
                        Log.e("check pos",""+outerHits.getHits().size()+"nalsl"+i);
                        Log.e("check gallery inner",""+outerHits.getHits().get(i).get_source().getMedia().getGallery());
                        tvShowsVideo.add((ArrayList<String>) outerHits.getHits().get(i).get_source().getMedia().getGallery());
                        //socialInterviewVideo.add(outerHits.getHits().get(i).get_source().getMedia().getVideo().get(0));
                }
            }
            tvShowsViewHolder = new TvShowsViewHolder(context,fragmentManager,tvShowsImg,tvShowsTitle,tvShowsVideo,watchFragCommInterface,
                    outerHits);
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(tvShowsViewHolder);
        } else if (holder.getItemViewType() == 4) {
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_title.setText("Social Buzz/Interviews");
            /*socialInterviewImg.clear();
            socialInterviewTitle.clear();*/
            for (int i=0;i<outerHits.getHits().size();i++)
            {
                if("social-buzz".equals(outerHits.getHits().get(i).get_source().getContentType()) || "interview".equals(outerHits.getHits().get(i).get_source().getContentType()))
                {
                    socialInterviewImg.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    socialInterviewTitle.add(outerHits.getHits().get(i).get_source().getTitle());
                    if(outerHits.getHits().get(i).get_source().getMedia().getVideo()!=null&&outerHits.getHits().get(i).get_source().getMedia().getVideo().size()!=0)
                        socialInterviewVideo.add(outerHits.getHits().get(i).get_source().getMedia().getVideo().get(0));
                }
            }
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            msocialBuzzOrInterViewsViewHolder = new SocialBuzzOrInterViewsViewHolder(context, socialInterviewTitle, socialInterviewImg,socialInterviewVideo,fragmentManager,watchFragCommInterface);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(msocialBuzzOrInterViewsViewHolder);

        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_title.setText("Movies");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            initMovieRetrofit(((ViewHolder5) holder).fragment_common_recyclerview_with_tv_recycler);
        } else if (holder.getItemViewType() == 6) {
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_title.setText("Trailers & Promos");
            trailerPromoImg.clear();
            trailerPromoTitle.clear();
            for (int i=0;i<outerHits.getHits().size();i++)
            {
                if("trailer".equals(outerHits.getHits().get(i).get_source().getContentType()) || "promo".equals(outerHits.getHits().get(i).get_source().getContentType()))
                {
                    trailerPromoImg.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    trailerPromoTitle.add(outerHits.getHits().get(i).get_source().getTitle());
                    if(outerHits.getHits().get(i).get_source().getMedia().getVideo()!=null&&outerHits.getHits().get(i).get_source().getMedia().getVideo().size()!=0)
                        trailerPromoVideo.add(outerHits.getHits().get(i).get_source().getMedia().getVideo().get(0));
                }
            }
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            trailersViewHolder = new TrailersViewHolder(context, trailerPromoTitle,trailerPromoImg,trailerPromoVideo,fragmentManager,watchFragCommInterface);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(trailersViewHolder);
        } else if (holder.getItemViewType() == 7) {
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_title.setText("Comedy");
            comedyImg.clear();
            comedyTitle.clear();
            for (int i=0;i<outerHits.getHits().size();i++)
            {
                if("comedy-clip".equals(outerHits.getHits().get(i).get_source().getContentType()))
                {
                    comedyImg.add(outerHits.getHits().get(i).get_source().getProfilePic());
                    comedyTitle.add(outerHits.getHits().get(i).get_source().getTitle());
                    if(outerHits.getHits().get(i).get_source().getMedia().getVideo()!=null&&outerHits.getHits().get(i).get_source().getMedia().getVideo().size()!=0)
                        comedyVideo.add(outerHits.getHits().get(i).get_source().getMedia().getVideo().get(0));
                }
            }
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            comedyViewHolder = new ComedyViewHolder(context, comedyTitle,comedyImg,comedyVideo,fragmentManager,watchFragCommInterface);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(comedyViewHolder);
        } else {
        }
    }

    private void initMovieRetrofit(final RecyclerView recyclerView) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/movies/").create(ApiInterface.class);
        Call<MovieData> call = apiInterface.getMovieData("http://apiservice-ec.flikster.com/movies/_search?size=4&sort=createdAt:desc");
        call.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                moviehits = response.body().getHits();
                for (int i=0;i<moviehits.getHits().size();i++)
                {
                    moviesImg.add(moviehits.getHits().get(i).get_source().getCoverPic());
                    moviesTitle.add(moviehits.getHits().get(i).get_source().getTitle());
                    moviesSlug.add(moviehits.getHits().get(i).get_source().getSlug());
                }
                moviesViewHolder = new MoviesViewHolder(context,fragmentManager,moviesImg,moviesTitle,moviesSlug,watchFragCommInterface);
                recyclerView.setAdapter(moviesViewHolder);
                }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Log.e("xxx", "xxx" + call + t);
            }
        });
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

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;
        CarouselView carouselView;
        View imgview;


        public ViewHolder1(View itemView) {
            super(itemView);
//            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
//            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            carouselView = (CarouselView) itemView.findViewById(R.id.carouselView);
            carouselView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.carouselView);
            {
                Toast.makeText(context,""+carouselView.getCurrentItem(),Toast.LENGTH_LONG).show();
            }
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
            innerTitleClick(v,"Music",musicImg,musicTitle,musicAudio,new MusicGridFragment());
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
            //innerTitleClick(v,"TV Shows",tvShowsImg,tvShowsTitle,tvShowsVideo,new MusicGridFragment());
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
            innerTitleClick(v,"Social Buzz/Interviews",socialInterviewImg,socialInterviewTitle,socialInterviewVideo,new MusicGridFragment());
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
            //innerTitleClick(v,"Movies",moviesImg,moviesTitle,moviesVideo,new MusicGridFragment());
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
            innerTitleClick(v,"Trailer & Promos",trailerPromoImg,trailerPromoTitle,trailerPromoVideo,new MusicGridFragment());
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
            innerTitleClick(v,"Comedy",comedyImg,comedyTitle,comedyVideo,new MusicGridFragment());
        }
    }

    private void innerTitleClick(View view,String toolbarTitle,List<String> img,List<String> title,List<String> audioVideoLink,Fragment fragment) {
        watchFragCommInterface.carouselContainerClick(toolbarTitle,img,title,audioVideoLink,fragment);
        }
}

