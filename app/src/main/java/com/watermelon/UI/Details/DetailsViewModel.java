package com.watermelon.UI.Details;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.UI.UiState;
import com.watermelon.domain.ChangeEpisodeWatchedFlagUseCase.ChangeEpisodeWatchedFlagUseCase;
import com.watermelon.domain.common.UseCaseHandler;
import com.watermelon.domain.usecase.FetchAndSaveTvSeriesDetailsUseCase;
import com.watermelon.domain.usecase.FetchTvSeriesDetailsUseCase;
import com.watermelon.domain.usecase.GetTvSeriesDetailsUseCase;

import java.util.List;

public class DetailsViewModel extends ViewModel {


    private final UseCaseHandler useCaseHandler;
    private final FetchAndSaveTvSeriesDetailsUseCase fetchAndSaveTvSeriesDetailsUseCase;
    private final GetTvSeriesDetailsUseCase getTvSeriesDetailsUseCase;

    private final ChangeEpisodeWatchedFlagUseCase changeEpisodeWatchedFlagUseCase;

    private final MutableLiveData<UiState> uiState;
    private final MutableLiveData<TvSeriesFull> detailsObservable;
    private final MutableLiveData<Integer> tvSeriesId;

    public DetailsViewModel(UseCaseHandler useCaseHandler, FetchAndSaveTvSeriesDetailsUseCase fetchAndSaveTvSeriesDetailsUseCase, GetTvSeriesDetailsUseCase getTvSeriesDetailsUseCase, ChangeEpisodeWatchedFlagUseCase changeEpisodeWatchedFlagUseCase) {

        this.useCaseHandler = useCaseHandler;
        this.fetchAndSaveTvSeriesDetailsUseCase = fetchAndSaveTvSeriesDetailsUseCase;
        this.getTvSeriesDetailsUseCase = getTvSeriesDetailsUseCase;
        this.changeEpisodeWatchedFlagUseCase = changeEpisodeWatchedFlagUseCase;

        uiState = new MutableLiveData<>();
        tvSeriesId = new MutableLiveData<>();
        detailsObservable = new MutableLiveData<>();
//        detailsObservable = Transformations.switchMap(tvSeriesId, id -> repository.fetchTvSeriesDetails((Integer) tvSeriesId.getValue()));

    }

    void setTvSeriesId(int id) {
        tvSeriesId.setValue(id);
        getTvSeriesFull(id, true);
        fetchTvSeriesDetails(id);
    }

    private void fetchTvSeriesDetails(int id) {
        useCaseHandler.execute(fetchAndSaveTvSeriesDetailsUseCase, new FetchAndSaveTvSeriesDetailsUseCase.RequestValues(id), new UseCaseHandler.UseCaseCallback<FetchAndSaveTvSeriesDetailsUseCase.ResponseValue>() {
            @Override
            public void onSuccess(FetchAndSaveTvSeriesDetailsUseCase.ResponseValue response) {
                getTvSeriesFull(id, false);
            }

            @Override
            public void onError(Exception exception) {
                uiState.setValue(UiState.ERROR);
            }
        });
    }

    private void getTvSeriesFull(int id, boolean shouldEmitStatus) {
        if(shouldEmitStatus) {
            uiState.setValue(UiState.LOADING);
        }
        useCaseHandler.execute(getTvSeriesDetailsUseCase, new GetTvSeriesDetailsUseCase.RequestValues(id), new UseCaseHandler.UseCaseCallback<GetTvSeriesDetailsUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetTvSeriesDetailsUseCase.ResponseValue response) {
                if (response.getTvSeriesFull() != null) {
                    detailsObservable.setValue(response.getTvSeriesFull());
                }
                if(shouldEmitStatus) {
                    uiState.setValue(UiState.SUCCESS);
                }
            }

            @Override
            public void onError(Exception exception) {
                if(shouldEmitStatus) {
                    uiState.setValue(UiState.ERROR);
                }
            }
        });
    }

    LiveData<TvSeriesFull> getDetailsObservable() {
        return detailsObservable;
    }

    LiveData<UiState> getUiState() {
        return uiState;
    }

    void changeAllSeasonsWatchedFlag(Pair<List<Integer>, Boolean> params) {
//        repository.setTvSeriesAllSeasonWatched(params);

    }

    void changeTvSeriesWatchedFlag(Pair<Integer, Boolean> params) {
        useCaseHandler.execute(changeEpisodeWatchedFlagUseCase, new ChangeEpisodeWatchedFlagUseCase.RequestValues(params), new UseCaseHandler.UseCaseCallback<ChangeEpisodeWatchedFlagUseCase.ResponseValue>() {
            @Override
            public void onSuccess(ChangeEpisodeWatchedFlagUseCase.ResponseValue response) {

            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }


}
