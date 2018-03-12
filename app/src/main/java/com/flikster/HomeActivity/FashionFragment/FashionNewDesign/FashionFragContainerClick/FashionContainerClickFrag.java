package com.flikster.HomeActivity.FashionFragment.FashionNewDesign.FashionFragContainerClick;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreData;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreInnerData;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 07-03-2018.
 */

public class FashionContainerClickFrag extends Fragment{
    View view;
    ApiInterface apiInterface;
    String URL="";
    RecyclerView fragment_common_recyclerview_recycler;
    GridLayoutManager linearLayoutManager;
    AllStoreInnerData hits;
    FashionContainerClickFragAdapter fashionContainerClickFragAdapter;
    FashionOnClickToProduct fashionOnClick;
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
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/products/_search/").create(ApiInterface.class);
        Call<AllStoreData> call = apiInterface.getAllStore(this.URL);
        call.enqueue(new Callback<AllStoreData>() {
            @Override
            public void onResponse(Call<AllStoreData> call, Response<AllStoreData> response) {
                hits = response.body().getHits();
                fashionContainerClickFragAdapter=new FashionContainerClickFragAdapter(hits,getActivity(),fashionOnClick,URL);
                fragment_common_recyclerview_recycler.setAdapter(fashionContainerClickFragAdapter);
            }

            @Override
            public void onFailure(Call<AllStoreData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initURL() {
        if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Celeb")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("CLOTHING")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=celeb%20AND%20Clothing";
        } else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Celeb")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("EYEWEAR")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=celeb%20AND%20eyewear";
        } else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Celeb")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("FOOTWEAR")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=celeb%20AND%20footwear";
        } else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Celeb")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ACCESSORIES")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=celeb%20AND%20accessories";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Movie")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("CLOTHING")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=movie%20AND%20Clothing";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Movie")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("EYEWEAR")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=celeb%20AND%20Eyewear";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Movie")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("FOOTWEAR")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=movie%20AND%20Footwear";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Movie")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ACCESSORIES")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=movie%20AND%20Accessories";
        }if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Men")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("CLOTHING")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=category:%22Men%20Fashion%22AND%22Clothing%22";
        } else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Men")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("EYEWEAR")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=category:%22Men%20Fashion%22AND%22Eyewear%22";
        } else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Men")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("FOOTWEAR")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=category:%22Men%20Fashion%22AND%22Footwear%22";}
        else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Men")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ACCESSORIES")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=category:%22Men%20Fashion%22AND%22Accessories%22";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Women")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("CLOTHING")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=category:%22Women%20Fashion%22AND%22Clothing%22";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Women")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("EYEWEAR")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=category:%22Women%20Fashion%22AND%22Eyewear%22";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Women")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("FOOTWEAR")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=category:%22Women%20Fashion%22AND%22Footwear%22";
        }else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Women")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ACCESSORIES")) {
            this.URL="http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&from=0&size=10&q=category:%22Women%20Fashion%22AND%22Accessories%22";
        }
    }

    private void initializeRest() {
        linearLayoutManager = new GridLayoutManager(getActivity(), 2,LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position==0||(position>hits.getHits().size()))
                    return 2;
                else return 1;
            }
        });
        fragment_common_recyclerview_recycler.setLayoutManager(linearLayoutManager);
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
    }

    public interface FashionOnClickToProduct {
        void onBuyClick(String productId, List<String> size, String userId, String price, String profilePic, String productTitle,
                        String productSlug, List<String> imageGallery, Fragment fragment);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fashionOnClick = (FashionOnClickToProduct) activity;
    }
}
