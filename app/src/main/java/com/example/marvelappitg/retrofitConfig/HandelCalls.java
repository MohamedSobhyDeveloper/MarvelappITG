package com.example.marvelappitg.retrofitConfig;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

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
 * Created by sobhy on 27/1/2020.
 */

public class HandelCalls {

    private static final String TAG = HandelCalls.class.getSimpleName();


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
    public static void inititobjHandcalls() {
        instance=null;

    }
    /**
     * @param onRespnseSucess
     */
    public void setonRespnseSucess(HandleRetrofitResp onRespnseSucess) {
        this.onRespnse = onRespnseSucess;
    }




    public void call(final String flag, HashMap<String, String> meMap,String url, Boolean ShowLoadingDialog, HandleRetrofitResp onRespnseSucess) {
        this.onRespnse = onRespnseSucess;

            if (flag.equals(DataEnum.CallCharacterList.name())) {
                String apikey = meMap.get("apikey");
                String hash = meMap.get("hash");
                String ts = meMap.get("ts");
                String offset = meMap.get("offset");

                callRetrofit(restRetrofit.getClientService().getCompanyInfo(ts,apikey,hash,offset), flag, ShowLoadingDialog);
              }else if (flag.equals(DataEnum.CallCharacterListMore.name())){
                String apikey = meMap.get("apikey");
                String hash = meMap.get("hash");
                String ts = meMap.get("ts");
                String offset = meMap.get("offset");
                callRetrofit(restRetrofit.getClientService().getCompanyInfo(ts,apikey,hash,offset), flag, ShowLoadingDialog);
            } else if (flag.equals(DataEnum.CallComicsDetails.name())) {
            callRetrofit(restRetrofit.getClientService().getcomicsDetails(url), flag, ShowLoadingDialog);
             } else if (flag.equals(DataEnum.CallseriesDetails.name())) {
                callRetrofit(restRetrofit.getClientService().getseriesDetails(url), flag, ShowLoadingDialog);
            } else if (flag.equals(DataEnum.CallstoriesDetails.name())) {
                callRetrofit(restRetrofit.getClientService().getstoriesDetails(url), flag, ShowLoadingDialog);
            } else if (flag.equals(DataEnum.CalleventDetails.name())) {
                callRetrofit(restRetrofit.getClientService().geteventsDetails(url), flag, ShowLoadingDialog);
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
                Log.d("test", "onResponse() called with: call = [" + call + "], response = [" + response + "]" + "response returned");

                if (ShowDialog) {
                    if (progressDialog!=null&&progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }

                Log.e(TAG, "onResponse: " + response.toString());
                if (response.code()==204){
                    onRespnse.onNoContent(flag,response.code());
                }else if (response.isSuccessful() && response.body() != null) {
                        if (onRespnse != null)
                            Log.d("testing", "onResponse() minma called with: call = [" + call + "], response = [" + response + "]");
                        Objects.requireNonNull(onRespnse).onResponseSuccess(flag, response.body());

                      // TODO - 4 Add 400 to condition base on (LoginActivity Response)
//                        if (response.code() == 400 || response.code() == 401||response.code()==404)
                    } else {
                    Log.e("res1", "resp");
                    if (onRespnse != null) {
                        Log.e("res2", "resp");
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
