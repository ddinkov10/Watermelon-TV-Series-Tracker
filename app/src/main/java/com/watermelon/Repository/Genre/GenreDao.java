package com.watermelon.Repository.Genre;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.watermelon.Models.TvSeriesGenre;
import com.watermelon.Repository.model.Genre;

import java.util.List;

@Dao
public interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGenres(List<Genre> genres);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertGenre(Genre genre);

    @Query("SELECT * FROM genre WHERE name=:name")
    Genre getGenreByName(String name);

    @Query("SELECT * FROM genre WHERE id=:genreId")
    Genre getGenreById(int genreId);

}
