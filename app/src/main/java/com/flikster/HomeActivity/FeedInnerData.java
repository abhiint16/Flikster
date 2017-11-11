package com.flikster.HomeActivity;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    @SerializedName("text")
    public String text;
    @SerializedName("movie")
    public List<FeedMovieInnerMoreData> movie;
    @SerializedName("celeb")
    public List<FeedCelebInnerMoreData> celeb;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<FeedMovieInnerMoreData> getMovie() {
        return movie;
    }

    public void setMovie(List<FeedMovieInnerMoreData> movie) {
        this.movie = movie;
    }

    public List<FeedCelebInnerMoreData> getCeleb() {
        return celeb;
    }

    public void setCeleb(List<FeedCelebInnerMoreData> celeb) {
        this.celeb = celeb;
    }

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

    public class FeedMovieInnerMoreData{
        @SerializedName("name")
        public String name;
        @SerializedName("type")
        public String type;
        @SerializedName("profilePic")
        public String profilePic;
        @SerializedName("slug")
        public String slug;

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }
    }



    public class FeedCelebInnerMoreData{
        @SerializedName("name")
        public String name;
        @SerializedName("type")
        public String type;
        @SerializedName("profilePic")
        public String profilePic;
        @SerializedName("slug")
        public String slug;

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }
    }
}
