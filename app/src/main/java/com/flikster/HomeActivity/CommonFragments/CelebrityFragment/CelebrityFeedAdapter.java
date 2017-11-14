package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.flikster.GlobalDataStorage;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GallaryCardClick;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.R;
import com.flikster.HomeActivity.CommonFragments.VideoFragment.VideoGalleryFragment;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by abhishek on 12-10-2017.
 */

public class CelebrityFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    String profilepic;
    String coverpic;
    String name;
    ArrayList<String> role = new ArrayList<>();

    public CelebrityFeedAdapter(Context context, FragmentManager fragmentManager, String profilepic, String coverpic,
                                String name, ArrayList<String> role) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        type.add(5);
        this.profilepic = profilepic;
        this.coverpic = coverpic;
        this.name = name;
        this.role = role;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_profile, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_gallary, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_news, parent, false);
            return new ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dialogue, parent, false);
            return new ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_video, parent, false);
            return new ViewHolder5(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1, parent, false);
            return new ViewHolder6(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
            Log.e("check4global", " " + new GlobalDataStorage().getCelebrityProfilePic());
            Log.e("check4global1", " " + profilepic);
            if (name != null && !name.isEmpty()) {
                ((ViewHolder1) holder).card_celebrity_feed_profile_name.setText(name);
            }

            ((ViewHolder1) holder).card_celebrity_feed_profile_role.setText(role.get(0));

            if (profilepic != null && !profilepic.isEmpty()) {
                Glide.with(context).load(profilepic).asBitmap().into(((ViewHolder1) holder).card_celebrity_feed_profile_image);
            }
            if (coverpic != null && !coverpic.isEmpty()) {
                Glide.with(context).load(coverpic).asBitmap().into(((ViewHolder1) holder).card_celebrity_feed_profile_coverpic);
            }


        } else if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).card_celebrity_feed_gallary_title.setText("Pooja's new Photo gallary");
            ((ViewHolder2) holder).card_celebrity_feed_gallary_img1.setImageResource(R.drawable.pooja6);
            ((ViewHolder2) holder).card_celebrity_feed_gallary_img2.setImageResource(R.drawable.pooja3);
            ((ViewHolder2) holder).card_celebrity_feed_gallary_img3.setImageResource(R.drawable.pooja2);
        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3) holder).card_celebrity_feed_news_title.setText("Pooja in the shooting of Padmavati");
            ((ViewHolder3) holder).name.setText("Pooja interview after shooting");
            ((ViewHolder3) holder).desc.setText("Pooja is busy these days in the shooting of Epic MohanJodaro.");
        } else if (holder.getItemViewType() == 4) {

        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).card_celebrity_feed_video_title.setText("Watch the latest update of Mohanjodaro");
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView card_celebrity_feed_profile_image, card_celebrity_feed_profile_coverpic;
        TextView card_celebrity_feed_profile_name, card_celebrity_feed_profile_role;

        public ViewHolder1(View itemView) {
            super(itemView);
            card_celebrity_feed_profile_image = (ImageView) itemView.findViewById(R.id.card_celebrity_feed_profile_image);
            card_celebrity_feed_profile_coverpic = (ImageView) itemView.findViewById(R.id.card_celebrity_feed_profile_coverpic);
            card_celebrity_feed_profile_name = (TextView) itemView.findViewById(R.id.card_celebrity_feed_profile_name);
            card_celebrity_feed_profile_role = (TextView) itemView.findViewById(R.id.card_celebrity_feed_profile_role);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView card_celebrity_feed_gallary_title;
        ImageView card_celebrity_feed_gallary_img1, card_celebrity_feed_gallary_img2, card_celebrity_feed_gallary_img3;

        public ViewHolder2(View itemView) {
            super(itemView);
            card_celebrity_feed_gallary_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallary_title);
            card_celebrity_feed_gallary_img1 = (ImageView) itemView.findViewById(R.id.card_celebrity_feed_gallary_img1);
            card_celebrity_feed_gallary_img2 = (ImageView) itemView.findViewById(R.id.card_celebrity_feed_gallary_img2);
            card_celebrity_feed_gallary_img3 = (ImageView) itemView.findViewById(R.id.card_celebrity_feed_gallary_img3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new GallaryCardClick())
                    .addToBackStack("")
                    .commit();
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView card_celebrity_feed_news_title, desc, name;

        public ViewHolder3(View itemView) {
            super(itemView);
            card_celebrity_feed_news_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_news_title);
            desc = (TextView) itemView.findViewById(R.id.tv_description);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new NewsOnClickFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder {
        public ViewHolder4(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView card_celebrity_feed_video_title;

        public ViewHolder5(View itemView) {
            super(itemView);
            card_celebrity_feed_video_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_video_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new VideoGalleryFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder {
        public ViewHolder6(View itemView) {
            super(itemView);
        }
    }
}
