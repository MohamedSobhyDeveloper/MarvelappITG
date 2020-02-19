package com.example.marvelappitg.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marvelappitg.models.modelcharacterlist.ModelCharacterList;
import com.example.marvelappitg.models.modelseriesdetails.ModelSeriesDetails;
import com.example.marvelappitg.repository.CharacterRepository;
import com.example.marvelappitg.repository.SeriesDetailsRepository;

import java.util.HashMap;


public class SeriesViewModel extends AndroidViewModel {

    private SeriesDetailsRepository seriesDetailsRepository;
    private LiveData<ModelSeriesDetails> modelSeriesDetailsLiveData;


    public SeriesViewModel(@NonNull Application application, HashMap<String, String> meMap) {
        super(application);
        seriesDetailsRepository = new SeriesDetailsRepository();
        this.modelSeriesDetailsLiveData = seriesDetailsRepository.getSeriesDetails(meMap);
    }

    public LiveData<ModelSeriesDetails> getseriesResponseLiveData() {
        return modelSeriesDetailsLiveData;
    }
}