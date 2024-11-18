package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.domain.repository.TvSeriesFullRepository;

import java.util.List;

public class GetWatchlistUseCaseImpl implements GetWatchlistUseCase{

    private final TvSeriesFullRepository tvSeriesFullRepository;

    public GetWatchlistUseCaseImpl (TvSeriesFullRepository tvSeriesFullRepository) {
        this.tvSeriesFullRepository = tvSeriesFullRepository;
    }
    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {
        List<TvSeriesFull> result = tvSeriesFullRepository.getTvSeriesByFlag(requestValues.getFlag());
        return new ResponseValue(result);
    }
}
