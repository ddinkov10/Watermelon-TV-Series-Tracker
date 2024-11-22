package com.watermelon.UI.ViewModelFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.watermelon.UI.Calendar.CalendarViewModel;
import com.watermelon.UI.Discover.DiscoverViewModel;
import com.watermelon.UI.framework.common.UseCaseHandler;
import com.watermelon.domain.usecase.GetAllTvSeriesUseCase;
import com.watermelon.domain.usecase.GetCalendarListUseCase;

public class DiscoverViewModelFactory implements ViewModelProvider.Factory {

    private UseCaseHandler useCaseHandler;
    private GetAllTvSeriesUseCase getAllTvSeriesUseCase;

    public DiscoverViewModelFactory(UseCaseHandler useCaseHandler, GetAllTvSeriesUseCase getAllTvSeriesUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getAllTvSeriesUseCase = getAllTvSeriesUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(DiscoverViewModel.class)) {
            return (T) new DiscoverViewModel(useCaseHandler, getAllTvSeriesUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
