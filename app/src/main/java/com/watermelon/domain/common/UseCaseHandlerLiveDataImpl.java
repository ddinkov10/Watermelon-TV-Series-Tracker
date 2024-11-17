package com.watermelon.domain.common;

public class UseCaseHandlerLiveDataImpl implements UseCaseHandler{

    @Override
    public <T extends UseCase.RequestValues, R extends UseCase.ResponseValue> void execute(UseCase<T, R> useCase, T values, UseCaseCallback<R> callback) {

    }
}
