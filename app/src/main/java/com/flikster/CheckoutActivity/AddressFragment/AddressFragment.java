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
    Button fragment_address_bottom_btn;
    Toolbar toolbar_frag_multiicons_toolbar;
    ImageButton toolbar_frag_multiicons_back_navigation,addressTabIcon,checkoutTabIcon,paymentTabIcon;
    TextView toolbar_frag_multiicons_title,addressTabText,checkoutTabText,paymentTabText;
    CheckoutUserData checkoutUserData;
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
    }


    private void initializeView() {
        fragment_address_bottom_btn=(Button)view.findViewById(R.id.fragment_address_bottom_btn);
        toolbar_frag_multiicons_back_navigation
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
            checkForValidInput();
            /*getFragmentManager().beginTransaction()
                    .replace(R.id.activity_mybag_continue_onclick_container,new CheckoutFragment())
                    .addToBackStack("")
                    .commit();*/
        }
        else if(view.getId()==R.id.toolbar_frag_multiicons_back_navigation)
        {
            Intent intent=new Intent(getActivity(),MyBagActivity.class);
            startActivity(intent);
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
}
