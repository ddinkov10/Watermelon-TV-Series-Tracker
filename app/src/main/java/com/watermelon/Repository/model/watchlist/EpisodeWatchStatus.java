package com.watermelon.Repository.model.watchlist;

import androidx.room.Entity;

@Entity(primaryKeys = {"watchlistId", "episodeId"})
public class EpisodeWatchStatus {
    private int watchlistId;
    private int episodeId;
    private boolean isWatched;

    // Constructors, Getters, and Setters
    public EpisodeWatchStatus(int watchlistId, int episodeId, boolean isWatched) {
        this.watchlistId = watchlistId;
        this.episodeId = episodeId;
        this.isWatched = isWatched;
    }

    public int getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(int watchlistId) {
        this.watchlistId = watchlistId;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
    }
}
