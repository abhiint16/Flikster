package com.flikster.CheckoutActivity.CheckoutFragment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Logins on 08-01-2018.
 */

public class InstamojoData {
    @SerializedName("access_token")
    public String access_token;



    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    @SerializedName("token_type")
    public String token_type;


    @SerializedName("grant_type")
    public String grant_type;

    @SerializedName("client_id")
    public String client_id;

    @SerializedName("client_secret")
    public String client_secret;

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public InstamojoData(String grant_type, String client_id, String client_secret) {
        this.grant_type = grant_type;
        this.client_id = client_id;
        this.client_secret = client_secret;
    }
}
