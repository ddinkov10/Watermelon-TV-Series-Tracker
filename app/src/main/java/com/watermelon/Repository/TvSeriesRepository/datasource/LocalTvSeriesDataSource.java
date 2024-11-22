package com.watermelon.Repository.TvSeriesRepository.datasource;

import com.watermelon.Models.TvSeries;

import java.util.List;

public interface LocalTvSeriesDataSource {

    List<TvSeries> getAllTvSeries();

    void addTvSeriesToWatchlist(int id);

    void removeTvSeriesFromWatchlist(int id);

}
