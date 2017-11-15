package com.flikster.HomeActivity.CommonFragments.NewsFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 15-11-2017.
 */

public class NewsData {
    @SerializedName("Items")
    public List<NewsInnerData> Items;
    @SerializedName("Count")
    public Integer Count;

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public List<NewsInnerData> getItems() {
        return Items;
    }

    public void setItems(List<NewsInnerData> items) {
        Items = items;
    }

    public class NewsInnerData {
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
