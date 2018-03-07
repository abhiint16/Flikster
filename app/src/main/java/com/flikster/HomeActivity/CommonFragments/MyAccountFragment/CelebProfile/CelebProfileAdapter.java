package com.flikster.HomeActivity.CommonFragments.MyAccountFragment.CelebProfile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.flikster.HomeActivity.CommonFragments.MyAccountFragment.UserProfile.MyAccountFragmentMyFeeds;
import com.flikster.HomeActivity.CommonFragments.MyAccountFragment.UserProfile.MyAccountFragmentMyOrder;
import com.flikster.HomeActivity.CommonFragments.MyAccountFragment.UserProfile.MyAccountFragmentMyStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 20-09-2017.
 */

public class CelebProfileAdapter extends FragmentStatePagerAdapter {
    List<String> list=new ArrayList<>();
    public CelebProfileAdapter(FragmentManager fm) {
        super(fm);
        list.add("Feed");
        list.add("Biography");
        list.add("Collections");
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            return  new MyAccountFragmentMyFeeds();
        }
        else if(position==1)
        {
            return  new MyAccountFragmentMyOrder();
        }
        else if (position==2)
        {
            return new MyAccountFragmentMyStyle();
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
