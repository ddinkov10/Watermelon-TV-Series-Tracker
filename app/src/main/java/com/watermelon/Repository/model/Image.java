package com.watermelon.Repository.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Series.class,
        parentColumns = "id",
        childColumns = "seriesId",
        onDelete = ForeignKey.CASCADE))
public class Image {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int seriesId; // Foreign key
    private String url;

    // Constructors, Getters, and Setters
    public Image(int seriesId, String url) {
        this.seriesId = seriesId;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

