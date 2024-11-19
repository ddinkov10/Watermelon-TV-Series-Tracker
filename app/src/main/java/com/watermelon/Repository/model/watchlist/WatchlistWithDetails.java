package com.watermelon.Repository.model.watchlist;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.watermelon.Repository.model.Movie;
import com.watermelon.Repository.model.SeriesWithAllDetails;

import java.util.List;

public class WatchlistWithDetails {
    @Embedded
    public UserWatchlist watchlist;

    @Relation(
            parentColumn = "id",
            entityColumn = "id",
            associateBy = @Junction(
                    value = WatchlistSeriesLink.class,
                    parentColumn = "watchlistId",
                    entityColumn = "seriesId"
            )
    )
    public List<SeriesWithEpisodes> seriesList;


    @Relation(
            parentColumn = "id",
            entityColumn = "id",
            associateBy = @Junction(
                    value = WatchlistMovieLink.class,
                    parentColumn = "watchlistId",
                    entityColumn = "movieId"
            )
    )
    public List<Movie> movieList;

    public List<SeriesWithEpisodes> getSeriesList() {
        return seriesList;
    }
}

