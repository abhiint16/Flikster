package com.flikster;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by abhishek on 21-10-2017.
 */

public class MyAccountFragmentMyPostsAdapter extends RecyclerView.Adapter<MyAccountFragmentMyPostsAdapter.ViewHolder> {
    @Override
    public MyAccountFragmentMyPostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallary3_1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAccountFragmentMyPostsAdapter.ViewHolder holder, int position) {
        holder.relativeLayout.setVisibility(View.GONE);
        holder.tv_description.setVisibility(View.GONE);
        holder.tv_name.setText("my latest pics");
        holder.card_gallary3_img1.setImageResource(R.drawable.neha2);
        holder.card_gallary3_img2.setImageResource(R.drawable.neha3);
        holder.card_gallary3_img3.setImageResource(R.drawable.neha);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        ImageView card_gallary3_img1,card_gallary3_img2,card_gallary3_img3;
        TextView tv_description,tv_name;
        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.card_header_container);
            card_gallary3_img1=(ImageView)itemView.findViewById(R.id.card_gallary3_img1);
            card_gallary3_img2=(ImageView)itemView.findViewById(R.id.card_gallary3_img2);
            card_gallary3_img3=(ImageView)itemView.findViewById(R.id.card_gallary3_img3);
            tv_description=(TextView)itemView.findViewById(R.id.tv_description);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
        }
    }
}
