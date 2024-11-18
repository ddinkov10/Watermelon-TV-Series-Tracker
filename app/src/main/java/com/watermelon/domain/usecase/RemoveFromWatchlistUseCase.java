package com.watermelon.domain.usecase;

import com.watermelon.UI.framework.common.UseCase;

public interface RemoveFromWatchlistUseCase extends UseCase<RemoveFromWatchlistUseCase.RequestValues, RemoveFromWatchlistUseCase.ResponseValue> {

    @Override
    RemoveFromWatchlistUseCase.ResponseValue executeUseCase(RemoveFromWatchlistUseCase.RequestValues requestValues);

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
