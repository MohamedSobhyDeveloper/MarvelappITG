package com.example.marvelappitg.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marvelappitg.R;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;


@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("CurrentScreen", this.getClass().getSimpleName());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }




    @Override
    public void onBackPressed() {
        new MaterialStyledDialog.Builder(this)
                .setDescription("Want to exit from app")
                .setPositiveText("yes")
                .setNegativeText("No")
//                .setIcon(R.drawable.logo)
                .setHeaderColor(R.color.colorPrimary)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .onPositive((dialog, which) -> {
                    dialog.dismiss();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                    BaseActivity.this.finish();

                }).onNegative((dialog, which) -> dialog.dismiss())
                .show();
    }

}
