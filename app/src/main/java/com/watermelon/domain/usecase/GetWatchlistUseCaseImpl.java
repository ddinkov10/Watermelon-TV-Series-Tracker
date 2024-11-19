package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.Repository.model.watchlist.WatchlistWithDetails;
import com.watermelon.domain.repository.TvSeriesFullRepository;
import com.watermelon.domain.repository.WatchlistRepository;

import java.util.List;

public class GetWatchlistUseCaseImpl implements GetWatchlistUseCase{

    private final WatchlistRepository watchlistRepository;

    public GetWatchlistUseCaseImpl (WatchlistRepository watchlistRepository) {
        this.watchlistRepository = watchlistRepository;
    }
    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {
        WatchlistWithDetails result = watchlistRepository.getWatchlist();
        return new ResponseValue(result);
    }
}
