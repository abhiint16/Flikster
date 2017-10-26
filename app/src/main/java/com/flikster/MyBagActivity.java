package com.flikster;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by abhishek on 24-10-2017.
 */

public class MyBagActivity extends AppCompatActivity implements View.OnClickListener {
    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyBagAdapter myBagAdapter;
    ImageButton activity_my_bag_toolbar_back_navigation_btn;
    Button activity_my_bag_bottom_continue_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bag);
        initializeViews();
        initializeRest();
    }

    private void initializeRest() {
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        myBagAdapter=new MyBagAdapter();
        recyclerView.setAdapter(myBagAdapter);
        activity_my_bag_toolbar_back_navigation_btn.setOnClickListener(this);
        activity_my_bag_bottom_continue_btn.setOnClickListener(this);
    }

    private void initializeViews() {
        recyclerView=(RecyclerView)findViewById(R.id.activity_my_bag_recycler_view);
        activity_my_bag_bottom_continue_btn=(Button)findViewById(R.id.activity_my_bag_bottom_continue_btn);
        activity_my_bag_toolbar_back_navigation_btn=(ImageButton)findViewById(R.id.activity_my_bag_toolbar_back_navigation_btn);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.activity_my_bag_toolbar_back_navigation_btn)
        {
            Intent intent=new Intent(this,HomeActivity.class);
            intent.putExtra("MyBag","MyBag");
            startActivity(intent);
        }
        else if(view.getId()==R.id.activity_my_bag_bottom_continue_btn)
        {
            Intent intent=new Intent(this,MyBagContinueOnClickActivity.class);
            startActivity(intent);
        }
    }
}
