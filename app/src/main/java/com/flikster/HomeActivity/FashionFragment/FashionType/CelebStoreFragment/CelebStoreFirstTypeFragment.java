package com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment;

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
import com.flikster.HomeActivity.FashionFragment.FashionType.MenFashionFragment.MenFashionFragmentAdapter;
import com.flikster.HomeActivity.ShopByVideoData;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;
import com.leo.simplearcloader.SimpleArcLoader;
import com.rohitarya.glide.facedetection.transformation.core.GlideFaceDetector;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Logins on 17-11-2017.
 */

public class CelebStoreFirstTypeFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManagerFashionFragment;
    CelebStoreFragmentAdapter celebStoreFragmentAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    FragmentManager fragmentManager;
    ImageButton toolbar_frag_multiicons_back_navigation, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart;
    String URL="";
    ApiInterface apiInterface;
    AllStoreInnerData hits;
    ShopByVideoInterafce shopByVideoInterafce;
    private boolean isViewShown = false;
    private Boolean isStarted = false;
    private Boolean isVisible = false;
    SimpleArcLoader simpleArcLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        initURL();
        initializeViews();
        initializeRest();
        retrofitInit();
        Log.e("check URL",""+URL);
        return view;
    }
    /*@Override
    public void onStart() {
        super.onStart();
        isStarted = true;
        if (isVisible && isStarted){
            retrofitInit();
        }
    }*/
    /*@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isStarted && isVisible) {
            retrofitInit();
        }
       *//* if(getView()!=null)
        {
            isViewShown=true;
            retrofitInit();
        }
        else
        {
            isViewShown=false;
        }*//*
    }*/

    private void retrofitInit() {
        GlideFaceDetector.initialize(getActivity());
        simpleArcLoader.setVisibility(View.VISIBLE);
        simpleArcLoader.start();
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/products/_search/").create(ApiInterface.class);
        Call<AllStoreData> call = apiInterface.getAllStore(this.URL);
        call.enqueue(new Callback<AllStoreData>() {
            @Override
            public void onResponse(Call<AllStoreData> call, Response<AllStoreData> response) {
                hits = response.body().getHits();
                celebStoreFragmentAdapter = new CelebStoreFragmentAdapter(getContext(), fragmentManager,hits,shopByVideoInterafce,URL);
                simpleArcLoader.setVisibility(View.GONE);
                simpleArcLoader.stop();
                fragment_common_recyclerview_recycler.setAdapter(celebStoreFragmentAdapter);
            }

            @Override
            public void onFailure(Call<AllStoreData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initURL() {
        Log.e("indie initurl","");
        Log.e("check shared",""+SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE"));
        if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Celeb")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ALL"))
        {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=100&q=celeb";
        }
        else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Celeb")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("CLOTHING"))
        {
            Log.e("insied clothing else if","inside clothign else if");
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=100&q=celeb%20AND%20Clothing";
        }
        else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Celeb")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("EYEWEAR"))
        {
            Log.e("insied clothing else if","inside clothign else if");
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=100&q=celeb%20AND%20eyewear";
        }
        else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Celeb")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("FOOTWEAR"))
        {
            Log.e("insied clothing else if","inside clothign else if");
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=100&q=celeb%20AND%20footwear";
        }
        else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Celeb")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ACCESSORIES"))
        {
            Log.e("insied clothing else if","inside clothign else if");
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=100&q=celeb%20AND%20accessories";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Movie")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ALL")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=100&q=movie";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Movie")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("CLOTHING")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=100&q=movie%20AND%20Clothing";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Movie")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("EYEWEAR")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=100&q=celeb%20AND%20Eyewear";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Movie")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("FOOTWEAR")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=100&q=movie%20AND%20Footwear";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Movie")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ACCESSORIES")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=100&q=movie%20AND%20Accessories";
        }
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

    public interface ShopByVideoInterafce {
        void playShopByVideoMethod(String audioLink, Fragment fragment, String audioImg, String type, List<ShopByVideoData.ShopByVideoInnerData.ShopByVideoInnerInnerData.ShopByVideoInnerMostData.ShopByVideoAllProduct> listOfProducts);
        void onBuyClick(String productId,List<String> size,String userId,String price,String profilePic,String productTitle,
                        String productSlug,List<String> imageGallery,Fragment fragment);
        void onGalleryContainerClick(String productId, List<String> size, String userId, String price, String profilePic, String productTitle,
                                     String productSlug, List<String> imageGallery,
                                     String profilepic,List<String> role,String name,String title,
                                     Fragment fragment);
        void fromCelebToCelebPage(String name, Fragment fragment,String userId,String entityId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        shopByVideoInterafce = (ShopByVideoInterafce) activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GlideFaceDetector.releaseDetector();
    }
}
