package com.flikster.HomeActivity;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 24-11-2017.
 */

public class ModelForPostCommentRequest {
    @SerializedName("userName")
    private String userName;
    @SerializedName("userId")
    private String userId;
    @SerializedName("entityId")
    private String entityId;
    @SerializedName("commentText")
    private String commentText;

    @SerializedName("statusCode")
    private Integer statusCode;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public ModelForPostCommentRequest(String userName, String userId, String entityId, String commentText) {
        this.userName = userName;
        this.userId = userId;
        this.entityId = entityId;
        this.commentText = commentText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        Log.e("commentres","ModelForPostCommentRequest{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", entityId='" + entityId + '\'' +
                ", commentText='" + commentText + '\'' +
                '}');
        return "ModelForPostCommentRequest{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", entityId='" + entityId + '\'' +
                ", commentText='" + commentText + '\'' +
                '}';

    }
}
