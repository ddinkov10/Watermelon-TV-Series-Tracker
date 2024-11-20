package com.watermelon.UI.ViewModelFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.watermelon.UI.Watchlist.WatchlistViewModel;
import com.watermelon.UI.framework.common.UseCaseHandler;
import com.watermelon.domain.usecase.ChangeEpisodeWatchedFlagUseCase;
import com.watermelon.domain.usecase.GetWatchlistUseCase;

public class WatchlistViewModelFactory implements ViewModelProvider.Factory {

    private UseCaseHandler useCaseHandler;
    private GetWatchlistUseCase getWatchlistUseCase;

    private ChangeEpisodeWatchedFlagUseCase changeEpisodeWatchedFlagUseCase;

    public WatchlistViewModelFactory(UseCaseHandler useCaseHandler, GetWatchlistUseCase getWatchlistUseCase, ChangeEpisodeWatchedFlagUseCase changeEpisodeWatchedFlagUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getWatchlistUseCase = getWatchlistUseCase;
        this.changeEpisodeWatchedFlagUseCase = changeEpisodeWatchedFlagUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(WatchlistViewModel.class)) {
            return (T) new WatchlistViewModel(useCaseHandler, getWatchlistUseCase, changeEpisodeWatchedFlagUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
