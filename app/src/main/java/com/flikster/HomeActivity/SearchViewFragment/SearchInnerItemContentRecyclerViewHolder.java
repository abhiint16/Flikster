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

public class SearchInnerItemContentRecyclerViewHolder extends RecyclerView.Adapter<SearchInnerItemContentRecyclerViewHolder.ViewHolder> {
    Context context;
    List<GlobalSearchGetData.SearchContentData> searchContentDatas;

    public SearchInnerItemContentRecyclerViewHolder(Context context, List<GlobalSearchGetData.SearchContentData> searchContentDatas) {
        this.context=context;
        this.searchContentDatas=searchContentDatas;
    }

    @Override
    public SearchInnerItemContentRecyclerViewHolder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_inner_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchInnerItemContentRecyclerViewHolder.ViewHolder holder, int position) {
        holder.textView.setText(searchContentDatas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return searchContentDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.searchview_inner_recycler_item);
        }
    }
}
