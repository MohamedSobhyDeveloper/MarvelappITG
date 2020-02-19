package com.example.marvelappitg.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marvelappitg.models.modelcharacterlist.ModelCharacterList;
import com.example.marvelappitg.models.modeleventdetails.ModelEventDetails;
import com.example.marvelappitg.repository.CharacterRepository;
import com.example.marvelappitg.repository.EventsDetailsRepository;

import java.util.HashMap;


public class EventsViewModel extends AndroidViewModel {

    private EventsDetailsRepository eventsDetailsRepository;
    private LiveData<ModelEventDetails> modelEventDetailsLiveData;


    public EventsViewModel(@NonNull Application application, HashMap<String, String> meMap) {
        super(application);
        eventsDetailsRepository = new EventsDetailsRepository();
        this.modelEventDetailsLiveData = eventsDetailsRepository.geteventDetails(meMap);
    }

    public LiveData<ModelEventDetails> getEventsResponseLiveData() {
        return modelEventDetailsLiveData;
    }
}