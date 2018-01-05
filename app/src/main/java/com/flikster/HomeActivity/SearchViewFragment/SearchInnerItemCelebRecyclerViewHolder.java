package com.flikster.HomeActivity.SearchViewFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragment;
import com.flikster.HomeActivity.CommonFragments.MovieFragment.MovieFragment;
import com.flikster.HomeActivity.GlobalSearchGetData;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import java.util.List;

/**
 * Created by abhishek on 03-01-2018.
 */

public class SearchInnerItemCelebRecyclerViewHolder extends RecyclerView.Adapter<SearchInnerItemCelebRecyclerViewHolder.ViewHolder> {
    Context context;
    List<GlobalSearchGetData.SearchCelebData> searchCelebDatas;
    SearchViewFragment.SearchViewToFrag searchViewToFrag;
    String userId="null";
    public SearchInnerItemCelebRecyclerViewHolder(Context context, List<GlobalSearchGetData.SearchCelebData> searchCelebDatas,
                                                  SearchViewFragment.SearchViewToFrag searchViewToFrag) {
        this.context=context;
        this.searchCelebDatas=searchCelebDatas;
        this.searchViewToFrag=searchViewToFrag;
    }

    @Override
    public SearchInnerItemCelebRecyclerViewHolder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_inner_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchInnerItemCelebRecyclerViewHolder.ViewHolder holder, int position) {
        if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null && !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
            userId = SharedPrefsUtil.getStringPreference(context, "USER_ID");
        }
        holder.textView.setText(searchCelebDatas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return searchCelebDatas.size();
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
            searchViewToFrag.test(searchCelebDatas.get(getAdapterPosition()).getSlug(),new CelebrityFragment(),2,userId,
                    searchCelebDatas.get(getAdapterPosition()).getId());
        }
    }
}
