package com.watermelon.UI.Calendar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.watermelon.Common.injection.Injection;
import com.watermelon.Models.TvSeriesCalendarEpisode;
import com.watermelon.Repository.AppRepository;
import com.watermelon.UI.WatermelonActivity;
import com.watermelon.UI.framework.common.UseCaseHandler;
import com.watermelon.domain.usecase.GetCalendarListUseCase;

import java.util.List;

public class CalendarViewModel extends ViewModel {
    private final UseCaseHandler useCaseHandler;

    private final GetCalendarListUseCase getCalendarListUseCase;
    private MutableLiveData<List<TvSeriesCalendarEpisode>> calendarListObservable;

    public CalendarViewModel(UseCaseHandler useCaseHandler, GetCalendarListUseCase getCalendarListUseCase) {

        this.useCaseHandler = useCaseHandler;
        this.getCalendarListUseCase = getCalendarListUseCase;
        calendarListObservable = new MutableLiveData<>();
//        calendarListObservable = repository.getCalendarListObservable();
    }

    public void loadCalendarList() {
        useCaseHandler.execute(getCalendarListUseCase, new GetCalendarListUseCase.RequestValues(WatermelonActivity.TVSERIES_WATCHED_FLAG_YES), new UseCaseHandler.UseCaseCallback<GetCalendarListUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetCalendarListUseCase.ResponseValue response) {
                calendarListObservable.setValue(response.getCalendarEpisodeList());
            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }

    LiveData<List<TvSeriesCalendarEpisode>> getCalendarListObservable() {
        return calendarListObservable;
    }


}
