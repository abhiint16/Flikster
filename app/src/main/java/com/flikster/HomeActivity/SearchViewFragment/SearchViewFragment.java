package com.flikster.HomeActivity.SearchViewFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flikster.HomeActivity.GlobalSearchGetData;
import com.flikster.R;

import java.util.List;

/**
 * Created by abhishek on 03-01-2018.
 */

public class SearchViewFragment extends Fragment {
    View view;
    GlobalSearchGetData globalSearchGetData;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchViewRecyclerAdapter searchViewRecyclerAdapter;
    SearchViewToFrag searchViewToFrag;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        initializeView();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        recyclerView.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        searchViewRecyclerAdapter=new SearchViewRecyclerAdapter(globalSearchGetData,searchViewToFrag,getActivity());
        recyclerView.setAdapter(searchViewRecyclerAdapter);
    }

    private void initializeView() {
        recyclerView=(RecyclerView)view.findViewById(R.id.fragment_common_recyclerview_recycler);
    }

    public void getSearchQueryData(GlobalSearchGetData globalSearchGetData)
    {
        this.globalSearchGetData=globalSearchGetData;
    }

    public interface SearchViewToFrag {
        void test(String name, Fragment fragment, int getClass, String userId, String entityId);
        void newsCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle,
                             String description, Fragment fragment, String contentType, String userId, String entityId);
        void galleryCardOnClick(List<String> galleryImgLinks, String name, String profilePic, String type, String title,
                                Fragment fragment, String userId, String entityId);
        void videoCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle,
                              String description, String videolink, Fragment fragment, String contentType,
                              String userId, String entityId);
        void onBuyClick(String productId, List<String> size, String userId, String price, String profilePic, String productTitle,
                        String productSlug, List<String> imageGallery, Fragment fragment);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        searchViewToFrag = (SearchViewToFrag) activity;
    }

}
