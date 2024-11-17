package com.watermelon.Repository.TvSeriesRepository.datasource;

public interface LocalTvSeriesDataSource {

    void addTvSeriesToWatchlist(int id);

    void removeTvSeriesFromWatchlist(int id);

}
