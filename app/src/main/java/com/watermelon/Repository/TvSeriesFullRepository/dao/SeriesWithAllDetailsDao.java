package com.watermelon.Repository.TvSeriesFullRepository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.watermelon.Models.TvSeriesFull;

import java.util.List;

@Dao
public interface SeriesWithAllDetailsDao {

    @Transaction
    @Query("SELECT * FROM series WHERE id=:id")
    LiveData<SeriesWithAllDetailsDao> getTvSeriesFullByIdLiveData(int id);

    @Transaction
    @Query("SELECT * FROM tv_series_table WHERE tv_series_api_id=:id")
    TvSeriesFull getTvSeriesFullById(int id);

    @Query("SELECT * FROM tv_series_table ")
    List<TvSeriesFull> getWatchlistTvSeriesFull();

    @Query("SELECT* FROM tv_series_table WHERE tv_series_flag=:flag")
    List<TvSeriesFull> getTvSeriesFullByFlag(boolean flag);
}
