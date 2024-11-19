package com.watermelon.Repository.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Series.class,
        parentColumns = "id",
        childColumns = "seriesId",
        onDelete = ForeignKey.CASCADE))
public class Episode {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int seriesId; // Foreign key
    private String title;
    private int seasonNumber;
    private int episodeNumber;

    private String airDate;


    // Constructors, Getters, and Setters
    public Episode(int seriesId, String title, int seasonNumber, int episodeNumber, String airDate) {
        this.seriesId = seriesId;
        this.title = title;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.airDate = airDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }
}

