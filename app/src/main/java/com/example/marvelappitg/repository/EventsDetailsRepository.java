package com.example.marvelappitg.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelappitg.models.modelcomicsdetails.ModelComicsDetails;
import com.example.marvelappitg.models.modeleventdetails.ModelEventDetails;
import com.example.marvelappitg.retrofit.ApiRequest;
import com.example.marvelappitg.retrofit.RetrofitRequest;
import com.example.marvelappitg.utlitites.Constant;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventsDetailsRepository {
    private static final String TAG = EventsDetailsRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public EventsDetailsRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ModelEventDetails> geteventDetails(HashMap<String, String> meMap) {
        final MutableLiveData<ModelEventDetails> data = new MutableLiveData<>();
        apiRequest.geteventsDetails(meMap.get(Constant.url),meMap.get(Constant.ts),meMap.get(Constant.apikey),meMap.get(Constant.hash))
                .enqueue(new Callback<ModelEventDetails>() {


                    @Override
                    public void onResponse(Call<ModelEventDetails> call, Response<ModelEventDetails> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<ModelEventDetails> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}