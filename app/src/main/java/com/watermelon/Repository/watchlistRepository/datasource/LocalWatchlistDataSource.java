package com.watermelon.Repository.watchlistRepository.datasource;

import com.watermelon.Repository.model.watchlist.WatchlistWithDetails;

public interface LocalWatchlistDataSource {

    WatchlistWithDetails getWatchlist();
}
