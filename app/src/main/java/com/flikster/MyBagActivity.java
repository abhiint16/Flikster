package com.flikster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class MyBagFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyBagAdapter myBagAdapter;
    ImageButton fragment_toolbar_plus_recyclerview_back_navigation;
    Button fragment_toolbar_plus_recyclerview_continue_button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_toolbar_plus_recyclerview,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        myBagAdapter=new MyBagAdapter();
        recyclerView.setAdapter(myBagAdapter);
        fragment_toolbar_plus_recyclerview_back_navigation.setOnClickListener(this);
        fragment_toolbar_plus_recyclerview_continue_button.setOnClickListener(this);
    }

    private void initializeViews() {
        recyclerView=(RecyclerView)view.findViewById(R.id.fragment_toolbar_plus_recyclerview_recycler_view);
        fragment_toolbar_plus_recyclerview_continue_button=(Button)view.findViewById(R.id.fragment_toolbar_plus_recyclerview_continue_button);
        fragment_toolbar_plus_recyclerview_back_navigation=(ImageButton)view.findViewById(R.id.fragment_toolbar_plus_recyclerview_back_navigation);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fragment_toolbar_plus_recyclerview_back_navigation)
        {
            getFragmentManager().popBackStackImmediate();
        }
        else if(view.getId()==R.id.fragment_toolbar_plus_recyclerview_continue_button)
        {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container,new MyBagContinueOnClickFragment())
                    .addToBackStack("")
                    .commit();
        }
    }
}
