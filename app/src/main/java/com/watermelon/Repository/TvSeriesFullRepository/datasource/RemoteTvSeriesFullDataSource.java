package com.watermelon.Repository.TvSeriesFullRepository.datasource;


import com.watermelon.Models.TvSeriesFull;

public interface RemoteTvSeriesFullDataSource {
    TvSeriesFull fetchTvSeriesDetails(int id);
}
