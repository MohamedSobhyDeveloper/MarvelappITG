package com.example.marvelappitg.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelappitg.models.modelseriesdetails.ModelSeriesDetails;
import com.example.marvelappitg.repository.SeriesDetailsRepository;

import java.util.HashMap;


public class SeriesViewModel extends AndroidViewModel {

    private MutableLiveData<ModelSeriesDetails> modelSeriesDetailsLiveData;


    public SeriesViewModel(@NonNull Application application, HashMap<String, String> meMap) {
        super(application);
        SeriesDetailsRepository seriesDetailsRepository = new SeriesDetailsRepository();
        this.modelSeriesDetailsLiveData = seriesDetailsRepository.getSeriesDetails(meMap);
    }

    public MutableLiveData<ModelSeriesDetails> getseriesResponseLiveData() {
        return modelSeriesDetailsLiveData;
    }
}