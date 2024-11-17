package com.watermelon.Repository.DiscoverRepository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.watermelon.Models.TvSeries;

import java.util.List;

@Dao
public interface DiscoverDao {
    @Query("SELECT * FROM tv_series_table")
    LiveData<List<TvSeries>> getDiscoverTvSeries();
}
