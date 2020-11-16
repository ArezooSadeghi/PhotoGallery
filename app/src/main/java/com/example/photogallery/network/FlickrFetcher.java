package com.example.photogallery.network;

import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FlickrFetcher {

    public static final String BASE_URL = "https://www.flickr.com/services/rest/";
    public static final String METHOD_RECENT = "flickr.photos.getRecent";
    public static final String API_KEY = "57650520726589b6870e70445d072b12";
    public static final String FORMAT = "json";
    public static final String NO_JSON_CALL_BACK = "1";
    public static final String PAGE = "1";
    public static final String EXTRAS = "url_s";


    public byte[] getUrlBytes(String urlSpec) throws IOException {

        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream inputStream = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + " : with " + urlSpec);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] result = outputStream.toByteArray();

            inputStream.close();
            outputStream.close();

            return result;

        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public String getRecentUrl() {
        Uri uri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter("method", METHOD_RECENT)
                .appendQueryParameter("format", FORMAT)
                .appendQueryParameter("nojsoncallback", NO_JSON_CALL_BACK)
                .appendQueryParameter("page", PAGE)
                .appendQueryParameter("extras", EXTRAS)
                .build();

        return uri.toString();
    }
}
