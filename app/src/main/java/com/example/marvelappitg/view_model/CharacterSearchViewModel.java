package com.example.marvelappitg.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marvelappitg.models.modelcharacterlist.ModelCharacterList;
import com.example.marvelappitg.repository.CharacterRepository;
import com.example.marvelappitg.repository.CharacterSearchRepository;

import java.util.HashMap;


public class CharacterSearchViewModel extends AndroidViewModel {

    private CharacterSearchRepository characterSearchRepository;
    private LiveData<ModelCharacterList> articleResponseLiveData;


    public CharacterSearchViewModel(@NonNull Application application, HashMap<String, String> meMap) {
        super(application);
        characterSearchRepository = new CharacterSearchRepository();
        this.articleResponseLiveData = characterSearchRepository.getCharacterList(meMap);
    }

    public LiveData<ModelCharacterList> getCharacterSearchResponseLiveData() {
        return articleResponseLiveData;
    }
}