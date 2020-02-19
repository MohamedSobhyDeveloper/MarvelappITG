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
import com.example.marvelappitg.models.modelcharacterlist.Result;
import com.example.marvelappitg.retrofitConfig.HandelCalls;
import com.example.marvelappitg.retrofitConfig.HandleRetrofitResp;
import com.example.marvelappitg.utlitites.Constant;
import com.example.marvelappitg.utlitites.DataEnum;
import com.example.marvelappitg.utlitites.HelpMe;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements HandleRetrofitResp {

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
    ModelCharacterList modelCharacterList;
    int offset=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initializePagination();



        callCharacterList(offset);



    }



    private void initializePagination() {
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
        meMap.put("offset",offset+"");
        if (offset==0){
            HandelCalls.getInstance(this).call(DataEnum.CallCharacterList.name(), meMap,"", true, this);
        }else {
            HandelCalls.getInstance(this).call(DataEnum.CallCharacterListMore.name(), meMap,"", true, this);
        }

    }
    //endregion



    
    //region Call Response Api

    @Override
    public void onResponseSuccess(String flag, Object o) {
        if (flag.equals(DataEnum.CallCharacterList.name())){
            modelCharacterList = (ModelCharacterList) o;
            getCharacterList(modelCharacterList.getData().getResults());
            if (modelCharacterList.getData().getOffset()<=modelCharacterList.getData().getTotal()){
                loading=true;
                offset=offset+20;
            }
        }else {
            modelCharacterList = (ModelCharacterList) o;
            getCharacterListmore(modelCharacterList.getData());
        }


    }




    @Override
    public void onResponseFailure(String flag, String o) {

    }

    @Override
    public void onNoContent(String flag, int code) {

    }


    //endregion



    //region Call Actions
    private void getCharacterList(List<Result> results) {
        characterListRecycler.setLayoutManager(layoutManager);
        characterListAdapter = new CharacterListAdapter(results, this);
        characterListRecycler.setAdapter(characterListAdapter);

    }

    private void getCharacterListmore(Data data) {
        characterListAdapter.addAll(data.getResults());
        characterListAdapter.notifyDataSetChanged();
        if (data.getOffset()<=data.getTotal()){
            loading=true;
            offset=offset+20;
        }
    }


    //endregion




    @OnClick(R.id.searchBtn)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }






}
