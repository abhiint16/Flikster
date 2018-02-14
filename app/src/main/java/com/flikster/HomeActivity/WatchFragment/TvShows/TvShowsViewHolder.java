package com.flikster.HomeActivity.WatchFragment.TvShows;

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
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryCardClick;
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

public class TvShowsViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    FragmentManager fragmentManager;
    Context context;
    WatchFragment.WatchFragCommInterface watchFragCommInterface;
    FeedInnerData outerHits;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    int count=5;

    public TvShowsViewHolder(Context context, FragmentManager fragmentManager,
            FeedInnerData feedInnerData,LinearLayoutManager linearLayoutManager
            ,WatchFragment.WatchFragCommInterface watchFragCommInterface) {
        this.fragmentManager = fragmentManager;
        this.context=context;
        this.watchFragCommInterface=watchFragCommInterface;
        this.outerHits=feedInnerData;
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
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_images_recycler_item,parent,false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==0)
        {

        }
        else if (holder.getItemViewType()==2) {
            loadMore();
        }
        else
        {
            if (outerHits.getHits().get(position).get_source().getMedia()!=null)
            {
                if (outerHits.getHits().get(position).get_source().getMedia().getGallery()!=null&&
                        outerHits.getHits().get(position).get_source().getMedia().getGallery().size()!=0)
                {
                    Glide.with(context).load(outerHits.getHits().get(position).get_source().getMedia().getGallery().get(0))
                            .transform(new FaceCenterCrop())
                            .into(((ViewHolder2)holder).carousel_image);
                }
            }
            if (outerHits.getHits().get(position).get_source().getTitle()!=null)
            ((ViewHolder2)holder).carousel_title.setText(outerHits.getHits().get(position).get_source().getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if (outerHits.getHits()!=null&&outerHits.getHits().size()!=0)
        {
            if (outerHits.getTotal()==outerHits.getHits().size())
                return outerHits.getHits().size();
            return outerHits.getHits().size()+1;
        }
        else return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(outerHits.getHits()!=null&&outerHits.getHits().size()!=0)
        {
            if (position!=outerHits.getHits().size())
            return 1;
            else return 2;
        }
        else
            return 0;
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
            if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null&&outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size()!=0) {
                watchFragCommInterface.carouselItemToGallery(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick(),
                        outerHits.getHits().get(getAdapterPosition()).get_id(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getSlug());
            } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null&&outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size()!=0) {
                watchFragCommInterface.carouselItemToGallery(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick(),
                        outerHits.getHits().get(getAdapterPosition()).get_id(),
                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug());
            } else {
                watchFragCommInterface.carouselItemToGallery(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                        "",
                        "", "", outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick(),
                        outerHits.getHits().get(getAdapterPosition()).get_id(),"");
            }
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
                "http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=5&from="+count+"&pretty=true&q=contentType:%22gallery%22");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                count=count+5;
                outerHits.getHits().addAll(response.body().getHits().getHits());
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }
}
