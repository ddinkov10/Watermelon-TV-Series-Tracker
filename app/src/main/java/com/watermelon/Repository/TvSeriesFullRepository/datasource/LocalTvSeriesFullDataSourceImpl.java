package com.watermelon.Repository.TvSeriesFullRepository.datasource;

import android.util.Log;

import com.watermelon.Helpers.DateHelper;
import com.watermelon.Helpers.TvSeriesHelper;
import com.watermelon.Models.TvSeries;
import com.watermelon.Models.TvSeriesEpisode;
import com.watermelon.Models.TvSeriesFull;
import com.watermelon.Models.TvSeriesGenre;
import com.watermelon.Models.TvSeriesPicture;
import com.watermelon.Repository.AppDatabase;
import com.watermelon.Repository.TvSeriesEpisodeRepository.dao.TvSeriesEpisodeDao;
import com.watermelon.Repository.TvSeriesGenre.TvSeriesGenreDao;
import com.watermelon.Repository.TvSeriesPicturesRepository.TvSeriesPicturesDao;
import com.watermelon.Repository.TvSeriesRepository.TvSeriesDao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LocalTvSeriesFullDataSourceImpl implements LocalTvSeriesFullDataSource {

    private final AppDatabase appDatabase;

    public LocalTvSeriesFullDataSourceImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public TvSeriesFull getTvSeriesFullById(int id) {
        return appDatabase.getTvSeriesFullDao().getTvSeriesFullById(id);
    }

    @Override
    public List<TvSeriesFull> getLocalData() {
//        TODO: fix this
        return appDatabase.getTvSeriesFullDao().getWatchlistTvSeriesFull();
    }

    @Override
    public List<TvSeriesFull> getTvSeriesFullByFlag(boolean flag) {
        return appDatabase.getTvSeriesFullDao().getTvSeriesFullByFlag(flag);
    }

    @Override
    public void saveTvSeriesFull(TvSeriesFull tvSeriesFull) {
        Log.d("", "detailsToDb");

        TvSeriesDao tvSeriesDao = appDatabase.getTvSeriesDao();
        TvSeriesEpisodeDao tvSeriesEpisodeDao = appDatabase.getTvSeriesEpisodeDao();
        TvSeriesGenreDao tvSeriesGenreDao = appDatabase.getTvSeriesGenreDao();
        TvSeriesPicturesDao tvSeriesPicturesDao = appDatabase.getTvSeriesPicturesDao();


//        TvSeriesFull tvSeriesFull = TvSeriesHelper.jsonToModel(item);
        TvSeries tvSeries = tvSeriesFull.getTvSeries();

        int id = tvSeries.getTvSeriesId();
        List<TvSeriesEpisode> episodes = tvSeriesFull.getEpisodes();

        Collections.sort(episodes, new Comparator<TvSeriesEpisode>() {
            @Override
            public int compare(TvSeriesEpisode ep1, TvSeriesEpisode ep2) {
                if (ep1.getEpisodeSeasonNum() == ep2.getEpisodeSeasonNum()) {
                    return 0;
                } else if (ep1.getEpisodeSeasonNum() > ep2.getEpisodeSeasonNum()) {
                    return 1;
                } else if (ep1.getEpisodeSeasonNum() < ep2.getEpisodeSeasonNum()) {
                    return -1;
                }
                return 0;
            }
        });

        for (TvSeriesEpisode episode : episodes) {
            String shortDate = episode.getEpisodeAirDate();
            if (shortDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                episode.setEpisodeAirDate(shortDate);
            } else {
                episode.setEpisodeAirDate("");
            }
        }
        List<TvSeriesGenre> genres = tvSeriesFull.getGenres();
        List<TvSeriesPicture> pictures = tvSeriesFull.getPictures();

        TvSeriesFull dbTvSeries = tvSeriesDao.getTvSeriesByApiId(id);

        if (dbTvSeries != null) {
            tvSeriesDao.updateTvSeriesDetails(tvSeries.getTvSeriesId(), tvSeries.getTvSeriesDesc(), tvSeries.getTvSeriesRuntime(), tvSeries.getTvSeriesYoutubeLink(), tvSeries.getTvSeriesRating());
        } else {
            tvSeriesDao.insertTvSeries(new TvSeries(tvSeries.getTvSeriesId(), tvSeries.getTvSeriesName(), tvSeries.getTvSeriesStartDate(), tvSeries.getTvSeriesEndDate(), tvSeries.getTvSeriesCountry(), tvSeries.getTvSeriesNetwork(), tvSeries.getTvSeriesStatus(), tvSeries.getTvSeriesImagePath()));
            tvSeriesDao.updateTvSeriesDetails(tvSeries.getTvSeriesId(), tvSeries.getTvSeriesDesc(), tvSeries.getTvSeriesRuntime(), tvSeries.getTvSeriesYoutubeLink(), tvSeries.getTvSeriesRating());
        }

//        TvSeriesEpisode testEpisode = new TvSeriesEpisode(35624, 7, 1, "Test Episode", "2020-08-11");
//        episodes.add(testEpisode);

        if (dbTvSeries == null){
            tvSeriesEpisodeDao.insertAllTvSeriesEpisodes(episodes);
            tvSeriesGenreDao.insertAllTvSeriesGenres(genres);
            tvSeriesPicturesDao.insertAllTvSeriesPictures(pictures);
        }else {
            if (dbTvSeries.getEpisodes().size() == 0) {
                tvSeriesEpisodeDao.insertAllTvSeriesEpisodes(episodes);
            } else {
                String lastEpisodeDate = tvSeriesEpisodeDao.getDateForTheLastEpisodeOfTvSeriesAired(id);
                for (TvSeriesEpisode episode : episodes) {
                    if (DateHelper.compareDates(lastEpisodeDate, episode.getEpisodeAirDate())) {
                        tvSeriesEpisodeDao.insertTvSeriesEpisode(episode);
                    }
                }
            }
            if (dbTvSeries.getGenres().size() == 0) {
                tvSeriesGenreDao.insertAllTvSeriesGenres(genres);
            }
            if (dbTvSeries.getPictures().size() == 0) {
                tvSeriesPicturesDao.insertAllTvSeriesPictures(pictures);
            }
        }
    }


}
