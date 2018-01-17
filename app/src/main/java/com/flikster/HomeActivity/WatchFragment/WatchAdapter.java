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
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieData;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedFragment.FeedCelebrityRecyclerItemAdapter;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.WatchFragment.Comedy.ComedyViewHolder;
import com.flikster.HomeActivity.WatchFragment.Movies.MoviesViewHolder;
import com.flikster.HomeActivity.WatchFragment.Music.MusicAdapterViewHolder;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridFragment;
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
    LinearLayoutManager layoutManager;
    MusicAdapterViewHolder musicAdapterViewHolder;
    SocialBuzzOrInterViewsViewHolder msocialBuzzOrInterViewsViewHolder;
    TrailersViewHolder trailersViewHolder;
    TvShowsViewHolder tvShowsViewHolder;
    MoviesViewHolder moviesViewHolder;
    ComedyViewHolder comedyViewHolder;
    int i=0;
    /*List<String> socialInterviewImg=new ArrayList<>();
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
    */
    List<String> moviesImg = new ArrayList<>();
    List<String> moviesTitle = new ArrayList<>();
    List<String> moviesSlug = new ArrayList<>();
    FeedInnerData outerHits, outerHitsMusic, outerHitsGallery, outerHitsSocialBuzz, outerHitsTrailer, outerHitsComedy;
    ApiInterface apiInterface;
    WatchFragment.WatchFragCommInterface watchFragCommInterface;
    List<String> carouselImgWatch = new ArrayList<>();
    MovieData.MovieInnerData moviehits;
    List<String> allUrls = new ArrayList<>();

    public WatchAdapter(Context context, FragmentManager fragmentManager, FeedInnerData outerHits, Integer Count, WatchFragment.WatchFragCommInterface watchFragCommInterface) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        type.add(5);
        type.add(6);
        type.add(7);
        this.outerHits = outerHits;
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
            carouselImgWatch.clear();
            allUrls.clear();
            i=0;
            allUrls.add("http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=1&from=0&pretty=true&q=contentType:%22audio-song%22%20OR%20contentType:%22dialouge%22");
            allUrls.add("http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=1&from=0&pretty=true&q=contentType:%22gallery%22");
            allUrls.add("http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=1&from=0&pretty=true&q=contentType:%22social-buzz%22%20OR%20contentType:%22interview%22");
            allUrls.add("http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=1&from=0&pretty=true&q=contentType:%22trailer%22%20OR%20contentType:%22promo%22");
            allUrls.add("http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=1&from=0&pretty=true&q=contentType:%22comedy-clip%22");
            Log.e("check position 0",""+allUrls.size()+"and"+i);
            hitRetrofitForCarousel(allUrls.get(0),((ViewHolder1) holder).carouselView);
        } else if (holder.getItemViewType() == 2) {
            fetchRetrofitForMusic(((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler, "http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=5&from=0&pretty=true&q=contentType:%22audio-song%22%20OR%20contentType:%22dialouge%22");
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setText("Music");
        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_title.setText("Gallery");
            fetchRetrofitForGallery(((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler, "http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=5&from=0&pretty=true&q=contentType:%22gallery%22");
        } else if (holder.getItemViewType() == 4) {
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_title.setText("Social Buzz/Interviews");
            fetchRetrofitForSocialBuzzAndInterview(((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler, "http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=5&from=0&pretty=true&q=contentType:%22social-buzz%22%20OR%20contentType:%22interview%22");
        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_title.setText("Movies");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            initMovieRetrofit(((ViewHolder5) holder).fragment_common_recyclerview_with_tv_recycler);
        } else if (holder.getItemViewType() == 6) {
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_title.setText("Trailers & Promos");
            fetchRetrofitForTrailerAndPromo(((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler, "http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=5&from=0&pretty=true&q=contentType:%22trailer%22%20OR%20contentType:%22promo%22");
        } else if (holder.getItemViewType() == 7) {
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_title.setText("Comedy");
            fetchRetrofitForComedy(((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler, "http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=5&from=0&pretty=true&q=contentType:%22comedy-clip%22");
        } else {
        }
    }

    private void initMovieRetrofit(final RecyclerView recyclerView) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/movies/").create(ApiInterface.class);
        Call<MovieData> call = apiInterface.getMovieData("http://apiservice-ec.flikster.com/movies/_search?size=5&from=0&sort=createdAt:desc");
        call.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                moviehits = response.body().getHits();
                moviesViewHolder = new MoviesViewHolder(context, fragmentManager,moviehits, watchFragCommInterface);
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
        Log.e("print hits", "" + outerHits.getHits().size());
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
            if (view.getId() == R.id.carouselView) ;
            {
                Toast.makeText(context, "" + carouselView.getCurrentItem(), Toast.LENGTH_LONG).show();
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
            innerTitleClick(v, "Music",
                    "http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=5&from=0&pretty=true&q=contentType:%22audio-song%22%20OR%20contentType:%22dialouge%22",
                    new MusicGridFragment());
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
            innerTitleClick(v, "Social Buzz/Interviews",
                    "http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=5&from=0&pretty=true&q=contentType:%22social-buzz%22%20OR%20contentType:%22interview%22",
                    new MusicGridFragment());
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
            innerTitleClick(v, "Trailer & Promos",
                    "http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=5&from=0&pretty=true&q=contentType:%22trailer%22%20OR%20contentType:%22promo%22",
                    new MusicGridFragment());
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
            innerTitleClick(v, "Comedy",
                    "http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=5&from=0&pretty=true&q=contentType:%22comedy-clip%22",
                    new MusicGridFragment());
        }
    }

    private void innerTitleClick(View view, String toolbarTitle, String url, Fragment fragment) {
        watchFragCommInterface.carouselContainerClick(toolbarTitle, url, fragment);
    }

    public void fetchRetrofitForMusic(final RecyclerView recyclerView, String url) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies(url);
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                outerHitsMusic = response.body().getHits();
                layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                musicAdapterViewHolder = new MusicAdapterViewHolder(context, response.body().getHits(), fragmentManager, layoutManager,watchFragCommInterface);
                recyclerView.setAdapter(musicAdapterViewHolder);
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    public void fetchRetrofitForGallery(final RecyclerView recyclerView, String url) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies(url);
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                outerHitsGallery = response.body().getHits();
                layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                tvShowsViewHolder = new TvShowsViewHolder(context, fragmentManager, response.body().getHits(), layoutManager,watchFragCommInterface);
                recyclerView.setAdapter(tvShowsViewHolder);
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    public void fetchRetrofitForSocialBuzzAndInterview(final RecyclerView recyclerView, String url) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies(url);
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                outerHitsSocialBuzz = response.body().getHits();
                layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                msocialBuzzOrInterViewsViewHolder = new SocialBuzzOrInterViewsViewHolder(context, response.body().getHits(), fragmentManager,layoutManager, watchFragCommInterface);
                recyclerView.setAdapter(msocialBuzzOrInterViewsViewHolder);
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    public void fetchRetrofitForTrailerAndPromo(final RecyclerView recyclerView, String url) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies(url);
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                outerHitsTrailer = response.body().getHits();
                layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                trailersViewHolder = new TrailersViewHolder(context, response.body().getHits(), fragmentManager,layoutManager, watchFragCommInterface);
                recyclerView.setAdapter(trailersViewHolder);
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    public void fetchRetrofitForComedy(final RecyclerView recyclerView, String url) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies(url);
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                outerHitsComedy = response.body().getHits();
                layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                comedyViewHolder = new ComedyViewHolder(context, response.body().getHits(), fragmentManager,layoutManager, watchFragCommInterface);
                recyclerView.setAdapter(comedyViewHolder);
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    public void hitRetrofitForCarousel(String url, final CarouselView carouselView) {
        Log.e("inside hitRetrofit","hit retrofitforcar");
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies(url);
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                Log.e("inside onresposnes","hit retrofitforcar");
                if (response.body().getHits().getHits().get(0).get_source().getProfilePic() != null)
                {
                    carouselImgWatch.add(response.body().getHits().getHits().get(0).get_source().getProfilePic());
                    i=++i;
                }
                else if (response.body().getHits().getHits().get(0).get_source().getMedia().getGallery() != null &&
                        response.body().getHits().getHits().get(0).get_source().getMedia().getGallery().size() != 0)
                {
                    carouselImgWatch.add(response.body().getHits().getHits().get(0).get_source().getMedia().getGallery().get(0));
                    i=++i;
                }
                Log.e("inside hitRetrofit","hit retrofitforcar"+carouselImgWatch);
                Log.e("inside hitRetrofit","iii"+i);
                if (carouselImgWatch.size()<5)
                    hitRetrofitForCarousel(allUrls.get(i),carouselView);
                else if (carouselImgWatch.size()==5)
                {
                    Log.e("print size",""+carouselImgWatch.size());
                    carouselView.setImageListener(imageListeners);
                    carouselView.setPageCount(carouselImgWatch.size());

                }
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }
}

