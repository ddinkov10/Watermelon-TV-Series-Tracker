package com.watermelon.Repository.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class Series {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;

    private String status;
    private String startDate;
    private String endDate;

    private String country;
    private String network;
    private String imagePath;
    private String runtime;
    private String youtubeLink;
    private String rating;

    private long lastUpdated;

    private int apiId;

    public Series(int apiId, String title, String status, String startDate, String endDate, String country, String network, String imagePath) {
//        this.id = id;
        this.title = title;
//        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.country = country;
        this.network = network;
        this.imagePath = imagePath;
//        this.runtime = runtime;
//        this.youtubeLink = youtubeLink;
//        this.rating = rating;
        this.apiId = apiId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getApiId() {
        return apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

