package com.flikster.HomeActivity;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 06-11-2017.
 */

public class FeedData {

    @SerializedName("Count")
    public Integer Count;
    @SerializedName("ScannedCount")
    public Integer ScannedCount;
    @SerializedName("Items")
    public List<FeedInnerData> Items;

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public Integer getScannedCount() {
        return ScannedCount;
    }

    public void setScannedCount(Integer scannedCount) {
        ScannedCount = scannedCount;
    }

    public List<FeedInnerData> getItems() {
        Log.e("eeeeeee","eeeeeeeeee");
        return Items;
    }

    public void setItems(List<FeedInnerData> items) {
        Items = items;
    }
}
