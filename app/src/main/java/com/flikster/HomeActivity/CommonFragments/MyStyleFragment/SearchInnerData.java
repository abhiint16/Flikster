package com.flikster.HomeActivity.CommonFragments.MyStyleFragment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Logins on 14-12-2017.
 */

public class SearchInnerData {
    @SerializedName("profilePic")
    private String profilePic;

    @SerializedName("name")
    private String name;

    @SerializedName("slug")
    private String slug;

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
