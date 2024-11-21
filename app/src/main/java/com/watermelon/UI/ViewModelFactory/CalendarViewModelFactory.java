package com.watermelon.UI.ViewModelFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.watermelon.UI.Calendar.CalendarViewModel;
import com.watermelon.UI.framework.common.UseCaseHandler;
import com.watermelon.domain.usecase.GetCalendarListUseCase;

public class CalendarViewModelFactory implements ViewModelProvider.Factory {

    private UseCaseHandler useCaseHandler;
    private GetCalendarListUseCase getCalendarListUseCase;

    public CalendarViewModelFactory(UseCaseHandler useCaseHandler, GetCalendarListUseCase getCalendarListUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getCalendarListUseCase = getCalendarListUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(CalendarViewModel.class)) {
            return (T) new CalendarViewModel(useCaseHandler, getCalendarListUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
