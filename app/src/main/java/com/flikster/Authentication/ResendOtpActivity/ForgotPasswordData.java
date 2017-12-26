package com.flikster.Authentication.ResendOtpActivity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 20-12-2017.
 */

public class ForgotPasswordData {
    @SerializedName("username")
    private String username;
    @SerializedName("type")
    private String type;
    @SerializedName("message")
    private String message;
    @SerializedName("statusCode")
    private Integer statusCode;

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
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ForgotPasswordData(String username, String type, String message) {
        this.username = username;
        this.type = type;
        this.message = message;
    }

    public ForgotPasswordData(String username, String type) {
        this.username = username;
        this.type = type;
    }
}
