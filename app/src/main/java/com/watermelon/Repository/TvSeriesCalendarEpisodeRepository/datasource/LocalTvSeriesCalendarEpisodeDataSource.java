package com.watermelon.Repository.TvSeriesCalendarEpisodeRepository.datasource;

import com.watermelon.Models.TvSeriesCalendarEpisode;

import java.util.List;

public interface LocalTvSeriesCalendarEpisodeDataSource {

    List<TvSeriesCalendarEpisode> getTvSeriesCalendarEpisodes(boolean flag);

}
