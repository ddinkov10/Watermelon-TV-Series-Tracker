package com.watermelon.Repository.TvSeriesRepository.datasource;

import com.watermelon.Models.TvSeries;
import com.watermelon.Repository.AppDatabase;

import java.util.List;

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

    @Override
    public List<TvSeries> getAllTvSeries() {
        return appDatabase.getTvSeriesDao().getAllTvSeries();
    }
}
