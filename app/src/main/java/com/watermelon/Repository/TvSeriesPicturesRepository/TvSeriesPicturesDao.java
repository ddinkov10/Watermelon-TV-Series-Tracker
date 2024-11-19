package com.watermelon.Repository.TvSeriesPicturesRepository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.watermelon.Models.TvSeriesPicture;
import com.watermelon.Repository.model.Image;

import java.util.List;

@Dao
public interface TvSeriesPicturesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertImages(List<Image> images);
}
