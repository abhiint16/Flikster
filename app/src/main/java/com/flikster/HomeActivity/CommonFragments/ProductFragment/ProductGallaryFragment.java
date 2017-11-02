package com.flikster.HomeActivity.CommonFragments.ProductFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.MenuFragments.Checkout;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.R;

/**
 * Created by abhishek on 17-10-2017.
 */

public class ProductGallaryFragment extends Fragment implements View.OnClickListener {
    View view;
    TextView textView;

    Button buynowbtn, closebtn;
    TextView toolbar_frag_title;
    FragmentManager fragmentManager;
    ImageButton toolbar_back_navigation_btn,toolbar_more_icon;
    TextView txt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_zoom_images, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        return view;
    }


    private void initializeViews() {
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
        toolbar_frag_title = (TextView) view.findViewById(R.id.toolbar_frag_title);
        buynowbtn = (Button) view.findViewById(R.id.buynowbtn);
        closebtn = (Button) view.findViewById(R.id.closebtn);
        toolbar_more_icon = (ImageButton) view.findViewById(R.id.toolbar_more_icon);
    }

    private void initializeRest() {
        toolbar_frag_title.setText("Product");
        toolbar_more_icon.setVisibility(View.GONE);
        toolbar_back_navigation_btn.setOnClickListener(this);
        buynowbtn.setOnClickListener(this);
        closebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buynowbtn) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new Checkout())
                    .addToBackStack("")
                    .commit();
        }
        if (view.getId() == R.id.closebtn) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new ProductOnClick())
                    .addToBackStack("")
                    .commit();
        } else if (view.getId() == R.id.toolbar_back_navigation_btn) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .addToBackStack("")
                    .commit();
        }
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
