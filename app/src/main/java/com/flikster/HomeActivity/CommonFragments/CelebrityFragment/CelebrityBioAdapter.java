package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import android.content.Context;
import android.media.Image;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieInfoAdapter;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-10-2017.
 */

public class CelebrityBioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    CelebrityBioAdapterVideoViewHolder celebrityBioAdapterVideoViewHolder;
    CelebrityBioAdapterFamilyViewHolder celebrityBioAdapterFamilyViewHolder;
    CelebrityBioAdapterFilmographyViewHolder celebrityBioAdapterFilmographyViewHolder;
    CelebrityBioAdapterPeersViewHolder celebrityBioAdapterPeersViewHolder;
    CelebrityBioAdapterImagesViewHolder celebrityBioAdapterImagesViewHolder;
    List<CelebrityData.CelebrityInnerData> items;
    Boolean biographyBoolean = true;

    public CelebrityBioAdapter(Context context, FragmentManager fragmentManager, List<CelebrityData.CelebrityInnerData> items) {
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
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_profile, parent, false);
            return new CelebrityBioAdapter.ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new CelebrityBioAdapter.ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new CelebrityBioAdapter.ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_news, parent, false);
            return new CelebrityBioAdapter.ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new CelebrityBioAdapter.ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new CelebrityBioAdapter.ViewHolder6(view);
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new CelebrityBioAdapter.ViewHolder7(view);
        } else if (viewType == 8) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_awards, parent, false);
            return new CelebrityBioAdapter.ViewHolder8(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_upcoming, parent, false);
            return new CelebrityBioAdapter.ViewHolder9(view);
        }
    }

    public String formatDOB(String dob) {
        String subString = dob.substring(3, dob.indexOf("GMT") - 9);
        return subString;
    }

    public String formatRole() {
        String roleString = "";
        for (int i = 0; i < items.get(0).role.size(); i++) {
            if (i < items.get(0).role.size() - 1)
                roleString = roleString + items.get(0).getRole().get(i) + " , ";
            else
                roleString = roleString + items.get(0).getRole().get(i);
        }
        return roleString;
    }

    public Spannable formatBiography() {
        String storyLine = items.get(0).getBiography();
        Spannable spannable = null;
        if (storyLine.length() > 200) {
            storyLine = storyLine.substring(0, 190) + " ...Click To Expand";
            spannable = new SpannableString(storyLine);
            spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorAccent)), 190
                    , storyLine.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        return spannable;
    }

    public void storyLineOnClick(CelebrityBioAdapter.ViewHolder1 holder) {
        String storyLine = items.get(0).getBiography();
        if (storyLine.length() > 200 && biographyBoolean == true) {
            holder.card_celebrity_bio_profile_biography.setText(items.get(0).getBiography());
            biographyBoolean = false;
        } else if (storyLine.length() > 200 && biographyBoolean == false) {
            holder.card_celebrity_bio_profile_biography.setText(formatBiography());
            biographyBoolean = true;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
            Log.e("DOB", "Shiva" + items.size());
            if (items.size() != 0){
                try {
                    if (items.get(0).getBiography() != null && !items.get(0).getBiography().isEmpty() && items.get(0).getBiography().length() != 0) {
                        if (items.get(0).getBiography().length() <= 200)
                            ((ViewHolder1) holder).card_celebrity_bio_profile_biography.setText(items.get(0).getBiography());
                        else if (items.get(0).getBiography().length() > 200)
                            ((ViewHolder1) holder).card_celebrity_bio_profile_biography.setText(formatBiography(), TextView.BufferType.SPANNABLE);
                    }

                    ((ViewHolder1) holder).card_celebrity_bio_profile_biography.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            storyLineOnClick(((ViewHolder1) holder));
                        }
                    });
                    Log.e("DOB", "Shiva" + formatDOB(items.get(0).getDateOfBirth()));
                    ((ViewHolder1) holder).card_celebrity_bio_profile_dob.setText(formatDOB(items.get(0).getDateOfBirth() + ""));
                    ((ViewHolder1) holder).card_celebrity_bio_profile_name.setText(items.get(0).getName() + "");
                    ((ViewHolder1) holder).card_celebrity_bio_profile_pob.setText(items.get(0).getPlaceOfBirth() + "");
                    ((ViewHolder1) holder).card_celebrity_bio_profile_role.setText(formatRole() + "");
                    Glide.with(context).load(items.get(0).getProfilePic()).asBitmap().into(((ViewHolder1) holder).card_celebrity_bio_profile_profilepic);
                    Glide.with(context).load(items.get(0).getCoverPic()).asBitmap().into(((ViewHolder1) holder).card_celebrity_bio_profile_coverpic);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setText("Videos");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterVideoViewHolder = new CelebrityBioAdapterVideoViewHolder(fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterVideoViewHolder);
        } else if (holder.getItemViewType() == 3) {
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_title.setText("Images");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterImagesViewHolder = new CelebrityBioAdapterImagesViewHolder(context);
            ((ViewHolder3) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterImagesViewHolder);
        } else if (holder.getItemViewType() == 5) {
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_title.setText("Filmography");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterFilmographyViewHolder = new CelebrityBioAdapterFilmographyViewHolder();
            ((ViewHolder5) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterFilmographyViewHolder);
        } else if (holder.getItemViewType() == 6) {
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_title.setText("Peers");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterPeersViewHolder = new CelebrityBioAdapterPeersViewHolder();
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterPeersViewHolder);
        } else if (holder.getItemViewType() == 7) {
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_title.setVisibility(View.VISIBLE);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_title.setText("Family");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celebrityBioAdapterFamilyViewHolder = new CelebrityBioAdapterFamilyViewHolder();
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celebrityBioAdapterFamilyViewHolder);
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
        ImageView card_celebrity_bio_profile_coverpic, card_celebrity_bio_profile_profilepic;
        TextView card_celebrity_bio_profile_pob, card_celebrity_bio_profile_dob, card_celebrity_bio_profile_role, card_celebrity_bio_profile_biography, card_celebrity_bio_profile_name;

        public ViewHolder1(View itemView) {
            super(itemView);
            card_celebrity_bio_profile_coverpic = (ImageView) itemView.findViewById(R.id.card_celebrity_bio_profile_coverpic);
            card_celebrity_bio_profile_profilepic = (ImageView) itemView.findViewById(R.id.card_celebrity_bio_profile_profilepic);
            card_celebrity_bio_profile_pob = (TextView) itemView.findViewById(R.id.card_celebrity_bio_profile_pob);
            card_celebrity_bio_profile_dob = (TextView) itemView.findViewById(R.id.card_celebrity_bio_profile_dob);
            card_celebrity_bio_profile_role = (TextView) itemView.findViewById(R.id.card_celebrity_bio_profile_role);
            card_celebrity_bio_profile_biography = (TextView) itemView.findViewById(R.id.card_celebrity_bio_profile_biography);
            card_celebrity_bio_profile_name = (TextView) itemView.findViewById(R.id.card_celebrity_bio_profile_name);
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
        public ViewHolder4(View itemView) {
            super(itemView);
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
        }
    }

    public class ViewHolder8 extends RecyclerView.ViewHolder {
        public ViewHolder8(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder9 extends RecyclerView.ViewHolder {
        public ViewHolder9(View itemView) {
            super(itemView);
        }
    }

}
