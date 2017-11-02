package com.flikster.HomeActivity.CommonFragments.AuctionFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductImagesAdapter;
import com.flikster.CheckoutActivity.MyBagContinueOnClickActivity;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.R;

/**
 * Created by abhishek on 16-10-2017.
 */

public class AuctionDetailFragment extends Fragment implements View.OnClickListener {
    View view;
    Button placebidbtn, add;
    ImageButton toolbar_back_navigation_btn,toolbar_more_icon;
    TextView toolbar_frag_title;
    FragmentManager fragmentManager;
    TextView txt, infotxt, descrtxt;
    RecyclerView card_celebrity_bio_video_carousel_recycler;
    ProductImagesAdapter myPrductImgAdapter;
    CelebrityBioAdapterImagesViewHolder myCeleAdapter;
    RecyclerView product_imgs_recycler_view;
    LinearLayout cartlayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_auction, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.placebidbtn) {
            Intent intent = new Intent(getActivity(), MyBagContinueOnClickActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.toolbar_back_navigation_btn) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    private void initializeRest() {
        toolbar_frag_title.setText("Auction");
        txt.setText("Recommended Product");
        toolbar_more_icon.setVisibility(View.VISIBLE);
        cartlayout.setVisibility(View.GONE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        product_imgs_recycler_view.setLayoutManager(layoutManager);
        myPrductImgAdapter = new ProductImagesAdapter((AppCompatActivity) getContext(), fragmentManager);
        product_imgs_recycler_view.setAdapter(myPrductImgAdapter);

        LinearLayoutManager layoutManagers = new LinearLayoutManager(getActivity());
        layoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
        card_celebrity_bio_video_carousel_recycler.setLayoutManager(layoutManagers);
        myPrductImgAdapter = new ProductImagesAdapter((AppCompatActivity) getContext(), fragmentManager);
        myCeleAdapter = new CelebrityBioAdapterImagesViewHolder(getActivity());
        card_celebrity_bio_video_carousel_recycler.setAdapter(myCeleAdapter);
        toolbar_back_navigation_btn.setOnClickListener(this);
        placebidbtn.setOnClickListener(this);

        setAvailableText();

    }

    private void setAvailableText() {
        String infostr = "<font color=\"#030a0a\"><bold>"
                + "Information : \n"
                + "</bold></font>"  + "Being Fab Men's Solid Formal Blue Shirt,Regular Fit, Full Sleeve, Being Fab has just the range of apparel";
        //
        infotxt.setText(Html.fromHtml(infostr));

        String descstr = "<font color=\"#030a0a\"><bold>"
                + "Description : \n"
                + "</bold></font>" + "If you are looking to be a trend setter when you go out to the fanciest of parties on a weekend, Being Fab has just the range of apparel that ensures you'd never miss that admirable glance or compliment. You can tuck the shirt inside for a formal look and also put it outside for casual look.";
        //Description :
        // If you are looking to be a trend setter when you go out to the fanciest of parties on a weekend, Being Fab has just the range of apparel that ensures you'd never miss that admirable glance or compliment. You can tuck the shirt inside for a formal look and also put it outside for casual look.
        descrtxt.setText(Html.fromHtml(descstr));
    }

    private void initializeViews() {
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
        toolbar_frag_title = (TextView) view.findViewById(R.id.toolbar_frag_title);
        txt = (TextView) view.findViewById(R.id.txt);
        infotxt = (TextView) view.findViewById(R.id.infotxt);
        descrtxt = (TextView) view.findViewById(R.id.descrtxt);
        product_imgs_recycler_view = (RecyclerView) view.findViewById(R.id.product_imgs_recycler_view);
        card_celebrity_bio_video_carousel_recycler = (RecyclerView) view.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);
        placebidbtn = (Button) view.findViewById(R.id.placebidbtn);
        toolbar_more_icon = (ImageButton) view.findViewById(R.id.toolbar_more_icon);
        cartlayout = (LinearLayout) view.findViewById(R.id.cartlayout);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}
