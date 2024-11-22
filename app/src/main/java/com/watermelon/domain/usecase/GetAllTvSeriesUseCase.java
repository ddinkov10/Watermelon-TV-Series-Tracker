package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeries;
import com.watermelon.UI.framework.common.UseCase;

import java.util.List;

public interface GetAllTvSeriesUseCase extends UseCase<GetAllTvSeriesUseCase.RequestValues, GetAllTvSeriesUseCase.ResponseValue> {

    @Override
    GetAllTvSeriesUseCase.ResponseValue executeUseCase(GetAllTvSeriesUseCase.RequestValues requestValues);

    final class RequestValues implements UseCase.RequestValues {
}

    final class ResponseValue implements UseCase.ResponseValue {
        private final List<TvSeries> tvSeriesList;

        public ResponseValue(List<TvSeries> tvSeriesList) {
            this.tvSeriesList = tvSeriesList;
        }

        public List<TvSeries> getTvSeriesList() {
            return tvSeriesList;
        }
    }
}
