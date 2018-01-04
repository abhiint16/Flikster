package com.flikster.HomeActivity.SearchViewFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryCardClick;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.HomeActivity.GlobalSearchGetData;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 03-01-2018.
 */

public class SearchInnerItemContentRecyclerViewHolder extends RecyclerView.Adapter<SearchInnerItemContentRecyclerViewHolder.ViewHolder> {
    Context context;
    List<GlobalSearchGetData.SearchContentData> searchContentDatas;
    SearchViewFragment.SearchViewToFrag searchViewToFrag;
    ApiInterface apiInterface;
    String userId="null";
    public SearchInnerItemContentRecyclerViewHolder(Context context, List<GlobalSearchGetData.SearchContentData> searchContentDatas,
                                                    SearchViewFragment.SearchViewToFrag searchViewToFrag) {
        this.context=context;
        this.searchContentDatas=searchContentDatas;
        this.searchViewToFrag=searchViewToFrag;

    }

    @Override
    public SearchInnerItemContentRecyclerViewHolder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_inner_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchInnerItemContentRecyclerViewHolder.ViewHolder holder, int position) {
        if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null && !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
            userId = SharedPrefsUtil.getStringPreference(context, "USER_ID");
        }
        holder.textView.setText(searchContentDatas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return searchContentDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.searchview_inner_recycler_item);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.e("bbbbb",""+searchContentDatas.get(getAdapterPosition()).getContentType());
            if ("gallery".equals(searchContentDatas.get(getAdapterPosition()).getContentType()))
            {Log.e("aaaaa",""+searchContentDatas.get(getAdapterPosition()).getContentType());
                getRetrofitForGallery(searchContentDatas.get(getAdapterPosition()).getSlug());
            }
            else if ("comedy-clip".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "video-song".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "movie-making".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "interview".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "trailer".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "teasers-promos".equals(searchContentDatas.get(getAdapterPosition()).getContentType()))
            {
                Log.e("ggggg",""+searchContentDatas.get(getAdapterPosition()).getContentType());
                Log.e("ggggg",""+searchContentDatas.get(getAdapterPosition()).getSlug());
                getRetrofitForVideo(searchContentDatas.get(getAdapterPosition()).getSlug());
            }
            else if ("news".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "first-look".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "poster".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "write-up".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "social-buzz".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "tweet".equals(searchContentDatas.get(getAdapterPosition()).getContentType()))
            {
                Log.e("ggggg",""+searchContentDatas.get(getAdapterPosition()).getContentType());
                Log.e("ggggg",""+searchContentDatas.get(getAdapterPosition()).getSlug());
                getRetrofitForNews(searchContentDatas.get(getAdapterPosition()).getSlug());
            }
            /*searchViewToFrag.newsCardOnClick("",
                    "",
                    "",
                    searchContentDatas.get(getAdapterPosition()).getProfilePic(),
                    searchContentDatas.get(getAdapterPosition()).getName(),
                    searchContentDatas.get(getAdapterPosition()).getName(),
                    new NewsOnClickFragment(),
                    searchContentDatas.get(getAdapterPosition()).getContentType(),
                    userId,
                    searchContentDatas.get(getAdapterPosition()).getId());*/
        }

        public void getRetrofitForGallery(String slug)
        {
            apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/content-ms/getContentBySlug/").create(ApiInterface.class);
            Call<SearchGalleryData> call = apiInterface.getSearchGalleryData("http://apiservice.flikster.com/v3/content-ms/getContentBySlug/" + slug);
            call.enqueue(new Callback<SearchGalleryData>() {
                @Override
                public void onResponse(Call<SearchGalleryData> call, Response<SearchGalleryData> response) {
                    if (response.body().getData().getItems().get(0).getCeleb()!= null) {
                        searchViewToFrag.galleryCardOnClick(response.body().getData().getItems().get(0).getMedia().getGallery(),
                                response.body().getData().getItems().get(0).getCeleb().get(0).getName(),
                                response.body().getData().getItems().get(0).getCeleb().get(0).getProfilePic(),
                                response.body().getData().getItems().get(0).getCeleb().get(0).getType(),
                                response.body().getData().getItems().get(0).getTitle(),
                                new GalleryCardClick(), userId,
                                response.body().getData().getItems().get(0).getId());
                    } else {
                        searchViewToFrag.galleryCardOnClick(response.body().getData().getItems().get(0).getMedia().getGallery(),
                                "",
                                "", "", response.body().getData().getItems().get(0).getTitle(),
                                new GalleryCardClick(), userId,
                                response.body().getData().getItems().get(0).getId());

                    }
                    }
                @Override
                public void onFailure(Call<SearchGalleryData> call, Throwable t) {
                    Log.e("vvvvvvvvvv", "vv" + call + t);
                }
            });
        }
        public void getRetrofitForNews(String slug)
        {
            apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/content-ms/getContentBySlug/").create(ApiInterface.class);
            Call<SearchGalleryData> call = apiInterface.getSearchGalleryData("http://apiservice.flikster.com/v3/content-ms/getContentBySlug/" + slug);
            call.enqueue(new Callback<SearchGalleryData>() {
                @Override
                public void onResponse(Call<SearchGalleryData> call, Response<SearchGalleryData> response) {
                    /*if (response != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                        testing.newsCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                                outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                                outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                                outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                                outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                                outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                                new NewsOnClickFragment(),
                                outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                                userId,
                                outerHits.getHits().get(getAdapterPosition()).get_source().getId()
                        );
                    } else {
                        testing.newsCardOnClick("",
                                "",
                                "",
                                outerHits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                                outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                                outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                                new NewsOnClickFragment(),
                                outerHits.getHits().get(getAdapterPosition()).get_source().getContentType(),
                                userId,
                                outerHits.getHits().get(getAdapterPosition()).get_source().getId());
                    }*/
                }
                @Override
                public void onFailure(Call<SearchGalleryData> call, Throwable t) {
                    Log.e("vvvvvvvvvv", "vv" + call + t);
                }
            });
        }
        public void getRetrofitForVideo(String slug)
        {
            apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/content-ms/getContentBySlug/").create(ApiInterface.class);
            Call<SearchGalleryData> call = apiInterface.getSearchGalleryData("http://apiservice.flikster.com/v3/content-ms/getContentBySlug/" + slug);
            call.enqueue(new Callback<SearchGalleryData>() {
                @Override
                public void onResponse(Call<SearchGalleryData> call, Response<SearchGalleryData> response) {
                    if (response.body().getData().getItems().get(0).getCeleb()!= null) {
                        searchViewToFrag.galleryCardOnClick(response.body().getData().getItems().get(0).getMedia().getGallery(),
                                response.body().getData().getItems().get(0).getCeleb().get(0).getName(),
                                response.body().getData().getItems().get(0).getCeleb().get(0).getProfilePic(),
                                response.body().getData().getItems().get(0).getCeleb().get(0).getType(),
                                response.body().getData().getItems().get(0).getTitle(),
                                new GalleryCardClick(), userId,
                                response.body().getData().getItems().get(0).getId());
                    } else {
                        searchViewToFrag.galleryCardOnClick(response.body().getData().getItems().get(0).getMedia().getGallery(),
                                "",
                                "", "", response.body().getData().getItems().get(0).getTitle(),
                                new GalleryCardClick(), userId,
                                response.body().getData().getItems().get(0).getId());

                    }
                }
                @Override
                public void onFailure(Call<SearchGalleryData> call, Throwable t) {
                    Log.e("vvvvvvvvvv", "vv" + call + t);
                }
            });
        }
    }
}
/*
if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
        testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
        outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
        new GalleryCardClick(), userId,
        outerHits.getHits().get(getAdapterPosition()).get_source().getId());
        } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
        testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(), outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
        new GalleryCardClick(), userId,
        outerHits.getHits().get(getAdapterPosition()).get_source().getId());
        } else {
        testing.galleryCardOnClick(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
        "",
        "", "", outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
        new GalleryCardClick(), userId,
        outerHits.getHits().get(getAdapterPosition()).get_source().getId());

        }*/
