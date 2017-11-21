package com.flikster.CheckoutActivity.CheckoutFragment;

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

import com.flikster.CheckoutActivity.PaymentFragment.PaymentFragment;
import com.flikster.R;

/**
 * Created by abhishek on 25-10-2017.
 */

public class CheckoutFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManager;
    CheckoutAdapter checkoutAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    ImageButton toolbar_frag_multiicons_back_navigation,addressTabIcon,checkoutTabIcon,paymentTabIcon;
    TextView toolbar_frag_multiicons_title,addressTabText,checkoutTabText,paymentTabText;
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
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
        checkoutAdapter=new  CheckoutAdapter(getFragmentManager(),getActivity());
        fragment_common_recyclerview_recycler.setAdapter(checkoutAdapter);
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        fragment_checkout_bottom_btn.setOnClickListener(this);
        toolbar_frag_multiicons_title.setText("Order Details");
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler=(RecyclerView)view.findViewById(R.id.fragment_common_recyclerview_recycler);
        toolbar_frag_multiicons_toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_back_navigation=(ImageButton)toolbar_frag_multiicons_toolbar.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_title=(TextView)toolbar_frag_multiicons_toolbar.findViewById(R.id.toolbar_frag_multiicons_title);
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
        if(view.getId()==R.id.toolbar_frag_multiicons_back_navigation)
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

    public void userData(String name,String mobile,String address,String city,String pin,String state,String landmark,
                         String additionalMobile)
    {

    }

}
