package com.example.marvelappitg.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marvelappitg.models.modelcharacterlist.ModelCharacterList;
import com.example.marvelappitg.models.modelcomicsdetails.ModelComicsDetails;
import com.example.marvelappitg.repository.CharacterRepository;
import com.example.marvelappitg.repository.ComicsDetailsRepository;

import java.util.HashMap;


public class ComicsViewModel extends AndroidViewModel {

    private ComicsDetailsRepository comicsDetailsRepository;
    private LiveData<ModelComicsDetails> modelComicsDetailsLiveData;


    public ComicsViewModel(@NonNull Application application, HashMap<String, String> meMap) {
        super(application);
        comicsDetailsRepository = new ComicsDetailsRepository();
        this.modelComicsDetailsLiveData = comicsDetailsRepository.getComicsDetails(meMap);
    }

    public LiveData<ModelComicsDetails> getComicsResponseLiveData() {
        return modelComicsDetailsLiveData;
    }
}