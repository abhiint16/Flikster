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
import com.flikster.HomeActivity.CommonFragments.VideoFragment.VideoGalleryFragment;
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
            if ("gallery".equals(searchContentDatas.get(getAdapterPosition()).getContentType()))
            {
                getRetrofitForGallery(searchContentDatas.get(getAdapterPosition()).getId());
            }
            else if ("comedy-clip".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "video-song".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "movie-making".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "interview".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "trailer".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "social-buzz".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "teasers-promos".equals(searchContentDatas.get(getAdapterPosition()).getContentType()))
            {
                getRetrofitForVideo(searchContentDatas.get(getAdapterPosition()).getId());
            }
            else if ("news".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "first-look".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "poster".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "write-up".equals(searchContentDatas.get(getAdapterPosition()).getContentType())||
                    "tweet".equals(searchContentDatas.get(getAdapterPosition()).getContentType()))
            {
                getRetrofitForNews(searchContentDatas.get(getAdapterPosition()).getId());
            }
        }

        public void getRetrofitForGallery(String id)
        {
            apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/content-ms/getContentById/").create(ApiInterface.class);
            Call<SearchGalleryData> call = apiInterface.getSearchGalleryData("http://apiservice.flikster.com/v3/content-ms/getContentById/" + id);
            call.enqueue(new Callback<SearchGalleryData>() {
                @Override
                public void onResponse(Call<SearchGalleryData> call, Response<SearchGalleryData> response) {
                    if (response.body().getCeleb()!= null) {
                        searchViewToFrag.galleryCardOnClick(response.body().getMedia().getGallery(),
                                response.body().getCeleb().get(0).getName(),
                                response.body().getCeleb().get(0).getProfilePic(),
                                response.body().getCeleb().get(0).getType(),
                                response.body().getTitle(),
                                new GalleryCardClick(), userId,
                                response.body().getId(),
                                response.body().getId(),response.body().getCeleb().get(0).getSlug());
                    } else {
                        searchViewToFrag.galleryCardOnClick(response.body().getMedia().getGallery(),
                                "",
                                "", "", response.body().getTitle(),
                                new GalleryCardClick(), userId,
                                response.body().getId(),
                                response.body().getId(),"");

                    }
                    }
                @Override
                public void onFailure(Call<SearchGalleryData> call, Throwable t) {
                    Log.e("vvvvvvvvvv", "vv" + call + t);
                }
            });
        }
        public void getRetrofitForNews(String id)
        {
            apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/content-ms/getContentById/").create(ApiInterface.class);
            Call<SearchGalleryData> call = apiInterface.getSearchGalleryData("http://apiservice.flikster.com/v3/content-ms/getContentById/" + id);
            call.enqueue(new Callback<SearchGalleryData>() {
                @Override
                public void onResponse(Call<SearchGalleryData> call, Response<SearchGalleryData> response) {
                    if (response.body().getCeleb() != null&&response.body().getCeleb().size()!=0) {
                        searchViewToFrag.newsCardOnClick(response.body().getCeleb().get(0).getProfilePic(),
                                response.body().getCeleb().get(0).getName(),
                                response.body().getCeleb().get(0).getType(),
                                response.body().getProfilePic(),
                                response.body().getTitle(),
                                response.body().getTitle(),
                                new NewsOnClickFragment(),
                                response.body().getContentType(),
                                userId,
                                response.body().getId(),
                                response.body().getId()
                        );
                    } else {
                        searchViewToFrag.newsCardOnClick("",
                                "",
                                "",
                                response.body().getProfilePic(),
                                response.body().getTitle(),
                                response.body().getTitle(),
                                new NewsOnClickFragment(),
                                response.body().getContentType(),
                                userId,
                                response.body().getId(),
                                response.body().getId());
                    }
                }
                @Override
                public void onFailure(Call<SearchGalleryData> call, Throwable t) {
                    Log.e("vvvvvvvvvv", "vv" + call + t);
                }
            });
        }
        public void getRetrofitForVideo(String id)
        {
            apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/content-ms/getContentById/").create(ApiInterface.class);
            Call<SearchGalleryData> call = apiInterface.getSearchGalleryData("http://apiservice.flikster.com/v3/content-ms/getContentById/" + id);
            call.enqueue(new Callback<SearchGalleryData>() {
                @Override
                public void onResponse(Call<SearchGalleryData> call, Response<SearchGalleryData> response) {
                    if (response.body().getCeleb() != null && response.body().getCeleb().size() != 0) {
                        searchViewToFrag.videoCardOnClick(response.body().getCeleb().get(0).getProfilePic(),
                                response.body().getCeleb().get(0).getName(),
                                response.body().getCeleb().get(0).getType(),
                                response.body().getProfilePic(),
                                response.body().getTitle(),
                                response.body().getTitle(),
                                response.body().getMedia().getVideo().get(0),
                                new VideoGalleryFragment(),
                                response.body().getContentType(),
                                userId,
                                response.body().getId(),
                                response.body().getId()
                        );
                    } else {
                        searchViewToFrag.videoCardOnClick("",
                                "",
                                "",
                                response.body().getProfilePic(),
                                response.body().getTitle(),
                                response.body().getTitle(),
                                response.body().getMedia().getVideo().get(0),
                                new VideoGalleryFragment(),
                                response.body().getContentType(),
                                userId,
                                response.body().getId(),
                                response.body().getId());
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
