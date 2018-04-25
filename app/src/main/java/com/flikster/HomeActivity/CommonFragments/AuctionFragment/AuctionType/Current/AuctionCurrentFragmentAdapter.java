package com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionDetailFragment;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.BidOnClick.BidOnClickActivity;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFeedAdapter;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.CustomStyleTypes.MyStyleFragmentOne;
import com.flikster.HomeActivity.FashionFragment.FashionFragmentAdapterJustArrivedViewHolder;
import com.flikster.HomeActivity.FashionFragment.FashionFragmentAdapterRecommenedViewHolder;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.FeedFragment.FeedRecyclerAdapter;
import com.flikster.R;
import com.flikster.Util.Common;
import com.flikster.Util.DateUtil;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by abhishek on 30-10-2017.
 */

public class AuctionCurrentFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RecyclerView.LayoutManager layoutManager;
    FashionFragmentAdapterJustArrivedViewHolder fashionFragmentAdapterJustArrivedViewHolder;
    FashionFragmentAdapterRecommenedViewHolder fashionFragmentAdapterRecommenedViewHolder;
    Context context;
    FragmentManager fragmentManager;
    AuctionRelatedProductsViewHolder auctionRelatedProductsViewHolder;
    AuctionCurrentOrUpcomingData auctionCurrentOrUpcomingData;
    AuctionDetailFragment auctionDetailFragment;
    Bundle bundle;

    public AuctionCurrentFragmentAdapter(Context context,
                                         FragmentManager fragmentManager,
                                         AuctionCurrentOrUpcomingData auctionCurrentOrUpcomingData) {
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.auctionCurrentOrUpcomingData = auctionCurrentOrUpcomingData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_auction_multiple_images, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview_with_tv_one, parent, false);
            return new ViewHolder2(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_auction, parent, false);
            return new ViewHolder3(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getProfilePic() != null && !auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getProfilePic().isEmpty()) {
                Glide.with(context).load(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getProfilePic()).asBitmap().into(
                        (((ViewHolder1) holder).profile_image));
                Glide.with(context).load(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getProfilePic()).asBitmap().into(
                        (((ViewHolder1) holder).card_gallary3_img1));

                if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getGallery().get(0) != null && !auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getGallery().get(0).isEmpty()) {
                    Glide.with(context).load(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getGallery().get(0)).asBitmap().into(
                            (((ViewHolder1) holder).card_gallary3_img2));
                }
                try {
                    if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getGallery().get(1) != null && !auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getGallery().get(1).isEmpty()) {
                        Glide.with(context).load(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getGallery().get(0)).asBitmap().into(
                                (((ViewHolder1) holder).card_gallary3_img3));
                    }
                } catch (Exception e) {
                }

                if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getName() != null && !auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getName().isEmpty()) {
                    ((ViewHolder1) holder).tv_tag_name.setText(Html.fromHtml(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getName()) + "");
                }
                if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getDescription() != null && !auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getDescription().isEmpty()) {
                    ((ViewHolder1) holder).tv_name.setText(Html.fromHtml(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getDescription()) + "");
                }

                if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getDescription() != null && !auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getDescription().isEmpty()) {
                    ((ViewHolder1) holder).tv_name.setText(Html.fromHtml(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getDescription()) + "");
                }

                if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getStartingPrice()
                        != null && !auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getStartingPrice().isEmpty()) {
                    ((ViewHolder1) holder).bidprice.setText("Starting Bid Rs "
                            + Html.fromHtml(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getStartingPrice()) + " /-");
                }

                if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getEndDate()
                        != null && !auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getEndDate().isEmpty()) {
                    try {
                       /* JSONObject objtime = new JSONObject(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getEndTime());
                        String hours = objtime.getString("hour");
                        String minute = objtime.getString("minute");
                        String second = objtime.getString("second");
                        String completeTime = "Time Left: " + hours + "h " + minute + "m " + second + "s ";

                        ((ViewHolder1) holder).timelefttxt.setText(Html.fromHtml(completeTime) + "");*/

//                        String enddateTime = DateUtil.serverSentTimeChange(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getEndDate());
                        String enddateTime = DateUtil.serverSentTimeChange(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getEndDate());
                        SimpleDateFormat simDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        Date enddate = simDf.parse(enddateTime);
                        long endTimeMillSec = enddate.getTime();
                        //

                        ((ViewHolder1) holder).timelefttxt.setText(Html.fromHtml(DateUtil.getTimeLeft(endTimeMillSec)) + "");


                    } catch (Exception e) {

                    }
                }

                if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getEndDate()
                        != null && !auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getEndDate().isEmpty()) {
                    //((ViewHolder1) holder).datetxt.setText(Html.fromHtml(auctionCurrentOrUpcomingData.getCurrentAuctions().get(0).getEndDate()) + "");
                }
            }


        } else if (holder.getItemViewType() == 1) {
            ((ViewHolder2) holder).fragment_common_recyclerview_with_tv_title.setVisibility(View.GONE);
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
            auctionRelatedProductsViewHolder = new AuctionRelatedProductsViewHolder(context,
                    fragmentManager, auctionCurrentOrUpcomingData);
            ((ViewHolder2) holder).fragment_common_recyclerview_recycler.setAdapter(auctionRelatedProductsViewHolder);
        } else {
            ((ViewHolder3)holder).auction_title.setText(auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getName());
            ((ViewHolder3)holder).bidprice.setText("Rs "+auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getStartingPrice()+"/-");
            if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getMovie()!=null&&
                    auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getMovie().size()!=0)
            {
                ((ViewHolder3)holder).tv_tag_name.setText(auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getMovie().get(0).getName());
                ((ViewHolder3)holder).tv_tag_desc.setText("#"+auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getMovie().get(0).getGenre().get(0));
                Glide.with(context).load(auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getMovie().get(0).getProfilePic()).asBitmap().into(((ViewHolder3)holder).profile_image);
            }
            else if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getCeleb()!=null&&
                    auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getCeleb().size()!=0)
            {
                ((ViewHolder3)holder).tv_tag_name.setText(auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getCeleb().get(0).getName());
                ((ViewHolder3)holder).tv_tag_desc.setText("#"+auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getCeleb().get(0).getRole().get(0));
                Glide.with(context).load(auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getCeleb().get(0).getProfilePic()).asBitmap().into(((ViewHolder3)holder).profile_image);
            }
            ((ViewHolder3)holder).auction_enddate.setText("( "+ auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getEndDate().substring(0,10)+" )");
            Log.e("check for img","chck for img"+auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getGallery().get(0));
            Glide.with(context).load(auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getGallery().get(0)).into(((ViewHolder3)holder).card_auction_img);
        }
    }

    @Override
    public int getItemCount() {
        Log.e("check for size","chec for size"+auctionCurrentOrUpcomingData.getCurrentAuctions().size());
        return auctionCurrentOrUpcomingData.getCurrentAuctions().size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.e("check for position","checl for pos"+position);
       if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(position).getGallery().size()==1)
       {
           return 2;
       }
       else if (position==auctionCurrentOrUpcomingData.getCurrentAuctions().size())
       {
           return 1;
       }
       return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_gallary3_img1, card_gallary3_img2, card_gallary3_img3, profile_image;
        TextView tv_tag_name, tv_tag_desc, tv_name, card_comment_text_see_more_comments,
                card_celebrity_feed_gallery1_title;
        TextView bidprice, timelefttxt, datetxt;

        public ViewHolder1(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
//            card_comment_text_see_more_comments = (TextView) itemView.findViewById(R.id.card_comment_text_see_more_comments);
            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
//            card_comment_text_see_more_comments.setVisibility(View.GONE);
            card_celebrity_feed_gallery1_title.setVisibility(View.GONE);
            followbtn.setText("BID");
            followbtn.setOnClickListener(this);

            card_celebrity_feed_gallery1_title = (TextView) itemView.findViewById(R.id.card_celebrity_feed_gallery1_title);
            card_gallary3_img1 = (ImageView) itemView.findViewById(R.id.card_gallary3_img1);
            card_gallary3_img2 = (ImageView) itemView.findViewById(R.id.card_gallary3_img2);
            card_gallary3_img3 = (ImageView) itemView.findViewById(R.id.card_gallary3_img3);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            tv_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            bidprice = (TextView) itemView.findViewById(R.id.bidprice);
            timelefttxt = (TextView) itemView.findViewById(R.id.timelefttxt);
            //datetxt = (TextView) itemView.findViewById(R.id.datetxt);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);


        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn) {
                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();
                auctionDetailFragment = new AuctionDetailFragment();
                bundle = new Bundle();
                bundle.putSerializable("AUCTION_DETAILS", (Serializable) auctionCurrentOrUpcomingData);
                auctionDetailFragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, auctionDetailFragment)
                        .addToBackStack("")
                        .commit();
            }
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView fragment_common_recyclerview_recycler;
        TextView fragment_common_recyclerview_with_tv_title;

        public ViewHolder2(View itemView) {
            super(itemView);
            fragment_common_recyclerview_with_tv_title = (TextView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
            fragment_common_recyclerview_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        }

        @Override
        public void onClick(View v) {
//            if (v.getId() == R.id.followbtn) {
//                Toast.makeText(context, "Buy Success", Toast.LENGTH_LONG).show();
//
//            }

        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button followbtn;
        ImageView card_auction_img,profile_image;
        TextView tv_tag_name,tv_tag_desc,auction_title,bidprice,auction_timer_text,auction_enddate;

        public ViewHolder3(View itemView) {
            super(itemView);
            followbtn = (Button) itemView.findViewById(R.id.followbtn);
            card_auction_img=(ImageView)itemView.findViewById(R.id.card_auction_img);
            profile_image=(ImageView)itemView.findViewById(R.id.profile_image);
            tv_tag_name=(TextView)itemView.findViewById(R.id.tv_tag_name);
            tv_tag_desc=(TextView)itemView.findViewById(R.id.tv_tag_desc);
            auction_enddate=(TextView)itemView.findViewById(R.id.auction_enddate);
            auction_timer_text=(TextView)itemView.findViewById(R.id.auction_timer_text);
            auction_title=(TextView)itemView.findViewById(R.id.auction_title);
            bidprice=(TextView)itemView.findViewById(R.id.bidprice);
            followbtn.setText("BID");
            followbtn.setOnClickListener(this);
            card_auction_img.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.followbtn||v.getId()==R.id.card_auction_img) {
                Intent intent=new Intent(context, BidOnClickActivity.class);
                intent.putExtra("name",auctionCurrentOrUpcomingData.getCurrentAuctions().get(getAdapterPosition()).getName());
                if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(getAdapterPosition()).getMovie()!=null&&
                        auctionCurrentOrUpcomingData.getCurrentAuctions().get(getAdapterPosition()).getMovie().size()!=0)
                {
                    intent.putExtra("celebname",auctionCurrentOrUpcomingData.getCurrentAuctions().get(getAdapterPosition()).getMovie().get(0).getName());
                }
                else if (auctionCurrentOrUpcomingData.getCurrentAuctions().get(getAdapterPosition()).getCeleb()!=null&&
                        auctionCurrentOrUpcomingData.getCurrentAuctions().get(getAdapterPosition()).getCeleb().size()!=0)
                {
                    intent.putExtra("celebname",auctionCurrentOrUpcomingData.getCurrentAuctions().get(getAdapterPosition()).getCeleb().get(0).getName());
                }
                intent.putStringArrayListExtra("colorlist", (ArrayList<String>) auctionCurrentOrUpcomingData.getCurrentAuctions().get(getAdapterPosition()).getColor());
                intent.putStringArrayListExtra("imagegallery", (ArrayList<String>) auctionCurrentOrUpcomingData.getCurrentAuctions().get(getAdapterPosition()).getGallery());
                intent.putExtra("listOfBidObjects", (Serializable) auctionCurrentOrUpcomingData.getCurrentAuctions().get(getAdapterPosition()).getBids());
                intent.putExtra("maxprice",auctionCurrentOrUpcomingData.getCurrentAuctions().get(getAdapterPosition()).getMaxPrice());
                context.startActivity(intent);
            }
        }
    }
}
