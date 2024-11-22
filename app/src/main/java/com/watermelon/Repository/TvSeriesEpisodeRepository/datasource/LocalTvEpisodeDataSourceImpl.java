package com.watermelon.Repository.TvSeriesEpisodeRepository.datasource;

import android.util.Pair;

import com.watermelon.Repository.AppDatabase;

import java.util.List;

public class LocalTvEpisodeDataSourceImpl implements LocalTvEpisodeDataSource {

    private final AppDatabase appDatabase;

    public LocalTvEpisodeDataSourceImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public void setTvSeriesEpisodeWatchedFlag(Pair<Integer, Boolean> params) {
        int id = params.first;
        boolean flag = params.second;

        appDatabase.getTvSeriesEpisodeDao()
                .updateTvSeriesEpisodeWatchedFlag(id, flag);
    }

    @Override
    public void setTvSeriesAllSeasonWatched(Pair<List<Integer>, Boolean> params) {
        List<Integer> ids = params.first;
        boolean flag = params.second;

        appDatabase.getTvSeriesEpisodeDao().updateTvSeriesAllSeasonWatched(ids, flag);
    }
}
