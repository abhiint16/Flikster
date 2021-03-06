package com.flikster.Authentication.SignUpActivity.SignUpWithPhoneNo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 20-12-2017.
 */

public class EmailRegisterPostData {
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("email")
    private String email;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("pwd")
    private String pwd;

    public EmailRegisterPostData(String firstname, String lastname, String email, String pwd) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.pwd = pwd;
    }

    public EmailRegisterPostData(String firstname, String lastname, String email,int a, String pwd) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = email;
        this.pwd = pwd;
    }
}
