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

import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionDetailFragment;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GallaryCardClick;
import com.flikster.Util.GlobalData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragment;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.HomeActivity.ProfileCollectionRecyclerItemAdapter;
import com.flikster.R;
import com.flikster.HomeActivity.StealStyleViewHolder;
import com.flikster.HomeActivity.CommonFragments.VideoFragment.VideoGalleryFragment;
import com.flikster.VideoFullScreenActivity.VideoPlayerActivity;

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
    FeedCelebrityRecyclerItemAdapter feedCelebrityRecyclerItemAdapter;

    public FeedRecyclerAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_steal_style_carousel, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
            return new ViewHolder3(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile_collection, parent, false);
            return new ViewHolder6(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary4_1, parent, false);
            return new ViewHolder4(view);
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_auction, parent, false);
            return new ViewHolder7(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary_4plus, parent, false);
            return new ViewHolder5(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
            profileCollectionRecyclerLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder1) holder).fragment_common_recyclerview_recycler.setLayoutManager(profileCollectionRecyclerLayoutManager);
            feedCelebrityRecyclerItemAdapter = new FeedCelebrityRecyclerItemAdapter();
            ((ViewHolder1) holder).fragment_common_recyclerview_recycler.setAdapter(feedCelebrityRecyclerItemAdapter);
            profile1 = false;

        } else if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setText("You won't believe these");
            stealStyleLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(stealStyleLayoutManager);
            stealStyleViewHolder = new StealStyleViewHolder(((ViewHolder2) holder).itemView.getContext(), fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(stealStyleViewHolder);
        } else if (holder.getItemViewType() == 3) {
            if (news == true) {
                ((ViewHolder3) holder).profile_image.setImageResource(globalData.movie.get(0));
                ((ViewHolder3) holder).news_img.setImageResource(globalData.movie.get(0));
                ((ViewHolder3) holder).tv_tag_name.setText("3 Idiots");
                ((ViewHolder3) holder).tv_tag_desc.setText("#Drama #Comedy");
                ((ViewHolder3) holder).tv_name.setText("Shooting of 3 Idiots is almost complete");
                ((ViewHolder3) holder).tv_description.setText("Shooting of 3 Idiots is almost complete and it is " +
                        "going to launch next year");
                news = false;
            } else if (news == false) {
                ((ViewHolder3) holder).profile_image.setImageResource(globalData.movie.get(1));
                ((ViewHolder3) holder).news_img.setImageResource(globalData.movie.get(1));
                ((ViewHolder3) holder).tv_tag_name.setText("Zindagi Na Milegi Dobara");
                ((ViewHolder3) holder).tv_tag_desc.setText("#Drama #Comedy #Life");
                ((ViewHolder3) holder).tv_name.setText("Shooting of ZNMD is complete");
                ((ViewHolder3) holder).tv_description.setText("ZNMD is a movie of 4 friends and it revolves around the plot of " +
                        "a trip");
                news = true;
            }
        } else if (holder.getItemViewType() == 4) {
            if (profile4 == true) {
                ((ViewHolder4) holder).profile_image.setImageResource(globalData.pooja.get(2));
                ((ViewHolder4) holder).card_gallary4_img1.setImageResource(globalData.pooja.get(0));
                ((ViewHolder4) holder).card_gallary4_img2.setImageResource(globalData.pooja.get(1));
                ((ViewHolder4) holder).card_gallary4_img3.setImageResource(globalData.pooja.get(2));
                ((ViewHolder4) holder).card_gallary4_img4.setImageResource(globalData.pooja.get(3));
                ((ViewHolder4) holder).tv_tag_name.setText("Pooja Hegde");
                ((ViewHolder4) holder).tv_tag_desc.setText("Actress");
                ((ViewHolder4) holder).tv_name.setText("Pooja Hegde was caught doing nightout");
                ((ViewHolder4) holder).tv_description.setText("Pooja Hegde was caught red handed by our camera crew while she" +
                        "was full drunk with her friends");
                profile4 = false;
            } else if (profile4 = false) {
                ((ViewHolder4) holder).profile_image.setImageResource(globalData.ranveer.get(2));
                ((ViewHolder4) holder).card_gallary4_img1.setImageResource(globalData.ranveer.get(0));
                ((ViewHolder4) holder).card_gallary4_img2.setImageResource(globalData.ranveer.get(1));
                ((ViewHolder4) holder).card_gallary4_img3.setImageResource(globalData.ranveer.get(2));
                ((ViewHolder4) holder).card_gallary4_img4.setImageResource(globalData.ranveer.get(1));
                ((ViewHolder4) holder).tv_tag_name.setText("Ranveer Singh");
                ((ViewHolder4) holder).tv_tag_desc.setText("Actor");
                ((ViewHolder4) holder).tv_name.setText("Ranveer Singh at the set of Padmavati");
                ((ViewHolder4) holder).tv_description.setText("Ranveer Singh at the set of Padmavati. He was caught red handed by" +
                        "our camera crew while he was kissing his girlfriend Deepika.");
                profile4 = true;
            }

        } else if (holder.getItemViewType() == 6) {
            if (collection == true) {
                ((ViewHolder6) holder).dp_name.setText("Pooja Hegde");
                ((ViewHolder6) holder).imageView2.setImageResource(globalData.pooja.get(2));
                ((ViewHolder6) holder).profile_collection_dp.setImageResource(globalData.pooja.get(3));
                ((ViewHolder6) holder).tv.setText("Pooja Hegde Collection");
                profileCollectionRecyclerLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                ((ViewHolder6) holder).fragment_common_recyclerview_recycler.setLayoutManager(profileCollectionRecyclerLayoutManager);
                profileCollectionRecyclerItemAdapter = new ProfileCollectionRecyclerItemAdapter(((ViewHolder6) holder).itemView.getContext(), 1, fragmentManager);
                ((ViewHolder6) holder).fragment_common_recyclerview_recycler.setAdapter(profileCollectionRecyclerItemAdapter);
                collection = false;
            } else if (collection == false) {
                ((ViewHolder6) holder).dp_name.setText("Ranveer Singh");
                ((ViewHolder6) holder).imageView2.setImageResource(globalData.ranveer.get(0));
                ((ViewHolder6) holder).profile_collection_dp.setImageResource(globalData.ranveer.get(1));
                ((ViewHolder6) holder).tv.setText("Ranveer Singh Collection");
                profileCollectionRecyclerLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                ((ViewHolder6) holder).fragment_common_recyclerview_recycler.setLayoutManager(profileCollectionRecyclerLayoutManager);
                profileCollectionRecyclerItemAdapter = new ProfileCollectionRecyclerItemAdapter(((ViewHolder6) holder).itemView.getContext(), 2, fragmentManager);
                ((ViewHolder6) holder).fragment_common_recyclerview_recycler.setAdapter(profileCollectionRecyclerItemAdapter);
                collection = true;
            }

        } else if (holder.getItemViewType() == 7) {
            ((ViewHolder7) holder).profile_image.setImageResource(R.drawable.rakulprofileicon);
//            ((ViewHolder7) holder).card_gallary1_img1.setImageResource(R.drawable.ranveer2);
            ((ViewHolder7) holder).tv_tag_desc.setText("#Actor");
            ((ViewHolder7) holder).tv_tag_name.setText("Rakul Preet");
            /*((ViewHolder1) holder).tv_name.setText("Ranveer Singh at the set of Padmavati");
            ((ViewHolder1) holder).tv_description.setText("Ranveer Singh at the set of Padmavati. He was caught red handed by" +
                    "our camera crew while he was kissing his girlfriend Deepika.");*/
//            profile1 = false;

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return globalData.type.size();
    }

    @Override
    public int getItemViewType(int position) {
        return globalData.type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        RecyclerView fragment_common_recyclerview_recycler;

        public ViewHolder1(View itemView) {
            super(itemView);
            fragment_common_recyclerview_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_recycler);
        }
    }

    public class ViewHolder11 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description;
        LinearLayout header_linear, card_description_linear;

        public ViewHolder11(View itemView) {
            super(itemView);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
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
        ImageView news_img, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description;
        ImageButton video_btn;
        LinearLayout header_linear;
        LinearLayout card_description_linear;

        public ViewHolder3(View itemView) {
            super(itemView);
            news_img = (ImageView) itemView.findViewById(R.id.news_img);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            video_btn = (ImageButton) itemView.findViewById(R.id.video_btn);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            video_btn.setOnClickListener(this);
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
        ImageView card_gallary4_img1, card_gallary4_img2, card_gallary4_img3, card_gallary4_img4, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description;
        LinearLayout header_linear, card_gallary4_img_container;

        public ViewHolder4(View itemView) {
            super(itemView);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_gallary4_img1 = (ImageView) itemView.findViewById(R.id.card_gallary4_img1);
            card_gallary4_img2 = (ImageView) itemView.findViewById(R.id.card_gallary4_img2);
            card_gallary4_img3 = (ImageView) itemView.findViewById(R.id.card_gallary4_img3);
            card_gallary4_img4 = (ImageView) itemView.findViewById(R.id.card_gallary4_img4);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_gallary4_img_container = (LinearLayout) itemView.findViewById(R.id.card_gallary4_img_container);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_gallary4_img_container.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            header_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a = 4;
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
        ImageView card_gallary5_img1, card_gallary5_img2, card_gallary5_img3, card_gallary5_img4;
        TextView card_gallary5_text;

        public ViewHolder5(View itemView) {
            super(itemView);
            card_gallary5_img1 = (ImageView) itemView.findViewById(R.id.card_gallary5_img1);
            card_gallary5_img2 = (ImageView) itemView.findViewById(R.id.card_gallary5_img2);
            card_gallary5_img3 = (ImageView) itemView.findViewById(R.id.card_gallary5_img3);
            card_gallary5_img4 = (ImageView) itemView.findViewById(R.id.card_gallary5_img4);
            card_gallary5_text = (TextView) itemView.findViewById(R.id.card_gallary5_text);
            itemView.setOnClickListener(this);
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

        ImageView imageView2, profile_collection_dp;
        TextView tv, dp_name;
        RecyclerView fragment_common_recyclerview_recycler;
        LinearLayout linearLayout;

        public ViewHolder6(View itemView) {
            super(itemView);
            fragment_common_recyclerview_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_recycler);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            profile_collection_dp = (ImageView) itemView.findViewById(R.id.profile_collection_dp);
            tv = (TextView) itemView.findViewById(R.id.tv);
            dp_name = (TextView) itemView.findViewById(R.id.dp_name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.profile_collection_linear);
            linearLayout.setOnClickListener(this);

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
        LinearLayout header_linear, card_description_linear;
        Button followbtn;

        public ViewHolder7(View itemView) {
            super(itemView);
            card_gallary1_img1 = (ImageView) itemView.findViewById(R.id.card_gallary1_img1);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            followbtn.setText("BID");
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
                        .replace(R.id.main_container, new AuctionDetailFragment())
                        .addToBackStack("")
                        .commit();
            }

        }
    }
}
