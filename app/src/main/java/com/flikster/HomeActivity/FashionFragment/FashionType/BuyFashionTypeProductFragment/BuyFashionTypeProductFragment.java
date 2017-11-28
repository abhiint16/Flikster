package com.flikster.HomeActivity.FashionFragment.FashionType.BuyFashionTypeProductFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.HomeActivity.CommonFragments.NotificationFragment.NotificationFragment;
import com.flikster.HomeActivity.FashionFragment.FashionLandingFragment.FashionLandingFragment;
import com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreFragmentAdapter;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.R;

/**
 * Created by Logins on 21-11-2017.
 */

public class BuyFashionTypeProductFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManagerFashionFragment;
    BuyFashionTypeProductFragmentAdapter celebStoreFragmentAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    FragmentManager fragmentManager;
    ImageButton toolbar_frag_multiicons_back_navigation, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart;
    TextView toolbar_frag_multiicons_title;
    FashionLandingFragment fashionLandingFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_buy_fashion_products, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {

        layoutManagerFashionFragment = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManagerFashionFragment);
        celebStoreFragmentAdapter = new BuyFashionTypeProductFragmentAdapter(getContext(), fragmentManager);
        fragment_common_recyclerview_recycler.setAdapter(celebStoreFragmentAdapter);
        toolbar_frag_multiicons_title.setText("BUY");
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        toolbar_frag_multiicons_notification.setOnClickListener(this);
        toolbar_frag_multiicons_cart.setOnClickListener(this);
    }

    private void initializeViews() {
        toolbar_frag_multiicons_title = (TextView) view.findViewById(R.id.toolbar_frag_multiicons_title);
        toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
        toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        toolbar_frag_multiicons_back_navigation = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
            fashionLandingFragment = new FashionLandingFragment();
            Bundle bundle = new Bundle();
            bundle.putString("BUY_FASHION_PAGE", "ENABLE");
            fashionLandingFragment.setArguments(bundle);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, fashionLandingFragment)
                    .addToBackStack("")
                    .commit();
        } else if (view.getId() == R.id.toolbar_frag_multiicons_notification) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new NotificationFragment())
                    .addToBackStack("")
                    .commit();
        }
    }
}
