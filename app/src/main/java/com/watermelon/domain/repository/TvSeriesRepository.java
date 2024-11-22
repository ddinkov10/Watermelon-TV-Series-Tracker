package com.watermelon.domain.repository;

import com.watermelon.Models.TvSeries;

import java.util.List;

public interface TvSeriesRepository {

    List<TvSeries> getAllTvSeries();

    void addToWatchlist(int id);

    void removeFromWatchlist(int id);
}
