package com.flikster.CheckoutActivity.PaymentFragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;

/**
 * Created by abhishek on 25-10-2017.
 */

public class PaymentFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManager;
    PaymentAdapter paymentAdapter;
    Button button;
    ImageButton toolbar_frag_multiicons_back_navigation,addressTabIcon,checkoutTabIcon,paymentTabIcon;
    TextView toolbar_frag_multiicons_title,addressTabText,checkoutTabText,paymentTabText;
    Toolbar toolbar_frag_multiicons_toolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_checkout,container,false);
        initializeViews();
        initializeRest();
        return view;
    }
    private void initializeRest() {
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
        paymentAdapter=new  PaymentAdapter(getActivity());
        fragment_common_recyclerview_recycler.setAdapter(paymentAdapter);
        button.setOnClickListener(this);
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        toolbar_frag_multiicons_title.setText("Payment Mode");
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler=(RecyclerView)view.findViewById(R.id.fragment_common_recyclerview_recycler);
        button=(Button)view.findViewById(R.id.fragment_checkout_bottom_btn);
        toolbar_frag_multiicons_toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_back_navigation=(ImageButton)toolbar_frag_multiicons_toolbar.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_title=(TextView)toolbar_frag_multiicons_toolbar.findViewById(R.id.toolbar_frag_multiicons_title);
        addressTabIcon=(ImageButton)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_address_linear_imgbtn);
        checkoutTabIcon=(ImageButton)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_checkout_linear_imgbtn);
        paymentTabIcon=(ImageButton)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_payment_linear_imgbtn);
        addressTabText=(TextView)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_address_linear_name);
        checkoutTabText=(TextView)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_checkout_linear_name);
        paymentTabText=(TextView)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_payment_linear_name);
    }

    @Override
    public void onResume() {
        super.onResume();
        addressTabIcon.setImageResource(R.drawable.address);
        addressTabText.setTextColor(getActivity().getResources().getColor(R.color.black));
        checkoutTabIcon.setImageResource(R.drawable.checkout);
        checkoutTabText.setTextColor(getActivity().getResources().getColor(R.color.black));
        paymentTabIcon.setImageResource(R.drawable.payment_pink);
        paymentTabText.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fragment_checkout_bottom_btn)
        {
            final Dialog dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_order_succesfully_placed);
            final Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            Button btn=(Button)dialog.findViewById(R.id.card_order_successfully_placed_btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(),HomeActivity.class);
                    startActivity(intent);
                }
            });
            //window.set
            window.setBackgroundDrawable(new ColorDrawable(getActivity().getResources().getColor(R.color.translucent)));
            dialog.show();
        }
        else if(view.getId()==R.id.toolbar_frag_multiicons_back_navigation)
        {
            getFragmentManager().popBackStackImmediate();
        }
    }
}
