package com.flikster.HomeActivity.CommonFragments.MyStyleFragment;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Logins on 13-12-2017.
 */

public class StyleSearchData {


    @SerializedName("ScannedCount")
    private Integer scannedCount;

    public Integer getScannedCount() {
        return scannedCount;
    }

    public void setScannedCount(Integer scannedCount) {
        this.scannedCount = scannedCount;
    }

    @SerializedName("Items")
    private List<SearchInnerData> Items;

    public void setItems(List<SearchInnerData> items) {
        Items = items;
    }


    public List<SearchInnerData> getItems() {
        return Items;
    }

    public void setHits(List<SearchInnerData> Items) {
        this.Items = Items;
    }

    /*@Override
    public String toString() {
        return "SearchInnerData{" +
                "Items=" + Items +
                '}';
    }*/
}
