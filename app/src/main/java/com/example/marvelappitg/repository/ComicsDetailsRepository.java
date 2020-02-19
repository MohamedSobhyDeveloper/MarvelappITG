package com.example.marvelappitg.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelappitg.models.modelcomicsdetails.ModelComicsDetails;
import com.example.marvelappitg.retrofit.ApiRequest;
import com.example.marvelappitg.retrofit.RetrofitRequest;
import com.example.marvelappitg.utlitites.Constant;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ComicsDetailsRepository {
    private static final String TAG = ComicsDetailsRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public ComicsDetailsRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ModelComicsDetails> getComicsDetails(HashMap<String, String> meMap) {
        final MutableLiveData<ModelComicsDetails> data = new MutableLiveData<>();
        apiRequest.getcomicsDetails(meMap.get(Constant.url),meMap.get(Constant.ts),meMap.get(Constant.apikey),meMap.get(Constant.hash))
                .enqueue(new Callback<ModelComicsDetails>() {


                    @Override
                    public void onResponse(Call<ModelComicsDetails> call, Response<ModelComicsDetails> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<ModelComicsDetails> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}