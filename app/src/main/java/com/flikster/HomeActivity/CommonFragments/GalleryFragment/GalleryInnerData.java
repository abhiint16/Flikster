package com.flikster.HomeActivity.CommonFragments.GalleryFragment;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Logins on 01-12-2017.
 */

public class GalleryInnerData implements Serializable {

    @SerializedName("total")
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<GalleryInnerMoreData> getHits() {
        return hits;
    }

    public void setHits(List<GalleryInnerMoreData> hits) {
        this.hits = hits;
    }

    @SerializedName("hits")
    private List<GalleryInnerMoreData> hits;


    public class GalleryInnerMoreData {
        @SerializedName("_source")
        private GalleryInnerCompleteData _source;

        public GalleryInnerCompleteData get_source() {
            return _source;
        }

        public void set_source(GalleryInnerCompleteData _source) {
            this._source = _source;
        }
    }

    public class GalleryInnerCompleteData {
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
        private String rating;
        @SerializedName("movie")
        private List<GallerMovieData> movie;
        @SerializedName("celeb")
        private List<GallerCelebData> celeb;
        @SerializedName("media")
        public GalleryMediaData media;

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

        public List<GallerCelebData> getCeleb() {
            return celeb;
        }

        public void setCeleb(List<GallerCelebData> celeb) {
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

        public List<GallerMovieData> getMovie() {
            return movie;
        }

        public void setMovie(List<GallerMovieData> movie) {
            this.movie = movie;
        }

        public GalleryMediaData getMedia() {
            return media;
        }

        public void setMedia(GalleryMediaData media) {
            this.media = media;
        }

    }


    public class GallerCelebData {
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

    public class GallerMovieData {
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

    public class GalleryMediaData {
        /*@SerializedName("audio")
        public List<String> audio;*/
        @SerializedName("video")
        private List<String> video;
        @SerializedName("gallery")
        private List<String> gallery;

        /*public List<String> getAudio() {

            return audio;
        }

        public void setAudio(List<String> audio) {
            this.audio = audio;
        }*/

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
