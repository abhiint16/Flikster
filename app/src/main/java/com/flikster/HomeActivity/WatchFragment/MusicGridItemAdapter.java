package com.flikster.HomeActivity.WatchFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flikster.HomeActivity.CommonFragments.ShopByVideoFragment.ShopByVideoFragmentItemClick;
import com.flikster.R;
import com.flikster.Util.GlobalData;

/**
 * Created by abhishek on 01-11-2017.
 */

public class MusicGridItemAdapter extends RecyclerView.Adapter<MusicGridItemAdapter.ViewHolder> {
    Context context;
    GlobalData globalData = new GlobalData();
    FragmentManager fragmentManager;

    public MusicGridItemAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public MusicGridItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_music_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicGridItemAdapter.ViewHolder holder, int position) {
//        holder.card_shopby_video_recycler_image.setImageResource(globalData.style.get(position));
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_shopby_video_recycler_image;

        public ViewHolder(View itemView) {
            super(itemView);
            card_shopby_video_recycler_image = (ImageView) itemView.findViewById(R.id.movieimg);
            card_shopby_video_recycler_image.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new SongByMovieFragment())
                    .addToBackStack("")
                    .commit();


        }
    }
}
