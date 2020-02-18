package com.example.marvelappitg.utlitites;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Created by Mina Shaker on 27-Mar-18.
 */

public class Constant {


    @SuppressLint("StaticFieldLeak")
    private static Context context;
    @SuppressLint("StaticFieldLeak")
    private static Constant instance = null;
    public static String companyNumber = "companyNumber";
    public static String partnerList = "partnerList";


    public static Constant getInstance(Context context) {
        Constant.context = context;
        if (instance == null) {
            instance = new Constant();
        }
        return instance;
    }



}
