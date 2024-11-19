package com.watermelon.Repository.model.watchlist;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.watermelon.Repository.model.Episode;

public class EpisodeWithWatchStatus {
    @Embedded
    public Episode episode;

    @Relation(
            parentColumn = "id",
            entityColumn = "episodeId"
    )
    public EpisodeWatchStatus watchStatus;

    public Episode getEpisode() {
        return episode;
    }

    public EpisodeWatchStatus getWatchStatus() {
        return watchStatus;
    }
}
