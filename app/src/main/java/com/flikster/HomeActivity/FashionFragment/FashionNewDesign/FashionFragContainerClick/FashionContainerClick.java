package com.flikster.HomeActivity.FashionFragment.FashionNewDesign.FashionFragContainerClick;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flikster.HomeActivity.FashionFragment.FashionNewDesign.FashionFragmentNew;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

/**
 * Created by abhishek on 07-03-2018.
 */

public class FashionContainerClick extends Fragment implements TabLayout.OnTabSelectedListener, View.OnClickListener {
    View view;
    TabLayout tabLayout;
    TextView toolbar_frag_multiicons_title;
    String storeType;
    ImageButton toolbar_back_navigation_btn;
    FashionContainerClickInterface fashionContainerClickInterface;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_coommon_tablayout, container, false);
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "HEADER_NAME").equals("MOVIE_STORE")) {
            this.storeType="Movie";
            toolbar_frag_multiicons_title.setText("Movie Store");
        } else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "HEADER_NAME").equals("CELEB_STORE")){
            this.storeType="Celeb";
            toolbar_frag_multiicons_title.setText("Celeb Store");
        }
        else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "HEADER_NAME").equals("MEN_FASHION")){
            this.storeType="Men";
            toolbar_frag_multiicons_title.setText("Men Fashion");
        }
        else if (SharedPrefsUtil.getStringPreference(getActivity().getApplicationContext(), "HEADER_NAME").equals("WOMEN_FASHION")){
            this.storeType="Women";
            toolbar_frag_multiicons_title.setText("Women Fashion");
        }
        createTabIcons();
        toolbar_back_navigation_btn.setOnClickListener(this);
        tabLayout.addOnTabSelectedListener(this);
        firstTimeLaunchTab();
    }

    private void initializeViews() {
        toolbar_back_navigation_btn=(ImageButton)view.findViewById(R.id.toolbar_back_navigation_btn);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        toolbar_frag_multiicons_title=(TextView)view.findViewById(R.id.toolbar_frag_title);
        tabLayout.setSelectedTabIndicatorColor(getContext().getResources().getColor(R.color.horizontal_line));
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

    private void createTabIcons() {
        tabLayout.addTab(tabLayout.newTab().setText("CLOTHING"));
        tabLayout.addTab(tabLayout.newTab().setText("EYEWEAR"));
        tabLayout.addTab(tabLayout.newTab().setText("FOOTWEAR"));
        tabLayout.addTab(tabLayout.newTab().setText("ACCESSORIES"));
    }

    private void firstTimeLaunchTab() {
        SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "CELEB_STORE", storeType);
        SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "TAB_NO", "CLOTHING");
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout_of_tablayout, new FashionContainerClickFrag())
                .commit();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.e("check for tabclick","check for tabclick"+tab.getPosition());
        if (tab.getPosition() == 0) {
            Log.e("check for tabclick1","check for tabclick1"+tab.getPosition());
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "CELEB_STORE", storeType);
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "TAB_NO", "CLOTHING");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_of_tablayout, new FashionContainerClickFrag())
                    .commit();
        }
        else if (tab.getPosition() == 1) {
            Log.e("check for tabclick2","check for tabclick2"+tab.getPosition());
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "CELEB_STORE", storeType);
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "TAB_NO", "EYEWEAR");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_of_tablayout, new FashionContainerClickFrag())
                    .commit();
        }
        else if (tab.getPosition() == 2) {
            Log.e("check for tabclick3","check for tabclick3"+tab.getPosition());
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "CELEB_STORE", storeType);
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "TAB_NO", "FOOTWEAR");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_of_tablayout, new FashionContainerClickFrag())
                    .commit();
        }
        else if (tab.getPosition() == 3) {
            Log.e("check for tabclick4","check for tabclick4"+tab.getPosition());
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "CELEB_STORE", storeType);
            SharedPrefsUtil.setStringPreference(getActivity().getApplicationContext(), "TAB_NO", "ACCESSORIES");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_of_tablayout, new FashionContainerClickFrag())
                    .commit();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.toolbar_back_navigation_btn)
        {
            fashionContainerClickInterface.backButtonClick(new FashionFragmentNew());
        }
    }

    public interface FashionContainerClickInterface
    {
        void backButtonClick(Fragment fragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fashionContainerClickInterface=(FashionContainerClickInterface) context;
    }
}
