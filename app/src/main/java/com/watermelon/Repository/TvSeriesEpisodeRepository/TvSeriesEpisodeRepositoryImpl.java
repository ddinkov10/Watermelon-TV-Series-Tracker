package com.watermelon.Repository.TvSeriesEpisodeRepository;

import android.util.Pair;

import com.watermelon.Repository.TvSeriesEpisodeRepository.datasource.LocalTvEpisodeDataSource;
import com.watermelon.domain.repository.TvSeriesEpisodeRepository;

public class TvSeriesEpisodeRepositoryImpl implements TvSeriesEpisodeRepository {

    private final LocalTvEpisodeDataSource localTvEpisodeDataSource;


    public TvSeriesEpisodeRepositoryImpl(LocalTvEpisodeDataSource localTvEpisodeDataSource) {
        this.localTvEpisodeDataSource = localTvEpisodeDataSource;
    }

    @Override
    public void setTvSeriesEpisodeWatchedFlag(Pair<Integer, Boolean> params) {
        localTvEpisodeDataSource.setTvSeriesEpisodeWatchedFlag(params);
    }

}
