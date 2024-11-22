package com.watermelon.UI.Discover;

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

import java.util.List;


public class DiscoverViewModel extends ViewModel {
    private final UseCaseHandler useCaseHandler;

    private final GetAllTvSeriesUseCase getAllTvSeriesUseCase;
    private final MutableLiveData<List<TvSeries>> discoverListObservable;

    public DiscoverViewModel(UseCaseHandler useCaseHandler, GetAllTvSeriesUseCase getAllTvSeriesUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getAllTvSeriesUseCase = getAllTvSeriesUseCase;
        discoverListObservable = new MutableLiveData<>();

//        if(WatermelonActivity.TEST_MODE) {
//            repository.fetchTestDetailsFromOffline();
//        }
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
        discoverListObservable.setValue(tvSeriesList);
    }

    LiveData<List<TvSeries>> getDiscoverListObservable() {
        return discoverListObservable;
    }

}
