package com.flikster.HomeActivity.CommonFragments.MyAccountFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.R;
import com.leo.simplearcloader.SimpleArcLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 21-10-2017.
 */

public class MyAccountFragmentMyOrder extends Fragment {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManagerMyPost;
    TextView nodataavailtxt;
    SimpleArcLoader mDialog;
    MyAccountFragmentMyOrderAdapter myAccountFragmentMyOrderAdapter;
    ApiInterface apiInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        initializeViews();
        initializeRest();
        retrofitInit();
        return view;
    }

    private void retrofitInit() {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/orders/_search/").create(ApiInterface.class);
        Call<MyOrderData> call = apiInterface.getMyOrderInAccount("http://apiservice-ec.flikster.com/orders/_search?sort=createdAt:desc&size=10000&pretty=true&q=userId:86a4eb76-7f60-430d-ad4c-1ef87fa44219");
        call.enqueue(new Callback<MyOrderData>() {
            @Override
            public void onResponse(Call<MyOrderData> call, Response<MyOrderData> response) {
                myAccountFragmentMyOrderAdapter = new MyAccountFragmentMyOrderAdapter(response,getActivity());
                fragment_common_recyclerview_recycler.setAdapter(myAccountFragmentMyOrderAdapter);
            }
            @Override
            public void onFailure(Call<MyOrderData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initializeRest() {
        layoutManagerMyPost = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManagerMyPost);
        fragment_common_recyclerview_recycler.setBackgroundColor(getActivity().getResources().getColor(R.color.backprofilescreen));
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        nodataavailtxt = (TextView) view.findViewById(R.id.nodataavailtxt);
        mDialog = (SimpleArcLoader) view.findViewById(R.id.arc_loader);
    }
}
