package com.example.android.yourmoviedatabase.utilities;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.yourmoviedatabase.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiri on 12-Apr-17.
 */

public final class MovieDBJsonUtils {

    public static List<Movie> getRequiredMovieDataFromJson(String searchResultsJsonStr)
            throws JSONException {

        final String MDB_RESULTS = "results";
        final String MDB_MOVIE_POSTER_PATH = "poster_path";
        final String MDB_MOVIE_TITLE = "title";
        final String MDB_MOVIE_ID = "id";
        final String MDB_MOVIE_OVERVIEW = "overview";
        final String MDB_MOVIE_RATING = "vote_average";
        final String MDB_MOVIE_RELEASE_DATE = "release_date";

        if (TextUtils.isEmpty(searchResultsJsonStr))
            return null;

        List<Movie> movieList = new ArrayList<>();

        JSONObject searchResultsJson = new JSONObject(searchResultsJsonStr);

        JSONArray resultsArray = searchResultsJson.getJSONArray(MDB_RESULTS);

        int totalItems = searchResultsJson.optInt("totalItems");

        Log.v("TEST", "TEST" + totalItems);

        for (int i = 0; i < resultsArray.length(); i++){
            String moviePosterPath;
            String movieTitle;
            String movieID;
            String movieOverview;
            Double movieRating;
            String movieReleaseDate;

            JSONObject resultsData = resultsArray.getJSONObject(i);

            moviePosterPath = resultsData.getString(MDB_MOVIE_POSTER_PATH);

            movieTitle = resultsData.getString(MDB_MOVIE_TITLE);

            movieID = resultsData.getString(MDB_MOVIE_ID);

            movieOverview = resultsData.getString(MDB_MOVIE_OVERVIEW);

            movieRating = resultsData.getDouble(MDB_MOVIE_RATING);

            movieReleaseDate = resultsData.getString(MDB_MOVIE_RELEASE_DATE);

            Movie movie = new Movie(movieTitle, movieID, moviePosterPath, movieOverview,
                    movieRating, movieReleaseDate);

            movieList.add(movie);
        }

        return movieList;
    }
}
