package com.flikster.HomeActivity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 03-01-2018.
 */

public class GlobalSearchGetData {
    @SerializedName("movie")
    private List<SearchMovieData> movie;
    @SerializedName("celeb")
    private List<SearchCelebData> celeb;
    @SerializedName("content")
    private List<SearchContentData> content;
    @SerializedName("products")
    private List<SearchProductsData> products;
    @SerializedName("designer")
    private List<SearchDesignerData> designer;
    @SerializedName("brand")
    private List<SearchBrandData> brand;

    public List<SearchMovieData> getMovie() {
        return movie;
    }

    public void setMovie(List<SearchMovieData> movie) {
        this.movie = movie;
    }

    public List<SearchCelebData> getCeleb() {
        return celeb;
    }

    public void setCeleb(List<SearchCelebData> celeb) {
        this.celeb = celeb;
    }

    public List<SearchContentData> getContent() {
        return content;
    }

    public void setContent(List<SearchContentData> content) {
        this.content = content;
    }

    public List<SearchProductsData> getProducts() {
        return products;
    }

    public void setProducts(List<SearchProductsData> products) {
        this.products = products;
    }

    public List<SearchDesignerData> getDesigner() {
        return designer;
    }

    public void setDesigner(List<SearchDesignerData> designer) {
        this.designer = designer;
    }

    public List<SearchBrandData> getBrand() {
        return brand;
    }

    public void setBrand(List<SearchBrandData> brand) {
        this.brand = brand;
    }

    public class SearchMovieData
    {
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
        @SerializedName("slug")
        private String slug;
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("genre")
        private List<String> genre;
        @SerializedName("serviceName")
        private String serviceName;

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

        public List<String> getGenre() {
            return genre;
        }

        public void setGenre(List<String> genre) {
            this.genre = genre;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }
    }
    public class SearchCelebData
    {
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
        @SerializedName("slug")
        private String slug;
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("serviceName")
        private String serviceName;
        @SerializedName("role")
        private List<String> role;

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

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public List<String> getRole() {
            return role;
        }

        public void setRole(List<String> role) {
            this.role = role;
        }
    }
    public class SearchContentData
    {
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
        @SerializedName("slug")
        private String slug;
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("contentType")
        private String contentType;
        @SerializedName("serviceName")
        private String serviceName;

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

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }
    }
    public class SearchProductsData
    {
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
        @SerializedName("slug")
        private String slug;
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("brand")
        private String brand;
        @SerializedName("serviceName")
        private String serviceName;

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

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }
    }
    public class SearchDesignerData
    {
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
        @SerializedName("slug")
        private String slug;
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("specialization")
        private String specialization;
        @SerializedName("serviceName")
        private String serviceName;

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

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }
    }
    public class SearchBrandData
    {
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
        @SerializedName("slug")
        private String slug;
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("serviceName")
        private String serviceName;

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

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }
    }
}
