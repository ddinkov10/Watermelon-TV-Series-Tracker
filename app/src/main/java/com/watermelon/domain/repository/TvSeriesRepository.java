package com.watermelon.domain.repository;

public interface TvSeriesRepository {

    void addToWatchlist(int id);

    void removeFromWatchlist(int id);
}
