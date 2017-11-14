package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

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
            MovieFragmentFeed movieFragmentFeed=new MovieFragmentFeed();
            movieFragmentFeed.setArguments(data);
            return  movieFragmentFeed;
        }
        else if(position==1)
        {
            MovieFragmentInfo movieFragmentInfo=new MovieFragmentInfo();
            Log.e("DataMovie", data +"");
            movieFragmentInfo.setArguments(data);
            return movieFragmentInfo;
        }
        else if(position==2)
        {
            MovieFragmentStore movieFragmentStore=new MovieFragmentStore();
            movieFragmentStore.setArguments(data);
            return movieFragmentStore;
        }
        else
        {
            return null ;
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
