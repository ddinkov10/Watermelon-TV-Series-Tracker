package com.watermelon.UI.WatchlistCompose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.watermelon.Common.injection.Injection

class WatchlistFragment : Fragment() {

    private lateinit var watchlistViewModel: WatchlistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val useCaseHandler = Injection.provideUseCaseHandler();
        val getWatchlistUseCase = Injection.provideGetWatchlistUseCase();

        watchlistViewModel = WatchlistViewModel(useCaseHandler, getWatchlistUseCase)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                WatchlistScreen(viewModel = watchlistViewModel)
            }
        }
    }
}