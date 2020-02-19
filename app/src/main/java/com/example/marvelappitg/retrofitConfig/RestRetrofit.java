package com.example.marvelappitg.retrofitConfig;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class RestRetrofit {

    @SuppressLint("StaticFieldLeak")
    private static RestRetrofit instance;
    private static ApiCall apiService;


    private RestRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(6, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES);


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        //  httpClient.addInterceptor(interceptor).build();


        OkHttpClient httpClient = builder.build();
        String baseUrl = "https://gateway.marvel.com:443/v1/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        apiService = retrofit.create(ApiCall.class);
    }

    static RestRetrofit getInstance() {
        if (instance == null) {
            instance = new RestRetrofit();
        }
        return instance;
    }


    ApiCall getClientService() {
        return apiService;
    }
}