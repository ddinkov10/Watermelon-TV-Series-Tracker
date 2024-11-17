package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.domain.common.UseCase;

public interface SaveTvSeriesDetailsUseCase extends UseCase<SaveTvSeriesDetailsUseCase.RequestValues, SaveTvSeriesDetailsUseCase.ResponseValue> {
    @Override
    SaveTvSeriesDetailsUseCase.ResponseValue executeUseCase(SaveTvSeriesDetailsUseCase.RequestValues requestValues);

    final class RequestValues implements UseCase.RequestValues {
        private final TvSeriesFull tvSeriesFull;

        public RequestValues(TvSeriesFull tvSeriesFull) {
            this.tvSeriesFull = tvSeriesFull;
        }

        public TvSeriesFull getTvSeriesFull() {
            return tvSeriesFull;
        }
    }

    final class ResponseValue implements UseCase.ResponseValue {

    }
}
