package com.flikster.HomeActivity.CommonFragments.MovieFragment;

import com.flikster.HomeActivity.FeedInnerData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 10-11-2017.
 */

public class MovieData {

    @SerializedName("hits")
    private MovieInnerData hits;

    public MovieInnerData getHits() {
        return hits;
    }

    public void setHits(MovieInnerData hits) {
        this.hits = hits;
    }

    public class MovieInnerData
     {
         @SerializedName("total")
         private Integer total;
         @SerializedName("hits")
         private List<MovieInnerInnerData> hits;

         public Integer getTotal() {
             return total;
         }

         public void setTotal(Integer total) {
             this.total = total;
         }

         public List<MovieInnerInnerData> getHits() {
             return hits;
         }

         public void setHits(List<MovieInnerInnerData> hits) {
             this.hits = hits;
         }


         public class MovieInnerInnerData
         {
             @SerializedName("_source")
             private MovieInnerMostData _source;

             public MovieInnerMostData get_source() {
                 return _source;
             }

             public void set_source(MovieInnerMostData _source) {
                 this._source = _source;
             }


             public  class MovieInnerMostData
             {
                 @SerializedName("dateOfRelease")
                 private String dateOfRelease;
                 @SerializedName("title")
                 private String title;
                 @SerializedName("censorCertificate")
                 private String censorCertificate;
                 @SerializedName("coverPic")
                 private String coverPic;
                 @SerializedName("profilePic")
                 private String profilePic;
                 @SerializedName("genre")
                 private List<String> genre;
                 @SerializedName("duration")
                 private  String duration;
                 @SerializedName("cast")
                 private List<MovieCastData> cast;
                 @SerializedName("crew")
                 private List<MovieCrewData> crew;
                 @SerializedName("storyLine")
                 private  String storyLine;
                 @SerializedName("slug")
                 private  String slug;
                 @SerializedName("id")
                 private  String id;

                 public String getProfilePic() {
                     return profilePic;
                 }

                 public void setProfilePic(String profilePic) {
                     this.profilePic = profilePic;
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

                 public List<MovieCrewData> getCrew() {
                     return crew;
                 }

                 public void setCrew(List<MovieCrewData> crew) {
                     this.crew = crew;
                 }

                 public List<MovieCastData> getCast() {
                     return cast;
                 }

                 public void setCast(List<MovieCastData> cast) {
                     this.cast = cast;
                 }

                 public String getStoryLine() {
                     return storyLine;
                 }

                 public void setStoryLine(String storyLine) {
                     this.storyLine = storyLine;
                 }

                 public String getDateOfRelease() {
                     return dateOfRelease;
                 }

                 public void setDateOfRelease(String dateOfRelease) {
                     this.dateOfRelease = dateOfRelease;
                 }

                 public String getTitle() {
                     return title;
                 }

                 public void setTitle(String title) {
                     this.title = title;
                 }

                 public String getCensorCertificate() {
                     return censorCertificate;
                 }

                 public void setCensorCertificate(String censorCertificate) {
                     this.censorCertificate = censorCertificate;
                 }

                 public String getCoverPic() {
                     return coverPic;
                 }

                 public void setCoverPic(String coverPic) {
                     this.coverPic = coverPic;
                 }

                 public List<String> getGenre() {
                     return genre;
                 }

                 public void setGenre(List<String> genre) {
                     this.genre = genre;
                 }

                 public String getDuration() {
                     return duration;
                 }

                 public void setDuration(String duration) {
                     this.duration = duration;
                 }


                 public class MovieCastData
                 {
                     @SerializedName("profilePic")
                     private String profilePic;
                     @SerializedName("name")
                     private String name;
                     @SerializedName("slug")
                     private String slug;
                     @SerializedName("id")
                     private String id;

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

                     public String getName() {
                         return name;
                     }

                     public void setName(String name) {
                         this.name = name;
                     }

                     public String getSlug() {
                         return slug;
                     }

                     public void setSlug(String slug) {
                         this.slug = slug;
                     }
                 }

                 public class MovieCrewData
                 {
                     @SerializedName("profilePic")
                     private String profilePic;
                     @SerializedName("name")
                     private String name;
                     @SerializedName("slug")
                     private String slug;
                     @SerializedName("id")
                     private String id;

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

                     public String getName() {
                         return name;
                     }

                     public void setName(String name) {
                         this.name = name;
                     }

                     public String getSlug() {
                         return slug;
                     }

                     public void setSlug(String slug) {
                         this.slug = slug;
                     }
                 }

             }
         }


     }

}
