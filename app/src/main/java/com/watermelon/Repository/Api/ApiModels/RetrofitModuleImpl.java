package com.watermelon.Repository.Api.ApiModels;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitModuleImpl implements RetrofitModule {


    private final Retrofit retrofit;

    public RetrofitModuleImpl(String baseURL, OkHttpClient okHttpClient) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }

}
