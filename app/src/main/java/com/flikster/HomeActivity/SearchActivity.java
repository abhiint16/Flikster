package com.flikster.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.flikster.CheckoutActivity.CheckoutFragment.CheckoutFragment;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.CustomStyleTypes.MyStyleFragmentOne;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.MyStyleFragment;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.SearchInnerData;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.SearchListAdapter;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.StyleSearchData;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.R;
import com.flikster.SharedPref.SharedPref;
import com.flikster.Util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Logins on 13-12-2017.
 */

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchListAdapter adapter;
    SearchView search;
    ListView listView;
    List<String> arrayList = new ArrayList<>();
    List<SearchInnerData> itemsdata = new ArrayList<SearchInnerData>();

    MyStyleFragment myStyleFragmentOne;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_style_layout);
        retrofitInit();
        search = (SearchView) findViewById(R.id.search);
        listView = (ListView) findViewById(R.id.list_view);
        myStyleFragmentOne = new MyStyleFragment();
        fragmentManager = getSupportFragmentManager();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("viewitem", view.toString() + "");
                String itemname = itemsdata.get(position).getName();
                String profilePic = itemsdata.get(position).getProfilePic();
                String slug = itemsdata.get(position).getSlug();

                SharedPrefsUtil.setStringPreference(getApplicationContext(), "NAME", itemname);
                SharedPrefsUtil.setStringPreference(getApplicationContext(), "SLUG", slug);
                if (getIntent().getStringExtra("IMAGE_ITEM_CLICK_NO").equals("1")) {
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG", profilePic);
                } else if (getIntent().getStringExtra("IMAGE_ITEM_CLICK_NO").equals("2")) {
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_TWO", profilePic);
                } else if (getIntent().getStringExtra("IMAGE_ITEM_CLICK_NO").equals("3")) {
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_THREE", profilePic);
                }


                Intent i = new Intent(SearchActivity.this, HomeActivity.class);
                i.putExtra("SEARCH_ITEM_CLICK", "SEARCH_ITEM_CLICK");
                startActivity(i);
            }
        });

    }

    private void retrofitInit() {
        ApiInterface apiService = ApiClient.getClientData().create(ApiInterface.class);
        Call<StyleSearchData> call = apiService.getStyletypeData("http://apiservice.flikster.com/v3/product-ms/products");//apiInterface.getStyletypeData("http://apiservice.flikster.com/v3/product-ms/products");
        call.enqueue(new Callback<StyleSearchData>() {
            @Override
            public void onResponse(Call<StyleSearchData> call,
                                   Response<StyleSearchData> response) {
                try {
                    Integer ScannedCount = response.body().getScannedCount();
                    itemsdata = response.body().getItems();
                    Log.e("ScannedCount", ScannedCount + "data");
                    adapter = new SearchListAdapter(getApplicationContext(), itemsdata);
                    listView.setAdapter(adapter);
                    listView.setTextFilterEnabled(false);
                    setupSearchView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<StyleSearchData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void setupSearchView() {
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setSubmitButtonEnabled(true);
        search.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(getApplicationContext(), "selectedItem " + query, Toast.LENGTH_SHORT).show();
        Log.e("search Click", query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
//        searcDataOther(newText);
        if (TextUtils.isEmpty(newText)) {
//            listView.clearTextFilter();
            android.widget.Filter filter = adapter.getFilter();
            filter.filter(newText);
            Log.e("searchName", newText);
            return true;
        } else {
//            listView.setFilterText(newText);
            android.widget.Filter filter = adapter.getFilter();
            filter.filter(newText);
        }
        return true;
    }



   /* private void searcDataOther(String newText) {
        newText = newText.toLowerCase().trim();
        ArrayList<SearchInnerData> newSearchList = new ArrayList<>();
        for (SearchInnerData seachdata : itemsdata) {
            String seacrhnamestr = seachdata.getName().toLowerCase().trim();
            if (seacrhnamestr.contains(newText)) {
                newSearchList.add(seachdata);
            }
        }
        adapter.notifyDataSetChanged();
        adapter.setFilter(newSearchList);
    }*/
}
