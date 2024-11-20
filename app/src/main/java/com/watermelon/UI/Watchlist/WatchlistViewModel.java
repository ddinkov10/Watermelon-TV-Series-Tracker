package com.watermelon.UI.Watchlist;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.watermelon.Models.TvSeriesFull;
import com.watermelon.UI.WatermelonActivity;
import com.watermelon.domain.usecase.ChangeEpisodeWatchedFlagUseCase;
import com.watermelon.domain.usecase.GetWatchlistUseCase;
import com.watermelon.UI.framework.common.UseCaseHandler;

import java.util.List;

public class WatchlistViewModel extends ViewModel {

    private UseCaseHandler useCaseHandler;
    private GetWatchlistUseCase getWatchlistUseCase;

    private ChangeEpisodeWatchedFlagUseCase changeEpisodeWatchedFlagUseCase;
    private MutableLiveData<List<TvSeriesFull>> watchlistList;

    public WatchlistViewModel(UseCaseHandler useCaseHandler, GetWatchlistUseCase getWatchlistUseCase, ChangeEpisodeWatchedFlagUseCase changeEpisodeWatchedFlagUseCase) {

        this.useCaseHandler  = useCaseHandler;
        this.getWatchlistUseCase = getWatchlistUseCase;
        this.changeEpisodeWatchedFlagUseCase = changeEpisodeWatchedFlagUseCase;
        this.watchlistList = new MutableLiveData<>();
    }

    public void loadWatchlist() {
        useCaseHandler.execute(getWatchlistUseCase, new GetWatchlistUseCase.RequestValues(WatermelonActivity.TVSERIES_WATCHED_FLAG_YES), new UseCaseHandler.UseCaseCallback<GetWatchlistUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetWatchlistUseCase.ResponseValue response) {
                updateWatchlist(response.getWatchlistList());
            }

            @Override
            public void onError(Exception exception) {
                throw new RuntimeException(exception);
            }
        });
    }

    private void updateWatchlist(List<TvSeriesFull> list) {
        watchlistList.setValue(list);
    }

    LiveData<List<TvSeriesFull>> getWatchlistListObservable() {
        return watchlistList;
    }

    void changeEpisodeWatchedFlag(Pair<Integer, Boolean> params) {
        useCaseHandler.execute(changeEpisodeWatchedFlagUseCase, new ChangeEpisodeWatchedFlagUseCase.RequestValues(params), new UseCaseHandler.UseCaseCallback<ChangeEpisodeWatchedFlagUseCase.ResponseValue>() {
            @Override
            public void onSuccess(ChangeEpisodeWatchedFlagUseCase.ResponseValue response) {
                loadWatchlist();
            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }


}
