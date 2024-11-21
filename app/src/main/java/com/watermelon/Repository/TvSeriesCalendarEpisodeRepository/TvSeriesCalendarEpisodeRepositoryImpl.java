package com.watermelon.Repository.TvSeriesCalendarEpisodeRepository;

import com.watermelon.Models.TvSeriesCalendarEpisode;
import com.watermelon.Repository.TvSeriesCalendarEpisodeRepository.datasource.LocalTvSeriesCalendarEpisodeDataSource;
import com.watermelon.domain.repository.TvSeriesCalendarEpisodeRepository;

import java.util.List;

public class TvSeriesCalendarEpisodeRepositoryImpl implements TvSeriesCalendarEpisodeRepository {

    private final LocalTvSeriesCalendarEpisodeDataSource localTvSeriesCalendarEpisodeDataSource;

    public TvSeriesCalendarEpisodeRepositoryImpl(LocalTvSeriesCalendarEpisodeDataSource localTvSeriesCalendarEpisodeDataSource) {
        this.localTvSeriesCalendarEpisodeDataSource = localTvSeriesCalendarEpisodeDataSource;
    }

    @Override
    public List<TvSeriesCalendarEpisode> getTvSeriesCalendarEpisodes(boolean flag) {
        return localTvSeriesCalendarEpisodeDataSource.getTvSeriesCalendarEpisodes(flag);
    }
}
