package com.watermelon.presentation.Repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.watermelon.presentation.Models.TvSeries;
import com.watermelon.presentation.Models.TvSeriesEpisode;
import com.watermelon.presentation.Models.TvSeriesGenre;
import com.watermelon.presentation.Models.TvSeriesPicture;


@Database(entities = {TvSeries.class, TvSeriesEpisode.class, TvSeriesPicture.class, TvSeriesGenre.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    public abstract AppDao appDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "watermelon_db")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }
}
