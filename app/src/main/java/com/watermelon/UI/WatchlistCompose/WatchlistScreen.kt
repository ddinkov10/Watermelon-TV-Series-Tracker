package com.watermelon.UI.WatchlistCompose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.watermelon.Common.injection.Injection
import com.watermelon.Helpers.DateHelper
import com.watermelon.Helpers.StringHelper
import com.watermelon.Helpers.TvSeriesHelper
import com.watermelon.Models.TvSeries
import com.watermelon.Models.TvSeriesFull


@Composable
fun WatchlistScreen(viewModel: WatchlistViewModel) {
    val uiState by viewModel.uiState.observeAsState(UiState(emptyList()))

    // Trigger data load when the screen is displayed
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
    ) {
        // Search Bar
//        OutlinedTextField(
//                value = "",
//                onValueChange = {},
//                label = { Text("Search") },
//                modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 16.dp)
//        )
        // List of Shows
        LazyColumn(
                verticalArrangement = Arrangement.spacedBy(1.dp),
                modifier = Modifier.fillMaxSize()
        ) {
            items(uiState.data) { item: TvSeriesFull ->
                val mapper = Mapper()
                val show =  mapper.toShow(item);
//                Log.d("ShowItem", "Show: $item")
                ShowItem(show)

//                Row(modifier = Modifier.fillMaxWidth()) {
//                    Text(text = show.name,
//                            color = Color.White)
//                }
            }
        }
    }
}
@Composable
fun ShowItem(show: Show) {
    Row(
            modifier = Modifier
                    .fillMaxWidth()
    ) {
        Image(
                painter = rememberImagePainter(show.imageUrl),
                contentDescription = null,
                modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
                modifier = Modifier.weight(1f)
        ) {
            Text(
                    text = show.name,
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
            )
            Text(
                    text = show.episodeName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Text(
                    text = show.date,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }

        // Progress Bar
//        Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center,
//                modifier = Modifier.padding(start = 8.dp)
//        ) {
//            Text(
//                    text = "${show.remaining} remaining",
//                    style = MaterialTheme.typography.bodyMedium
//            )
//            LinearProgressIndicator(
//                    progress = {
//                        show.remaining.toFloat() / show.progressMax.toFloat() // Arbitrary max value for progress
//                    },
//                    modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 8.dp)
//                            .height(4.dp),
//            )
//        }
    }
}

data class Show(
        val name: String,
        val episodeName: String,
        val date: String,
        val remaining: Int,
        val progressMax: Int,
        val network: String,
        val imageUrl: String
)

class Mapper(
) {
    fun toShow(item: TvSeriesFull) : Show {

        var name: String = item.tvSeries.tvSeriesName;
        var episodeName: String;
        var date: String
        var remaining: Int
        var network: String = item.tvSeries.tvSeriesNetwork;
        var imageUrl: String = item.tvSeries.tvSeriesImagePath


        val watched = TvSeriesHelper.getEpisodeProgress(item.episodes);
        remaining = watched
        val progressMax: Int = item.episodes.count();



        if (item.episodes.isEmpty()) {
            episodeName = "no episodes avaible"
            date = "no episodes avaible"
        } else if (!TvSeriesHelper.getTvSeriesState(item.episodes)) {
            val tvSeriesEpisode = TvSeriesHelper.getNextWatched(item.episodes)
            episodeName = StringHelper.addZero(tvSeriesEpisode.episodeSeasonNum) + "x" + StringHelper.addZero(tvSeriesEpisode.episodeNum) + " " + tvSeriesEpisode.episodeName
            date = DateHelper.getDateString(tvSeriesEpisode.episodeAirDate);
//        }
        } else {
            episodeName = "No more released episodes"
            date = ""
        }


        return Show(name, episodeName, date, remaining, progressMax, network, imageUrl)
    }
}

@Preview
@Composable
fun WatchlistScreenPreview() {
    WatchlistScreen(viewModel = WatchlistViewModel(Injection.provideUseCaseHandler(), Injection.provideGetWatchlistUseCase()));
}
