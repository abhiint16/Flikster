package com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current;

import com.flikster.HomeActivity.FeedInnerData;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Logins on 03-01-2018.
 */

public class AuctionCurrentOrUpcomingData implements Serializable {

    @SerializedName("currentAuctions")
    public List<AuctionInnerData> currentAuctions;

    @SerializedName("upcomingAuctions")
    public List<AuctionInnerData> upcomingAuctions;

    public List<AuctionInnerData> getUpcomingAuctions() {
        return upcomingAuctions;
    }

    public void setUpcomingAuctions(List<AuctionInnerData> upcomingAuctions) {
        this.upcomingAuctions = upcomingAuctions;
    }

    public List<AuctionInnerData> getCurrentAuctions() {
        return currentAuctions;
    }

    public void setCurrentAuctions(List<AuctionInnerData> currentAuctions) {
        this.currentAuctions = currentAuctions;
    }

    @SerializedName("Count")
    public Integer Count;

    @SerializedName("ScannedCount")
    public Integer ScannedCount;



    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public Integer getScannedCount() {
        return ScannedCount;
    }

    public void setScannedCount(Integer scannedCount) {
        ScannedCount = scannedCount;
    }

    public class AuctionInnerData implements Serializable {
        @SerializedName("profilePic")
        public String profilePic;
        @SerializedName("name")
        public String name;
        @SerializedName("brand")
        public String brand;

        @SerializedName("startingPrice")
        public String startingPrice;

        @SerializedName("startTime")
        public String startTime;

        @SerializedName("startDate")
        public String startDate;

        @SerializedName("endDate")
        public String endDate;

        @SerializedName("endTime")
        public String endTime;

        @SerializedName("id")
        public String id;

        @SerializedName("bidIncrement")
        private String bidIncrement;

        public String getBidIncrement() {
            return bidIncrement;
        }

        public void setBidIncrement(String bidIncrement) {
            this.bidIncrement = bidIncrement;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStartingPrice() {
            return startingPrice;
        }

        public void setStartingPrice(String startingPrice) {
            this.startingPrice = startingPrice;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        //movie
        @SerializedName("movie")
        public List<MovieTypeInnerData> movie;

        @SerializedName("celeb")
        public List<CelebTypeInnerData> celeb;

        public List<CelebTypeInnerData> getCeleb() {
            return celeb;
        }

        public void setCeleb(List<CelebTypeInnerData> celeb) {
            this.celeb = celeb;
        }

        public List<MovieTypeInnerData> getMovie() {
            return movie;
        }

        public void setMovie(List<MovieTypeInnerData> movie) {
            this.movie = movie;
        }

        @SerializedName("description")
        public String description;


        @SerializedName("info")
        public String info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @SerializedName("gallery")
        public List<String> gallery;

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

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public List<String> getGallery() {
            return gallery;
        }

        public void setGallery(List<String> gallery) {
            this.gallery = gallery;
        }
    }

    public class MovieTypeInnerData implements  Serializable{
        @SerializedName("role")
        public List<String> role;
        @SerializedName("genre")
        public List<String> genre;
        @SerializedName("profilePic")
        public String profilePic;
        @SerializedName("name")
        public String name;

        public List<String> getGenre() {
            return genre;
        }

        public void setGenre(List<String> genre) {
            this.genre = genre;
        }

        public List<String> getRole() {
            return role;
        }

        public void setRole(List<String> role) {
            this.role = role;
        }

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
    public class CelebTypeInnerData implements  Serializable{
        @SerializedName("role")
        public List<String> role;
        @SerializedName("genre")
        public List<String> genre;
        @SerializedName("profilePic")
        public String profilePic;
        @SerializedName("name")
        public String name;

        public List<String> getGenre() {
            return genre;
        }

        public void setGenre(List<String> genre) {
            this.genre = genre;
        }

        public List<String> getRole() {
            return role;
        }

        public void setRole(List<String> role) {
            this.role = role;
        }

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
