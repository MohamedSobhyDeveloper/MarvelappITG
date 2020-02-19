package com.example.marvelappitg.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelappitg.R;
import com.example.marvelappitg.adapters.CharacterListAdapter;
import com.example.marvelappitg.models.modelcharacterlist.Data;
import com.example.marvelappitg.models.modelcharacterlist.ModelCharacterList;
import com.example.marvelappitg.utlitites.Constant;
import com.example.marvelappitg.utlitites.HelpMe;
import com.example.marvelappitg.utlitites.Loading;
import com.example.marvelappitg.view_model.CharacterViewModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.searchBtn)
    ImageView searchBtn;
    @BindView(R.id.character_list_recycler)
    RecyclerView characterListRecycler;

    CharacterListAdapter characterListAdapter;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    private boolean loading = false;
    int offset = 0;
    CharacterViewModel characterViewModel;

    Loading loadingview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializePagination();
        callCharacterList(offset);
    }


    private void initializePagination() {
        loadingview = new Loading(this);

        characterListRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            callCharacterList(offset);
                            loading = false;
                        }
                    }
                }
            }

        });
    }

    //region Call Api
    private void callCharacterList(int offset) {
        long tsLong = System.currentTimeMillis() / 1000;
        String ts = Long.toString(tsLong);
        HashMap<String, String> meMap = new HashMap<>();
        meMap.put("apikey", Constant.publicKey);
        meMap.put("ts", ts);
        meMap.put("hash", HelpMe.md5(ts + Constant.privateKey + Constant.publicKey));
        meMap.put("offset", String.valueOf(offset));

        loadingview.show();
        characterViewModel = new CharacterViewModel(this.getApplication(), meMap);
        if (offset == 0) {
            getCharacterList();
        } else {
            getCharacterListMore();
        }

    }

    private void getCharacterList() {
        characterViewModel.getCharacterResponseLiveData().observe(this, (modelCharacterList) -> {
            if (modelCharacterList != null) {
                getCharacterList(modelCharacterList.getData());
            }

        });
    }

    private void getCharacterListMore() {
        characterViewModel.getCharacterResponseLiveData().observe(this, (modelCharacterList) -> {
            if (modelCharacterList != null) {
                CharacterListmore(modelCharacterList.getData());
            }

        });
    }
    //endregion


    //region Call Actions
    private void getCharacterList(Data data) {
        loadingview.dismiss();
        characterListRecycler.setLayoutManager(layoutManager);
        characterListAdapter = new CharacterListAdapter(data.getResults(), this);
        characterListRecycler.setAdapter(characterListAdapter);

        if (data.getOffset() <= data.getTotal()) {
            loading = true;
            offset = offset + 20;
        }

    }

    private void CharacterListmore(Data data) {
        loadingview.dismiss();
        characterListAdapter.addAll(data.getResults());
        characterListAdapter.notifyDataSetChanged();
        if (data.getOffset() <= data.getTotal()) {
            loading = true;
            offset = offset + 20;
        }
    }


    //endregion


    @OnClick(R.id.searchBtn)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }


}
