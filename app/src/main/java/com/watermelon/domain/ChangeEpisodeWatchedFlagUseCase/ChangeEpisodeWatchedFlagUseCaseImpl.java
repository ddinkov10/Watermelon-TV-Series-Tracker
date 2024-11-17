package com.watermelon.domain.ChangeEpisodeWatchedFlagUseCase;

import com.watermelon.domain.repository.TvSeriesEpisodeRepository;

public class ChangeEpisodeWatchedFlagUseCaseImpl implements ChangeEpisodeWatchedFlagUseCase{

    private final TvSeriesEpisodeRepository tvSeriesEpisodeRepository;

    public ChangeEpisodeWatchedFlagUseCaseImpl(TvSeriesEpisodeRepository tvSeriesEpisodeRepository) {
        this.tvSeriesEpisodeRepository = tvSeriesEpisodeRepository;
    }

    @Override
    public ResponseValue executeUseCase(RequestValues requestValues) {
        tvSeriesEpisodeRepository.setTvSeriesEpisodeWatchedFlag(requestValues.getPair());
        return null;
    }
}
