package com.flikster;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by abhishek on 24-10-2017.
 */

public class AddressFragment extends Fragment implements View.OnClickListener {
    View view;
    Button fragment_address_bottom_btn;
    Toolbar toolbar;
    ImageButton backButton,addressTabIcon,checkoutTabIcon,paymentTabIcon;
    TextView titleToolbar,addressTabText,checkoutTabText,paymentTabText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_address,container,false);
        initializeView();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        fragment_address_bottom_btn.setOnClickListener(this);
        backButton.setOnClickListener(this);
        titleToolbar.setText("Enter Address");
    }

    private void initializeView() {
        fragment_address_bottom_btn=(Button)view.findViewById(R.id.fragment_address_bottom_btn);
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
        addressTabIcon.setImageResource(R.drawable.address_pink);
        addressTabText.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
        checkoutTabIcon.setImageResource(R.drawable.checkout);
        checkoutTabText.setTextColor(getActivity().getResources().getColor(R.color.black));
        paymentTabIcon.setImageResource(R.drawable.payment);
        paymentTabText.setTextColor(getActivity().getResources().getColor(R.color.black));
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fragment_address_bottom_btn)
        {
            getFragmentManager().beginTransaction()
                    .replace(R.id.activity_mybag_continue_onclick_container,new CheckoutFragment())
                    .addToBackStack("")
                    .commit();
        }
        else if(view.getId()==R.id.activity_mybag_continue_onclick_toolbar_back_navigation_btn)
        {
            Intent intent=new Intent(getActivity(),MyBagActivity.class);
            startActivity(intent);
        }
    }
}
