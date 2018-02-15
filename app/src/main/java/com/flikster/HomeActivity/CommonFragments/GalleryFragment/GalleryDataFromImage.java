package com.flikster.HomeActivity.CommonFragments.GalleryFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 15-02-2018.
 */

public class GalleryDataFromImage {
    @SerializedName("movie")
    private List<InnerMovieData> movie;
    @SerializedName("celeb")
    private List<InnerCelebData> celeb;
    @SerializedName("media")
    private InnerMediaData media;
    @SerializedName("slug")
    private String slug;
    @SerializedName("title")
    private String title;
    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<InnerMovieData> getMovie() {
        return movie;
    }

    public void setMovie(List<InnerMovieData> movie) {
        this.movie = movie;
    }

    public List<InnerCelebData> getCeleb() {
        return celeb;
    }

    public void setCeleb(List<InnerCelebData> celeb) {
        this.celeb = celeb;
    }

    public InnerMediaData getMedia() {
        return media;
    }

    public void setMedia(InnerMediaData media) {
        this.media = media;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public class InnerMovieData
    {
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
        @SerializedName("slug")
        private String slug;
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("type")
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }
    }

    public class InnerCelebData
    {
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
        @SerializedName("slug")
        private String slug;
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("type")
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }
    }

    public class InnerMediaData
    {
        @SerializedName("gallery")
        private List<String> gallery;

        public List<String> getGallery() {
            return gallery;
        }

        public void setGallery(List<String> gallery) {
            this.gallery = gallery;
        }
    }
}
