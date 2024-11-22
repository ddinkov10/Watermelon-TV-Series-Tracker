package com.watermelon.Repository.TvSeriesRepository;

import com.watermelon.Models.TvSeries;
import com.watermelon.Repository.TvSeriesRepository.datasource.LocalTvSeriesDataSource;
import com.watermelon.domain.repository.TvSeriesRepository;

import java.util.List;

public class TvSeriesRepositoryImpl implements TvSeriesRepository {

    private final LocalTvSeriesDataSource localTvSeriesDataSource;

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

    @Override
    public List<TvSeries> getAllTvSeries() {
        return this.localTvSeriesDataSource.getAllTvSeries();
    }
}
