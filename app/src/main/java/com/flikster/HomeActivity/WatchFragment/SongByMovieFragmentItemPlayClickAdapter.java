package com.flikster.HomeActivity.WatchFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flikster.R;
import com.flikster.Util.GlobalData;

/**
 * Created by abhishek on 01-11-2017.
 */

public class SongByMovieFragmentItemPlayClickAdapter extends RecyclerView.Adapter<SongByMovieFragmentItemPlayClickAdapter.ViewHolder> {
    GlobalData globalData = new GlobalData();
    FragmentManager fragmentManager;
    Context context;


    public SongByMovieFragmentItemPlayClickAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public SongByMovieFragmentItemPlayClickAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongByMovieFragmentItemPlayClickAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return globalData.style.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView movieimg;

        public ViewHolder(View itemView) {
            super(itemView);
            movieimg = (ImageView) itemView.findViewById(R.id.movieimg);
            movieimg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new SongByMovieProductFragmentItemClick())
                    .addToBackStack("")
                    .commit();
        }
    }
}
