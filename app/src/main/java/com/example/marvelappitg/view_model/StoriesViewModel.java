package com.example.marvelappitg.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.marvelappitg.models.modelstoriesdetails.ModelStoriesDetails;
import com.example.marvelappitg.repository.StoriesDetailsRepository;

import java.util.HashMap;


public class StoriesViewModel extends AndroidViewModel {

    private StoriesDetailsRepository storiesDetailsRepository;
    private LiveData<ModelStoriesDetails> modelStoriesDetailsLiveData;


    public StoriesViewModel(@NonNull Application application, HashMap<String, String> meMap) {
        super(application);
        storiesDetailsRepository = new StoriesDetailsRepository();
        this.modelStoriesDetailsLiveData = storiesDetailsRepository.getstoriesDetails(meMap);
    }

    public LiveData<ModelStoriesDetails> getStoriesResponseLiveData() {
        return modelStoriesDetailsLiveData;
    }
}