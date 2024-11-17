package com.watermelon.Repository.TvSeriesRepository;

import com.watermelon.Repository.TvSeriesRepository.datasource.LocalTvSeriesDataSource;
import com.watermelon.domain.repository.TvSeriesRepository;

public class TvSeriesRepositoryImpl implements TvSeriesRepository {

    private LocalTvSeriesDataSource localTvSeriesDataSource;

    public TvSeriesRepositoryImpl(LocalTvSeriesDataSource localTvSeriesDataSource) {
        this.localTvSeriesDataSource = localTvSeriesDataSource;
    }

    @Override
    public void addToWatchlist(int id) {
        this.localTvSeriesDataSource.addTvSeriesToWatchlist(id);
    }

    @Override
    public void removeFromWatchlist(int id) {
        this.localTvSeriesDataSource.removeTvSeriesFromWatchlist(id);
    }
}
