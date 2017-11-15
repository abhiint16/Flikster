package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 15-11-2017.
 */

public class RecommendedProductData {
    @SerializedName("Items")
    public List<RecommendedProductInnerData> Items;

    public List<RecommendedProductInnerData> getItems() {
        return Items;
    }

    public void setItems(List<RecommendedProductInnerData> items) {
        Items = items;
    }

    public class RecommendedProductInnerData
    {
        @SerializedName("profilePic")
        public String profilePic;
        @SerializedName("name")
        public String name;

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
    }

}
