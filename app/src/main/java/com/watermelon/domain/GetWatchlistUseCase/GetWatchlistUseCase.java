package com.watermelon.domain.GetWatchlistUseCase;

import androidx.lifecycle.LiveData;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.domain.common.UseCase;

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
