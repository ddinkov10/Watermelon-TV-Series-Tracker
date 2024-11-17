package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.domain.repository.TvSeriesFullRepository;

public class FetchAndSaveTvSeriesDetailsUseCaseImpl implements FetchAndSaveTvSeriesDetailsUseCase {

    private final FetchTvSeriesDetailsUseCase fetchTvSeriesDetailsUseCase;
    private final SaveTvSeriesDetailsUseCase saveTvSeriesDetailsUseCase;

    public FetchAndSaveTvSeriesDetailsUseCaseImpl (FetchTvSeriesDetailsUseCase fetchTvSeriesDetailsUseCase, SaveTvSeriesDetailsUseCase saveTvSeriesDetailsUseCase) {
        this.fetchTvSeriesDetailsUseCase = fetchTvSeriesDetailsUseCase;
        this.saveTvSeriesDetailsUseCase = saveTvSeriesDetailsUseCase;
    }

    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {
        TvSeriesFull tvSeriesFull = fetchTvSeriesDetailsUseCase.executeUseCase(new FetchTvSeriesDetailsUseCase.RequestValues(requestValues.getId())).getTvSeriesFull();
        saveTvSeriesDetailsUseCase.executeUseCase(new SaveTvSeriesDetailsUseCase.RequestValues(tvSeriesFull));
        return null;
    }
}
