package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 10-11-2017.
 */

public class MovieData {

    @SerializedName("Items")
    public List<MovieInnerData> Items;

    public List<MovieInnerData> getItems() {
        return Items;
    }

    public void setItems(List<MovieInnerData> items) {
        Items = items;
    }

    public class MovieInnerData
     {
         @SerializedName("dateOfRelease")
         public String dateOfRelease;
         @SerializedName("industry")
         public String industry;
         @SerializedName("profilePic")
         public String profilePic;
         @SerializedName("storyLine")
         public String storyLine;
         @SerializedName("coverPic")
         public String coverPic;
         @SerializedName("criticRating")
         public String criticRating;
         @SerializedName("duration")
         public String duration;
         @SerializedName("title")
         public String title;
         @SerializedName("genre")
         public List<String> genre;
         @SerializedName("cast")
         public List<MovieInnerCastData> cast;
         @SerializedName("crew")
         public List<MovieInnerCastData> crew;

         public List<MovieInnerCastData> getCrew() {
             return crew;
         }

         public void setCrew(List<MovieInnerCastData> crew) {
             this.crew = crew;
         }

         public List<MovieInnerCastData> getCast() {
             return cast;
         }

         public void setCast(List<MovieInnerCastData> cast) {
             this.cast = cast;
         }

         public List<String> getGenre() {
             return genre;
         }

         public void setGenre(List<String> genre) {
             this.genre = genre;
         }

         public String getDateOfRelease() {
             return dateOfRelease;
         }

         public void setDateOfRelease(String dateOfRelease) {
             this.dateOfRelease = dateOfRelease;
         }

         public String getIndustry() {
             return industry;
         }

         public void setIndustry(String industry) {
             this.industry = industry;
         }

         public String getProfilePic() {
             return profilePic;
         }

         public void setProfilePic(String profilePic) {
             this.profilePic = profilePic;
         }

         public String getStoryLine() {
             return storyLine;
         }

         public void setStoryLine(String storyLine) {
             this.storyLine = storyLine;
         }

         public String getCoverPic() {
             return coverPic;
         }

         public void setCoverPic(String coverPic) {
             this.coverPic = coverPic;
         }

         public String getCriticRating() {
             return criticRating;
         }

         public void setCriticRating(String criticRating) {
             this.criticRating = criticRating;
         }

         public String getDuration() {
             return duration;
         }

         public void setDuration(String duration) {
             this.duration = duration;
         }

         public String getTitle() {
             return title;
         }

         public void setTitle(String title) {
             this.title = title;
         }

         public class MovieInnerCastData
         {
             @SerializedName("name")
             public String name;
             @SerializedName("profilePic")
             public String profilePic;

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
         }

         public class MovieInnerCrewData
         {
             @SerializedName("name")
             public String name;
             @SerializedName("profilePic")
             public String profilePic;

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
         }

     }

}
