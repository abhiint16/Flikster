package com.flikster.HomeActivity.CommonFragments.GalleryFragment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Logins on 01-12-2017.
 */

public class GalleryData {

    @SerializedName("hits")
    private GalleryInnerData hits;

    public GalleryInnerData getHits() {
        return hits;
    }

    public void setHits(GalleryInnerData hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "GalleryData{" +
                "hits=" + hits +
                '}';
    }

}
