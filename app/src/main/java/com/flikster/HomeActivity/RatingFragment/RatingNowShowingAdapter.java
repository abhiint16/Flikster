package com.flikster;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by abhishek on 30-10-2017.
 */

public class RatingNowShowingAdapter extends RecyclerView.Adapter<RatingNowShowingAdapter.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    public RatingNowShowingAdapter(Context context,FragmentManager fragmentManager) {
        this.context=context;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public RatingNowShowingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_now_showing_frag_recycler_item,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RatingNowShowingAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout rating_now_showing_frag_recycler_item_rate_movie;
        public ViewHolder(View itemView)
        {
            super(itemView);
            rating_now_showing_frag_recycler_item_rate_movie=(LinearLayout)itemView.findViewById(R.id.rating_now_showing_frag_recycler_item_rate_movie);
            rating_now_showing_frag_recycler_item_rate_movie.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_rate_movie);
            final Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            Button btn=(Button) dialog.findViewById(R.id.dialog_rate_movie_bottom_btn);
            RatingBar ratingBar=(RatingBar)dialog.findViewById(R.id.rating);
            final TextView rating_inside_star=(TextView)dialog.findViewById(R.id.rating_inside_star);
            ImageButton dialog_enter_rate_movie_cancel_btn=(ImageButton)dialog.findViewById(R.id.dialog_enter_rate_movie_cancel_btn);
            dialog_enter_rate_movie_cancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            Log.e("aaaaaaaaaaa","aaaaaaaaaa");
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    Log.e("bbbbbb","bbbbbbbb");
                    rating_inside_star.setText(" "+ratingBar.getRating());
                }
            });
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        dialog.dismiss();
                }
            });
            //window.set
            window.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.translucent)));
            dialog.show();
        }
    }
}
