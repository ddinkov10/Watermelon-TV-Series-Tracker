package com.watermelon.Repository.model.mappers;

import com.watermelon.Repository.Api.ApiModels.TvSeriesBasicInfo.JsonTvSeries;
import com.watermelon.Repository.Api.ApiModels.TvSeriesBasicInfo.JsonTvSeriesBasicRoot;
import com.watermelon.Repository.Api.ApiModels.TvSeriesDetails.JsonEpisode;
import com.watermelon.Repository.Api.ApiModels.TvSeriesDetails.JsonTvSeriesFull;
import com.watermelon.Repository.model.Episode;
import com.watermelon.Repository.model.Genre;
import com.watermelon.Repository.model.Image;
import com.watermelon.Repository.model.Series;

import java.util.ArrayList;
import java.util.List;

public class DataModelMapper {

    public List<Series> toSeriesList(JsonTvSeriesBasicRoot root) {
        List<Series> returnTvSeries = new ArrayList<>();
        List<JsonTvSeries> TVShows = root.getTVShows();
        for(int i=0;i<TVShows.size();i++){

            JsonTvSeries jsonTvSeries = TVShows.get(i);;

            int tvSeriesId = jsonTvSeries.getId();
            String tvSeriesName = jsonTvSeries.getName();
            String tvSeriesStartDate = jsonTvSeries.getStartDate();
            String tvSeriesEndDate = jsonTvSeries.getEndDate();
            String tvSeriesCountry = jsonTvSeries.getCountry();
            String tvSeriesNetwork = jsonTvSeries.getNetwork();
            String tvSeriesStatus = jsonTvSeries.getStatus();
            String tvSeriesImage = jsonTvSeries.getImageThumbnailPath();


            Series tvSeries = new Series(tvSeriesId, tvSeriesName, tvSeriesStartDate, tvSeriesEndDate, tvSeriesCountry, tvSeriesNetwork, tvSeriesStatus, tvSeriesImage);
            returnTvSeries.add(tvSeries);

        }
        return returnTvSeries;
    }

    public Series toSeries(JsonTvSeriesFull jsonTvSeriesFull) {

        Series series = new Series(jsonTvSeriesFull.getId(), jsonTvSeriesFull.getName(), jsonTvSeriesFull.getStartDate(), jsonTvSeriesFull.getEndDate(), jsonTvSeriesFull.getCountry(), jsonTvSeriesFull.getNetwork(), jsonTvSeriesFull.getStatus(), jsonTvSeriesFull.getImageThumbnailPath());
        series.setRuntime(String.valueOf(jsonTvSeriesFull.getRuntime()));
        series.setDescription(jsonTvSeriesFull.getDescription());
        series.setYoutubeLink(jsonTvSeriesFull.getYoutubeLink());
        series.setRating(jsonTvSeriesFull.getRating());

        return series;
    }

    public List<Episode> toEpisodes(List<JsonEpisode> apiEpisodes, int seriesId) {
        List<Episode> episodes = new ArrayList<>();
        for (JsonEpisode apiEpisode : apiEpisodes) {
            episodes.add(new Episode(
                    seriesId,
                    apiEpisode.getName(),
                    apiEpisode.getSeason(),
                    apiEpisode.getEpisode(),
                    apiEpisode.getAirDate()
            ));
        }
        return episodes;
    }

    public List<Genre> toGenres(List<String> genreNames) {
        List<Genre> genres = new ArrayList<>();
        for (String name : genreNames) {
            genres.add(new Genre(name));
        }
        return genres;
    }

    public List<Image> toImages(List<String> imageUrls, int seriesId) {
        List<Image> images = new ArrayList<>();
        for (String url : imageUrls) {
            images.add(new Image(seriesId, url));
        }
        return images;
    }

//    private Date parseDate(String dateString) {
//        // Implement date parsing logic
//        return new Date(); // Placeholder
//    }
}
