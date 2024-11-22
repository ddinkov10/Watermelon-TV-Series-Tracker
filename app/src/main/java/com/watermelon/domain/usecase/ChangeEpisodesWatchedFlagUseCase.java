package com.watermelon.domain.usecase;

import android.util.Pair;

import com.watermelon.UI.framework.common.UseCase;

import java.util.List;

public interface ChangeEpisodesWatchedFlagUseCase extends UseCase<ChangeEpisodesWatchedFlagUseCase.RequestValues, ChangeEpisodesWatchedFlagUseCase.ResponseValue> {

    @Override
    ChangeEpisodesWatchedFlagUseCase.ResponseValue executeUseCase(ChangeEpisodesWatchedFlagUseCase.RequestValues requestValues);

    final class RequestValues implements UseCase.RequestValues {
        private final Pair<List<Integer>, Boolean> pair;

        public RequestValues( Pair<List<Integer>, Boolean> pair) {
            this.pair = pair;
        }

        public  Pair<List<Integer>, Boolean> getPair() {
            return pair;
        }
    }

    final class ResponseValue implements UseCase.ResponseValue {

    }
}
