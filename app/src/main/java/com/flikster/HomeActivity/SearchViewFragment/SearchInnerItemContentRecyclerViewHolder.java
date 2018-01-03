package com.flikster.HomeActivity.SearchViewFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.HomeActivity.CommonFragments.NewsFragment.NewsOnClickFragment;
import com.flikster.HomeActivity.GlobalSearchGetData;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import java.util.List;

/**
 * Created by abhishek on 03-01-2018.
 */

public class SearchInnerItemContentRecyclerViewHolder extends RecyclerView.Adapter<SearchInnerItemContentRecyclerViewHolder.ViewHolder> {
    Context context;
    List<GlobalSearchGetData.SearchContentData> searchContentDatas;
    SearchViewFragment.SearchViewToFrag searchViewToFrag;
    String userId="null";
    public SearchInnerItemContentRecyclerViewHolder(Context context, List<GlobalSearchGetData.SearchContentData> searchContentDatas,
                                                    SearchViewFragment.SearchViewToFrag searchViewToFrag) {
        this.context=context;
        this.searchContentDatas=searchContentDatas;
        this.searchViewToFrag=searchViewToFrag;

    }

    @Override
    public SearchInnerItemContentRecyclerViewHolder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_inner_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchInnerItemContentRecyclerViewHolder.ViewHolder holder, int position) {
        if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null && !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
            userId = SharedPrefsUtil.getStringPreference(context, "USER_ID");
        }
        holder.textView.setText(searchContentDatas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return searchContentDatas.size();
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
            searchViewToFrag.newsCardOnClick("",
                    "",
                    "",
                    searchContentDatas.get(getAdapterPosition()).getProfilePic(),
                    searchContentDatas.get(getAdapterPosition()).getName(),
                    searchContentDatas.get(getAdapterPosition()).getName(),
                    new NewsOnClickFragment(),
                    searchContentDatas.get(getAdapterPosition()).getContentType(),
                    userId,
                    searchContentDatas.get(getAdapterPosition()).getId());
        }
    }
}
