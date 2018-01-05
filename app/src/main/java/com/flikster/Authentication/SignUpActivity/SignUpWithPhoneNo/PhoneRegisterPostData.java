package com.flikster.Authentication.SignUpActivity.SignUpWithPhoneNo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 20-12-2017.
 */

public class PhoneRegisterPostData {
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
    @SerializedName("role")
    private String role;
    @SerializedName("gender")
    private String gender;

    public PhoneRegisterPostData(String firstname,
                                 String email, String mobile, String pwd,
                                 String role, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
        this.pwd = pwd;
        this.role = role;
        this.gender = gender;
    }

    public PhoneRegisterPostData(String firstname, String lastname,
                                 String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
