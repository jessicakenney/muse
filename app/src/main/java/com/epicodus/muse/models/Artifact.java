package com.epicodus.muse.models;

import org.parceler.Parcel;

/**
 * Created by momma on 10/19/17.
 */

@Parcel
public class Artifact {
    private String title;
    private String type;
    private String url;
    private String medium;
    private String date;
    private String description;
    private String justification;
    private String objectId;
    private String imageUrl;
    private String pushId;
    private String index;

    public Artifact() {}
    public Artifact( String title, String type, String url, String medium, String date, String description, String justification, String objectId, String imageUrl ){

        this.title = title;
        this.type = type;
        this.url = url;
        this.medium = medium;
        this.date = date;
        this.description = description;
        this.justification = justification;
        this.objectId = objectId;
        this.imageUrl = imageUrl;
        this.index = "not_specified";
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getMedium() {
        return medium;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getJustification() {
        return justification;
    }
    public String getObjectId() {
        return objectId;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }


}
