package com.example.marvelappitg.views.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelappitg.R;
import com.example.marvelappitg.adapters.CharacterListAdapter;
import com.example.marvelappitg.models.modelcharacterlist.Data;
import com.example.marvelappitg.utlitites.Constant;
import com.example.marvelappitg.utlitites.HelpMe;
import com.example.marvelappitg.utlitites.Loading;
import com.example.marvelappitg.view_model.CharacterSearchViewModel;
import com.example.marvelappitg.view_model.CharacterViewModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.searchword)
    EditText searchword;
    @BindView(R.id.cancelbtn)
    TextView cancelbtn;
    @BindView(R.id.character_list_recycler)
    RecyclerView characterListRecycler;
    CharacterListAdapter characterListAdapter;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    private boolean loading = false;
    int offset=0;
    CharacterSearchViewModel characterViewModel;

    Loading loadingview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);


        initailizePagination();



        initializeSearch();





    }


    private void initailizePagination() {
        loadingview=new Loading(this);

        characterListRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            callCharacterList(offset,searchword.getText().toString());

                            loading = false;
                        }
                    }
                }
            }

        });
    }


    private void initializeSearch() {
        searchword.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length()>1){
                    offset=0;
                    callCharacterList(offset,searchword.getText().toString());

                }

            }
        });
    }



    //region Call API
    private void callCharacterList(int offset, String searchword) {
        long tsLong = System.currentTimeMillis() / 1000;
        String ts = Long.toString(tsLong);
        HashMap<String, String> meMap = new HashMap<>();
        meMap.put("apikey", Constant.publicKey);
        meMap.put("ts", ts);
        meMap.put("hash", HelpMe.md5(ts + Constant.privateKey + Constant.publicKey));
        meMap.put("offset", offset + "");
        meMap.put("nameStartsWith", searchword);
        if (offset==0){
            loadingview.show();
            characterViewModel=new CharacterSearchViewModel(this.getApplication(),meMap);
            getCharacterList();
        }else {
            loadingview.show();
            characterViewModel=new CharacterSearchViewModel(this.getApplication(),meMap);
            getCharacterListMore();
        }
    }
    private void getCharacterList() {
        characterViewModel.getCharacterSearchResponseLiveData().observe(this, (modelCharacterList) -> {
            if (modelCharacterList!=null){
                getCharacterList(modelCharacterList.getData());
            }

        });
    }

    private void getCharacterListMore() {
        characterViewModel.getCharacterSearchResponseLiveData().observe(this, (modelCharacterList) -> {
            if (modelCharacterList!=null){
                CharacterListmore(modelCharacterList.getData());
            }

        });
    }
    //endregion







    //region Call Action
    private void getCharacterList(Data data) {
        loadingview.dismiss();
            characterListRecycler.setLayoutManager(layoutManager);
            characterListAdapter = new CharacterListAdapter(data.getResults(), this);
            characterListRecycler.setAdapter(characterListAdapter);
            characterListAdapter.notifyDataSetChanged();
         if (data.getOffset()<=data.getTotal()){
            loading=true;
            offset=offset+20;
        }

    }

    private void CharacterListmore(Data data) {
        loadingview.dismiss();
        characterListAdapter.addAll(data.getResults());
        characterListAdapter.notifyDataSetChanged();
        if (data.getOffset()<=data.getTotal()){
            loading=true;
            offset=offset+20;
        }
    }

    //endregion





    @OnClick(R.id.cancelbtn)
    public void onViewClicked() {
        finish();
    }

}
