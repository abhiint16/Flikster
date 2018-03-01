package com.flikster.HomeActivity;

import android.app.Dialog;
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

public class DialogFilterIndustryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    String title;
    List<String> industry = new ArrayList<>();
    List<String> contentType = new ArrayList<>();
    Button dialog_filter_industry_contenttype_reset_btn;
    Context context;
    List<String> contentTypeSelected = new ArrayList<>();
    String industry_type;
    Button apply_btn_dialog;
    int m=0;
    Dialog dialog;
    DialogCommunication dialogCommunication;

    public DialogFilterIndustryAdapter(String title, Button dialog_filter_industry_contenttype_reset_btn, Context context,
                                       Button apply_btn_dialog,Dialog  dialog,DialogCommunication dialogCommunication) {
        this.title = title;
        this.dialogCommunication=dialogCommunication;
        this.dialog=dialog;
        this.apply_btn_dialog = apply_btn_dialog;
        this.context = context;
        setHasStableIds(true);
        this.dialog_filter_industry_contenttype_reset_btn = dialog_filter_industry_contenttype_reset_btn;
        industry.add("Bollywood");
        industry.add("Tollywood");
        industry.add("Kollywood");
        industry.add("Mollywood");
        industry.add("Sandalwood");
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==100)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_industry_item,
                    parent, false);
            return new ViewHolder1(view);
        }
        else
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_filter_industry_contenttype_recycler_item,
                    parent, false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==100)
        {
            ((ViewHolder1)holder).dialog_bollywood_text.setText(industry.get(0));
            ((ViewHolder1)holder).dialog_tollywood_text.setText(industry.get(1));
            ((ViewHolder1)holder).dialog_tamil_text.setText(industry.get(2));
            ((ViewHolder1)holder).dialog_kannada_text.setText(industry.get(3));
            ((ViewHolder1)holder).dialog_malayalam_text.setText(industry.get(4));
        }
        else
        {
            if ("Filter".equals(title)) {
                if (contentTypeSelected.size() == 0) {
                    dialog_filter_industry_contenttype_reset_btn.setEnabled(false);
                    ((ViewHolder2)holder).checkBox.setChecked(false);
                }
                ((ViewHolder2)holder).textView.setText(contentType.get(position));
                ((ViewHolder2)holder).radioButton.setVisibility(View.GONE);
            } else {
                ((ViewHolder2)holder).textView.setText(industry.get(position));
                ((ViewHolder2)holder).checkBox.setVisibility(View.GONE);
                if (industry.get(position).equals(industry_type));
                {
                    ((ViewHolder2)holder).radioButton.setChecked(true);
                }
                ((ViewHolder2)holder).radioButton.setChecked(false);
        }
        }
    }

    // for removing item duplication, adding this things. getItemViewType(), getItemId(), and one line in constructor (setHasStableIds(true))
    @Override
    public int getItemViewType(int position) {
        if (!"Filter".equals(title))
            return 100;
        return position;
    }

    @Override
    public int getItemCount() {
        if ("Filter".equals(title))
            return contentType.size();
        else return 1;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dialog_bollywood_text,dialog_tollywood_text,dialog_tamil_text,dialog_kannada_text,dialog_malayalam_text;
        RadioButton dialog_malayalam_btn,dialog_kannada_btn,dialog_tamil_btn,dialog_tollywood_btn,dialog_bollywood_btn;
        public ViewHolder1(View itemView)
        {
            super(itemView);
            dialog_bollywood_text=(TextView)itemView.findViewById(R.id.dialog_bollywood_text);
            dialog_tollywood_text=(TextView)itemView.findViewById(R.id.dialog_tollywood_text);
            dialog_tamil_text=(TextView)itemView.findViewById(R.id.dialog_tamil_text);
            dialog_kannada_text=(TextView)itemView.findViewById(R.id.dialog_kannada_text);
            dialog_malayalam_text=(TextView)itemView.findViewById(R.id.dialog_malayalam_text);

            dialog_malayalam_btn=(RadioButton) itemView.findViewById(R.id.dialog_malayalam_btn);
            dialog_kannada_btn=(RadioButton)itemView.findViewById(R.id.dialog_kannada_btn);
            dialog_tamil_btn=(RadioButton)itemView.findViewById(R.id.dialog_tamil_btn);
            dialog_tollywood_btn=(RadioButton)itemView.findViewById(R.id.dialog_tollywood_btn);
            dialog_bollywood_btn=(RadioButton)itemView.findViewById(R.id.dialog_bollywood_btn);

            dialog_bollywood_btn.setOnClickListener(this);
            dialog_tollywood_btn.setOnClickListener(this);
            dialog_tamil_btn.setOnClickListener(this);
            dialog_kannada_btn.setOnClickListener(this);
            dialog_malayalam_btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.dialog_bollywood_btn)
            {
                dialog_bollywood_btn.setChecked(true);
                dialog_tollywood_btn.setChecked(false);
                dialog_tamil_btn.setChecked(false);
                dialog_kannada_btn.setChecked(false);
                dialog_malayalam_btn.setChecked(false);
                industry_type= (String) dialog_bollywood_text.getText();
                dialog_filter_industry_contenttype_reset_btn.setEnabled(true);
                dialog_filter_industry_contenttype_reset_btn.setOnClickListener(this);
                apply_btn_dialog.setVisibility(View.VISIBLE);
                apply_btn_dialog.setOnClickListener(this);
            }
            else if (view.getId()==R.id.dialog_tollywood_btn)
            {
                dialog_bollywood_btn.setChecked(false);
                dialog_tollywood_btn.setChecked(true);
                dialog_tamil_btn.setChecked(false);
                dialog_kannada_btn.setChecked(false);
                dialog_malayalam_btn.setChecked(false);
                industry_type= (String) dialog_tollywood_text.getText();
                dialog_filter_industry_contenttype_reset_btn.setEnabled(true);
                dialog_filter_industry_contenttype_reset_btn.setOnClickListener(this);
                apply_btn_dialog.setVisibility(View.VISIBLE);
                apply_btn_dialog.setOnClickListener(this);
            }
            else if (view.getId()==R.id.dialog_tamil_btn)
            {
                dialog_bollywood_btn.setChecked(false);
                dialog_tollywood_btn.setChecked(false);
                dialog_tamil_btn.setChecked(true);
                dialog_kannada_btn.setChecked(false);
                dialog_malayalam_btn.setChecked(false);
                industry_type= (String) dialog_tamil_text.getText();
                dialog_filter_industry_contenttype_reset_btn.setEnabled(true);
                dialog_filter_industry_contenttype_reset_btn.setOnClickListener(this);
                apply_btn_dialog.setVisibility(View.VISIBLE);
                apply_btn_dialog.setOnClickListener(this);
            }
            else if (view.getId()==R.id.dialog_kannada_btn)
            {
                dialog_bollywood_btn.setChecked(false);
                dialog_tollywood_btn.setChecked(false);
                dialog_tamil_btn.setChecked(false);
                dialog_kannada_btn.setChecked(true);
                dialog_malayalam_btn.setChecked(false);
                industry_type= (String) dialog_kannada_text.getText();
                dialog_filter_industry_contenttype_reset_btn.setEnabled(true);
                dialog_filter_industry_contenttype_reset_btn.setOnClickListener(this);
                apply_btn_dialog.setVisibility(View.VISIBLE);
                apply_btn_dialog.setOnClickListener(this);
            }
            else if (view.getId()==R.id.dialog_malayalam_btn)
            {
                dialog_bollywood_btn.setChecked(false);
                dialog_tollywood_btn.setChecked(false);
                dialog_tamil_btn.setChecked(false);
                dialog_kannada_btn.setChecked(false);
                dialog_malayalam_btn.setChecked(true);
                industry_type= (String) dialog_malayalam_text.getText();
                dialog_filter_industry_contenttype_reset_btn.setEnabled(true);
                dialog_filter_industry_contenttype_reset_btn.setOnClickListener(this);
                apply_btn_dialog.setVisibility(View.VISIBLE);
                apply_btn_dialog.setOnClickListener(this);
            }
            else if (view.getId()==R.id.dialog_filter_industry_contenttype_reset_btn)
            {
                industry_type="";
                apply_btn_dialog.setVisibility(View.GONE);
                dialog_filter_industry_contenttype_reset_btn.setEnabled(false);
                dialog_bollywood_btn.setChecked(false);
                dialog_tollywood_btn.setChecked(false);
                dialog_tamil_btn.setChecked(false);
                dialog_kannada_btn.setChecked(false);
                dialog_malayalam_btn.setChecked(false);
            }
            else if (view.getId()==R.id.apply_btn_dialog)
            {
                //SharedPrefsUtil.setStringPreference(context.getApplicationContext(), "INDUSTRY_TYPE", industry_type);
                dialogCommunication.getDialogValue(industry_type,"Industry");
                dialog.dismiss();
            }
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        TextView textView;
        CheckBox checkBox;
        RadioButton radioButton;

        public ViewHolder2(View itemView) {
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
            if (compoundButton.getId() == R.id.dialog_check_box) {
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
