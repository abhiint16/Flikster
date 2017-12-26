package com.flikster.Authentication.SignUpActivity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Logins on 22-12-2017.
 */

public class RegisterPostStatus {
    @SerializedName("statusCode")
    private Integer statusCode;
    @SerializedName("otpStatus")
    private Boolean otpStatus;

    public Boolean getOtpStatus() {
        return otpStatus;
    }

    public void setOtpStatus(Boolean otpStatus) {
        this.otpStatus = otpStatus;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }


}
