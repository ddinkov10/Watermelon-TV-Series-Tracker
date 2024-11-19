package com.watermelon.Repository.model.watchlist;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.watermelon.Repository.model.Movie;
import com.watermelon.Repository.model.Series;

import java.util.List;

public class WatchlistWithSeries {
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
    public List<Series> seriesList;


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


}
