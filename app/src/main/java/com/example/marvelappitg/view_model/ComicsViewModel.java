package com.example.marvelappitg.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelappitg.models.modelcomicsdetails.ModelComicsDetails;
import com.example.marvelappitg.repository.ComicsDetailsRepository;

import java.util.HashMap;


public class ComicsViewModel extends AndroidViewModel {

    private MutableLiveData<ModelComicsDetails> modelComicsDetailsLiveData;


    public ComicsViewModel(@NonNull Application application, HashMap<String, String> meMap) {
        super(application);
        ComicsDetailsRepository comicsDetailsRepository = new ComicsDetailsRepository();
        this.modelComicsDetailsLiveData = comicsDetailsRepository.getComicsDetails(meMap);
    }

    public MutableLiveData<ModelComicsDetails> getComicsResponseLiveData() {
        return modelComicsDetailsLiveData;
    }
}