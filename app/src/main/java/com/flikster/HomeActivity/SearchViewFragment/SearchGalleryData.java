package com.flikster.HomeActivity.SearchViewFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 04-01-2018.
 */

public class SearchGalleryData {
    @SerializedName("data")
    private SearchInsideData data;

    public SearchInsideData getData() {
        return data;
    }

    public void setData(SearchInsideData data) {
        this.data = data;
    }

    public class SearchInsideData
    {
        @SerializedName("Items")
        private List<SearchInsideItem> Items;

        public List<SearchInsideItem> getItems() {
            return Items;
        }

        public void setItems(List<SearchInsideItem> items) {
            Items = items;
        }

        public class SearchInsideItem
        {
            @SerializedName("media")
            private SearchInsideMedia media;
            @SerializedName("slug")
            private String slug;
            @SerializedName("id")
            private String id;
            @SerializedName("title")
            private String title;
            @SerializedName("celeb")
            private List<SearchInsideCeleb> celeb;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public SearchInsideMedia getMedia() {
                return media;
            }

            public void setMedia(SearchInsideMedia media) {
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

            public List<SearchInsideCeleb> getCeleb() {
                return celeb;
            }

            public void setCeleb(List<SearchInsideCeleb> celeb) {
                this.celeb = celeb;
            }

            public class SearchInsideMedia
            {
                @SerializedName("gallery")
                private List<String> gallery;
                @SerializedName("audio")
                private List<String> audio;
                @SerializedName("video")
                private List<String> video;

                public List<String> getGallery() {
                    return gallery;
                }

                public void setGallery(List<String> gallery) {
                    this.gallery = gallery;
                }

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
            }
            public class SearchInsideCeleb
            {
                @SerializedName("name")
                private String name;
                @SerializedName("id")
                private String id;
                @SerializedName("type")
                private String type;
                @SerializedName("profilePic")
                private String profilePic;

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
    }

}
