package com.flikster.HomeActivity;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 24-11-2017.
 */

public class ModelForPostRequest {
    @SerializedName("type")
    private String type;
    @SerializedName("userId")
    private String userId;
    @SerializedName("entityId")
    private String entityId;

    public ModelForPostRequest(String type, String userId, String entityId) {
        this.type = type;
        this.userId = userId;
        this.entityId = entityId;
        Log.e("insied ModelForPost","insied ModelForPostRequest"+this.type+this.userId+this.entityId);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}
