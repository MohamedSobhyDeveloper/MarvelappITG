package com.example.marvelappitg.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelappitg.models.modelstoriesdetails.ModelStoriesDetails;
import com.example.marvelappitg.repository.StoriesDetailsRepository;

import java.util.HashMap;


public class StoriesViewModel extends AndroidViewModel {

    private MutableLiveData<ModelStoriesDetails> modelStoriesDetailsLiveData;


    public StoriesViewModel(@NonNull Application application, HashMap<String, String> meMap) {
        super(application);
        StoriesDetailsRepository storiesDetailsRepository = new StoriesDetailsRepository();
        this.modelStoriesDetailsLiveData = storiesDetailsRepository.getstoriesDetails(meMap);
    }

    public MutableLiveData<ModelStoriesDetails> getStoriesResponseLiveData() {
        return modelStoriesDetailsLiveData;
    }
}