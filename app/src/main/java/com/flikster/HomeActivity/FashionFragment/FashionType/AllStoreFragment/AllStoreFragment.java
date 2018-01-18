package com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment;

import android.app.Activity;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Logins on 17-11-2017.
 */

public class AllStoreFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    LinearLayoutManager layoutManagerFashionFragment;
    AllStoreFragmentAdapter allStoreFragmentAdapter;
    ApiInterface apiInterface;
    AllStoreInnerData hits;
    SimpleArcLoader simpleArcLoader;
    AllStoreInterafce allStoreInterafce;
    int c=0;
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
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/products/").create(ApiInterface.class);
        Call<AllStoreData> call = apiInterface.getAllStore("http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=10&from=0&q=*");
        call.enqueue(new Callback<AllStoreData>() {
            @Override
            public void onResponse(Call<AllStoreData> call, Response<AllStoreData> response) {
                hits = response.body().getHits();
                allStoreFragmentAdapter = new AllStoreFragmentAdapter(getActivity(),hits,allStoreInterafce);
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
        /*fragment_common_recyclerview_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("gggggg",""+layoutManagerFashionFragment.getChildCount()+"and"+layoutManagerFashionFragment.getItemCount()+"and"+layoutManagerFashionFragment.findLastVisibleItemPosition());
                int visibleItemCount = layoutManagerFashionFragment.getChildCount();
                int totalItemCount = layoutManagerFashionFragment.getItemCount();
                int firstVisibleItemPosition = layoutManagerFashionFragment.findFirstVisibleItemPosition();
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        c=c+10;
                        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/products/").create(ApiInterface.class);
                        Call<AllStoreData> call = apiInterface.getAllStore("http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=10&from="+c+"&q=*");
                        call.enqueue(new Callback<AllStoreData>() {
                            @Override
                            public void onResponse(Call<AllStoreData> call, Response<AllStoreData> response) {
                                Log.e("indie onres",""+response.body().getHits().getHits());
                                //allStoreFragmentAdapter.hits.getHits().addAll(response.body().getHits().getHits());
                                allStoreFragmentAdapter.updateDataPagination(response.body().getHits().getHits());
                            }

                            @Override
                            public void onFailure(Call<AllStoreData> call, Throwable t) {
                                Log.e("vvvvvvvvvv", "vv" + call + t);
                            }
                        });
                    }
            }
        });*/
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

    public interface AllStoreInterafce {
        void onBuyClick(String productId, List<String> size, String userId, String price, String profilePic, String productTitle,
                        String productSlug, List<String> imageGallery, Fragment fragment);
        void onGalleryContainerClick(String productId, List<String> size, String userId, String price, String profilePic, String productTitle,
                        String productSlug, List<String> imageGallery,
                                     String profilepic,List<String> role,String name,String title,
                                     Fragment fragment);
        void toCelebPage(String name, Fragment fragment,String userId,String entityId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        allStoreInterafce = (AllStoreInterafce) activity;
    }
}
