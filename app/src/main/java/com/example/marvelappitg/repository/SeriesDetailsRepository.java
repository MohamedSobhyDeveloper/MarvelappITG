package com.example.marvelappitg.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelappitg.models.modelcomicsdetails.ModelComicsDetails;
import com.example.marvelappitg.models.modelseriesdetails.ModelSeriesDetails;
import com.example.marvelappitg.retrofit.ApiRequest;
import com.example.marvelappitg.retrofit.RetrofitRequest;
import com.example.marvelappitg.utlitites.Constant;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SeriesDetailsRepository {
    private static final String TAG = SeriesDetailsRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public SeriesDetailsRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ModelSeriesDetails> getSeriesDetails(HashMap<String, String> meMap) {
        final MutableLiveData<ModelSeriesDetails> data = new MutableLiveData<>();
        apiRequest.getseriesDetails(meMap.get(Constant.url),meMap.get(Constant.ts),meMap.get(Constant.apikey),meMap.get(Constant.hash))
                .enqueue(new Callback<ModelSeriesDetails>() {


                    @Override
                    public void onResponse(Call<ModelSeriesDetails> call, Response<ModelSeriesDetails> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<ModelSeriesDetails> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}