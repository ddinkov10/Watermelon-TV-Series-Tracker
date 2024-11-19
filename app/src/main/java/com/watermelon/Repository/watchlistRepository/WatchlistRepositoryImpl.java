package com.watermelon.Repository.watchlistRepository;

import com.watermelon.Repository.model.watchlist.WatchlistWithDetails;
import com.watermelon.Repository.watchlistRepository.datasource.LocalWatchlistDataSource;
import com.watermelon.domain.repository.WatchlistRepository;

public class WatchlistRepositoryImpl implements WatchlistRepository {

    private final LocalWatchlistDataSource localWatchlistDataSource;

    public WatchlistRepositoryImpl(LocalWatchlistDataSource localWatchlistDataSource) {
        this.localWatchlistDataSource = localWatchlistDataSource;
    }

    @Override
    public WatchlistWithDetails getWatchlist() {
        return localWatchlistDataSource.getWatchlist();
    }
}
