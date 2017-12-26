package com.flikster.Authentication.ResendOtpActivity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 20-12-2017.
 */

public class SendOTPData {
    @SerializedName("username")
    private String username;
    @SerializedName("type")
    private String type;
    @SerializedName("id")
    private String id;
    @SerializedName("statusCode")
    private Integer statusCode;

    @SerializedName("otpStatus")
    private boolean otpStatus;

    public boolean isOtpStatus() {
        return otpStatus;
    }

    public void setOtpStatus(boolean otpStatus) {
        this.otpStatus = otpStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return type;
    }

    public void setPassword(String password) {
        this.type = password;
    }

    public String getMessage() {
        return id;
    }

    public void setMessage(String message) {
        this.id = message;
    }

    public SendOTPData(String username, String type, String id) {
        this.username = username;
        this.type = type;
        this.id = id;
    }

    public SendOTPData(String username, String type) {
        this.username = username;
        this.type = type;
    }
}
