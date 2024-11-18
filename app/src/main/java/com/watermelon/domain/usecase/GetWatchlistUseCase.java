package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.UI.framework.common.UseCase;

import java.util.List;

public interface GetWatchlistUseCase extends UseCase<GetWatchlistUseCase.RequestValues, GetWatchlistUseCase.ResponseValue> {

    @Override
    ResponseValue executeUseCase(RequestValues requestValues);

    final class RequestValues implements UseCase.RequestValues {

        private final boolean flag;

        public RequestValues(boolean flag) {
            this.flag = flag;
        }

        public boolean getFlag() {
            return flag;
        }
    }

    final class ResponseValue implements UseCase.ResponseValue {

        private final List<TvSeriesFull> watchlistList;

        public ResponseValue(List<TvSeriesFull> watchlistList) {
            this.watchlistList = watchlistList;
        }

        public List<TvSeriesFull> getWatchlistList() {
            return watchlistList;
        }
    }
}
