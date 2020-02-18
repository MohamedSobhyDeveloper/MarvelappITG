package com.example.marvelappitg.views;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marvelappitg.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.searchword)
    EditText searchword;
    @BindView(R.id.cancelbtn)
    TextView cancelbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.cancelbtn)
    public void onViewClicked() {
        finish();
    }
}
