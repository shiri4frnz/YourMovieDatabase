package com.example.android.yourmoviedatabase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.yourmoviedatabase.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by shiri on 12-Apr-17.
 */

public class ImageGridAdapter extends ArrayAdapter<Movie> {

    private Context context;
    private LayoutInflater inflater;

    //Before creating movie class
    //private String[] posterPathUrls;
    private List<Movie> movieList;

    public ImageGridAdapter(Context context, List<Movie> movieList) {
        super(context, R.layout.gridview_item_image, movieList);

        this.context = context;
        //Before creating movie class
        //this.posterPathUrls = posterPathUrls;

        this.movieList = movieList;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Movie movie = getItem(position);
        String moviePosterPath;

        if (convertView == null)
            convertView = inflater.inflate(R.layout.gridview_item_image, parent, false);

        /*String temporaryTag = NetworkUtils.buildPosterPathURL().toString();
        String posterTag = temporaryTag + posterPathUrls[position];*/

        moviePosterPath = movie.getmMoviePosterPath();

        Picasso
                .with(context)
                .load(NetworkUtils.buildPosterPathURL(moviePosterPath).toString())
                .into((ImageView) convertView);

        return convertView;
    }
}
