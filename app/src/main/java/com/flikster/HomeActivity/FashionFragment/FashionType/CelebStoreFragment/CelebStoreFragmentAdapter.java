package com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.HomeActivity.FashionFragment.FashionType.MenFashionFragment.MenFashionFragmentAdapterViewHolder;
import com.flikster.R;
import com.flikster.Util.ExpandedGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 23-10-2017.
 */

public class CelebStoreFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RecyclerView.LayoutManager layoutManager;
    Context context;
    List<Integer> type = new ArrayList<>();
    FragmentManager fragmentManager;
    MenFashionFragmentAdapterViewHolder menFashionFragmentAdapterViewHolder;
//    MenFashionFragmentListAdapterViewHolder menFashionFragmentlistAdapterViewHolder;

    public CelebStoreFragmentAdapter(Context context, FragmentManager fragmentManager) {
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_profile, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_product_singleimg, parent, false);
            return new ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_product_singleimg, parent, false);
            return new ViewHolder5(view);
        } else if (viewType == 6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv, parent, false);
            return new ViewHolder6(view);
        } else if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_product_singleimg, parent, false);
            return new ViewHolder7(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_profile, parent, false);
            return new ViewHolder1(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
        } else if (holder.getItemViewType() == 2) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            layoutManager = new GridLayoutManager(context, 2);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            menFashionFragmentAdapterViewHolder = new MenFashionFragmentAdapterViewHolder(fragmentManager);
//            ((ViewHolder2) holder).expandgrid.setAdapter(menFashionFragmentlistAdapterViewHolder);
//            ((ViewHolder2) holder).expandgrid.setExpanded(true);
            layoutManager.setAutoMeasureEnabled(true);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(menFashionFragmentAdapterViewHolder);
        } else if (holder.getItemViewType() == 3) {
        } else if (holder.getItemViewType() == 4) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            layoutManager = new GridLayoutManager(context, 2);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            menFashionFragmentAdapterViewHolder = new MenFashionFragmentAdapterViewHolder(fragmentManager);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(menFashionFragmentAdapterViewHolder);
        } else if (holder.getItemViewType() == 5) {
        } else if (holder.getItemViewType() == 6) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            menFashionFragmentAdapterViewHolder = new MenFashionFragmentAdapterViewHolder(fragmentManager);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(menFashionFragmentAdapterViewHolder);
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

        TextView card_celebrity_feed_profile_name,card_celebrity_feed_profile_role;
        public ViewHolder1(View itemView) {
            super(itemView);
            card_celebrity_feed_profile_name  = (TextView) itemView.findViewById(R.id.card_celebrity_feed_profile_name);
            card_celebrity_feed_profile_role  = (TextView) itemView.findViewById(R.id.card_celebrity_feed_profile_role);
            card_celebrity_feed_profile_name.setVisibility(View.GONE);
            card_celebrity_feed_profile_role.setVisibility(View.GONE);
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
