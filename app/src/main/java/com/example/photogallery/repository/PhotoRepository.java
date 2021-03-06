package com.example.photogallery.repository;

import android.util.Log;

import com.example.photogallery.model.GalleryItem;
import com.example.photogallery.network.FlickrFetcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotoRepository {

    private static final String TAG = "PhotoRepository";
    private FlickrFetcher mFetcher;

    public PhotoRepository() {
        mFetcher = new FlickrFetcher();
    }

    public List<GalleryItem> fetchItems() {
        String url = mFetcher.getRecentUrl();
        try {
            String response = mFetcher.getUrlString(url);
            Log.d(TAG, "response : " + response);

            JSONObject bodyObject = new JSONObject(response);
            List<GalleryItem> items = parseJson(bodyObject);

            return items;

        } catch (IOException | JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    private List<GalleryItem> parseJson(JSONObject bodyObject) throws JSONException {
        List<GalleryItem> items = new ArrayList<>();
        JSONObject photosObject = bodyObject.getJSONObject("photos");
        JSONArray photoArray = photosObject.getJSONArray("photo");
        for (int i = 0; i < photoArray.length(); i++) {
            JSONObject photoObject = photoArray.getJSONObject(i);

            if (!(photoObject.has("url_s"))) {
                continue;
            }

            String id = photoObject.getString("id");
            String title = photoObject.getString("title");
            String url = photoObject.getString("url_s");
            GalleryItem galleryItem = new GalleryItem(id, title, url);
            items.add(galleryItem);
        }

        return items;
    }
}
