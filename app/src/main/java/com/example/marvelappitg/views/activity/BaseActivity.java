package com.example.marvelappitg.views.activity;

import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marvelappitg.R;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;


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
                .setDescription(getString(R.string.exit_app))
                .setPositiveText(getString(R.string.yes))
                .setNegativeText(getString(R.string.no))
                .setIcon(R.drawable.marvel)
                .setHeaderColor(R.color.colorPrimary)
                .withDialogAnimation(true)
                .withIconAnimation(true)
                .onPositive((dialog, which) -> {
                    dialog.dismiss();
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    BaseActivity.this.finish();

                }).onNegative((dialog, which) -> dialog.dismiss())
                .show();
    }

}
