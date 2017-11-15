package com.flikster.HomeActivity.FeedFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionDetailFragment;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.GalleryFragment.GalleryCardClick;
import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.Util.Common;
import com.flikster.Util.GlobalData;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragment;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.HomeActivity.ProfileCollectionRecyclerItemAdapter;
import com.flikster.R;
import com.flikster.HomeActivity.StealStyleViewHolder;
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
    FeedCelebrityRecyclerItemAdapter feedCelebrityRecyclerItemAdapter;
    FeedFragment.Testing testing;

    public FeedRecyclerAdapter(Context context, FragmentManager fragmentManager, List<FeedInnerData> items, Integer Count, FeedFragment.Testing testing) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.items = items;
        this.Count = Count;
        this.testing = testing;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
            //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_steal_style_carousel, parent, false);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_quote, parent, false);
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
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1, parent, false);
            return new ViewHolder7(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == 1) {
            Glide.with(context).load(items.get(position).getProfilePic()).into(((ViewHolder1) holder).news_img);
            if (items.get(position).getMovie() != null) {
                Glide.with(context).load(items.get(position).getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder1) holder).profile_image));
                ((ViewHolder1) holder).tv_tag_desc.setText(items.get(position).getMovie().get(0).getType());
                ((ViewHolder1) holder).tv_tag_name.setText(items.get(position).getMovie().get(0).getName());
            } else if (items.get(position).getCeleb() != null) {
                Glide.with(context).load(items.get(position).getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder1) holder).profile_image));
                ((ViewHolder1) holder).tv_tag_desc.setText(items.get(position).getCeleb().get(0).getType());
                ((ViewHolder1) holder).tv_tag_name.setText(items.get(position).getCeleb().get(0).getName());
            }
            if (items.get(position).getText() == null)
                ((ViewHolder1) holder).tv_description.setVisibility(View.GONE);
            else if (items.get(position).getText() != null)
                ((ViewHolder1) holder).tv_description.setText(Html.fromHtml(Common.formatString(items.get(position).getText())));
            ((ViewHolder1) holder).tv_name.setText(items.get(position).getTitle());
        } else if (holder.getItemViewType() == 2) {
            /*((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setText("You won't believe these");
            stealStyleLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(stealStyleLayoutManager);
            stealStyleViewHolder = new StealStyleViewHolder(((ViewHolder2) holder).itemView.getContext(), fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(stealStyleViewHolder);*/
            ((ViewHolder2) holder).card_quote_tv.setText(items.get(position).getText());
            if (items.get(position).getMovie() != null) {
                Log.e("pic", "" + items.get(position).getMovie().get(0).getProfilePic());
                Glide.with(context).load(items.get(position).getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder2) holder).profile_image));
                ((ViewHolder2) holder).tv_tag_desc.setText(items.get(position).getMovie().get(0).getType());
                ((ViewHolder2) holder).tv_tag_name.setText(items.get(position).getMovie().get(0).getName());
            } else if (items.get(position).getCeleb() != null) {
                Glide.with(context).load(items.get(position).getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder2) holder).profile_image));
                ((ViewHolder2) holder).tv_tag_desc.setText(items.get(position).getCeleb().get(0).getType());
                ((ViewHolder2) holder).tv_tag_name.setText(items.get(position).getCeleb().get(0).getName());
            }
            //if (items.get(position).getText() == null)
                ((ViewHolder2) holder).tv_description.setVisibility(View.GONE);
            //else if (items.get(position).getText() != null)
              //  ((ViewHolder2) holder).tv_description.setText(Html.fromHtml(items.get(position).getText()));
            ((ViewHolder2) holder).tv_name.setText(items.get(position).getTitle());
        } else if (holder.getItemViewType() == 3) {

            Log.e("hfdhhd","insde news"+items.get(position).getMovie());
            if (items.get(position).getMovie() != null) {
                Log.e("CelaDta", "news_card" + items.get(position).getMovie().get(0).getProfilePic());
                Log.e("pic", "" + items.get(position).getMovie().get(0).getProfilePic());
                Glide.with(context).load(items.get(position).getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder3) holder).profile_image));
                ((ViewHolder3) holder).tv_tag_desc.setText(items.get(position).getMovie().get(0).getType());
                ((ViewHolder3) holder).tv_tag_name.setText(items.get(position).getMovie().get(0).getName());
            } else if (items.get(position).getCeleb() != null) {
                Log.e("MovieDta", "news_card" + items.get(position).getCeleb());
                Log.e("pic", "" + items.get(position).getCeleb().get(0).getProfilePic());
                Glide.with(context).load(items.get(position).getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder3) holder).profile_image));
                ((ViewHolder3) holder).tv_tag_desc.setText(items.get(position).getCeleb().get(0).getType());
                ((ViewHolder3) holder).tv_tag_name.setText(items.get(position).getCeleb().get(0).getName());
            }
            if (items.get(position).getText() == null)
                ((ViewHolder3) holder).tv_description.setVisibility(View.GONE);
            else if (items.get(position).getText() != null)
                ((ViewHolder3) holder).tv_description.setText(Html.fromHtml(Common.formatString(items.get(position).getText())));
            Glide.with(context).load(items.get(position).getProfilePic()).into(((ViewHolder3) holder).card_gallary1_img1);
            ((ViewHolder3) holder).tv_name.setText(items.get(position).getTitle());
            // Common.makeTextViewResizable(((ViewHolder3) holder).tv_description, 3, "View More", true);

        } else if (holder.getItemViewType() == 4) {
            if (items.get(position).getMovie() != null && items.get(position).getMovie().size() != 0) {
                Glide.with(context).load(items.get(position).getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder4) holder).profile_image));
                ((ViewHolder4) holder).tv_tag_desc.setText(items.get(position).getMovie().get(0).getType());
                ((ViewHolder4) holder).tv_tag_name.setText(items.get(position).getMovie().get(0).getName());
            } else if (items.get(position).getCeleb() != null && items.get(position).getCeleb().size() != 0) {
                Glide.with(context).load(items.get(position).getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder4) holder).profile_image));
                ((ViewHolder4) holder).tv_tag_desc.setText(items.get(position).getCeleb().get(0).getType());
                ((ViewHolder4) holder).tv_tag_name.setText(items.get(position).getCeleb().get(0).getName());
            }
            if (items.get(position).getText() == null)
                ((ViewHolder4) holder).tv_description.setVisibility(View.GONE);
            else if (items.get(position).getText() != null)
                ((ViewHolder4) holder).tv_description.setText(Html.fromHtml(items.get(position).getText()));
            Glide.with(context).load(items.get(position).getProfilePic()).into(((ViewHolder4) holder).news_img);
            ((ViewHolder4) holder).tv_name.setText(items.get(position).getTitle());
        } else if (holder.getItemViewType() == 5) {
            if (items.get(position).getMovie() != null) {
                Glide.with(context).load(items.get(position).getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder5) holder).profile_image));
                ((ViewHolder5) holder).tv_tag_desc.setText(items.get(position).getMovie().get(0).getType());
                ((ViewHolder5) holder).tv_tag_name.setText(items.get(position).getMovie().get(0).getName());
            } else if (items.get(position).getCeleb() != null) {
                Glide.with(context).load(items.get(position).getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder5) holder).profile_image));
                ((ViewHolder5) holder).tv_tag_desc.setText(items.get(position).getCeleb().get(0).getType());
                ((ViewHolder5) holder).tv_tag_name.setText(items.get(position).getCeleb().get(0).getName());
            }
            if (items.get(position).getText() == null)
                ((ViewHolder5) holder).tv_description.setVisibility(View.GONE);
            else if (items.get(position).getText() != null)
                ((ViewHolder5) holder).tv_description.setText(Html.fromHtml(items.get(position).getText()));
            Glide.with(context).load(items.get(position).getProfilePic()).into(((ViewHolder5) holder).card_gallary1_img1);
            ((ViewHolder5) holder).tv_name.setText(items.get(position).getTitle());
        } else if (holder.getItemViewType() == 6) {
            if (items.get(position).getMovie() != null) {
                Glide.with(context).load(items.get(position).getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder6) holder).profile_image));
                ((ViewHolder6) holder).tv_tag_desc.setText(items.get(position).getMovie().get(0).getType());
                ((ViewHolder6) holder).tv_tag_name.setText(items.get(position).getMovie().get(0).getName());
            } else if (items.get(position).getCeleb() != null) {
                Glide.with(context).load(items.get(position).getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder6) holder).profile_image));
                ((ViewHolder6) holder).tv_tag_desc.setText(items.get(position).getCeleb().get(0).getType());
                ((ViewHolder6) holder).tv_tag_name.setText(items.get(position).getCeleb().get(0).getName());
            }
            if (items.get(position).getText() == null)
                ((ViewHolder6) holder).tv_description.setVisibility(View.GONE);
            else if (items.get(position).getText() != null)
                ((ViewHolder6) holder).tv_description.setText(Html.fromHtml(items.get(position).getText()));
            Glide.with(context).load(items.get(position).getProfilePic()).into(((ViewHolder6) holder).card_gallary1_img1);
            ((ViewHolder6) holder).tv_name.setText(items.get(position).getTitle());
        } else if (holder.getItemViewType() == 7) {
            if (items.get(position).getMovie() != null && items.get(position).getMovie().size() != 0) {
                Glide.with(context).load(items.get(position).getMovie().get(0).getProfilePic()).asBitmap().into((((ViewHolder7) holder).profile_image));
                ((ViewHolder7) holder).tv_tag_desc.setText(items.get(position).getMovie().get(0).getType());
                ((ViewHolder7) holder).tv_tag_name.setText(items.get(position).getMovie().get(0).getName());
            } else if (items.get(position).getCeleb() != null) {
                Glide.with(context).load(items.get(position).getCeleb().get(0).getProfilePic()).asBitmap().into((((ViewHolder7) holder).profile_image));
                ((ViewHolder7) holder).tv_tag_desc.setText(items.get(position).getCeleb().get(0).getType());
                ((ViewHolder7) holder).tv_tag_name.setText(items.get(position).getCeleb().get(0).getName());
                Log.e("check", "" + items.get(position).getMedia().getGallery());
            }
            if (items.get(position).getText() == null)
                ((ViewHolder7) holder).tv_description.setVisibility(View.GONE);
            else if (items.get(position).getText() != null)
                ((ViewHolder7) holder).tv_description.setText(Html.fromHtml(items.get(position).getText()));
            Glide.with(context).load(items.get(position).getProfilePic()).into(((ViewHolder7) holder).card_gallary1_img1);
            ((ViewHolder7) holder).tv_name.setText(items.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return Count;
    }

    @Override
    public int getItemViewType(int position) {
        Log.e("ssss", "sss" + items.get(position).getContentType());
        switch (items.get(position).getContentType()) {
            case "comedy-clip":
                return 4;
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
            case "gallery": {
                return 7;
            }
            case "movie-making":
                return 4;
            case "audio-song":
                return 4;
            case "dialouge":
                return 4;
            case "critic-review":
                return 1;
            case "social-buzz":
                return 4;
            case "interview":
                return 4;
            case "trailer":
                return 4;
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
            header_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if ((view.getId() == R.id.profile_image) || (view.getId() == R.id.header_linear)) {
                if (items.get(getAdapterPosition()).getCeleb() == null) {
                    testing.test(items.get(getAdapterPosition()).getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (items.get(getAdapterPosition()).getMovie() == null) {
                    testing.test(items.get(getAdapterPosition()).getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            }
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description, card_quote_tv;
        LinearLayout card_description_linear, header_linear;

        public ViewHolder2(View itemView) {
            super(itemView);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_quote_tv = (TextView) itemView.findViewById(R.id.card_quote_tv);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            header_linear.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.header_linear) {
                if (items.get(getAdapterPosition()).getMovie() != null) {
                    testing.test(items.get(getAdapterPosition()).getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (items.get(getAdapterPosition()).getCeleb() != null) {
                    testing.test(items.get(getAdapterPosition()).getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            }
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
            if (view.getId() == R.id.video_btn) {
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                context.startActivity(intent);
            } else if ((view.getId() == R.id.profile_image) || (view.getId() == R.id.header_linear)) {
                if (items.get(getAdapterPosition()).getMovie() != null) {
                    testing.test(items.get(getAdapterPosition()).getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (items.get(getAdapterPosition()).getCeleb() != null) {
                    testing.test(items.get(getAdapterPosition()).getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            } else if (view.getId() == R.id.card_description_linear) {
                if (items.get(getAdapterPosition()).getMovie() != null) {
                    Log.e("Card_data", "Movies" + items.get(getAdapterPosition()).getMovie().get(0).getName());
                    testing.newsCardOnClick(items.get(getAdapterPosition()).getMovie().get(0).getProfilePic(),
                            items.get(getAdapterPosition()).getMovie().get(0).getName(),
                            items.get(getAdapterPosition()).getMovie().get(0).getType(),
                            items.get(getAdapterPosition()).getProfilePic(),
                            items.get(getAdapterPosition()).getTitle(),
                            items.get(getAdapterPosition()).getText(), new NewsOnClickFragment(),
                            items.get(getAdapterPosition()).getContentType()
                    );
                } else if (items.get(getAdapterPosition()).getCeleb() != null) {
                    Log.e("Card_data", "Celebr" + items.get(getAdapterPosition()).getCeleb().get(0).getName());
                    testing.newsCardOnClick(items.get(getAdapterPosition()).getCeleb().get(0).getProfilePic(),
                            items.get(getAdapterPosition()).getCeleb().get(0).getName(),
                            items.get(getAdapterPosition()).getCeleb().get(0).getType(),
                            items.get(getAdapterPosition()).getProfilePic(),
                            items.get(getAdapterPosition()).getTitle(),
                            items.get(getAdapterPosition()).getText(), new NewsOnClickFragment(),
                            items.get(getAdapterPosition()).getContentType()
                    );
                } else {
                    testing.newsCardOnClick("",
                            "",
                            "",
                            items.get(getAdapterPosition()).getProfilePic(),
                            items.get(getAdapterPosition()).getTitle(),
                            items.get(getAdapterPosition()).getText(), new NewsOnClickFragment(),
                            items.get(getAdapterPosition()).getContentType());
                }
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
                        .replace(R.id.main_container, new GalleryCardClick())
                        .addToBackStack("")
                        .commit();
            } else if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (items.get(getAdapterPosition()).getMovie() != null) {
                    testing.test(items.get(getAdapterPosition()).getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (items.get(getAdapterPosition()).getCeleb() != null) {
                    testing.test(items.get(getAdapterPosition()).getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
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
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (items.get(getAdapterPosition()).getMovie() != null) {
                    testing.test(items.get(getAdapterPosition()).getMovie().get(0).getSlug(), new MovieFragment(), 1);
                    Log.e("movie-making","movie making");
                } else if (items.get(getAdapterPosition()).getCeleb() != null) {
                    Log.e("celeb-making","celeb making");
                    testing.test(items.get(getAdapterPosition()).getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            }
            else if (view.getId() == R.id.card_description_linear) {
                if (items.get(getAdapterPosition()).getMovie() != null) {
                    Log.e("Card_data", "Movies" + items.get(getAdapterPosition()).getMovie().get(0).getName());
                    testing.newsCardOnClick(items.get(getAdapterPosition()).getMovie().get(0).getProfilePic(),
                            items.get(getAdapterPosition()).getMovie().get(0).getName(),
                            items.get(getAdapterPosition()).getMovie().get(0).getType(),
                            items.get(getAdapterPosition()).getProfilePic(),
                            items.get(getAdapterPosition()).getTitle(),
                            " ", new NewsOnClickFragment(),items.get(getAdapterPosition()).getContentType()
                    );
                } else if (items.get(getAdapterPosition()).getCeleb() != null) {
                    Log.e("Card_data", "Celebr" + items.get(getAdapterPosition()).getCeleb().get(0).getName());
                    testing.newsCardOnClick(items.get(getAdapterPosition()).getCeleb().get(0).getProfilePic(),
                            items.get(getAdapterPosition()).getCeleb().get(0).getName(),
                            items.get(getAdapterPosition()).getCeleb().get(0).getType(),
                            items.get(getAdapterPosition()).getProfilePic(),
                            items.get(getAdapterPosition()).getTitle(),
                            " ", new NewsOnClickFragment(),items.get(getAdapterPosition()).getContentType()
                    );
                } else {
                    testing.newsCardOnClick("",
                            "",
                            "",
                            items.get(getAdapterPosition()).getProfilePic(),
                            items.get(getAdapterPosition()).getTitle(),
                            " ", new NewsOnClickFragment(),items.get(getAdapterPosition()).getContentType());
                }
            }
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
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (items.get(getAdapterPosition()).getCeleb() == null) {
                    testing.test(items.get(getAdapterPosition()).getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (items.get(getAdapterPosition()).getMovie() == null) {
                    testing.test(items.get(getAdapterPosition()).getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            }
            else if (view.getId() == R.id.card_description_linear) {
                if (items.get(getAdapterPosition()).getMovie() != null) {
                    Log.e("Card_data", "Movies" + items.get(getAdapterPosition()).getMovie().get(0).getName());
                    testing.newsCardOnClick(items.get(getAdapterPosition()).getMovie().get(0).getProfilePic(),
                            items.get(getAdapterPosition()).getMovie().get(0).getName(),
                            items.get(getAdapterPosition()).getMovie().get(0).getType(),
                            items.get(getAdapterPosition()).getProfilePic(),
                            items.get(getAdapterPosition()).getTitle(),
                            " ", new NewsOnClickFragment(),items.get(getAdapterPosition()).getContentType()
                    );
                } else if (items.get(getAdapterPosition()).getCeleb() != null) {
                    Log.e("Card_data", "Celebr" + items.get(getAdapterPosition()).getCeleb().get(0).getName());
                    testing.newsCardOnClick(items.get(getAdapterPosition()).getCeleb().get(0).getProfilePic(),
                            items.get(getAdapterPosition()).getCeleb().get(0).getName(),
                            items.get(getAdapterPosition()).getCeleb().get(0).getType(),
                            items.get(getAdapterPosition()).getProfilePic(),
                            items.get(getAdapterPosition()).getTitle(),
                            " ", new NewsOnClickFragment(),items.get(getAdapterPosition()).getContentType()
                    );
                } else {
                    testing.newsCardOnClick("",
                            "",
                            "",
                            items.get(getAdapterPosition()).getProfilePic(),
                            items.get(getAdapterPosition()).getTitle(),
                            " ", new NewsOnClickFragment(),items.get(getAdapterPosition()).getContentType());
                }
            }
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
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            card_description_linear = (LinearLayout) itemView.findViewById(R.id.card_description_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_description_linear.setOnClickListener(this);
            card_gallary1_img1.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            globalData.a = 1;
            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_image)) {
                if (items.get(getAdapterPosition()).getCeleb() == null) {
                    testing.test(items.get(getAdapterPosition()).getMovie().get(0).getSlug(), new MovieFragment(), 1);
                } else if (items.get(getAdapterPosition()).getMovie() == null) {
                    testing.test(items.get(getAdapterPosition()).getCeleb().get(0).getSlug(), new CelebrityFragment(), 2);
                }
            } else if (view.getId() == R.id.card_description_linear) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new AuctionDetailFragment())
                        .addToBackStack("")
                        .commit();
            } else if (view.getId() == R.id.card_gallary1_img1) {
                testing.galleryCardOnClick(items.get(getAdapterPosition()).getMedia().getGallery(),
                        items.get(getAdapterPosition()).getCeleb().get(0).getName(),
                        items.get(getAdapterPosition()).getCeleb().get(0).getProfilePic(), items.get(getAdapterPosition()).getCeleb().get(0).getType(),
                        items.get(getAdapterPosition()).getTitle(), new GalleryCardClick());
            }

        }
    }
}
