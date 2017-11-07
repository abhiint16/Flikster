package com.flikster.HomeActivity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 06-11-2017.
 */

public class FeedInnerData {

    @SerializedName("title")
    public String title;
    @SerializedName("contentType")
    public String contentType;
    @SerializedName("profilePic")
    public String profilePic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
