package com.watermelon.UI.framework.common;


/**
 * Runs {@link UseCase}s using a {@link UseCaseScheduler}.
 */
public class UseCaseHandlerImpl implements UseCaseHandler {


    private final UseCaseScheduler mUseCaseScheduler;

    public UseCaseHandlerImpl(UseCaseScheduler useCaseScheduler) {
        mUseCaseScheduler = useCaseScheduler;
    }

    @Override
    public <T extends UseCase.RequestValues, R extends UseCase.ResponseValue>
        void execute(final UseCase<T, R> useCase, final T values, final UseCaseCallback<R> callbackParameter) {

        final UiCallbackWrapper<R> callback = new UiCallbackWrapper<>(callbackParameter, this);
        // The network request might be handled in a different thread so make sure
        // Espresso knows that the app is busy until the response is handled.
//        EspressoIdlingResource.increment(); // App is busy until further notice


        mUseCaseScheduler.execute(new Runnable() {
            @Override
            public void run() {

                R ret = null;
                try {
                    ret = useCase.executeUseCase(values);
                }catch(Exception exception){
                    callback.onError(exception);
//                    EspressoIdlingResource.decrement();
                    return;
                }
                callback.onSuccess(ret);
//                EspressoIdlingResource.decrement(); // Set app as idle.

            }
        });
    }



    private <V extends UseCase.ResponseValue> void notifyResponse(final V response,
            final UseCaseCallback<V> useCaseCallback) {
        mUseCaseScheduler.notifyResponse(response, useCaseCallback);
    }


    private <V extends UseCase.ResponseValue> void notifyError(Exception exception,
                                                               final UseCaseCallback<V> useCaseCallback) {
        mUseCaseScheduler.onError(exception, useCaseCallback);
    }

    private static final class UiCallbackWrapper<V extends UseCase.ResponseValue> implements
            UseCaseCallback<V> {
        private final UseCaseCallback<V> mCallback;
        private final UseCaseHandlerImpl mUseCaseHandler;

        public UiCallbackWrapper(UseCaseCallback<V> callback,
                UseCaseHandlerImpl useCaseHandler) {
            mCallback = callback;
            mUseCaseHandler = useCaseHandler;
        }

        @Override
        public void onSuccess(V response) {
            mUseCaseHandler.notifyResponse(response, mCallback);
        }

        @Override
        public void onError(Exception exception) {
            mUseCaseHandler.notifyError(exception, mCallback);
        }
    }








}
