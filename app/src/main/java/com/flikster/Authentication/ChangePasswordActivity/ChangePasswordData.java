package com.flikster.Authentication.ChangePasswordActivity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 20-12-2017.
 */

public class ChangePasswordData {
    @SerializedName("id")
    private String id;
    @SerializedName("password")
    private String password;
    @SerializedName("message")
    private String message;
    @SerializedName("statusCode")
    private Integer statusCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public ChangePasswordData(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public ChangePasswordData(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
