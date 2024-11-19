package com.watermelon.Helpers;

import com.watermelon.Models.TvSeries;
import com.watermelon.Models.TvSeriesEpisode;
import com.watermelon.Models.TvSeriesSeason;
import com.watermelon.Repository.Api.ApiModels.TvSeriesBasicInfo.JsonTvSeries;
import com.watermelon.Repository.Api.ApiModels.TvSeriesBasicInfo.JsonTvSeriesBasicRoot;
import com.watermelon.Repository.Api.ApiModels.TvSeriesDetails.JsonEpisode;
import com.watermelon.Repository.Api.ApiModels.TvSeriesDetails.JsonTvSeriesFull;
import com.watermelon.Repository.Api.ApiModels.TvSeriesDetails.JsonTvSeriesFullRoot;
import com.watermelon.Repository.model.Episode;
import com.watermelon.Repository.model.Genre;
import com.watermelon.Repository.model.Image;
import com.watermelon.Repository.model.Series;
import com.watermelon.Repository.model.SeriesWithAllDetails;
import com.watermelon.Repository.model.mappers.DataModelMapper;
import com.watermelon.Repository.model.watchlist.EpisodeWithWatchStatus;

import java.util.ArrayList;
import java.util.List;

public class TvSeriesHelper {
    public static EpisodeWithWatchStatus getNextWatched(List<EpisodeWithWatchStatus> episodes) {
        for (int i = 0; i < episodes.size(); i++) {
            if (!episodes.get(i).getWatchStatus().isWatched()) {
                return episodes.get(i);
            }
        }
        return null;
    }

    public static boolean getTvSeriesWatchlistState(TvSeries tvSeries){
        return tvSeries.isTvSeriesWatchingFlag();
    }

    public static boolean getTvSeriesState(List<EpisodeWithWatchStatus> episodes) {
        boolean state = true;
        for (int i = 0; i < episodes.size(); i++) {
            if (!episodes.get(i).getWatchStatus().isWatched()) {
                state = false;
                return state;
            }
        }
        return state;
    }

    public static int getEpisodeProgress(List<EpisodeWithWatchStatus> episodes) {
        int counter = 0;
        for (int i = 0; i < episodes.size(); i++) {
            if (episodes.get(i).getWatchStatus().isWatched()) {
                counter++;
            }
        }
        return counter;
    }

    public static List<TvSeriesSeason> getTvSeriesSeasons(List<TvSeriesEpisode> episodes) {
        List<TvSeriesSeason> tvSeriesSeasons = new ArrayList<>();
        List<TvSeriesEpisode> currentSeasonEpisodes = new ArrayList<>();
        int currentSeason = 1;
        for (TvSeriesEpisode episode : episodes){
            int currentEpisodeSeason = episode.getEpisodeSeasonNum();
            if(currentEpisodeSeason == currentSeason){
                currentSeasonEpisodes.add(episode);
            }else{
                tvSeriesSeasons.add(new TvSeriesSeason(currentSeason, currentSeasonEpisodes));
                currentSeason++;
                currentSeasonEpisodes = new ArrayList<>();
                currentSeasonEpisodes.add(episode);
            }
        }
        tvSeriesSeasons.add(new TvSeriesSeason(currentSeason, currentSeasonEpisodes));
        return tvSeriesSeasons;
    }

    public static List<TvSeries> toTvSeriesArray(JsonTvSeriesBasicRoot root) {
        List<TvSeries> returnTvSeries = new ArrayList<>();
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


            TvSeries tvSeries = new TvSeries(tvSeriesId, tvSeriesName, tvSeriesStartDate, tvSeriesEndDate, tvSeriesCountry, tvSeriesNetwork, tvSeriesStatus, tvSeriesImage);
            returnTvSeries.add(tvSeries);

        }
        return returnTvSeries;
    }

    public static SeriesWithAllDetails jsonToModel(JsonTvSeriesFullRoot root){
        DataModelMapper mapper = new DataModelMapper();
        JsonTvSeriesFull jsonTvSeriesFull = root.getTvShow();

        Series series = mapper.toSeries(jsonTvSeriesFull);

        List<JsonEpisode> episodes = jsonTvSeriesFull.getJsonEpisodes();
        List<String> genres = jsonTvSeriesFull.getGenres();
        List<String> pictures = jsonTvSeriesFull.getPictures();

        int id = series.getId();
        List<Genre> genresList = mapper.toGenres(genres);
        List<Episode> episodesList = mapper.toEpisodes(episodes, id);
        List<Image> imageList = mapper.toImages(pictures, id);


//        genresList
        return new SeriesWithAllDetails(series, episodesList, imageList);
    }


}
