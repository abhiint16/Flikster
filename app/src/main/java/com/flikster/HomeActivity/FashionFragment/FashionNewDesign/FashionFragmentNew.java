package com.flikster.HomeActivity.FashionFragment.FashionNewDesign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flikster.R;

import java.util.List;

/**
 * Created by abhishek on 07-03-2018.
 */

public class FashionFragmentNew extends Fragment {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    FashionFragmentNewAdapter fashionFragmentNewAdapter;
    RecyclerView.LayoutManager layoutManagerNew;
    FashionOnClick fashionOnClick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        layoutManagerNew=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManagerNew);
        fashionFragmentNewAdapter=new FashionFragmentNewAdapter(getActivity(),fashionOnClick);
        fragment_common_recyclerview_recycler.setAdapter(fashionFragmentNewAdapter);
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler=(RecyclerView)view.findViewById(R.id.fragment_common_recyclerview_recycler);
    }

    public interface FashionOnClick {
        void fashionContainerClick(Fragment fragment);

        void onBuyClick(String productId, List<String> size, String userId, String price, String profilePic, String productTitle,
                        String productSlug, List<String> imageGallery, Fragment fragment);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fashionOnClick = (FashionOnClick) activity;
    }
}
