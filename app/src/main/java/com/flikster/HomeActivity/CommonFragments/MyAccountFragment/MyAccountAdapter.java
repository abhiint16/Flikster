package com.flikster.HomeActivity.CommonFragments.MyAccountFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 20-09-2017.
 */

public class MyAccountAdapter extends FragmentStatePagerAdapter {
    List<String> list=new ArrayList<>();
    public MyAccountAdapter(FragmentManager fm) {
        super(fm);
        list.add("My Posts");
        list.add("Reposts");
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            return  new MyAccountFragmentMyPosts();
        }
        else if(position==1)
        {
            return  new MyAccountFragmentMyPosts();
        }
        else
        {
            return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
