package com.watermelon.UI.ViewModelFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.watermelon.UI.Calendar.CalendarViewModel;
import com.watermelon.UI.Search.SearchViewModel;
import com.watermelon.UI.framework.common.UseCaseHandler;
import com.watermelon.domain.usecase.GetAllTvSeriesUseCase;
import com.watermelon.domain.usecase.GetCalendarListUseCase;
import com.watermelon.domain.usecase.SearchTvSeriesUseCase;

public class SearchViewModelFactory implements ViewModelProvider.Factory {

    private UseCaseHandler useCaseHandler;
    private GetAllTvSeriesUseCase getAllTvSeriesUseCase;
    private SearchTvSeriesUseCase searchTvSeriesUseCase;

    public SearchViewModelFactory(UseCaseHandler useCaseHandler, GetAllTvSeriesUseCase getAllTvSeriesUseCase, SearchTvSeriesUseCase searchTvSeriesUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getAllTvSeriesUseCase = getAllTvSeriesUseCase;
        this.searchTvSeriesUseCase = searchTvSeriesUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(useCaseHandler, getAllTvSeriesUseCase, searchTvSeriesUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
