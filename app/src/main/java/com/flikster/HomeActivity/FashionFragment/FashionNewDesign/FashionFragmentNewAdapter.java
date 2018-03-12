package com.flikster.HomeActivity.FashionFragment.FashionNewDesign;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.FashionFragment.FashionNewDesign.FashionFragContainerClick.FashionContainerClick;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreData;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreInnerData;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 07-03-2018.
 */

public class FashionFragmentNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<String> fashion_container_title = new ArrayList<>();
    List<String> fashion_URLs = new ArrayList<>();
    List<String> fashion_container_pref = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    FashionHorRecyclerAdapter fashionHorRecyclerAdapter;
    ApiInterface apiInterface;
    AllStoreInnerData hits;
    FashionFragmentNew.FashionOnClick fashionOnClick;

    public FashionFragmentNewAdapter(Context context, FashionFragmentNew.FashionOnClick fashionOnClick) {
        this.context = context;
        this.fashionOnClick=fashionOnClick;
        fashion_container_title.add("Celebrity Store");
        fashion_container_title.add("Movie Store");
        fashion_container_title.add("Men Fashion");
        fashion_container_title.add("Women Fashion");
        fashion_container_title.add("Designer Store");
        fashion_container_title.add("Brands");
        fashion_URLs.add("http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=5&from=0&q=celeb");
        fashion_URLs.add("http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=5&from=0&q=movie");
        fashion_URLs.add("http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=5&from=0&q=category:%22Men%20Fashion%22");
        fashion_URLs.add("http://apiservice-ec.flikster.com/products/_search?pretty=true&sort=createdAt:desc&size=5&from=0&q=category:%22Women%20Fashion%22");
        fashion_URLs.add("");
        fashion_URLs.add("");
        fashion_container_pref.add("CELEB_STORE");
        fashion_container_pref.add("MOVIE_STORE");
        fashion_container_pref.add("MEN_FASHION");
        fashion_container_pref.add("WOMEN_FASHION");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_fashion_new, parent, false);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder1) holder).fragment_fashion_new_title.setText(fashion_container_title.get(position));
        retrofitInit(position,((ViewHolder1) holder).fragment_fashion_new_recycler);
    }

    public void retrofitInit(final int pos, final RecyclerView recyclerView) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/products/_search/").create(ApiInterface.class);
        Call<AllStoreData> call = apiInterface.getAllStore(fashion_URLs.get(pos));
        call.enqueue(new Callback<AllStoreData>() {
            @Override
            public void onResponse(Call<AllStoreData> call, Response<AllStoreData> response) {
                hits = response.body().getHits();
                layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                fashionHorRecyclerAdapter = new FashionHorRecyclerAdapter(context,hits,fashionOnClick,fashion_URLs.get(pos));
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(fashionHorRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<AllStoreData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView fragment_fashion_new_recycler;
        RelativeLayout fragment_fashion_new_container;
        TextView fragment_fashion_new_title;

        public ViewHolder1(View itemView) {
            super(itemView);
            fragment_fashion_new_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_fashion_new_recycler);
            fragment_fashion_new_container = (RelativeLayout) itemView.findViewById(R.id.fragment_fashion_new_container);
            fragment_fashion_new_title = (TextView) itemView.findViewById(R.id.fragment_fashion_new_title);
            fragment_fashion_new_container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.fragment_fashion_new_container)
            {
                SharedPrefsUtil.setStringPreference(context.getApplicationContext(), "HEADER_NAME", fashion_container_pref.get(getAdapterPosition()));
                fashionOnClick.fashionContainerClick(new FashionContainerClick());
            }
        }
    }
}
