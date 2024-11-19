package com.watermelon.Repository.TvSeriesFullRepository.datasource;


import com.watermelon.Repository.model.SeriesWithAllDetails;

public interface RemoteTvSeriesFullDataSource {
    SeriesWithAllDetails fetchTvSeriesDetails(int id);
}
