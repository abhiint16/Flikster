package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
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
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreData;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreInnerData;
import com.flikster.HomeActivity.FashionFragment.FashionType.CommonAllProductPage.CommonAllProductPage;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.HomeActivity.ProfileCollectionRecyclerItemAdapter;
import com.flikster.R;
import com.flikster.HomeActivity.StealStyleViewHolder;
import com.flikster.SharedPref.SharedPref;
import com.flikster.Util.Common;
import com.flikster.Util.SharedPrefsUtil;
import com.rohitarya.glide.facedetection.transformation.FaceCenterCrop;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 12-10-2017.
 */

public class CelebrityStoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    ProfileCollectionRecyclerItemAdapter profileCollectionRecyclerItemAdapter;
    CelebrityBioShopByVideoViewHolder celebrityBioShopByVideoViewHolder;
    StealStyleViewHolder stealStyleViewHolder;
    RecyclerView.LayoutManager layoutManager2;
    String biography;
    String coverpic;
    String dateOfBirth;
    String placeOfBirth;
    AllStoreInnerData hits;
    String entityId;
    String profilePic = "";
    String name = "";
    String title = "";
    List<String> role = new ArrayList<>();
    String price = "";
    String userId;
    String slug;
    ApiInterface apiInterface;
    int count=4;
    CelebrityFragment.CelebItemClickInterface celebItemClickInterface;
    Boolean followBoolean;
    String followString="";

    public CelebrityStoreAdapter(Context context, FragmentManager fragmentManager, String coverpic, String biography,
                                 String dateOfBirth, ArrayList<String> role, String placeOfBirth, String name,
                                 AllStoreInnerData hits, String userId, String entityId,String slug) {
        /*new PostRetrofit().checkForFollow("follow", userId, entityId, ((ViewHolder1) holder).followbtn, context);*/
        this.userId = userId;
        this.entityId = entityId;
        this.context = context;
        this.slug=slug;
        celebItemClickInterface = (CelebrityFragment.CelebItemClickInterface) context;
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
        this.placeOfBirth = placeOfBirth;
        this.coverpic = coverpic;
        this.name = name;
        this.role = role;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
        this.hits = hits;
    }

    public void updateFollow()
    {
        notifyItemChanged(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celeb_feed_profile, parent, false);
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
        }
        else if(viewType==300)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hor_last_item_load_more, parent, false);
            return new ViewHolder300(view);
        }else
            return null;
    }

    public String formatRole(List<String> role) {
        String roleString = "";
        try {
            for (int i = 0; i < role.size(); i++) {
                roleString = roleString + ", " + role.get(i);
            }
        } catch (Exception e) {
            Log.e("Error", "Celeb Store Role  size null");
        }

        return roleString;
    }

    public String formatRole() {
        String genre = "";
        try {
            for (int i = 0; i < this.role.size(); i++) {
                if (i < genre.length() - 1)
                    genre = genre + this.role.get(i) + " | ";
                else
                    genre = genre + this.role.get(i);
            }
        } catch (Exception e) {
            Log.e("Error", "Celeb Store Role size null");
        }

        return genre;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*if (SharedPrefsUtil.getStringPreference(context, userId) != null && !SharedPrefsUtil.getStringPreference(context, userId).isEmpty()) {
            userId = SharedPrefsUtil.getStringPreference(context, "USER_ID");
        } else {
            userId = "";
        }*/

        if (holder.getItemViewType() == 0) {
            if (name != null && !name.isEmpty()) {
                ((ViewHolder0) holder).card_celebrity_feed_profile_name.setText(name);
                new PostRetrofit().checkForFollow("follow", userId, entityId, ((ViewHolder0) holder).followbtn, context);
            }

            if (userId != null && !userId.isEmpty()) {
                new PostRetrofit().checkForLikesCount("like", userId, entityId, ((ViewHolder0) holder).card_celeb_feed_profile_likes_txt, context);
                new PostRetrofit().checkForFollowersCount("follow", userId, entityId, ((ViewHolder0) holder).card_celeb_feed_profile_followers_txt, context);
                /*if ("follow".equals(followString))
                {
                    if (followBoolean==true)
                    {
                        ((ViewHolder0) holder).followbtn.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                        ((ViewHolder0) holder).followbtn.setBackground(context.getResources().getDrawable(R.drawable.corner_rounded_pink));
                        ((ViewHolder0) holder).followbtn.setText("Following");
                        ((ViewHolder0) holder).followbtn.setTextColor(context.getResources().getColor(R.color.white));
                    }
                    else if (followBoolean==false)
                    {
                        ((ViewHolder0) holder).followbtn.setBackgroundColor(context.getResources().getColor(R.color.black));
                        ((ViewHolder0) holder).followbtn.setBackground(context.getResources().getDrawable(R.drawable.corner_rounded));
                        ((ViewHolder0) holder).followbtn.setText("follow");
                        ((ViewHolder0) holder).followbtn.setTextColor(context.getResources().getColor(R.color.black));
                    }
                }
                else
                {*/
                    new PostRetrofit().checkForFollow("follow", userId, entityId, ((ViewHolder0) holder).followbtn, context);
                /*}*/
            } else {
                new PostRetrofit().checkForLikesCount("like", "null", entityId, ((ViewHolder0) holder).card_celeb_feed_profile_likes_txt, context);
                new PostRetrofit().checkForFollowersCount("follow", "null", entityId, ((ViewHolder0) holder).card_celeb_feed_profile_followers_txt, context);
            }

            ((ViewHolder0) holder).card_celebrity_feed_profile_role.setText(formatRole());
            if (coverpic != null && !coverpic.isEmpty()) {
                Glide.with(context).load(coverpic).asBitmap()
                        .transform(new FaceCenterCrop())
                        .into(((ViewHolder0) holder).card_celebrity_feed_profile_coverpic);
            }
        } else if (holder.getItemViewType() == 1) {
            ((ViewHolder1) holder).followbtn.setText("BUY");
            ((ViewHolder1) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder1) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder1) holder).card_fashion_details1_txt.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder1) holder).card_description_with_price_price.setText(hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getProductDescription() != null) {
                ((ViewHolder1) holder).card_description_with_price_title.setText(Html.fromHtml(hits.getHits().get(position).get_source().getProductDescription()));
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null && hits.getHits().get(position).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder1) holder).tv_tag_name.setText(hits.getHits().get(position).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder1) holder).profile_image);
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder1) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder1) holder).card_fashion_details1_img);
            }
        } else if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).followbtn.setText("BUY");
            ((ViewHolder2) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder2) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder2) holder).card_fashion_details2_txt.setText(Html.fromHtml(hits.getHits().get(position).get_source().getName()));
            }
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder2) holder).card_description_with_price_price.setText(Html.fromHtml(hits.getHits().get(position).get_source().getPrice()) + " /-");
            }
            if (hits.getHits().get(position).get_source().getProductDescription() != null) {
                ((ViewHolder2) holder).card_description_with_price_title.setText(Html.fromHtml(hits.getHits().get(position).get_source().getProductDescription()));
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null && hits.getHits().get(position).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder2) holder).tv_tag_name.setText(Html.fromHtml(hits.getHits().get(position).get_source().getCeleb().get(0).getName()));
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder2) holder).profile_image);
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder2) holder).tv_tag_desc.setText(Html.fromHtml(formatRole(hits.getHits().get(position).get_source().getCeleb().get(0).getRole())));
            }
            if (hits.getHits().get(position).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder2) holder).card_fashion_details2_img1);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(1).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder2) holder).card_fashion_details2_img2);
            }
        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3) holder).followbtn.setText("BUY");
            ((ViewHolder3) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder3) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder3) holder).card_fashion_details3_txt.setText(Html.fromHtml(hits.getHits().get(position).get_source().getName()));
            }
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder3) holder).card_description_with_price_price.setText(hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getProductDescription() != null) {
                ((ViewHolder3) holder).card_description_with_price_title.setText(Html.fromHtml((hits.getHits().get(position).get_source().getProductDescription())));
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null && hits.getHits().get(position).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder3) holder).tv_tag_name.setText(Html.fromHtml(hits.getHits().get(position).get_source().getCeleb().get(0).getName()));
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder3) holder).profile_image);
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder3) holder).tv_tag_desc.setText(Html.fromHtml(formatRole(hits.getHits().get(position).get_source().getCeleb().get(0).getRole())));
            }
            if (hits.getHits().get(position).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder3) holder).card_fashion_details3_img1);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(1).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder3) holder).card_fashion_details3_img2);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(2).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder3) holder).card_fashion_details3_img3);
            }
        } else if (holder.getItemViewType() == 4) {
            ((ViewHolder4) holder).followbtn.setText("BUY");
            ((ViewHolder4) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder4) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder4) holder).card_fashion_details4_txt.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder4) holder).card_description_with_price_price.setText(hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getProductDescription() != null) {
                ((ViewHolder4) holder).card_description_with_price_title.setText(Html.fromHtml(hits.getHits().get(position).get_source().getProductDescription()));
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null && hits.getHits().get(position).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder4) holder).tv_tag_name.setText(Html.fromHtml(hits.getHits().get(position).get_source().getCeleb().get(0).getName()));
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder4) holder).profile_image);
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder4) holder).tv_tag_desc.setText(Html.fromHtml(formatRole(hits.getHits().get(position).get_source().getCeleb().get(0).getRole())));
            }
            if (hits.getHits().get(position).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder4) holder).card_fashion_details4_img1);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(1).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder4) holder).card_fashion_details4_img2);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(2).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder4) holder).card_fashion_details4_img3);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(3).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder4) holder).card_fashion_details4_img4);
            }
        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).followbtn.setText("BUY");
            ((ViewHolder5) holder).card_header_container.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder5) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder5) holder).card_fashion_details4_plus_txt.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder5) holder).card_description_with_price_price.setText(hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getProductDescription() != null) {
                ((ViewHolder5) holder).card_description_with_price_title.setText(Html.fromHtml(hits.getHits().get(position).get_source().getProductDescription()));
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null && hits.getHits().get(position).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder5) holder).tv_tag_name.setText(Html.fromHtml(hits.getHits().get(position).get_source().getCeleb().get(0).getName()));
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap()
                            .into(((ViewHolder5) holder).profile_image);
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder5) holder).tv_tag_desc.setText(Html.fromHtml(formatRole(hits.getHits().get(position).get_source().getCeleb().get(0).getRole())));
            }
            if (hits.getHits().get(position).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img1);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(1).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img2);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(2).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img3);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(3).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img4);
            }
            ((ViewHolder5) holder).card_fashion_details4_plus_text.setText("+ " + (hits.getHits().get(position).get_source().getImageGallery().size() - 4));
        } else if (holder.getItemViewType() == 100) {
            ((ViewHolder100) holder).activity_no_comments_tv.setText("No Contents Available!");
        }
        else if (holder.getItemViewType()==300)
        {
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) context.getResources().getDimension(R.dimen.fifty));
            ((ViewHolder300)holder).hor_last_item_load_more_container.setLayoutParams(params);
            loadMore();
        }
    }

    private void loadMore()
    {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/products/_search/").create(ApiInterface.class);
        Call<AllStoreData> call = apiInterface.getCelebMovieStoreData(true, 4,count, "tags:\"" + slug + "\"");
        call.enqueue(new Callback<AllStoreData>() {
            @Override
            public void onResponse(Call<AllStoreData> call, Response<AllStoreData> response) {
                count=count+4;
                hits.getHits().addAll(response.body().getHits().getHits());
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AllStoreData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (hits.getHits().size()==0||hits.getHits()==null)
            return 1;
        if ((hits.getTotal()==hits.getHits().size()))
            return hits.getHits().size();
        return hits.getHits().size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        /*if (position == 0)
            return 0;
        else */if (hits.getHits().size()!=0&&(position==hits.getHits().size()))
            return 300;
        else {
            if (hits.getHits().size() != 0 && hits.getHits() != null) {
                if (hits.getHits().get(position).get_source().getImageGallery() != null && hits.getHits().get(position).get_source().getImageGallery().size() != 0) {
                    switch (hits.getHits().get(position).get_source().getImageGallery().size()) {
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
        ImageView card_celebrity_feed_profile_coverpic;
        TextView card_celebrity_feed_profile_name, card_celebrity_feed_profile_role;
        Button followbtn;

        TextView card_celeb_feed_profile_likes_txt, card_celeb_feed_profile_followers_txt;

        public ViewHolder0(View itemView) {
            super(itemView);
            card_celebrity_feed_profile_coverpic = (ImageView) itemView.findViewById(R.id.card_celeb_feed_profile_coverpic);
            card_celebrity_feed_profile_name = (TextView) itemView.findViewById(R.id.card_celeb_feed_profile_name);
            card_celebrity_feed_profile_role = (TextView) itemView.findViewById(R.id.card_celeb_feed_profile_role);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);


            card_celeb_feed_profile_likes_txt = (TextView) itemView.findViewById(R.id.card_celeb_feed_profile_likes_txt);
            card_celeb_feed_profile_followers_txt = (TextView) itemView.findViewById(R.id.card_celeb_feed_profile_followers_txt);

            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.followOrUnFollow(context, followbtn, userId, entityId);
                    if (followbtn.getText().toString().equals("follow"))
                    {
                        celebItemClickInterface.followBtnChange(true,3);
                    }
                    else
                    {
                        celebItemClickInterface.followBtnChange(false,3);
                    }
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
        LinearLayout card_fashion_details1_img_container,card_description_with_price_container;

        public ViewHolder1(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            card_fashion_details1_txt = (TextView) itemView.findViewById(R.id.card_fashion_details1_txt);
            card_fashion_details1_img = (ImageView) itemView.findViewById(R.id.card_fashion_details1_img);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_description_with_price_container=(LinearLayout)itemView.findViewById(R.id.card_description_with_price_container);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_fashion_details1_img_container = (LinearLayout) itemView.findViewById(R.id.card_fashion_details1_img_container);
            card_fashion_details1_img_container.setOnClickListener(this);
            card_description_with_price_container.setOnClickListener(this);
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.followOrUnFollow(context, followbtn, userId, entityId);
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.card_fashion_details1_img_container||v.getId()==R.id.card_description_with_price_container) {
                cardContainerClick(getAdapterPosition());
            }
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details2_img1, card_fashion_details2_img2, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price, tv_tag_desc, tv_tag_name,
                card_fashion_details2_txt;
        RelativeLayout card_header_container;
        LinearLayout card_fashion_details2_img_container,card_description_with_price_container;

        public ViewHolder2(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            card_fashion_details2_txt = (TextView) itemView.findViewById(R.id.card_fashion_details2_txt);
            card_fashion_details2_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details2_img1);
            card_fashion_details2_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details2_img2);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_description_with_price_container=(LinearLayout)itemView.findViewById(R.id.card_description_with_price_container);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_fashion_details2_img_container = (LinearLayout) itemView.findViewById(R.id.card_fashion_details2_img_container);
            card_fashion_details2_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);
            card_description_with_price_container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();

            } else if (v.getId() == R.id.card_fashion_details2_img_container||v.getId()==R.id.card_description_with_price_container) {
                cardContainerClick(getAdapterPosition());
            }

        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details3_img1, card_fashion_details3_img2, card_fashion_details3_img3, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price, tv_tag_desc, tv_tag_name,
                card_fashion_details3_txt;
        RelativeLayout card_header_container;
        LinearLayout card_my_style_one_big_img_container,card_description_with_price_container;

        public ViewHolder3(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_header_container = (RelativeLayout) itemView.findViewById(R.id.card_header_container);
            card_fashion_details3_txt = (TextView) itemView.findViewById(R.id.card_fashion_details3_txt);
            card_fashion_details3_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img1);
            card_fashion_details3_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img2);
            card_fashion_details3_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img3);
            card_description_with_price_container=(LinearLayout)itemView.findViewById(R.id.card_description_with_price_container);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_my_style_one_big_img_container = (LinearLayout) itemView.findViewById(R.id.card_my_style_one_big_img_container);
            card_my_style_one_big_img_container.setOnClickListener(this);
            card_description_with_price_container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();

            } else if (v.getId() == R.id.card_my_style_one_big_img_container||v.getId()==R.id.card_description_with_price_container) {
                cardContainerClick(getAdapterPosition());
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
        LinearLayout card_fashion_deatails4_img_container,card_description_with_price_container;

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
            card_description_with_price_container=(LinearLayout)itemView.findViewById(R.id.card_description_with_price_container);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            // card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_fashion_deatails4_img_container = (LinearLayout) itemView.findViewById(R.id.card_fashion_deatails4_img_container);
            card_fashion_deatails4_img_container.setOnClickListener(this);
            card_description_with_price_container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();
            } else if (v.getId() == R.id.card_fashion_deatails4_img_container||v.getId()==R.id.card_description_with_price_container) {
                cardContainerClick(getAdapterPosition());
            }
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details4_plus_img1, card_fashion_details4_plus_img2, card_fashion_details4_plus_img3,
                card_fashion_details4_plus_img4, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price, tv_tag_desc, tv_tag_name, card_fashion_details4_plus_text,
                card_fashion_details4_plus_txt;
        RelativeLayout card_header_container, card_fashion_details4_plus_img_container;
        LinearLayout card_description_with_price_container;

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
            card_description_with_price_container=(LinearLayout)itemView.findViewById(R.id.card_description_with_price_container);
            card_fashion_details4_plus_text = (TextView) itemView.findViewById(R.id.card_fashion_details4_plus_text);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            // card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_fashion_details4_plus_img_container = (RelativeLayout) itemView.findViewById(R.id.card_fashion_details4_plus_img_container);
            card_fashion_details4_plus_img_container.setOnClickListener(this);
            card_description_with_price_container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();
            } else if (v.getId() == R.id.card_fashion_details4_plus_img_container||v.getId()==R.id.card_description_with_price_container) {
                cardContainerClick(getAdapterPosition());
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

    public class ViewHolder300 extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout hor_last_item_load_more_container;
        public ViewHolder300(View itemView) {
            super(itemView);
            hor_last_item_load_more_container=(LinearLayout)itemView.findViewById(R.id.hor_last_item_load_more_container);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public void cardContainerClick(int pos) {
        if (hits.getHits().get(pos).get_source().getCeleb().get(0).getProfilePic() != null)
            profilePic = hits.getHits().get(pos).get_source().getCeleb().get(0).getProfilePic();
        if (hits.getHits().get(pos).get_source().getCeleb().get(0).getName() != null)
            name = hits.getHits().get(pos).get_source().getCeleb().get(0).getName();
        if (hits.getHits().get(pos).get_source().getPrice() != null)
            price = hits.getHits().get(pos).get_source().getPrice();
        if (hits.getHits().get(pos).get_source().getName() != null)
            title = hits.getHits().get(pos).get_source().getName();
        if (hits.getHits().get(pos).get_source().getCeleb() != null) {
            if (hits.getHits().get(pos).get_source().getCeleb().get(0).getRole() != null &&
                    hits.getHits().get(pos).get_source().getCeleb().get(0).getRole().size() != 0)
                role.addAll(hits.getHits().get(pos).get_source().getCeleb().get(0).getRole());
        }
        celebItemClickInterface.onGalleryContainerClick(hits.getHits().get(pos).get_source().getId(),
                hits.getHits().get(pos).get_source().getSize(), "abhiint",
                hits.getHits().get(pos).get_source().getPrice(),
                hits.getHits().get(pos).get_source().getProfilePic(),
                hits.getHits().get(pos).get_source().getProductInfo(),
                hits.getHits().get(pos).get_source().getSlug(),
                hits.getHits().get(pos).get_source().getImageGallery(),
                profilePic, role, name, title, new CommonAllProductPage());
    }


}
