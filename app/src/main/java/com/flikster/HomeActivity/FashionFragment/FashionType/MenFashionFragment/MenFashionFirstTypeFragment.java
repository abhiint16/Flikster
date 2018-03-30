package com.flikster.HomeActivity.FashionFragment.FashionType.MenFashionFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreData;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreInnerData;
import com.flikster.HomeActivity.ShopByVideoData;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;
import com.leo.simplearcloader.SimpleArcLoader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Logins on 17-11-2017.
 */

public class MenFashionFirstTypeFragment extends Fragment  {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManagerFashionFragment;
    MenFashionFragmentAdapter menFashionFragmentAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    FragmentManager fragmentManager;
    ImageButton toolbar_frag_multiicons_back_navigation, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart;
    String URL="";
    ApiInterface apiInterface;
    AllStoreInnerData hits;
    ShopByVideoMenInterafce shopByVideoMenInterafce;
    SimpleArcLoader simpleArcLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        initURL();
        initializeViews();
        initializeRest();
        retrofitInit();
        return view;
    }

    private void retrofitInit() {
        simpleArcLoader.setVisibility(View.VISIBLE);
        simpleArcLoader.start();
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/products/_search/").create(ApiInterface.class);
        Call<AllStoreData> call = apiInterface.getAllStore(this.URL);
        call.enqueue(new Callback<AllStoreData>() {
            @Override
            public void onResponse(Call<AllStoreData> call, Response<AllStoreData> response) {
                hits = response.body().getHits();
                menFashionFragmentAdapter = new MenFashionFragmentAdapter(getContext(), fragmentManager,hits,shopByVideoMenInterafce);
                simpleArcLoader.setVisibility(View.GONE);
                simpleArcLoader.stop();
                fragment_common_recyclerview_recycler.setAdapter(menFashionFragmentAdapter);
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
        fragmentManager =  getActivity().getSupportFragmentManager();
        simpleArcLoader=(SimpleArcLoader)view.findViewById(R.id.arc_loader);
    }

    private void initURL() {
        Log.e("indie initurl","");
        Log.e("check shared",""+ SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "MEN_STORE"));
        if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "MEN_STORE").equals("Men")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ALL"))
        {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&q=category:%22Men%20Fashion%22";
        }
        else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "MEN_STORE").equals("Men")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("CLOTHING"))
        {
            Log.e("insied clothing else if","inside clothign else if");
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&q=category:%22Men%20Fashion%22AND%22Clothing%22";
        }
        else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "MEN_STORE").equals("Men")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("EYEWEAR"))
        {
            Log.e("insied clothing else if","inside clothign else if");
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&q=category:%22Men%20Fashion%22AND%22Eyewear%22";
        }
        else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "MEN_STORE").equals("Men")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("FOOTWEAR"))
        {
            Log.e("insied clothing else if","inside clothign else if");
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&q=category:%22Men%20Fashion%22AND%22Footwear%22";
        }
        else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "MEN_STORE").equals("Men")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ACCESSORIES"))
        {
            Log.e("insied clothing else if","inside clothign else if");
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&q=category:%22Men%20Fashion%22AND%22Accessories%22";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "MEN_STORE").equals("Women")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ALL")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&q=category:%22Women%20Fashion%22";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "MEN_STORE").equals("Women")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("CLOTHING")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&q=category:%22Women%20Fashion%22AND%22Clothing%22";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "MEN_STORE").equals("Women")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("EYEWEAR")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&q=category:%22Women%20Fashion%22AND%22Eyewear%22";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "MEN_STORE").equals("Women")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("FOOTWEAR")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&q=category:%22Women%20Fashion%22AND%22Footwear%22";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "MEN_STORE").equals("Women")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ACCESSORIES")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&q=category:%22Women%20Fashion%22AND%22Accessories%22";
        }
    }

    public interface ShopByVideoMenInterafce {
        void playShopByVideoMenMethod(String audioLink, Fragment fragment, String audioImg, String type, List<ShopByVideoData.ShopByVideoInnerData.ShopByVideoInnerInnerData.ShopByVideoInnerMostData.ShopByVideoAllProduct> listOfProducts);
        void onBuyClick(String productId,List<String> size,String userId,String price,String profilePic,String productTitle,
                        String productSlug,List<String> imageGallery,String productDesc,String productInfo,Activity fragment);
        void onGalleryContainerClick(String productId, List<String> size, String userId, String price, String profilePic, String productTitle,
                                     String productSlug, List<String> imageGallery,
                                     String profilepic,List<String> role,String name,String title,
                                     Fragment fragment);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        shopByVideoMenInterafce = (ShopByVideoMenInterafce) activity;
    }

}
