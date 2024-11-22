package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeries;
import com.watermelon.UI.framework.common.UseCase;

import java.util.List;

public interface SearchTvSeriesUseCase extends UseCase<SearchTvSeriesUseCase.RequestValues, SearchTvSeriesUseCase.ResponseValue> {
    @Override
    SearchTvSeriesUseCase.ResponseValue executeUseCase(SearchTvSeriesUseCase.RequestValues requestValues);

    final class RequestValues implements UseCase.RequestValues {
        private final String searchWord;

        public RequestValues(String searchWord) {
            this.searchWord = searchWord;
        }

        public String getSearchWord() {
            return searchWord;
        }
    }

    final class ResponseValue implements UseCase.ResponseValue {

        private final List<TvSeries> tvSeriesList;

        public ResponseValue(List<TvSeries> tvSeriesList) {
            this.tvSeriesList = tvSeriesList;
        }

        public List<TvSeries> getTvSeriesList() {
            return tvSeriesList;
        }
    }
}
