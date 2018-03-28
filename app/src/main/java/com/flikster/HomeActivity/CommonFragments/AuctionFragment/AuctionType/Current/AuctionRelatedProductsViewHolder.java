package com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionDetailFragment;
import com.flikster.R;
import com.flikster.Util.DateUtil;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by abhishek on 13-10-2017.
 */

public class AuctionRelatedProductsViewHolder extends RecyclerView.Adapter<AuctionRelatedProductsViewHolder.ViewHolder> {
    List<String> imag = new ArrayList<>();
    FragmentManager fragmentManager;
    int a;
    Context context;
    AuctionCurrentOrUpcomingData auctionCurrentOrUpcomingData;
    AuctionDetailFragment auctionDetailFragment;
    Bundle bundle;

    public AuctionRelatedProductsViewHolder(Context context, FragmentManager fragmentManager, AuctionCurrentOrUpcomingData auctionCurrentOrUpcomingData) {
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.auctionCurrentOrUpcomingData = auctionCurrentOrUpcomingData;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video_item_with_bottom_title, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (auctionCurrentOrUpcomingData.getCurrentAuctions().size() != 0) {
            try {
                Glide.with(context).load(auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getProfilePic()).asBitmap().into(
                        (holder.movieimg));
                if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getEndTime()
                        != null && !auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getEndTime().isEmpty()) {
                }

               /* JSONObject objtime = new JSONObject(auctionCurrentOrUpcomingData.get(position).getEndTime());
                String hours = objtime.getString("hour");
                String minute = objtime.getString("minute");
                String second = objtime.getString("second");
                String completeTime = "Time Left: " + hours + "h " + minute + "m " + second + "s "*/

                    if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getEndDate()
                            != null && !auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getEndDate().isEmpty()) {
                        try {
                       /* JSONObject objtime = new JSONObject(auctionCurrentOrUpcomingData.get(0).getEndTime());
                        String hours = objtime.getString("hour");
                        String minute = objtime.getString("minute");
                        String second = objtime.getString("second");
                        String completeTime = "Time Left: " + hours + "h " + minute + "m " + second + "s ";
                        ((ViewHolder1) holder).timelefttxt.setText(Html.fromHtml(completeTime) + "");*/
//                        String enddateTime = DateUtil.serverSentTimeChange(auctionCurrentOrUpcomingData.get(0).getEndDate());
                            String enddateTime = DateUtil.serverSentTimeChange(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getEndDate());
                            SimpleDateFormat simDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                            Date enddate = simDf.parse(enddateTime);
                            long endTimeMillSec = enddate.getTime();
                            holder.timeLefttxt.setText("Time Left: " + Html.fromHtml(DateUtil.getTimeLeft(endTimeMillSec)) + "");
                        } catch (Exception e) {
                        }
                    }

//                ((AuctionCurrentFragmentAdapter.ViewHolder1) holder).timelefttxt.setText(Html.fromHtml(completeTime) + "");
//                holder.timeLefttxt.setText( Html.fromHtml(completeTime));




                holder.card_video_item_desc.setText(Html.fromHtml(auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getInfo()));
                holder.card_video_item_title.setText(Html.fromHtml(auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getName()));
//        Glide.with(context).load(auctionCurrentOrUpcomingData.get(0).getProfilePic()).into(holder.movieimg);


            } catch (Exception e) {
            }
        }

    }

    @Override
    public int getItemCount() {
        //return imag.size();
        return auctionCurrentOrUpcomingData.getCurrentAuctions().size();
    }

    @Override
    public int getItemViewType(int position) {
        return auctionCurrentOrUpcomingData.getCurrentAuctions().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView movieimg;
        Button buybtn;
        TextView timeLefttxt, card_video_item_desc, card_video_item_title;
        LinearLayout recomdedauction;

        public ViewHolder(View itemView) {
            super(itemView);
            movieimg = (ImageView) itemView.findViewById(R.id.card_video_item_image);
            timeLefttxt = (TextView) itemView.findViewById(R.id.title);
            card_video_item_title = (TextView) itemView.findViewById(R.id.card_video_item_title);
            card_video_item_desc = (TextView) itemView.findViewById(R.id.card_video_item_desc);
            recomdedauction = (LinearLayout) itemView.findViewById(R.id.recomdedauction);
            recomdedauction.setOnClickListener(this);
            timeLefttxt.setVisibility(View.VISIBLE);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "Auction Complete Details", Toast.LENGTH_LONG).show();
            auctionDetailFragment = new AuctionDetailFragment();
            bundle = new Bundle();
            bundle.putInt("POSITION_VALUE", getAdapterPosition());
            bundle.putSerializable("AUCTION_DETAILS", (Serializable) auctionCurrentOrUpcomingData);
            auctionDetailFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, auctionDetailFragment)
                    .addToBackStack("")
                    .commit();
        }
    }
}
