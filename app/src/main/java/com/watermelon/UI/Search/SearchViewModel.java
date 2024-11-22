package com.watermelon.UI.Search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.watermelon.Common.injection.Injection;
import com.watermelon.Models.TvSeries;
import com.watermelon.UI.WatermelonActivity;
import com.watermelon.Repository.AppRepository;
import com.watermelon.UI.framework.common.UseCaseHandler;
import com.watermelon.domain.usecase.GetAllTvSeriesUseCase;
import com.watermelon.domain.usecase.SearchTvSeriesUseCase;

import java.util.ArrayList;
import java.util.List;


public class SearchViewModel extends ViewModel {

    private final UseCaseHandler useCaseHandler;
    private final GetAllTvSeriesUseCase getAllTvSeriesUseCase;
    private final SearchTvSeriesUseCase searchTvSeriesUseCase;
    private final MutableLiveData<List<TvSeries>> discoverList;
    private final MutableLiveData<List<TvSeries>> allSearchWordTvSeries;

    public SearchViewModel(UseCaseHandler useCaseHandler, GetAllTvSeriesUseCase getAllTvSeriesUseCase, SearchTvSeriesUseCase searchTvSeriesUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getAllTvSeriesUseCase = getAllTvSeriesUseCase;
        this.searchTvSeriesUseCase = searchTvSeriesUseCase;
        allSearchWordTvSeries = new MutableLiveData<>();
        discoverList = new MutableLiveData<>();

//        if (WatermelonActivity.TEST_MODE) {
//            repository.fetchTestDetailsFromOffline();
//        }
    }

    LiveData<List<TvSeries>> getDiscoverList() {
        return discoverList;
    }

    LiveData<List<TvSeries>> getAllSearchWordTvSeries() {
        return allSearchWordTvSeries;
    }

    public void loadAllTvSeries() {
        useCaseHandler.execute(getAllTvSeriesUseCase, null, new UseCaseHandler.UseCaseCallback<GetAllTvSeriesUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetAllTvSeriesUseCase.ResponseValue response) {
                updateDiscoverList(response.getTvSeriesList());
            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }

    private void updateDiscoverList(List<TvSeries> tvSeriesList) {
        discoverList.setValue(tvSeriesList);
    }

    private void updateAllSearchWordTvSeries(List<TvSeries> searchList) {
        allSearchWordTvSeries.setValue(searchList);
    }

    public void searchTvSeriesData(String searchWord) {
        useCaseHandler.execute(searchTvSeriesUseCase, new SearchTvSeriesUseCase.RequestValues(searchWord), new UseCaseHandler.UseCaseCallback<SearchTvSeriesUseCase.ResponseValue>() {
            @Override
            public void onSuccess(SearchTvSeriesUseCase.ResponseValue response) {
                updateAllSearchWordTvSeries(response.getTvSeriesList());
            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }

    public void clearSearchedTvSeries() {
        updateAllSearchWordTvSeries(new ArrayList<>());
    }

}
