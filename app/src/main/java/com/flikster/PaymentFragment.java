package com.flikster;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by abhishek on 25-10-2017.
 */

public class PaymentFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PaymentAdapter paymentAdapter;
    Button button;
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
        paymentAdapter=new  PaymentAdapter(getActivity());
        recyclerView.setAdapter(paymentAdapter);
        button.setOnClickListener(this);
    }

    private void initializeViews() {
        recyclerView=(RecyclerView)view.findViewById(R.id.fragment_checkout_recyclerview);
        button=(Button)view.findViewById(R.id.fragment_checkout_bottom_btn);
    }

    @Override
    public void onClick(View view) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.card_order_succesfully_placed);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        //window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
