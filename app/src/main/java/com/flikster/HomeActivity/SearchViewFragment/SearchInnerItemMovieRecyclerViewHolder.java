package com.flikster.HomeActivity.SearchViewFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragment;
import com.flikster.HomeActivity.GlobalSearchGetData;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import java.util.List;

/**
 * Created by abhishek on 03-01-2018.
 */

public class SearchInnerItemMovieRecyclerViewHolder extends RecyclerView.Adapter<SearchInnerItemMovieRecyclerViewHolder.ViewHolder> {
    Context context;
    List<GlobalSearchGetData.SearchMovieData> searchMovieData;
    SearchViewFragment.SearchViewToFrag searchViewToFrag;
    String userId="null";
    public SearchInnerItemMovieRecyclerViewHolder(Context context, List<GlobalSearchGetData.SearchMovieData> searchMovieDatas,
                                                  SearchViewFragment.SearchViewToFrag searchViewToFrag) {
        this.context=context;
        this.searchMovieData=searchMovieDatas;
        this.searchViewToFrag=searchViewToFrag;
    }

    @Override
    public SearchInnerItemMovieRecyclerViewHolder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_inner_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchInnerItemMovieRecyclerViewHolder.ViewHolder holder, int position) {
        if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null && !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
            userId = SharedPrefsUtil.getStringPreference(context, "USER_ID");
        }
        holder.textView.setText(searchMovieData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return searchMovieData.size();
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
            searchViewToFrag.test(searchMovieData.get(getAdapterPosition()).getSlug(),new MovieFragment(),1,userId,
                    searchMovieData.get(getAdapterPosition()).getId());
        }
    }
}
