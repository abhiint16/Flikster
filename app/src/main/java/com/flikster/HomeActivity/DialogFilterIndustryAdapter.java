package com.flikster.HomeActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 27-02-2018.
 */

public class DialogFilterIndustryAdapter extends RecyclerView.Adapter<DialogFilterIndustryAdapter.ViewHolder> {

    String title;
    List<String> industry = new ArrayList<>();
    List<String> contentType = new ArrayList<>();
    Button dialog_filter_industry_contenttype_reset_btn;
    Context context;
    List<String> contentTypeSelected = new ArrayList<>();
    Button apply_btn_dialog;

    public DialogFilterIndustryAdapter(String title, Button dialog_filter_industry_contenttype_reset_btn, Context context,
                                       Button apply_btn_dialog) {
        this.title = title;
        this.apply_btn_dialog = apply_btn_dialog;
        this.context = context;
        setHasStableIds(true);
        this.dialog_filter_industry_contenttype_reset_btn = dialog_filter_industry_contenttype_reset_btn;
        industry.add("Bollywood");
        industry.add("Tollywood");
        industry.add("Tamil");
        industry.add("Kannada");
        industry.add("Malayalam");
        contentType.add("News");
        contentType.add("First Look");
        contentType.add("Gallery");
        contentType.add("Teasers & Promos");
        contentType.add("Trailers");
        contentType.add("Posters");
        contentType.add("Juke Box");
        contentType.add("Video Songs");
        contentType.add("Interviews");
        contentType.add("Movie Making");
        contentType.add("Comedy Clips");
        contentType.add("Social Buzz");
        contentType.add("Dialouges");
        contentType.add("Tweets");
        contentType.add("Quotes");
    }

    @Override
    public DialogFilterIndustryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_filter_industry_contenttype_recycler_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DialogFilterIndustryAdapter.ViewHolder holder, int position) {
        if ("Filter".equals(title)) {
            if (contentTypeSelected.size() == 0) {
                dialog_filter_industry_contenttype_reset_btn.setEnabled(false);
                holder.checkBox.setChecked(false);
            }
            holder.textView.setText(contentType.get(position));
            holder.radioButton.setVisibility(View.GONE);
        } else {
            holder.textView.setText(industry.get(position));
            holder.checkBox.setVisibility(View.GONE);
            Log.e("check industrtype", "" + SharedPrefsUtil.getStringPreference(context.getApplicationContext(), "INDUSTRY_TYPE"));
            if (industry.get(position).equals(SharedPrefsUtil.getStringPreference(context.getApplicationContext(), "INDUSTRY_TYPE")))
                ;
            {
                Log.e("check inside if" + industry.get(position), "chck inside dif" + SharedPrefsUtil.getStringPreference(context.getApplicationContext(), "INDUSTRY_TYPE"));
                holder.radioButton.setChecked(true);
            }
        }
    }

    // for removing item duplication, adding this things. getItemViewType(), getItemId(), and one line in constructor (setHasStableIds(true))
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if ("Filter".equals(title))
            return contentType.size();
        else return industry.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        TextView textView;
        CheckBox checkBox;
        RadioButton radioButton;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.dialog_text);
            checkBox = (CheckBox) itemView.findViewById(R.id.dialog_check_box);
            radioButton = (RadioButton) itemView.findViewById(R.id.dialog_radio_btn);
            itemView.setOnClickListener(this);
            radioButton.setOnCheckedChangeListener(this);
            checkBox.setOnCheckedChangeListener(this);
            /*if (contentTypeSelected.size()!=0)
            {
                dialog_filter_industry_contenttype_reset_btn.setEnabled(true);
                dialog_filter_industry_contenttype_reset_btn.setOnClickListener(this);
            }*/
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() == 0) {

            } else if (view.getId() == R.id.dialog_filter_industry_contenttype_reset_btn) {
                apply_btn_dialog.setVisibility(View.GONE);
                contentTypeSelected.clear();
                notifyDataSetChanged();
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (compoundButton.getId() == R.id.dialog_radio_btn) {

            } else if (compoundButton.getId() == R.id.dialog_check_box) {
                Log.e("chekc forf cehclbox", "cehcke fof cbox" + b);
                if (b) {
                    dialog_filter_industry_contenttype_reset_btn.setEnabled(true);
                    dialog_filter_industry_contenttype_reset_btn.setOnClickListener(this);
                    apply_btn_dialog.setVisibility(View.VISIBLE);
                    contentTypeSelected.add(contentType.get(getAdapterPosition()));
                } else if (!b) {
                    contentTypeSelected.remove(contentType.get(getAdapterPosition()));
                    if (contentTypeSelected.size() == 0) {
                        apply_btn_dialog.setVisibility(View.GONE);
                        dialog_filter_industry_contenttype_reset_btn.setEnabled(false);
                    }
                }
                /*if (contentTypeSelected.size()!=0)
                {
                    dialog_filter_industry_contenttype_reset_btn.setEnabled(true);
                    dialog_filter_industry_contenttype_reset_btn.setOnClickListener(this);
                }*/
                Log.e("checc for list", "check for list" + contentTypeSelected);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
