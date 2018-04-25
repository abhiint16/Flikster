package com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.BidOnClick;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.AuctionCurrentOrUpcomingData;
import com.flikster.R;
import com.rohitarya.glide.facedetection.transformation.FaceCenterCrop;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

/**
 * Created by abhishek on 02-04-2018.
 */

public class BidOnClickActivity extends AppCompatActivity {
    CarouselView carouselView;
    RecyclerView recyclerView;
    BidOnClickAdapter bidOnClickAdapter;
    RecyclerView.LayoutManager layoutManager;
    String name;
    String celebname;
    List<String> colorlist;
    List<String> imagegallery;
    List<AuctionCurrentOrUpcomingData.Bids> listOfBidObjects;
    String maxprice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidonclick);
        getAllIntentData();
        initializeView();
        initializeRest();
    }

    private void getAllIntentData() {
        this.name=getIntent().getStringExtra("name");
        this.celebname=getIntent().getStringExtra("celebname");
        this.colorlist=getIntent().getStringArrayListExtra("colorlist");
        this.imagegallery=getIntent().getStringArrayListExtra("imagegallery");
        this.maxprice=getIntent().getStringExtra("maxprice");
        this.listOfBidObjects= (List<AuctionCurrentOrUpcomingData.Bids>) getIntent().getSerializableExtra("listOfBidObjects");
    }

    private void initializeRest() {
        carouselView.setImageListener(imageListeners);
        carouselView.setPageCount(imagegallery.size());
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        bidOnClickAdapter=new BidOnClickAdapter(this,name,celebname,colorlist,maxprice,listOfBidObjects);
        recyclerView.setAdapter(bidOnClickAdapter);
    }

    private void initializeView() {
        carouselView=(CarouselView)findViewById(R.id.bid_onclick_carousel);
        recyclerView=(RecyclerView)findViewById(R.id.bid_onclick_recycler);
    }

    ImageListener imageListeners = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            try {
                Glide.with(BidOnClickActivity.this).load(imagegallery.get(position).trim())
                        .transform(new FaceCenterCrop())
                        .into(imageView);
            }catch (Exception e){

            }

        }
    };
}
