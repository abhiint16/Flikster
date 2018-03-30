package com.flikster.HomeActivity.CommonFragments.ProductFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductImagesAdapter;
import com.flikster.CheckoutActivity.MyBagContinueOnClickActivity;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.MyBagActivity.MyBagActivity;
import com.flikster.MyBagActivity.MyBagData;
import com.flikster.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 16-10-2017.
 */

public class ProductOnClick extends AppCompatActivity implements View.OnClickListener {
   // View view;
    Button buy, add;
    ImageButton  toolbar_back_navigation_btn;
    TextView toolbar_frag_title, product_price, product_size_small, product_size_med, product_size_large, product_size_extra, product_size_extra_extra;
    TextView product_title, product_quantity_minus_btn, product_quanitity_plus_btn, product_quantity_txt;
    TextView infotxt,product_expand_info,descrtxt,product_expand_description,dispatchtxt,product_expand_dispatch,policytxt,product_expand_policy;
    FragmentManager fragmentManager;
    TextView fragment_common_recyclerview_with_tv_title;
    RecyclerView fragment_common_recyclerview_with_tv_recycler;
    ProductImagesAdapter productImagesAdapter;
    CelebrityBioAdapterImagesViewHolder myCeleAdapter;
    RecyclerView fragment_product_details_recyclerview;
    RecyclerView.LayoutManager layoutManager;
    String productId;
    List<String> size = new ArrayList<>();
    String userId;
    String price,productDesc,productInfo;
    String profilePic;
    String productTitle;
    String productSlug;
    List<String> imageGallery;
    ApiInterface apiInterface;
    int sizeOfSize;
    String chosenSize = "";
    Boolean desc=false,info=false,policy=false,dispatch=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_product_details);
        getDataFromIntent();
        initializeViews();
        initializeRest();
    }

    private void getDataFromIntent() {
        this.productId = getIntent().getStringExtra("productId");
        this.size = getIntent().getStringArrayListExtra("size");
        //Log.e("chekc for null1000","chkc for null1000"+getIntent().getStringArrayListExtra("size"));
        /*if (getIntent().getStringArrayExtra("size").s != null) {
            this.size = getIntent().getStringArrayListExtra("size");
        } else {
            Log.e("chekc for null","chkc for null");
//            this.size = size;
        }*/

        this.userId = getIntent().getStringExtra("userId");
        this.productDesc = getIntent().getStringExtra("productDesc");
        this.productInfo = getIntent().getStringExtra("productInfo");
        this.price = getIntent().getStringExtra("price");
        this.profilePic = getIntent().getStringExtra("profilePic");
        this.productTitle = getIntent().getStringExtra("productTitle");
        this.productSlug = getIntent().getStringExtra("productSlug");
        this.imageGallery = getIntent().getStringArrayListExtra("imageGallery");
    }

    private void initializeRest() {
        toolbar_frag_title.setText("Product");
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        fragment_product_details_recyclerview.setLayoutManager(layoutManager);
        productImagesAdapter = new ProductImagesAdapter(this, fragmentManager, imageGallery);
        fragment_product_details_recyclerview.setAdapter(productImagesAdapter);
        product_title.setText(productTitle);
        product_price.setText("Rs. " + price + " /-");
        sizeOfSize = size.size();
        formatSize();
        descrtxt.setOnClickListener(this);
        infotxt.setOnClickListener(this);
        policytxt.setOnClickListener(this);
        dispatchtxt.setOnClickListener(this);
        toolbar_back_navigation_btn.setOnClickListener(this);
        add.setOnClickListener(this);
        buy.setOnClickListener(this);
        product_quantity_minus_btn.setOnClickListener(this);
        product_quanitity_plus_btn.setOnClickListener(this);
        product_size_small.setOnClickListener(this);
        product_size_med.setOnClickListener(this);
        product_size_large.setOnClickListener(this);
        product_size_extra.setOnClickListener(this);
        product_size_extra_extra.setOnClickListener(this);
    }

    private void formatSize() {
        if (sizeOfSize==0)
        {
            product_size_med.setVisibility(View.GONE);
            product_size_large.setVisibility(View.GONE);
            product_size_extra.setVisibility(View.GONE);
            product_size_extra_extra.setVisibility(View.GONE);
            product_size_small.setVisibility(View.GONE);
        }
        if (sizeOfSize == 1) {
            product_size_med.setVisibility(View.GONE);
            product_size_large.setVisibility(View.GONE);
            product_size_extra.setVisibility(View.GONE);
            product_size_extra_extra.setVisibility(View.GONE);
            product_size_small.setText(size.get(0));
        }
        if (sizeOfSize == 2) {
            product_size_large.setVisibility(View.GONE);
            product_size_extra.setVisibility(View.GONE);
            product_size_extra_extra.setVisibility(View.GONE);
            product_size_small.setText(size.get(0));
            product_size_med.setText(size.get(1));
        }
        if (sizeOfSize == 3) {
            product_size_extra.setVisibility(View.GONE);
            product_size_extra_extra.setVisibility(View.GONE);
            product_size_small.setText(size.get(0));
            product_size_med.setText(size.get(1));
            product_size_large.setText(size.get(2));
        }
        if (sizeOfSize == 4) {
            product_size_extra_extra.setVisibility(View.GONE);
            product_size_small.setText(size.get(0));
            product_size_med.setText(size.get(1));
            product_size_large.setText(size.get(2));
            product_size_extra.setText(size.get(3));
        }
        if (sizeOfSize == 5) {
            product_size_small.setText(size.get(0));
            product_size_med.setText(size.get(1));
            product_size_large.setText(size.get(2));
            product_size_extra.setText(size.get(3));
            product_size_extra_extra.setText(size.get(4));
        }
    }

    private void initializeViews() {
        toolbar_back_navigation_btn = (ImageButton) findViewById(R.id.toolbar_back_navigation_btn);
        toolbar_frag_title = (TextView) findViewById(R.id.toolbar_frag_title);
        product_expand_description=(TextView)findViewById(R.id.product_expand_description);
        product_expand_dispatch=(TextView)findViewById(R.id.product_expand_dispatch);
        product_expand_info=(TextView)findViewById(R.id.product_expand_info);
        product_expand_policy=(TextView)findViewById(R.id.product_expand_policy);
        dispatchtxt=(TextView)findViewById(R.id.dispatchtxt);
        infotxt=(TextView)findViewById(R.id.infotxt);
        policytxt=(TextView)findViewById(R.id.policytxt);
        descrtxt=(TextView)findViewById(R.id.descrtxt);
        fragment_product_details_recyclerview = (RecyclerView) findViewById(R.id.fragment_common_recyclerview_recycler);
        product_price = (TextView) findViewById(R.id.buy_click_product_price);
        product_title = (TextView) findViewById(R.id.buy_click_product_title);
        product_size_small = (TextView) findViewById(R.id.buy_click_product_size_small);
        product_size_med = (TextView) findViewById(R.id.buy_click_product_size_medium);
        product_size_large = (TextView) findViewById(R.id.buy_click_product_size_large);
        product_size_extra = (TextView) findViewById(R.id.buy_click_product_size_extra_large);
        product_size_extra_extra = (TextView) findViewById(R.id.buy_click_product_size_extra_extra_large);
        product_quantity_minus_btn = (TextView) findViewById(R.id.buy_click_product_quantity_minus_btn);
        product_quanitity_plus_btn = (TextView) findViewById(R.id.buy_click_product_quantity_plus_btn);
        product_quantity_txt = (TextView) findViewById(R.id.buy_click_product_quantity_no);
        add = (Button) findViewById(R.id.add_to_cart_btn);
        buy = (Button) findViewById(R.id.buy_now_btn);
        //toolbar_more_icon = (ImageButton) view.findViewById(R.id.toolbar_more_icon);
        fragmentManager = this.getSupportFragmentManager();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buy_now_btn) {
            if (chosenSize.trim().length() == 0 || chosenSize == null) {
                if (size.size() != 1) {
                    Toast.makeText(this, "choose size before buying", Toast.LENGTH_SHORT).show();
                    return;
                } else if (size.size() == 1) {
                    chosenSize = size.get(0);
                }
            }
            Intent intent = new Intent(this, MyBagContinueOnClickActivity.class);
            intent.putExtra("productId", productId);
            intent.putExtra("productSlug", productSlug);
            intent.putExtra("productTitle", productTitle);
            intent.putExtra("userId", userId);
            intent.putExtra("size", chosenSize);
            intent.putExtra("color", "RED");
            intent.putExtra("profilePic", profilePic);
            intent.putExtra("price", price);
            intent.putExtra("quantity", product_quantity_txt.getText().toString());
            startActivity(intent);
        } else if (view.getId() == R.id.add_to_cart_btn) {
            if (chosenSize.trim().length() == 0 || chosenSize == null) {
                if (size.size() != 1) {
                    Toast.makeText(this, "choose size before adding to cart", Toast.LENGTH_SHORT).show();
                    return;
                } else if (size.size() == 1) {
                    chosenSize = size.get(0);
                }
            }
            postRetrofitAddToCart();
        } else if (view.getId() == R.id.toolbar_back_navigation_btn) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.buy_click_product_quantity_minus_btn) {
            int i = Integer.parseInt(product_quantity_txt.getText().toString());
            if (i > 1)
                product_quantity_txt.setText("" + (i - 1));
        } else if (view.getId() == R.id.buy_click_product_quantity_plus_btn) {
            int i = Integer.parseInt(product_quantity_txt.getText().toString());
            product_quantity_txt.setText("" + (i + 1));
        } else if (view.getId() == R.id.buy_click_product_size_small) {
            product_size_small.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            product_size_med.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_large.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_extra.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_extra_extra.setBackgroundColor(getResources().getColor(R.color.white));
            chosenSize = size.get(0);
        } else if (view.getId() == R.id.buy_click_product_size_medium) {
            product_size_small.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_med.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            product_size_large.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_extra.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_extra_extra.setBackgroundColor(getResources().getColor(R.color.white));
            this.chosenSize = size.get(1);
        } else if (view.getId() == R.id.buy_click_product_size_large) {
            product_size_small.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_med.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_large.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            product_size_extra.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_extra_extra.setBackgroundColor(getResources().getColor(R.color.white));
            chosenSize = size.get(2);
        } else if (view.getId() == R.id.buy_click_product_size_extra_large) {
            product_size_small.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_med.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_large.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_extra.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            product_size_extra_extra.setBackgroundColor(getResources().getColor(R.color.white));
            chosenSize = size.get(3);
        } else if (view.getId() == R.id.buy_click_product_size_extra_extra_large) {
            product_size_small.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_med.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_large.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_extra.setBackgroundColor(getResources().getColor(R.color.white));
            product_size_extra_extra.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            chosenSize = size.get(4);
        }
        else if (view.getId()==R.id.descrtxt)
        {
            if (desc==false)
            {
                desc=true;
                descrtxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.product_down_arrow,0,0,0);
                product_expand_description.setVisibility(View.VISIBLE);
                product_expand_description.setText(productInfo);
            }
            else if (desc)
            {
                desc=false;
                descrtxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.product_right_arrow,0,0,0);
                product_expand_description.setVisibility(View.GONE);
            }
        }
        else if (view.getId()==R.id.infotxt)
        {
            if (info==false)
            {
                info=true;
                infotxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.product_down_arrow,0,0,0);
                product_expand_info.setVisibility(View.VISIBLE);
                product_expand_info.setText(productInfo);
            }
            else if (info)
            {
                info=false;
                infotxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.product_right_arrow,0,0,0);
                product_expand_info.setVisibility(View.GONE);
            }
        }
        else if (view.getId()==R.id.policytxt)
        {
            if (policy==false)
            {
                policy=true;
                policytxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.product_down_arrow,0,0,0);
                product_expand_policy.setVisibility(View.VISIBLE);
            }
            else if (policy)
            {
                policy=false;
                policytxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.product_right_arrow,0,0,0);
                product_expand_policy.setVisibility(View.GONE);
            }
        }
        else if (view.getId()==R.id.dispatchtxt)
        {
            if (dispatch==false)
            {
                dispatch=true;
                dispatchtxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.product_down_arrow,0,0,0);
                product_expand_dispatch.setVisibility(View.VISIBLE);
            }
            else if (dispatch)
            {
                dispatch=false;
                dispatchtxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.product_right_arrow,0,0,0);
                product_expand_dispatch.setVisibility(View.GONE);
            }
        }
    }

    private void postRetrofitAddToCart() {
        Toast.makeText(this, "Adding to Cart.....wait", Toast.LENGTH_SHORT).show();
        ProductDetailsDataToSend productDetailsDataToSend = new ProductDetailsDataToSend(userId, productId, chosenSize, "RED", new ProductDetailsDataToSend.InnerProductData(price, productId, profilePic, productTitle, productSlug,
                Integer.parseInt(product_quantity_txt.getText().toString()), "RED", chosenSize));
        apiInterface = ApiClient.getClient("http://apiservice.flikster.com/v3/cart-ms/createCart/").create(ApiInterface.class);
        Call<ProductDetailsDataToSend> call = apiInterface.postSendToCartData(productDetailsDataToSend);
        call.enqueue(new Callback<ProductDetailsDataToSend>() {
            @Override
            public void onResponse(Call<ProductDetailsDataToSend> call, Response<ProductDetailsDataToSend> response) {
                Toast.makeText(ProductOnClick.this, "Added to Cart.....fetching details", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProductOnClick.this, MyBagActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ProductDetailsDataToSend> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }

   /* public void getProductData(String productId, List<String> size, String userId, String price, String profilePic,
                               String productTitle, String productSlug, List<String> imageGallery) {
        this.productId = productId;
        if (size != null) {
            this.size = size;
        } else {
//            this.size = size;
        }

        this.userId = userId;
        this.price = price;
        this.profilePic = profilePic;
        this.productTitle = productTitle;
        this.productSlug = productSlug;
        this.imageGallery = imageGallery;
    }*/

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
