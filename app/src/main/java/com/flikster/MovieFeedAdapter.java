package com.flikster;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    StealStyleViewHolder stealStyleViewHolder;
    RecyclerView.LayoutManager layoutManager2;
    public MovieFeedAdapter(Context context, FragmentManager fragmentManager) {
        this.context=context;
        this.fragmentManager=fragmentManager;
        type.add(1);
        type.add(2);type.add(3);type.add(4);type.add(5);type.add(6);type.add(7);
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
        else if(viewType==6)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_feed_jukebox,parent,false);
            return new ViewHolder6(view);
        }
        else
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_steal_style_carousel,parent,false);
            return new ViewHolder7(view);
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
            ((ViewHolder2)holder).card_celebrity_feed_gallary_img1.setImageResource(R.drawable.idiots1);
            ((ViewHolder2)holder).card_celebrity_feed_gallary_img2.setImageResource(R.drawable.idiots2);
            ((ViewHolder2)holder).card_celebrity_feed_gallary_img3.setImageResource(R.drawable.idiots3);
        }
        else if(holder.getItemViewType()==3)
        {
            ((ViewHolder3)holder).card_celebrity_feed_news_title.setText("Amir Khan in the shooting of 3Idiots");
            ((ViewHolder3)holder).name.setText("Amir interview after shooting");
            ((ViewHolder3)holder).desc.setText("Amir is busy these days in the shooting of 3Idiots. It is a movie based on the " +
                    "novel of Chetan Bhagat");
            ((ViewHolder3)holder).card_celebrity_feed_news_image.setImageResource(R.drawable.movie);
        }
        else if(holder.getItemViewType()==4)
        {
            ((ViewHolder4)holder).card_dialogue_title.setText("Dialogue");
            ((ViewHolder4)holder).tv_name.setText("All is well Song");
            ((ViewHolder4)holder).tv_description.setVisibility(View.GONE);
        }
        else if(holder.getItemViewType()==5)
        {
            ((ViewHolder5)holder).card_celebrity_feed_video_title.setText("Watch the teaser of 3 Idiots");
            ((ViewHolder5)holder).card_celebrity_feed_video_image.setImageResource(R.drawable.idiots2);
        }
        else if(holder.getItemViewType()==6)
        {
        }
        else if(holder.getItemViewType()==7)
        {
            ((ViewHolder7)holder).card_steal_style_carousel_title.setText("Recommended Movies");
            ((ViewHolder7)holder).card_steal_style_carousel_desc.setVisibility(View.GONE);
            layoutManager2=new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder7)holder).card_steal_style_recycler.setLayoutManager(layoutManager2);
            stealStyleViewHolder=new StealStyleViewHolder("movie_feed");
            ((ViewHolder7)holder).card_steal_style_recycler.setAdapter(stealStyleViewHolder);
        }

    }

    @Override
    public int getItemCount() {
        return 7;
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
    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView card_celebrity_feed_gallary_title;
        ImageView card_celebrity_feed_gallary_img1,card_celebrity_feed_gallary_img2,card_celebrity_feed_gallary_img3;
        public ViewHolder2(View itemView) {
            super(itemView);
            card_celebrity_feed_gallary_title=(TextView)itemView.findViewById(R.id.card_celebrity_feed_gallary_title);
            card_celebrity_feed_gallary_img1=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_gallary_img1);
            card_celebrity_feed_gallary_img2=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_gallary_img2);
            card_celebrity_feed_gallary_img3=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_gallary_img3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container,new GallaryCardClick())
                    .addToBackStack("")
                    .commit();
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView card_celebrity_feed_news_title,desc,name;
        ImageView card_celebrity_feed_news_image;
        public ViewHolder3(View itemView) {
            super(itemView);
            card_celebrity_feed_news_title=(TextView)itemView.findViewById(R.id.card_celebrity_feed_news_title);
            desc=(TextView)itemView.findViewById(R.id.tv_description);
            name=(TextView)itemView.findViewById(R.id.tv_name);
            card_celebrity_feed_news_image=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_news_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container,new NewsOnClickFragment())
                    .addToBackStack("")
                    .commit();
        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder {
        ImageButton ib_play;
        TextView card_dialogue_title,tv_name,tv_description;
        public ViewHolder4(View itemView) {
            super(itemView);
            card_dialogue_title=(TextView)itemView.findViewById(R.id.card_dialogue_title);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_description=(TextView)itemView.findViewById(R.id.tv_description);
            ib_play=(ImageButton)itemView.findViewById(R.id.ib_play);
        }
    }
    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView card_celebrity_feed_video_title;
        ImageView card_celebrity_feed_video_image;
        public ViewHolder5(View itemView) {
            super(itemView);
            card_celebrity_feed_video_title=(TextView)itemView.findViewById(R.id.card_celebrity_feed_video_title);
            card_celebrity_feed_video_image=(ImageView)itemView.findViewById(R.id.card_celebrity_feed_video_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container,new VideoGalleryFragment())
                    .addToBackStack("")
                    .commit();
        }
    }
    public class ViewHolder6 extends RecyclerView.ViewHolder {
        public ViewHolder6(View itemView) {
            super(itemView);
        }
    }
    public class ViewHolder7 extends RecyclerView.ViewHolder {
        TextView card_steal_style_carousel_title,card_steal_style_carousel_desc;
        RecyclerView card_steal_style_recycler;
        public ViewHolder7(View itemView) {
            super(itemView);
            card_steal_style_carousel_title=(TextView)itemView.findViewById(R.id.card_steal_style_carousel_title);
            card_steal_style_carousel_desc=(TextView)itemView.findViewById(R.id.card_steal_style_carousel_desc);
            card_steal_style_recycler=(RecyclerView)itemView.findViewById(R.id.card_steal_style_recycler);
        }
    }
}
