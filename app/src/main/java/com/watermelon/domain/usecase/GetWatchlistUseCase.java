package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.Repository.model.SeriesWithAllDetails;
import com.watermelon.Repository.model.watchlist.WatchlistWithDetails;
import com.watermelon.UI.framework.common.UseCase;

import java.util.List;

public interface GetWatchlistUseCase extends UseCase<GetWatchlistUseCase.RequestValues, GetWatchlistUseCase.ResponseValue> {

    @Override
    ResponseValue executeUseCase(RequestValues requestValues);

    final class RequestValues implements UseCase.RequestValues {

    }

    final class ResponseValue implements UseCase.ResponseValue {

        private final WatchlistWithDetails watchlistList;

        public ResponseValue(WatchlistWithDetails watchlistList) {
            this.watchlistList = watchlistList;
        }

        public WatchlistWithDetails getWatchlistList() {
            return watchlistList;
        }
    }
}
