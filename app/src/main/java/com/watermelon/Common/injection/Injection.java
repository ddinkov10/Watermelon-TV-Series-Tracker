package com.watermelon.Common.injection;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;

import com.watermelon.Common.injection.framework.InjectionBase;
import com.watermelon.Repository.Api.ApiBuilder;
import com.watermelon.Repository.Api.ApiModels.RetrofitModule;
import com.watermelon.Repository.Api.ApiModels.RetrofitModuleImpl;
import com.watermelon.Repository.Api.ApiService;
import com.watermelon.Repository.AppDatabase;
import com.watermelon.Repository.TvSeriesEpisodeRepository.datasource.LocalTvEpisodeDataSource;
import com.watermelon.Repository.TvSeriesEpisodeRepository.datasource.LocalTvEpisodeDataSourceImpl;
import com.watermelon.Repository.TvSeriesEpisodeRepository.TvSeriesEpisodeRepositoryImpl;
import com.watermelon.Repository.TvSeriesFullRepository.datasource.LocalTvSeriesFullDataSource;
import com.watermelon.Repository.TvSeriesFullRepository.datasource.LocalTvSeriesFullDataSourceImpl;
import com.watermelon.Repository.TvSeriesFullRepository.TvSeriesFullRepositoryImpl;
import com.watermelon.Repository.TvSeriesFullRepository.datasource.RemoteTvSeriesFullDataSource;
import com.watermelon.Repository.TvSeriesFullRepository.datasource.RemoteTvSeriesFullDataSourceImpl;
import com.watermelon.Repository.TvSeriesRepository.TvSeriesRepositoryImpl;
import com.watermelon.Repository.TvSeriesRepository.datasource.LocalTvSeriesDataSource;
import com.watermelon.Repository.TvSeriesRepository.datasource.LocalTvSeriesDataSourceImpl;
import com.watermelon.WatermelonApplication;
import com.watermelon.domain.AddToWatchlistUseCase.AddToWatchlistUseCase;
import com.watermelon.domain.AddToWatchlistUseCase.AddToWatchlistUseCaseImpl;
import com.watermelon.domain.ChangeEpisodeWatchedFlagUseCase.ChangeEpisodeWatchedFlagUseCase;
import com.watermelon.domain.ChangeEpisodeWatchedFlagUseCase.ChangeEpisodeWatchedFlagUseCaseImpl;
import com.watermelon.domain.GetWatchlistUseCase.GetWatchlistUseCase;
import com.watermelon.domain.GetWatchlistUseCase.GetWatchlistUseCaseImpl;
import com.watermelon.domain.RemoveFromWatchlistUseCase.RemoveFromWatchlistUseCase;
import com.watermelon.domain.RemoveFromWatchlistUseCase.RemoveFromWatchlistUseCaseImpl;
import com.watermelon.domain.common.UseCaseHandler;
import com.watermelon.domain.common.UseCaseHandlerImpl;
import com.watermelon.domain.common.UseCaseThreadPoolScheduler;
import com.watermelon.domain.repository.TvSeriesEpisodeRepository;
import com.watermelon.domain.repository.TvSeriesFullRepository;
import com.watermelon.domain.repository.TvSeriesRepository;
import com.watermelon.domain.usecase.FetchAndSaveTvSeriesDetailsUseCase;
import com.watermelon.domain.usecase.FetchAndSaveTvSeriesDetailsUseCaseImpl;
import com.watermelon.domain.usecase.FetchTvSeriesDetailsUseCase;
import com.watermelon.domain.usecase.FetchTvSeriesDetailsUseCaseImpl;
import com.watermelon.domain.usecase.GetTvSeriesDetailsUseCase;
import com.watermelon.domain.usecase.GetTvSeriesDetailsUseCaseImpl;
import com.watermelon.domain.usecase.SaveTvSeriesDetailsUseCase;
import com.watermelon.domain.usecase.SaveTvSeriesDetailsUseCaseImpl;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


public class Injection extends InjectionBase {

    public static RemoveFromWatchlistUseCase provideRemoveFromWatchlistUseCase() {
        return new RemoveFromWatchlistUseCaseImpl(provideTvSeriesRepository());
    }

    public static AddToWatchlistUseCase provideAddToWatchlistUseCase() {
        return new AddToWatchlistUseCaseImpl(provideTvSeriesRepository());
    }

    public static TvSeriesRepository provideTvSeriesRepository() {
        return provideSingleton(TvSeriesRepository.class, new SingletonFactory<TvSeriesRepository>() {
            @Override
            public TvSeriesRepository create() {
                return new TvSeriesRepositoryImpl(provideLocalTvSeriesDataSource());
            }
        });
    }

    public static LocalTvSeriesDataSource provideLocalTvSeriesDataSource() {
        return new LocalTvSeriesDataSourceImpl(provideAppDatabase());
    }


    public static FetchAndSaveTvSeriesDetailsUseCase provideFetchAndSaveTvSeriesDetailsUseCase() {
        return new FetchAndSaveTvSeriesDetailsUseCaseImpl(provideFetchTvSeriesDetailsUseCase(), provideSaveTvSeriesDetailsUseCase());
    }

    public static SaveTvSeriesDetailsUseCase provideSaveTvSeriesDetailsUseCase() {
        return new SaveTvSeriesDetailsUseCaseImpl(provideTvSeriesFullRepository());
    }

    public static FetchTvSeriesDetailsUseCase provideFetchTvSeriesDetailsUseCase() {
        return new FetchTvSeriesDetailsUseCaseImpl(provideTvSeriesFullRepository());
    }

    public static GetTvSeriesDetailsUseCase provideGetTvSeriesDetailsUseCase() {
        return new GetTvSeriesDetailsUseCaseImpl(provideTvSeriesFullRepository());
    }


    public static ChangeEpisodeWatchedFlagUseCase provideChangeEpisodeWatchedFlagUseCase() {
        return new ChangeEpisodeWatchedFlagUseCaseImpl(provideTvSeriesEpisodeRepository());
    }

    public static TvSeriesEpisodeRepository provideTvSeriesEpisodeRepository() {
        return provideSingleton(TvSeriesEpisodeRepository.class, new SingletonFactory<TvSeriesEpisodeRepository>() {
            @Override
            public TvSeriesEpisodeRepository create() {
                return new TvSeriesEpisodeRepositoryImpl(provideLocalTvSeriesEpisodeDataSource());
            }
        });
    }

    public static LocalTvEpisodeDataSource provideLocalTvSeriesEpisodeDataSource() {
        return new LocalTvEpisodeDataSourceImpl(provideAppDatabase());
    }

    public static GetWatchlistUseCase provideGetWatchlistUseCase() {
        return new GetWatchlistUseCaseImpl(provideTvSeriesFullRepository());
    }

    public static LocalTvSeriesFullDataSource provideLocalTvSeriesFullDataSource() {
        return new LocalTvSeriesFullDataSourceImpl(provideAppDatabase());
    }

    public static RemoteTvSeriesFullDataSource provideRemoteTvSeriesFullDataSource() {
        return new RemoteTvSeriesFullDataSourceImpl(provideApiService());
    }

    public static ApiService provideApiService() {
        return provideRetrofit().getRetrofit().create(ApiService.class);
    }

    public static RetrofitModule provideRetrofit() {
        return provideSingleton(RetrofitModule.class, new SingletonFactory<RetrofitModule>() {
            @Override
            public RetrofitModule create() {
                return new RetrofitModuleImpl(provideBaseURL(), providesOkHttpClientBuilder());
            }
        });
    }

    public static String provideBaseURL() {
        return "https://www.episodate.com/api/";
    }

    private static OkHttpClient providesOkHttpClientBuilder(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return httpClient.readTimeout(1200, TimeUnit.SECONDS)
                .connectTimeout(1200, TimeUnit.SECONDS).build();

    }

    public static TvSeriesFullRepository provideTvSeriesFullRepository() {
        return provideSingleton(TvSeriesFullRepository.class, new SingletonFactory<TvSeriesFullRepository>() {
            @Override
            public TvSeriesFullRepository create() {
                return new TvSeriesFullRepositoryImpl(provideLocalTvSeriesFullDataSource(), provideRemoteTvSeriesFullDataSource());
            }
        });
    }

    public static AppDatabase provideAppDatabase() {
        return provideSingleton(AppDatabase.class, new SingletonFactory<AppDatabase>() {
            @Override
            public AppDatabase create() {
                return Room.databaseBuilder(provideAppContext(),
                        AppDatabase.class, "watermelon_db").openHelperFactory(provideSQLHelperFactory()).build();
            }
        });
    }

    private static FrameworkSQLiteOpenHelperFactory provideSQLHelperFactory(){

        return new FrameworkSQLiteOpenHelperFactory();
    }

    private static Context provideAppContext(){
        return WatermelonApplication.getAppContext();
    }

    private static Application provideApplication() {
        return WatermelonApplication.getApplication();
    }


    public static UseCaseHandler provideUseCaseHandler() {
        return provideSingleton(UseCaseHandler.class, new SingletonFactory<UseCaseHandler>() {
            @Override
            public UseCaseHandler create() {
                return new UseCaseHandlerImpl(new UseCaseThreadPoolScheduler());
            }
        });
    }



//    private static EventBus provideEventBus() {
//        return provideSingleton(EventBus.class, new SingletonFactory<EventBus>() {
//            @Override
//            public EventBus create() {
//                return new EventBusImp();
//            }
//        });
//    }

//    public static InfrastructureEventBus provideInfrastructureEventBus(){
//        EventBus bus = provideEventBus();
//        if (bus instanceof  InfrastructureEventBus) {
//            return (InfrastructureEventBus) provideEventBus();
//        }
//        throw new RuntimeException("EventBus should implement InfrastructureEventBus");
//
//
//    }



//    private static InfrastructureAuthenticationTokensRepository provideInfrastructureAuthenticationTokensRepository() {
//        AuthenticationTokensRepository repo = provideAuthenticationTokensRepository();
//        if (repo instanceof InfrastructureAuthenticationTokensRepository){
//            return (InfrastructureAuthenticationTokensRepository)repo;
//        }
//        throw new RuntimeException("AuthenticationTokensRepository should implement InfrastructureAuthenticationTokensRepository");
//    }


}
