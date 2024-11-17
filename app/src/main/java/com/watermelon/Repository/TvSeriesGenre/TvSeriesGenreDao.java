package com.watermelon.Repository.TvSeriesGenre;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.watermelon.Models.TvSeriesGenre;

import java.util.List;

@Dao
public interface TvSeriesGenreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllTvSeriesGenres(List<TvSeriesGenre> genres);
}
