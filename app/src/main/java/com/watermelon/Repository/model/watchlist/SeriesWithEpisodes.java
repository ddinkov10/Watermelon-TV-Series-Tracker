package com.watermelon.Repository.model.watchlist;


import androidx.room.Embedded;
import androidx.room.Relation;

import com.watermelon.Repository.model.Episode;
import com.watermelon.Repository.model.Series;

import java.util.List;

public class SeriesWithEpisodes {

    @Embedded
    public Series series;

    @Relation(
            parentColumn = "id",
            entityColumn = "seriesId",
            entity = Episode.class
    )
    public List<EpisodeWithWatchStatus> episodes;

    public Series getSeries() {
        return series;
    }

    public List<EpisodeWithWatchStatus> getEpisodes() {
        return episodes;
    }
}
