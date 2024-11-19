package com.watermelon.UI.Watchlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.watermelon.Helpers.DateHelper;
import com.watermelon.Models.TvSeriesEpisode;
import com.watermelon.Models.TvSeriesFull;
import com.watermelon.Helpers.StringHelper;
import com.watermelon.Models.TvSeries;
import com.watermelon.Helpers.TvSeriesHelper;
import com.watermelon.R;
import com.squareup.picasso.Picasso;
import com.watermelon.Repository.model.Series;
import com.watermelon.Repository.model.watchlist.EpisodeWithWatchStatus;
import com.watermelon.Repository.model.watchlist.SeriesWithEpisodes;


import java.util.ArrayList;
import java.util.List;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.TvSeriesFullViewHolder>{

    public interface OnItemClickListener {
    void onItemClick(SeriesWithEpisodes tvSeriesFull);
    void onButtonClick(SeriesWithEpisodes tvSeriesFull, int position);
    }

    private List<SeriesWithEpisodes> tvSeries = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public TvSeriesFullViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.watchlist_item, parent, false);

        return new TvSeriesFullViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TvSeriesFullViewHolder holder, int position) {
        Series currentTvSeries = tvSeries.get(position).getSeries();
        int watched = TvSeriesHelper.getEpisodeProgress(tvSeries.get(position).getEpisodes());
        int progressMax = tvSeries.get(position).getEpisodes().size();

        Picasso.get()
                .load(currentTvSeries.getImagePath())
                .error(R.drawable.image_error_placeholder)
                .placeholder(R.drawable.image_loading_placeholder)
                .fit()
                .into(holder.textViewTvSeriesImageThumbnail);

        holder.textViewProgress.setText(progressMax - watched  + " remaining");
        holder.textViewTvSeriesName.setText(currentTvSeries.getTitle());
        holder.textViewTvSeriesCountry.setText(currentTvSeries.getCountry());
        holder.textViewTvSeriesNetwork.setText(currentTvSeries.getNetwork());
        holder.imageViewEpisodeWatched.setImageResource(R.drawable.ic_check);

        if(tvSeries.get(position).getEpisodes().size() == 0){
            holder.textViewEpisodeName.setText("no episodes avaible");
            holder.textViewEpisodeReleaseDate.setText("no episodes avaible");
        }else if(!TvSeriesHelper.getTvSeriesState(tvSeries.get(position).getEpisodes())) {
            EpisodeWithWatchStatus tvSeriesEpisode = TvSeriesHelper.getNextWatched(tvSeries.get(position).getEpisodes());
            holder.textViewEpisodeName.setText(StringHelper.addZero(tvSeriesEpisode.getEpisode().getSeasonNumber()) + "x" + StringHelper.addZero(tvSeriesEpisode.getEpisode().getEpisodeNumber())  + " " + tvSeriesEpisode.getEpisode().getTitle());
            holder.textViewEpisodeReleaseDate.setText(DateHelper.getDateString(tvSeriesEpisode.getEpisode().getAirDate()));
//        }
        }else {
            holder.textViewEpisodeName.setText("No more released episodes");
            holder.textViewEpisodeReleaseDate.setText("");
        }

            holder.textViewTvSeriesEpisodeProgress.setMax(progressMax);
            holder.textViewTvSeriesEpisodeProgress.setProgress(watched);

    }

    void setTvSeries(List<SeriesWithEpisodes> tvSeries) {
        this.tvSeries = tvSeries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(tvSeries != null) {
            return tvSeries.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }


    class TvSeriesFullViewHolder extends RecyclerView.ViewHolder {
        private ImageView textViewTvSeriesImageThumbnail;
        private TextView textViewTvSeriesName;
        private TextView textViewTvSeriesCountry;
        private TextView textViewTvSeriesNetwork;
        private ProgressBar textViewTvSeriesEpisodeProgress;
        private TextView textViewProgress;
        private TextView textViewEpisodeName;
        private TextView textViewEpisodeReleaseDate;
        private ImageView imageViewEpisodeWatched;

        private TvSeriesFullViewHolder(View itemView) {
            super(itemView);
            textViewTvSeriesImageThumbnail = itemView.findViewById(R.id.watchlist_image_thumbnail);
            textViewProgress = itemView.findViewById(R.id.watchlist_episode_progress_text);
            textViewTvSeriesCountry = itemView.findViewById(R.id.watchlist_tv_series_country);
            textViewTvSeriesNetwork = itemView.findViewById(R.id.text_view_watchlist_tv_series_network);
            imageViewEpisodeWatched = itemView.findViewById(R.id.episode_watch_button);
            textViewTvSeriesName = itemView.findViewById(R.id.watchlist_tv_series_name);
            textViewEpisodeName = itemView.findViewById(R.id.watchlist_episode_name);
            textViewEpisodeReleaseDate = itemView.findViewById(R.id.watchlist_episode_release_date);
            textViewTvSeriesEpisodeProgress = itemView.findViewById(R.id.watchlist_episode_progress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(tvSeries.get(position));
                    }
                }
            });

            imageViewEpisodeWatched.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onButtonClick(tvSeries.get(position), position);
                    }
                }
            });


        }
    }
    }


