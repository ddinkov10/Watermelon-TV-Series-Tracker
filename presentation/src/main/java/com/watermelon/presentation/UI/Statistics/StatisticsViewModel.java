package com.watermelon.presentation.UI.Statistics;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.watermelon.presentation.Repository.AppRepository;

import java.util.List;

public class StatisticsViewModel extends AndroidViewModel {
    private AppRepository repository;
    private LiveData<List<String>> statisticsListObservable = new MediatorLiveData<>();

    public StatisticsViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
        statisticsListObservable = repository.getStatisticsTvSeriesListObservable();
    }
    LiveData<List<String>> getStatisticsListObservable() {
        return statisticsListObservable;
    }

    void fetchStatisticsData() {
        repository.fetchStatistics();
    }
}
