package com.flikster.CheckoutActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flikster.CheckoutActivity.AddressFragment.AddressFragment;
import com.flikster.R;

/**
 * Created by abhishek on 24-10-2017.
 */

public class MyBagContinueOnClickActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton toolbar_frag_multiicons_back_navigation;
    FragmentManager fragmentManager;
    LinearLayout activity_mybag_continue_onclick_tabs_address_linear,activity_mybag_continue_onclick_tabs_checkout_linear
            ,activity_mybag_continue_onclick_tabs_payment_linear;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybag_continue_onclick);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initializeView();
        initializeRest();
        addressFragmentLaunch();
    }

    private void initializeRest() {
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        fragmentManager=getSupportFragmentManager();
        activity_mybag_continue_onclick_tabs_address_linear.setOnClickListener(this);
        activity_mybag_continue_onclick_tabs_checkout_linear.setOnClickListener(this);
        activity_mybag_continue_onclick_tabs_payment_linear.setOnClickListener(this);
    }

    private void initializeView() {
        activity_mybag_continue_onclick_tabs_address_linear=(LinearLayout)findViewById(R.id.activity_mybag_continue_onclick_tabs_address_linear);
        toolbar_frag_multiicons_back_navigation = (ImageButton) findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        activity_mybag_continue_onclick_tabs_checkout_linear=(LinearLayout)findViewById(R.id.activity_mybag_continue_onclick_tabs_checkout_linear);
        activity_mybag_continue_onclick_tabs_payment_linear=(LinearLayout)findViewById(R.id.activity_mybag_continue_onclick_tabs_payment_linear);
    }

    @Override
    public void onClick(View view) {
        /*if (view.getId() == R.id.activity_mybag_continue_onclick_toolbar_back_navigation_btn) {
            getFragmentManager().popBackStackImmediate();
        }
        else if(view.getId()==R.id.activity_mybag_continue_onclick_tabs_address_linear)
        {
            fragmentManager.beginTransaction()
                    .replace(R.id.activity_mybag_continue_onclick_container,new AddressFragment())
                    .commit();
        }
        else if(view.getId()==R.id.activity_mybag_continue_onclick_tabs_checkout_linear)
        {
            fragmentManager.beginTransaction()
                    .replace(R.id.activity_mybag_continue_onclick_container,new CheckoutFragment())
                    .commit();
        }
        else if(view.getId()==R.id.activity_mybag_continue_onclick_tabs_payment_linear)
        {
            fragmentManager.beginTransaction()
                    .replace(R.id.activity_mybag_continue_onclick_container,new PaymentFragment())
                    .commit();
        }*/
    }

    private void addressFragmentLaunch() {
        fragmentManager.beginTransaction()
                .replace(R.id.activity_mybag_continue_onclick_container,new AddressFragment())
                .commit();
    }
}