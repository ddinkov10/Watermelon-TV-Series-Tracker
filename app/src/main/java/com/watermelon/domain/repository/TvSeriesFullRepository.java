package com.watermelon.domain.repository;

import com.watermelon.Models.TvSeriesFull;

import java.util.List;

public interface TvSeriesFullRepository {

    List<TvSeriesFull> getTvSeriesByFlag(boolean flag);

    List<TvSeriesFull> getWatchlistListObservable();

    TvSeriesFull getTvSeriesFullById(int id);

    TvSeriesFull fetchTvSeriesDetails(int id);

    void saveTvSeriesDetails(TvSeriesFull tvSeriesFull);

}
