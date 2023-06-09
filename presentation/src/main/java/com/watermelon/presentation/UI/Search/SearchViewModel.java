package com.watermelon.presentation.UI.Search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.watermelon.presentation.Models.TvSeries;
import com.watermelon.presentation.UI.WatermelonActivity;
import com.watermelon.presentation.Repository.AppRepository;

import java.util.List;


public class SearchViewModel extends AndroidViewModel {
    private AppRepository repository;
    private final LiveData<List<TvSeries>> discoverList;
    private final LiveData<List<TvSeries>> allSearchWordTvSeries;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
        allSearchWordTvSeries = repository.getSearchTvSeriesListObservable();
        discoverList = repository.getDiscoverListObservable();
        if (WatermelonActivity.TEST_MODE) {
            repository.fetchTestDetailsFromOffline();
        }
    }

    LiveData<List<TvSeries>> getDiscoverList() {
        return discoverList;
    }

    LiveData<List<TvSeries>> getAllSearchWordTvSeries() {
        return allSearchWordTvSeries;
    }

    void searchTvSeriesData(String searchWord) {
        repository.searchTvSeries(searchWord, 1);
    }

    void clearSearchedTvSeries() {
        repository.clearSearchList();
    }

    void fetchTvSeriesDetails(int id) {
        repository.fetchTvSeriesDetailsFromSearch(id);
    }


}
