package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 10-11-2017.
 */

public class CelebrityData {

    @SerializedName("Items")
    public List<CelebrityInnerData> Items;

    public List<CelebrityInnerData> getItems() {
        return Items;
    }

    public void setItems(List<CelebrityInnerData> items) {
        Items = items;
    }

    public class CelebrityInnerData {
        @SerializedName("placeOfBirth")
        public String placeOfBirth;
        @SerializedName("name")
        public String name;
        @SerializedName("profilePic")
        public String profilePic;
        @SerializedName("gender")
        public String gender;
        @SerializedName("biography")
        public String biography;
        @SerializedName("coverPic")
        public String coverPic;
        @SerializedName("dateOfBirth")
        public String dateOfBirth;
        @SerializedName("role")
        public List<String> role;

        public String getPlaceOfBirth() {
            return placeOfBirth;
        }

        public void setPlaceOfBirth(String placeOfBirth) {
            this.placeOfBirth = placeOfBirth;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getBiography() {
            return biography;
        }

        public void setBiography(String biography) {
            this.biography = biography;
        }

        public String getCoverPic() {
            return coverPic;
        }

        public void setCoverPic(String coverPic) {
            this.coverPic = coverPic;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public List<String> getRole() {
            return role;
        }

        public void setRole(List<String> role) {
            this.role = role;
        }
    }
}
