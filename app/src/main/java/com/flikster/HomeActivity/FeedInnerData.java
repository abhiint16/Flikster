package com.flikster.HomeActivity;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abhishek on 06-11-2017.
 */

public class FeedInnerData {

    @SerializedName("total")
    private Integer total;
    @SerializedName("hits")
    private List<FeedInnerMoreData> hits;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<FeedInnerMoreData> getHits() {
        return hits;
    }

    public void setHits(List<FeedInnerMoreData> hits) {
        this.hits = hits;
    }

    public class FeedInnerMoreData
    {
        @SerializedName("_source")
        private FeedInnerMostData _source;

        public FeedInnerMostData get_source() {
            return _source;
        }

        public void set_source(FeedInnerMostData _source) {
            this._source = _source;
        }
    }

    public class FeedInnerMostData
    {
        @SerializedName("contentType")
        private String contentType;
        @SerializedName("text")
        private String text;
        @SerializedName("id")
        private String id;
        @SerializedName("tags")
        private List<String> tags;
        @SerializedName("title")
        private String title;
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("rating")
        private  String rating;
        @SerializedName("movie")
        private List<FeedMovieData> movie;
        @SerializedName("celeb")
        private List<FeedCelebData> celeb;
        @SerializedName("media")
        public FeedMediaData media;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<FeedCelebData> getCeleb() {
            return celeb;
        }

        public void setCeleb(List<FeedCelebData> celeb) {
            this.celeb = celeb;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public List<FeedMovieData> getMovie() {
            return movie;
        }

        public void setMovie(List<FeedMovieData> movie) {
            this.movie = movie;
        }

        public FeedMediaData getMedia() {
            return media;
        }

        public void setMedia(FeedMediaData media) {
            this.media = media;
        }
    }


    public class FeedMovieData
    {
        @SerializedName("id")
        private String id;
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("type")
        private String type;
        @SerializedName("slug")
        private String slug;
        @SerializedName("name")
        private String name;
        @SerializedName("tag")
        private List<String> tag;

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

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
    }

    public class FeedCelebData
    {
        @SerializedName("id")
        private String id;
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("type")
        private String type;
        @SerializedName("slug")
        private String slug;
        @SerializedName("name")
        private String name;
        @SerializedName("tag")
        private List<String> tag;

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

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
    }

    public  class FeedMediaData
    {
        @SerializedName("audio")
        private List<String> audio;
        @SerializedName("video")
        private List<String> video;
        @SerializedName("gallery")
        private List<String> gallery;

        public List<String> getAudio() {

            return audio;
        }

        public void setAudio(List<String> audio) {
            this.audio = audio;
        }

        public List<String> getVideo() {
            return video;
        }

        public void setVideo(List<String> video) {
            this.video = video;
        }

        public List<String> getGallery() {
            return gallery;
        }

        public void setGallery(List<String> gallery) {
            this.gallery = gallery;
        }
    }


}
