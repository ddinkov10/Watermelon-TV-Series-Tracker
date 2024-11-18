package com.watermelon.UI.framework.common;


public interface UseCaseHandler {
    <T extends UseCase.RequestValues, R extends UseCase.ResponseValue>
        void execute(UseCase<T, R> useCase, T values, UseCaseCallback<R> callback);

    interface UseCaseCallback<R> {
        void onSuccess(R response);
        void onError(Exception exception);
    }
}
