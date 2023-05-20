package com.watermelon.presentation.UI.Discover;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.watermelon.presentation.Models.TvSeries;
import com.watermelon.presentation.UI.WatermelonActivity;
import com.watermelon.presentation.Repository.AppRepository;

import java.util.List;


public class DiscoverViewModel extends AndroidViewModel {
    private AppRepository repository;
    private final LiveData<List<TvSeries>> discoverListObservable;

    public DiscoverViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
        discoverListObservable = repository.getDiscoverListObservable();

        if(WatermelonActivity.TEST_MODE) {
            repository.fetchTestDetailsFromOffline();
        }
    }

    LiveData<List<TvSeries>> getDiscoverListObservable() {
        return discoverListObservable;
    }

}
