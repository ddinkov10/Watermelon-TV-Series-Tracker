package com.watermelon.domain.usecase;

import com.watermelon.UI.framework.common.UseCase;

public interface FetchAndSaveTvSeriesDetailsUseCase extends UseCase<FetchAndSaveTvSeriesDetailsUseCase.RequestValues, FetchAndSaveTvSeriesDetailsUseCase.ResponseValue> {

    @Override
    FetchAndSaveTvSeriesDetailsUseCase.ResponseValue executeUseCase(FetchAndSaveTvSeriesDetailsUseCase.RequestValues requestValues);

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

    }
}
