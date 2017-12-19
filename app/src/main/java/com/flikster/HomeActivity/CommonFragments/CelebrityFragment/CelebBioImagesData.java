package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 19-12-2017.
 */

public class CelebBioImagesData {
    @SerializedName("celebSlug")
    private String celebSlug;
    @SerializedName("data")
    private List<String> data;
    @SerializedName("statusCode")
    private String statusCode;

    public CelebBioImagesData(String celebSlug) {
        this.celebSlug = celebSlug;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
