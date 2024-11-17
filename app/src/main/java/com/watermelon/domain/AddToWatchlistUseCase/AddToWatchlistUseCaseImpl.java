package com.watermelon.domain.AddToWatchlistUseCase;

import com.watermelon.domain.repository.TvSeriesRepository;

public class AddToWatchlistUseCaseImpl implements AddToWatchlistUseCase {

    private final TvSeriesRepository tvSeriesRepository;

    public AddToWatchlistUseCaseImpl(TvSeriesRepository tvSeriesRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
    }

    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {
        int id = requestValues.getId();
        tvSeriesRepository.addToWatchlist(id);
        return null;
    }
}
