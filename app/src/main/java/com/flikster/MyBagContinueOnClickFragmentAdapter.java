package com.flikster;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 20-09-2017.
 */

public class MyBagContinueOnClickFragmentAdapter extends FragmentStatePagerAdapter {
    List<String> list=new ArrayList<>();
    public MyBagContinueOnClickFragmentAdapter(FragmentManager fm) {
        super(fm);
        list.add("Address");
        list.add("Checkout");
        list.add("Payment");
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            return  new CelebrityFragmentFeed();
        }
        else if(position==1)
        {
            return  new CelebrityFragmentBio();
        }
        else if(position==2)
        {
            return new CelebrityFragmentStore();
        }
        else
        {
            return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
