package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieData;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.HomeActivity.ShopByVideoData;
import com.flikster.R;
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

public class CelebrityBioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    CelebrityBioShopByVideoViewHolder celebrityBioShopByVideoViewHolder;
    CelebrityBioAdapterFamilyViewHolder celebrityBioAdapterFamilyViewHolder;
    CelebrityBioAdapterFilmographyViewHolder celebrityBioAdapterFilmographyViewHolder;
    CelebrityBioAdapterPeersViewHolder celebrityBioAdapterPeersViewHolder;
    CelebrityBioAdapterImagesViewHolder celebrityBioAdapterImagesViewHolder;
    ApiInterface apiInterface;
    ShopByVideoData.ShopByVideoInnerData shopByVideoInnerData;
    MovieData.MovieInnerData movieInnerData;
    List<CelebBioImagesData.CelebBioImagesDataInner> celebAllImages = new ArrayList<>();
    Boolean biographyBoolean = true;
    String biography;
    String coverpic;
    String name;
    ArrayList<String> role = new ArrayList<>();
    String dateOfBirth;
    String placeOfBirth;
    String userId;
    String entityId;
    String slug;
    CelebrityFragmentBio.CelebToShopByVideoInterface celebToShopByVideoInterface;


    public CelebrityBioAdapter(Context context, FragmentManager fragmentManager, String coverpic, String biography,
                               String dateOfBirth, ArrayList<String> role, String placeOfBirth,
                               String name, String userId, String entityId, String slug, CelebrityFragmentBio.CelebToShopByVideoInterface celebToShopByVideoInterface) {
        this.userId = userId;
        this.entityId = entityId;
        this.context = context;
        this.fragmentManager = fragmentManager;
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(5);
        type.add(6);
        type.add(8);
        type.add(9);
        this.placeOfBirth = placeOfBirth;
        this.coverpic = coverpic;
        this.name = name;
        this.role = role;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
        this.slug = slug;
        this.celebToShopByVideoInterface = celebToShopByVideoInterface;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celeb_bio_profile, parent, false);
            return new CelebrityBioAdapter.ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new CelebrityBioAdapter.ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new CelebrityBioAdapter.ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_news, parent, false);
            return new CelebrityBioAdapter.ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new CelebrityBioAdapter.ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new CelebrityBioAdapter.ViewHolder6(view);
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new CelebrityBioAdapter.ViewHolder7(view);
        } else if (viewType == 8) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_awards, parent, false);
            return new CelebrityBioAdapter.ViewHolder8(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_upcoming, parent, false);
            return new CelebrityBioAdapter.ViewHolder9(view);
        }
    }

    public String formatDOB(String dob) {
        String subString = dob.substring(3, dob.indexOf("GMT") - 9);
        return subString;
    }

    public String formatRole() {
        String roleString = "";
        try {
            for (int i = 0; i < role.size(); i++) {
                if (i < role.size() - 1)
                    roleString = roleString + role.get(i) + " , ";
                else
                    roleString = roleString + role.get(i);
            }
        } catch (Exception e) {

        }

        return roleString;
    }

    /*public Spannable formatBiography() {
        String storyLine = items.get(0).getBiography();
        Spannable spannable = null;
        if (storyLine.length() > 200) {
            storyLine = storyLine.substring(0, 190) + " ...Click To Expand";
            spannable = new SpannableString(storyLine);
            spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorAccent)), 190
                    , storyLine.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        return spannable;
    }

    public void storyLineOnClick(CelebrityBioAdapter.ViewHolder1 holder) {
        String storyLine = items.get(0).getBiography();
        if (storyLine.length() > 200 && biographyBoolean == true) {
            holder.card_celebrity_bio_profile_biography.setText(items.get(0).getBiography());
            biographyBoolean = false;
        } else if (storyLine.length() > 200 && biographyBoolean == false) {
            holder.card_celebrity_bio_profile_biography.setText(formatBiography());
            biographyBoolean = true;
        }
    }
*/
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
            /*if (items.size() != 0){
                try {
                    if (biography != null && !items.get(0).getBiography().isEmpty() && items.get(0).getBiography().length() != 0) {
                        if (items.get(0).getBiography().length() <= 200)
                            ((ViewHolder1) holder).card_celebrity_bio_profile_biography.setText(items.get(0).getBiography());
                        else if (items.get(0).getBiography().length() > 200)
                            ((ViewHolder1) holder).card_celebrity_bio_profile_biography.setText(formatBiography(), TextView.BufferType.SPANNABLE);
                    }

                    ((ViewHolder1) holder).card_celebrity_bio_profile_biography.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            storyLineOnClick(((ViewHolder1) holder));
                        }
                    });
                    Log.e("DOB", "Shiva" + formatDOB(items.get(0).getDateOfBirth()));
                    ((ViewHolder1) holder).card_celebrity_bio_profile_dob.setText(formatDOB(items.get(0).getDateOfBirth() + ""));
                    ((ViewHolder1) holder).card_celebrity_bio_profile_name.setText(items.get(0).getName() + "");
                    ((ViewHolder1) holder).card_celebrity_bio_profile_pob.setText(items.get(0).getPlaceOfBirth() + "");
                    ((ViewHolder1) holder).card_celebrity_bio_profile_role.setText(formatRole() + "");
//                    Glide.with(context).load(items.get(0).getProfilePic()).asBitmap().into(((ViewHolder1) holder).card_celebrity_bio_profile_profilepic);
                    Glide.with(context).load(items.get(0).getCoverPic()).asBitmap().into(((ViewHolder1) holder).card_celebrity_bio_profile_coverpic);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/


            /*Log.e("CELEB_BIO_PAGE", "CELEB_BIO_PAGE");
            Log.e("ENTITY_ID", entityId);

            userId = SharedPrefsUtil.getStringPreference(context, "USER_ID");*/

            ((ViewHolder1) holder).card_celebrity_bio_profile_biography.setText(Html.fromHtml(biography));
            ((ViewHolder1) holder).card_celebrity_bio_profile_name.setText(name);
            ((ViewHolder1) holder).card_celebrity_bio_profile_pob.setText(placeOfBirth);
            if (dateOfBirth != null)
                ((ViewHolder1) holder).card_celebrity_bio_profile_dob.setText(dateOfBirth);
            ((ViewHolder1) holder).card_celebrity_bio_profile_role.setText(formatRole());
            Glide.with(context).load(coverpic)
                    .transform(new FaceCenterCrop())
                    .into(((ViewHolder1) holder).card_celebrity_bio_profile_coverpic);


            if (userId != null && !userId.isEmpty()) {
                new PostRetrofit().checkForLikesCount("like", userId, entityId, ((ViewHolder1) holder).card_celeb_bio_likes_txt, context);
                new PostRetrofit().checkForFollowersCount("follow", userId, entityId, ((ViewHolder1) holder).card_celeb_bio_followers_txt, context);
                new PostRetrofit().checkForFollow("follow", userId, entityId, ((ViewHolder1) holder).followbtn, context);
            } else {
                new PostRetrofit().checkForLikesCount("like", "null", entityId, ((ViewHolder1) holder).card_celeb_bio_likes_txt, context);
                new PostRetrofit().checkForFollowersCount("follow", "null", entityId, ((ViewHolder1) holder).card_celeb_bio_followers_txt, context);
            }

        } else if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setText("Videos");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            initShopByVideoRetrofit(((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler, ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title);
        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_title.setText("Images");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            initImagesRetrofit(((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler, ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_title);
        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_title.setText("Filmography");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            initPeersRetrofit(((ViewHolder5) holder).fragment_common_recyclerview_with_tv_recycler, "filmography", ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_title);
        } else if (holder.getItemViewType() == 6) {
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_title.setText("Peers");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            initPeersRetrofit(((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler, "peers", ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_title);
        } /*else if (holder.getItemViewType() == 7) {
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_title.setVisibility(View.VISIBLE);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_title.setText("Family");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterFamilyViewHolder = new CelebrityBioAdapterFamilyViewHolder();
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterFamilyViewHolder);
        }*/
    }

    private void initImagesRetrofit(final RecyclerView fragment_common_recyclerview_with_tv_recycler, final TextView headerText) {
        CelebBioImagesData celebBioImagesData = new CelebBioImagesData(slug);
        apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/search-ms/collectionsByCeleb/").create(ApiInterface.class);
        Call<CelebBioImagesData> call = apiInterface.postForCelebImageBySlug(celebBioImagesData);
        call.enqueue(new Callback<CelebBioImagesData>() {
            @Override
            public void onResponse(Call<CelebBioImagesData> call, Response<CelebBioImagesData> response) {
                Log.e("check msg", "" + response.body().getStatusCode());
                Log.e("check msg", "" + response.body().getData());
                celebAllImages = response.body().getData();
                celebrityBioAdapterImagesViewHolder = new CelebrityBioAdapterImagesViewHolder(context, celebAllImages);
                fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterImagesViewHolder);
            }

            @Override
            public void onFailure(Call<CelebBioImagesData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initPeersRetrofit(final RecyclerView recyclerView, final String type, final TextView headertxt) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/movies/_search/").create(ApiInterface.class);
        Call<MovieData> call = apiInterface.getMovieData("http://apiservice-ec.flikster.com/movies/_search?pretty=true&q=tags:\"" + slug + "\"");
        call.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                movieInnerData = response.body().getHits();
                if ("peers".equals(type)) {
//                    if (movieInnerData.getHits().size() != 0) {
                        celebrityBioAdapterPeersViewHolder = new CelebrityBioAdapterPeersViewHolder(context, movieInnerData, celebToShopByVideoInterface);
                        recyclerView.setAdapter(celebrityBioAdapterPeersViewHolder);
                    /*} else {
                        recyclerView.setVisibility(View.GONE);
                        headertxt.setVisibility(View.GONE);
                    }*/

                } else if ("filmography".equals(type)) {
                    celebrityBioAdapterFilmographyViewHolder = new CelebrityBioAdapterFilmographyViewHolder(context, movieInnerData, celebToShopByVideoInterface);
                    recyclerView.setAdapter(celebrityBioAdapterFilmographyViewHolder);
                }
                /*else {
                    recyclerView.setVisibility(View.GONE);
                    headertxt.setVisibility(View.GONE);
                }*/
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initShopByVideoRetrofit(final RecyclerView recyclerView, final TextView headerText) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/shopbyvideos/").create(ApiInterface.class);
        Call<ShopByVideoData> call = apiInterface.getShopByVideo("http://apiservice-ec.flikster.com/shopbyvideos/_search?pretty=true&q=\"" + slug + "\"");
        call.enqueue(new Callback<ShopByVideoData>() {
            @Override
            public void onResponse(Call<ShopByVideoData> call, Response<ShopByVideoData> response) {
                shopByVideoInnerData = response.body().getHits();
//                if (shopByVideoInnerData.getHits().size() != 0) {
                    celebrityBioShopByVideoViewHolder = new CelebrityBioShopByVideoViewHolder(context, fragmentManager, shopByVideoInnerData, celebToShopByVideoInterface);
                    recyclerView.setAdapter(celebrityBioShopByVideoViewHolder);
                    headerText.setVisibility(View.VISIBLE);
                /*} else {
                    recyclerView.setVisibility(View.GONE);
                    headerText.setVisibility(View.GONE);
                }*/

            }

            @Override
            public void onFailure(Call<ShopByVideoData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView card_celebrity_bio_profile_coverpic;
        Button followbtn;
        TextView card_celebrity_bio_profile_pob, card_celebrity_bio_profile_dob, card_celebrity_bio_profile_role, card_celebrity_bio_profile_biography, card_celebrity_bio_profile_name;

        TextView card_celeb_bio_likes_txt, card_celeb_bio_followers_txt;

        public ViewHolder1(View itemView) {
            super(itemView);
            card_celebrity_bio_profile_coverpic = (ImageView) itemView.findViewById(R.id.card_celeb_bio_profile_coverpic);
//            card_celebrity_bio_profile_profilepic = (ImageView) itemView.findViewById(R.id.card_celebrity_bio_profile_profilepic);
            card_celebrity_bio_profile_pob = (TextView) itemView.findViewById(R.id.card_celeb_bio_location);
            card_celebrity_bio_profile_dob = (TextView) itemView.findViewById(R.id.card_celeb_bio_dob);
            card_celebrity_bio_profile_role = (TextView) itemView.findViewById(R.id.card_celeb_bio_role);
            card_celebrity_bio_profile_biography = (TextView) itemView.findViewById(R.id.card_celeb_bio_profile_biography);
            card_celebrity_bio_profile_name = (TextView) itemView.findViewById(R.id.card_celeb_bio_profile_name);

            card_celeb_bio_likes_txt = (TextView) itemView.findViewById(R.id.card_celeb_bio_likes_txt);
            card_celeb_bio_followers_txt = (TextView) itemView.findViewById(R.id.card_celeb_bio_followers_txt);


            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            followbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.followOrUnFollow(context, followbtn, userId, entityId);
                }
            });
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder2(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            fragment_common_recyclerview_with_tv_recycler.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.white_grey_radius_border));
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder3(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder {
        public ViewHolder4(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder5(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder6(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }
    }

    public class ViewHolder7 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder7(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }
    }

    public class ViewHolder8 extends RecyclerView.ViewHolder {
        public ViewHolder8(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder9 extends RecyclerView.ViewHolder {
        public ViewHolder9(View itemView) {
            super(itemView);
        }
    }

}
