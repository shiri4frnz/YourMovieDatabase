package com.example.android.yourmoviedatabase.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by shiri on 12-Apr-17.
 */

public final class NetworkUtils {

    private static final String MOVIE_DB_BASE_URL =
            "http://api.themoviedb.org/3/movie";
    private static final String KEY_PARAM = "api_key";
    private static final String MY_KEY = "Put your key here";
    private static final String SORT_USING_POPULAR = "popular";

    private static final String MOVIE_DB_POSTER_PATH_BASE_URL =
            "https://image.tmdb.org/t/p/w500";
    private static final String SORT_USING_TOP_RATED = "top_rated";

    private static final String TAG = NetworkUtils.class.getSimpleName();

    //Builds a URL for popular movies
    public static URL buildURL() {
        Uri builtUri = Uri.parse(MOVIE_DB_BASE_URL).buildUpon()
                .appendPath(SORT_USING_POPULAR)
                .appendQueryParameter(KEY_PARAM, MY_KEY)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URL" + url);

        return url;
    }

    //Builds a URL for poster paths
    public static URL buildPosterPathURL(String s) {
        Uri builtPosterPathUri = Uri.parse(MOVIE_DB_POSTER_PATH_BASE_URL + s);

        URL url = null;

        try {
            url = new URL(builtPosterPathUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built Poster Paths URL" + url);

        return url;
    }

    //Builds a URL for Top Rated movies
    public static URL buildTopRatedUrl() {
        Uri topRatedBuiltUri = Uri.parse(MOVIE_DB_BASE_URL).buildUpon()
                .appendPath(SORT_USING_TOP_RATED)
                .appendQueryParameter(KEY_PARAM, MY_KEY)
                .build();

        URL url = null;

        try {
            url = new URL(topRatedBuiltUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built Top Rated URL" + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
