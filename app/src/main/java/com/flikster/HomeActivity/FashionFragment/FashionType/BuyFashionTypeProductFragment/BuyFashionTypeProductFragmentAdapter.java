package com.flikster.HomeActivity.FashionFragment.FashionType.BuyFashionTypeProductFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreInnerItems.CeleStoreRecyclerItemAdapter;
import com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreInnerItems.CeleStoreTredingCelebFashionRecyclerItemAdapter;
import com.flikster.HomeActivity.FashionFragment.FashionType.MenFashionFragment.MenFashionFragmentAdapterViewHolder;
import com.flikster.MyBagActivity.MyBagActivity;
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
    int[] sampleImages = {R.drawable.rakulpreetred, R.drawable.prabha, R.drawable.rakulpreetred, R.drawable.prabha, R.drawable.rakulpreetred};

    public BuyFashionTypeProductFragmentAdapter(Context context, FragmentManager fragmentManager) {
        type.add(1);
        type.add(2);
        type.add(3);
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
        }
        if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_buy_bottom_header, parent, false);
            return new ViewHolder3(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_product_shoplook_img, parent, false);
            return new ViewHolder1(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 2) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
            buyFashionTypeProductFragmentAdapterViewHolder = new BuyFashionTypeProductFragmentAdapterViewHolder(context, fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_recycler.setAdapter(buyFashionTypeProductFragmentAdapterViewHolder);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
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
            /*buylayout = (LinearLayout) itemView.findViewById(R.id.buylayout);
//            carouselView = (CarouselView) itemView.findViewById(R.id.carouselView);
            buyimg = (ImageView) itemView.findViewById(R.id.buyimg);
//            carouselView.setVisibility(View.GONE);
            buyimg.setVisibility(View.VISIBLE);
            buylayout.setBackgroundColor(Color.WHITE);
            buylayout.setOnClickListener(this);*/
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
            fragment_common_recyclerview_with_tv_title.setVisibility(View.GONE);
            fragment_common_recyclerview_with_tv_recycler.setBackgroundColor(context.getResources().getColor(R.color.style_main_background));
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
        Button buyBtn;

        public ViewHolder3(View itemView) {
            super(itemView);
            buyBtn = (Button) itemView.findViewById(R.id.buyBtn);
            buyBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, MyBagActivity.class);
            context.startActivity(intent);
        }
    }
}
