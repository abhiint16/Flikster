package com.flikster.HomeActivity.CommonFragments.MyAccountFragment.UserProfile;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flikster.R;

/**
 * Created by abhishek on 21-10-2017.
 */

public class MyAccountFragmentMyStyleAdapter extends RecyclerView.Adapter<MyAccountFragmentMyStyleAdapter.ViewHolder> {
    @Override
    public MyAccountFragmentMyStyleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_no_comments,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAccountFragmentMyStyleAdapter.ViewHolder holder, int position) {
        holder.fragment_my_account_container.setGravity(Gravity.CENTER_HORIZONTAL);
        holder.activity_no_comments_tv.setText("No Styles");
        /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        holder.activity_no_comments_tv.setText(""+df.format(Calendar.getInstance().getTime()));*/

        /*holder.relativeLayout.setVisibility(View.GONE);
        holder.tv_description.setVisibility(View.GONE);
        holder.tv_name.setText("my latest pics");
        holder.card_gallary3_img1.setImageResource(R.drawable.neha2);
        holder.card_gallary3_img2.setImageResource(R.drawable.neha3);
        holder.card_gallary3_img3.setImageResource(R.drawable.neha);*/
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    /*public class ViewHolder extends RecyclerView.ViewHolder {
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
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView activity_no_comments_tv;
        LinearLayout fragment_my_account_container;

        public ViewHolder(View itemView) {
            super(itemView);
            activity_no_comments_tv = (TextView) itemView.findViewById(R.id.activity_no_comments_tv);
            fragment_my_account_container=(LinearLayout)itemView.findViewById(R.id.fragment_my_account_container);
        }
    }
}
