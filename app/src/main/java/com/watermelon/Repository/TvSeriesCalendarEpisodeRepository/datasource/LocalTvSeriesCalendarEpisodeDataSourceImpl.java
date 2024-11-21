package com.watermelon.Repository.TvSeriesCalendarEpisodeRepository.datasource;

import com.watermelon.Models.TvSeriesCalendarEpisode;
import com.watermelon.Repository.AppDatabase;

import java.util.List;

public class LocalTvSeriesCalendarEpisodeDataSourceImpl implements LocalTvSeriesCalendarEpisodeDataSource {

    private final AppDatabase appDatabase;

    public LocalTvSeriesCalendarEpisodeDataSourceImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public List<TvSeriesCalendarEpisode> getTvSeriesCalendarEpisodes(boolean flag) {
        return appDatabase.getTvSeriesCalendarEpisodeDao().getCalendarTvSeries(flag);
    }
}
