package com.flikster.IntroSlider;

import android.widget.LinearLayout;

import com.flikster.SharedPref.SharedPref;


/**
 * Created by abhishek on 14-09-2017.
 */

public class IntroSliderPresenter {

    SharedPrefMethods sharedPrefMethods;
    SharedPref sharedPref;
    DotsCreationAndButtonClick dotsCreationAndButtonClick;

    public IntroSliderPresenter(SharedPrefMethods sharedPrefMethods, DotsCreationAndButtonClick dotsCreationAndButtonClick, SharedPref sharedPref) {
        this.sharedPref=sharedPref;
        this.dotsCreationAndButtonClick=dotsCreationAndButtonClick;
        this.sharedPrefMethods=sharedPrefMethods;
    }

    public void checkForFirstTimeLaunch()
    {
        if(!sharedPref.isFirstTimeLaunch())
            sharedPrefMethods.launchHomeScreen();
        else
            sharedPrefMethods.makeNotificationBarTrans();
    }

    public  void dotsCreation(LinearLayout linearLayout)
    {
            dotsCreationAndButtonClick.createViews(0);

    }

}
