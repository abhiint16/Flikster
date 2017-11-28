package com.flikster.HomeActivity.FashionFragment.FashionType.BuyFashionTypeProductFragment;

import android.content.Context;
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
import com.flikster.R;
import com.flikster.Util.ExpandedGridView;
import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Logins on 21-11-2017.
 */

public class BuyFashionTypeProductFragmentAdapterViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RecyclerView.LayoutManager layoutManager;
    Context context;
    List<Integer> type = new ArrayList<>();
    FragmentManager fragmentManager;
    BuyFashionTypeProductFragmentAdapter menFashionFragmentAdapterViewHolder;
    CeleStoreRecyclerItemAdapter celeStoreRecyclerItemAdapter;
    CeleStoreTredingCelebFashionRecyclerItemAdapter celeStoreTredingCelebFashionRecyclerItemAdapter;
    //    MenFashionFragmentListAdapterViewHolder menFashionFragmentlistAdapterViewHolder;
    int[] sampleImages = {R.drawable.rakulpreetred, R.drawable.prabha, R.drawable.rakulpreetred, R.drawable.prabha, R.drawable.rakulpreetred};

    public BuyFashionTypeProductFragmentAdapterViewHolder(Context context, FragmentManager fragmentManager) {
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_buy_product, parent, false);
        return new ViewHolder1(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView buyimg;
        Button buyBtn;
        LinearLayout carousellayout, buylayout;

        public ViewHolder1(View itemView) {
            super(itemView);
            buylayout = (LinearLayout) itemView.findViewById(R.id.buylayout);
            buyimg = (ImageView) itemView.findViewById(R.id.buyimg);
            buyBtn = (Button) itemView.findViewById(R.id.buyBtn);
            buyBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.buyBtn) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, new BuyFashionTypeSingleProductCarouselViewFragment())
                        .addToBackStack("")
                        .commit();
            }

        }
    }

//    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView fragment_common_recyclerview_with_tv_title;
//        ExpandedGridView expandgrid;
//        RecyclerView fragment_common_recyclerview_with_tv_recycler;
//
//        public ViewHolder2(View itemView) {
//            super(itemView);
//            fragment_common_recyclerview_with_tv_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
//            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
//        }
//
//        @Override
//        public void onClick(View v) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.main_container, new BuyFashionTypeSingleProductCarouselViewFragment())
//                    .addToBackStack("")
//                    .commit();
//        }
//    }
}
