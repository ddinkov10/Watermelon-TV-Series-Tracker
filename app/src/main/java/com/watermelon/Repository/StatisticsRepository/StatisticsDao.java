package com.watermelon.Repository.StatisticsRepository;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.watermelon.Models.TvSeriesFull;

import java.util.List;

@Dao
public interface StatisticsDao {
    @Transaction
    @Query("SELECT * FROM tv_series_table WHERE tv_series_flag=:flag")
    List<TvSeriesFull> getStatisticsTvSeriesFull(boolean flag);

}
