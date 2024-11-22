package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeries;
import com.watermelon.domain.repository.TvSeriesRepository;

import java.util.List;

public class SearchTvSeriesUseCaseImpl implements SearchTvSeriesUseCase {

    private final TvSeriesRepository tvSeriesRepository;

    public SearchTvSeriesUseCaseImpl(TvSeriesRepository tvSeriesRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
    }

    @Override
    public SearchTvSeriesUseCase.ResponseValue executeUseCase(SearchTvSeriesUseCase.RequestValues requestValues) {
        List<TvSeries> tvSeriesList = tvSeriesRepository.searchTvSeries(requestValues.getSearchWord());
        return new ResponseValue(tvSeriesList);
    }
}
