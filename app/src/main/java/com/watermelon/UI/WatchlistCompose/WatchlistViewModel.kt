package com.watermelon.UI.WatchlistCompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.watermelon.Models.TvSeriesFull
import com.watermelon.UI.framework.common.UseCaseHandler
import com.watermelon.domain.usecase.GetWatchlistUseCase
import java.lang.Exception

class WatchlistViewModel(
        private val useCaseHandler: UseCaseHandler,
        private val getWatchlistUseCase: GetWatchlistUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadData() {
        useCaseHandler.execute(getWatchlistUseCase, GetWatchlistUseCase.RequestValues(true), object : UseCaseHandler.UseCaseCallback<GetWatchlistUseCase.ResponseValue> {
            override fun onSuccess(response: GetWatchlistUseCase.ResponseValue?) {
                if (response != null) {
                    _uiState.value = UiState(response.watchlistList)
                }
            }

            override fun onError(exception: Exception?) {
                _uiState.value = UiState(emptyList())
            }
        })
    }
}

data class UiState(val data: List<TvSeriesFull>)