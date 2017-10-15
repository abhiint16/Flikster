package com.flikster;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-10-2017.
 */

public class CelebrityStoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type=new ArrayList<>();
    List<String> imag=new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    ProfileCollectionRecyclerItemAdapter profileCollectionRecyclerItemAdapter;
    CelebrityBioAdapterVideoViewHolder celebrityBioAdapterVideoViewHolder;
    public CelebrityStoreAdapter(Context context, FragmentManager fragmentManager) {
        this.context=context;
        this.fragmentManager=fragmentManager;
        type.add(1);
        type.add(2);type.add(3);type.add(4);type.add(5);type.add(6);type.add(7);type.add(8);type.add(9);type.add(3);type.add(4);
        type.add(3);type.add(4);type.add(7);
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
            return new CelebrityStoreAdapter.ViewHolder1(view);
        }
        else if(viewType==2)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_store_gallary4_1,parent,false);
            return new CelebrityStoreAdapter.ViewHolder2(view);
        }
        else if(viewType==3)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_store_profile_collection,parent,false);
            return new CelebrityStoreAdapter.ViewHolder3(view);
        }
        else if(viewType==4)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_videos_carousel,parent,false);
            return new CelebrityStoreAdapter.ViewHolder4(view);
        }
        else
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_upcoming,parent,false);
            return new CelebrityStoreAdapter.ViewHolder4(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==2)
        {

        }
        else if(holder.getItemViewType()==3)
        {
            layoutManager=new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder3)holder).recyclerView.setLayoutManager(layoutManager);
            profileCollectionRecyclerItemAdapter =new ProfileCollectionRecyclerItemAdapter(context);
            ((ViewHolder3)holder).recyclerView.setAdapter(profileCollectionRecyclerItemAdapter);
        }
        else if(holder.getItemViewType()==4)
        {
            ((ViewHolder4)holder).textView.setText("Shop By videos");
            layoutManager=new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder4)holder).recyclerView.setLayoutManager(layoutManager);
            celebrityBioAdapterVideoViewHolder =new CelebrityBioAdapterVideoViewHolder();
            ((ViewHolder4)holder).recyclerView.setAdapter(celebrityBioAdapterVideoViewHolder);
        }

        else if(holder.getItemViewType()==7)
        {

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
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
        public ViewHolder2(View itemView) {
            super(itemView);
        /*    textView=(TextView)itemView.findViewById(R.id.txt);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);*/
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
        public ViewHolder3(View itemView) {
            super(itemView);
            //textView=(TextView)itemView.findViewById(R.id.txt);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.rc);
        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
        public ViewHolder4(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.txt);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);
        }
    }


}
