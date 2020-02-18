package com.example.marvelappitg.retrofitConfig;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestRetrofit {

    private static final String TAG = RestRetrofit.class.getSimpleName();

    @SuppressLint("StaticFieldLeak")
    private static RestRetrofit instance;
    private static ApiCall apiService;
    @SuppressLint("StaticFieldLeak")
    private static Context mcontext;

    public static String BaseUrl = "https://gateway.marvel.com:443/v1/";


    private RestRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(6, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES);


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        //  httpClient.addInterceptor(interceptor).build();


        OkHttpClient httpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        apiService = retrofit.create(ApiCall.class);
    }

    public static RestRetrofit getInstance(Context context) {
        mcontext = context;
        if (instance == null) {
            instance = new RestRetrofit();
        }
        return instance;
    }

    public static void inititobj() {
       instance=null;

    }



    ApiCall getClientService() {
        return apiService;
    }
}