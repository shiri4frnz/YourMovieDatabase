package com.example.android.yourmoviedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.yourmoviedatabase.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by shiri on 12-Apr-17.
 */

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        TextView mDetailsMovieTitle;
        ImageView mDetailsMoviePoster;
        TextView mDetailsMovieReleaseDate;
        TextView mDetailsMovieRating;
        TextView mDetailsMovieOverview;

        Intent intentThatStartedThisActivity = getIntent();

        Movie detailsMovie = intentThatStartedThisActivity.getParcelableExtra("movie");

        mDetailsMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        mDetailsMovieTitle.setText(detailsMovie.getmMovieTitle());

        mDetailsMoviePoster = (ImageView) findViewById(R.id.iv_movie_thumbnail);
        String mDetailsMoviePosterPath = detailsMovie.getmMoviePosterPath();

        final String POSTER_URL = NetworkUtils.buildPosterPathURL(mDetailsMoviePosterPath)
                .toString();

        Picasso.with(getBaseContext()).load(POSTER_URL).into(mDetailsMoviePoster);

        mDetailsMovieReleaseDate = (TextView) findViewById(R.id.tv_movie_release_date);
        mDetailsMovieReleaseDate.setText(detailsMovie.getmMovieReleaseDate());

        mDetailsMovieRating = (TextView) findViewById(R.id.tv_movie_rating);
        String mRating = String.valueOf(detailsMovie.getmMovieRating()) + "/10";
        mDetailsMovieRating.setText(mRating);

        mDetailsMovieOverview = (TextView) findViewById(R.id.tv_movie_overview);
        mDetailsMovieOverview.setText(detailsMovie.getmMovieOverview());
    }
}
