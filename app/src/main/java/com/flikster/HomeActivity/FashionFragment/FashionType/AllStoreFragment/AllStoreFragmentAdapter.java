package com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.R;

import java.util.List;

/**
 * Created by abhishek on 23-10-2017.
 */

public class AllStoreFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    AllStoreInnerData hits;

    public AllStoreFragmentAdapter(Context context, AllStoreInnerData hits) {
        this.context = context;
        this.hits = hits;
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
        if (holder.getItemViewType() == 1) {
            ((ViewHolder1) holder).followbtn.setText("BUY");
            ((ViewHolder1) holder).card_fashion_details1_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder1) holder).card_description_with_price_title.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null&&hits.getHits().get(position).get_source().getCeleb().size()!=0) {
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
                        .into(((ViewHolder1) holder).card_fashion_details1_img);
            }
        } else if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).followbtn.setText("BUY");
            ((ViewHolder2) holder).card_fashion_details2_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder2) holder).card_description_with_price_title.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null&&hits.getHits().get(position).get_source().getCeleb().size()!=0) {
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
                        .into(((ViewHolder2) holder).card_fashion_details2_img1);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(1).trim())
                        .into(((ViewHolder2) holder).card_fashion_details2_img2);
            }
        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3) holder).followbtn.setText("BUY");
            ((ViewHolder3) holder).card_fashion_details3_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder3) holder).card_description_with_price_title.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null &&hits.getHits().get(position).get_source().getCeleb().size()!=0) {
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
                        .into(((ViewHolder3) holder).card_fashion_details3_img1);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(1).trim())
                        .into(((ViewHolder3) holder).card_fashion_details3_img2);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(2).trim())
                        .into(((ViewHolder3) holder).card_fashion_details3_img3);
            }
        } else if (holder.getItemViewType() == 4) {
            ((ViewHolder4) holder).followbtn.setText("BUY");
            ((ViewHolder4) holder).card_fashion_details4_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder4) holder).card_description_with_price_title.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null&&hits.getHits().get(position).get_source().getCeleb().size()!=0) {
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
                        .into(((ViewHolder4) holder).card_fashion_details4_img1);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(1).trim())
                        .into(((ViewHolder4) holder).card_fashion_details4_img2);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(2).trim())
                        .into(((ViewHolder4) holder).card_fashion_details4_img3);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(3).trim())
                        .into(((ViewHolder4) holder).card_fashion_details4_img4);
            }
        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).followbtn.setText("BUY");
            ((ViewHolder5) holder).card_fashion_details4_plus_txt.setVisibility(View.GONE);
            if (hits.getHits().get(position).get_source().getName() != null) {
                ((ViewHolder5) holder).card_description_with_price_title.setText(hits.getHits().get(position).get_source().getName());
            }
            if (hits.getHits().get(position).get_source().getCeleb() != null&&hits.getHits().get(position).get_source().getCeleb().size()!=0) {
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
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img1);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(1).trim())
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img2);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(2).trim())
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img3);
                Glide.with(context).load(hits.getHits().get(position).get_source().getImageGallery().get(3).trim())
                        .into(((ViewHolder5) holder).card_fashion_details4_plus_img4);
            }
            ((ViewHolder5) holder).card_fashion_details4_plus_text.setText("+ " + (hits.getHits().get(position).get_source().getImageGallery().size() - 4));
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
        ImageView card_fashion_details1_img, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name,card_fashion_details1_txt;

        public ViewHolder1(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details1_img = (ImageView) itemView.findViewById(R.id.card_fashion_details1_img);
            card_fashion_details1_txt=(TextView)itemView.findViewById(R.id.card_fashion_details1_txt);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            followbtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();

            }
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details2_img1, card_fashion_details2_img2, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc,
                card_description_with_price_price, tv_tag_desc, tv_tag_name,card_fashion_details2_txt;

        public ViewHolder2(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details2_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details2_img1);
            card_fashion_details2_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details2_img2);
            card_fashion_details2_txt=(TextView)itemView.findViewById(R.id.card_fashion_details2_txt);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            followbtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();

            }

        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details3_img1, card_fashion_details3_img2, card_fashion_details3_img3, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name,card_fashion_details3_txt;

        public ViewHolder3(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details3_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img1);
            card_fashion_details3_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img2);
            card_fashion_details3_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details3_img3);
            card_fashion_details3_txt=(TextView)itemView.findViewById(R.id.card_fashion_details3_txt);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();

            }
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details4_img1, card_fashion_details4_img2, card_fashion_details4_img3,
                card_fashion_details4_img4, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name,card_fashion_details4_txt;

        public ViewHolder4(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details4_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img1);
            card_fashion_details4_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img2);
            card_fashion_details4_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img3);
            card_fashion_details4_img4 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_img4);
            card_fashion_details4_txt=(TextView)itemView.findViewById(R.id.card_fashion_details4_txt);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();

            }
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_fashion_details4_plus_img1, card_fashion_details4_plus_img2, card_fashion_details4_plus_img3,
                card_fashion_details4_plus_img4, profile_image;
        TextView card_description_with_price_title, card_description_with_price_desc, card_description_with_price_price,
                tv_tag_desc, tv_tag_name, card_fashion_details4_plus_text,card_fashion_details4_plus_txt;

        public ViewHolder5(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_fashion_details4_plus_img1 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img1);
            card_fashion_details4_plus_img2 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img2);
            card_fashion_details4_plus_img3 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img3);
            card_fashion_details4_plus_img4 = (ImageView) itemView.findViewById(R.id.card_fashion_details4_plus_img4);
            card_fashion_details4_plus_txt=(TextView)itemView.findViewById(R.id.card_fashion_details4_plus_txt);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            card_fashion_details4_plus_text = (TextView) itemView.findViewById(R.id.card_fashion_details4_plus_text);
            card_description_with_price_title = (TextView) itemView.findViewById(R.id.card_description_with_price_title);
            card_description_with_price_desc = (TextView) itemView.findViewById(R.id.card_description_with_price_desc);
            card_description_with_price_price = (TextView) itemView.findViewById(R.id.card_description_with_price_price);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();

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
}
