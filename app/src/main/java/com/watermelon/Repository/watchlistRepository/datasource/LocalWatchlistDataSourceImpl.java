package com.watermelon.Repository.watchlistRepository.datasource;

import com.watermelon.Repository.AppDatabase;
import com.watermelon.Repository.model.watchlist.WatchlistWithDetails;
import com.watermelon.Repository.watchlistRepository.datasource.LocalWatchlistDataSource;

public class LocalWatchlistDataSourceImpl implements LocalWatchlistDataSource {

    public AppDatabase appDatabase;

    public LocalWatchlistDataSourceImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public WatchlistWithDetails getWatchlist() {
        return appDatabase.getWatchlistDao().getWatchlistWithDetails();
    }
}
