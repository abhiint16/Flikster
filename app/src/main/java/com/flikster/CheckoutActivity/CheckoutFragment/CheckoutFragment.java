package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by abhishek on 25-10-2017.
 */

public class CheckoutFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CheckoutAdapter checkoutAdapter;
    Toolbar toolbar;
    ImageButton backButton,addressTabIcon,checkoutTabIcon,paymentTabIcon;
    TextView titleToolbar,addressTabText,checkoutTabText,paymentTabText;
    Button fragment_checkout_bottom_btn;
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
        checkoutAdapter=new  CheckoutAdapter(getFragmentManager(),getActivity());
        recyclerView.setAdapter(checkoutAdapter);
        backButton.setOnClickListener(this);
        fragment_checkout_bottom_btn.setOnClickListener(this);
        titleToolbar.setText("Order Details");
    }

    private void initializeViews() {
        recyclerView=(RecyclerView)view.findViewById(R.id.fragment_checkout_recyclerview);
        toolbar=(Toolbar)getActivity().findViewById(R.id.activity_mybag_continue_onclick_toolbar);
        backButton=(ImageButton)toolbar.findViewById(R.id.activity_mybag_continue_onclick_toolbar_back_navigation_btn);
        titleToolbar=(TextView)toolbar.findViewById(R.id.activity_mybag_continue_onclick_toolbar_title);
        fragment_checkout_bottom_btn=(Button)view.findViewById(R.id.fragment_checkout_bottom_btn);
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
        checkoutTabIcon.setImageResource(R.drawable.checkout_pink);
        checkoutTabText.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
        paymentTabIcon.setImageResource(R.drawable.payment);
        paymentTabText.setTextColor(getActivity().getResources().getColor(R.color.black));
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.activity_mybag_continue_onclick_toolbar_back_navigation_btn)
        {
            getFragmentManager().popBackStackImmediate();
        }
        else if(view.getId()==R.id.fragment_checkout_bottom_btn)
        {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_mybag_continue_onclick_container,new PaymentFragment())
                    .addToBackStack("")
                    .commit();
        }
    }
}
