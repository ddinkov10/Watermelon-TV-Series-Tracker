package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.domain.repository.TvSeriesFullRepository;

public class FetchTvSeriesDetailsUseCaseImpl implements FetchTvSeriesDetailsUseCase {

    private final TvSeriesFullRepository tvSeriesFullRepository;

    public FetchTvSeriesDetailsUseCaseImpl (TvSeriesFullRepository tvSeriesFullRepository) {
        this.tvSeriesFullRepository = tvSeriesFullRepository;
    }

    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {
        TvSeriesFull tvSeriesFull = tvSeriesFullRepository.fetchTvSeriesDetails(requestValues.getId());
        return new ResponseValue(tvSeriesFull);
    }
}
