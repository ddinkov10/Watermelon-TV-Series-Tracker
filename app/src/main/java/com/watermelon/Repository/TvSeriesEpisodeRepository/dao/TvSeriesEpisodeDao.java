package com.watermelon.Repository.TvSeriesEpisodeRepository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.watermelon.Models.TvSeriesEpisode;
import com.watermelon.Repository.model.Episode;

import java.util.List;

@Dao
public interface TvSeriesEpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEpisode(Episode episode);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertEpisodes(List<Episode> episodes);

    @Query("UPDATE tv_series_episode_table SET episode_is_watched=:isWatched WHERE id IN (:id)")
    void updateTvSeriesEpisodeWatchedFlag(int id, boolean isWatched);

    @Query("UPDATE tv_series_episode_table SET episode_is_watched=:flag WHERE id IN (:ids)")
    void updateTvSeriesAllSeasonWatched(List<Integer> ids, boolean flag);

    @Query("SELECT * FROM tv_series_episode_table WHERE episode_tv_series_id IN (:tvSeriesId) AND episode_season IN(:seasonNum)")
    List<TvSeriesEpisode> getTvSeriesEpisodesByIdAndSeasonNum(int tvSeriesId, int seasonNum);

    @Query("SELECT MAX(episode_air_date) FROM tv_series_episode_table WHERE episode_tv_series_id=:id")
    String getDateForTheLastEpisodeOfTvSeriesAired(int id);
}
