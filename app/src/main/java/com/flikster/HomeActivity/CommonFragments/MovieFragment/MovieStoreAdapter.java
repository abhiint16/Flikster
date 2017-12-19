package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioShopByVideoViewHolder;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreInnerData;
import com.flikster.HomeActivity.FashionFragment.FashionType.CommonAllProductPage.CommonAllProductPage;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.HomeActivity.ProfileCollectionRecyclerItemAdapter;
import com.flikster.R;
import com.flikster.Util.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MovieStoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    ProfileCollectionRecyclerItemAdapter profileCollectionRecyclerItemAdapter;
    CelebrityBioShopByVideoViewHolder celebrityBioAdapterVideoViewHolder;
    MovieFeedRecommendedProductViewHolder movieFeedRecommendedProductViewHolder;
    RecyclerView.LayoutManager layoutManager2;
    ApiInterface apiInterface;
    List<RecommendedProductData.RecommendedProductInnerData> items;
    MovieFragment.MovieItemClickInterface movieItemClickInterface;
    String censor;
    String coverpic;
    String dor;
    String duration;
    ArrayList<String> genre = new ArrayList<>();
    String storyline;
    String slug;
    AllStoreInnerData hits;
    String entityId;
    String profilePic="";
    String name="";
    String title="";
    List<String> role=new ArrayList<>();
    String price="";
    String userId;

    public MovieStoreAdapter(Context context, FragmentManager fragmentManager, String coverpic, String censor,
                             String dor, ArrayList<String> genre, String duration, String title, String storyline, String slug,
                             AllStoreInnerData hits, String userId, String entityId) {
        this.userId = userId;
        this.entityId = entityId;
        this.context = context;
        movieItemClickInterface=(MovieFragment.MovieItemClickInterface)context;
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
        this.censor = censor;
        this.duration = duration;
        this.storyline = storyline;
        this.slug = slug;
        this.hits = hits;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_feed_profile, parent, false);
            return new ViewHolder0(view);
        } else if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details1, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details2, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details3, parent, false);
            return new ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details4, parent, false);
            return new ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details4_plus, parent, false);
            return new ViewHolder5(view);
        } else if (viewType == 100) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_no_comments, parent, false);
            return new ViewHolder100(view);
        } else
            return null;
    }

    public String formatDate(String dateOfRelease) {
        String subString = dateOfRelease.substring(3, dateOfRelease.indexOf("GMT") - 9);
        return subString;
    }

    public String formatRole(List<String> role) {
        String roleString = "";
        for (int i = 0; i < role.size(); i++) {
            roleString = roleString + ", " + role.get(i);
        }
        return roleString;
    }

    public String formatGenre() {
        String genre = "";
        for (int i = 0; i < this.genre.size(); i++) {
            if (i < genre.length() - 1)
                genre = genre + this.genre.get(i) + " | ";
            else
                genre = genre + this.genre.get(i);
        }
        return genre;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            if (title != null && !title.isEmpty()) {
                ((ViewHolder0) holder).card_movie_feed_profile_moviename.setText(title);
                new PostRetrofit().checkForFollow("follow", userId, entityId, ((ViewHolder0) holder).followbtn, context);
            }
            if (coverpic != null && !coverpic.isEmpty()) {
                Glide.with(context).load(coverpic).asBitmap()
                        .into(((ViewHolder0) holder).card_movie_feed_profile_image);
            }
            if (censor != null && !censor.isEmpty()) {
                ((ViewHolder0) holder).card_movie_feed_profile_censor.setText(censor);
            }
            if (dor != null && !dor.isEmpty()) {
                ((ViewHolder0) holder).card_movie_feed_profile_dor.setText(dor);
            }
            if (duration != null && !duration.isEmpty()) {
                ((ViewHolder0) holder).card_movie_feed_profile_dur.setText(duration);
            }
            if (genre != null && !genre.isEmpty()) {
                ((ViewHolder0) holder).card_movie_feed_profile_genre.setText(formatGenre());
            }
            ((ViewHolder0) holder).card_movie_feed_profile_storyline.setVisibility(View.GONE);
        } else if (holder.getItemViewType() == 1) {
            ((ViewHolder1) holder).followbtn.setText("BUY");
            ((ViewHolder1) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getPrice() != null) {
                ((ViewHolder1) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position - 1).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 1).get_source().getName() != null) {
                ((ViewHolder1) holder).card_fashion_details1_txt.setText(hits.getHits().get(position - 1).get_source().getName());
            }
            if (hits.getHits().get(position - 1).get_source().getPrice() != null) {
                ((ViewHolder1) holder).card_description_with_price_price.setText(hits.getHits().get(position - 1).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 1).get_source().getProductDescription() != null) {
                ((ViewHolder1) holder).card_description_with_price_title.setText(hits.getHits().get(position - 1).get_source().getProductDescription());
            }
            if (hits.getHits().get(position - 1).get_source().getCeleb() != null && hits.getHits().get(position - 1).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder1) holder).tv_tag_name.setText(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder1) holder).profile_image);
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder1) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position - 1).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(0).trim())
                        .into(((ViewHolder1) holder).card_fashion_details1_img);
            }
        } else if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).followbtn.setText("BUY");
            ((ViewHolder2) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getPrice() != null) {
                ((ViewHolder2) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position - 1).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 1).get_source().getName() != null) {
                ((ViewHolder2) holder).card_fashion_details2_txt.setText(hits.getHits().get(position - 1).get_source().getName());
            }
            if (hits.getHits().get(position - 1).get_source().getPrice() != null) {
                ((ViewHolder2) holder).card_description_with_price_price.setText(hits.getHits().get(position - 1).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 1).get_source().getProductDescription() != null) {
                ((ViewHolder2) holder).card_description_with_price_title.setText(hits.getHits().get(position - 1).get_source().getProductDescription());
            }
            if (hits.getHits().get(position - 1).get_source().getCeleb() != null && hits.getHits().get(position - 1).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder2) holder).tv_tag_name.setText(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder2) holder).profile_image);
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder2) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position - 1).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(0).trim())
                        .into(((ViewHolder2) holder).card_fashion_details2_img1);
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(1).trim())
                        .into(((ViewHolder2) holder).card_fashion_details2_img2);
            }
        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3) holder).followbtn.setText("BUY");
            ((ViewHolder3) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getPrice() != null) {
                ((ViewHolder3) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position - 1).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 1).get_source().getName() != null) {
                ((ViewHolder3) holder).card_fashion_details3_txt.setText(hits.getHits().get(position - 1).get_source().getName());
            }
            if (hits.getHits().get(position - 1).get_source().getPrice() != null) {
                ((ViewHolder3) holder).card_description_with_price_price.setText(hits.getHits().get(position - 1).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 1).get_source().getProductDescription() != null) {
                ((ViewHolder3) holder).card_description_with_price_title.setText(hits.getHits().get(position - 1).get_source().getProductDescription());
            }
            if (hits.getHits().get(position - 1).get_source().getCeleb() != null && hits.getHits().get(position - 1).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder3) holder).tv_tag_name.setText(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder3) holder).profile_image);
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder3) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position - 1).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(0).trim())
                        .into(((ViewHolder3) holder).card_fashion_details3_img1);
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(1).trim())
                        .into(((ViewHolder3) holder).card_fashion_details3_img2);
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(2).trim())
                        .into(((ViewHolder3) holder).card_fashion_details3_img3);
            }
        } else if (holder.getItemViewType() == 4) {
            ((ViewHolder4) holder).followbtn.setText("BUY");
            ((ViewHolder4) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getPrice() != null) {
                ((ViewHolder4) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position - 1).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 1).get_source().getName() != null) {
                ((ViewHolder4) holder).card_fashion_details4_txt.setText(hits.getHits().get(position - 1).get_source().getName());
            }
            if (hits.getHits().get(position - 1).get_source().getPrice() != null) {
                ((ViewHolder4) holder).card_description_with_price_price.setText(hits.getHits().get(position - 1).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 1).get_source().getProductDescription() != null) {
                ((ViewHolder4) holder).card_description_with_price_title.setText(hits.getHits().get(position - 1).get_source().getProductDescription());
            }
            if (hits.getHits().get(position - 1).get_source().getCeleb() != null && hits.getHits().get(position - 1).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder4) holder).tv_tag_name.setText(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder4) holder).profile_image);
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder4) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position - 1).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(0).trim())
                        .into(((ViewHolder4) holder).card_fashion_details4_img1);
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(1).trim())
                        .into(((ViewHolder4) holder).card_fashion_details4_img2);
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(2).trim())
                        .into(((ViewHolder4) holder).card_fashion_details4_img3);
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(3).trim())
                        .into(((ViewHolder4) holder).card_fashion_details4_img4);
            }
        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).followbtn.setText("BUY");
            ((ViewHolder5) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position - 1).get_source().getPrice() != null) {
                ((ViewHolder5) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position - 1).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 1).get_source().getName() != null) {
                ((ViewHolder5) holder).card_fashion_details4_plus_txt.setText(hits.getHits().get(position - 1).get_source().getName());
            }
            if (hits.getHits().get(position - 1).get_source().getPrice() != null) {
                ((ViewHolder5) holder).card_description_with_price_price.setText(hits.getHits().get(position - 1).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 1).get_source().getProductDescription() != null) {
                ((ViewHolder5) holder).card_description_with_price_title.setText(hits.getHits().get(position - 1).get_source().getProductDescription());
            }
            if (hits.getHits().get(position - 1).get_source().getCeleb() != null && hits.getHits().get(position - 1).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder5) holder).tv_tag_name.setText(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap()
                            .into(((ViewHolder5) holder).profile_image);
                if (hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder5) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position - 1).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position - 1).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(0).trim())
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img1);
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(1).trim())
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img2);
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(2).trim())
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img3);
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(3).trim())
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img4);
            }
            ((ViewHolder5) holder).card_fashion_details4_plus_text.setText("+ " + (hits.getHits().get(position - 1).get_source().getImageGallery().size() - 4));
        } else if (holder.getItemViewType() == 100) {
            ((ViewHolder100) holder).activity_no_comments_tv.setText("No Contents Available!");
        }
    }

   /* private void recommendedMoviesRetrofitInit(final RecyclerView.ViewHolder holder) {
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
    }*/

    @Override
    public int getItemCount() {
        if (hits.getHits().size() != 0 && hits.getHits() != null) {
            return hits.getHits().size() + 1;
        } else
            return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        else {
            if (hits.getHits().size() != 0 && hits.getHits() != null) {
                if (hits.getHits().get(position - 1).get_source().getImageGallery() != null && hits.getHits().get(position - 1).get_source().getImageGallery().size() != 0) {
                    switch (hits.getHits().get(position - 1).get_source().getImageGallery().size()) {
                        case 1:
                            return 1;
                        case 2:
                            return 2;
                        case 3:
                            return 3;
                        case 4:
                            return 4;
                        case 5:
                            return 5;
                    }
                }
            }
            return 100;
        }
    }

    public class ViewHolder0 extends RecyclerView.ViewHolder {
        TextView card_movie_feed_profile_moviename, card_movie_feed_profile_censor, card_movie_feed_profile_dor,
                card_movie_feed_profile_dur, card_movie_feed_profile_genre, card_movie_feed_profile_storyline;
        ImageView card_movie_feed_profile_image;
        Button followbtn;

        public ViewHolder0(View itemView) {
            super(itemView);
            card_movie_feed_profile_moviename = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_moviename);
            card_movie_feed_profile_censor = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_censor);
            card_movie_feed_profile_image = (ImageView) itemView.findViewById(R.id.card_movie_feed_profile_image);
            card_movie_feed_profile_dor = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_dor);
            card_movie_feed_profile_dur = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_dur);
            card_movie_feed_profile_genre = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_genre);
            card_movie_feed_profile_storyline = (TextView) itemView.findViewById(R.id.card_movie_feed_profile_storyline);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.followOrUnFollow(context, followbtn, userId, entityId);
                }
            });
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details1_img, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price, tv_tag_desc, tv_tag_name,
                card_fashion_details1_txt;
        RelativeLayout card_header_container;
        LinearLayout card_fashion_details1_img_container;
        public ViewHolder1(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            card_fashion_details1_txt = (TextView) itemView.findViewById(R.id.card_fashion_details1_txt);
            card_fashion_details1_img = (ImageView) itemView.findViewById(R.id.card_fashion_details1_img);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_fashion_details1_img_container=(LinearLayout)itemView.findViewById(R.id.card_fashion_details1_img_container);
            card_fashion_details1_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();

            }else if (v.getId()==R.id.card_fashion_details1_img_container)
            {
                cardContainerClick(getAdapterPosition()-1);
            }
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details2_img1, card_fashion_details2_img2, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price, tv_tag_desc, tv_tag_name,
                card_fashion_details2_txt;
        RelativeLayout card_header_container;
        LinearLayout card_fashion_details2_img_container;

        public ViewHolder2(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            card_fashion_details2_txt = (TextView) itemView.findViewById(R.id.card_fashion_details2_txt);
            card_fashion_details2_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details2_img1);
            card_fashion_details2_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details2_img2);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_fashion_details2_img_container=(LinearLayout)itemView.findViewById(R.id.card_fashion_details2_img_container);
            card_fashion_details2_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();

            }
            else if (v.getId()==R.id.card_fashion_details2_img_container)
            {
                cardContainerClick(getAdapterPosition()-1);
            }
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details3_img1, card_fashion_details3_img2, card_fashion_details3_img3, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price, tv_tag_desc, tv_tag_name,
                card_fashion_details3_txt;
        RelativeLayout card_header_container;
        LinearLayout card_my_style_one_big_img_container;

        public ViewHolder3(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            card_fashion_details3_txt = (TextView) itemView.findViewById(R.id.card_fashion_details3_txt);
            card_fashion_details3_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img1);
            card_fashion_details3_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img2);
            card_fashion_details3_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img3);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_my_style_one_big_img_container=(LinearLayout)itemView.findViewById(R.id.card_my_style_one_big_img_container);
            card_my_style_one_big_img_container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();
            }else if (v.getId()==R.id.card_my_style_one_big_img_container)
            {
                cardContainerClick(getAdapterPosition()-1);
            }
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details4_img1, card_fashion_details4_img2, card_fashion_details4_img3,
                card_fashion_details4_img4, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price, tv_tag_desc, tv_tag_name,
                card_fashion_details4_txt;
        RelativeLayout card_header_container;
        LinearLayout card_fashion_deatails4_img_container;

        public ViewHolder4(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            card_fashion_details4_txt = (TextView) itemView.findViewById(R.id.card_fashion_details4_txt);
            card_fashion_details4_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img1);
            card_fashion_details4_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img2);
            card_fashion_details4_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img3);
            card_fashion_details4_img4 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img4);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_fashion_deatails4_img_container=(LinearLayout)itemView.findViewById(R.id.card_fashion_deatails4_img_container);
            card_fashion_deatails4_img_container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();
            }
            else if (v.getId()==R.id.card_fashion_deatails4_img_container)
            {
                cardContainerClick(getAdapterPosition()-1);
            }
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details4_plus_img1, card_fashion_details4_plus_img2, card_fashion_details4_plus_img3,
                card_fashion_details4_plus_img4, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price, tv_tag_desc, tv_tag_name, card_fashion_details4_plus_text,
                card_fashion_details4_plus_txt;
        RelativeLayout card_header_container,card_fashion_details4_plus_img_container;

        public ViewHolder5(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            card_fashion_details4_plus_txt = (TextView) itemView.findViewById(R.id.card_fashion_details4_plus_txt);
            card_fashion_details4_plus_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img1);
            card_fashion_details4_plus_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img2);
            card_fashion_details4_plus_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img3);
            card_fashion_details4_plus_img4 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img4);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_fashion_details4_plus_text = (TextView) itemView.findViewById(R.id.card_fashion_details4_plus_text);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_fashion_details4_plus_img_container=(RelativeLayout)itemView.findViewById(R.id.card_fashion_details4_plus_img_container);
            card_fashion_details4_plus_img_container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();
            }
            else if (v.getId()==R.id.card_fashion_details4_plus_img_container)
            {
                cardContainerClick(getAdapterPosition()-1);
            }
        }
    }

    public class ViewHolder100 extends RecyclerView.ViewHolder {
        TextView activity_no_comments_tv;

        public ViewHolder100(View itemView) {
            super(itemView);
            activity_no_comments_tv = (TextView) itemView.findViewById(R.id.activity_no_comments_tv);
        }
    }

    public void cardContainerClick(int pos)
    {
        if (hits.getHits().get(pos).get_source().getCeleb().get(0).getProfilePic()!=null)
            profilePic=hits.getHits().get(pos).get_source().getCeleb().get(0).getProfilePic();
        if(hits.getHits().get(pos).get_source().getCeleb().get(0).getName()!=null)
            name=hits.getHits().get(pos).get_source().getCeleb().get(0).getName();
        if (hits.getHits().get(pos).get_source().getPrice()!=null)
            price=hits.getHits().get(pos).get_source().getPrice();
        if (hits.getHits().get(pos).get_source().getName()!=null)
            title=hits.getHits().get(pos).get_source().getName();
        if (hits.getHits().get(pos).get_source().getCeleb()!=null)
        {
            if (hits.getHits().get(pos).get_source().getCeleb().get(0).getRole() != null &&
                    hits.getHits().get(pos).get_source().getCeleb().get(0).getRole().size() != 0)
                role.addAll(hits.getHits().get(pos).get_source().getCeleb().get(0).getRole());
        }
        movieItemClickInterface.onGalleryContainerClick(hits.getHits().get(pos).get_source().getId(),
                hits.getHits().get(pos).get_source().getSize(),"abhiint",
                hits.getHits().get(pos).get_source().getPrice(),
                hits.getHits().get(pos).get_source().getProfilePic(),
                hits.getHits().get(pos).get_source().getProductInfo(),
                hits.getHits().get(pos).get_source().getSlug(),
                hits.getHits().get(pos).get_source().getImageGallery(),
                profilePic,role,name,title,new CommonAllProductPage());
    }
}
