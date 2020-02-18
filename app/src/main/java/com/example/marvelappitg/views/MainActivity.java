package com.example.marvelappitg.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelappitg.R;
import com.example.marvelappitg.adapters.CharacterListAdapter;
import com.example.marvelappitg.models.Data;
import com.example.marvelappitg.models.ModelCharacterList;
import com.example.marvelappitg.models.Result;
import com.example.marvelappitg.retrofitConfig.HandelCalls;
import com.example.marvelappitg.retrofitConfig.HandleRetrofitResp;
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

        callCharacterList(offset);



    }

    private void callCharacterList(int offset) {
        if (offset==0){
            Long tsLong = System.currentTimeMillis() / 1000;
            String ts = tsLong.toString();
            HashMap<String, String> meMap = new HashMap<>();
            meMap.put("apikey", "f2d587b7acc1cf9ae8d86fdcde51f394");
            meMap.put("ts", ts);
            meMap.put("hash", HelpMe.md5(ts + "b5abf01b39744e74f81d1079fa04a3b3a51c9b08" + "f2d587b7acc1cf9ae8d86fdcde51f394"));
            meMap.put("offset",offset+"");
            HandelCalls.getInstance(this).call(DataEnum.CallCharacterList.name(), meMap,"", true, this);
        }else {
            Long tsLong = System.currentTimeMillis() / 1000;
            String ts = tsLong.toString();
            HashMap<String, String> meMap = new HashMap<>();
            meMap.put("apikey", "f2d587b7acc1cf9ae8d86fdcde51f394");
            meMap.put("ts", ts);
            meMap.put("hash", HelpMe.md5(ts + "b5abf01b39744e74f81d1079fa04a3b3a51c9b08" + "f2d587b7acc1cf9ae8d86fdcde51f394"));
            meMap.put("offset",offset+"");
            HandelCalls.getInstance(this).call(DataEnum.CallCharacterListMore.name(), meMap,"", true, this);
        }


    }

    @OnClick(R.id.searchBtn)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }
    
    
    


    @Override
    public void onResponseSuccess(String flag, Object o) {
        if (flag.equals(DataEnum.CallCharacterList.name())){
            modelCharacterList = (ModelCharacterList) o;
            getCharacterList(modelCharacterList.getData().getResults());
            if (modelCharacterList.getData().getCount()<=modelCharacterList.getData().getTotal()){
                loading=true;
                offset=offset+1;
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


    private void getCharacterList(List<Result> results) {
        characterListRecycler.setLayoutManager(layoutManager);
        characterListAdapter = new CharacterListAdapter(results, this);
        characterListRecycler.setAdapter(characterListAdapter);


    }

    private void getCharacterListmore(Data data) {
        characterListAdapter.addAll(data.getResults());
        characterListAdapter.notifyDataSetChanged();
        if (data.getCount()<=data.getTotal()){
            loading=true;
            offset=offset+1;
        }
    }


}
