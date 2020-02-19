package com.example.marvelappitg.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.marvelappitg.R;
import com.example.marvelappitg.models.modelcomicsdetails.Image;

import java.util.List;
import java.util.Objects;

public class ImageFragment extends Fragment {
    private String url;
    private List<Image> photos;


    public static ImageFragment getInstance(String url) {

        ImageFragment f = new ImageFragment();
        f.url = url;
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.item_image_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.image);
        Glide.with(Objects.requireNonNull(getContext())).load(url).into(imageView);


    }
}
