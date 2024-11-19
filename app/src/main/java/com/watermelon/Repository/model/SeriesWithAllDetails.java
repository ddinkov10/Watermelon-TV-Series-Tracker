package com.watermelon.Repository.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class SeriesWithAllDetails {

    public SeriesWithAllDetails(Series series, List<Episode> episodes, List<Image> images) {
        this.series = series;
        this.episodes = episodes;
        this.images = images;
//        this.genres = genres;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

//    public List<Genre> getGenres() {
//        return genres;
//    }

//    public void setGenres(List<Genre> genres) {
//        this.genres = genres;
//    }

    @Embedded
    public Series series;

    @Relation(
            parentColumn = "id",
            entityColumn = "seriesId",
            entity = Episode.class
    )
    public List<Episode> episodes;

    @Relation(
            parentColumn = "id",
            entityColumn = "seriesId",
            entity = Image.class
    )
    public List<Image> images;

//    @Relation(
//            parentColumn = "id",
//            entityColumn = "id",
//            associateBy = @Junction(SeriesGenreLink.class)
//    )
//    public List<Genre> genres;
}
