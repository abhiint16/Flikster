package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Logins on 30-12-2017.
 */

public class WatchStatusData {
    @SerializedName("statusCode")
    public Integer statusCode;
    @SerializedName("watchCount")
    public Integer watchCount;

    @SerializedName("wontWatchCount")
    public Integer wontWatchCount;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(Integer watchCount) {
        this.watchCount = watchCount;
    }

    public Integer getWontWatchCount() {
        return wontWatchCount;
    }

    public void setWontWatchCount(Integer wontWatchCount) {
        this.wontWatchCount = wontWatchCount;
    }
}
