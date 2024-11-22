package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeries;
import com.watermelon.domain.repository.TvSeriesRepository;

import java.util.List;

public class GetAllTvSeriesUseCaseImpl implements GetAllTvSeriesUseCase {

    private final TvSeriesRepository tvSeriesRepository;

    public GetAllTvSeriesUseCaseImpl(TvSeriesRepository tvSeriesRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
    }

    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {
        List<TvSeries> tvSeriesList = tvSeriesRepository.getAllTvSeries();
        return new ResponseValue(tvSeriesList);
    }
}
