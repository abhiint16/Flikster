package com.flikster.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by abhishek on 14-09-2017.
 */

public class SharedPref {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "androidhive-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_FIRST_TIME_LAUNCH2 = "IsFirstTimeLaunch2";

    public SharedPref(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch2(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH2, isFirstTime);
        editor.commit();
    }


    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
    public boolean isFirstTimeLaunch2() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH2, true);
    }
}
