package com.flikster.HomeActivity.FashionFragment.FashionType.MenFashionFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flikster.R;
import com.flikster.Util.ExpandedGridView;
import com.flikster.Util.SharedPrefsUtil;
import com.flikster.Util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by abhishek on 23-10-2017.
 */

public class MenFashionFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RecyclerView.LayoutManager layoutManager;
    Context context;
    List<Integer> type = new ArrayList<>();
    FragmentManager fragmentManager;
    MenFashionFragmentAdapterViewHolder menFashionFragmentAdapterViewHolder;
    MenFashionFragmentAdapterVideosViewHolder menFashionFragmentAdapterVideosViewHolder;
//    MenFashionFragmentListAdapterViewHolder menFashionFragmentlistAdapterViewHolder;

    public MenFashionFragmentAdapter(Context context, FragmentManager fragmentManager) {
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        type.add(5);
        type.add(6);
        type.add(7);
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celeb_profile, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv_one, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_product_singleimg, parent, false);
            return new ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv_one, parent, false);
            return new ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_product_singleimg, parent, false);
            return new ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv_one, parent, false);
            return new ViewHolder6(view);
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_product_singleimg, parent, false);
            return new ViewHolder7(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_profile, parent, false);
            return new ViewHolder1(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
            if (SharedPrefsUtil.getStringPreference(context, "HEADER_NAME").equals("MEN")) {
                ((ViewHolder1) holder).card_celebrity_feed_profile_layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.white_radius_yellow_fill));
                ((ViewHolder1) holder).card_celebrity_feed_profile_coverpic.setBackgroundColor(context.getResources().getColor(R.color.yellowthink));
                ((ViewHolder1) holder).fashionname.setText("Men Fashion");
                ((ViewHolder1) holder).profileimg.setImageResource(R.drawable.menuser);
            } else {
                ((ViewHolder1) holder).card_celebrity_feed_profile_layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.white_radius_womentab));
                ((ViewHolder1) holder).card_celebrity_feed_profile_coverpic.setBackgroundColor(context.getResources().getColor(R.color.dark_blue));
                ((ViewHolder1) holder).profileimg.setImageResource(R.drawable.womenuser);
//                ((ViewHolder1) holder).profileimg.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.womenuser));
                ((ViewHolder1) holder).fashionname.setText("Women Fashion");
            }
        } else if (holder.getItemViewType() == 2) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            layoutManager = new GridLayoutManager(context, 2);

            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            menFashionFragmentAdapterViewHolder = new MenFashionFragmentAdapterViewHolder(context, fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(menFashionFragmentAdapterViewHolder);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setBackgroundColor(context.getResources().getColor(R.color.style_main_background));
//            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.addItemDecoration(new SpacesItemDecoration(context));
        } else if (holder.getItemViewType() == 3) {
        } else if (holder.getItemViewType() == 4) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            layoutManager = new GridLayoutManager(context, 2);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            menFashionFragmentAdapterViewHolder = new MenFashionFragmentAdapterViewHolder(context, fragmentManager);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(menFashionFragmentAdapterViewHolder);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setBackgroundColor(context.getResources().getColor(R.color.style_main_background));
        } else if (holder.getItemViewType() == 5) {
        } else if (holder.getItemViewType() == 6) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            menFashionFragmentAdapterVideosViewHolder = new MenFashionFragmentAdapterVideosViewHolder(context, fragmentManager);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(menFashionFragmentAdapterVideosViewHolder);
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

    /*@Override
    public int getItemViewType(int position) {
        return position;
    }*/

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView fashionname;
        ImageView card_celebrity_feed_profile_coverpic, profileimg;
        LinearLayout card_celebrity_feed_profile_layout;

        public ViewHolder1(View itemView) {
            super(itemView);
            profileimg = (ImageView) itemView.findViewById(R.id.profileimg);
            card_celebrity_feed_profile_layout = (LinearLayout) itemView.findViewById(R.id.card_celebrity_feed_profile_layout);
            fashionname = (TextView) itemView.findViewById(R.id.fashionname);
            card_celebrity_feed_profile_coverpic = (ImageView) itemView.findViewById(R.id.card_celebrity_feed_profile_coverpic);
//            card_celebrity_feed_profile_role  = (TextView) itemView.findViewById(R.id.card_celebrity_feed_profile_role);
//            card_celebrity_feed_profile_name.setVisibility(View.GONE);
//            card_celebrity_feed_profile_role.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        ExpandedGridView expandgrid;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder2(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
//            expandgrid = (ExpandedGridView) itemView.findViewById(R.id.expandgrid);
            fragment_common_recyclerview_with_tv_title.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder3(View itemView) {
            super(itemView);

        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder4(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder5(View itemView) {
            super(itemView);

        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fragment_common_recyclerview_with_tv_title;
        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder6(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolder7 extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder7(View itemView) {
            super(itemView);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
