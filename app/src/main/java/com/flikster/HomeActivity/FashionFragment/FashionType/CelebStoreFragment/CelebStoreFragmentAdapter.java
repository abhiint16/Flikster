package com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductOnClick;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreInnerData;
import com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreInnerItems.CeleStoreRecyclerItemAdapter;
import com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreInnerItems.CeleStoreTredingCelebFashionRecyclerItemAdapter;
import com.flikster.HomeActivity.FashionFragment.FashionType.CommonAllProductPage.CommonAllProductPage;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.HomeActivity.ShopByVideoData;
import com.flikster.HomeActivity.WidgetData;
import com.flikster.R;
import com.flikster.Util.Common;
import com.flikster.Util.ExpandedGridView;
import com.flikster.Util.SharedPrefsUtil;
import com.rohitarya.glide.facedetection.transformation.FaceCenterCrop;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 23-10-2017.
 */

public class CelebStoreFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RecyclerView.LayoutManager layoutManager;
    Context context;
    List<Integer> type = new ArrayList<>();
    FragmentManager fragmentManager;
    CelebShopByVideosFragmentAdapterViewHolder celebShopByVideosFragmentAdapterViewHolder;
    CeleStoreRecyclerItemAdapter celeStoreRecyclerItemAdapter;
    CeleStoreTredingCelebFashionRecyclerItemAdapter celeStoreTredingCelebFashionRecyclerItemAdapter;
    List<String> carouselImg = new ArrayList<>();
    AllStoreInnerData hits;
    ApiInterface apiInterface;
    ShopByVideoData.ShopByVideoInnerData outerHits;
    WidgetData.WidgetInnerData widgetHits;
    CelebStoreFirstTypeFragment.ShopByVideoInterafce shopByVideoInterafce;
    String profilePic = "";
    String name = "";
    String title = "";
    List<String> role = new ArrayList<>();
    String price = "";
    String userId = "";
    String URL;

    public CelebStoreFragmentAdapter(Context context, FragmentManager fragmentManager, AllStoreInnerData hits,
                                     CelebStoreFirstTypeFragment.ShopByVideoInterafce shopByVideoInterafce,
                                     String URL) {
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        type.add(5);
        type.add(6);
        type.add(7);
        this.context = context;
        this.URL=URL;
        this.fragmentManager = fragmentManager;
        this.hits = hits;
        this.shopByVideoInterafce = shopByVideoInterafce;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celeb_profile, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carouselview, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview, parent, false);
            return new ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv_one, parent, false);
            return new ViewHolder4(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv_one, parent, false);
            return new ViewHolder6(view);
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv_one, parent, false);
            return new ViewHolder7(view);
        } else if (viewType == 11) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details1, parent, false);
            return new ViewHolder11(view);
        } else if (viewType == 12) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details2, parent, false);
            return new ViewHolder12(view);
        } else if (viewType == 13) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details3, parent, false);
            return new ViewHolder13(view);
        } else if (viewType == 14) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details4, parent, false);
            return new ViewHolder14(view);
        } else if (viewType == 15) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details4_plus, parent, false);
            return new ViewHolder15(view);
        } else if (viewType == 20) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details1, parent, false);
            return new ViewHolder20(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_profile, parent, false);
            return new ViewHolder1(view);
        }
    }

    public String formatRole(List<String> role) {
        String roleString = "";
        for (int i = 0; i < role.size(); i++) {
            roleString = roleString + ", #" + role.get(i);
        }
        return roleString.substring(1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null &&
                !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
            userId = SharedPrefsUtil.getStringPreference(context, "USER_ID");
        }
        if (holder.getItemViewType() == 1) {
            if (SharedPrefsUtil.getStringPreference(context, "HEADER_NAME").equals("MOVIE_STORE")) {
                ((ViewHolder1) holder).fashionname.setText("Movie Store");
                ((ViewHolder1) holder).card_celebrity_feed_profile_layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.white_radius_moviestoretab));
                ((ViewHolder1) holder).card_celebrity_feed_profile_coverpic.setBackgroundColor(context.getResources().getColor(R.color.ligth_blue_grey));
                ((ViewHolder1) holder).profileimg.setImageResource(R.drawable.moviestore);
            } else {
                ((ViewHolder1) holder).fashionname.setText("Celebrity Store");
            }


        } else if (holder.getItemViewType() == 2) {
            int j = 0;
            int k;
            if (hits.getHits().size()>10)
            {
                k=10;
            }
            else
            k=hits.getHits().size();
            for (int i = 0; i < k; i++) {
                if (hits.getHits().get(i).get_source().getImageGallery() != null && hits.getHits().get(i).get_source().getImageGallery().size() != 0) {
                    carouselImg.add(hits.getHits().get(i).get_source().getImageGallery().get(0));
                    j = ++j;
                }
            }
            ((ViewHolder2) holder).carouselView.setPageCount(j);
            ((ViewHolder2) holder).carouselView.setImageListener(imageListener);
        } else if (holder.getItemViewType() == 3) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ((ViewHolder3) holder).fragment_common_recycler_container.setLayoutParams(layoutParams);
            ((ViewHolder3) holder).fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
            initRetrofitProfileCollection(((ViewHolder3) holder).fragment_common_recyclerview_recycler,
                    "http://apiservice-es.flikster.com/widgets/_search?pretty=true&size=100&q=*");
        } else if (holder.getItemViewType() == 4) {

            ((ViewHolder4)holder).fragment_common_recyclerview_with_tv_layout.setVisibility(View.GONE);
            //((ViewHolder4) holder).fragment_common_recyclerview_with_tv_title.setText("Trending Celebrity Fashions");
            /*layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celeStoreTredingCelebFashionRecyclerItemAdapter = new CeleStoreTredingCelebFashionRecyclerItemAdapter(context, 3, fragmentManager);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celeStoreTredingCelebFashionRecyclerItemAdapter);*/
        } else if (holder.getItemViewType() == 6) {
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_title.setText("Shop By Videos");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            initRetrofit(((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler,
                    "http://apiservice-es.flikster.com/shopbyvideos/_search?size=100&pretty=true&q=category:\"celebrity\"");
        } else if (holder.getItemViewType() == 7) {
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_title.setText("Recommended Products");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            //celebShopByVideosFragmentAdapterViewHolder = new CelebShopByVideosFragmentAdapterViewHolder(context,fragmentManager);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebShopByVideosFragmentAdapterViewHolder);
        }
        if (holder.getItemViewType() == 11) {
            ((ViewHolder11) holder).followbtn.setText("BUY");
            ((ViewHolder11) holder).card_fashion_details1_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position - 4).get_source().getPrice() != null) {
                ((ViewHolder11) holder).card_description_with_price_price.setText("\u20B9 "
                        + hits.getHits().get(position - 4).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 4).get_source().getName() != null) {
                ((ViewHolder11) holder).card_description_with_price_title.setText(hits.getHits().get(position - 4).get_source().getName());
            }
            if (hits.getHits().get(position - 4).get_source().getCeleb() != null && hits.getHits().get(position - 4).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder11) holder).tv_tag_name.setText(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic() != null)
                    Log.e("singleImgPro", hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic() + "SS");
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic())
                        .asBitmap().into(((ViewHolder11) holder).profile_image);
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder11) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position - 4).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder11) holder).card_fashion_details1_img);
            }

            if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null &&
                    !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
                new PostRetrofit().checkForLike("like",
                        userId, hits.getHits().get(position - 4).get_source().getProfilePic(),
                        ((ViewHolder11) holder).ib_like, context);
                new PostRetrofit().checkForBookmark("bookmark", userId,
                        hits.getHits().get(position - 4).get_source().getProfilePic(),
                        ((ViewHolder11) holder).ib_bookmark, context);
            }


        } else if (holder.getItemViewType() == 12) {
            ((ViewHolder12) holder).followbtn.setText("BUY");
            ((ViewHolder12) holder).card_fashion_details2_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position - 4).get_source().getPrice() != null) {
                ((ViewHolder12) holder).card_description_with_price_price.setText("\u20B9 " + hits.getHits().get(position - 4).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 4).get_source().getName() != null) {
                ((ViewHolder12) holder).card_description_with_price_title.setText(hits.getHits().get(position - 4).get_source().getName());
            }
            if (hits.getHits().get(position - 4).get_source().getCeleb() != null && hits.getHits().get(position - 4).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder12) holder).tv_tag_name.setText(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder12) holder).profile_image);
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder12) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position - 4).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder12) holder).card_fashion_details2_img1);
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(1).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder12) holder).card_fashion_details2_img2);
                if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null &&
                        !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
                    new PostRetrofit().checkForLike("like",
                            userId, hits.getHits().get(position - 4).get_source().getProfilePic(),
                            ((ViewHolder12) holder).ib_like, context);
                    new PostRetrofit().checkForBookmark("bookmark", userId,
                            hits.getHits().get(position - 4).get_source().getProfilePic(),
                            ((ViewHolder12) holder).ib_bookmark, context);
                }
            }
        } else if (holder.getItemViewType() == 13) {
            ((ViewHolder13) holder).followbtn.setText("BUY");
            ((ViewHolder13) holder).card_fashion_details3_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position - 4).get_source().getPrice() != null) {
                ((ViewHolder13) holder).card_description_with_price_price.setText("\u20B9 " + hits.getHits().get(position - 4).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 4).get_source().getName() != null) {
                ((ViewHolder13) holder).card_description_with_price_title.setText(hits.getHits().get(position - 4).get_source().getName());
            }
            if (hits.getHits().get(position - 4).get_source().getCeleb() != null && hits.getHits().get(position - 4).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder13) holder).tv_tag_name.setText(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder13) holder).profile_image);
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder13) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position - 4).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder13) holder).card_fashion_details3_img1);
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(1).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder13) holder).card_fashion_details3_img2);
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(2).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder13) holder).card_fashion_details3_img3);

            }
            if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null &&
                    !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
                new PostRetrofit().checkForLike("like",
                        userId, hits.getHits().get(position - 4).get_source().getProfilePic(),
                        ((ViewHolder13) holder).ib_like, context);
                new PostRetrofit().checkForBookmark("bookmark", userId,
                        hits.getHits().get(position - 4).get_source().getProfilePic(),
                        ((ViewHolder13) holder).ib_bookmark, context);
            }
        } else if (holder.getItemViewType() == 14) {
            ((ViewHolder14) holder).followbtn.setText("BUY");
            ((ViewHolder14) holder).card_fashion_details4_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position - 4).get_source().getPrice() != null) {
                ((ViewHolder14) holder).card_description_with_price_price.setText("\u20B9 " + hits.getHits().get(position - 4).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 4).get_source().getName() != null) {
                ((ViewHolder14) holder).card_description_with_price_title.setText(hits.getHits().get(position - 4).get_source().getName());
            }
            if (hits.getHits().get(position - 4).get_source().getCeleb() != null && hits.getHits().get(position - 4).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder14) holder).tv_tag_name.setText(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder14) holder).profile_image);
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder14) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position - 4).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder14) holder).card_fashion_details4_img1);
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(1).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder14) holder).card_fashion_details4_img2);
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(2).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder14) holder).card_fashion_details4_img3);
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(3).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder14) holder).card_fashion_details4_img4);
            }
            if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null &&
                    !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
                new PostRetrofit().checkForLike("like",
                        userId, hits.getHits().get(position - 4).get_source().getProfilePic(),
                        ((ViewHolder14) holder).ib_like, context);
                new PostRetrofit().checkForBookmark("bookmark", userId,
                        hits.getHits().get(position - 4).get_source().getProfilePic(),
                        ((ViewHolder14) holder).ib_bookmark, context);
            }
        } else if (holder.getItemViewType() == 15) {
            ((ViewHolder15) holder).followbtn.setText("BUY");
            ((ViewHolder15) holder).card_fashion_details4_plus_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position - 4).get_source().getPrice() != null) {
                ((ViewHolder15) holder).card_description_with_price_price.setText("\u20B9 " + hits.getHits().get(position - 4).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position - 4).get_source().getName() != null) {
                ((ViewHolder15) holder).card_description_with_price_title.setText(hits.getHits().get(position - 4).get_source().getName());
            }
            if (hits.getHits().get(position - 4).get_source().getCeleb() != null && hits.getHits().get(position - 4).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder15) holder).tv_tag_name.setText(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap()
                            .into(((ViewHolder15) holder).profile_image);
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder15) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position - 4).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder15) holder).card_fashion_details4_plus_img1);
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(1).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder15) holder).card_fashion_details4_plus_img2);
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(2).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder15) holder).card_fashion_details4_plus_img3);
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getImageGallery().get(3).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder15) holder).card_fashion_details4_plus_img4);
            }
            ((ViewHolder15) holder).card_fashion_details4_plus_text.setText("+ " + (hits.getHits().get(position - 4).get_source().getImageGallery().size() - 4));
            if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null &&
                    !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
                new PostRetrofit().checkForLike("like",
                        userId, hits.getHits().get(position - 4).get_source().getProfilePic(),
                        ((ViewHolder15) holder).ib_like, context);
                new PostRetrofit().checkForBookmark("bookmark", userId,
                        hits.getHits().get(position - 4).get_source().getProfilePic(),
                        ((ViewHolder15) holder).ib_bookmark, context);
            }
        } else if (holder.getItemViewType() == 20) {
            ((ViewHolder20) holder).followbtn.setText("BUY");
            if (hits.getHits().get(position - 4).get_source().getPrice() != null) {
                ((ViewHolder20) holder).card_description_with_price_price.setText("\u20B9 " + hits.getHits().get(position - 4).get_source().getPrice() + " /-");
            }
            ((ViewHolder20) holder).card_fashion_details1_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position - 4).get_source().getName() != null) {
                ((ViewHolder20) holder).card_description_with_price_title.setText(hits.getHits().get(position - 4).get_source().getName());
            }
            if (hits.getHits().get(position - 4).get_source().getCeleb() != null && hits.getHits().get(position - 4).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder20) holder).tv_tag_name.setText(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder20) holder).profile_image);
                if (hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder20) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position - 4).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position - 4).get_source().getProfilePic() != null) {
                Glide.with(context).load(hits.getHits().get(position - 4).get_source().getProfilePic().trim())
                        .into(((ViewHolder20) holder).card_fashion_details1_img);
            }
            if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null &&
                    !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
                new PostRetrofit().checkForLike("like",
                        userId, hits.getHits().get(position - 4).get_source().getProfilePic(),
                        ((ViewHolder20) holder).ib_like, context);
                new PostRetrofit().checkForBookmark("bookmark", userId,
                        hits.getHits().get(position - 4).get_source().getProfilePic(),
                        ((ViewHolder20) holder).ib_bookmark, context);
            }
        }


    }

    private void initRetrofitProfileCollection(final RecyclerView recyclerView, String url) {
        apiInterface = ApiClient.getClient("http://apiservice-es.flikster.com/widgets/_search/").create(ApiInterface.class);
        Call<WidgetData> call = apiInterface.getWidgetData(url);
        call.enqueue(new Callback<WidgetData>() {
            @Override
            public void onResponse(Call<WidgetData> call, Response<WidgetData> response) {
                widgetHits = response.body().getHits();
                celeStoreRecyclerItemAdapter = new CeleStoreRecyclerItemAdapter(context, fragmentManager, widgetHits);
                recyclerView.setAdapter(celeStoreRecyclerItemAdapter);
            }

            @Override
            public void onFailure(Call<WidgetData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initRetrofit(final RecyclerView recyclerView, String url) {

        apiInterface = ApiClient.getClient("http://apiservice-es.flikster.com/shopbyvideos/_search/").create(ApiInterface.class);
        Call<ShopByVideoData> call = apiInterface.getShopByVideo(url);
        call.enqueue(new Callback<ShopByVideoData>() {
            @Override
            public void onResponse(Call<ShopByVideoData> call, Response<ShopByVideoData> response) {
                outerHits = response.body().getHits();
                celebShopByVideosFragmentAdapterViewHolder = new CelebShopByVideosFragmentAdapterViewHolder(context, fragmentManager, outerHits, shopByVideoInterafce);
                recyclerView.setAdapter(celebShopByVideosFragmentAdapterViewHolder);
            }

            @Override
            public void onFailure(Call<ShopByVideoData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e("size map", "" + (hits.getHits().size() + 6));
        return (hits.getHits().size() + 6);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 1;
        else if (position == 1)
            return 2;
        else if (position == 2)
            return 3;
        else if (position == 3)
            return 4;
        else if (position == (hits.getHits().size() + 6) - 2) {
            return 6;
        } else if (position == (hits.getHits().size() + 6) - 1) {
            return 7;
        } else {
            if (hits.getHits().get(position - 4).get_source().getImageGallery() != null && hits.getHits().get(position - 4).get_source().getImageGallery().size() != 0) {
                switch (hits.getHits().get(position - 4).get_source().getImageGallery().size()) {
                    case 1:
                        return 11;
                    case 2:
                        return 12;
                    case 3:
                        return 13;
                    case 4:
                        return 14;
                    default:
                        return 15;
                }
            } else
                return 20;
        }
        //return 20;
    }

    /*@Override
    public int getItemViewType(int position) {
        return position;
    }*/

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView fashionname;
        ImageView card_celebrity_feed_profile_coverpic, profileimg;
        LinearLayout card_celebrity_feed_profile_layout;

        public ViewHolder1(View itemView) {
            super(itemView);
            profileimg = (ImageView) itemView.findViewById(R.id.profileimg);
            card_celebrity_feed_profile_layout = (LinearLayout) itemView.findViewById(R.id.card_celebrity_feed_profile_layout);
            fashionname = (TextView) itemView.findViewById(R.id.fashionname);
            card_celebrity_feed_profile_coverpic = (ImageView) itemView.findViewById(R.id.card_celebrity_feed_profile_coverpic);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        ExpandedGridView expandgrid;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;
        CarouselView carouselView;


        public ViewHolder2(View itemView) {
            super(itemView);
//            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
//            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
//            expandgrid = (ExpandedGridView) itemView.findViewById(R.id.expandgrid);
//            fragment_common_recyclerview_with_tv_title.setVisibility(View.GONE);
            carouselView = (CarouselView) itemView.findViewById(R.id.carouselView);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView fragment_common_recyclerview_recycler;
        RelativeLayout fragment_common_recycler_container;

        public ViewHolder3(View itemView) {
            super(itemView);
            fragment_common_recyclerview_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_recycler);
            fragment_common_recycler_container = (RelativeLayout) itemView.findViewById(R.id.fragment_common_recycler_container);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;
        LinearLayout fragment_common_recyclerview_with_tv_layout;

        public ViewHolder4(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            fragment_common_recyclerview_with_tv_layout=(LinearLayout)itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_layout);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder5(View itemView) {
            super(itemView);

        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder6(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolder7 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder7(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolder11 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details1_img, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name, card_fashion_details1_txt;
        LinearLayout card_fashion_details1_img_container, header_linear;
        ImageButton ib_like, ib_bookmark, card_footer_share;

        public ViewHolder11(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details1_img = (ImageView) itemView.findViewById(R.id.card_fashion_details1_img);
            card_fashion_details1_txt = (TextView) itemView.findViewById(R.id.card_fashion_details1_txt);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_fashion_details1_img_container = (LinearLayout) itemView.findViewById(R.id.card_fashion_details1_img_container);
            card_fashion_details1_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("singleImgProint", hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic() + "SS");
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 4).get_source().getImageGallery().get(0).trim(),context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("singleImgProint", hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic() + "SS");
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic());
                }
            });


        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                checkForLoggedIn(getAdapterPosition());
            } else if (v.getId() == R.id.card_fashion_details1_img_container) {
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getProfilePic() != null)
                    profilePic = hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getProfilePic();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getName() != null)
                    name = hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getName();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice() != null)
                    price = hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getName() != null)
                    title = hits.getHits().get(getAdapterPosition() - 4).get_source().getName();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole() != null &&
                            hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole().size() != 0)
                        role.addAll(hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole());
                }
                shopByVideoInterafce.onGalleryContainerClick(hits.getHits().get(getAdapterPosition() - 4).get_source().getId(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getSize(), "abhiint",
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getProductInfo(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getSlug(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getImageGallery(),
                        profilePic, role, name, title, new CommonAllProductPage());
            } else if ((v.getId() == R.id.header_linear) || (v.getId() == R.id.profile_image)) {
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb() != null && hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().size() != 0) {
                    shopByVideoInterafce.fromCelebToCelebPage(hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getId());
                }
            }
        }
    }

    public class ViewHolder12 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details2_img1, card_fashion_details2_img2, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name, card_fashion_details2_txt;
        LinearLayout card_fashion_details2_img_container, header_linear;
        ImageButton ib_like, ib_bookmark, card_footer_share;

        public ViewHolder12(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details2_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details2_img1);
            card_fashion_details2_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details2_img2);
            card_fashion_details2_txt = (TextView) itemView.findViewById(R.id.card_fashion_details2_txt);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_fashion_details2_img_container = (LinearLayout) itemView.findViewById(R.id.card_fashion_details2_img_container);
            card_fashion_details2_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("singleImgProint", hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic() + "SS");
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 4).get_source().getImageGallery().get(0).trim(), context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("singleImgProint", hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic() + "SS");
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                checkForLoggedIn(getAdapterPosition());
            } else if (v.getId() == R.id.card_fashion_details2_img_container) {
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getProfilePic() != null)
                    profilePic = hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getProfilePic();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getName() != null)
                    name = hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getName();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice() != null)
                    price = hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getName() != null)
                    title = hits.getHits().get(getAdapterPosition() - 4).get_source().getName();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole() != null &&
                            hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole().size() != 0)
                        role.addAll(hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole());
                }
                shopByVideoInterafce.onGalleryContainerClick(hits.getHits().get(getAdapterPosition() - 4).get_source().getId(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getSize(), "abhiint",
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getProductInfo(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getSlug(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getImageGallery(),
                        profilePic, role, name, title, new CommonAllProductPage());
            } else if ((v.getId() == R.id.header_linear) || (v.getId() == R.id.profile_image)) {
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb() != null && hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().size() != 0) {
                    shopByVideoInterafce.fromCelebToCelebPage(hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getId());
                }
            }
        }
    }

    public class ViewHolder13 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details3_img1, card_fashion_details3_img2, card_fashion_details3_img3, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name, card_fashion_details3_txt;
        LinearLayout card_my_style_one_big_img_container, header_linear;
        ImageButton ib_like, ib_bookmark, card_footer_share;

        public ViewHolder13(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details3_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img1);
            card_fashion_details3_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img2);
            card_fashion_details3_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img3);
            card_fashion_details3_txt = (TextView) itemView.findViewById(R.id.card_fashion_details3_txt);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_my_style_one_big_img_container = (LinearLayout) itemView.findViewById(R.id.card_my_style_one_big_img_container);
            card_my_style_one_big_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("singleImgProint", hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic() + "SS");
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 4).get_source().getImageGallery().get(0).trim(), context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("singleImgProint", hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic() + "SS");
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                checkForLoggedIn(getAdapterPosition());
            } else if (v.getId() == R.id.card_my_style_one_big_img_container) {
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getProfilePic() != null)
                    profilePic = hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getProfilePic();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getName() != null)
                    name = hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getName();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice() != null)
                    price = hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getName() != null)
                    title = hits.getHits().get(getAdapterPosition() - 4).get_source().getName();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole() != null &&
                            hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole().size() != 0)
                        role.addAll(hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole());
                }
                shopByVideoInterafce.onGalleryContainerClick(hits.getHits().get(getAdapterPosition() - 4).get_source().getId(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getSize(), "abhiint",
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getProductInfo(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getSlug(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getImageGallery(),
                        profilePic, role, name, title, new CommonAllProductPage());
            } else if ((v.getId() == R.id.header_linear) || (v.getId() == R.id.profile_image)) {
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb() != null && hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().size() != 0) {
                    shopByVideoInterafce.fromCelebToCelebPage(hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getId());
                }
            }
        }
    }

    public class ViewHolder14 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details4_img1, card_fashion_details4_img2, card_fashion_details4_img3,
                card_fashion_details4_img4, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name, card_fashion_details4_txt;
        LinearLayout card_fashion_deatails4_img_container, header_linear;
        ImageButton ib_like, ib_bookmark, card_footer_share;

        public ViewHolder14(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details4_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img1);
            card_fashion_details4_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img2);
            card_fashion_details4_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img3);
            card_fashion_details4_img4 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img4);
            card_fashion_details4_txt = (TextView) itemView.findViewById(R.id.card_fashion_details4_txt);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_fashion_deatails4_img_container = (LinearLayout) itemView.findViewById(R.id.card_fashion_deatails4_img_container);
            card_fashion_deatails4_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("singleImgProint", hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic() + "SS");
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 4).get_source().getImageGallery().get(0).trim(),context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("singleImgProint", hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic() + "SS");
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                checkForLoggedIn(getAdapterPosition());
            } else if (v.getId() == R.id.card_fashion_deatails4_img_container) {
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getProfilePic() != null)
                    profilePic = hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getProfilePic();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getName() != null)
                    name = hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getName();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice() != null)
                    price = hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getName() != null)
                    title = hits.getHits().get(getAdapterPosition() - 4).get_source().getName();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole() != null &&
                            hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole().size() != 0)
                        role.addAll(hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole());
                }
                shopByVideoInterafce.onGalleryContainerClick(hits.getHits().get(getAdapterPosition() - 4).get_source().getId(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getSize(), "abhiint",
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getProductInfo(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getSlug(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getImageGallery(),
                        profilePic, role, name, title, new CommonAllProductPage());
            } else if ((v.getId() == R.id.header_linear) || (v.getId() == R.id.profile_image)) {
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb() != null && hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().size() != 0) {
                    shopByVideoInterafce.fromCelebToCelebPage(hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getId());
                }
            }
        }
    }

    public class ViewHolder15 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details4_plus_img1, card_fashion_details4_plus_img2, card_fashion_details4_plus_img3,
                card_fashion_details4_plus_img4, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name, card_fashion_details4_plus_text, card_fashion_details4_plus_txt;
        RelativeLayout card_fashion_details4_plus_img_container;
        LinearLayout header_linear;
        ImageButton ib_like, ib_bookmark, card_footer_share;

        public ViewHolder15(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details4_plus_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img1);
            card_fashion_details4_plus_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img2);
            card_fashion_details4_plus_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img3);
            card_fashion_details4_plus_img4 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img4);
            card_fashion_details4_plus_txt = (TextView) itemView.findViewById(R.id.card_fashion_details4_plus_txt);
            card_fashion_details4_plus_text = (TextView) itemView.findViewById(R.id.card_fashion_details4_plus_text);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_fashion_details4_plus_img_container = (RelativeLayout) itemView.findViewById(R.id.card_fashion_details4_plus_img_container);
            card_fashion_details4_plus_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);

            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("singleImgProint", hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic() + "SS");
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 4).get_source().getImageGallery().get(0).trim()
                            , context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("singleImgProint", hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic() + "SS");
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                checkForLoggedIn(getAdapterPosition());
            } else if (v.getId() == R.id.card_fashion_details4_plus_img_container) {
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getProfilePic() != null)
                    profilePic = hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getProfilePic();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getName() != null)
                    name = hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getName();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice() != null)
                    price = hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getName() != null)
                    title = hits.getHits().get(getAdapterPosition() - 4).get_source().getName();
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole() != null &&
                            hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole().size() != 0)
                        role.addAll(hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getRole());
                }
                shopByVideoInterafce.onGalleryContainerClick(hits.getHits().get(getAdapterPosition() - 4).get_source().getId(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getSize(), "abhiint",
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getPrice(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getProductInfo(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getSlug(),
                        hits.getHits().get(getAdapterPosition() - 4).get_source().getImageGallery(),
                        profilePic, role, name, title, new CommonAllProductPage());
            } else if ((v.getId() == R.id.header_linear) || (v.getId() == R.id.profile_image)) {
                if (hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb() != null && hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().size() != 0) {
                    shopByVideoInterafce.fromCelebToCelebPage(hits.getHits().get(getAdapterPosition() - 4).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getId());
                }
            }
        }
    }

    public class ViewHolder20 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details1_img, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name, card_fashion_details1_txt;
        ImageButton ib_like, ib_bookmark, card_footer_share;

        public ViewHolder20(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details1_img = (ImageView) itemView.findViewById(R.id.card_fashion_details1_img);
            card_fashion_details1_txt = (TextView) itemView.findViewById(R.id.card_fashion_details1_txt);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            // card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            followbtn.setOnClickListener(this);

            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("singleImgProint", hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic() + "SS");
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 4).get_source().getImageGallery().get(0).trim(), context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("singleImgProint", hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic() + "SS");
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition() - 4).get_source().getProfilePic());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();

            }
        }
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(context).load(carouselImg.get(position).trim())
                    .transform(new FaceCenterCrop())
                    .into(imageView);
            //imageView.setImageURI(Uri.parse(carouselImg.get(position)));
        }
    };

    public void checkForLoggedIn(int pos) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            Toast.makeText(context, "You need to first Login", Toast.LENGTH_SHORT).show();
            return;
        }
        shopByVideoInterafce.onBuyClick(hits.getHits().get(pos - 4).get_source().getId(),
                hits.getHits().get(pos - 4).get_source().getSize(), "abhiint",
                hits.getHits().get(pos - 4).get_source().getPrice(),
                hits.getHits().get(pos - 4).get_source().getProfilePic(),
                hits.getHits().get(pos - 4).get_source().getProductInfo(),
                hits.getHits().get(pos - 4).get_source().getSlug(),
                hits.getHits().get(pos - 4).get_source().getImageGallery(), new ProductOnClick());
    }
}
