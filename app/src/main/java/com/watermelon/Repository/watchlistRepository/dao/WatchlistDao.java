package com.watermelon.Repository.watchlistRepository.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.watermelon.Repository.model.watchlist.WatchlistMovieLink;
import com.watermelon.Repository.model.watchlist.WatchlistSeriesLink;
import com.watermelon.Repository.model.watchlist.WatchlistWithDetails;
import com.watermelon.Repository.model.watchlist.WatchlistWithSeries;

import java.util.List;

@Dao
public interface WatchlistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSeriesToWatchlist(WatchlistSeriesLink watchlistSeriesLink);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMovieToWatchlist(WatchlistMovieLink watchlistMovieLink);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSeriesToWatchlist(List<WatchlistSeriesLink> watchlistSeriesLinks);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMoviesToWatchlist(List<WatchlistMovieLink> watchlistMovieLinks);

//     WHERE id = :watchlistId AND profileId=:profileId

    @Transaction
    @Query("SELECT * FROM UserWatchlist")
    WatchlistWithDetails getWatchlistWithDetails();

    @Transaction
    @Query("SELECT * FROM UserWatchlist WHERE profileId=:profileId")
    List<WatchlistWithDetails> getAllWatchlistWithDetails(int profileId);

    @Delete
    void removeSeriesFromWatchlist(WatchlistSeriesLink watchlistSeriesLink);

    @Delete
    void removeMovieFromWatchlist(WatchlistMovieLink watchlistMovieLink);

}
