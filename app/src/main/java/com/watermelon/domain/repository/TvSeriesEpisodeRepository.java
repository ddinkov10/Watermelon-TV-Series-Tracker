package com.watermelon.domain.repository;

import android.util.Pair;

import java.util.List;

public interface TvSeriesEpisodeRepository {

     void setTvSeriesEpisodeWatchedFlag(Pair<Integer, Boolean> params);

     void setTvSeriesAllSeasonWatched(Pair<List<Integer>, Boolean> params);

}
