package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import com.flikster.HomeActivity.FeedInnerData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 10-11-2017.
 */

public class CelebrityData {

    @SerializedName("hits")
    private CelebrityInnerData hits;

    public CelebrityInnerData getHits() {
        return hits;
    }

    public void setHits(CelebrityInnerData hits) {
        this.hits = hits;
    }

    public class CelebrityInnerData
    {
        @SerializedName("total")
        private Integer total;
        @SerializedName("hits")
        private List<CelebrityInnerInnerData> hits;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<CelebrityInnerInnerData> getHits() {
            return hits;
        }

        public void setHits(List<CelebrityInnerInnerData> hits) {
            this.hits = hits;
        }


        public class CelebrityInnerInnerData
        {
            @SerializedName("_source")
            private CelebrityInnerMostData _source;

            public CelebrityInnerMostData get_source() {
                return _source;
            }

            public void set_source(CelebrityInnerMostData _source) {
                this._source = _source;
            }


            public  class CelebrityInnerMostData
            {
                @SerializedName("coverPic")
                private String coverPic;
                @SerializedName("biography")
                private String biography;
                @SerializedName("dateOfBirth")
                private String dateOfBirth;
                @SerializedName("placeOfBirth")
                private String placeOfBirth;
                @SerializedName("name")
                private String name;
                @SerializedName("role")
                private List<String> role;
                @SerializedName("gender")
                private String gender;

                public String getCoverPic() {
                    return coverPic;
                }

                public void setCoverPic(String coverPic) {
                    this.coverPic = coverPic;
                }

                public String getBiography() {
                    return biography;
                }

                public void setBiography(String biography) {
                    this.biography = biography;
                }

                public String getDateOfBirth() {
                    return dateOfBirth;
                }

                public void setDateOfBirth(String dateOfBirth) {
                    this.dateOfBirth = dateOfBirth;
                }

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

                public List<String> getRole() {
                    return role;
                }

                public void setRole(List<String> role) {
                    this.role = role;
                }

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                /*public class MovieCastData
                {
                    @SerializedName("profilePic")
                    private String profilePic;
                    @SerializedName("name")
                    private String name;
                    @SerializedName("slug")
                    private String slug;

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
                }*/

            }
        }


    }

}
