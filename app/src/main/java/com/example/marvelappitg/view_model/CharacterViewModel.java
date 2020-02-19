package com.example.marvelappitg.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marvelappitg.models.modelcharacterlist.ModelCharacterList;
import com.example.marvelappitg.repository.CharacterRepository;

import java.util.HashMap;


public class CharacterViewModel extends AndroidViewModel {

    private CharacterRepository characterRepository;
    private LiveData<ModelCharacterList> articleResponseLiveData;


    public CharacterViewModel(@NonNull Application application, HashMap<String, String> meMap) {
        super(application);
        characterRepository = new CharacterRepository();
        this.articleResponseLiveData = characterRepository.getCharacterList(meMap);
    }

    public LiveData<ModelCharacterList> getCharacterResponseLiveData() {
        return articleResponseLiveData;
    }
}