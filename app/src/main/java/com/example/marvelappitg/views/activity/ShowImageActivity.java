package com.example.marvelappitg.views.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.marvelappitg.R;
import com.example.marvelappitg.adapters.CharacterImagesAdapter;
import com.example.marvelappitg.utlitites.Constant;
import com.example.marvelappitg.utlitites.DataEnum;
import com.example.marvelappitg.utlitites.HelpMe;
import com.example.marvelappitg.utlitites.Loading;
import com.example.marvelappitg.view_model.ComicsViewModel;
import com.example.marvelappitg.view_model.EventsViewModel;
import com.example.marvelappitg.view_model.SeriesViewModel;
import com.example.marvelappitg.view_model.StoriesViewModel;
import com.example.marvelappitg.views.fragment.ImageFragment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowImageActivity extends AppCompatActivity  {

    @BindView(R.id.closebtn)
    TextView closebtn;
    @BindView(R.id.pager)
    ViewPager pager;
    CharacterImagesAdapter characterImagesAdapter;
    ComicsViewModel comicsViewModel;
    EventsViewModel eventsViewModel;
    SeriesViewModel seriesViewModel;
    StoriesViewModel storiesViewModel;

    Loading loadingview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ButterKnife.bind(this);
        loadingview=new Loading(this);


        initialize();





    }

    private void initialize() {
        characterImagesAdapter = new CharacterImagesAdapter(getSupportFragmentManager());

        String url = getIntent().getStringExtra(Constant.url);
        String type = getIntent().getStringExtra(Constant.type);

        CallImageList(type,url);

    }




    //region Call API

    private void CallImageList(String type, String url) {
        long tsLong = System.currentTimeMillis() / 1000;
        String ts = Long.toString(tsLong);
        HashMap<String, String> meMap = new HashMap<>();
        meMap.put(Constant.url, url);
        meMap.put("apikey", "f2d587b7acc1cf9ae8d86fdcde51f394");
        meMap.put("ts", ts);
        meMap.put("hash", HelpMe.md5(ts + "b5abf01b39744e74f81d1079fa04a3b3a51c9b08" + "f2d587b7acc1cf9ae8d86fdcde51f394"));
//        HandelCalls.getInstance(this).call(type, meMap, url, true, this);


        if (type.equals(DataEnum.CallComicsDetails.name())) {
            loadingview.show();
            comicsViewModel=new ComicsViewModel(this.getApplication(),meMap);
            getComicsImageList();

        } else if (type.equals(DataEnum.CallseriesDetails.name())) {
            loadingview.show();
            seriesViewModel=new SeriesViewModel(this.getApplication(),meMap);
            getSeriesImageList();

        } else if (type.equals(DataEnum.CallstoriesDetails.name())) {
            loadingview.show();
            storiesViewModel=new StoriesViewModel(this.getApplication(),meMap);
            getStoriesImageList();
        } else {
            loadingview.show();
            eventsViewModel=new EventsViewModel(this.getApplication(),meMap);
            getEventsImageList();
        }


    }

    private void getStoriesImageList() {
//        loadingview.dismiss();
//        storiesViewModel.getStoriesResponseLiveData().observe(this, (modelSeriesDetails) -> {
//
//            String url = modelSeriesDetails.getData().getResults().get(0).getThumbnail().getPath() + "." + modelSeriesDetails.getData().getResults().get(0).getThumbnail().getExtension();
//            ImageFragment imageFragment = ImageFragment.getInstance(url);
//            characterImagesAdapter.addFragment(imageFragment);
//            pager.setAdapter(characterImagesAdapter);
//            characterImagesAdapter.notifyDataSetChanged();
//
//
//        });
    }

    private void getEventsImageList() {
        loadingview.dismiss();


        eventsViewModel.getEventsResponseLiveData().observe(this, (modelEventDetails) -> {

            String url = modelEventDetails.getData().getResults().get(0).getThumbnail().getPath() + "." + modelEventDetails.getData().getResults().get(0).getThumbnail().getExtension();
            ImageFragment imageFragment = ImageFragment.getInstance(url);
            characterImagesAdapter.addFragment(imageFragment);
            pager.setAdapter(characterImagesAdapter);
            characterImagesAdapter.notifyDataSetChanged();


        });

    }

    private void getSeriesImageList() {
        loadingview.dismiss();
        seriesViewModel.getseriesResponseLiveData().observe(this, (modelSeriesDetails) -> {

                String url = modelSeriesDetails.getData().getResults().get(0).getThumbnail().getPath() + "." + modelSeriesDetails.getData().getResults().get(0).getThumbnail().getExtension();
                ImageFragment imageFragment = ImageFragment.getInstance(url);
                characterImagesAdapter.addFragment(imageFragment);
                pager.setAdapter(characterImagesAdapter);
                characterImagesAdapter.notifyDataSetChanged();


        });
    }

    private void getComicsImageList() {
        loadingview.dismiss();
        comicsViewModel.getComicsResponseLiveData().observe(this, (modelComicsDetails) -> {
            if (modelComicsDetails!=null){
                for (int i = 0; i < modelComicsDetails.getData().getResults().get(0).getImages().size(); i++) {
                    String url = modelComicsDetails.getData().getResults().get(0).getImages().get(i).getPath() + "." + modelComicsDetails.getData().getResults().get(0).getImages().get(i).getExtension();
                    ImageFragment imageFragment = ImageFragment.getInstance(url);
                    characterImagesAdapter.addFragment(imageFragment);
                }
                pager.setAdapter(characterImagesAdapter);
                characterImagesAdapter.notifyDataSetChanged();            }

        });

    }

    //endregion







    @OnClick(R.id.closebtn)
    public void onViewClicked() {
        finish();
    }
}
