package com.watermelon.UI.ViewModelFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.watermelon.UI.Calendar.CalendarViewModel;
import com.watermelon.UI.Details.DetailsViewModel;
import com.watermelon.UI.framework.common.UseCaseHandler;
import com.watermelon.domain.usecase.AddToWatchlistUseCase;
import com.watermelon.domain.usecase.ChangeEpisodesWatchedFlagUseCase;
import com.watermelon.domain.usecase.FetchAndSaveTvSeriesDetailsUseCase;
import com.watermelon.domain.usecase.GetCalendarListUseCase;
import com.watermelon.domain.usecase.GetTvSeriesDetailsUseCase;
import com.watermelon.domain.usecase.RemoveFromWatchlistUseCase;

public class DetailsViewModelFactory implements ViewModelProvider.Factory {

    private final UseCaseHandler useCaseHandler;
    private final FetchAndSaveTvSeriesDetailsUseCase fetchAndSaveTvSeriesDetailsUseCase;
    private final GetTvSeriesDetailsUseCase getTvSeriesDetailsUseCase;

    private final AddToWatchlistUseCase addToWatchlistUseCase;

    private final RemoveFromWatchlistUseCase removeFromWatchlistUseCase;

    private final ChangeEpisodesWatchedFlagUseCase changeEpisodesWatchedFlagUseCase;

    public DetailsViewModelFactory(UseCaseHandler useCaseHandler, FetchAndSaveTvSeriesDetailsUseCase fetchAndSaveTvSeriesDetailsUseCase, GetTvSeriesDetailsUseCase getTvSeriesDetailsUseCase, AddToWatchlistUseCase addToWatchlistUseCase, RemoveFromWatchlistUseCase removeFromWatchlistUseCase, ChangeEpisodesWatchedFlagUseCase changeEpisodesWatchedFlagUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.fetchAndSaveTvSeriesDetailsUseCase = fetchAndSaveTvSeriesDetailsUseCase;
        this.getTvSeriesDetailsUseCase = getTvSeriesDetailsUseCase;
        this.addToWatchlistUseCase = addToWatchlistUseCase;
        this.removeFromWatchlistUseCase = removeFromWatchlistUseCase;
        this.changeEpisodesWatchedFlagUseCase = changeEpisodesWatchedFlagUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            return (T) new DetailsViewModel(useCaseHandler, fetchAndSaveTvSeriesDetailsUseCase, getTvSeriesDetailsUseCase, addToWatchlistUseCase, removeFromWatchlistUseCase, changeEpisodesWatchedFlagUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
