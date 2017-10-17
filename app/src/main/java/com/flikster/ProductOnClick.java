package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by abhishek on 16-10-2017.
 */

public class ProductOnClick extends Fragment implements View.OnClickListener {
    View view;
    Button buy,add;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.product_onclick,container,false);
        add=(Button)view.findViewById(R.id.add_to_cart_btn);
        buy=(Button)view.findViewById(R.id.buy_now_btn);
        fragmentManager=getActivity().getSupportFragmentManager();
        add.setOnClickListener(this);
        buy.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buy_now_btn)
        {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container,new Checkout())
                    .addToBackStack("")
                    .commit();
        }
        else if(view.getId()==R.id.add_to_cart_btn)
        {

        }
    }
}
