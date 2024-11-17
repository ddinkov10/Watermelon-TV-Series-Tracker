package com.watermelon.Repository.CalendarRepository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.watermelon.Models.TvSeriesCalendarEpisode;

import java.util.List;

@Dao
public interface CalendarDao {
    @Transaction
    @Query("SELECT * FROM tv_series_table, tv_series_episode_table WHERE tv_series_flag=:flag AND tv_series_api_id = episode_tv_series_id AND date(episode_air_date)>=date('now') AND date(episode_air_date) <= date('now', '+14 days') ORDER BY date(episode_air_date) ASC")
    LiveData<List<TvSeriesCalendarEpisode>> getCalendarTvSeries(boolean flag);
}
