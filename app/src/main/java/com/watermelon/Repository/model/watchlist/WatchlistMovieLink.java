package com.watermelon.Repository.model.watchlist;

import androidx.room.Entity;

@Entity(primaryKeys = {"watchlistId", "movieId"})
public class WatchlistMovieLink {
    private int watchlistId;
    private int movieId;

    // Constructors, Getters, and Setters
    public WatchlistMovieLink(int watchlistId, int movieId) {
        this.watchlistId = watchlistId;
        this.movieId = movieId;
    }

    public int getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(int watchlistId) {
        this.watchlistId = watchlistId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}

