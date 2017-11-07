package com.flikster.HomeActivity.FeedFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionDetailFragment;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GallaryCardClick;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.Util.GlobalData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragment;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.HomeActivity.ProfileCollectionRecyclerItemAdapter;
import com.flikster.R;
import com.flikster.HomeActivity.StealStyleViewHolder;
import com.flikster.HomeActivity.CommonFragments.VideoFragment.VideoGalleryFragment;
import com.flikster.VideoFullScreenActivity.VideoPlayerActivity;

import java.util.List;

/**
 * Created by abhishek on 04-10-2017.
 */

public class FeedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Boolean news = true, profile1 = true, profile4 = true, collection = true;
    GlobalData globalData = new GlobalData();
    Context context;
    FragmentManager fragmentManager;
    RecyclerView.LayoutManager stealStyleLayoutManager, profileCollectionRecyclerLayoutManager;
    StealStyleViewHolder stealStyleViewHolder;
    ProfileCollectionRecyclerItemAdapter profileCollectionRecyclerItemAdapter;
    List<FeedInnerData> items;
    Integer Count;

    //FeedInnerData a;
    public FeedRecyclerAdapter(Context context, FragmentManager fragmentManager, List<FeedInnerData> items, Integer Count) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.items = items;
        this.Count = Count;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_steal_style_carousel, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1, parent, false);
            return new ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
            return new ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1, parent, false);
            return new ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1, parent, false);
            return new ViewHolder6(view);
        } else if(viewType==7){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1, parent, false);
            return new ViewHolder7(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
            Glide.with(context).load(items.get(0).getProfilePic()).into(((ViewHolder1) holder).news_img);
            Glide.with(context).load(items.get(0).getProfilePic()).asBitmap().into((((ViewHolder1) holder).profile_image));
            ((ViewHolder1) holder).tv_tag_desc.setText(items.get(0).getTitle());
            ((ViewHolder1) holder).tv_tag_name.setText(items.get(0).getTitle());
            ((ViewHolder1) holder).tv_name.setText(items.get(0).getTitle());
            ((ViewHolder1) holder).tv_description.setText(items.get(0).getTitle());
        } else if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setText("You won't believe these");
            stealStyleLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(stealStyleLayoutManager);
            stealStyleViewHolder = new StealStyleViewHolder(((ViewHolder2) holder).itemView.getContext(), fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(stealStyleViewHolder);
        } else if (holder.getItemViewType() == 3) {
            Glide.with(context).load(items.get(1).getProfilePic()).asBitmap().into((((ViewHolder3) holder).profile_image));
            Glide.with(context).load(items.get(1).getProfilePic()).into(((ViewHolder3) holder).card_gallary1_img1);
            ((ViewHolder3) holder).tv_tag_name.setText(items.get(1).getTitle());
            ((ViewHolder3) holder).tv_tag_desc.setText(items.get(1).getTitle());
            ((ViewHolder3) holder).tv_name.setText(items.get(1).getTitle());
            ((ViewHolder3) holder).tv_description.setText(items.get(1).getTitle());
        } else if (holder.getItemViewType() == 4) {
            Glide.with(context).load(items.get(2).getProfilePic()).into(((ViewHolder4) holder).news_img);
            Glide.with(context).load(items.get(2).getProfilePic()).asBitmap().into((((ViewHolder4) holder).profile_image));
            ((ViewHolder4) holder).tv_tag_desc.setText(items.get(2).getTitle());
            ((ViewHolder4) holder).tv_tag_name.setText(items.get(2).getTitle());
            ((ViewHolder4) holder).tv_name.setText(items.get(2).getTitle());
            ((ViewHolder4) holder).tv_description.setText(items.get(2).getTitle());
        } else if (holder.getItemViewType() == 5) {
            Glide.with(context).load(items.get(3).getProfilePic()).asBitmap().into((((ViewHolder5) holder).profile_image));
            Glide.with(context).load(items.get(3).getProfilePic()).into(((ViewHolder5) holder).card_gallary1_img1);
            ((ViewHolder5) holder).tv_tag_name.setText(items.get(3).getTitle());
            ((ViewHolder5) holder).tv_tag_desc.setText(items.get(3).getTitle());
            ((ViewHolder5) holder).tv_name.setText(items.get(3).getTitle());
            ((ViewHolder5) holder).tv_description.setText(items.get(3).getTitle());
        } else if (holder.getItemViewType() == 6) {
            Glide.with(context).load(items.get(4).getProfilePic()).asBitmap().into((((ViewHolder6) holder).profile_image));
            Glide.with(context).load(items.get(4).getProfilePic()).into(((ViewHolder6) holder).card_gallary1_img1);
            ((ViewHolder6) holder).tv_tag_name.setText(items.get(4).getTitle());
            ((ViewHolder6) holder).tv_tag_desc.setText(items.get(4).getTitle());
            ((ViewHolder6) holder).tv_name.setText(items.get(4).getTitle());
            ((ViewHolder6) holder).tv_description.setText(items.get(4).getTitle());
        } else if(holder.getItemViewType() == 7){
            Glide.with(context).load(items.get(5).getProfilePic()).asBitmap().into((((ViewHolder7) holder).profile_image));
            Glide.with(context).load(items.get(5).getProfilePic()).into(((ViewHolder7) holder).card_gallary1_img1);
            ((ViewHolder7) holder).tv_tag_name.setText(items.get(5).getTitle());
            ((ViewHolder7) holder).tv_tag_desc.setText(items.get(5).getTitle());
            ((ViewHolder7) holder).tv_name.setText(items.get(5).getTitle());
            ((ViewHolder7) holder).tv_description.setText(items.get(5).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    @Override
    public int getItemViewType(int position) {
        switch (items.get(position).getContentType()) {
            case "comedy-clip":
                return 1;
            case "quote":
                return 2;
            case "news":
                return 3;
            case "video-song":
                return 4;
            case "first-look":
                return 5;
            case "poster":
                return 6;
            case "gallery":
                return 7;
        }
        return super.getItemViewType(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView news_img, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description;
        LinearLayout header_linear, card_description_linear;

        public ViewHolder1(View itemView) {
            super(itemView);
            news_img = (ImageView) itemView.findViewById(R.id.news_img);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            card_description_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            header_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a = 1;
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_name)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new CelebrityFragment())
                        .addToBackStack("")
                        .commit();
            } else if (view.getId() == R.id.card_description_linear) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new NewsOnClickFragment())
                        .addToBackStack("")
                        .commit();
            }

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

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description;
        ImageButton video_btn;
        LinearLayout header_linear;
        LinearLayout card_description_linear;

        public ViewHolder3(View itemView) {
            super(itemView);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            //video_btn = (ImageButton) itemView.findViewById(R.id.video_btn);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            //video_btn.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a = 3;
            if (view.getId() == R.id.video_btn) {
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                context.startActivity(intent);
            } else if ((view.getId() == R.id.profile_image) || (view.getId() == R.id.header_linear)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new MovieFragment())
                        .addToBackStack("")
                        .commit();
            } else if (view.getId() == R.id.card_description_linear) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new VideoGalleryFragment())
                        .addToBackStack("")
                        .commit();
            }
        }

    }

    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView news_img, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description;
        LinearLayout header_linear, card_description_linear;

        public ViewHolder4(View itemView) {
            super(itemView);
            news_img = (ImageView) itemView.findViewById(R.id.news_img);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            card_description_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            header_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_gallary4_img_container) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new GallaryCardClick())
                        .addToBackStack("")
                        .commit();
            } else if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new CelebrityFragment())
                        .addToBackStack("")
                        .commit();
            }

        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description;
        ImageButton video_btn;
        LinearLayout header_linear;
        LinearLayout card_description_linear;

        public ViewHolder5(View itemView) {
            super(itemView);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            //video_btn = (ImageButton) itemView.findViewById(R.id.video_btn);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            //video_btn.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a = globalData.type.get(getAdapterPosition());
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new GallaryCardClick())
                    .addToBackStack("")
                    .commit();
        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description;
        ImageButton video_btn;
        LinearLayout header_linear;
        LinearLayout card_description_linear;

        public ViewHolder6(View itemView) {
            super(itemView);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            //video_btn = (ImageButton) itemView.findViewById(R.id.video_btn);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            //video_btn.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a = globalData.type.get(getAdapterPosition());
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new CelebrityFragment())
                    .addToBackStack("")
                    .commit();
        }
    }


    public class ViewHolder7 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description;
        ImageButton video_btn;
        LinearLayout header_linear;
        LinearLayout card_description_linear;

        public ViewHolder7(View itemView) {
            super(itemView);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            //video_btn = (ImageButton) itemView.findViewById(R.id.video_btn);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            //video_btn.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a = 1;
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_name)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new CelebrityFragment())
                        .addToBackStack("")
                        .commit();
            } else if (view.getId() == R.id.card_description_linear) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new AuctionDetailFragment())
                        .addToBackStack("")
                        .commit();
            }

        }
    }
}
