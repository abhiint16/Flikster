package com.flikster.HomeActivity.WatchFragment.TrailerOrPromos;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.SongByMovieFragmentItemClick;
import com.flikster.HomeActivity.WatchFragment.WatchFragment;
import com.flikster.R;
import com.flikster.VideoFullScreenActivity.VideoPlayerActivity;
import com.rohitarya.glide.facedetection.transformation.FaceCenterCrop;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 13-10-2017.
 */

public class TrailersViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    WatchFragment.WatchFragCommInterface watchFragCommInterface;
    FeedInnerData feedInnerData;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    int count=5;
    public TrailersViewHolder(Context context,
                              FeedInnerData feedInnerData, FragmentManager fragmentManager,
                              LinearLayoutManager linearLayoutManager,
                              WatchFragment.WatchFragCommInterface watchFragCommInterface) {
        this.context=context;
        this.feedInnerData=feedInnerData;
        this.fragmentManager = fragmentManager;
        this.watchFragCommInterface=watchFragCommInterface;
        this.linearLayoutManager=linearLayoutManager;
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
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_watch_video,parent,false);
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
            if (feedInnerData.getHits().get(position).get_source().getProfilePic()!=null)
            Glide.with(context).load(feedInnerData.getHits().get(position).get_source().getProfilePic())
                    .transform(new FaceCenterCrop())
                    .into(((ViewHolder2)holder).carousel_image);
            if (feedInnerData.getHits().get(position).get_source().getTitle()!=null)
            ((ViewHolder2)holder).carousel_title.setText(feedInnerData.getHits().get(position).get_source().getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if (feedInnerData.getHits()!=null&&feedInnerData.getHits().size()!=0)
        {
            if (feedInnerData.getTotal()==feedInnerData.getHits().size())
                return feedInnerData.getHits().size();
            return feedInnerData.getHits().size()+1;
        }
        else return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (feedInnerData.getHits()!=null&&feedInnerData.getHits().size()!=0)
        {
         if (position!=feedInnerData.getHits().size())
             return 1;
            else return 2;
        }
        else return  0;
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
            watchFragCommInterface.carouselItemClick(feedInnerData.getHits().get(getAdapterPosition()).get_source().getTitle(),
                    feedInnerData.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                    feedInnerData.getHits().get(getAdapterPosition()).get_source().getTitle(),
                    feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().get(0),"video",new MovieSongsListFragment(),"trailer");
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        public ViewHolder1(View itemView) {
            super(itemView);
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

    public void loadMore()
    {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/").create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies(
                "http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=5&from="+count+"&pretty=true&q=contentType:%22trailer%22%20OR%20contentType:%22promo%22");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                count=count+5;
                feedInnerData.getHits().addAll(response.body().getHits().getHits());
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }
}
