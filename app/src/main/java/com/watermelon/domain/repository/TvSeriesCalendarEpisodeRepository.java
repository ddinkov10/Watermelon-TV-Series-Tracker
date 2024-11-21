package com.watermelon.domain.repository;

import com.watermelon.Models.TvSeriesCalendarEpisode;

import java.util.List;

public interface TvSeriesCalendarEpisodeRepository {
    List<TvSeriesCalendarEpisode> getTvSeriesCalendarEpisodes(boolean flag);
}
