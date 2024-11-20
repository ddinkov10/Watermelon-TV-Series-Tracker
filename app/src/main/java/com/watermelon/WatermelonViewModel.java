package com.watermelon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WatermelonViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isDataDirty = new MutableLiveData<>(false);

    public LiveData<Boolean> isDataDirty() {
        return isDataDirty;
    }

    public void markDataAsDirty() {
        isDataDirty.setValue(true);
    }

    public void markDataAsClean() {
        isDataDirty.setValue(false);
    }

}
