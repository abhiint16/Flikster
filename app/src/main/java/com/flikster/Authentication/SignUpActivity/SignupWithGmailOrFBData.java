package com.flikster.Authentication.SignUpActivity;

import com.flikster.Authentication.LoginActivity.LoginResponseData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Logins on 05-01-2018.
 */

public class SignupWithGmailOrFBData {
    @SerializedName("firstname")
    public String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("email")
    private String email;

    @SerializedName("statusCode")
    private Integer statusCode;

    @SerializedName("data")
    private SignInnerData data;

    @SerializedName("id")
    private String id;

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

    public SignInnerData getData() {
        return data;
    }

    public void setData(SignInnerData data) {
        this.data = data;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SignupWithGmailOrFBData(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public class SignInnerData {
        @SerializedName("email")
        private String email;
        @SerializedName("mobile")
        private String mobile;
        @SerializedName("id")
        private String id;
        @SerializedName("firstname")
        private String firstname;
        @SerializedName("lastname")
        private String lastname;

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }
    }
}
