package com.flikster.HomeActivity.WatchFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.HomeActivity.CommonFragments.ShopByVideoFragment.ShopByVideoFragmentAdapter;
import com.flikster.R;

/**
 * Created by abhishek on 01-11-2017.
 */

public class MusicGridFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView fragment_common_recyclerview_recycler;
    RecyclerView.LayoutManager layoutManagerShopByVideoFragment;
    MusicGridItemAdapter musicGridItemAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    TextView toolbar_frag_multiicons_title;
    ImageButton toolbar_frag_multiicons_back_navigation, toolbar_frag_multiicons_notification, toolbar_frag_multiicons_cart, toolbar_frag_multiicons_overflow;
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_music, container, false);
        initializeViews();
        initializeRest();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    private void initializeRest() {
        toolbar_frag_multiicons_title.setText("Music");
        fragmentManager = getActivity().getSupportFragmentManager();
        layoutManagerShopByVideoFragment = new GridLayoutManager(getActivity(), 2);
        fragment_common_recyclerview_recycler.setLayoutManager(layoutManagerShopByVideoFragment);
        musicGridItemAdapter = new MusicGridItemAdapter(getActivity(), fragmentManager);
        fragment_common_recyclerview_recycler.setAdapter(musicGridItemAdapter);
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        toolbar_frag_multiicons_notification.setVisibility(View.GONE);
        toolbar_frag_multiicons_cart.setVisibility(View.GONE);
        toolbar_frag_multiicons_overflow.setVisibility(View.GONE);
    }

    private void initializeViews() {
        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        toolbar_frag_multiicons_toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_multiicons_toolbar);
        toolbar_frag_multiicons_title = (TextView) view.findViewById(R.id.toolbar_frag_multiicons_title);
        toolbar_frag_multiicons_back_navigation = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
        toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        toolbar_frag_multiicons_overflow = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_overflow);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_frag_multiicons_notification) {
            fragmentManager.popBackStackImmediate();
        }
    }
}
