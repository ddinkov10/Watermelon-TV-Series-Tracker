package com.watermelon.domain.usecase;

import android.util.Pair;

import com.watermelon.UI.framework.common.UseCase;

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
