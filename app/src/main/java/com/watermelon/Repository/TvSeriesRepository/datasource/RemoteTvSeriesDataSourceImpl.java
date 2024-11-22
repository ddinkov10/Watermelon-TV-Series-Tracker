package com.watermelon.Repository.TvSeriesRepository.datasource;

import com.watermelon.Models.TvSeries;
import com.watermelon.Repository.Api.ApiModels.JsonTvSeriesSearchRoot;
import com.watermelon.Repository.Api.ApiModels.TvSeriesBasicInfo.JsonTvSeries;
import com.watermelon.Repository.Api.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RemoteTvSeriesDataSourceImpl implements RemoteTvSeriesDataSource{
    private final ApiService apiService;

    public RemoteTvSeriesDataSourceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public List<TvSeries> searchTvSeries(String searchWord) {
        Call<JsonTvSeriesSearchRoot> request = apiService.getTvSeriesSearch(searchWord, 1);
        try {
            Response<JsonTvSeriesSearchRoot> result = request.execute();
            JsonTvSeriesSearchRoot jsonTvSeriesSearchRoot = result.body();

            List<TvSeries> searchedTvSeries = mapResponseToTvSeries(jsonTvSeriesSearchRoot);
            return searchedTvSeries;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<TvSeries> mapResponseToTvSeries(JsonTvSeriesSearchRoot jsonRoot) {
        List<TvSeries> tvSeriesList = new ArrayList<>();
        for (JsonTvSeries jsonTvSeries : jsonRoot.getTvShows()) {
            TvSeries tvSeries = new TvSeries(
                    jsonTvSeries.getId(),
                    jsonTvSeries.getName(),
                    jsonTvSeries.getStartDate(),
                    jsonTvSeries.getEndDate(),
                    jsonTvSeries.getCountry(),
                    jsonTvSeries.getNetwork(),
                    jsonTvSeries.getStatus(),
                    jsonTvSeries.getImageThumbnailPath()
            );
            tvSeriesList.add(tvSeries);
        }
        return tvSeriesList;
    }
}
