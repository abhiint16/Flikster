package com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class CelebShopByVideosFragmentAdapterViewHolder extends RecyclerView.Adapter<CelebShopByVideosFragmentAdapterViewHolder.ViewHolder> {
    List<String> imag = new ArrayList<>();
    FragmentManager fragmentManager;
    int a;
    Context context;

    public CelebShopByVideosFragmentAdapterViewHolder(Context context, FragmentManager fragmentManager) {
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");
        imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");
        imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
        this.fragmentManager = fragmentManager;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video_item_with_bottom_title, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.movieimg.setImageResource(R.drawable.pooja);
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
        ImageView movieimg;
        Button buybtn;

        public ViewHolder(View itemView) {
            super(itemView);
            movieimg = (ImageView) itemView.findViewById(R.id.carousel_image);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();
            /*fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new BuyFashionTypeProductFragment())
                    .addToBackStack("")
                    .commit();*/
        }
    }
}
