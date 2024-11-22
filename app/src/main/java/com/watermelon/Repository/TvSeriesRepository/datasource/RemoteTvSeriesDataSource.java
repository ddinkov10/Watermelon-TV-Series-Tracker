package com.watermelon.Repository.TvSeriesRepository.datasource;

import com.watermelon.Models.TvSeries;

import java.util.List;

public interface RemoteTvSeriesDataSource {
    List<TvSeries> searchTvSeries(String searchWord);
}
