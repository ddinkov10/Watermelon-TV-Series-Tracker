package com.watermelon.UI.Statistics;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.watermelon.Common.injection.Injection;
import com.watermelon.Repository.AppRepository;
import com.watermelon.UI.WatermelonActivity;
import com.watermelon.UI.framework.common.UseCaseHandler;
import com.watermelon.domain.usecase.GetStatisticsUseCase;
import com.watermelon.domain.usecase.GetWatchlistUseCase;

import java.util.List;

public class StatisticsViewModel extends ViewModel {
    private final UseCaseHandler useCaseHandler;
    private final GetStatisticsUseCase getStatisticsUseCase;
    private final MutableLiveData<List<String>> statisticsListObservable;

    public StatisticsViewModel(UseCaseHandler useCaseHandler, GetStatisticsUseCase getStatisticsUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getStatisticsUseCase = getStatisticsUseCase;
        this.statisticsListObservable = new MutableLiveData<>();
//        statisticsListObservable = repository.getStatisticsTvSeriesListObservable();
    }

    public void fetchStatisticsData() {
        useCaseHandler.execute(getStatisticsUseCase, new GetStatisticsUseCase.RequestValues(WatermelonActivity.TVSERIES_WATCHED_FLAG_YES), new UseCaseHandler.UseCaseCallback<GetStatisticsUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetStatisticsUseCase.ResponseValue response) {
                updateStatistics(response.getStatistics());
            }

            @Override
            public void onError(Exception exception) {
                throw new RuntimeException(exception);
            }
        });
    }

    private void updateStatistics(List<String> list) {
        statisticsListObservable.setValue(list);
    }



    LiveData<List<String>> getStatisticsListObservable() {
        return statisticsListObservable;
    }

}
