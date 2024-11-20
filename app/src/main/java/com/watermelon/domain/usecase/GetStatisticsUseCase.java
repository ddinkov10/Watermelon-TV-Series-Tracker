package com.watermelon.domain.usecase;

import com.watermelon.UI.framework.common.UseCase;

import java.util.List;

public interface GetStatisticsUseCase extends UseCase<GetStatisticsUseCase.RequestValues, GetStatisticsUseCase.ResponseValue> {

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

        private final List<String> statistics;

        public ResponseValue(List<String> statistics) {
            this.statistics = statistics;
        }

        public List<String> getStatistics() {
            return statistics;
        }
    }
}