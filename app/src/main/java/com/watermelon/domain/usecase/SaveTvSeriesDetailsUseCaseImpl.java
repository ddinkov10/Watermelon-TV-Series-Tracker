package com.watermelon.domain.usecase;

import com.watermelon.domain.repository.TvSeriesFullRepository;

public class SaveTvSeriesDetailsUseCaseImpl implements SaveTvSeriesDetailsUseCase{

    private final TvSeriesFullRepository tvSeriesFullRepository;

    public SaveTvSeriesDetailsUseCaseImpl(TvSeriesFullRepository tvSeriesFullRepository) {
        this.tvSeriesFullRepository = tvSeriesFullRepository;
    }

    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {
        tvSeriesFullRepository.saveTvSeriesDetails(requestValues.getTvSeriesFull());
        return null;
    }
}
