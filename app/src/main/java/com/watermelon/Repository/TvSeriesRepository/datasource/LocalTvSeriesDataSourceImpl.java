package com.watermelon.Repository.TvSeriesRepository.datasource;

import com.watermelon.Repository.AppDatabase;

public class LocalTvSeriesDataSourceImpl implements LocalTvSeriesDataSource {

    private final AppDatabase appDatabase;

    public LocalTvSeriesDataSourceImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public void addTvSeriesToWatchlist(int id) {
        appDatabase.getTvSeriesDao().addTvSeriesToWatchlist(id);
    }

    @Override
    public void removeTvSeriesFromWatchlist(int id) {
        appDatabase.getTvSeriesDao().removeTvSeriesFromWatchlist(id);
    }
}
