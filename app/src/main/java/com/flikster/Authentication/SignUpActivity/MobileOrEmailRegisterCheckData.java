package com.flikster.Authentication.SignUpActivity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 20-12-2017.
 */

public class MobileOrEmailRegisterCheckData {
    @SerializedName("Count")
    private Integer Count;

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }
}
