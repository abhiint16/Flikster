package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.content.Context;
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
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterVideoViewHolder;
import com.flikster.HomeActivity.ProfileCollectionRecyclerItemAdapter;
import com.flikster.HomeActivity.StealStyleViewHolder;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MovieStoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    ProfileCollectionRecyclerItemAdapter profileCollectionRecyclerItemAdapter;
    CelebrityBioAdapterVideoViewHolder celebrityBioAdapterVideoViewHolder;
    MovieFeedRecommendedProductViewHolder movieFeedRecommendedProductViewHolder;
    RecyclerView.LayoutManager layoutManager2;
    ApiInterface apiInterface;
    List<RecommendedProductData.RecommendedProductInnerData> items;
    String censor;
    String coverpic;
    String dor;
    String duration;
    String title;
    ArrayList<String> genre = new ArrayList<>();
    String storyline;
    String slug;

    public MovieStoreAdapter(Context context, FragmentManager fragmentManager, String coverpic, String censor,
                             String dor, ArrayList<String> genre,String duration,String title,String storyline,String slug) {
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
        this.storyline=storyline;
        this.slug=slug;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_feed_profile, parent, false);
            return new MovieStoreAdapter.ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_store_gallary4_1, parent, false);
            return new MovieStoreAdapter.ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_store_profile_collection, parent, false);
            return new MovieStoreAdapter.ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new MovieStoreAdapter.ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_store_gallary4_1, parent, false);
            return new MovieStoreAdapter.ViewHolder5(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_steal_style_carousel, parent, false);
            return new MovieStoreAdapter.ViewHolder6(view);
        }
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
                ((ViewHolder1) holder).card_movie_feed_profile_dor.setText(formatDate(dor));
            }
            if(duration != null && !duration.isEmpty()){
                ((ViewHolder1) holder).card_movie_feed_profile_dur.setText(duration);
            }
            if(genre != null && !genre.isEmpty())
            {
                ((ViewHolder1) holder).card_movie_feed_profile_genre.setText(formatGenre());
            }
            ((ViewHolder1)holder).card_movie_feed_profile_storyline.setVisibility(View.GONE);


        } else if (holder.getItemViewType() == 2) {

        } else if (holder.getItemViewType() == 3) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder3) holder).fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
            profileCollectionRecyclerItemAdapter = new ProfileCollectionRecyclerItemAdapter(context, 3, fragmentManager);
            ((ViewHolder3) holder).fragment_common_recyclerview_recycler.setAdapter(profileCollectionRecyclerItemAdapter);
        } else if (holder.getItemViewType() == 4) {
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_title.setText("Shop By videos");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterVideoViewHolder = new CelebrityBioAdapterVideoViewHolder(fragmentManager);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterVideoViewHolder);
        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).card_store_gallary4_img1.setImageResource(R.drawable.top1);
            ((ViewHolder5) holder).card_store_gallary4_img2.setImageResource(R.drawable.top2);
            ((ViewHolder5) holder).card_store_gallary4_img3.setImageResource(R.drawable.jhumka);
            ((ViewHolder5) holder).card_store_gallary4_img4.setImageResource(R.drawable.sandal);
            ((ViewHolder5) holder).card_store_product_gallary_title.setText("Latest trend");
            ((ViewHolder5) holder).card_store_product_gallary_cost.setText("7500/-");
        } else if (holder.getItemViewType() == 6) {
            recommendedMoviesRetrofitInit(holder);
        }
    }

    private void recommendedMoviesRetrofitInit(final RecyclerView.ViewHolder holder) {
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/product-ms/products").create(ApiInterface.class);
        Call<RecommendedProductData> call = apiInterface.getRecommendedProductData("http://apiv3.flikster.com/v3/product-ms/products");
        call.enqueue(new Callback<RecommendedProductData>() {
            @Override
            public void onResponse(Call<RecommendedProductData> call, Response<RecommendedProductData> response) {
                items = response.body().getItems();
                ((MovieStoreAdapter.ViewHolder6) holder).card_steal_style_carousel_title.setText("Recommended Products");
                ((MovieStoreAdapter.ViewHolder6) holder).fragment_common_recyclerview_with_tv_title.setVisibility(View.GONE);
                layoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                ((MovieStoreAdapter.ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager2);
                movieFeedRecommendedProductViewHolder = new MovieFeedRecommendedProductViewHolder(items, context);
                ((MovieStoreAdapter.ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(movieFeedRecommendedProductViewHolder);
            }

            @Override
            public void onFailure(Call<RecommendedProductData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
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
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        public ViewHolder2(View itemView) {
            super(itemView);
        /*    textView=(TextView)itemView.findViewById(R.id.txt);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);*/
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView fragment_common_recyclerview_recycler;

        public ViewHolder3(View itemView) {
            super(itemView);
            //textView=(TextView)itemView.findViewById(R.id.txt);
            fragment_common_recyclerview_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_recycler);
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
        TextView card_store_product_gallary_title, card_store_product_gallary_cost;
        ImageView card_store_gallary4_img1, card_store_gallary4_img2, card_store_gallary4_img3, card_store_gallary4_img4;

        public ViewHolder5(View itemView) {
            super(itemView);
            card_store_product_gallary_title = (TextView) itemView.findViewById(R.id.card_store_product_gallary_title);
            card_store_product_gallary_cost = (TextView) itemView.findViewById(R.id.card_store_product_gallary_cost);
            card_store_gallary4_img1 = (ImageView) itemView.findViewById(R.id.card_store_gallary4_img1);
            card_store_gallary4_img2 = (ImageView) itemView.findViewById(R.id.card_store_gallary4_img2);
            card_store_gallary4_img3 = (ImageView) itemView.findViewById(R.id.card_store_gallary4_img3);
            card_store_gallary4_img4 = (ImageView) itemView.findViewById(R.id.card_store_gallary4_img4);
        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder {
        TextView card_steal_style_carousel_title, fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder6(View itemView) {
            super(itemView);
            card_steal_style_carousel_title = (TextView) itemView.findViewById(R.id.card_steal_style_carousel_title);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }
    }


}
