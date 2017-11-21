package com.flikster.HomeActivity.FashionFragment.FashionType.BuyFashionTypeProductFragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreInnerItems.CeleStoreRecyclerItemAdapter;
import com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreInnerItems.CeleStoreTredingCelebFashionRecyclerItemAdapter;
import com.flikster.HomeActivity.FashionFragment.FashionType.MenFashionFragment.MenFashionFragmentAdapterViewHolder;
import com.flikster.R;
import com.flikster.Util.ExpandedGridView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by abhishek on 23-10-2017.
 */

public class BuyFashionTypeProductFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RecyclerView.LayoutManager layoutManager;
    Context context;
    List<Integer> type = new ArrayList<>();
    FragmentManager fragmentManager;
    BuyFashionTypeProductFragmentAdapterViewHolder buyFashionTypeProductFragmentAdapterViewHolder;
    CeleStoreRecyclerItemAdapter celeStoreRecyclerItemAdapter;
    CeleStoreTredingCelebFashionRecyclerItemAdapter celeStoreTredingCelebFashionRecyclerItemAdapter;
    //    MenFashionFragmentListAdapterViewHolder menFashionFragmentlistAdapterViewHolder;
    int[] sampleImages = {R.drawable.rakulpreetred, R.drawable.prabha, R.drawable.rakulpreetred, R.drawable.prabha, R.drawable.rakulpreetred};

    public BuyFashionTypeProductFragmentAdapter(Context context, FragmentManager fragmentManager) {
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_product_shoplook_img, parent, false);
            return new ViewHolder1(view);
        }
        if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv_one, parent, false);
            return new ViewHolder2(view);
        } if (viewType == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_buy_bottom_header, parent, false);
            return new ViewHolder3(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_buy_product, parent, false);
            return new ViewHolder1(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setText("Recommended Products");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            buyFashionTypeProductFragmentAdapterViewHolder = new BuyFashionTypeProductFragmentAdapterViewHolder(context, fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(buyFashionTypeProductFragmentAdapterViewHolder);
        }
      /*  if (holder.getItemViewType() == 1) {
        } else if (holder.getItemViewType() == 2) {
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setText("Recommended Products");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            menFashionFragmentAdapterViewHolder = new MenFashionFragmentAdapterViewHolder(fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(menFashionFragmentAdapterViewHolder);
        } else if (holder.getItemViewType() == 3) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder3) holder).fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
            celeStoreRecyclerItemAdapter = new CeleStoreRecyclerItemAdapter(context, 3, fragmentManager);
            ((ViewHolder3) holder).fragment_common_recyclerview_recycler.setAdapter(celeStoreRecyclerItemAdapter);
        } else if (holder.getItemViewType() == 4) {
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_title.setText("Trending Celebrity Fashions");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            celeStoreTredingCelebFashionRecyclerItemAdapter = new CeleStoreTredingCelebFashionRecyclerItemAdapter(context, 3, fragmentManager);
            ((ViewHolder4) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(celeStoreTredingCelebFashionRecyclerItemAdapter);
        } else if (holder.getItemViewType() == 5) {
        } else if (holder.getItemViewType() == 6) {
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_title.setText("Shop By Videos");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            menFashionFragmentAdapterViewHolder = new MenFashionFragmentAdapterViewHolder(fragmentManager);
            ((ViewHolder6) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(menFashionFragmentAdapterViewHolder);
        } else if (holder.getItemViewType() == 7) {
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_title.setText("Recommended Products");
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            menFashionFragmentAdapterViewHolder = new MenFashionFragmentAdapterViewHolder(fragmentManager);
            ((ViewHolder7) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(menFashionFragmentAdapterViewHolder);
        }*/


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        CarouselView carouselView;
        ImageView buyimg;
        LinearLayout buylayout;

        public ViewHolder1(View itemView) {
            super(itemView);
            buylayout = (LinearLayout) itemView.findViewById(R.id.buylayout);
            carouselView = (CarouselView) itemView.findViewById(R.id.carouselView);
            buyimg = (ImageView) itemView.findViewById(R.id.buyimg);
            carouselView.setVisibility(View.GONE);
            buyimg.setVisibility(View.VISIBLE);
            buylayout.setBackgroundColor(Color.WHITE);
            buylayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new BuyFashionTypeSingleProductCarouselViewFragment())
                    .addToBackStack("")
                    .commit();
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
        }

        @Override
        public void onClick(View v) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new BuyFashionTypeSingleProductCarouselViewFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView fragment_common_recyclerview_with_tv_title;
//        ExpandedGridView expandgrid;
//        RecyclerView fragment_common_recyclerview_with_tv_recycler;

        public ViewHolder3(View itemView) {
            super(itemView);
//            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
//            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        }

        @Override
        public void onClick(View v) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new BuyFashionTypeSingleProductCarouselViewFragment())
                    .addToBackStack("")
                    .commit();
        }
    }
}
