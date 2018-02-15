package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.content.Context;
import android.content.Intent;
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
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebBioImagesData;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryCardClick;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryDataFromImage;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryFullScreen;
import com.flikster.R;
import com.rohitarya.glide.facedetection.transformation.FaceCenterCrop;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 13-10-2017.
 */

public class MovieInfoImagesViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CelebBioImagesData.CelebBioImagesDataInner> movieAllImages=new ArrayList<>();
    Context context;
    MovieFragmentInfo.MovieToShopByVideoInterface movieToShopByVideoInterface;
    String userId;
    ApiInterface apiInterface;

    public MovieInfoImagesViewHolder(Context context, List<CelebBioImagesData.CelebBioImagesDataInner> movieAllImages,
                                     MovieFragmentInfo.MovieToShopByVideoInterface movieToShopByVideoInterface,
                                     String userId) {
        this.context=context;
        this.movieAllImages=movieAllImages;
        this.movieToShopByVideoInterface=movieToShopByVideoInterface;
        this.userId=userId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_not_available_layout,parent,false);
            return new ViewHolder1(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_images_recycler_item,parent,false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==1)
        {

        }
        else if (holder.getItemViewType()==2)
        {
            Glide.with(context).load(movieAllImages.get(position).getGallery())
                    .transform(new FaceCenterCrop())
                    .into(((ViewHolder2)holder).carousel_image);
            ((ViewHolder2)holder).carousel_title.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount()
    {
        if (movieAllImages==null||movieAllImages.size()==0)
            return 1;
        else
            return movieAllImages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (movieAllImages==null||movieAllImages.size()==0)
            return 1;
        else
            return 2;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView carousel_image;
        TextView carousel_title;
        public ViewHolder2(View itemView) {
            super(itemView);
            carousel_image=(ImageView)itemView.findViewById(R.id.carousel_image);
            carousel_title=(TextView)itemView.findViewById(R.id.carousel_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    retrofitForImageGallery();
                }
            });
        }

        private void retrofitForImageGallery() {
            apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/content-ms/getContentById/").create(ApiInterface.class);
            Call<GalleryDataFromImage> call = apiInterface.getGalleryDataFromImage("http://apiservice.flikster.com/v3/content-ms/getContentById/"+movieAllImages.get(getAdapterPosition()).getId());
            call.enqueue(new Callback<GalleryDataFromImage>() {
                @Override
                public void onResponse(Call<GalleryDataFromImage> call, Response<GalleryDataFromImage> response) {
                    if (response.body().getMovie() != null&&
                            response.body().getMovie().size()!=0) {
                        movieToShopByVideoInterface.galleryCardOnClick(response.body().getMedia().getGallery(),
                                response.body().getMovie().get(0).getName(),
                                response.body().getMovie().get(0).getProfilePic(),
                                response.body().getMovie().get(0).getType(),
                                response.body().getTitle(),
                                new GalleryCardClick(), userId,
                                response.body().getMovie().get(0).getId(),
                                response.body().getMovie().get(0).getId(),response.body().getMovie().get(0).getSlug());
                    } else if (response.body().getCeleb() != null&&
                            response.body().getCeleb().size()!=0) {
                        movieToShopByVideoInterface.galleryCardOnClick(response.body().getMedia().getGallery(),
                                response.body().getCeleb().get(0).getName(),
                                response.body().getCeleb().get(0).getProfilePic(),
                                response.body().getCeleb().get(0).getType(),
                                response.body().getTitle(),
                                new GalleryCardClick(), userId,
                                response.body().getCeleb().get(0).getId(),
                                response.body().getCeleb().get(0).getId(),response.body().getCeleb().get(0).getSlug());
                    } else {
                        movieToShopByVideoInterface.galleryCardOnClick(response.body().getMedia().getGallery(),
                                "",
                                "", "", response.body().getTitle(),
                                new GalleryCardClick(), userId,
                                response.body().getCeleb().get(0).getId(),
                                response.body().getCeleb().get(0).getId(),"");
                    }
                }
                @Override
                public void onFailure(Call<GalleryDataFromImage> call, Throwable t) {
                    Log.e("vvvvvvvvvv","vv"+call+t);
                }
            });
        }
    }
}
