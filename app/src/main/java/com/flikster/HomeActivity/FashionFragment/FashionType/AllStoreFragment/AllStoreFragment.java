package com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.R;
import com.leo.simplearcloader.SimpleArcLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Logins on 17-11-2017.
 */

public class AllStoreFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManagerFashionFragment;
    AllStoreFragmentAdapter allStoreFragmentAdapter;
    ApiInterface apiInterface;
    AllStoreInnerData hits;
    SimpleArcLoader simpleArcLoader;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        initializeViews();
        initializeRest();
        retrofitInit();
        return view;
    }

    private void retrofitInit() {
        simpleArcLoader.setVisibility(View.VISIBLE);
        simpleArcLoader.start();
        apiInterface = ApiClient.getClient("http://apiv3-es.flikster.com/products/_search?pretty=true&sort=createdAt:desc&q=*").create(ApiInterface.class);
        Call<AllStoreData> call = apiInterface.getAllStore("http://apiv3-es.flikster.com/products/_search?pretty=true&sort=createdAt:desc&q=*");
        call.enqueue(new Callback<AllStoreData>() {
            @Override
            public void onResponse(Call<AllStoreData> call, Response<AllStoreData> response) {
                hits = response.body().getHits();
                allStoreFragmentAdapter = new AllStoreFragmentAdapter(getActivity(),hits);
                simpleArcLoader.setVisibility(View.GONE);
                simpleArcLoader.stop();
                fragment_common_recyclerview_recycler.setAdapter(allStoreFragmentAdapter);
            }

            @Override
            public void onFailure(Call<AllStoreData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }
    private void initializeRest() {
        layoutManagerFashionFragment = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManagerFashionFragment);
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        simpleArcLoader=(SimpleArcLoader)view.findViewById(R.id.arc_loader);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View view) {
        /*if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .addToBackStack("")
                    .commit();
        } else if (view.getId() == R.id.toolbar_frag_multiicons_notification) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new NotificationFragment())
                    .addToBackStack("")
                    .commit();
        } else if (view.getId() == R.id.toolbar_frag_multiicons_cart) {
            Intent intent = new Intent(getActivity(), MyBagActivity.class);
            startActivity(intent);
        }*/
    }
}
