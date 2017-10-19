package com.epicodus.muse.models;

/**
 * Created by momma on 10/19/17.
 */

public class Artifact {
    private String title;
    private String type;
    private String medium;
    private String date;
    private String description;
    private String justification;
    private String objectId;
    private String imageUrl;

    public Artifact( String title, String type, String medium, String date, String description, String justification, String objectId, String imageUrl ){

        this.title = title;
        this.type = type;
        this.medium = medium;
        this.date = date;
        this.description = description;
        this.justification = justification;
        this.objectId = objectId;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
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

}
