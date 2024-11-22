package com.watermelon.domain.usecase;

import com.watermelon.domain.repository.TvSeriesEpisodeRepository;

public class ChangeEpisodesWatchedFlagUseCaseImpl implements ChangeEpisodesWatchedFlagUseCase{

    private final TvSeriesEpisodeRepository tvSeriesEpisodeRepository;

    public ChangeEpisodesWatchedFlagUseCaseImpl(TvSeriesEpisodeRepository tvSeriesEpisodeRepository) {
        this.tvSeriesEpisodeRepository = tvSeriesEpisodeRepository;
    }

    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {
        tvSeriesEpisodeRepository.setTvSeriesAllSeasonWatched(requestValues.getPair());
        return null;
    }
}
