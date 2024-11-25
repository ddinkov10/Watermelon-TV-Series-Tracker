package com.watermelon.Repository.TvSeriesFullRepository.datasource;

import com.watermelon.Models.TvSeriesFull;

import java.util.List;

public interface LocalTvSeriesFullDataSource {

    TvSeriesFull getTvSeriesFullById(int id);

    void saveTvSeriesFull(TvSeriesFull tvSeriesFull);

    List<TvSeriesFull> getLocalData();

    List<TvSeriesFull> getTvSeriesFullByFlag(boolean flag);


}
