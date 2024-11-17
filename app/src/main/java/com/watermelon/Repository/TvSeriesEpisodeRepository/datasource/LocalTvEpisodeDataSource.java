package com.watermelon.Repository.TvSeriesEpisodeRepository.datasource;

import android.util.Pair;

public interface LocalTvEpisodeDataSource {
    void setTvSeriesEpisodeWatchedFlag(Pair<Integer, Boolean> params);
}
