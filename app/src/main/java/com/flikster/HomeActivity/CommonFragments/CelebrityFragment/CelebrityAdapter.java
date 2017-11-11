package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragmentBio;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragmentFeed;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragmentStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 20-09-2017.
 */

public class CelebrityAdapter extends FragmentStatePagerAdapter {
    List<String> list=new ArrayList<>();
    Bundle data;
    public CelebrityAdapter(FragmentManager fm,Bundle data) {
        super(fm);
        list.add("Feed");
        list.add("Biography");
        list.add("Store");
        this.data=data;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            return  new CelebrityFragmentFeed();
        }
        else if(position==1)
        {
            CelebrityFragmentBio celebrityFragmentBio=new CelebrityFragmentBio();
            celebrityFragmentBio.setArguments(data);
            return celebrityFragmentBio;

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
