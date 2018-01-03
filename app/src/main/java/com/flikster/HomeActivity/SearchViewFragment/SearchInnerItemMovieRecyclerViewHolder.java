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

public class SearchInnerItemMovieRecyclerViewHolder extends RecyclerView.Adapter<SearchInnerItemMovieRecyclerViewHolder.ViewHolder> {
    Context context;
    List<GlobalSearchGetData.SearchMovieData> searchMovieData;

    public SearchInnerItemMovieRecyclerViewHolder(Context context, List<GlobalSearchGetData.SearchMovieData> searchMovieDatas) {
        this.context=context;
        this.searchMovieData=searchMovieDatas;
    }

    @Override
    public SearchInnerItemMovieRecyclerViewHolder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_inner_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchInnerItemMovieRecyclerViewHolder.ViewHolder holder, int position) {
        holder.textView.setText(searchMovieData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return searchMovieData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.searchview_inner_recycler_item);
        }
    }
}
