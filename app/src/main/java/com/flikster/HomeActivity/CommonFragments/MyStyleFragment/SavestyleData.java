package com.flikster.HomeActivity.CommonFragments.MyStyleFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Logins on 18-12-2017.
 */

public class SavestyleData {
    String userId;
    String slug;
    String title;
    String imageurl;


    public SavestyleData(String userId, String slug, String title, String imageurl) {
        this.userId = userId;
        this.slug = slug;
        this.title = title;
        this.imageurl = imageurl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


    @Override
    public String toString()
    {
        String json = "";
        try
        {
            json = toJSON().toString();
        }
        catch (Exception e)
        {
        }
        return "userObject: " + json;
    }

    public JSONObject toJSON() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("name", userId);
        jsonObject.putOpt("slug", slug);
//        jsonObject.putOpt("name", title);
        jsonObject.putOpt("profilePic", imageurl);
        return jsonObject;
    }
}
