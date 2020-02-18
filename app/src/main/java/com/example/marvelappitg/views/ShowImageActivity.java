package com.example.marvelappitg.views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.marvelappitg.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowImageActivity extends AppCompatActivity {

    @BindView(R.id.closebtn)
    TextView closebtn;
    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ButterKnife.bind(this);
    }
}
