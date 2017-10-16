package com.flikster;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MovieFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type=new ArrayList<>();
    List<String> imag=new ArrayList<>();
    public MovieFeedAdapter(Context context, FragmentManager fragmentManager) {
        this.context=context;
        this.fragmentManager=fragmentManager;
        type.add(1);
        type.add(2);type.add(3);type.add(4);type.add(5);
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_profile,parent,false);
            return new ViewHolder1(view);
        }
        else if(viewType==2)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_gallary,parent,false);
            return new ViewHolder2(view);
        }
        else if(viewType==3)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_news,parent,false);
            return new ViewHolder3(view);
        }
        else if(viewType==4)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dialogue,parent,false);
            return new ViewHolder4(view);
        }
        else if(viewType==5)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_feed_video,parent,false);
            return new ViewHolder5(view);
        }
        else
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1,parent,false);
            return new ViewHolder6(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==1)
        {
            ((ViewHolder1)holder).card_celebrity_feed_profile_name.setText("3 Idiots");
            ((ViewHolder1)holder).card_celebrity_feed_profile_desc.setText("Romance . Drama . Comedy");
            ((ViewHolder1)holder).card_celebrity_feed_profile_image.setImageResource(R.drawable.movie);
        }
        else if(holder.getItemViewType()==2)
        {
            ((ViewHolder2)holder).card_celebrity_feed_gallary_title.setText("Photo Gallary");
            ((ViewHolder2)holder).card_celebrity_feed_gallary_img1.setImageResource(R.drawable.pooja6);
            ((ViewHolder2)holder).card_celebrity_feed_gallary_img2.setImageResource(R.drawable.pooja3);
            ((ViewHolder2)holder).card_celebrity_feed_gallary_img3.setImageResource(R.drawable.pooja2);
        }
        else if(holder.getItemViewType()==3)
        {
            ((ViewHolder3)holder).card_celebrity_feed_news_title.setText("Pooja in the shooting of Padmavati");
            ((ViewHolder3)holder).name.setText("Pooja interview after shooting");
            ((ViewHolder3)holder).desc.setText("Pooja is busy these days in the shooting of Epic MohanJodaro.");
        }
        else if(holder.getItemViewType()==4)
        {

        }
        else if(holder.getItemViewType()==5)
        {
            ((ViewHolder5)holder).card_celebrity_feed_video_title.setText("Watch the latest update of Mohanjodaro");
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView card_celebrity_feed_profile_name,card_celebrity_feed_profile_desc;
        ImageView card_celebrity_feed_profile_image;
        public ViewHolder1(View itemView) {
            super(itemView);
            card_celebrity_feed_profile_name=(TextView)itemView.findViewById(R.id.card_celebrity_feed_profile_name);
            card_celebrity_feed_profile_desc=(TextView)itemView.findViewById(R.id.card_celebrity_feed_profile_desc);
            card_celebrity_feed_profile_image=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_profile_image);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView card_celebrity_feed_gallary_title;
        ImageView card_celebrity_feed_gallary_img1,card_celebrity_feed_gallary_img2,card_celebrity_feed_gallary_img3;
        public ViewHolder2(View itemView) {
            super(itemView);
            card_celebrity_feed_gallary_title=(TextView)itemView.findViewById(R.id.card_celebrity_feed_gallary_title);
            card_celebrity_feed_gallary_img1=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_gallary_img1);
            card_celebrity_feed_gallary_img2=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_gallary_img2);
            card_celebrity_feed_gallary_img3=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_gallary_img3);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {
        TextView card_celebrity_feed_news_title,desc,name;
        public ViewHolder3(View itemView) {
            super(itemView);
            card_celebrity_feed_news_title=(TextView)itemView.findViewById(R.id.card_celebrity_feed_news_title);
            desc=(TextView)itemView.findViewById(R.id.tv_description);
            name=(TextView)itemView.findViewById(R.id.tv_name);
        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder {
        public ViewHolder4(View itemView) {
            super(itemView);
        }
    }
    public class ViewHolder5 extends RecyclerView.ViewHolder {
        TextView card_celebrity_feed_video_title;
        public ViewHolder5(View itemView) {
            super(itemView);
            card_celebrity_feed_video_title=(TextView)itemView.findViewById(R.id.card_celebrity_feed_video_title);
        }
    }
    public class ViewHolder6 extends RecyclerView.ViewHolder {
        public ViewHolder6(View itemView) {
            super(itemView);
        }
    }
}
