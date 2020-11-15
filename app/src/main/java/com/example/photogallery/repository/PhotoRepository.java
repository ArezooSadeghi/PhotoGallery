package com.example.photogallery.repository;

import com.example.photogallery.model.GalleryItem;

import java.util.ArrayList;
import java.util.List;

public class PhotoRepository {

    private List<GalleryItem> mItems = new ArrayList<>();

    public List<GalleryItem> getItems() {
        return mItems;
    }

    public void setItems(List<GalleryItem> items) {
        mItems = items;
    }
}
