package com.watermelon.domain.usecase;

import com.watermelon.domain.repository.TvSeriesRepository;

public class RemoveFromWatchlistUseCaseImpl implements RemoveFromWatchlistUseCase {

    private final TvSeriesRepository tvSeriesRepository;

    public RemoveFromWatchlistUseCaseImpl(TvSeriesRepository tvSeriesRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
    }

    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {
        int id = requestValues.getId();
        tvSeriesRepository.removeFromWatchlist(id);
        return null;
    }
}
