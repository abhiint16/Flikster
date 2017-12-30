package com.flikster.HomeActivity.CommonFragments.MyStyleFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.CustomStyleTypes.MyStyleFragmentOne;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MyStyleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    MyStyleAdapterViewHolder myStyleAdapterViewHolder;
    MyStyleFragmentOne fragment;
    Bundle bundle = new Bundle();
    String userId = "";
    String userName = "";


    public MyStyleAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        type.add(1);
        type.add(2);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_custom_style, parent, false);
            return new MyStyleAdapter.ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_my_style_bottom, parent, false);
            return new MyStyleAdapter.ViewHolder2(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_common_recyclerview, parent, false);
            return new MyStyleAdapter.ViewHolder1(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null && !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
            userId = SharedPrefsUtil.getStringPreference(context, "USER_ID");
            Log.e("LoginUserId", userId);
        }
        if (SharedPrefsUtil.getStringPreference(context, "USER_NAME") != null && SharedPrefsUtil.getStringPreference(context, "USER_NAME") != null) {
            userName = SharedPrefsUtil.getStringPreference(context, "USER_NAME");
            Log.e("LoginUserName", userName);
        }
        if (holder.getItemViewType() == 1) {
        } else if (holder.getItemViewType() == 2) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder2) holder).fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
            myStyleAdapterViewHolder = new MyStyleAdapterViewHolder(context, fragmentManager);
            ((ViewHolder2) holder).fragment_common_recyclerview_recycler.setAdapter(myStyleAdapterViewHolder);
        } else {
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        public ViewHolder1(View itemView) {
            super(itemView);
            customizingStyle("FIRST_STYLE");
        }

        private void customizingStyle(String type) {
            fragment = new MyStyleFragmentOne();
            bundle = new Bundle();
            bundle.putString("MY_STYLE_TYPE", type);
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.mystyle_container, fragment)
                    .addToBackStack("")
                    .commit();
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView fragment_common_recyclerview_recycler;
        Button saveandshare, sharebtn;
        EditText sayet;
        String sayetStr;

        public ViewHolder2(View itemView) {
            super(itemView);
            fragment_common_recyclerview_recycler = (RecyclerView) itemView.findViewById(R.id.fragment_common_recyclerview_recycler);
            fragment_common_recyclerview_recycler.setBackgroundColor(context.getResources().getColor(R.color.white));
            saveandshare = (Button) itemView.findViewById(R.id.saveandshare);
            sharebtn = (Button) itemView.findViewById(R.id.sharebtn);
            sayet = (EditText) itemView.findViewById(R.id.sayet);
            saveandshare.setOnClickListener(this);
            sharebtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.saveandshare) {
                sayetStr = sayet.getText().toString();
                shareImages(sayetStr);
            } else if (v.getId() == R.id.sharebtn) {
                shareImages(sayetStr);
                SharedPrefsUtil.setStringPreference(context, "ImageString", "");
                SharedPrefsUtil.setStringPreference(context, "PRODUCT_IMG", "");
                SharedPrefsUtil.setStringPreference(context, "PRODUCT_IMG_TWO", "");
                SharedPrefsUtil.setStringPreference(context, "PRODUCT_IMG_THREE", "");
            }
        }
    }

    private void shareImages(String sayetStr) {
        sayetStr = "Shiva style";
        String sayetStrslug = sayetStr.replaceAll(" ", "-");
        Log.e("sayetStrslug", sayetStrslug);

        ArrayList<String> availableImages = new ArrayList<String>();
        ArrayList<CreateShareYourStyleData.Product> productdata = new ArrayList<CreateShareYourStyleData.Product>();
        ArrayList<CreateShareYourStyleData.UserObject> userObject = new ArrayList<CreateShareYourStyleData.UserObject>();
        String completeProfileStyle = "";
        String profile_image = SharedPrefsUtil.getStringPreference(context, "ImageString");
        String product_img_url = SharedPrefsUtil.getStringPreference(context, "PRODUCT_IMG");
        String product_img_two_url = SharedPrefsUtil.getStringPreference(context, "PRODUCT_IMG_TWO");
        String product_img_three_url = SharedPrefsUtil.getStringPreference(context, "PRODUCT_IMG_THREE");

        if (product_img_url != null && !product_img_url.isEmpty()) {
            completeProfileStyle = product_img_url;
            availableImages.add(product_img_url);
            productdata.add(new CreateShareYourStyleData.Product(SharedPrefsUtil.getStringPreference(context, "PRODUCT_IMG_NAME"),
                    SharedPrefsUtil.getStringPreference(context, "PRODUCT_IMG_SLUG"), product_img_url));
        } else {
            product_img_url = "";
        }
        if (product_img_two_url != null && !product_img_two_url.isEmpty()) {
            completeProfileStyle = product_img_two_url + "\n" + product_img_url;
            availableImages.add(product_img_two_url);

            productdata.add(new CreateShareYourStyleData.Product(SharedPrefsUtil.getStringPreference(context, "PRODUCT_IMG_TWO_NAME"),
                    SharedPrefsUtil.getStringPreference(context, "PRODUCT_IMG_TWO_PRODUCT_IMG_SLUG"), product_img_two_url));
        } else {
            product_img_two_url = "";
        }
        if (product_img_three_url != null && !product_img_three_url.isEmpty()) {
            completeProfileStyle = product_img_three_url + "\n" + product_img_two_url + "\n" + product_img_url;
            availableImages.add(product_img_three_url);
            productdata.add(new CreateShareYourStyleData.Product(SharedPrefsUtil.getStringPreference(context, "PRODUCT_IMG_THREE_NAME"),
                    SharedPrefsUtil.getStringPreference(context, "PRODUCT_IMG_THREE__SLUG"), product_img_three_url));
        } else {
            product_img_three_url = "";
        }

        if (profile_image != null && !profile_image.isEmpty()) {
            boolean noproductImgavailable = validateProductImageAvailable(product_img_url, product_img_two_url, product_img_three_url);

            if (noproductImgavailable) {
                userObject.add(new CreateShareYourStyleData.UserObject(
                        "https://officechai.com/wp-content/uploads/2015/12/deepika-padukone.png",
                        userName));
                CreateShareYourStyleData createShareData = new CreateShareYourStyleData(
                        userId,
                        availableImages,
                        productdata, sayetStr,
                        sayetStrslug,
                        SharedPrefsUtil.getStringPreference(context, "CONTAINER_TYPE"),
                        userObject);
                new PostRetrofit().saveYourStyleAPI(createShareData, completeProfileStyle, context);
            } else {
                Toast.makeText(context, "At least one image must be Product Image", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(context, "Please Upload Profile Pic", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean validateProductImageAvailable(String product_img_url,
                                                  String product_img_two_url, String product_img_three_url) {
        boolean productImagavailble = false;
        if (product_img_url != null && !product_img_url.isEmpty()) {
            productImagavailble = true;
        }
        if (product_img_two_url != null && !product_img_two_url.isEmpty()) {
            productImagavailble = true;
        }
        if (product_img_three_url != null && !product_img_three_url.isEmpty()) {
            productImagavailble = true;
        }
        return productImagavailble;
    }

}
