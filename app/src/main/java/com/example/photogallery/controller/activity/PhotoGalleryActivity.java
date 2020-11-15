package com.example.photogallery.controller.activity;

import androidx.fragment.app.Fragment;

import com.example.photogallery.controller.fragment.PhotoGalleryFragment;

public class PhotoGalleryActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }

}