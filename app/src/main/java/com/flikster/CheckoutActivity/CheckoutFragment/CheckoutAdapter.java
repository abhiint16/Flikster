package com.flikster.CheckoutActivity.CheckoutFragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.flikster.CheckoutActivity.AddressFragment.AddressFragment;
import com.flikster.R;

/**
 * Created by abhishek on 25-10-2017.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    FragmentManager fragmentManager;
    Context context;
    public CheckoutAdapter(FragmentManager fragmentManager,Context context) {
        this.fragmentManager=fragmentManager;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_checkout_customer_details,parent,false);
            return new ViewHolder1(view);
        }
        else if(viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mybag_recycler_item,parent,false);
            return new ViewHolder2(view);
        }
        else if(viewType==2)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_checkout_promo,parent,false);
            return new ViewHolder3(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fragment_mybag_order_summary,parent,false);
            return new ViewHolder4(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==1)
        {
            ((ViewHolder2)holder).notification_item_cancel_btn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button card_checkout_customer_details_button;
        public ViewHolder1(View itemView) {
            super(itemView);
            card_checkout_customer_details_button=(Button)itemView.findViewById(R.id.card_checkout_customer_details_button);
            card_checkout_customer_details_button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentManager.beginTransaction()
                    .replace(R.id.activity_mybag_continue_onclick_container,new AddressFragment())
                    .addToBackStack("")
                    .commit();
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageButton notification_item_cancel_btn;
        public ViewHolder2(View itemView) {
            super(itemView);
            //notification_item_cancel_btn=(ImageButton)itemView.findViewById(R.id.notification_item_cancel_btn);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button button;
        public ViewHolder3(View itemView) {
            super(itemView);
            button=(Button)itemView.findViewById(R.id.card_checkout_promo_btn);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_enter_promocode);
            final Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            Button dialog_enter_promocode_done_btn=(Button)dialog.findViewById(R.id.dialog_enter_promocode_done_btn);
            ImageButton dialog_enter_promocode_cancel_btn=(ImageButton)dialog.findViewById(R.id.dialog_enter_promocode_cancel_btn);
            dialog_enter_promocode_done_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog_enter_promocode_cancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            window.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.translucent)));
            dialog.show();
        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder {
        public ViewHolder4(View itemView) {
            super(itemView);
        }
    }
}
