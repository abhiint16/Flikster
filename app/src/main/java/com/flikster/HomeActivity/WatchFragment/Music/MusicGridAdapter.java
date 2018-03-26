package com.flikster.HomeActivity.WatchFragment.Music;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.FeedData;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.SongByMovieFragmentItemClick;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 01-11-2017.
 */

public class MusicGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    MusicGridFragment.WatchAudioVideoSendFromGridFrag watchAudioVideoSendFromGridFrag;
    FeedInnerData feedInnerData;
    String audioVideoLink;
    String itemType;
    int count = 10;
    String url;
    ApiInterface apiInterface;

    public MusicGridAdapter(Context context, FragmentManager fragmentManager, FeedInnerData feedInnerData, MusicGridFragment.WatchAudioVideoSendFromGridFrag watchAudioVideoSendFromGridFrag,
                            String itemType, String url) {
        this.context = context;
        this.itemType = itemType;
        this.url = url;
        this.fragmentManager = fragmentManager;
        this.feedInnerData = feedInnerData;
        this.watchAudioVideoSendFromGridFrag = watchAudioVideoSendFromGridFrag;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_no_comments, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hor_last_item_load_more, parent, false);
            return new ViewHolder3(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_music_recycler_item, parent, false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            ((ViewHolder1) holder).activity_no_comments_tv.setText("No Item Available");
        } else if (holder.getItemViewType() == 2) {
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) context.getResources().getDimension(R.dimen.fifty));
            ((ViewHolder3)holder).hor_last_item_load_more_container.setLayoutParams(params);
            loadMore();
        } else if (holder.getItemViewType() == 1) {
            if (feedInnerData.getHits().get(position).get_source().getProfilePic() != null)
                Glide.with(context).load(feedInnerData.getHits().get(position).get_source().getProfilePic().trim()).into(((ViewHolder2) holder).card_music_recycler_item_img);
            if (feedInnerData.getHits().get(position).get_source().getTitle() != null)
                ((ViewHolder2) holder).card_music_recycler_item_title.setText(feedInnerData.getHits().get(position).get_source().getTitle());
        }
    }

    private void loadMore() {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/contents/")
                .create(ApiInterface.class);
        Call<FeedData> call = apiInterface.getTopRatedMovies("http://apiservice-ec.flikster.com/contents/_search?sort=createdAt:desc&size=10&from="+count+url);
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(Call<FeedData> call, Response<FeedData> response) {
                count=count+10;
                feedInnerData.getHits().addAll(response.body().getHits().getHits());
                notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<FeedData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (feedInnerData.getHits() != null && feedInnerData.getHits().size() != 0) {
            if (feedInnerData.getTotal() == feedInnerData.getHits().size())
                return feedInnerData.getHits().size();
            return feedInnerData.getHits().size() + 1;
        } else return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (feedInnerData.getHits() != null && feedInnerData.getHits().size() != 0) {
            if (position != feedInnerData.getHits().size())
                return 1;
            else return 2;
        } else return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView activity_no_comments_tv;

        public ViewHolder1(View itemView) {
            super(itemView);
            activity_no_comments_tv = (TextView) itemView.findViewById(R.id.activity_no_comments_tv);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView hor_last_item_load_more_txt;
        LinearLayout hor_last_item_load_more_container;

        public ViewHolder3(View itemView) {
            super(itemView);
            //hor_last_item_load_more_txt=(TextView)itemView.findViewById(R.id.hor_last_item_load_more_txt);
            hor_last_item_load_more_container=(LinearLayout)itemView.findViewById(R.id.hor_last_item_load_more_container);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_music_recycler_item_img;
        TextView card_music_recycler_item_title;

        public ViewHolder2(View itemView) {
            super(itemView);
            card_music_recycler_item_img = (ImageView) itemView.findViewById(R.id.card_music_recycler_item_img);
            card_music_recycler_item_title = (TextView) itemView.findViewById(R.id.card_music_recycler_item_title);
            card_music_recycler_item_img.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia() != null) {
                if (feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getAudio() != null &&
                        feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getAudio().size() != 0) {
                    audioVideoLink = feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getAudio().get(0);
                } else if (feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo() != null &&
                        feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().size() != 0) {
                    audioVideoLink = feedInnerData.getHits().get(getAdapterPosition()).get_source().getMedia().getVideo().get(0);
                }
            }
            watchAudioVideoSendFromGridFrag.sendAudioVideoLink(feedInnerData.getHits().get(getAdapterPosition()).get_source().getTitle(),
                    feedInnerData.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                    feedInnerData.getHits().get(getAdapterPosition()).get_source().getTitle(),
                    audioVideoLink, new SongByMovieFragmentItemClick(), itemType);
        }
    }
}