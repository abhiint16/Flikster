package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Logins on 30-12-2017.
 */

public class WatchOrUnWatchCheckData {
    @SerializedName("statusCode")
    private Integer statusCode;

    @SerializedName("watchStatus")
    private Boolean isWatch;

    @SerializedName("wontWatchStatus")
    private Boolean isNotWatch;

    @SerializedName("userId")
    private String userId;

    @SerializedName("entityId")
    private String entityId;

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

//


    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getWatch() {
        return isWatch;
    }

    public void setWatch(Boolean watch) {
        isWatch = watch;
    }

    public Boolean getNotWatch() {
        return isNotWatch;
    }

    public void setNotWatch(Boolean notWatch) {
        isNotWatch = notWatch;
    }

    public WatchOrUnWatchCheckData(String userId, String entityId) {
        this.userId = userId;
        this.entityId = entityId;
    }

    //getUserWatchStatus
}
