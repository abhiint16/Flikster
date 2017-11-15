package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 15-11-2017.
 */

public class RecommendedMoviesData {
    @SerializedName("Items")
    public List<RecommendedMoviesInnerData> Items;

    public List<RecommendedMoviesInnerData> getItems() {
        return Items;
    }

    public void setItems(List<RecommendedMoviesInnerData> items) {
        Items = items;
    }

    public class RecommendedMoviesInnerData
    {
        @SerializedName("profilePic")
        public String profilePic;
        @SerializedName("title")
        public String title;

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
