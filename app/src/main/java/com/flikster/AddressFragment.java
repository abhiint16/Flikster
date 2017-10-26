package com.flikster;

import android.content.Intent;
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

/**
 * Created by abhishek on 24-10-2017.
 */

public class AddressFragment extends Fragment implements View.OnClickListener {
    View view;
    Button fragment_address_bottom_btn;
    Toolbar toolbar;
    ImageButton imageButton;
    TextView titleToolbar;
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
        imageButton.setOnClickListener(this);
        titleToolbar.setText("Enter Address");
    }

    private void initializeView() {
        fragment_address_bottom_btn=(Button)view.findViewById(R.id.fragment_address_bottom_btn);
        toolbar=(Toolbar)getActivity().findViewById(R.id.activity_mybag_continue_onclick_toolbar);
        imageButton=(ImageButton)toolbar.findViewById(R.id.activity_mybag_continue_onclick_toolbar_back_navigation_btn);
        titleToolbar=(TextView)toolbar.findViewById(R.id.activity_mybag_continue_onclick_toolbar_title);
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
