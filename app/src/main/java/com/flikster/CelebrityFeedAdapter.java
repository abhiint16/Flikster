package com.flikster;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 20-09-2017.
 */

public class CelebrityFeedAdapter extends FragmentStatePagerAdapter {
    List<String> list=new ArrayList<>();
    public CelebrityFeedAdapter(FragmentManager fm) {
        super(fm);
        list.add("Feed");
        list.add("Biography");
        list.add("Store");
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            return  null;
        }
        else if(position==1)
        {
            return  null;
        }
        else if(position==2)
        {
            return null;
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
