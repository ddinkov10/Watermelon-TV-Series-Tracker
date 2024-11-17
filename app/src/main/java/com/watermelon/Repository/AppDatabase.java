package com.watermelon.Repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.watermelon.Models.TvSeries;
import com.watermelon.Models.TvSeriesEpisode;
import com.watermelon.Models.TvSeriesGenre;
import com.watermelon.Models.TvSeriesPicture;
import com.watermelon.Repository.CalendarRepository.CalendarDao;
import com.watermelon.Repository.DiscoverRepository.DiscoverDao;
import com.watermelon.Repository.StatisticsRepository.StatisticsDao;
import com.watermelon.Repository.TvSeriesEpisodeRepository.dao.TvSeriesEpisodeDao;
import com.watermelon.Repository.TvSeriesFullRepository.dao.TvSeriesFullDao;
import com.watermelon.Repository.TvSeriesGenre.TvSeriesGenreDao;
import com.watermelon.Repository.TvSeriesPicturesRepository.TvSeriesPicturesDao;
import com.watermelon.Repository.TvSeriesRepository.TvSeriesDao;


@Database(entities = {TvSeries.class, TvSeriesEpisode.class, TvSeriesPicture.class, TvSeriesGenre.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    public abstract TvSeriesDao getTvSeriesDao();
    public abstract TvSeriesFullDao getTvSeriesFullDao();

    public abstract TvSeriesEpisodeDao getTvSeriesEpisodeDao();
    public abstract TvSeriesGenreDao getTvSeriesGenreDao();

    public abstract TvSeriesPicturesDao getTvSeriesPicturesDao();

    public abstract DiscoverDao getDiscoverDao();

    public abstract StatisticsDao getStatisticsDao();
    public abstract CalendarDao getCalendarDao();

//    private static AppDatabase instance;
//    public abstract AppDao appDao();

//    public static synchronized AppDatabase getInstance(Context context) {
//        if (instance == null) {
//            instance = Room.databaseBuilder(context.getApplicationContext(),
//                    AppDatabase.class, "watermelon_db")
//                    .fallbackToDestructiveMigration()
//                    .build();
//
//        }
//        return instance;
//    }
}
