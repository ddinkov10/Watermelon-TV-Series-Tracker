package com.watermelon.Repository.TvSeriesEpisodeRepository.datasource;

import android.util.Pair;

import java.util.List;

public interface LocalTvEpisodeDataSource {
    void setTvSeriesEpisodeWatchedFlag(Pair<Integer, Boolean> params);

    void setTvSeriesAllSeasonWatched(Pair<List<Integer>, Boolean> params);
}
