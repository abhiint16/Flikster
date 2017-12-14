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
import com.flikster.MyBagActivity.MyBagActivity;
import com.flikster.MyBagActivity.MyBagData;
import com.flikster.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 16-10-2017.
 */

public class ProductOnClick extends Fragment implements View.OnClickListener {
    View view;
    Button buy, add;
    ImageButton toolbar_more_icon,toolbar_back_navigation_btn;
    TextView toolbar_frag_title,product_price,product_size_small,product_size_med,product_size_large,product_size_extra,product_size_extra_extra;
    TextView product_title,product_quantity_minus_btn,product_quanitity_plus_btn,product_quantity_txt;
    FragmentManager fragmentManager;
    TextView fragment_common_recyclerview_with_tv_title;
    RecyclerView fragment_common_recyclerview_with_tv_recycler;
    ProductImagesAdapter productImagesAdapter;
    CelebrityBioAdapterImagesViewHolder myCeleAdapter;
    RecyclerView fragment_product_details_recyclerview;
    RecyclerView.LayoutManager layoutManager;
    String productId;
    List<String> size;
    String userId;
    String price;
    String profilePic;
    String productTitle;
    String productSlug;
    List<String> imageGallery;
    ApiInterface apiInterface;
    int sizeOfSize;
    String chosenSize="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_details, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        toolbar_frag_title.setText("Product");
        fragment_common_recyclerview_with_tv_title.setText("Recommended Product");
        toolbar_more_icon.setVisibility(View.VISIBLE);
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        fragment_product_details_recyclerview.setLayoutManager(layoutManager);
        productImagesAdapter = new ProductImagesAdapter(getActivity(), fragmentManager,imageGallery);
        fragment_product_details_recyclerview.setAdapter(productImagesAdapter);
        product_title.setText(productTitle);
        product_price.setText("Rs. "+price+" /-");
        sizeOfSize=size.size();
        formatSize();
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
        if (sizeOfSize==1)
        {
            product_size_med.setVisibility(View.GONE);
            product_size_large.setVisibility(View.GONE);
            product_size_extra.setVisibility(View.GONE);
            product_size_extra_extra.setVisibility(View.GONE);
            product_size_small.setText(size.get(0));
        }
        if (sizeOfSize==2)
        {
            product_size_large.setVisibility(View.GONE);
            product_size_extra.setVisibility(View.GONE);
            product_size_extra_extra.setVisibility(View.GONE);
            product_size_small.setText(size.get(0));
            product_size_med.setText(size.get(1));
        }
        if (sizeOfSize==3)
        {
            product_size_extra.setVisibility(View.GONE);
            product_size_extra_extra.setVisibility(View.GONE);
            product_size_small.setText(size.get(0));
            product_size_med.setText(size.get(1));
            product_size_large.setText(size.get(2));
        }
        if (sizeOfSize==4)
        {
            product_size_extra_extra.setVisibility(View.GONE);
            product_size_small.setText(size.get(0));
            product_size_med.setText(size.get(1));
            product_size_large.setText(size.get(2));
            product_size_extra.setText(size.get(3));
        }
        if (sizeOfSize==5)
        {
            product_size_small.setText(size.get(0));
            product_size_med.setText(size.get(1));
            product_size_large.setText(size.get(2));
            product_size_extra.setText(size.get(3));
            product_size_extra_extra.setText(size.get(4));
        }
    }

    private void initializeViews() {
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
        toolbar_frag_title = (TextView) view.findViewById(R.id.toolbar_frag_title);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        fragment_product_details_recyclerview = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        product_price=(TextView)view.findViewById(R.id.buy_click_product_price);
        product_title=(TextView)view.findViewById(R.id.buy_click_product_title);
        product_size_small=(TextView)view.findViewById(R.id.buy_click_product_size_small);
        product_size_med=(TextView)view.findViewById(R.id.buy_click_product_size_medium);
        product_size_large=(TextView)view.findViewById(R.id.buy_click_product_size_large);
        product_size_extra=(TextView)view.findViewById(R.id.buy_click_product_size_extra_large);
        product_size_extra_extra=(TextView)view.findViewById(R.id.buy_click_product_size_extra_extra_large);
        product_quantity_minus_btn=(TextView)view.findViewById(R.id.buy_click_product_quantity_minus_btn);
        product_quanitity_plus_btn=(TextView)view.findViewById(R.id.buy_click_product_quantity_plus_btn);
        product_quantity_txt=(TextView)view.findViewById(R.id.buy_click_product_quantity_no);
        add = (Button) view.findViewById(R.id.add_to_cart_btn);
        buy = (Button) view.findViewById(R.id.buy_now_btn);
        toolbar_more_icon = (ImageButton) view.findViewById(R.id.toolbar_more_icon);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buy_now_btn) {
            if (chosenSize.trim().length()==0||chosenSize==null)
            {
                if(size.size()!=1)
                {
                    Toast.makeText(getActivity(), "choose size before buying", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (size.size()==1)
                {
                    chosenSize=size.get(0);
                }
            }
            Intent intent=new Intent(getActivity(),MyBagContinueOnClickActivity.class);
            intent.putExtra("productId",productId);
            intent.putExtra("productSlug",productSlug);
            intent.putExtra("productTitle",productTitle);
            intent.putExtra("userId",userId);
            intent.putExtra("size",chosenSize);
            intent.putExtra("color","RED");
            intent.putExtra("profilePic",profilePic);
            intent.putExtra("price",price);
            intent.putExtra("quantity",product_quantity_txt.getText().toString());
            startActivity(intent);
        }
        else if (view.getId() == R.id.add_to_cart_btn) {
            if (chosenSize.trim().length()==0||chosenSize==null)
            {
                if(size.size()!=1)
                {
                    Toast.makeText(getActivity(), "choose size before adding to cart", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (size.size()==1)
                {
                    chosenSize=size.get(0);
                }
            }
            postRetrofitAddToCart();
        }
        else if (view.getId() == R.id.toolbar_back_navigation_btn) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .addToBackStack("")
                    .commit();
        }
        else if(view.getId()==R.id.buy_click_product_quantity_minus_btn)
        {
            int i= Integer.parseInt(product_quantity_txt.getText().toString());
            if (i>1)
            product_quantity_txt.setText(""+(i-1));
        }
        else if (view.getId()==R.id.buy_click_product_quantity_plus_btn)
        {
            int i= Integer.parseInt(product_quantity_txt.getText().toString());
            product_quantity_txt.setText(""+(i+1));
        }
        else if(view.getId()==R.id.buy_click_product_size_small)
        {
            product_size_small.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
            product_size_med.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_large.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            chosenSize=size.get(0);
        }
        else if(view.getId()==R.id.buy_click_product_size_medium)
        {
            product_size_small.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_med.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
            product_size_large.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            this.chosenSize=size.get(1);
        }
        else if(view.getId()==R.id.buy_click_product_size_large)
        {
            product_size_small.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_med.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_large.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
            product_size_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            chosenSize=size.get(2);
        }
        else if(view.getId()==R.id.buy_click_product_size_extra_large)
        {
            product_size_small.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_med.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_large.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
            product_size_extra_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            chosenSize=size.get(3);
        }
        else if(view.getId()==R.id.buy_click_product_size_extra_extra_large)
        {
            product_size_small.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_med.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_large.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
            chosenSize=size.get(4);
        }
    }

    private void postRetrofitAddToCart() {
        Toast.makeText(getActivity(),"Adding to Cart.....wait",Toast.LENGTH_SHORT).show();
        ProductDetailsDataToSend productDetailsDataToSend = new ProductDetailsDataToSend(userId,productId,chosenSize,"RED",new ProductDetailsDataToSend.InnerProductData(price,productId,profilePic,productTitle,productSlug,
                Integer.parseInt(product_quantity_txt.getText().toString()),"RED",chosenSize));
        apiInterface = ApiClient.getClient("http://apiv3.flikster.com/v3/cart-ms/createCart").create(ApiInterface.class);
        Call<ProductDetailsDataToSend> call = apiInterface.postSendToCartData(productDetailsDataToSend);
        call.enqueue(new Callback<ProductDetailsDataToSend>() {
            @Override
            public void onResponse(Call<ProductDetailsDataToSend> call, Response<ProductDetailsDataToSend> response) {
                Log.e("success", "insied onrespnse" + call + "bcbbc" + response + "gggg" + response.body().getStatusCode());
                Log.e("success", "insied onrespnse" + call + "bcbbc" + response + "gggg" + response.body().getMessage());
                Intent intent=new Intent(getActivity(),MyBagActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
                //getCartData(userId);
            }

            @Override
            public void onFailure(Call<ProductDetailsDataToSend> call, Throwable t) {
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }

    public void getProductData(String productId, List<String> size,String userId,String price,String profilePic,
                               String productTitle,String productSlug,List<String> imageGallery)
    {
        this.productId=productId;
        this.size=size;
        this.userId=userId;
        this.price=price;
        this.profilePic=profilePic;
        this.productTitle=productTitle;
        this.productSlug=productSlug;
        this.imageGallery=imageGallery;
        Log.e("check userId",""+this.userId);
        Log.e("check userId",""+this.price);
        Log.e("check userId",""+this.size);
        Log.e("check userId",""+this.productId);
        Log.e("check userId",""+this.productSlug);
        Log.e("check userId",""+this.imageGallery);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}
