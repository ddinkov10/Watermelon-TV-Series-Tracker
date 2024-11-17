package com.watermelon.domain.ChangeEpisodeWatchedFlagUseCase;

import android.util.Pair;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.domain.common.UseCase;

import java.util.List;

public interface ChangeEpisodeWatchedFlagUseCase extends UseCase<ChangeEpisodeWatchedFlagUseCase.RequestValues, ChangeEpisodeWatchedFlagUseCase.ResponseValue> {

    @Override
    ChangeEpisodeWatchedFlagUseCase.ResponseValue executeUseCase(ChangeEpisodeWatchedFlagUseCase.RequestValues requestValues);

    final class RequestValues implements UseCase.RequestValues {
        private final Pair<Integer, Boolean> pair;

        public RequestValues(Pair<Integer, Boolean> pair) {
            this.pair = pair;
        }

        public Pair<Integer, Boolean> getPair() {
            return pair;
        }
    }

    final class ResponseValue implements UseCase.ResponseValue {

    }
}
