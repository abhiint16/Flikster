package com.flikster;

import android.content.Context;
import android.media.Image;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 04-10-2017.
 */

public class FeedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Integer> type=new ArrayList<>();
    List<String> imag=new ArrayList<>();
    Context context;
    FragmentManager fragmentManager;
    public FeedRecyclerAdapter(Context context, FragmentManager fragmentManager) {
        this.context=context;
        this.fragmentManager=fragmentManager;
        type.add(4);
        type.add(3);type.add(4);type.add(2);type.add(4);type.add(1);type.add(2);type.add(2);type.add(1);type.add(3);type.add(4);
        type.add(3);type.add(4);type.add(7);type.add(4);type.add(9);type.add(3);type.add(4);type.add(1);type.add(2);type.add(4);
        type.add(4);type.add(8);type.add(5);
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");imag.add("http://img.youtube.com/vi/FXAngnH3s0c/0.jpg");
        imag.add("http://img.youtube.com/vi/rJwPCq61qfo/0.jpg");imag.add("http://img.youtube.com/vi/9gwNKVnmVIg/0.jpg");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary1,parent,false);
            return  new ViewHolder1(view);
        }
        else if(viewType==2)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary2,parent,false);
            return  new ViewHolder2(view);
        }
        else if(viewType==3)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary3_1,parent,false);
            return  new ViewHolder3(view);
        }
        else if(viewType==4)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary4_1,parent,false);
            return  new ViewHolder4(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary_4plus,parent,false);
            return  new ViewHolder5(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==1)
        {
                Glide.with(((ViewHolder1)holder).itemView.getContext()).load(imag.get(0))
                .into(((ViewHolder1)holder).card_gallary1_img1);

        }
        else if(holder.getItemViewType()==2)
        {
            Glide.with(((ViewHolder2)holder).itemView.getContext()).load(imag.get(0))
                    .into(((ViewHolder2)holder).card_gallary2_img1);
            Glide.with(context).load(imag.get(1))
                    .into(((ViewHolder2)holder).card_gallary2_img2);
        }
        else if(holder.getItemViewType()==3)
        {
            Glide.with(((ViewHolder3)holder).itemView.getContext()).load(imag.get(2))
                    .into(((ViewHolder3)holder).card_gallary3_img1);
            Glide.with(((ViewHolder3)holder).itemView.getContext()).load(imag.get(3))
                    .into(((ViewHolder3)holder).card_gallary3_img2);
            Glide.with(((ViewHolder3)holder).itemView.getContext()).load(imag.get(4))
                    .into(((ViewHolder3)holder).card_gallary3_img3);
        }
        else if(holder.getItemViewType()==4)
        {
            Glide.with(((ViewHolder4)holder).itemView.getContext()).load(imag.get(4))
                    .into(((ViewHolder4)holder).card_gallary4_img1);
            Glide.with(((ViewHolder4)holder).itemView.getContext()).load(imag.get(5))
                    .into(((ViewHolder4)holder).card_gallary4_img2);
            Glide.with(((ViewHolder4)holder).itemView.getContext()).load(imag.get(6))
                    .into(((ViewHolder4)holder).card_gallary4_img3);
            Glide.with(((ViewHolder4)holder).itemView.getContext()).load(imag.get(7))
                    .into(((ViewHolder4)holder).card_gallary4_img4);
        }
        else
        {
            Glide.with(((ViewHolder5)holder).itemView.getContext()).load(imag.get(5))
                    .into(((ViewHolder5)holder).card_gallary5_img1);
            Glide.with(((ViewHolder5)holder).itemView.getContext()).load(imag.get(2))
                    .into(((ViewHolder5)holder).card_gallary5_img2);
            Glide.with(((ViewHolder5)holder).itemView.getContext()).load(imag.get(8))
                    .into(((ViewHolder5)holder).card_gallary5_img3);
            Glide.with(((ViewHolder5)holder).itemView.getContext()).load(imag.get(4))
                    .into(((ViewHolder5)holder).card_gallary5_img4);
            ((ViewHolder5)holder).card_gallary5_text.setText("+"+(type.get(position)-4)+"More");
        }
    }

    @Override
    public int getItemCount() {
        return type.size();
    }

    @Override
    public int getItemViewType(int position) {
        return  type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary1_img1;
        public ViewHolder1(View itemView) {
            super(itemView);
            card_gallary1_img1=(ImageView)itemView.findViewById(R.id.card_gallary1_img1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .add(R.id.main_container,new GallaryCardClick())
                    .addToBackStack("")
                    .commit();

        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary2_img1,card_gallary2_img2;
        public ViewHolder2(View itemView) {
            super(itemView);
            card_gallary2_img1=(ImageView)itemView.findViewById(R.id.card_gallary2_img1);
            card_gallary2_img2=(ImageView)itemView.findViewById(R.id.card_gallary2_img2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary3_img1,card_gallary3_img2,card_gallary3_img3;
        public ViewHolder3(View itemView) {
            super(itemView);
            card_gallary3_img1=(ImageView)itemView.findViewById(R.id.card_gallary3_img1);
            card_gallary3_img2=(ImageView)itemView.findViewById(R.id.card_gallary3_img2);
            card_gallary3_img3=(ImageView)itemView.findViewById(R.id.card_gallary3_img3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary4_img1,card_gallary4_img2,card_gallary4_img3,card_gallary4_img4;
        public ViewHolder4(View itemView) {
            super(itemView);
            card_gallary4_img1=(ImageView)itemView.findViewById(R.id.card_gallary4_img1);
            card_gallary4_img2=(ImageView)itemView.findViewById(R.id.card_gallary4_img2);
            card_gallary4_img3=(ImageView)itemView.findViewById(R.id.card_gallary4_img3);
            card_gallary4_img4=(ImageView)itemView.findViewById(R.id.card_gallary4_img4);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView card_gallary5_img1,card_gallary5_img2,card_gallary5_img3,card_gallary5_img4;
        TextView card_gallary5_text;
        public ViewHolder5(View itemView) {
            super(itemView);
            card_gallary5_img1=(ImageView)itemView.findViewById(R.id.card_gallary5_img1);
            card_gallary5_img2=(ImageView)itemView.findViewById(R.id.card_gallary5_img2);
            card_gallary5_img3=(ImageView)itemView.findViewById(R.id.card_gallary5_img3);
            card_gallary5_img4=(ImageView)itemView.findViewById(R.id.card_gallary5_img4);
            card_gallary5_text=(TextView)itemView.findViewById(R.id.card_gallary5_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
