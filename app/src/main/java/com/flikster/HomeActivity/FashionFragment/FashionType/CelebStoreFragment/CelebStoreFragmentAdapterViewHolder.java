package com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class CelebStoreFragmentAdapterViewHolder extends RecyclerView.Adapter<CelebStoreFragmentAdapterViewHolder.ViewHolder> {
    List<String> imag = new ArrayList<>();
    FragmentManager fragmentManager;
    int a;

    public CelebStoreFragmentAdapterViewHolder(FragmentManager fragmentManager) {
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");
        imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");
        imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
        this.fragmentManager = fragmentManager;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.imageView.setImageResource(R.drawable.pooja);
    }

    @Override
    public int getItemCount() {
        //return imag.size();
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        ImageView movieimg;

        public ViewHolder(View itemView) {
            super(itemView);
//            movieimg = (ImageView) itemView.findViewById(R.id.fashion_proimg);
//            movieimg.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.main_container, new MovieSongsListFragment())
//                    .addToBackStack("")
//                    .commit();
        }
    }
}
