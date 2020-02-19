package com.example.marvelappitg.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelappitg.models.modelcharacterlist.ModelCharacterList;
import com.example.marvelappitg.retrofit.ApiRequest;
import com.example.marvelappitg.retrofit.RetrofitRequest;
import com.example.marvelappitg.utlitites.Constant;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CharacterRepository {
    private static final String TAG = CharacterRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public CharacterRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ModelCharacterList> getCharacterList(HashMap<String, String> meMap) {
        final MutableLiveData<ModelCharacterList> data = new MutableLiveData<>();
        apiRequest.getCharacterList(meMap.get(Constant.ts),meMap.get(Constant.apikey),meMap.get(Constant.hash),meMap.get(Constant.offset))
                .enqueue(new Callback<ModelCharacterList>() {


                    @Override
                    public void onResponse(Call<ModelCharacterList> call, Response<ModelCharacterList> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<ModelCharacterList> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}