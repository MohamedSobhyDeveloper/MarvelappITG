package com.example.marvelappitg.views.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.marvelappitg.R;
import com.example.marvelappitg.adapters.InflateListAdapter;
import com.example.marvelappitg.models.modelcharacterlist.Comics;
import com.example.marvelappitg.models.modelcharacterlist.Events;
import com.example.marvelappitg.models.modelcharacterlist.Result;
import com.example.marvelappitg.models.modelcharacterlist.Series;
import com.example.marvelappitg.models.modelcharacterlist.Stories;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CharacterDetailsActivity extends AppCompatActivity {

    @BindView(R.id.imgCharacter)
    ImageView imgCharacter;
    @BindView(R.id.backBtn)
    ImageView backBtn;
    @BindView(R.id.characterName)
    TextView characterName;
    @BindView(R.id.characterDescription)
    TextView characterDescription;
    @BindView(R.id.inflate_layout)
    LinearLayout inflateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        ButterKnife.bind(this);
        Result results=getIntent().getParcelableExtra("characterDetails");
        RequestOptions options = new RequestOptions()
                .override(ViewGroup.LayoutParams.MATCH_PARENT,200)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(this).load(results.getThumbnail().getPath()+"."+results.getThumbnail().getExtension()).apply(options).into(imgCharacter);
        characterName.setText(results.getName());
        characterDescription.setText(results.getDescription()+"");


        getinflateList(results.getComics(),results.getSeries(),results.getStories(),results.getEvents());


    }

    private void getinflateList(Comics comics, Series series, Stories stories, Events events) {

        for (int i=0;i<4;i++){
            if (i==0){
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View rowView;
                if (inflater != null) {

                    rowView = inflater.inflate(R.layout.inflate_layout, null);
                    final TextView inflateTv = rowView.findViewById(R.id.tvInflatername);
                    RecyclerView recyclerView = rowView.findViewById(R.id.recycer_inflate);
                    inflateTv.setText("Comics");

                    recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
                    InflateListAdapter inflateListAdapter = new InflateListAdapter(comics.getItemcomics(),null,null,null,this);
                    recyclerView.setAdapter(inflateListAdapter);

                    inflateLayout.addView(rowView);

                }


            }else if (i==1){

                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View rowView;
                if (inflater != null) {

                    rowView = inflater.inflate(R.layout.inflate_layout, null);
                    final TextView inflateTv = rowView.findViewById(R.id.tvInflatername);
                    RecyclerView recyclerView = rowView.findViewById(R.id.recycer_inflate);
                    inflateTv.setText("Series");

                    recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
                    InflateListAdapter inflateListAdapter = new InflateListAdapter(null,series.getItems(),null,null,this);
                    recyclerView.setAdapter(inflateListAdapter);

                    inflateLayout.addView(rowView);

                }

            }else if (i==2){
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View rowView;
                if (inflater != null) {

                    rowView = inflater.inflate(R.layout.inflate_layout, null);
                    final TextView inflateTv = rowView.findViewById(R.id.tvInflatername);
                    RecyclerView recyclerView = rowView.findViewById(R.id.recycer_inflate);
                    inflateTv.setText("Stories");

                    recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
                    InflateListAdapter inflateListAdapter = new InflateListAdapter(null,null,stories.getItems(),null,this);
                    recyclerView.setAdapter(inflateListAdapter);

                    inflateLayout.addView(rowView);

                }

            }else {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View rowView;
                if (inflater != null) {

                    rowView = inflater.inflate(R.layout.inflate_layout, null);
                    final TextView inflateTv = rowView.findViewById(R.id.tvInflatername);
                    RecyclerView recyclerView = rowView.findViewById(R.id.recycer_inflate);
                    inflateTv.setText("Events");

                    recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
                    InflateListAdapter inflateListAdapter = new InflateListAdapter(null,null,null,events.getItems(),this);
                    recyclerView.setAdapter(inflateListAdapter);

                    inflateLayout.addView(rowView);

                }
            }

        }


    }

    @OnClick(R.id.backBtn)
    public void onViewClicked() {
        finish();
    }
}
