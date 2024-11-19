package com.watermelon.Repository.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"seriesId", "genreId"})
public class SeriesGenreLink {
    private int seriesId;
    private int genreId;

    public SeriesGenreLink(int seriesId, int genreId) {
        this.seriesId = seriesId;
        this.genreId = genreId;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}

