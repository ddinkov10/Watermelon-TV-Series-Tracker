package com.watermelon.Repository;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.watermelon.Helpers.ConnectivityHelper;
import com.watermelon.Helpers.TvSeriesHelper;
import com.watermelon.Models.TvSeriesCalendarEpisode;
import com.watermelon.Models.TvSeries;
import com.watermelon.Models.TvSeriesEpisode;
import com.watermelon.Models.TvSeriesFull;
import com.watermelon.Repository.Api.ApiBuilder;
import com.watermelon.Repository.Api.ApiModels.TvSeriesDetails.JsonEpisode;
import com.watermelon.Repository.Api.ApiModels.TvSeriesDetails.JsonTvSeriesFull;
import com.watermelon.Repository.Api.ApiService;
import com.watermelon.Repository.Api.ApiModels.JsonTvSeriesSearchRoot;
import com.watermelon.Repository.Api.ApiModels.TvSeriesBasicInfo.JsonTvSeries;
import com.watermelon.Repository.Api.ApiModels.TvSeriesBasicInfo.JsonTvSeriesBasicRoot;
import com.watermelon.Repository.Api.ApiModels.TvSeriesDetails.JsonTvSeriesFullRoot;
//import com.watermelon.Repository.CalendarRepository.CalendarDao;
//import com.watermelon.Repository.DiscoverRepository.DiscoverDao;
//import com.watermelon.Repository.StatisticsRepository.StatisticsDao;
import com.watermelon.Repository.TvSeriesEpisodeRepository.dao.TvSeriesEpisodeDao;
import com.watermelon.Repository.TvSeriesFullRepository.dao.SeriesWithAllDetailsDao;
import com.watermelon.Repository.Genre.GenreDao;
import com.watermelon.Repository.TvSeriesPicturesRepository.TvSeriesPicturesDao;
import com.watermelon.Repository.SeriesRepository.dao.SeriesDao;
import com.watermelon.Repository.model.Episode;
import com.watermelon.Repository.model.Genre;
import com.watermelon.Repository.model.Image;
import com.watermelon.Repository.model.Series;
import com.watermelon.Repository.model.SeriesGenreLink;
import com.watermelon.Repository.model.mappers.DataModelMapper;
import com.watermelon.UI.WatermelonActivity;
import com.watermelon.Repository.AppRepoHelpClasses.NetworkBoundResource;
import com.watermelon.Repository.AppRepoHelpClasses.Resource;
import com.watermelon.Repository.AppRepoHelpClasses.MultiTaskHandler;
import com.watermelon.Helpers.DateHelper;
import com.watermelon.Models.TvSeriesSeason;
import com.watermelon.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AppRepository {
//    private AppDao appDao;


    private SeriesDao seriesDao;
    private SeriesWithAllDetailsDao seriesWithAllDetailsDao;
    private TvSeriesEpisodeDao tvSeriesEpisodeDao;
    private GenreDao genreDao;
    private TvSeriesPicturesDao tvSeriesPicturesDao;


//    private CalendarDao calendarDao;

//    private DiscoverDao discoverDao;

//    private StatisticsDao statisticsDao;

    private Context context;

    private List<TvSeriesFull> watchlistListObservable;
    private LiveData<List<TvSeriesCalendarEpisode>> calendarListObservable;
    private LiveData<List<TvSeries>> discoverListObservable;
    private MutableLiveData<List<String>> statisticsTvSeriesListObservable;
    private MutableLiveData<TvSeriesSeason> seasonEpisodesListObservable;
    private MutableLiveData<Boolean> syncState;
    private MutableLiveData<List<TvSeries>> searchTvSeriesListObservable;

    private final MultiTaskHandler handler;

    public AppRepository(Application application, AppDatabase appDatabase) {
        context = application.getApplicationContext();
        AppDatabase database = appDatabase;

        seriesDao = database.getSeriesDao();
//        seriesWithAllDetailsDao = database.getTvSeriesFullDao();
//        tvSeriesEpisodeDao = database.getTvSeriesEpisodeDao();
//        tvSeriesGenreDao = database.getTvSeriesGenreDao();
//        tvSeriesPicturesDao = database.getTvSeriesPicturesDao();

//        calendarDao = database.getCalendarDao();
//        statisticsDao = database.getStatisticsDao();
//        discoverDao = database.getDiscoverDao();

//        watchlistListObservable = watchlistDao.getWatchlistTvSeriesFull(WatermelonActivity.TVSERIES_WATCHED_FLAG_YES);
//        calendarListObservable = calendarDao.getCalendarTvSeries(WatermelonActivity.TVSERIES_WATCHED_FLAG_YES);
//        discoverListObservable = discoverDao.getDiscoverTvSeries();
//        statisticsTvSeriesListObservable = new MutableLiveData<>();
//        seasonEpisodesListObservable = new MutableLiveData<>();
        syncState = new MutableLiveData<>();
        searchTvSeriesListObservable = new MutableLiveData<>();

        if (!WatermelonActivity.TEST_MODE) {
            handler = new MultiTaskHandler(WatermelonActivity.TV_SERIES_MOST_POPULAR_PAGES_COUNT) {
                @Override
                protected void onAllTasksCompleted() {
                    syncState.setValue(true);
                }
            };

        }
    }

    public List<TvSeriesFull> getWatchlistListObservable() {
        return watchlistListObservable;
    }

    public LiveData<List<TvSeriesCalendarEpisode>> getCalendarListObservable() {
        return calendarListObservable;
    }

    public LiveData<List<TvSeries>> getDiscoverListObservable() {
        return discoverListObservable;
    }

    public LiveData<List<String>> getStatisticsTvSeriesListObservable() {
        return statisticsTvSeriesListObservable;
    }

    public LiveData<TvSeriesSeason> getSeasonEpisodesListObservable() {
        return seasonEpisodesListObservable;
    }

    public LiveData<List<TvSeries>> getSearchTvSeriesListObservable() {
        return searchTvSeriesListObservable;
    }

    public LiveData<Boolean> getSyncState() {
        return syncState;
    }

    public void initialFetchDataFromApi() {
        if (ConnectivityHelper.isConnectedFast(context)) {
            ApiService apiService = ApiBuilder.getRetrofitInstance().create(ApiService.class);
            for (int i = 1; i <= WatermelonActivity.TV_SERIES_MOST_POPULAR_PAGES_COUNT; i++) {
                apiService.getTvSeriesBasic(i).enqueue(new Callback<JsonTvSeriesBasicRoot>() {
                    @Override
                    public void onResponse(Call<JsonTvSeriesBasicRoot> call, Response<JsonTvSeriesBasicRoot> response) {
                        if (response.isSuccessful()) {
                            DataModelMapper mapper = new DataModelMapper();
                            List<Series> series = mapper.toSeriesList(response.body());
                            addTvSeriesToDb(series);
//                            addTvSeriesToDb(TvSeriesHelper.toTvSeriesArray(response.body()));
                            Log.d("initialFetchData", "Succesfull call");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonTvSeriesBasicRoot> call, Throwable t) {
                        Log.d("initialFetchData", "Error");
                    }
                });
            }
        } else {
            syncState.setValue(true);
        }
    }

    public void initialFetchTestDataFromOffline() {
        AssetManager assetManager = context.getAssets();
        JsonTvSeriesBasicRoot jsonTvSeriesBasicRoot = new JsonTvSeriesBasicRoot();
        try (InputStream is = assetManager.open(context.getString(R.string.mostPopularTvSeriesOffline)); // or file2
             Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            jsonTvSeriesBasicRoot = gson.fromJson(reader, JsonTvSeriesBasicRoot.class);
            DataModelMapper mapper = new DataModelMapper();
            List<Series> series = mapper.toSeriesList(jsonTvSeriesBasicRoot);
            addTvSeriesToDb(series);
            syncState.setValue(true);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void fetchTestDetailsFromOffline() {
        AssetManager assetManager = context.getAssets();
        List<String> testData = new ArrayList<>();
        testData.add(context.getString(R.string.gameOfThronesDetails));
        testData.add(context.getString(R.string.theFlashDetails));
        testData.add(context.getString(R.string.theWalkingDeadDetails));

        for (String fileName : testData) {
            JsonTvSeriesFullRoot jsonTvSeriesFullRoot = new JsonTvSeriesFullRoot();
            try (InputStream is = assetManager.open(fileName); // or file2
                 Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                Gson gson = new Gson();
                jsonTvSeriesFullRoot = gson.fromJson(reader, JsonTvSeriesFullRoot.class);
                addDetailsToDbAsync(jsonTvSeriesFullRoot);
            } catch (IOException e) {
                e.getMessage();
            }
        }


    }
/*
    public void fetchStatistics() {
        Log.d("Statistics", "fetch statistics");
        new AsyncTask<Void, Void, List<String>>() {
            @Override
            protected List<String> doInBackground(Void... voids) {
                List<TvSeriesFull> tvSeriesFulls = statisticsDao.getStatisticsTvSeriesFull(WatermelonActivity.TVSERIES_WATCHED_FLAG_YES);
                List<String> dataForStatistics = new ArrayList<>();
                int showsWithNextEpisodesCounter = 0;
                int showsRunningCounter = 0;
                int episodesCounter = 0;
                int episodeProgressCounter = 0;
                int totalRuntimeCounter = 0;
                for (TvSeriesFull tvSeriesFull : tvSeriesFulls) {
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
                String showsCount = String.valueOf(tvSeriesFulls.size());
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

                return dataForStatistics;
            }

            @Override
            protected void onPostExecute(List<String> strings) {
                statisticsTvSeriesListObservable.postValue(strings);
            }
        }.execute();
    }

 */

    public LiveData<Resource<SeriesWithAllDetailsDao>> fetchTvSeriesDetails(int id) {
        return new NetworkBoundResource<SeriesWithAllDetailsDao, JsonTvSeriesFullRoot>() {
            @Override
            protected void saveCallResult(@NonNull JsonTvSeriesFullRoot item) {
                detailsToDb(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable SeriesWithAllDetailsDao data) {
                return ConnectivityHelper.isConnectedFast(context);
            }

            @NonNull
            @Override
            protected LiveData<SeriesWithAllDetailsDao> loadFromDb() {
                return seriesWithAllDetailsDao.getTvSeriesFullByIdLiveData(id);
            }

            @NonNull
            @Override
            protected Call<JsonTvSeriesFullRoot> createCall() {
                ApiService apiService = ApiBuilder.getRetrofitInstance().create(ApiService.class);
                return apiService.getTvSeriesDetailed(id);
            }
        }.getAsLiveData();
    }

    public void fetchTvSeriesDetailsFromSearch(int id) {
        if (ConnectivityHelper.isConnectedFast(context)) {
            Log.d("", "getDetailsFromWeb");
            ApiService apiService = ApiBuilder.getRetrofitInstance().create(ApiService.class);
            apiService.getTvSeriesDetailed(id).enqueue(new Callback<JsonTvSeriesFullRoot>() {
                @Override
                public void onResponse(Call<JsonTvSeriesFullRoot> call, Response<JsonTvSeriesFullRoot> response) {
                    if (response.isSuccessful()) {
                        addDetailsToDbAsync(response.body());
                    }
                }

                @Override
                public void onFailure(Call<JsonTvSeriesFullRoot> call, Throwable t) {

                }
            });
        } else {
            Log.d("", "no network available or not a fast one :D");
        }
    }

    public void fetchTvSeriesEpisodesBySeason(int id, int seasonNum) {
        Log.d("", "fetchTvSeriesEpisodesBySeason");
        new AsyncTask<Void, Void, TvSeriesSeason>() {
            @Override
            protected TvSeriesSeason doInBackground(Void... voids) {
                List<TvSeriesEpisode> episodes = tvSeriesEpisodeDao.getTvSeriesEpisodesByIdAndSeasonNum(id, seasonNum);
                TvSeriesSeason season = new TvSeriesSeason(seasonNum, episodes);
                return season;
            }

            @Override
            protected void onPostExecute(TvSeriesSeason tvSeriesSeason) {
                seasonEpisodesListObservable.postValue(tvSeriesSeason);
            }
        }.execute();
    }

    //Help Db methods

    public void handleGenres(List<String> genres, int seriesId) {

    }

    private void handleGenres(List<String> genres) {
        // Handle genre insertion and association
        DataModelMapper mapper = new DataModelMapper();
        List<Genre> genresList = mapper.toGenres(genres);

        for (Genre genre : genresList) {
            Genre existingGenre = genreDao.getGenreByName(genre.getName());
            if (existingGenre == null) {
                // Insert new genre and get its ID
                genreDao.insertGenre(genre);
            }
        }
    }

    private void handleSeriesGenres(int seriesId, List<String> apiGenres) {
        List<SeriesGenreLink> links = new ArrayList<>();
        DataModelMapper mapper = new DataModelMapper();
        List<Genre> genres = mapper.toGenres(apiGenres);

        for (Genre genre : genres) {
            Genre existingGenre = genreDao.getGenreByName(genre.getName());
            if(existingGenre != null) {
                int genreId = existingGenre.getId();
                links.add(new SeriesGenreLink(seriesId, genreId));
            }

            // Create a link between the series and the genre
        }

        // Insert Series-Genre associations
        seriesDao.insertSeriesGenreLinks(links);
    }

    private void handleEpisodes(int seriesId, List<JsonEpisode> ApiEpisodes) {

        DataModelMapper mapper = new DataModelMapper();
        List<Episode> episodes = mapper.toEpisodes(ApiEpisodes, seriesId);

        Collections.sort(episodes, (ep1, ep2) -> {
            if (ep1.getSeasonNumber() == ep2.getSeasonNumber()) {
                return 0;
            } else if (ep1.getSeasonNumber() > ep2.getSeasonNumber()) {
                return 1;
            } else if (ep1.getSeasonNumber() < ep2.getSeasonNumber()) {
                return -1;
            }
            return 0;
        });

        for (Episode episode : episodes) {
            String shortDate = episode.getAirDate();
            if (shortDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                episode.setAirDate(shortDate);
            } else {
                episode.setAirDate("");
            }
        }

        tvSeriesEpisodeDao.insertEpisodes(episodes);
    }

    private int handleSeries(JsonTvSeriesFull jsonTvSeriesFull) {
        DataModelMapper mapper = new DataModelMapper();
        Series tempSeries = mapper.toSeries(jsonTvSeriesFull);
        tempSeries.setLastUpdated(System.currentTimeMillis());
        int apiId = tempSeries.getApiId();

        Series series;
        Series dbSeries;
        dbSeries = seriesDao.getSeriesByApiId(apiId);
        if(dbSeries == null) {
            int dbId= (int) seriesDao.insertSeries(tempSeries);
            seriesDao.updateSeriesDetails(dbId, tempSeries.getDescription(), tempSeries.getRuntime(), tempSeries.getYoutubeLink(), tempSeries.getRating());
            seriesDao.updateSeriesLastUpdated(dbId, tempSeries.getLastUpdated());
            series = seriesDao.getSeriesById(dbId);
        }else {
            series = dbSeries;
            if(dbSeries.getLastUpdated() > (System.currentTimeMillis() + 3600 * 1000)) {
                seriesDao.updateSeriesDetails(series.getId(), tempSeries.getDescription(), tempSeries.getRuntime(), tempSeries.getYoutubeLink(), tempSeries.getRating());
            }
        }
        int seriesId = series.getId();
        return seriesId;
    }

    private void handleImages(int seriesId, List<String> apiImages) {
        DataModelMapper mapper = new DataModelMapper();
        List<Image> images = mapper.toImages(apiImages, seriesId);
        tvSeriesPicturesDao.insertImages(images);
    }

    private void detailsToDb(JsonTvSeriesFullRoot item) {
        Log.d("", "detailsToDb");
        JsonTvSeriesFull jsonTvSeriesFull = item.getTvShow();

//      Series
        int seriesId = handleSeries(jsonTvSeriesFull);

//      Episodes
        List<JsonEpisode> apiEpisodes = jsonTvSeriesFull.getJsonEpisodes();
        handleEpisodes(seriesId, apiEpisodes);
//      add update option code

//      Genres
        List<String> apiGenres = jsonTvSeriesFull.getGenres();
        handleGenres(apiGenres);
        handleSeriesGenres(seriesId, apiGenres);

//      Images
        List<String> images = jsonTvSeriesFull.getPictures();
        handleImages(seriesId, images);


        Series dbTvSeries = seriesDao.getSeriesById(seriesId);



//        if (dbTvSeries != null) {
//            seriesDao.updateSeriesDetails(series.getId(), series.getDescription(), series.getRuntime(), series.getYoutubeLink(), series.getRating());
//        } else {
//            seriesDao.insertSeries(new Series(series.getTvSeriesId(), series.getTvSeriesName(), series.getTvSeriesStartDate(), series.getTvSeriesEndDate(), series.getTvSeriesCountry(), series.getTvSeriesNetwork(), series.getTvSeriesStatus(), series.getTvSeriesImagePath()));
//            seriesDao.updateTvSeriesDetails(tvSeries.getTvSeriesId(), tvSeries.getTvSeriesDesc(), tvSeries.getTvSeriesRuntime(), tvSeries.getTvSeriesYoutubeLink(), tvSeries.getTvSeriesRating());
//        }

//        TvSeriesEpisode testEpisode = new TvSeriesEpisode(35624, 7, 1, "Test Episode", "2020-08-11");
//        episodes.add(testEpisode);

//        if (dbTvSeries == null){
//            tvSeriesEpisodeDao.insertAllTvSeriesEpisodes(episodes);
//            handleSeriesGenres(series.getId(), genresList);
//            tvSeriesPicturesDao.insertAllTvSeriesPictures(pictures);
//        }else {
//            if (dbTvSeries.getEpisodes().size() == 0) {
//                tvSeriesEpisodeDao.insertAllTvSeriesEpisodes(episodes);
//            } else {
//                String lastEpisodeDate = tvSeriesEpisodeDao.getDateForTheLastEpisodeOfTvSeriesAired(id);
//                for (TvSeriesEpisode episode : episodes) {
//                    if (DateHelper.compareDates(lastEpisodeDate, episode.getEpisodeAirDate())) {
//                        tvSeriesEpisodeDao.insertTvSeriesEpisode(episode);
//                    }
//                }
//            }
//            if (dbTvSeries.getGenres().size() == 0) {
//                genreDao.insertAllTvSeriesGenres(genres);
//            }
//            if (dbTvSeries.getPictures().size() == 0) {
//                tvSeriesPicturesDao.insertAllTvSeriesPictures(pictures);
//            }
//        }
    }


    private void addDetailsToDbAsync(JsonTvSeriesFullRoot item) {
        Log.d("", "addDetailsToDbAsync");
        new AsyncTask<JsonTvSeriesFullRoot, Void, Void>() {
            @Override
            protected Void doInBackground(JsonTvSeriesFullRoot... jsonTvSeriesFullRoots) {
                detailsToDb(jsonTvSeriesFullRoots[0]);
                return null;
            }
        }.execute(item);
    }

    private void addTvSeriesToDb(List<Series> tvSeries) {
        Log.d("", "add tv shows to db");
        new AsyncTask<List<Series>, Void, Void>() {
            @Override
            protected synchronized Void doInBackground(List<Series>... params) {
                List<Series> apiShows = params[0];
                List<Integer> ids = new ArrayList<>();
                for (int i = 0; i < apiShows.size(); i++) {
                    ids.add(apiShows.get(i).getId());
                }
                List<Integer> existingShowsIds = seriesDao.getSeriesIfExists(ids);
                for (Series series : apiShows) {
                    if (existingShowsIds.contains(series.getId())) {
                        //update tv show fix all slots
                        seriesDao.updateSeries(series.getId(), series.getTitle(), series.getStatus(), series.getStartDate(), series.getEndDate(), series.getCountry(), series.getNetwork(), series.getImagePath());
                    } else {
                        seriesDao.insertSeries(series);
                    }
                }
                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {

                if (!WatermelonActivity.TEST_MODE) {
                    handler.taskComplete();
                }
            }
        }.execute(tvSeries);
    }
/*
    public void addSingleTvSeriesToDb(TvSeries tvSeries) {
        Log.d("", "add tv shows to db");
        new AsyncTask<TvSeries, Void, Void>() {
            @Override
            protected synchronized Void doInBackground(TvSeries... params) {
                if (seriesDao.getTvSeriesByApiId(params[0].getTvSeriesId()) != null) {
                    //update tv show fix all slots
                    seriesDao.updateTvSeries(tvSeries.getId(), tvSeries.getTvSeriesName(), tvSeries.getTvSeriesStatus(), tvSeries.getTvSeriesStartDate(), tvSeries.getTvSeriesEndDate(), tvSeries.getTvSeriesCountry(), tvSeries.getTvSeriesNetwork(), tvSeries.getTvSeriesImagePath());
                } else {
                    seriesDao.insertTvSeries(tvSeries);
                }
                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
            }
        }.execute(tvSeries);
    }

    public void setTvSeriesWatchedFlag(Pair<Integer, Boolean> params) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                int id = params.first;
                boolean flag = params.second;
                seriesDao.updateTvSeriesWatchedFlag(id, flag);
                return null;
            }
        }.execute();
    }

    public void setTvSeriesEpisodeWatchedFlag(Pair<Integer, Boolean> params) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                int id = params.first;
                boolean flag = params.second;
                tvSeriesEpisodeDao.updateTvSeriesEpisodeWatchedFlag(id, flag);
                return null;
            }
        }.execute();
    }

    public void setTvSeriesAllSeasonWatched(Pair<List<Integer>, Boolean> params) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<Integer> ids = params.first;
                boolean flag = params.second;
                tvSeriesEpisodeDao.updateTvSeriesAllSeasonWatched(ids, flag);
                return null;
            }
        }.execute();
    }

    //-------------------------------------------------


    //Api calls

    public void searchTvSeries(String searchWord, int pageNum) {
        ApiService apiService = ApiBuilder.getRetrofitInstance().create(ApiService.class);
        Call<JsonTvSeriesSearchRoot> jsonTvSeriesSearchRootCall = apiService.getTvSeriesSearch(searchWord, pageNum);
        jsonTvSeriesSearchRootCall.enqueue(new Callback<JsonTvSeriesSearchRoot>() {
            @Override
            public void onResponse(Call<JsonTvSeriesSearchRoot> call, Response<JsonTvSeriesSearchRoot> response) {
                JsonTvSeriesSearchRoot root = response.body();
                addTvSeriesToSearchList(root);
                Log.e("", String.valueOf(response));
            }

            @Override
            public void onFailure(Call<JsonTvSeriesSearchRoot> call, Throwable t) {
                Log.e("BIGFAIL", t.getMessage());
            }
        });
    }

    private void addTvSeriesToSearchList(JsonTvSeriesSearchRoot root) {
        List<TvSeries> searchedTvSeries = new ArrayList<>();
        for (int i = 0; i < root.getTvShows().size(); i++) {
            JsonTvSeries jsonTvSeries = root.getTvShows().get(i);
            TvSeries tvSeries = new TvSeries(jsonTvSeries.getId(), jsonTvSeries.getName(), jsonTvSeries.getStartDate(), jsonTvSeries.getEndDate(), jsonTvSeries.getCountry(), jsonTvSeries.getNetwork(), jsonTvSeries.getStatus(), jsonTvSeries.getImageThumbnailPath());
            searchedTvSeries.add(tvSeries);
        }
        searchTvSeriesListObservable.setValue(searchedTvSeries);
    }

    public void clearSearchList() {
        List<TvSeries> emptyTvSeries = new ArrayList<>();
        searchTvSeriesListObservable.setValue(emptyTvSeries);
    }
*/

    //TvSeries

    //TvSeriesPicture
    //TvSeriesEpisode
    //TvSeriesGenre
    //DetailsFragment
    //TvSeries AsyncTasks

}
