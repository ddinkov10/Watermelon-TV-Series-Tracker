package com.watermelon.Repository.SeriesRepository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.watermelon.Models.TvSeries;
import com.watermelon.Models.TvSeriesFull;
import com.watermelon.Repository.model.Episode;
import com.watermelon.Repository.model.Genre;
import com.watermelon.Repository.model.Image;
import com.watermelon.Repository.model.Series;
import com.watermelon.Repository.model.SeriesGenreLink;
import com.watermelon.Repository.model.SeriesWithAllDetails;

import java.util.List;

@Dao
public interface SeriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSeries(Series series);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEpisodes(List<Episode> episodes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGenres(List<Genre> genres);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImages(List<Image> images);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSeriesGenreLinks(List<SeriesGenreLink> links);


    @Update
    void updateSeries(Series series);

    @Query("UPDATE series SET title=:name, status=:status, startDate=:startDate, endDate=:endDate, country=:country, network=:network, imagePath=:imagePath WHERE id IN(:id)")
    void updateSeries(int id, String name, String status, String startDate, String endDate, String country, String network, String imagePath);

    @Query("UPDATE series SET description=:description, runtime=:runtime, youtubeLink=:youtubeLink, rating=:rating WHERE apiId IN(:tvSeriesId)")
    void updateSeriesDetails(int tvSeriesId, String description, String runtime, String youtubeLink, String rating);

    @Query("UPDATE series SET lastUpdated=:lastUpdated WHERE id=:seriesId")
    void updateSeriesLastUpdated(int seriesId, long lastUpdated);

    @Transaction
    @Query("SELECT * FROM series WHERE id=:seriesId")
    SeriesWithAllDetails getSeriesWithAllDetailsById(int seriesId);

    @Query("SELECT * FROM series WHERE id=:seriesId")
    Series getSeriesById(int seriesId);

    @Query("SELECT * FROM series WHERE apiId=:seriesApiId")
    Series getSeriesByApiId(int seriesApiId);

    @Query("SELECT id FROM series WHERE id IN (:seriesIds)")
    List<Integer> getSeriesIfExists(List<Integer> seriesIds);

    @Query("SELECT * FROM series")
    List<Series> getAllSeries();

    @Transaction
    @Query("SELECT * FROM Genre WHERE id IN (SELECT genreId FROM SeriesGenreLink WHERE seriesId = :seriesId)")
    List<Genre> getGenresForSeries(int seriesId);

    // Fetch Episodes for a Series
    @Transaction
    @Query("SELECT * FROM Episode WHERE seriesId = :seriesId")
    List<Episode> getEpisodesForSeries(int seriesId);

    @Query("SELECT MAX(airDate) FROM episode WHERE seriesId=:id")
    String getDateForTheLastEpisodeOfTvSeriesAired(int id);

    // Fetch Images for a Series
    @Transaction
    @Query("SELECT * FROM Image WHERE seriesId = :seriesId")
    List<Image> getImagesForSeries(int seriesId);



    @Query("DELETE FROM series")
    void deleteAllSeries();

    @Query("DELETE FROM series WHERE id IN (:id)")
    void deleteSeriesById(int id);

//    @Query("UPDATE tv_series_table SET tv_series_flag=:watchingId WHERE tv_series_api_id IN(:id)")
//    void updateTvSeriesWatchedFlag(int id, boolean watchingId);


//    @Query("UPDATE tv_series_table SET tv_series_flag=1 WHERE tv_series_api_id IN(:id)")
//    void addTvSeriesToWatchlist(int id);
//
//    @Query("UPDATE tv_series_table SET tv_series_flag=0 WHERE tv_series_api_id IN(:id)")
//    void removeTvSeriesFromWatchlist(int id);



//    @Transaction
//    @Query("SELECT * FROM tv_series_table WHERE tv_series_api_id=:Id")
//    TvSeriesFull getTvSeriesByApiId(int Id);

//    @Query("SELECT tv_series_api_id FROM tv_series_table WHERE tv_series_api_id IN(:ids)")
//    List<Integer> getTvSeriesIfExists(List<Integer> ids);



}
