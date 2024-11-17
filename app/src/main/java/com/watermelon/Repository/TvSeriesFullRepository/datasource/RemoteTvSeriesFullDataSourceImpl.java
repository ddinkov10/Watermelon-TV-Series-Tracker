package com.watermelon.Repository.TvSeriesFullRepository.datasource;

import android.util.Log;

import com.watermelon.Helpers.DateHelper;
import com.watermelon.Helpers.TvSeriesHelper;
import com.watermelon.Models.TvSeries;
import com.watermelon.Models.TvSeriesEpisode;
import com.watermelon.Models.TvSeriesFull;
import com.watermelon.Models.TvSeriesGenre;
import com.watermelon.Models.TvSeriesPicture;
import com.watermelon.Repository.Api.ApiModels.TvSeriesDetails.JsonTvSeriesFullRoot;
import com.watermelon.Repository.Api.ApiService;
import com.watermelon.Repository.AppDatabase;
import com.watermelon.Repository.TvSeriesEpisodeRepository.dao.TvSeriesEpisodeDao;
import com.watermelon.Repository.TvSeriesGenre.TvSeriesGenreDao;
import com.watermelon.Repository.TvSeriesPicturesRepository.TvSeriesPicturesDao;
import com.watermelon.Repository.TvSeriesRepository.TvSeriesDao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteTvSeriesFullDataSourceImpl implements RemoteTvSeriesFullDataSource
{
    private  final ApiService apiService;

    public RemoteTvSeriesFullDataSourceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public TvSeriesFull fetchTvSeriesDetails(int id) {
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
