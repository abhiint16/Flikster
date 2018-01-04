package com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductOnClick;
import com.flikster.HomeActivity.FashionFragment.FashionType.CommonAllProductPage.CommonAllProductPage;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.R;
import com.flikster.Util.Common;
import com.flikster.Util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 23-10-2017.
 */

public class AllStoreFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    AllStoreInnerData hits;
    AllStoreFragment.AllStoreInterafce allStoreInterafce;
    String profilePic = "";
    String name = "";
    String title = "";
    List<String> role = new ArrayList<>();
    String price = "";
    String userId = "PAWAN_KALYAN";
    String userName = "null";

    public AllStoreFragmentAdapter(Context context, AllStoreInnerData hits, AllStoreFragment.AllStoreInterafce allStoreInterafce) {
        this.context = context;
        this.hits = hits;
        this.allStoreInterafce = allStoreInterafce;
    }

    public void updateDataPagination(List<AllStoreInnerData.AllStoreInnerMoreData> a)
    {
        this.hits.getHits().addAll(a);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details1, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details2, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details3, parent, false);
            return new ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details4, parent, false);
            return new ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_details4_plus, parent, false);
            return new ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testingnull, parent, false);
            return new ViewHolder6(view);
        }
        return null;
    }

    public String formatRole(List<String> role) {
        String roleString = "";
        for (int i = 0; i < role.size(); i++) {
            roleString = roleString + ", " + role.get(i);
        }
        return roleString;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null && !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
            userId = SharedPrefsUtil.getStringPreference(context, "USER_ID");
            Log.e("LoginUserId", userId);
        }
        if (SharedPrefsUtil.getStringPreference(context, "USER_NAME") != null && SharedPrefsUtil.getStringPreference(context, "USER_NAME") != null) {
            userName = SharedPrefsUtil.getStringPreference(context, "USER_NAME");
            Log.e("LoginUserName", userName);
        }

        if (holder.getItemViewType() == 1) {
            ((ViewHolder1) holder).followbtn.setText("BUY");
            ((ViewHolder1) holder).card_fashion_details1_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder1) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder1) holder).card_description_with_price_title.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null && hits.getHits().get(position).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder1) holder).tv_tag_name.setText(hits.getHits().get(position).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder1) holder).profile_image);
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder1) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder1) holder).card_fashion_details1_img);
            }

            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position).get_source().getId(), ((ViewHolder1) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position).get_source().getId(), ((ViewHolder1) holder).ib_bookmark, context);

        } else if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).followbtn.setText("BUY");
            ((ViewHolder2) holder).card_fashion_details2_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder2) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder2) holder).card_description_with_price_title.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null && hits.getHits().get(position).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder2) holder).tv_tag_name.setText(hits.getHits().get(position).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder2) holder).profile_image);
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder2) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder2) holder).card_fashion_details2_img1);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(1).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder2) holder).card_fashion_details2_img2);
            }
            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position).get_source().getId(), ((ViewHolder2) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position).get_source().getId(), ((ViewHolder2) holder).ib_bookmark, context);

        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3) holder).followbtn.setText("BUY");
            ((ViewHolder3) holder).card_fashion_details3_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder3) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder3) holder).card_description_with_price_title.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null && hits.getHits().get(position).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder3) holder).tv_tag_name.setText(hits.getHits().get(position).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder3) holder).profile_image);
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder3) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder3) holder).card_fashion_details3_img1);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(1).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder3) holder).card_fashion_details3_img2);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(2).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder3) holder).card_fashion_details3_img3);
            }

            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position).get_source().getId(), ((ViewHolder3) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position).get_source().getId(), ((ViewHolder3) holder).ib_bookmark, context);
        } else if (holder.getItemViewType() == 4) {
            ((ViewHolder4) holder).followbtn.setText("BUY");
            ((ViewHolder4) holder).card_fashion_details4_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder4) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder4) holder).card_description_with_price_title.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null && hits.getHits().get(position).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder4) holder).tv_tag_name.setText(hits.getHits().get(position).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap().into(((ViewHolder4) holder).profile_image);
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder4) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder4) holder).card_fashion_details4_img1);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(1).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder4) holder).card_fashion_details4_img2);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(2).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder4) holder).card_fashion_details4_img3);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(3).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder4) holder).card_fashion_details4_img4);
            }
            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position).get_source().getId(), ((ViewHolder4) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position).get_source().getId(), ((ViewHolder4) holder).ib_bookmark, context);
        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).followbtn.setText("BUY");
            ((ViewHolder5) holder).card_fashion_details4_plus_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getPrice() != null) {
                ((ViewHolder5) holder).card_description_with_price_price.setText("Rs. " + hits.getHits().get(position).get_source().getPrice() + " /-");
            }
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder5) holder).card_description_with_price_title.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null && hits.getHits().get(position).get_source().getCeleb().size() != 0) {
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getName() != null)
                    ((ViewHolder5) holder).tv_tag_name.setText(hits.getHits().get(position).get_source().getCeleb().get(0).getName());
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic() != null)
                    Glide.with(context).load(hits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic())
                            .asBitmap()
                            .into(((ViewHolder5) holder).profile_image);
                if (hits.getHits().get(position).get_source().getCeleb().get(0).getRole() != null &&
                        hits.getHits().get(position).get_source().getCeleb().get(0).getRole().size() != 0)
                    ((ViewHolder5) holder).tv_tag_desc.setText(formatRole(hits.getHits().get(position).get_source().getCeleb().get(0).getRole()));
            }
            if (hits.getHits().get(position).get_source().getImageGallery() != null) {
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(0).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img1);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(1).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img2);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(2).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img3);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(3).trim())
                        .thumbnail(Glide.with(context).load(R.drawable.loading_gif3))
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img4);
            }
            ((ViewHolder5) holder).card_fashion_details4_plus_text.setText("+ " + (hits.getHits().get(position).get_source().getImageGallery().size() - 4));

            new PostRetrofit().checkForLike("like", userId, hits.getHits().get(position).get_source().getId(), ((ViewHolder5) holder).ib_like, context);
            new PostRetrofit().checkForBookmark("bookmark", userId, hits.getHits().get(position).get_source().getId(), ((ViewHolder5) holder).ib_bookmark, context);
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return hits.getHits().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (hits.getHits().get(position).get_source().getImageGallery() != null && hits.getHits().get(position).get_source().getImageGallery().size() != 0) {
            switch (hits.getHits().get(position).get_source().getImageGallery().size()) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
            }
        }
        return 6;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details1_img;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name, card_fashion_details1_txt;
        LinearLayout card_fashion_details1_img_container;
        LinearLayout header_linear;
        ImageView profile_image;
        ImageButton card_footer_share, ib_like, ib_bookmark, card_comment_text_send_btn;

        public ViewHolder1(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details1_img = (ImageView) itemView.findViewById(R.id.card_fashion_details1_img);
            card_fashion_details1_txt = (TextView) itemView.findViewById(R.id.card_fashion_details1_txt);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_fashion_details1_img_container = (LinearLayout) itemView.findViewById(R.id.card_fashion_details1_img_container);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_fashion_details1_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);


            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);

            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(), context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            });


        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                checkForLoggedIn(getAdapterPosition());
            } else if (v.getId() == R.id.card_fashion_details1_img_container) {
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic() != null)
                        profilePic = hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic();
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName() != null)
                        name = hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName();
                }
                if (hits.getHits().get(getAdapterPosition()).get_source().getPrice() != null)
                    price = hits.getHits().get(getAdapterPosition()).get_source().getPrice();
                if (hits.getHits().get(getAdapterPosition()).get_source().getName() != null)
                    title = hits.getHits().get(getAdapterPosition()).get_source().getName();
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole() != null &&
                            hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole().size() != 0)
                        role.addAll(hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole());
                }
                allStoreInterafce.onGalleryContainerClick(hits.getHits().get(getAdapterPosition()).get_source().getId(),
                        hits.getHits().get(getAdapterPosition()).get_source().getSize(), "abhiint",
                        hits.getHits().get(getAdapterPosition()).get_source().getPrice(),
                        hits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        hits.getHits().get(getAdapterPosition()).get_source().getProductInfo(),
                        hits.getHits().get(getAdapterPosition()).get_source().getSlug(),
                        hits.getHits().get(getAdapterPosition()).get_source().getImageGallery(),
                        profilePic, role, name, title, new CommonAllProductPage());
            } else if ((v.getId() == R.id.header_linear) || (v.getId() == R.id.profile_image)) {
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && hits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    allStoreInterafce.toCelebPage(hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            }
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details2_img1, card_fashion_details2_img2;
        TextView card_description_with_price_title, card_description_with_price_desc,
                card_description_with_price_price, tv_tag_desc, tv_tag_name, card_fashion_details2_txt;
        LinearLayout card_fashion_details2_img_container;
        LinearLayout header_linear;
        ImageView profile_image;
        ImageButton card_footer_share, ib_like, ib_bookmark, card_comment_text_send_btn;

        public ViewHolder2(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details2_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details2_img1);
            card_fashion_details2_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details2_img2);
            card_fashion_details2_txt = (TextView) itemView.findViewById(R.id.card_fashion_details2_txt);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_fashion_details2_img_container = (LinearLayout) itemView.findViewById(R.id.card_fashion_details2_img_container);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_fashion_details2_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);

            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);

            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(), context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                checkForLoggedIn(getAdapterPosition());
            } else if (v.getId() == R.id.card_fashion_details2_img_container) {
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic() != null)
                        profilePic = hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic();
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName() != null)
                        name = hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName();
                }
                if (hits.getHits().get(getAdapterPosition()).get_source().getPrice() != null)
                    price = hits.getHits().get(getAdapterPosition()).get_source().getPrice();
                if (hits.getHits().get(getAdapterPosition()).get_source().getName() != null)
                    title = hits.getHits().get(getAdapterPosition()).get_source().getName();
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole() != null &&
                            hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole().size() != 0)
                        role.addAll(hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole());
                }
                allStoreInterafce.onGalleryContainerClick(hits.getHits().get(getAdapterPosition()).get_source().getId(),
                        hits.getHits().get(getAdapterPosition()).get_source().getSize(), "abhiint",
                        hits.getHits().get(getAdapterPosition()).get_source().getPrice(),
                        hits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        hits.getHits().get(getAdapterPosition()).get_source().getProductInfo(),
                        hits.getHits().get(getAdapterPosition()).get_source().getSlug(),
                        hits.getHits().get(getAdapterPosition()).get_source().getImageGallery(),
                        profilePic, role, name, title, new CommonAllProductPage());
            } else if ((v.getId() == R.id.header_linear) || (v.getId() == R.id.profile_image)) {
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && hits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    allStoreInterafce.toCelebPage(hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            }

        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details3_img1, card_fashion_details3_img2, card_fashion_details3_img3;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name, card_fashion_details3_txt;
        LinearLayout card_my_style_one_big_img_container;
        LinearLayout header_linear;
        ImageView profile_image;
        ImageButton card_footer_share, ib_like, ib_bookmark, card_comment_text_send_btn;

        public ViewHolder3(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details3_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img1);
            card_fashion_details3_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img2);
            card_fashion_details3_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img3);
            card_fashion_details3_txt = (TextView) itemView.findViewById(R.id.card_fashion_details3_txt);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_my_style_one_big_img_container = (LinearLayout) itemView.findViewById(R.id.card_my_style_one_big_img_container);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_my_style_one_big_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);

            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (hits.getHits().get(getAdapterPosition()).get_source()
                            .getCeleb() != null && hits.getHits().get(getAdapterPosition()).get_source()
                            .getCeleb().size() != 0) {
                        Common.shareClick(hits.getHits().get(getAdapterPosition())
                                .get_source().getCeleb().get(0).getProfilePic(), context);
                    } else {
                        Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(), context);
                    }

                    //Common.shareClick(hits.getHits().get(getAdapterPosition() - 1).get_source().getProfilePic(), context);
                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                checkForLoggedIn(getAdapterPosition());
            } else if (v.getId() == R.id.card_my_style_one_big_img_container) {
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic() != null)
                        profilePic = hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic();
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName() != null)
                        name = hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName();
                }
                if (hits.getHits().get(getAdapterPosition()).get_source().getPrice() != null)
                    price = hits.getHits().get(getAdapterPosition()).get_source().getPrice();
                if (hits.getHits().get(getAdapterPosition()).get_source().getName() != null)
                    title = hits.getHits().get(getAdapterPosition()).get_source().getName();
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole() != null &&
                            hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole().size() != 0)
                        role.addAll(hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole());
                }
                allStoreInterafce.onGalleryContainerClick(hits.getHits().get(getAdapterPosition()).get_source().getId(),
                        hits.getHits().get(getAdapterPosition()).get_source().getSize(), "abhiint",
                        hits.getHits().get(getAdapterPosition()).get_source().getPrice(),
                        hits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        hits.getHits().get(getAdapterPosition()).get_source().getProductInfo(),
                        hits.getHits().get(getAdapterPosition()).get_source().getSlug(),
                        hits.getHits().get(getAdapterPosition()).get_source().getImageGallery(),
                        profilePic, role, name, title, new CommonAllProductPage());
            } else if ((v.getId() == R.id.header_linear) || (v.getId() == R.id.profile_image)) {
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && hits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    allStoreInterafce.toCelebPage(hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            }
        }
    }


    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details4_img1, card_fashion_details4_img2, card_fashion_details4_img3,
                card_fashion_details4_img4;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name, card_fashion_details4_txt;
        LinearLayout card_fashion_deatails4_img_container;
        LinearLayout header_linear;
        ImageView profile_image;
        ImageButton card_footer_share, ib_like, ib_bookmark, card_comment_text_send_btn;

        public ViewHolder4(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details4_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img1);
            card_fashion_details4_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img2);
            card_fashion_details4_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img3);
            card_fashion_details4_img4 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img4);
            card_fashion_details4_txt = (TextView) itemView.findViewById(R.id.card_fashion_details4_txt);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_fashion_deatails4_img_container = (LinearLayout) itemView.findViewById(R.id.card_fashion_deatails4_img_container);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_fashion_deatails4_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);

            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (hits.getHits().get(getAdapterPosition()).get_source()
                            .getCeleb() != null && hits.getHits().get(getAdapterPosition()).get_source()
                            .getCeleb().size() != 0) {
                        Common.shareClick(hits.getHits().get(getAdapterPosition())
                                .get_source().getCeleb().get(0).getProfilePic(), context);
                    } else {
                    }

                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                checkForLoggedIn(getAdapterPosition());
            } else if (v.getId() == R.id.card_fashion_deatails4_img_container) {
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic() != null)
                        profilePic = hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic();
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName() != null)
                        name = hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName();
                }
                if (hits.getHits().get(getAdapterPosition()).get_source().getPrice() != null)
                    price = hits.getHits().get(getAdapterPosition()).get_source().getPrice();
                if (hits.getHits().get(getAdapterPosition()).get_source().getName() != null)
                    title = hits.getHits().get(getAdapterPosition()).get_source().getName();
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole() != null &&
                            hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole().size() != 0)
                        role.addAll(hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole());
                }
                allStoreInterafce.onGalleryContainerClick(hits.getHits().get(getAdapterPosition()).get_source().getId(),
                        hits.getHits().get(getAdapterPosition()).get_source().getSize(), "abhiint",
                        hits.getHits().get(getAdapterPosition()).get_source().getPrice(),
                        hits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        hits.getHits().get(getAdapterPosition()).get_source().getProductInfo(),
                        hits.getHits().get(getAdapterPosition()).get_source().getSlug(),
                        hits.getHits().get(getAdapterPosition()).get_source().getImageGallery(),
                        profilePic, role, name, title, new CommonAllProductPage());
            } else if ((v.getId() == R.id.header_linear) || (v.getId() == R.id.profile_image)) {
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && hits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    allStoreInterafce.toCelebPage(hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            }

        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details4_plus_img1, card_fashion_details4_plus_img2, card_fashion_details4_plus_img3,
                card_fashion_details4_plus_img4;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name, card_fashion_details4_plus_text, card_fashion_details4_plus_txt;
        RelativeLayout card_fashion_details4_plus_img_container;
        LinearLayout header_linear;
        ImageView profile_image;
        ImageButton card_footer_share, ib_like, ib_bookmark, card_comment_text_send_btn;

        public ViewHolder5(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details4_plus_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img1);
            card_fashion_details4_plus_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img2);
            card_fashion_details4_plus_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img3);
            card_fashion_details4_plus_img4 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img4);
            card_fashion_details4_plus_txt = (TextView) itemView.findViewById(R.id.card_fashion_details4_plus_txt);
            card_fashion_details4_plus_text = (TextView) itemView.findViewById(R.id.card_fashion_details4_plus_text);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            //card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            card_fashion_details4_plus_img_container = (RelativeLayout) itemView.findViewById(R.id.card_fashion_details4_plus_img_container);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            header_linear = (LinearLayout) itemView.findViewById(R.id.header_linear);
            header_linear.setOnClickListener(this);
            profile_image.setOnClickListener(this);
            card_fashion_details4_plus_img_container.setOnClickListener(this);
            followbtn.setOnClickListener(this);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            ib_bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark);
            card_footer_share = (ImageButton) itemView.findViewById(R.id.card_footer_share);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);

            card_footer_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (hits.getHits().get(getAdapterPosition()).get_source()
                            .getCeleb() != null && hits.getHits().get(getAdapterPosition()).get_source()
                            .getCeleb().size() != 0) {
                        Common.shareClick(hits.getHits().get(getAdapterPosition())
                                .get_source().getCeleb().get(0).getProfilePic(), context);
                    } else {
                    }


                }
            });
            ib_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.likeAndUnLikeEvent(context, ib_like, userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            });
            ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.bookmarkAndUnBookmarkeEvent(context, ib_bookmark, userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                checkForLoggedIn(getAdapterPosition());
            } else if ((v.getId() == R.id.header_linear) || (v.getId() == R.id.profile_image)) {
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && hits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                    allStoreInterafce.toCelebPage(hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getSlug(),
                            new CelebrityFragment(), userId, hits.getHits().get(getAdapterPosition()).get_source().getId());
                }
            } else if (v.getId() == R.id.card_fashion_details4_plus_img_container) {
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic() != null)
                        profilePic = hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic();
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName() != null)
                        name = hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName();
                }
                if (hits.getHits().get(getAdapterPosition()).get_source().getPrice() != null)
                    price = hits.getHits().get(getAdapterPosition()).get_source().getPrice();
                if (hits.getHits().get(getAdapterPosition()).get_source().getName() != null)
                    title = hits.getHits().get(getAdapterPosition()).get_source().getName();
                if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
                    if (hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole() != null &&
                            hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole().size() != 0)
                        role.addAll(hits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getRole());
                }
                allStoreInterafce.onGalleryContainerClick(hits.getHits().get(getAdapterPosition()).get_source().getId(),
                        hits.getHits().get(getAdapterPosition()).get_source().getSize(), "abhiint",
                        hits.getHits().get(getAdapterPosition()).get_source().getPrice(),
                        hits.getHits().get(getAdapterPosition()).get_source().getProfilePic(),
                        hits.getHits().get(getAdapterPosition()).get_source().getProductInfo(),
                        hits.getHits().get(getAdapterPosition()).get_source().getSlug(),
                        hits.getHits().get(getAdapterPosition()).get_source().getImageGallery(),
                        profilePic, role, name, title, new CommonAllProductPage());
            }
        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;

        public ViewHolder6(View itemView) {
            super(itemView);
            /*followbtn = (Button) itemView.findViewById(R.id.followbtn);
            followbtn.setText("BUY");
            followbtn.setOnClickListener(this);*/
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();

            }
        }
    }

    public void checkForLoggedIn(int pos) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            Toast.makeText(context, "You need to first Login", Toast.LENGTH_SHORT).show();
            return;
        }
        if (hits.getHits().get(pos).get_source().getSize() != null) {
            allStoreInterafce.onBuyClick(hits.getHits().get(pos).get_source().getId(),
                    hits.getHits().get(pos).get_source().getSize(), "abhiint",
                    hits.getHits().get(pos).get_source().getPrice(),
                    hits.getHits().get(pos).get_source().getProfilePic(),
                    hits.getHits().get(pos).get_source().getProductInfo(),
                    hits.getHits().get(pos).get_source().getSlug(),
                    hits.getHits().get(pos).get_source().getImageGallery(), new ProductOnClick());
        }

    }

}
