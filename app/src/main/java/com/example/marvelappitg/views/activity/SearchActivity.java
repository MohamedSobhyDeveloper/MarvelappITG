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
import com.example.marvelappitg.models.modelcharacterlist.ModelCharacterList;
import com.example.marvelappitg.models.modelcharacterlist.Result;
import com.example.marvelappitg.retrofitConfig.HandelCalls;
import com.example.marvelappitg.retrofitConfig.HandleRetrofitResp;
import com.example.marvelappitg.utlitites.DataEnum;
import com.example.marvelappitg.utlitites.HelpMe;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements HandleRetrofitResp {

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
    ModelCharacterList modelCharacterList;
    int offset=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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
                            callCharacterList(offset,searchword.getText().toString());

                            loading = false;
                        }
                    }
                }
            }

        });


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

    private void callCharacterList(int offset, String searchword) {
        if (offset==0) {
            Long tsLong = System.currentTimeMillis() / 1000;
            String ts = tsLong.toString();
            HashMap<String, String> meMap = new HashMap<>();
            meMap.put("apikey", "f2d587b7acc1cf9ae8d86fdcde51f394");
            meMap.put("ts", ts);
            meMap.put("hash", HelpMe.md5(ts + "b5abf01b39744e74f81d1079fa04a3b3a51c9b08" + "f2d587b7acc1cf9ae8d86fdcde51f394"));
            meMap.put("offset", offset + "");
            meMap.put("nameStartsWith", searchword);
            HandelCalls.getInstance(this).call(DataEnum.CallCharacterSearch.name(), meMap, "", true, this);
        }else {
            Long tsLong = System.currentTimeMillis() / 1000;
            String ts = tsLong.toString();
            HashMap<String, String> meMap = new HashMap<>();
            meMap.put("apikey", "f2d587b7acc1cf9ae8d86fdcde51f394");
            meMap.put("ts", ts);
            meMap.put("hash", HelpMe.md5(ts + "b5abf01b39744e74f81d1079fa04a3b3a51c9b08" + "f2d587b7acc1cf9ae8d86fdcde51f394"));
            meMap.put("offset", offset + "");
            meMap.put("nameStartsWith", searchword);
            HandelCalls.getInstance(this).call(DataEnum.CallCharacterSearchMore.name(), meMap, "", true, this);
        }
    }

    @OnClick(R.id.cancelbtn)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onResponseSuccess(String flag, Object o) {
        if (flag.equals(DataEnum.CallCharacterSearch.name())){
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

    private void getCharacterList(List<Result> results) {
        if (results!=null&&results.size()>0){
            characterListRecycler.setLayoutManager(layoutManager);
            characterListAdapter = new CharacterListAdapter(results, this);
            characterListRecycler.setAdapter(characterListAdapter);
            characterListAdapter.notifyDataSetChanged();
        }


    }

    private void getCharacterListmore(Data data) {
        characterListAdapter.addAll(data.getResults());
        characterListAdapter.notifyDataSetChanged();
        if (data.getOffset()<=data.getTotal()){
            loading=true;
            offset=offset+20;
        }
    }
}
