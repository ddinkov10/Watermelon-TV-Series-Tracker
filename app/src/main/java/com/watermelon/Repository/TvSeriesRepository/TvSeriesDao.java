package com.watermelon.Repository.TvSeriesRepository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.watermelon.Models.TvSeries;
import com.watermelon.Models.TvSeriesFull;

import java.util.List;

@Dao
public interface TvSeriesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTvSeries(TvSeries tvSeries);

    @Query("UPDATE tv_series_table SET tv_series_name=:name, tv_series_status=:status, tv_series_start_date=:startDate, tv_series_end_date=:endDate, tv_series_country=:country, tv_series_network=:network, tv_series_image_path=:imagePath WHERE tv_series_api_id IN(:id)")
    void updateTvSeries(int id, String name, String status, String startDate, String endDate, String country, String network, String imagePath);

    @Query("DELETE FROM tv_series_table")
    void deleteAllTvSeries();

    @Query("DELETE FROM tv_series_table WHERE tv_series_api_id IN (:id)")
    void deleteTvSeriesById(int id);

    @Query("UPDATE tv_series_table SET tv_series_description=:description, tv_series_runtime=:runtime, tv_series_youtube_link=:youtubeLink, tv_series_rating=:rating WHERE tv_series_api_id IN(:tvSeriesId)")
    void updateTvSeriesDetails(int tvSeriesId, String description, String runtime, String youtubeLink, String rating);

    @Query("UPDATE tv_series_table SET tv_series_flag=:watchingId WHERE tv_series_api_id IN(:id)")
    void updateTvSeriesWatchedFlag(int id, boolean watchingId);


    @Query("SELECT * FROM tv_series_table WHERE tv_series_id=:Id")
    TvSeries getTvSeriesById(int Id);

    @Transaction
    @Query("SELECT * FROM tv_series_table WHERE tv_series_api_id=:Id")
    TvSeriesFull getTvSeriesByApiId(int Id);

    @Query("SELECT tv_series_api_id FROM tv_series_table WHERE tv_series_api_id IN(:ids)")
    List<Integer> getTvSeriesIfExists(List<Integer> ids);
}
