package com.watermelon.presentation.UI;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.watermelon.presentation.Repository.AppRepository;

public class SyncViewModel extends AndroidViewModel {
    private AppRepository repository;
    private LiveData<Boolean> syncStateObservable;

    public SyncViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);

        if(!WatermelonActivity.TEST_MODE){
            repository.initialFetchDataFromApi();
        }else {
            repository.initialFetchTestDataFromOffline();
        }
        syncStateObservable = repository.getSyncState();
    }

    LiveData<Boolean> getSyncStateObservable() {
        return syncStateObservable;
    }
}
