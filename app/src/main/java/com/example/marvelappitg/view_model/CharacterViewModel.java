package com.example.marvelappitg.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.marvelappitg.models.modelcharacterlist.ModelCharacterList;
import com.example.marvelappitg.repository.CharacterRepository;

import java.util.HashMap;


public class CharacterViewModel extends ViewModel {

    private MutableLiveData<ModelCharacterList> articleResponseLiveData;


    public CharacterViewModel(@NonNull Application application, HashMap<String, String> meMap) {
        CharacterRepository characterRepository = new CharacterRepository();
        this.articleResponseLiveData = characterRepository.getCharacterList(meMap);
    }

    public MutableLiveData<ModelCharacterList> getCharacterResponseLiveData() {
        return articleResponseLiveData;
    }
}