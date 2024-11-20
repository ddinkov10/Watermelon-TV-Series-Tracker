package com.watermelon.UI.ViewModelFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.watermelon.UI.Statistics.StatisticsViewModel;
import com.watermelon.UI.framework.common.UseCaseHandler;
import com.watermelon.domain.usecase.GetStatisticsUseCase;

public class StatisticsViewModelFactory implements ViewModelProvider.Factory {

    private UseCaseHandler useCaseHandler;
    private GetStatisticsUseCase getStatisticsUseCase;

    public StatisticsViewModelFactory(UseCaseHandler useCaseHandler, GetStatisticsUseCase getStatisticsUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getStatisticsUseCase = getStatisticsUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(StatisticsViewModel.class)) {
            return (T) new StatisticsViewModel(useCaseHandler, getStatisticsUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
