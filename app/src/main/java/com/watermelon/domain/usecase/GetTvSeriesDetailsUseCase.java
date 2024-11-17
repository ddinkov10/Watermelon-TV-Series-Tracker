package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.domain.common.UseCase;

public interface GetTvSeriesDetailsUseCase extends UseCase<GetTvSeriesDetailsUseCase.RequestValues, GetTvSeriesDetailsUseCase.ResponseValue> {

    @Override
    GetTvSeriesDetailsUseCase.ResponseValue executeUseCase(GetTvSeriesDetailsUseCase.RequestValues requestValues);

    final class RequestValues implements UseCase.RequestValues {
        private final int id;

        public RequestValues(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    final class ResponseValue implements UseCase.ResponseValue {
        private final TvSeriesFull tvSeriesFull;

        public ResponseValue(TvSeriesFull tvSeriesFull) {
            this.tvSeriesFull = tvSeriesFull;
        }

        public TvSeriesFull getTvSeriesFull() {
            return tvSeriesFull;
        }
    }

}
