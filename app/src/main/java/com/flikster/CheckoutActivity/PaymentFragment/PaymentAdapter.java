package com.flikster.CheckoutActivity.PaymentFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.flikster.R;

/**
 * Created by abhishek on 25-10-2017.
 */

public class PaymentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    public PaymentAdapter(Context context) {
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_payment_mode,parent,false);
            return new ViewHolder1(view);
        }
        else
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fragment_mybag_order_summary,parent,false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==0)
        {
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView card_payment_mode_creditcard_tv,card_payment_mode_deditcard_tv
                ,card_payment_mode_netbanking_tv,card_payment_mode_cash_tv,card_payment_mode_other_tv;
        RadioButton card_payment_mode_creditcard_rb,card_payment_mode_deditcard_rb
                ,card_payment_mode_netbanking_rb,card_payment_mode_cash_rb,card_payment_mode_other_rb;
        public ViewHolder1(View itemView) {
            super(itemView);
            card_payment_mode_creditcard_tv=(TextView)itemView.findViewById(R.id.card_payment_mode_creditcard_tv);
            card_payment_mode_deditcard_tv=(TextView)itemView.findViewById(R.id.card_payment_mode_deditcard_tv);
            card_payment_mode_netbanking_tv=(TextView)itemView.findViewById(R.id.card_payment_mode_netbanking_tv);
            card_payment_mode_cash_tv=(TextView)itemView.findViewById(R.id.card_payment_mode_cash_tv);
            card_payment_mode_other_tv=(TextView)itemView.findViewById(R.id.card_payment_mode_other_tv);
            card_payment_mode_creditcard_rb=(RadioButton)itemView.findViewById(R.id.card_payment_mode_creditcard_rb);
            card_payment_mode_deditcard_rb=(RadioButton)itemView.findViewById(R.id.card_payment_mode_deditcard_rb);
            card_payment_mode_netbanking_rb=(RadioButton)itemView.findViewById(R.id.card_payment_mode_netbanking_rb);
            card_payment_mode_cash_rb=(RadioButton)itemView.findViewById(R.id.card_payment_mode_cash_rb);
            card_payment_mode_other_rb=(RadioButton)itemView.findViewById(R.id.card_payment_mode_other_rb);
            card_payment_mode_creditcard_rb.setOnClickListener(this);
            card_payment_mode_deditcard_rb.setOnClickListener(this);
            card_payment_mode_netbanking_rb.setOnClickListener(this);
            card_payment_mode_cash_rb.setOnClickListener(this);
            card_payment_mode_other_rb.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.card_payment_mode_creditcard_rb)
            {
                card_payment_mode_creditcard_rb.setChecked(true);
                card_payment_mode_deditcard_rb.setChecked(false);
                card_payment_mode_netbanking_rb.setChecked(false);
                card_payment_mode_cash_rb.setChecked(false);
                card_payment_mode_other_rb.setChecked(false);
                card_payment_mode_creditcard_tv.setTextColor(context.getResources().getColor(R.color.colorAccent));
                card_payment_mode_deditcard_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_netbanking_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_cash_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_other_tv.setTextColor(context.getResources().getColor(R.color.black));

            }
            else if(view.getId()==R.id.card_payment_mode_deditcard_rb)
            {
                card_payment_mode_creditcard_rb.setChecked(false);
                card_payment_mode_deditcard_rb.setChecked(true);
                card_payment_mode_netbanking_rb.setChecked(false);
                card_payment_mode_cash_rb.setChecked(false);
                card_payment_mode_other_rb.setChecked(false);
                card_payment_mode_creditcard_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_deditcard_tv.setTextColor(context.getResources().getColor(R.color.colorAccent));
                card_payment_mode_netbanking_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_cash_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_other_tv.setTextColor(context.getResources().getColor(R.color.black));
            }
            else if(view.getId()==R.id.card_payment_mode_netbanking_rb)
            {
                card_payment_mode_creditcard_rb.setChecked(false);
                card_payment_mode_deditcard_rb.setChecked(false);
                card_payment_mode_netbanking_rb.setChecked(true);
                card_payment_mode_cash_rb.setChecked(false);
                card_payment_mode_other_rb.setChecked(false);
                card_payment_mode_creditcard_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_deditcard_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_netbanking_tv.setTextColor(context.getResources().getColor(R.color.colorAccent));
                card_payment_mode_cash_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_other_tv.setTextColor(context.getResources().getColor(R.color.black));
            }
            else if(view.getId()==R.id.card_payment_mode_cash_rb)
            {
                card_payment_mode_creditcard_rb.setChecked(false);
                card_payment_mode_deditcard_rb.setChecked(false);
                card_payment_mode_netbanking_rb.setChecked(false);
                card_payment_mode_cash_rb.setChecked(true);
                card_payment_mode_other_rb.setChecked(false);
                card_payment_mode_creditcard_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_deditcard_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_netbanking_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_cash_tv.setTextColor(context.getResources().getColor(R.color.colorAccent));
                card_payment_mode_other_tv.setTextColor(context.getResources().getColor(R.color.black));
            }
            else if(view.getId()==R.id.card_payment_mode_other_rb)
            {
                card_payment_mode_creditcard_rb.setChecked(false);
                card_payment_mode_deditcard_rb.setChecked(false);
                card_payment_mode_netbanking_rb.setChecked(false);
                card_payment_mode_cash_rb.setChecked(false);
                card_payment_mode_other_rb.setChecked(true);
                card_payment_mode_creditcard_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_deditcard_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_netbanking_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_cash_tv.setTextColor(context.getResources().getColor(R.color.black));
                card_payment_mode_other_tv.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        public ViewHolder2(View itemView) {
            super(itemView);
        }
    }
}
