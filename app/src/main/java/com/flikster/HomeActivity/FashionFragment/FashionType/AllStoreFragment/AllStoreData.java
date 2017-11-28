package com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment;

import com.flikster.HomeActivity.FeedInnerData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 06-11-2017.
 */

public class AllStoreData {

    @SerializedName("hits")
    private AllStoreInnerData hits;

    public AllStoreInnerData getHits() {
        return hits;
    }

    public void setHits(AllStoreInnerData hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "FeedData{" +
                "hits=" + hits +
                '}';
    }
}
