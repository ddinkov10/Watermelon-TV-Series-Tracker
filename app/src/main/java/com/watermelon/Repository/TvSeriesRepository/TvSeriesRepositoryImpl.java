package com.watermelon.Repository.TvSeriesRepository;

import com.watermelon.Models.TvSeries;
import com.watermelon.Repository.TvSeriesRepository.datasource.LocalTvSeriesDataSource;
import com.watermelon.Repository.TvSeriesRepository.datasource.RemoteTvSeriesDataSource;
import com.watermelon.domain.repository.TvSeriesRepository;

import java.util.List;

public class TvSeriesRepositoryImpl implements TvSeriesRepository {

    private final LocalTvSeriesDataSource localTvSeriesDataSource;
    private final RemoteTvSeriesDataSource remoteTvSeriesDataSource;

    public TvSeriesRepositoryImpl(LocalTvSeriesDataSource localTvSeriesDataSource, RemoteTvSeriesDataSource remoteTvSeriesDataSource) {
        this.localTvSeriesDataSource = localTvSeriesDataSource;
        this.remoteTvSeriesDataSource = remoteTvSeriesDataSource;
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

    @Override
    public List<TvSeries> searchTvSeries(String searchWord) {
        return remoteTvSeriesDataSource.searchTvSeries(searchWord);
    }
}
