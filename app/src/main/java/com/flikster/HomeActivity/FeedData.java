package com.flikster.HomeActivity;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 06-11-2017.
 */

public class FeedData {

    @SerializedName("hits")
    private FeedInnerData hits;

    public FeedInnerData getHits() {
        return hits;
    }

    public void setHits(FeedInnerData hits) {
        this.hits = hits;
    }
}
