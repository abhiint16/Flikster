package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.flikster.Adapter.ProductImagesAdapter;

/**
 * Created by abhishek on 16-10-2017.
 */

public class ProductOnClick extends Fragment implements View.OnClickListener {
    View view;
    Button toolbar_back_navigation_btn, buy, add;
    ImageButton moreimg;
    TextView activity_my_bag_toolbar_title;
    FragmentManager fragmentManager;
    TextView txt;
    RecyclerView card_celebrity_bio_video_carousel_recycler;
    ProductImagesAdapter myPrductImgAdapter;
    CelebrityBioAdapterImagesViewHolder myCeleAdapter;
    RecyclerView product_imgs_recycler_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.product_onclick, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buy_now_btn) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new Checkout())
                    .addToBackStack("")
                    .commit();
        } else if (view.getId() == R.id.add_to_cart_btn) {

        } else if (view.getId() == R.id.toolbar_back_navigation_btn) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    private void initializeRest() {
        activity_my_bag_toolbar_title.setText("Product");
        txt.setText("Recommended Product");
        moreimg.setVisibility(View.GONE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        product_imgs_recycler_view.setLayoutManager(layoutManager);
        myPrductImgAdapter = new ProductImagesAdapter((AppCompatActivity) getContext(), fragmentManager);
        product_imgs_recycler_view.setAdapter(myPrductImgAdapter);

        LinearLayoutManager layoutManagers = new LinearLayoutManager(getActivity());
        layoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
        card_celebrity_bio_video_carousel_recycler.setLayoutManager(layoutManagers);
        myPrductImgAdapter = new ProductImagesAdapter((AppCompatActivity) getContext(), fragmentManager);
        myCeleAdapter = new CelebrityBioAdapterImagesViewHolder();
        card_celebrity_bio_video_carousel_recycler.setAdapter(myCeleAdapter);
        toolbar_back_navigation_btn.setOnClickListener(this);
        add.setOnClickListener(this);
        buy.setOnClickListener(this);

    }

    private void initializeViews() {
        toolbar_back_navigation_btn = (Button) view.findViewById(R.id.toolbar_back_navigation_btn);
        activity_my_bag_toolbar_title = (TextView) view.findViewById(R.id.activity_my_bag_toolbar_title);
        txt = (TextView) view.findViewById(R.id.txt);
        product_imgs_recycler_view = (RecyclerView) view.findViewById(R.id.product_imgs_recycler_view);
        card_celebrity_bio_video_carousel_recycler = (RecyclerView) view.findViewById(R.id.card_celebrity_bio_video_carousel_recycler);
        add = (Button) view.findViewById(R.id.add_to_cart_btn);
        buy = (Button) view.findViewById(R.id.buy_now_btn);
        moreimg = (ImageButton) view.findViewById(R.id.moreimg);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
//    }
}
