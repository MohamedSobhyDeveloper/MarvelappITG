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
    public static String publicKey = "f2d587b7acc1cf9ae8d86fdcde51f394";
    public static String privateKey = "b5abf01b39744e74f81d1079fa04a3b3a51c9b08";
    public static String characterDetails="characterDetails";
    public static String comics="Comics";
    public static String series="Series";
    public static String stories="Stories";
    public static String events="Events";
    public static String apikey="apikey";
    public static String hash="hash";
    public static String ts="ts";
    public static String offset="offset";
    public static String nameStartsWith="nameStartsWith";
    public static String url="url";
    public static String type="type";

    public static Constant getInstance(Context context) {
        Constant.context = context;
        if (instance == null) {
            instance = new Constant();
        }
        return instance;
    }



}
