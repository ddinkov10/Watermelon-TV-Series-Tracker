package com.watermelon.Repository.TvSeriesFullRepository.datasource;

import com.watermelon.Helpers.TvSeriesHelper;
import com.watermelon.Repository.Api.ApiModels.TvSeriesDetails.JsonTvSeriesFullRoot;
import com.watermelon.Repository.Api.ApiService;
import com.watermelon.Repository.model.SeriesWithAllDetails;

import retrofit2.Call;
import retrofit2.Response;

public class RemoteTvSeriesFullDataSourceImpl implements RemoteTvSeriesFullDataSource
{
    private  final ApiService apiService;

    public RemoteTvSeriesFullDataSourceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public SeriesWithAllDetails fetchTvSeriesDetails(int id) {
        Call<JsonTvSeriesFullRoot> request = apiService.getTvSeriesDetailed(id);
        try {
            Response<JsonTvSeriesFullRoot> result = request.execute();
            JsonTvSeriesFullRoot jsonTvSeriesFullRoot = result.body();
//            detailsToDb(jsonTvSeriesFullRoot);
            return TvSeriesHelper.jsonToModel(jsonTvSeriesFullRoot);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
