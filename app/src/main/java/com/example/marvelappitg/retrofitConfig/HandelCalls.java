package com.example.marvelappitg.retrofitConfig;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.marvelappitg.utlitites.Constant;
import com.example.marvelappitg.utlitites.DataEnum;
import com.example.marvelappitg.utlitites.HelpMe;
import com.example.marvelappitg.utlitites.Loading;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sobhy on 19/2/2020.
 */

public class HandelCalls {


    @SuppressLint("StaticFieldLeak")
    private static Context context;
    @SuppressLint("StaticFieldLeak")
    private static HandelCalls instance = null;
    @SuppressLint("StaticFieldLeak")
    private static RestRetrofit restRetrofit;
    private HandleRetrofitResp onRespnse;


    public static HandelCalls getInstance(Context context) {

        HandelCalls.context = context;

        if (instance == null) {
            instance = new HandelCalls();
            restRetrofit = RestRetrofit.getInstance(context);
        }
        return instance;
    }


    public void call(final String flag, HashMap<String, String> meMap,String url, Boolean ShowLoadingDialog, HandleRetrofitResp onRespnseSucess) {
        this.onRespnse = onRespnseSucess;

            if (flag.equals(DataEnum.CallCharacterList.name())) {
                String apikey = meMap.get(Constant.apikey);
                String hash = meMap.get(Constant.hash);
                String ts = meMap.get(Constant.ts);
                String offset = meMap.get(Constant.offset);
                callRetrofit(restRetrofit.getClientService().getCharacterList(ts,apikey,hash,offset), flag, ShowLoadingDialog);
              }else if (flag.equals(DataEnum.CallCharacterListMore.name())){
                String apikey = meMap.get(Constant.apikey);
                String hash = meMap.get(Constant.hash);
                String ts = meMap.get(Constant.ts);
                String offset = meMap.get(Constant.offset);
                callRetrofit(restRetrofit.getClientService().getCharacterList(ts,apikey,hash,offset), flag, ShowLoadingDialog);
            }else if (flag.equals(DataEnum.CallCharacterSearch.name())){
                String apikey = meMap.get(Constant.apikey);
                String hash = meMap.get(Constant.hash);
                String ts = meMap.get(Constant.ts);
                String offset = meMap.get(Constant.offset);
                String nameStartsWith = meMap.get(Constant.nameStartsWith);
                callRetrofit(restRetrofit.getClientService().getCharacterList(ts,apikey,hash,offset,nameStartsWith), flag, ShowLoadingDialog);
            }else if (flag.equals(DataEnum.CallCharacterSearchMore.name())){
                String apikey = meMap.get(Constant.apikey);
                String hash = meMap.get(Constant.hash);
                String ts = meMap.get(Constant.ts);
                String offset = meMap.get(Constant.offset);
                String nameStartsWith = meMap.get(Constant.nameStartsWith);
                callRetrofit(restRetrofit.getClientService().getCharacterList(ts,apikey,hash,offset,nameStartsWith), flag, ShowLoadingDialog);
            }
            else if (flag.equals(DataEnum.CallComicsDetails.name())) {
                String apikey = meMap.get(Constant.apikey);
                String hash = meMap.get(Constant.hash);
                String ts = meMap.get(Constant.ts);
            callRetrofit(restRetrofit.getClientService().getcomicsDetails(url,ts,apikey,hash), flag, ShowLoadingDialog);
             } else if (flag.equals(DataEnum.CallseriesDetails.name())) {
                String apikey = meMap.get(Constant.apikey);
                String hash = meMap.get(Constant.hash);
                String ts = meMap.get(Constant.ts);
                callRetrofit(restRetrofit.getClientService().getseriesDetails(url,ts,apikey,hash), flag, ShowLoadingDialog);
            } else if (flag.equals(DataEnum.CallstoriesDetails.name())) {
                String apikey = meMap.get(Constant.apikey);
                String hash = meMap.get(Constant.hash);
                String ts = meMap.get(Constant.ts);
                callRetrofit(restRetrofit.getClientService().getstoriesDetails(url,ts,apikey,hash), flag, ShowLoadingDialog);
            } else if (flag.equals(DataEnum.CalleventDetails.name())) {
                String apikey = meMap.get(Constant.apikey);
                String hash = meMap.get(Constant.hash);
                String ts = meMap.get(Constant.ts);
                callRetrofit(restRetrofit.getClientService().geteventsDetails(url,ts,apikey,hash), flag, ShowLoadingDialog);
            }

    }




    private <T> void callRetrofit(Call<T> call, final String flag, final Boolean ShowDialog) {

        final Loading progressDialog = new Loading(context);
        if (ShowDialog) {
            if (progressDialog!=null) {
                progressDialog.show();
            }
        }
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (ShowDialog) {
                    if (progressDialog!=null&&progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
                if (response.code()==204){
                    onRespnse.onNoContent(flag,response.code());
                }else if (response.isSuccessful() && response.body() != null) {
                        if (onRespnse != null)
                        Objects.requireNonNull(onRespnse).onResponseSuccess(flag, response.body());

                    } else {
                    if (onRespnse != null) {
                        try {
                            onRespnse.onResponseFailure(flag, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                if (ShowDialog)
                    if (progressDialog!=null&&progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        HelpMe.getInstance(context).retrofironFailure(t);
                    }

            }
        });

    }


}
