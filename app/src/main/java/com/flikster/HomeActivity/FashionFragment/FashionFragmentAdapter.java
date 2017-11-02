package com.flikster.HomeActivity.FashionFragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flikster.R;

/**
 * Created by abhishek on 23-10-2017.
 */

public class FashionFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RecyclerView.LayoutManager layoutManager;
    FashionFragmentAdapterJustArrivedViewHolder fashionFragmentAdapterJustArrivedViewHolder;
    FashionFragmentAdapterRecommenedViewHolder fashionFragmentAdapterRecommenedViewHolder;
    Context context;

    public FashionFragmentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fashion_option,parent,false);
            return new ViewHolder1(view);
        }
        else if(viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_videos_carousel,parent,false);
            return new ViewHolder2(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_videos_carousel,parent,false);
            return new ViewHolder3(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==0)
        {

        }
        else if(holder.getItemViewType()==1)
        {
            ((ViewHolder2)holder).textView.setText("Just Arrived");
            layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            ((ViewHolder2)holder).recyclerView.setLayoutManager(layoutManager);
            fashionFragmentAdapterJustArrivedViewHolder=new FashionFragmentAdapterJustArrivedViewHolder();
            ((ViewHolder2)holder).recyclerView.setAdapter(fashionFragmentAdapterJustArrivedViewHolder);
        }
        else
        {
            ((ViewHolder3)holder).textView.setText("Recommended Products");
            layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            ((ViewHolder3)holder).recyclerView.setLayoutManager(layoutManager);
            fashionFragmentAdapterRecommenedViewHolder=new FashionFragmentAdapterRecommenedViewHolder();
            ((ViewHolder3)holder).recyclerView.setAdapter(fashionFragmentAdapterRecommenedViewHolder);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        RelativeLayout card_fashion_option_women_relative,card_fashion_option_men_relative;
        public ViewHolder1(View itemView) {
            super(itemView);
            card_fashion_option_women_relative=(RelativeLayout)itemView.findViewById(R.id.card_fashion_option_women_relative);
            card_fashion_option_men_relative=(RelativeLayout)itemView.findViewById(R.id.card_fashion_option_men_relative);
            card_fashion_option_women_relative.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            view.setFitsSystemWindows(true);
                            view.setAlpha((float) 0.5);
                            break;
                        case MotionEvent.ACTION_UP:
                            view.setAlpha((float) 1.0);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            view.setAlpha((float) 0.5);
                            break;
                    }
                    return true;
                }
            });
            card_fashion_option_men_relative.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            view.setFitsSystemWindows(true);
                            view.setAlpha((float) 0.5);
                            break;
                        case MotionEvent.ACTION_UP:
                            view.setAlpha((float) 1.0);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            view.setAlpha((float) 0.5);
                            break;
                    }
                    return true;
                }
            });
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView textView;
        public ViewHolder2(View itemView) {
            super(itemView);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);
            textView=(TextView)itemView.findViewById(R.id.txt);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView textView;
        public ViewHolder3(View itemView) {
            super(itemView);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);
            textView=(TextView)itemView.findViewById(R.id.txt);
        }
    }
}