package com.flikster;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MovieInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    List<String> imag = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    CelebrityBioAdapterVideoViewHolder celebrityBioAdapterVideoViewHolder;
    CelebrityBioAdapterFamilyViewHolder celebrityBioAdapterFamilyViewHolder;
    CelebrityBioAdapterFilmographyViewHolder celebrityBioAdapterFilmographyViewHolder;
    CelebrityBioAdapterPeersViewHolder celebrityBioAdapterPeersViewHolder;
    CelebrityBioAdapterImagesViewHolder celebrityBioAdapterImagesViewHolder;

    public MovieInfoAdapter(Context context, FragmentManager fragmentManager) {
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
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");
        imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");
        imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_info_profile, parent, false);
            return new MovieInfoAdapter.ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_videos_carousel, parent, false);
            return new MovieInfoAdapter.ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_videos_carousel, parent, false);
            return new MovieInfoAdapter.ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_videos_carousel, parent, false);
            return new MovieInfoAdapter.ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_news, parent, false);
            return new MovieInfoAdapter.ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_videos_carousel, parent, false);
            return new MovieInfoAdapter.ViewHolder6(view);
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_review, parent, false);
            return new MovieInfoAdapter.ViewHolder7(view);
        } else if (viewType == 8) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_user_movie_review, parent, false);
            return new MovieInfoAdapter.ViewHolder8(view);
        } else if (viewType == 9) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_videos_carousel, parent, false);
            return new MovieInfoAdapter.ViewHolder9(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_news, parent, false);
            return new MovieInfoAdapter.ViewHolder9(view);
        }
        /*else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_news, parent, false);
            return new MovieInfoAdapter.ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_filmography, parent, false);
            return new MovieInfoAdapter.ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_peers, parent, false);
            return new MovieInfoAdapter.ViewHolder6(view);
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_family, parent, false);
            return new MovieInfoAdapter.ViewHolder7(view);
        } else if (viewType == 8) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_awards, parent, false);
            return new MovieInfoAdapter.ViewHolder8(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_upcoming, parent, false);
            return new MovieInfoAdapter.ViewHolder9(view);
        }*/

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 2) {
//            ((ViewHolder2) holder).textView.setText("Videos");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).recyclerView.setLayoutManager(layoutManager);
            celebrityBioAdapterVideoViewHolder = new CelebrityBioAdapterVideoViewHolder();
            ((ViewHolder2) holder).recyclerView.setAdapter(celebrityBioAdapterVideoViewHolder);
        } else if (holder.getItemViewType() == 3) {
//            ((ViewHolder3) holder).textView.setText("Cast");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder3) holder).recyclerView.setLayoutManager(layoutManager);
            celebrityBioAdapterImagesViewHolder = new CelebrityBioAdapterImagesViewHolder(context);
            ((ViewHolder3) holder).recyclerView.setAdapter(celebrityBioAdapterImagesViewHolder);
        } else if (holder.getItemViewType() == 4) {
            //((ViewHolder5)holder).textView.setText("videos");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder4) holder).recyclerView.setLayoutManager(layoutManager);
            celebrityBioAdapterFilmographyViewHolder = new CelebrityBioAdapterFilmographyViewHolder();
            ((ViewHolder4) holder).recyclerView.setAdapter(celebrityBioAdapterFilmographyViewHolder);
        } else if (holder.getItemViewType() == 6) {
            //((ViewHolder6)holder).textView.setText("videos");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6) holder).recyclerView.setLayoutManager(layoutManager);
            celebrityBioAdapterPeersViewHolder = new CelebrityBioAdapterPeersViewHolder();
            ((ViewHolder6) holder).recyclerView.setAdapter(celebrityBioAdapterPeersViewHolder);
        } else if (holder.getItemViewType() == 9) {
//            ((ViewHolder3) holder).textView.setText("Cast");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder9) holder).recyclerView.setLayoutManager(layoutManager);
            celebrityBioAdapterImagesViewHolder = new CelebrityBioAdapterImagesViewHolder(context);
            ((ViewHolder9) holder).recyclerView.setAdapter(celebrityBioAdapterImagesViewHolder);
        }
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        public ViewHolder2(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);
            textView.setText("Videos");
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        public ViewHolder3(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);
            textView.setText("Cast");
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        public ViewHolder4(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);
            textView.setText("Crew");
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder {
        public ViewHolder5(View itemView) {
            super(itemView);
        }
    }

    /*public class ViewHolder5 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        public ViewHolder5(View itemView) {
            super(itemView);
            //textView=(TextView)itemView.findViewById(R.id.txt);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.card_celebrity_bio_filmography_recycler);
        }
    }*/

    public class ViewHolder6 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        public ViewHolder6(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);
            textView.setText("Images");
        }
    }

    public class ViewHolder7 extends RecyclerView.ViewHolder {
        public ViewHolder7(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder8 extends RecyclerView.ViewHolder {
        public ViewHolder8(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder9 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        public ViewHolder9(View itemView) {

            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);
            textView.setText("Recommended Movies");
        }
    }

}
