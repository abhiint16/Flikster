package com.flikster.HomeActivity.WatchFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterFamilyViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterFilmographyViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterPeersViewHolder;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterVideoViewHolder;
import com.flikster.HomeActivity.FeedFragment.FeedCelebrityRecyclerItemAdapter;
import com.flikster.HomeActivity.FeedFragment.FeedRecyclerAdapter;
import com.flikster.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-10-2017.
 */

public class WatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    List<String> imag = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    CelebrityBioAdapterVideoViewHolder celebrityBioAdapterVideoViewHolder;
    MusicAdapterViewHolder musicAdapterViewHolder;
    WatchTrailerViewHolder watchTrailerViewHolder;
    CelebrityBioAdapterFamilyViewHolder celebrityBioAdapterFamilyViewHolder;
    CelebrityBioAdapterFilmographyViewHolder celebrityBioAdapterFilmographyViewHolder;
    CelebrityBioAdapterPeersViewHolder celebrityBioAdapterPeersViewHolder;
    CelebrityBioAdapterImagesViewHolder celebrityBioAdapterImagesViewHolder;

    int[] sampleImages = {R.drawable.rakulpreetred, R.drawable.prabha, R.drawable.rakulpreetred, R.drawable.prabha, R.drawable.rakulpreetred};

    public WatchAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        type.add(5);
        type.add(6);
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
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
//            return new WatchAdapter.ViewHolder1(view);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carouselview, parent, false);
            return new WatchAdapter.ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder6(view);
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder7(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new WatchAdapter.ViewHolder5(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {

            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            ((ViewHolder1) holder).fragment_common_recyclerview_with_tv_title.setVisibility(View.GONE);
//            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//            ((ViewHolder1) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
//            watchTrailerViewHolder = new WatchTrailerViewHolder();
//            ((ViewHolder1) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(watchTrailerViewHolder);
            ImageListener imageListeners;
            imageListeners = new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    imageView.setImageResource(sampleImages[position]);
                }
            };
            //
            ((ViewHolder1) holder).carouselView.setImageListener(imageListeners);
//            ViewListener viewListener = new ViewListener() {
//                @Override
//                public View setViewForPosition(int position) {
//                    View imgview = inflater.inflate(R.layout.card_image_item, null);
//                    return imgview;
//                }
//            };


        } else if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setText("Music");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            musicAdapterViewHolder = new MusicAdapterViewHolder(fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(musicAdapterViewHolder);
        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_title.setText("Tv Shows");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterImagesViewHolder = new CelebrityBioAdapterImagesViewHolder(context);
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterImagesViewHolder);
        } else if (holder.getItemViewType() == 4) {
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_title.setText("Social Buzz/Interviews");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterFilmographyViewHolder = new CelebrityBioAdapterFilmographyViewHolder();
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterFilmographyViewHolder);
        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_title.setText("Movies");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterFilmographyViewHolder = new CelebrityBioAdapterFilmographyViewHolder();
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterFilmographyViewHolder);
        } else if (holder.getItemViewType() == 6) {
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_title.setText("Trailers & Promos");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterPeersViewHolder = new CelebrityBioAdapterPeersViewHolder();
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterPeersViewHolder);
        } else if (holder.getItemViewType() == 7) {
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_title.setText("Comedy");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterPeersViewHolder = new CelebrityBioAdapterPeersViewHolder();
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterPeersViewHolder);
        } else {
        }
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
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;
        CarouselView carouselView;
        View imgview;


        public ViewHolder1(View itemView) {
            super(itemView);
//            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
//            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);

            carouselView = (CarouselView) itemView.findViewById(R.id.carouselView);
            carouselView.setPageCount(sampleImages.length);

        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder2(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
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
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder4(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
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
            fragment_common_recyclerview_with_tv_title.setText("Comedy");
        }


    }


}
