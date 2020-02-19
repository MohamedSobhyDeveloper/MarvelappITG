package com.example.marvelappitg.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelappitg.models.modelcomicsdetails.ModelComicsDetails;
import com.example.marvelappitg.models.modelstoriesdetails.ModelStoriesDetails;
import com.example.marvelappitg.retrofit.ApiRequest;
import com.example.marvelappitg.retrofit.RetrofitRequest;
import com.example.marvelappitg.utlitites.Constant;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StoriesDetailsRepository {
    private static final String TAG = StoriesDetailsRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public StoriesDetailsRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ModelStoriesDetails> getstoriesDetails(HashMap<String, String> meMap) {
        final MutableLiveData<ModelStoriesDetails> data = new MutableLiveData<>();
        apiRequest.getstoriesDetails(meMap.get(Constant.url),meMap.get(Constant.ts),meMap.get(Constant.apikey),meMap.get(Constant.hash))
                .enqueue(new Callback<ModelStoriesDetails>() {


                    @Override
                    public void onResponse(Call<ModelStoriesDetails> call, Response<ModelStoriesDetails> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<ModelStoriesDetails> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}