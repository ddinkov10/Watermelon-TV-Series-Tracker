package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeriesCalendarEpisode;
import com.watermelon.UI.framework.common.UseCase;

import java.util.List;

public interface GetCalendarListUseCase extends UseCase<GetCalendarListUseCase.RequestValues, GetCalendarListUseCase.ResponseValue> {

    @Override
    GetCalendarListUseCase.ResponseValue executeUseCase(GetCalendarListUseCase.RequestValues requestValues);

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

        private final List<TvSeriesCalendarEpisode> calendarEpisodeList;

        public ResponseValue(List<TvSeriesCalendarEpisode> calendarEpisodeList) {
            this.calendarEpisodeList = calendarEpisodeList;
        }

        public List<TvSeriesCalendarEpisode> getCalendarEpisodeList() {
            return calendarEpisodeList;
        }
    }

}
