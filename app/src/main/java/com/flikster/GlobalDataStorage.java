package com.flikster;

import android.app.Application;
import android.util.Log;

/**
 * Created by abhishek on 11-11-2017.
 */

public class GlobalDataStorage extends Application {

    String celebrityProfilePic;
    String celebrityCoverPic;
    String celebrityName;
    String celebrityRole;

    public String getCelebrityProfilePic() {
        return celebrityProfilePic;
    }

    public void setCelebrityProfilePic(String celebrityProfilePic) {
        Log.e("inside global","GLOBALDAtastorage");
        Log.e("checkpic"," "+getCelebrityProfilePic());
        this.celebrityProfilePic = celebrityProfilePic;
        Log.e("checkpic1"," "+getCelebrityProfilePic());
    }

    public String getCelebrityCoverPic() {
        return celebrityCoverPic;
    }

    public void setCelebrityCoverPic(String celebrityCoverPic) {
        this.celebrityCoverPic = celebrityCoverPic;
    }

    public String getCelebrityName() {
        return celebrityName;
    }

    public void setCelebrityName(String celebrityName) {
        this.celebrityName = celebrityName;
    }

    public String getCelebrityRole() {
        return celebrityRole;
    }

    public void setCelebrityRole(String celebrityRole) {
        this.celebrityRole = celebrityRole;
    }
}
