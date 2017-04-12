package com.example.android.yourmoviedatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.yourmoviedatabase.utilities.MovieDBJsonUtils;
import com.example.android.yourmoviedatabase.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView mMovieGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieGridView = (GridView) findViewById(R.id.gridview_ymdb);

        makeMovieDBQuery();

        mMovieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageGridAdapter adapter = (ImageGridAdapter) mMovieGridView.getAdapter();
                Movie movieDataToBeSent = adapter.getItem(position);
                Intent intentToStartDetailsActivity = new Intent(MainActivity.this,
                        MovieDetailsActivity.class);
                intentToStartDetailsActivity.putExtra("movie", movieDataToBeSent);
                startActivity(intentToStartDetailsActivity);
            }
        });
    }

    //Method for Building URL for popular movies
    //Then starting AsyncTask class
    private void makeMovieDBQuery() {
        URL movieDBBuiltUrl = NetworkUtils.buildURL();
        new MovieDBTask().execute(movieDBBuiltUrl);
    }

    //Method for Building URL for top rated movies
    //Then starting AsyncTask class
    private void makeMovieDBQueryTopRated() {
        URL movieDBTopRatedBuiltUrl = NetworkUtils.buildTopRatedUrl();
        new MovieDBTask().execute(movieDBTopRatedBuiltUrl);
    }

    //To check internet availability
    public boolean isOnline() {
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public class MovieDBTask extends AsyncTask<URL, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(URL... params) {
            if (isOnline()) {
                URL queryUrl = params[0];
                String movieSearchResults;

                List<Movie> movieList = null;

                try {
                    movieSearchResults = NetworkUtils.getResponseFromHttpUrl(queryUrl);

                    movieList = MovieDBJsonUtils.getRequiredMovieDataFromJson(movieSearchResults);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return movieList;
            } else
                return null;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            if (movies != null)
                mMovieGridView.setAdapter(new ImageGridAdapter(MainActivity.this, movies));

            else
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG)
                        .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gridview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemClicked = item.getItemId();

        if (menuItemClicked == R.id.action_top_rated) {
            makeMovieDBQueryTopRated();
            return true;
        } else if (menuItemClicked == R.id.action_most_popular) {
            makeMovieDBQuery();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}

