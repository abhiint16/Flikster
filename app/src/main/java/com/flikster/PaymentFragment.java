package com.flikster;

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

/**
 * Created by abhishek on 25-10-2017.
 */

public class PaymentFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PaymentAdapter paymentAdapter;
    Button button;
    ImageButton backButton,addressTabIcon,checkoutTabIcon,paymentTabIcon;
    TextView titleToolbar,addressTabText,checkoutTabText,paymentTabText;
    Toolbar toolbar;
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
        recyclerView.setLayoutManager(layoutManager);
        paymentAdapter=new  PaymentAdapter(getActivity());
        recyclerView.setAdapter(paymentAdapter);
        button.setOnClickListener(this);
        backButton.setOnClickListener(this);
        titleToolbar.setText("Payment Mode");
    }

    private void initializeViews() {
        recyclerView=(RecyclerView)view.findViewById(R.id.fragment_checkout_recyclerview);
        button=(Button)view.findViewById(R.id.fragment_checkout_bottom_btn);
        toolbar=(Toolbar)getActivity().findViewById(R.id.activity_mybag_continue_onclick_toolbar);
        backButton=(ImageButton)toolbar.findViewById(R.id.activity_mybag_continue_onclick_toolbar_back_navigation_btn);
        titleToolbar=(TextView)toolbar.findViewById(R.id.activity_mybag_continue_onclick_toolbar_title);
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
        else if(view.getId()==R.id.activity_mybag_continue_onclick_toolbar_back_navigation_btn)
        {
            getFragmentManager().popBackStackImmediate();
        }
    }
}
