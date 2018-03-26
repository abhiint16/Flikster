package com.flikster.HomeActivity.WatchFragment.Movies;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryFullScreen;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.SongByMovieFragmentItemClick;
import com.flikster.HomeActivity.WatchFragment.WatchFragment;
import com.flikster.R;
import com.flikster.VideoFullScreenActivity.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 13-10-2017.
 */

public class MoviesViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    WatchFragment.WatchFragCommInterface watchFragCommInterface;
    MovieData.MovieInnerData movieInnerData;
    ApiInterface apiInterface;
    int count=5;
    public MoviesViewHolder(Context context, FragmentManager fragmentManager, MovieData.MovieInnerData movieInnerData, WatchFragment.WatchFragCommInterface watchFragCommInterface) {
        this.context=context;
        this.fragmentManager = fragmentManager;
        this.movieInnerData=movieInnerData;
        this.watchFragCommInterface=watchFragCommInterface;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_not_available_layout,parent,false);
            return new ViewHolder1(view);
        }
        else if (viewType==2)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hor_last_item_load_more, parent, false);
            return new ViewHolder3(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_images_recycler_item,parent,false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==0)
        {

        }
        else if (holder.getItemViewType()==2)
        {
            loadMore();
        }
        else
        {
            if (movieInnerData.getHits().get(position).get_source().getCoverPic()==null||
                    movieInnerData.getHits().get(position).get_source().getCoverPic().isEmpty())
            {
                Glide.with(context).load(movieInnerData.getHits().get(position).get_source().getProfilePic()).into(((ViewHolder2)holder).carousel_image);
            }
            Glide.with(context).load(movieInnerData.getHits().get(position).get_source().getCoverPic()).into(((ViewHolder2)holder).carousel_image);
            ((ViewHolder2)holder).carousel_title.setText(movieInnerData.getHits().get(position).get_source().getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if(movieInnerData.getHits().size()==0)
            return 1;
        else{
            if (movieInnerData.getTotal()==movieInnerData.getHits().size())
                return movieInnerData.getHits().size();
            return movieInnerData.getHits().size()+1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(movieInnerData.getHits().size()==0)
            return 0;
        else{
            if (position!=movieInnerData.getHits().size())
            return 1;
            else return 2;
        }
    }


    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView carousel_image;
        TextView carousel_title;
        public ViewHolder2(View itemView) {
            super(itemView);
            carousel_image=(ImageView)itemView.findViewById(R.id.carousel_image);
            carousel_title=(TextView)itemView.findViewById(R.id.carousel_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
              watchFragCommInterface.carouselItemToMovie(movieInnerData.getHits().get(getAdapterPosition()).get_source().getSlug(),new MovieFragment());
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView hor_last_item_load_more_txt;
        public ViewHolder3(View itemView) {
            super(itemView);
            //hor_last_item_load_more_txt=(TextView)itemView.findViewById(R.id.hor_last_item_load_more_txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }

    public void loadMore()
    {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/movies/").create(ApiInterface.class);
        Call<MovieData> call = apiInterface.getMovieData("http://apiservice-ec.flikster.com/movies/_search?size=5&from="+count+"&sort=createdAt:desc");
        call.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                count=count+5;
                movieInnerData.getHits().addAll(response.body().getHits().getHits());
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Log.e("xxx", "xxx" + call + t);
            }
        });
    }
}
