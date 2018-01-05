package com.flikster.HomeActivity.CommonFragments.AuctionFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.AuctionCurrentFragment;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.UpComing.AuctionUpComingFragment;
import com.flikster.Util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 20-09-2017.
 */

public class AuctionFeedViewPagerAdapter extends FragmentStatePagerAdapter {
    List<String> list=new ArrayList<>();
    public AuctionFeedViewPagerAdapter(FragmentManager fm) {
        super(fm);
        list.add("Current");
        list.add("Up Comming");
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            return  new AuctionCurrentFragment();
        }
        else if(position==1)
        {
            return  new AuctionUpComingFragment();
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
