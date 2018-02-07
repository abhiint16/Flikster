package com.flikster.HomeActivity.CommonFragments.MyStyleFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flikster.CheckoutActivity.AddressFragment.AddressFragment;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.R;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MyStyleFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView movieFragmentInfoRecycler;
    RecyclerView.LayoutManager movieFragmentInfoLayoutManager;
    MyStyleAdapter myStyleAdapter;
    FragmentManager fragmentManager;
    TextView fragment_common_recyclerview_with_tv_title, toolbar_frag_multiicons_title;

    LinearLayout fragment_common_recyclerview_with_tv_layout;
    String slug, name, profilePic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mystyle, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        movieFragmentInfoLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        movieFragmentInfoRecycler.setLayoutManager(movieFragmentInfoLayoutManager);
        myStyleAdapter = new MyStyleAdapter(getActivity(), fragmentManager);
        movieFragmentInfoRecycler.setAdapter(myStyleAdapter);
        fragment_common_recyclerview_with_tv_title.setVisibility(View.GONE);
        toolbar_frag_multiicons_title.setText("My Style");
        //toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        fragment_common_recyclerview_with_tv_layout.setBackgroundColor(getContext().getResources().getColor(R.color.style_main_background));
        //toolbar_frag_multiicons_search.setOnClickListener(this);
        //toolbar_frag_multiicons_overflow.setVisibility(View.GONE);
        //toolbar_frag_multiicons_cart.setVisibility(View.GONE);
        //toolbar_frag_multiicons_notification.setVisibility(View.GONE);
//        searchTActivityToMyStyleFragment = new SearchTActivityToMyStyleFragment();
//        searchTActivityToMyStyleFragment.userChoice();

    }

    private void initializeViews() {
        movieFragmentInfoRecycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragment_common_recyclerview_with_tv_layout = (LinearLayout) view.findViewById(R.id.fragment_common_recyclerview_with_tv_layout);
        //toolbar_frag_multiicons_back_navigation = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        toolbar_frag_multiicons_title = (TextView) view.findViewById(R.id.toolbar_frag_multiicons_title);
        //toolbar_frag_multiicons_search = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_search);
        //toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
        //toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        //toolbar_frag_multiicons_overflow = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_overflow);
    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View v) {
        /*if (v.getId() == R.id.toolbar_frag_multiicons_search) {
//            Intent i = new Intent(getContext(), SearchActivity.class);
//            startActivity(i);
        }*/
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .addToBackStack("")
                    .commit();


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

}
