package com.flikster;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by abhishek on 24-10-2017.
 */

public class MyBagActivity extends AppCompatActivity implements View.OnClickListener {
    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyBagAdapter myBagAdapter;
    ImageButton activity_my_bag_toolbar_back_navigation_btn;
    Button activity_my_bag_bottom_continue_btn, backhomebtn;
    LinearLayout nodatalayout;
    ImageView notifcationimg;
    TextView nodataavailtxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bag);
        initializeViews();
        initializeRest();
    }

    private void initializeRest() {
        recyclerView.setVisibility(View.VISIBLE);
        activity_my_bag_bottom_continue_btn.setVisibility(View.VISIBLE);
        nodatalayout.setVisibility(View.GONE);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        myBagAdapter = new MyBagAdapter();
        recyclerView.setAdapter(myBagAdapter);

        notifcationimg.setBackgroundDrawable(getResources().getDrawable(R.drawable.store_bag));

        String cartmptystr = "<font color=\"#030a0a\"><bold>"
                        + "Your bag is empty ! \n"
                        + "</bold></font>" + "<br/>" + "<br/>" + "Add items to it now";
        nodataavailtxt.setText(Html.fromHtml(cartmptystr));
        backhomebtn.setText("Shop Now");

        activity_my_bag_toolbar_back_navigation_btn.setOnClickListener(this);
        activity_my_bag_bottom_continue_btn.setOnClickListener(this);
        backhomebtn.setOnClickListener(this);
    }

    private void initializeViews() {
        recyclerView = (RecyclerView) findViewById(R.id.activity_my_bag_recycler_view);
        activity_my_bag_bottom_continue_btn = (Button) findViewById(R.id.activity_my_bag_bottom_continue_btn);
        activity_my_bag_toolbar_back_navigation_btn = (ImageButton) findViewById(R.id.activity_my_bag_toolbar_back_navigation_btn);

        nodatalayout = (LinearLayout) findViewById(R.id.nodatalayout);
        notifcationimg = (ImageView) findViewById(R.id.notifcationimg);
        nodataavailtxt = (TextView) findViewById(R.id.nodataavailtxt);
        backhomebtn = (Button) findViewById(R.id.backhomebtn);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activity_my_bag_toolbar_back_navigation_btn) {
            backtoHome();
        } else if (view.getId() == R.id.backhomebtn) {
            backtoHome();
        } else if (view.getId() == R.id.activity_my_bag_bottom_continue_btn) {
            Intent intent = new Intent(this, MyBagContinueOnClickActivity.class);
            startActivity(intent);
        }
    }

    private void backtoHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("MyBag", "MyBag");
        startActivity(intent);
    }
}
