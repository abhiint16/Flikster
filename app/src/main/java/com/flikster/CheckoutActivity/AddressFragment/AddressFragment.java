package com.flikster.CheckoutActivity.AddressFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.CheckoutActivity.CheckoutFragment.CheckoutFragment;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.MyBagActivity.MyBagActivity;
import com.flikster.R;

import java.util.List;

/**
 * Created by abhishek on 24-10-2017.
 */

public class AddressFragment extends Fragment implements View.OnClickListener {
    View view;
    Button fragment_address_bottom_btn,address_gps_btn;
    Toolbar toolbar_frag_multiicons_toolbar;
    ImageButton toolbar_frag_multiicons_back_navigation,addressTabIcon,checkoutTabIcon/*,paymentTabIcon*/;
    TextView toolbar_frag_multiicons_title,addressTabText,checkoutTabText/*,paymentTabText*/;
    CheckoutUserData checkoutUserData;
    String name="",mobile="",address="",city="",pin="",state="",landmark="",additionalMobile="";
    EditText address_fragment_name,address_fragment_mobileno,address_fragment_address,address_fragment_city
            ,address_fragment_pin,address_fragment_state,address_fragment_landmark,address_fragment_additionmobile;
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
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        toolbar_frag_multiicons_title.setText("Enter Address");
        address_gps_btn.setOnClickListener(this);
        setTextsinFields();
    }

    private void setTextsinFields() {
        address_fragment_name.setText(name);
        address_fragment_mobileno.setText(mobile);
        address_fragment_address.setText(address);
        address_fragment_city.setText(city);
        address_fragment_pin.setText(pin);
        address_fragment_state.setText(state);
        address_fragment_landmark.setText(landmark);
        address_fragment_additionmobile.setText(additionalMobile);
    }


    private void initializeView() {
        address_gps_btn=(Button)view.findViewById(R.id.address_gps_btn);
        fragment_address_bottom_btn=(Button)view.findViewById(R.id.fragment_address_bottom_btn);
        toolbar_frag_multiicons_toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_back_navigation=(ImageButton)toolbar_frag_multiicons_toolbar.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_title=(TextView)toolbar_frag_multiicons_toolbar.findViewById(R.id.toolbar_frag_multiicons_title);
        addressTabIcon=(ImageButton)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_address_linear_imgbtn);
        checkoutTabIcon=(ImageButton)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_checkout_linear_imgbtn);
        //paymentTabIcon=(ImageButton)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_payment_linear_imgbtn);
        addressTabText=(TextView)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_address_linear_name);
        checkoutTabText=(TextView)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_checkout_linear_name);
        //paymentTabText=(TextView)getActivity().findViewById(R.id.activity_mybag_continue_onclick_tabs_payment_linear_name);
        address_fragment_name=(EditText)view.findViewById(R.id.address_fragment_name);
        address_fragment_mobileno=(EditText)view.findViewById(R.id.address_fragment_mobileno);
        address_fragment_address=(EditText)view.findViewById(R.id.address_fragment_address);
        address_fragment_city=(EditText)view.findViewById(R.id.address_fragment_city);
        address_fragment_pin=(EditText)view.findViewById(R.id.address_fragment_pin);
        address_fragment_state=(EditText)view.findViewById(R.id.address_fragment_state);
        address_fragment_landmark=(EditText)view.findViewById(R.id.address_fragment_landmark);
        address_fragment_additionmobile=(EditText)view.findViewById(R.id.address_fragment_additionmobile);
    }

    @Override
    public void onResume() {
        super.onResume();
        addressTabIcon.setImageResource(R.drawable.address_pink);
        addressTabText.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
        checkoutTabIcon.setImageResource(R.drawable.checkout);
        checkoutTabText.setTextColor(getActivity().getResources().getColor(R.color.black));
        /*paymentTabIcon.setImageResource(R.drawable.payment);
        paymentTabText.setTextColor(getActivity().getResources().getColor(R.color.black));*/
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fragment_address_bottom_btn)
        {
            checkForValidInput();
            /*getFragmentManager().beginTransaction()
                    .replace(R.id.activity_mybag_continue_onclick_container,new CheckoutFragment())
                    .addToBackStack("")
                    .commit();*/
        }
        else if(view.getId()==R.id.toolbar_frag_multiicons_back_navigation)
        {
            Intent intent=new Intent(getActivity(),MyBagActivity.class);
            intent.putExtra("userId",getActivity().getIntent().getStringExtra("userId"));
            startActivity(intent);
        }
        else if (view.getId()==R.id.address_gps_btn)
        {
            Toast.makeText(getActivity(),"Coming soon",Toast.LENGTH_LONG).show();
        }
    }

    private void checkForValidInput() {
        if (address_fragment_name.getText().toString().trim().length()==0)
        {
            Toast.makeText(getActivity(), "name can't be empty", Toast.LENGTH_LONG).show();
            address_fragment_name.setError("write something");
            return;
        }
        if (address_fragment_mobileno.getText().toString().trim().length()==0)
        {
            Toast.makeText(getActivity(), "mobileNo can't be empty", Toast.LENGTH_LONG).show();
            address_fragment_mobileno.setError("write something");
            return;
        }
        if (address_fragment_address.getText().toString().trim().length()==0)
        {
            Toast.makeText(getActivity(), "address can't be empty", Toast.LENGTH_LONG).show();
            address_fragment_address.setError("write something");
            return;
        }
        if (address_fragment_city.getText().toString().trim().length()==0)
        {
            Toast.makeText(getActivity(), "city can't be empty", Toast.LENGTH_LONG).show();
            address_fragment_city.setError("write something");
            return;
        }
        if (address_fragment_state.getText().toString().trim().length()==0)
        {
            Toast.makeText(getActivity(), "state can't be empty", Toast.LENGTH_LONG).show();
            address_fragment_state.setError("write something");
            return;
        }
        if (address_fragment_pin.getText().toString().trim().length()==0)
        {
            Toast.makeText(getActivity(), "pincode can't be empty", Toast.LENGTH_LONG).show();
            address_fragment_pin.setError("write something");
            return;
        }
        sendDataToPayment();
    }

    private void sendDataToPayment() {
        checkoutUserData.userInput(address_fragment_name.getText().toString(),
                address_fragment_mobileno.getText().toString(),
                address_fragment_address.getText().toString(),
                address_fragment_city.getText().toString(),
                address_fragment_pin.getText().toString(),
                address_fragment_state.getText().toString(),
                address_fragment_landmark.getText().toString(),
                address_fragment_additionmobile.getText().toString(),
                new CheckoutFragment());
    }

    public interface CheckoutUserData {
        void userInput(String name, String mobileNo,String Address,String city,String PinCode,String state,String landmark,String additionMobile,Fragment fragment);

/*
        void galleryCardOnClick(List<String> galleryImgLinks, String name, String profilePic, String type, String title, Fragment fragment);

        void newsCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle, String description,Fragment fragment,String contentType);

        void videoCardOnClick(String profilePic, String title, String type, String bannerImg, String headertitle, String description,Fragment fragment,String contentType);
*/
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        checkoutUserData = (CheckoutUserData) activity;
    }

    public void checkoutToAddressData(String name, String mobileNo,String address,String city,
                                      String pinCode,String state,String landmark,String additionMobile)
    {
        this.name=name;
        this.mobile=mobileNo;
        this.address=address;
        this.city=city;
        this.state=state;
        this.pin=pinCode;
        this.landmark=landmark;
        this.additionalMobile=additionMobile;
    }

}
