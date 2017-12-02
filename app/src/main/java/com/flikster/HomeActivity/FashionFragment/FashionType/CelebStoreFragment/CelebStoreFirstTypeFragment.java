package com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment;

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
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initURL();
        initializeViews();
        initializeRest();
        retrofitInit();
        Log.e("check URL",""+URL);
        return view;
    }

    private void retrofitInit() {
        apiInterface = ApiClient.getClient(this.URL).create(ApiInterface.class);
        Call<AllStoreData> call = apiInterface.getAllStore(this.URL);
        call.enqueue(new Callback<AllStoreData>() {
            @Override
            public void onResponse(Call<AllStoreData> call, Response<AllStoreData> response) {
                hits = response.body().getHits();
                celebStoreFragmentAdapter = new CelebStoreFragmentAdapter(getContext(), fragmentManager,hits);
                fragment_common_recyclerview_recycler.setAdapter(celebStoreFragmentAdapter);
            }

            @Override
            public void onFailure(Call<AllStoreData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initURL() {
        Log.e("check shared",""+SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE"));
        if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Movie")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ALL")) {
            this.URL="http://apiv3-es.flikster.com/products/_search?pretty=true&size=100&q=movie";
        }
        else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Celeb")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("ALL"))
        {
            this.URL="http://apiv3-es.flikster.com/products/_search?pretty=true&size=100&q=celeb";
        }
        else if(SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "CELEB_STORE").equals("Celeb")
                && SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "TAB_NO").equals("CLOTHING"))
        {
            this.URL="http://apiv3-es.flikster.com/products/_search?pretty=true&size=100&q=celeb";
        }
    }

    private void initializeRest() {
        layoutManagerFashionFragment = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManagerFashionFragment);
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager =  getActivity().getSupportFragmentManager();
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
