package com.example.marvelappitg.utlitites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


import com.sdsmdg.tastytoast.TastyToast;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


/**
 * Created by Mina Shaker on 27-Mar-18.
 */

public class HelpMe {

    // static Uri.Builder builder;

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    @SuppressLint("StaticFieldLeak")
    private static HelpMe instance = null;


//  public   Socket mSocket;

    public static HelpMe getInstance(Context context) {


        HelpMe.context = context;

        if (instance == null) {
            instance = new HelpMe();
        }
        return instance;
    }







    //======================================================//






    public void retrofironFailure(Throwable t) {


        if (t instanceof IOException) {

               TastyToast.makeText(context, "Check Internet Connection", TastyToast.LENGTH_LONG, TastyToast.ERROR);

        } else {
               TastyToast.makeText(context, t.getMessage(), TastyToast.LENGTH_LONG, TastyToast.ERROR);
            Log.e("errrr", Objects.requireNonNull(t.getMessage()));
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDate(String dateValue) {

        String currentDate = dateValue;
        String[] separated = currentDate.split("T");
        currentDate =separated[0];

        return currentDate;

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTimeStamp(String dateValue) {

        @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = formatter.parse(dateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Today is " + Objects.requireNonNull(date).getTime());

        return date.getTime()+"";

    }


    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}
