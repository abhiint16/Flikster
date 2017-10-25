package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by abhishek on 24-10-2017.
 */

public class MyBagContinueOnClickFragment extends Fragment implements View.OnClickListener {
    View view;
    ImageButton fragment_mybag_continue_onclick_toolbar_back_navigation;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_mybag_continue_onclick,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        addressFragmentLaunch();
        initializeView();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        fragment_mybag_continue_onclick_toolbar_back_navigation.setOnClickListener(this);
    }

    private void initializeView() {
        fragment_mybag_continue_onclick_toolbar_back_navigation=(ImageButton)view.findViewById(R.id.fragment_mybag_continue_onclick_toolbar_back_navigation);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fragment_mybag_continue_onclick_toolbar_back_navigation)
        {
            getFragmentManager().popBackStackImmediate();
        }
    }

    private void addressFragmentLaunch()
    {
        getFragmentManager().beginTransaction()
                .replace(R.id.payment_container,new PaymentAddressFragment())
                .addToBackStack("")
                .commit();
    }
}
