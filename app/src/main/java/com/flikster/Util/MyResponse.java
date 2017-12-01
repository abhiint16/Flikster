package com.flikster.Util;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Logins on 29-11-2017.
 */

public class MyResponse {
    @SerializedName("success")
    private boolean success;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean _success) {
        success = _success;
    }

}
