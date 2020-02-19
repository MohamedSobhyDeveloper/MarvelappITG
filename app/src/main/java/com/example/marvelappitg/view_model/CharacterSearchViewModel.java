package com.example.marvelappitg.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelappitg.models.modelcharacterlist.ModelCharacterList;
import com.example.marvelappitg.repository.CharacterSearchRepository;

import java.util.HashMap;


public class CharacterSearchViewModel extends AndroidViewModel {

    private MutableLiveData<ModelCharacterList> articleResponseLiveData;


    public CharacterSearchViewModel(@NonNull Application application, HashMap<String, String> meMap) {
        super(application);
        CharacterSearchRepository characterSearchRepository = new CharacterSearchRepository();
        this.articleResponseLiveData = characterSearchRepository.getCharacterList(meMap);
    }

    public MutableLiveData<ModelCharacterList> getCharacterSearchResponseLiveData() {
        return articleResponseLiveData;
    }
}