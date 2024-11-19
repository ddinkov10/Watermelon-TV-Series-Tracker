package com.watermelon.Repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.watermelon.Repository.SeriesRepository.dao.SeriesDao;
import com.watermelon.Repository.model.Episode;
import com.watermelon.Repository.model.Genre;
import com.watermelon.Repository.model.Image;
import com.watermelon.Repository.model.Series;
import com.watermelon.Repository.model.SeriesGenreLink;
import com.watermelon.Repository.model.userProfile.UserProfile;
import com.watermelon.Repository.model.watchlist.UserWatchlist;
import com.watermelon.Repository.model.watchlist.WatchlistMovieLink;
import com.watermelon.Repository.model.watchlist.WatchlistSeriesLink;
import com.watermelon.Repository.watchlistRepository.dao.WatchlistDao;


@Database(entities = {Series.class, Episode.class, Image.class, Genre.class, SeriesGenreLink.class, UserProfile.class, UserWatchlist.class, WatchlistMovieLink.class, WatchlistSeriesLink.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    public abstract SeriesDao getSeriesDao();
    public abstract WatchlistDao getWatchlistDao();
//    public abstract SeriesWithAllDetailsDao getTvSeriesFullDao();

//    public abstract TvSeriesEpisodeDao getTvSeriesEpisodeDao();
//    public abstract TvSeriesGenreDao getTvSeriesGenreDao();

//    public abstract TvSeriesPicturesDao getTvSeriesPicturesDao();

//    public abstract DiscoverDao getDiscoverDao();

//    public abstract StatisticsDao getStatisticsDao();
//    public abstract CalendarDao getCalendarDao();

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
