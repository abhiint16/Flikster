package com.flikster.HomeActivity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 03-01-2018.
 */

public class GlobalSearchPostData {
    @SerializedName("searchTag")
    private String text;

    public GlobalSearchPostData(String text) {
        this.text = text;
    }
}
