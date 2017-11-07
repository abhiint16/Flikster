package com.flikster.HomeActivity.FeedFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductOnClick;
import com.flikster.R;

/**
 * Created by abhishek on 11-10-2017.
 */

public class FeedCelebrityRecyclerItemAdapter extends RecyclerView.Adapter<FeedCelebrityRecyclerItemAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((ViewHolder) holder).profile_image.setImageResource(R.drawable.ranveer1);
        ((ViewHolder) holder).card_gallary1_img1.setImageResource(R.drawable.ranveer2);
        ((ViewHolder) holder).tv_tag_desc.setText("#Actor");
        ((ViewHolder) holder).tv_tag_name.setText("Ranveer Singh");
        ((ViewHolder) holder).tv_name.setText("Ranveer Singh at the set of Padmavati");
        ((ViewHolder) holder).tv_description.setText("Ranveer Singh at the set of Padmavati. He was caught red handed by" +
                "our camera crew while he was kissing his girlfriend Deepika.");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, tv_description;
        LinearLayout header_linear, card_description_linear;

        public ViewHolder(View itemView) {
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
//            if ((view.getId() == R.id.header_linear) || (view.getId() == R.id.profile_name)) {
//                fragmentManager.beginTransaction()
//                        .replace(R.id.main_container, new CelebrityFragment())
//                        .addToBackStack("")
//                        .commit();
//            } else if (view.getId() == R.id.card_description_linear) {
//                fragmentManager.beginTransaction()
//                        .replace(R.id.main_container, new NewsOnClickFragment())
//                        .addToBackStack("")
//                        .commit();
//            }

        }
    }
}
