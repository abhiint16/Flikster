package com.flikster.HomeActivity.CommonFragments.MyStyleFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anupamchugh on 07/02/16.
 */
public class StyleListDataAdapter extends BaseAdapter implements Filterable {

    List<SearchInnerData> mData;
    List<SearchInnerData> mStringFilterList;
    List<SearchInnerData> mCopyFilterList;
    ValueFilter valueFilter;
    private LayoutInflater inflater;
    View view;
    TextView product_name;

    public StyleListDataAdapter(List<SearchInnerData> cancel_type) {
        mData = cancel_type;
        mStringFilterList = cancel_type;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return String.valueOf(mData.size());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        view = inflater.inflate(R.layout.search_item, parent, false);
        product_name = (TextView) view.findViewById(R.id.product_name);
        product_name.setText(mData.get(position).getName());
        return view;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = null;
            try {
                results = new FilterResults();
                if (constraint != null && constraint.toString().trim().length() > 0) {
                    mCopyFilterList = new ArrayList<SearchInnerData>();
                    List<String> filterList = new ArrayList<>();
                    for (int i = 0; i < mStringFilterList.size(); i++) {
                        if ((mStringFilterList.get(i).getName()).toLowerCase().contains(constraint.toString().toLowerCase())) {
                            mCopyFilterList.add(mStringFilterList.get(i));
                            break;
                        }
                    }
                    mData = mCopyFilterList;
//                    results.count = filterList.size();
//                    results.values = filterList;
                } else {
                    notifyDataSetChanged();
//                    results.count = mStringFilterList.size();
//                    results.values = mStringFilterList;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mData = (List<SearchInnerData>) results.values;
            notifyDataSetChanged();
        }

    }

}
