package com.watermelon.domain.usecase;

import com.watermelon.Models.TvSeriesCalendarEpisode;
import com.watermelon.domain.repository.TvSeriesCalendarEpisodeRepository;

import java.util.List;

public class GetCalendarListUseCaseImpl implements GetCalendarListUseCase {

    private final TvSeriesCalendarEpisodeRepository calendarEpisodeRepository;

    public GetCalendarListUseCaseImpl(TvSeriesCalendarEpisodeRepository repository) {
        this.calendarEpisodeRepository = repository;
    }

    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {

        List<TvSeriesCalendarEpisode> episodeList = calendarEpisodeRepository.getTvSeriesCalendarEpisodes(requestValues.getFlag());
        return new ResponseValue(episodeList);
    }
}
