package com.flikster.HomeActivity.SearchViewFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductOnClick;
import com.flikster.HomeActivity.GlobalSearchGetData;
import com.flikster.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 03-01-2018.
 */

public class SearchInnerItemProductRecyclerViewHolder extends RecyclerView.Adapter<SearchInnerItemProductRecyclerViewHolder.ViewHolder> {
    Context context;
    List<GlobalSearchGetData.SearchProductsData> searchProductsDatas;
    SearchViewFragment.SearchViewToFrag searchViewToFrag;
    ApiInterface apiInterface;
    public SearchInnerItemProductRecyclerViewHolder(Context context, List<GlobalSearchGetData.SearchProductsData> searchProductsDatas,
                                                    SearchViewFragment.SearchViewToFrag searchViewToFrag) {
        this.context=context;
        this.searchProductsDatas=searchProductsDatas;
        this.searchViewToFrag=searchViewToFrag;
    }

    @Override
    public SearchInnerItemProductRecyclerViewHolder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_inner_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchInnerItemProductRecyclerViewHolder.ViewHolder holder, int position) {
        holder.textView.setText(searchProductsDatas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return searchProductsDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.searchview_inner_recycler_item);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            getRetrofitForProduct(searchProductsDatas.get(getAdapterPosition()).getId());
        }

        public void getRetrofitForProduct(String id)
        {
                apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/product-ms/getProductById/").create(ApiInterface.class);
            Call<SearchProductOnClickData> call = apiInterface.getProductData("http://apiservice.flikster.com/v3/product-ms/getProductById/" + id);
            call.enqueue(new Callback<SearchProductOnClickData>() {
                @Override
                public void onResponse(Call<SearchProductOnClickData> call, Response<SearchProductOnClickData> response) {
                    searchViewToFrag.onBuyClick(response.body().getId(),
                            response.body().getSize(), "abhiint",
                            response.body().getPrice(),
                            response.body().getProfilePic(),
                            response.body().getName(),
                            response.body().getSlug(),
                            response.body().getImageGallery(),
                            response.body().getProductDescription(),
                            response.body().getProductInfo(), new ProductOnClick());
                }
                @Override
                public void onFailure(Call<SearchProductOnClickData> call, Throwable t) {
                    Log.e("vvvvvvvvvv", "vv" + call + t);
                }
            });

        }

    }
}
