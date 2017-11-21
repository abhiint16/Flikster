package com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreInnerItems;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductOnClick;
import com.flikster.R;

/**
 * Created by abhishek on 11-10-2017.
 */

public class CeleStoreTredingCelebFashionRecyclerItemAdapter extends RecyclerView.Adapter<CeleStoreTredingCelebFashionRecyclerItemAdapter.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    int a;
    Boolean b = true;

    public CeleStoreTredingCelebFashionRecyclerItemAdapter(Context context, int a, FragmentManager fragmentManager) {
        this.context = context;
        this.a = a;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_trending_celb_fashion_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.card_celebrity_feed_profile_image);
        }

        @Override
        public void onClick(View view) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.main_container, new ProductOnClick())
//                    .addToBackStack("")
//                    .commit();
        }
    }
}
