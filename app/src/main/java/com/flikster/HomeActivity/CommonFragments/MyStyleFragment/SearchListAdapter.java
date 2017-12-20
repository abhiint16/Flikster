package com.flikster.HomeActivity.CommonFragments.MyStyleFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.R;
import com.flikster.Util.SquareImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Logins on 14-12-2017.
 */

public class SearchListAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public List<SearchInnerData> employeeArrayList;
    public List<SearchInnerData> orig;

    public ArrayList<SearchInnerData> searchList;

    public SearchListAdapter(Context context, List<SearchInnerData> employeeArrayList) {
        super();
        this.context = context;
        this.employeeArrayList = employeeArrayList;
    }


    public class SearchListHolder {
        TextView name;
        SquareImageView productimg;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<SearchInnerData> results = new ArrayList<SearchInnerData>();
                if (orig == null)
                    orig = employeeArrayList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final SearchInnerData availablename : orig) {
                            if (availablename.getName().toLowerCase().trim().contains(constraint.toString().toLowerCase().trim())) {
                                results.add(availablename);
                                Log.e("Searchkey", availablename + "");
                                Log.e("Search Key", constraint.toString().toLowerCase().trim() + "");
                            }
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                employeeArrayList = (ArrayList<SearchInnerData>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return employeeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchListHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.search_item, parent,
                    false);
            holder = new SearchListHolder();
            holder.name = (TextView) convertView.findViewById(R.id.product_name);
            holder.productimg = (SquareImageView) convertView.findViewById(R.id.picture);
//            Glide.with(context).load(R.drawable.loadinggif).into(holder.productimg);
//            holder.age = (TextView) convertView.findViewById(R.id.txtAge);
            convertView.setTag(holder);
        } else {
            holder = (SearchListHolder) convertView.getTag();
        }
        Log.e("profileimageurl", employeeArrayList.get(position).getProfilePic());
        holder.name.setText(employeeArrayList.get(position).getName());
        Glide.with(context).load(employeeArrayList.get(position).getProfilePic()).into(holder.productimg);
        return convertView;

    }

    public void setFilter(ArrayList<SearchInnerData> searchInnerData) {
        searchList = new ArrayList<>();
        searchList.addAll(searchInnerData);
        notifyDataSetChanged();
    }

}
