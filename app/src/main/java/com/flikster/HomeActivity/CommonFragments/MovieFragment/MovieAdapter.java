package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityFragmentStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 16-10-2017.
 */

public class MovieAdapter extends FragmentStatePagerAdapter {

    List<String> list=new ArrayList<>();
    Bundle data;
    public MovieAdapter(FragmentManager fm, Bundle data) {
        super(fm);
        list.add("Feed");
        list.add("Info");
        list.add("Store");
        this.data=data;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            return  new MovieFragmentFeed();
        }
        else if(position==1)
        {
            MovieFragmentInfo movieFragmentInfo=new MovieFragmentInfo();
            movieFragmentInfo.setArguments(data);
            return movieFragmentInfo;
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
