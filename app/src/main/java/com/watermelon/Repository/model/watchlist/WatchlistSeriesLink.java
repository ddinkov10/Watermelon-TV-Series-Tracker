package com.watermelon.Repository.model.watchlist;

import androidx.room.Entity;

@Entity(primaryKeys = {"watchlistId", "seriesId"})
public class WatchlistSeriesLink {
    private int watchlistId;
    private int seriesId;

    // Constructors, Getters, and Setters
    public WatchlistSeriesLink(int watchlistId, int seriesId) {
        this.watchlistId = watchlistId;
        this.seriesId = seriesId;
    }

    public int getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(int watchlistId) {
        this.watchlistId = watchlistId;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }
}

