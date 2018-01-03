package com.flikster.HomeActivity.SearchViewFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.HomeActivity.GlobalSearchGetData;
import com.flikster.R;

import java.util.List;

/**
 * Created by abhishek on 03-01-2018.
 */

public class SearchInnerItemProductRecyclerViewHolder extends RecyclerView.Adapter<SearchInnerItemProductRecyclerViewHolder.ViewHolder> {
    Context context;
    List<GlobalSearchGetData.SearchProductsData> searchProductsDatas;

    public SearchInnerItemProductRecyclerViewHolder(Context context, List<GlobalSearchGetData.SearchProductsData> searchProductsDatas) {
        this.context=context;
        this.searchProductsDatas=searchProductsDatas;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.searchview_inner_recycler_item);
        }
    }
}
