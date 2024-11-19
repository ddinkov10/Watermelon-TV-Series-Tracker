package com.watermelon.Repository.SeriesRepository.datasource;

public interface LocalTvSeriesDataSource {

    void addTvSeriesToWatchlist(int id);

    void removeTvSeriesFromWatchlist(int id);

}
