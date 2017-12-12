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
    String name,mobile,address,city,pin,state,landmark,additionalMobile;
    String productId;String productSlug;String productTitle;String userId;String size;
    String color;String profilePic;String price;
    int quantity;
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
        checkoutAdapter=new  CheckoutAdapter(getFragmentManager(),getActivity(),name,address,city,state,pin,mobile,landmark,additionalMobile,
                productId,productSlug,productTitle,userId,size,color,profilePic,price,quantity);
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

    }

    private void hitCreateUserApi() {
    }

    public void userData(String name,String mobile,String address,String city,String pin,String state,String landmark,
                         String additionalMobile)
    {
        this.name=name;
        this.mobile=mobile;
        this.address=address;
        this.city=city;
        this.pin=pin;
        this.state=state;
        this.landmark=landmark;
        this.additionalMobile=additionalMobile;
    }

    public void productData(String productId,String productSlug,String productTitle,String userId,String size,
                            String color,String profilePic,String price,int quantity)
    {
        this.productId=productId;
        this.productSlug=productSlug;
        this.productTitle=productTitle;
        this.userId=userId;
        this.size=size;
        this.color=color;
        this.profilePic=profilePic;
        this.price=price;
        this.quantity=quantity;
    }

}
