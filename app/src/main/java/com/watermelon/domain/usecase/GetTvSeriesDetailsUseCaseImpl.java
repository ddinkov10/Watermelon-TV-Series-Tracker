package com.watermelon.domain.usecase;

import com.watermelon.domain.repository.TvSeriesFullRepository;

public class GetTvSeriesDetailsUseCaseImpl implements GetTvSeriesDetailsUseCase {

    private final TvSeriesFullRepository tvSeriesFullRepository;

    public GetTvSeriesDetailsUseCaseImpl(TvSeriesFullRepository tvSeriesFullRepository) {
        this.tvSeriesFullRepository = tvSeriesFullRepository;
    }

    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {
        return new ResponseValue(tvSeriesFullRepository.getTvSeriesFullById(requestValues.getId()));
    }
}
