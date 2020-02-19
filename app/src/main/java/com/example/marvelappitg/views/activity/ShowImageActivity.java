package com.example.marvelappitg.views.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.marvelappitg.R;
import com.example.marvelappitg.adapters.CharacterImagesAdapter;
import com.example.marvelappitg.models.modelcomicsdetails.ModelComicsDetails;
import com.example.marvelappitg.models.modeleventdetails.ModelEventDetails;
import com.example.marvelappitg.models.modelseriesdetails.ModelSeriesDetails;
import com.example.marvelappitg.retrofitConfig.HandelCalls;
import com.example.marvelappitg.retrofitConfig.HandleRetrofitResp;
import com.example.marvelappitg.utlitites.Constant;
import com.example.marvelappitg.utlitites.DataEnum;
import com.example.marvelappitg.utlitites.HelpMe;
import com.example.marvelappitg.views.fragment.ImageFragment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowImageActivity extends AppCompatActivity implements HandleRetrofitResp {

    @BindView(R.id.closebtn)
    TextView closebtn;
    @BindView(R.id.pager)
    ViewPager pager;
    CharacterImagesAdapter characterImagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ButterKnife.bind(this);


        initialize();





    }

    private void initialize() {
        characterImagesAdapter = new CharacterImagesAdapter(getSupportFragmentManager());

        String url = getIntent().getStringExtra(Constant.url);
        String type = getIntent().getStringExtra(Constant.type);
        if (type.equals(DataEnum.CallComicsDetails.name())) {
            CallImageList(DataEnum.CallComicsDetails.name(), url);
        } else if (type.equals(DataEnum.CallseriesDetails.name())) {
            CallImageList(DataEnum.CallseriesDetails.name(), url);

        } else if (type.equals(DataEnum.CallstoriesDetails.name())) {
            CallImageList(DataEnum.CallstoriesDetails.name(), url);

        } else {
            CallImageList(DataEnum.CalleventDetails.name(), url);
        }
    }




    //region Call API

    private void CallImageList(String type, String url) {
        long tsLong = System.currentTimeMillis() / 1000;
        String ts = Long.toString(tsLong);
        HashMap<String, String> meMap = new HashMap<>();
        meMap.put("apikey", "f2d587b7acc1cf9ae8d86fdcde51f394");
        meMap.put("ts", ts);
        meMap.put("hash", HelpMe.md5(ts + "b5abf01b39744e74f81d1079fa04a3b3a51c9b08" + "f2d587b7acc1cf9ae8d86fdcde51f394"));
        HandelCalls.getInstance(this).call(type, meMap, url, true, this);

    }

    //endregion



    //region call response

    @Override
    public void onResponseSuccess(String flag, Object o) {


        if (flag.equals(DataEnum.CallComicsDetails.name())) {

            ModelComicsDetails modelComicsDetails = (ModelComicsDetails) o;
            for (int i = 0; i < modelComicsDetails.getData().getResults().get(0).getImages().size(); i++) {
                String url = modelComicsDetails.getData().getResults().get(0).getImages().get(i).getPath() + "." + modelComicsDetails.getData().getResults().get(0).getImages().get(i).getExtension();
                ImageFragment imageFragment = ImageFragment.getInstance(url);
                characterImagesAdapter.addFragment(imageFragment);
            }
            pager.setAdapter(characterImagesAdapter);
            characterImagesAdapter.notifyDataSetChanged();
        } else if (flag.equals(DataEnum.CallseriesDetails.name())) {

            ModelSeriesDetails modelSeriesDetails = (ModelSeriesDetails) o;
            String url = modelSeriesDetails.getData().getResults().get(0).getThumbnail().getPath() + "." + modelSeriesDetails.getData().getResults().get(0).getThumbnail().getExtension();
            ImageFragment imageFragment = ImageFragment.getInstance(url);
            characterImagesAdapter.addFragment(imageFragment);
            pager.setAdapter(characterImagesAdapter);
            characterImagesAdapter.notifyDataSetChanged();
        } else {

            ModelEventDetails modelEventDetails = (ModelEventDetails) o;
            String url = modelEventDetails.getData().getResults().get(0).getThumbnail().getPath() + "." + modelEventDetails.getData().getResults().get(0).getThumbnail().getExtension();
            ImageFragment imageFragment = ImageFragment.getInstance(url);
            characterImagesAdapter.addFragment(imageFragment);
            pager.setAdapter(characterImagesAdapter);
            characterImagesAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onResponseFailure(String flag, String o) {

    }

    @Override
    public void onNoContent(String flag, int code) {

    }


    //endregion




    @OnClick(R.id.closebtn)
    public void onViewClicked() {
        finish();
    }
}
