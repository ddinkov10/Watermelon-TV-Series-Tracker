package com.watermelon.Repository.TvSeriesFullRepository;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.Repository.TvSeriesFullRepository.datasource.LocalTvSeriesFullDataSource;
import com.watermelon.Repository.TvSeriesFullRepository.datasource.RemoteTvSeriesFullDataSource;
import com.watermelon.domain.repository.TvSeriesFullRepository;

import java.util.List;

public class TvSeriesFullRepositoryImpl implements TvSeriesFullRepository {

    LocalTvSeriesFullDataSource localTvSeriesFullDataSource;
    RemoteTvSeriesFullDataSource remoteTvSeriesFullDataSource;

    public TvSeriesFullRepositoryImpl(LocalTvSeriesFullDataSource localTvSeriesFullDataSource, RemoteTvSeriesFullDataSource remoteTvSeriesFullDataSource) {
        this.localTvSeriesFullDataSource = localTvSeriesFullDataSource;
        this.remoteTvSeriesFullDataSource = remoteTvSeriesFullDataSource;
    }

    @Override
    public List<TvSeriesFull> getWatchlistListObservable() {
        return localTvSeriesFullDataSource.getLocalData();
    }

    @Override
    public TvSeriesFull getTvSeriesFullById(int id) {
        return localTvSeriesFullDataSource.getTvSeriesFullById(id);
    }

    @Override
    public TvSeriesFull fetchTvSeriesDetails(int id) {
        return  remoteTvSeriesFullDataSource.fetchTvSeriesDetails(id);
    }

    @Override
    public void saveTvSeriesDetails(TvSeriesFull tvSeriesFull) {
        localTvSeriesFullDataSource.saveTvSeriesFull(tvSeriesFull);
    }

    @Override
    public List<TvSeriesFull> getTvSeriesByFlag(boolean flag) {
        return localTvSeriesFullDataSource.getTvSeriesFullByFlag(flag);
    }
}
