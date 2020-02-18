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
import com.stfalcon.imageviewer.StfalconImageViewer;

import java.util.List;
import java.util.Objects;

public class ImageFragment extends Fragment {

    private String url;
    private List<String> photos;
    private ViewPager pager;
    private Context context;

    public static ImageFragment getInstance(List<String> photos, ViewPager pager, Context context) {

        ImageFragment f = new ImageFragment();
        f.context = context;
        f.pager = pager;
        f.photos = photos;
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
//        Glide.with(Objects.requireNonNull(getContext())).load(url).into(imageView);

        imageView.setOnClickListener(v -> new StfalconImageViewer.Builder<>(getActivity(), photos,
                (imageView1, image) -> {
                    if (context != null)
                        Glide.with(context).load(image).into(imageView1);

                })
                .withTransitionFrom(imageView)
                .withHiddenStatusBar(true)
                .withStartPosition(photos.indexOf(url) > -1 ? photos.indexOf(url) : 0)
                .withHiddenStatusBar(true)
                .withImageChangeListener(position1 -> {
                    if (pager != null)
                        pager.setCurrentItem(position1);
                })
                .show());
        Glide.with(Objects.requireNonNull(getContext())).load(url).into(imageView);


    }
}