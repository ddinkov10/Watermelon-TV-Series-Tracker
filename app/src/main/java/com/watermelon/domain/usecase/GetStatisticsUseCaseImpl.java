package com.watermelon.domain.usecase;

import com.watermelon.Helpers.TvSeriesHelper;
import com.watermelon.Models.TvSeries;
import com.watermelon.Models.TvSeriesEpisode;
import com.watermelon.Models.TvSeriesFull;
import com.watermelon.UI.WatermelonActivity;

import java.util.ArrayList;
import java.util.List;

public class GetStatisticsUseCaseImpl implements GetStatisticsUseCase{

    private final GetWatchlistUseCase getWatchlistUseCase;

    public GetStatisticsUseCaseImpl(GetWatchlistUseCase getWatchlistUseCase) {
        this.getWatchlistUseCase = getWatchlistUseCase;
    }

    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {

        GetWatchlistUseCase.ResponseValue responseValue =  this.getWatchlistUseCase.executeUseCase(new GetWatchlistUseCase.RequestValues(requestValues.getFlag()));
        List<TvSeriesFull> watchlist = responseValue.getWatchlistList();

        List<String> dataForStatistics = new ArrayList<>();
        int showsWithNextEpisodesCounter = 0;
        int showsRunningCounter = 0;
        int episodesCounter = 0;
        int episodeProgressCounter = 0;
        int totalRuntimeCounter = 0;
        for (TvSeriesFull tvSeriesFull : watchlist) {
            TvSeries tvSeries = tvSeriesFull.getTvSeries();
            List<TvSeriesEpisode> episodes = tvSeriesFull.getEpisodes();
            if (TvSeriesHelper.getNextWatched(episodes) != null) {
                showsWithNextEpisodesCounter++;
            }
            if (tvSeries.getTvSeriesStatus().contains(WatermelonActivity.STATUS_RUNNING)) {
                showsRunningCounter++;
            }
            episodesCounter += episodes.size();
            episodeProgressCounter += TvSeriesHelper.getEpisodeProgress(episodes);
            totalRuntimeCounter += episodes.size() * Integer.parseInt(tvSeries.getTvSeriesRuntime());
        }
        String showsCount = String.valueOf(watchlist.size());
        String showsWithNextEpisodesCount = String.valueOf(showsWithNextEpisodesCounter);
        String showsNotEndedCount = String.valueOf(showsRunningCounter);
        String episodesCount = String.valueOf(episodesCounter);
        String episodeProgressCount = String.valueOf(episodeProgressCounter);
        String totalRuntime = String.valueOf(totalRuntimeCounter);

        dataForStatistics.add(showsCount);
        dataForStatistics.add(showsWithNextEpisodesCount);
        dataForStatistics.add(showsNotEndedCount);
        dataForStatistics.add(episodesCount);
        dataForStatistics.add(episodeProgressCount);
        dataForStatistics.add(totalRuntime);

        return new ResponseValue(dataForStatistics);
    }
}
