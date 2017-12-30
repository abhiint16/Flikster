package com.flikster.Authentication.OtpAndResendOtpActivity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 20-12-2017.
 */

public class VerifyOTPData {
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("otp")
    private String otp;

    @SerializedName("message")
    private String message;
    @SerializedName("statusCode")
    private Integer statusCode;

    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("role")
    private String role;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("email")
    private String email;
    @SerializedName("mobileVerify")
    private String mobileVerify;
    @SerializedName("emailVerify")
    private String emailVerify;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileVerify() {
        return mobileVerify;
    }

    public void setMobileVerify(String mobileVerify) {
        this.mobileVerify = mobileVerify;
    }

    public String getEmailVerify() {
        return emailVerify;
    }

    public void setEmailVerify(String emailVerify) {
        this.emailVerify = emailVerify;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
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

    public VerifyOTPData(String id, String type, String otp) {
        this.id = id;
        this.type = type;
        this.otp = otp;
    }

    public VerifyOTPData(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public VerifyOTPData(String id, String firstname, String lastname, String role, String mobile, String email, String mobileVerify, String emailVerify) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.mobile = mobile;
        this.email = email;
        this.mobileVerify = mobileVerify;
        this.emailVerify = emailVerify;
    }
}
