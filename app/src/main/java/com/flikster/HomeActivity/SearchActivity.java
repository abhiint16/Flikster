package com.flikster.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
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

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {
    SearchListAdapter adapter;
    SearchView search;
    ListView listView;
    List<String> arrayList = new ArrayList<>();
    List<SearchInnerData> itemsdata = new ArrayList<SearchInnerData>();

    MyStyleFragment myStyleFragmentOne;
    FragmentManager fragmentManager;
    GridView gridView;

    ImageButton toolbar_frag_multiicons_back_navigation, toolbar_frag_multiicons_overflow, toolbar_frag_multiicons_search, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart;
    TabLayout tabLayout;
    Toolbar toolbar_frag_multiicons_toolbar;
    TextView toolbar_frag_multiicons_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_style_layout);
        initializeViews();
        initializeRest();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("viewitem", view.toString() + "");
                String itemname = itemsdata.get(position).getName();
                String profilePic = itemsdata.get(position).getProfilePic();
                String slug = itemsdata.get(position).getSlug();
                if (getIntent().getStringExtra("IMAGE_ITEM_CLICK_NO").equals("1")) {
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG", profilePic);
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_NAME", itemname);
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_SLUG", slug);
                } else if (getIntent().getStringExtra("IMAGE_ITEM_CLICK_NO").equals("2")) {
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_TWO", profilePic);
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_TWO_NAME", itemname);
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_TWO_PRODUCT_IMG_SLUG", slug);
                } else if (getIntent().getStringExtra("IMAGE_ITEM_CLICK_NO").equals("3")) {
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_THREE", profilePic);
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_THREE_NAME", itemname);
                    SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_THREE__SLUG", slug);
                }
                Intent i = new Intent(SearchActivity.this, HomeActivity.class);
                i.putExtra("SEARCH_ITEM_CLICK", "SEARCH_ITEM_CLICK");
                startActivity(i);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productdata(position);
            }
        });

    }

    private void productdata(int position) {
        Toast.makeText(SearchActivity.this, "" + itemsdata.get(position).getName(), Toast.LENGTH_SHORT).show();
        String itemname = itemsdata.get(position).getName();
        String profilePic = itemsdata.get(position).getProfilePic();
        String slug = itemsdata.get(position).getSlug();
        if (getIntent().getStringExtra("IMAGE_ITEM_CLICK_NO").equals("1")) {
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG", profilePic);
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_NAME", itemname);
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_SLUG", slug);
        } else if (getIntent().getStringExtra("IMAGE_ITEM_CLICK_NO").equals("2")) {
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_TWO", profilePic);
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_TWO_NAME", itemname);
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_TWO_PRODUCT_IMG_SLUG", slug);
        } else if (getIntent().getStringExtra("IMAGE_ITEM_CLICK_NO").equals("3")) {
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_THREE", profilePic);
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_THREE_NAME", itemname);
            SharedPrefsUtil.setStringPreference(getApplicationContext(), "PRODUCT_IMG_THREE__SLUG", slug);
        }
        Intent i = new Intent(SearchActivity.this, HomeActivity.class);
        i.putExtra("SEARCH_ITEM_CLICK", "SEARCH_ITEM_CLICK");
        startActivity(i);
    }

    private void initializeViews() {
        search = (SearchView) findViewById(R.id.search);
        listView = (ListView) findViewById(R.id.list_view);
        myStyleFragmentOne = new MyStyleFragment();
        fragmentManager = getSupportFragmentManager();
        toolbar_frag_multiicons_title = (TextView) findViewById(R.id.toolbar_frag_multiicons_title);
        toolbar_frag_multiicons_toolbar = (Toolbar) findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_back_navigation = (ImageButton) findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_notification = (ImageButton) findViewById(R.id.toolbar_frag_multiicons_notification);
        toolbar_frag_multiicons_cart = (ImageButton) findViewById(R.id.toolbar_frag_multiicons_cart);
        toolbar_frag_multiicons_search = (ImageButton) findViewById(R.id.toolbar_frag_multiicons_search);
        toolbar_frag_multiicons_overflow = (ImageButton) findViewById(R.id.toolbar_frag_multiicons_overflow);
        gridView = (GridView) findViewById(R.id.gridview);
        retrofitInit(getIntent().getStringExtra("CATEGORY_NO").toString());
    }

    private void initializeRest() {
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        toolbar_frag_multiicons_cart.setVisibility(View.GONE);
        toolbar_frag_multiicons_notification.setVisibility(View.GONE);
        toolbar_frag_multiicons_search.setVisibility(View.GONE);
        toolbar_frag_multiicons_overflow.setVisibility(View.GONE);
    }

    private void retrofitInit(String categoryno) {
        if (categoryno.equals("1")) {
            toolbar_frag_multiicons_title.setText("Product");
            Toast.makeText(getApplicationContext(), "Product data", Toast.LENGTH_SHORT).show();
            categorynodataRequestFromServer(ApiClient.PRODUCT_DATA_URL);
        } else if (categoryno.equals("2")) {
            toolbar_frag_multiicons_title.setText("Movie Store");
            Toast.makeText(getApplicationContext(), "Movie store data", Toast.LENGTH_SHORT).show();
            categorynodataRequestFromServer(ApiClient.MOVIE_STORE_DATA_URL);
        } else if (categoryno.equals("3")) {
            toolbar_frag_multiicons_title.setText("Celeb Store");
            Toast.makeText(getApplicationContext(), "Celeb store data", Toast.LENGTH_SHORT).show();
            categorynodataRequestFromServer(ApiClient.CELEB_STORE_DATA_URL);
        } else if (categoryno.equals("4")) {
            toolbar_frag_multiicons_title.setText("Design");
            Toast.makeText(getApplicationContext(), "Design data", Toast.LENGTH_SHORT).show();
            categorynodataRequestFromServer(ApiClient.DESIGN_DATA_URL);
        } else if (categoryno.equals("5")) {
            toolbar_frag_multiicons_title.setText("Brand");
            Toast.makeText(getApplicationContext(), "Brand data", Toast.LENGTH_SHORT).show();
            categorynodataRequestFromServer(ApiClient.BRAND_DATA_URL);
        }
    }

    private void categorynodataRequestFromServer(String STYLE_REQ_URL) {
        ApiInterface apiService = ApiClient.getClientData().create(ApiInterface.class);
        Call<StyleSearchData> call = apiService.getStyletypeData(STYLE_REQ_URL);
        call.enqueue(new Callback<StyleSearchData>() {
            @Override
            public void onResponse(Call<StyleSearchData> call,
                                   Response<StyleSearchData> response) {
                try {
                    Integer ScannedCount = response.body().getScannedCount();
                    itemsdata = response.body().getItems();
                    Log.e("ScannedCount", ScannedCount + "data");
                    adapter = new SearchListAdapter(getApplicationContext(), itemsdata);
                    gridView.setAdapter(adapter);
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
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(SearchActivity.this, HomeActivity.class);
        i.putExtra("SEARCH_ITEM_CLICK", "SEARCH_ITEM_CLICK");
        startActivity(i);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
//            fragmentManager.popBackStack();
            Intent i = new Intent(SearchActivity.this, HomeActivity.class);
            i.putExtra("SEARCH_ITEM_CLICK", "SEARCH_ITEM_CLICK");
            startActivity(i);
        }
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
