package com.flikster.MyBagActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.CheckoutActivity.MyBagContinueOnClickActivity;
import com.flikster.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 24-10-2017.
 */

public class MyBagActivity extends AppCompatActivity implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManager;
    MyBagAdapter myBagAdapter;
    ImageButton toolbar_frag_multiicons_back_navigation;
    Button activity_my_bag_bottom_continue_btn, backhomebtn;
    LinearLayout nodatalayout;
    ImageView notifcationimg;
    TextView nodataavailtxt;
    TextView toolbar_frag_multiicons_title;
    ApiInterface apiInterface;
    MyBagData.MyBagInnerData myBagInnerData;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bag);
        initializeViews();
        initializeRest();
        context=this;
        getCartData();
    }

    private void getCartData() {
        apiInterface = ApiClient.getClient("http://apiv3-es.flikster.com/cart/_search?pretty=true&sort=createdAt:desc&q=userId:"+getIntent().getStringExtra("userId")).create(ApiInterface.class);
        Call<MyBagData> call = apiInterface.getMyBagData("http://apiv3-es.flikster.com/cart/_search?pretty=true&sort=createdAt:desc&q=userId:"+getIntent().getStringExtra("userId"));
        call.enqueue(new Callback<MyBagData>() {
            @Override
            public void onResponse(Call<MyBagData> call, Response<MyBagData> response) {
                myBagInnerData=response.body().getHits();
                myBagAdapter = new MyBagAdapter(context,myBagInnerData);
                fragment_common_recyclerview_recycler.setAdapter(myBagAdapter);
            }

            @Override
            public void onFailure(Call<MyBagData> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }

    private void initializeRest() {
        fragment_common_recyclerview_recycler.setVisibility(View.VISIBLE);
        activity_my_bag_bottom_continue_btn.setVisibility(View.VISIBLE);
        nodatalayout.setVisibility(View.GONE);
        toolbar_frag_multiicons_title.setText("My Bag");
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
        notifcationimg.setBackgroundDrawable(getResources().getDrawable(R.drawable.store_bag));


        setAvailableText();

        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        activity_my_bag_bottom_continue_btn.setOnClickListener(this);
        backhomebtn.setOnClickListener(this);
    }

    private void setAvailableText() {
        try {
            String cartmptystr = "<font color=\"#030a0a\"><bold>"
                    + "Your bag is empty ! \n"
                    + "</bold></font>" + "<br/>" + "<br/>" + "Add items to it now";
            nodataavailtxt.setText(Html.fromHtml(cartmptystr));
        } catch (Exception e) {
            nodataavailtxt.setText("Your bag is empty !" + "\n" + "Add items to it now");
        }

        backhomebtn.setText("Shop Now");
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) findViewById(R.id.fragment_common_recyclerview_recycler);
        activity_my_bag_bottom_continue_btn = (Button) findViewById(R.id.activity_my_bag_bottom_continue_btn);
        toolbar_frag_multiicons_back_navigation = (ImageButton) findViewById(R.id.toolbar_frag_multiicons_back_navigation);

        nodatalayout = (LinearLayout) findViewById(R.id.nodatalayout);
        notifcationimg = (ImageView) findViewById(R.id.notifcationimg);
        nodataavailtxt = (TextView) findViewById(R.id.nodataavailtxt);
        toolbar_frag_multiicons_title = (TextView) findViewById(R.id.toolbar_frag_multiicons_title);
        backhomebtn = (Button) findViewById(R.id.backhomebtn);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
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
