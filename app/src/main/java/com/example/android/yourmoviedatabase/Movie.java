package com.example.android.yourmoviedatabase;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shiri on 12-Apr-17.
 */

public class Movie implements Parcelable {

    private String mMovieTitle;
    private String mMovieID;
    private String mMoviePosterPath;
    private String mMovieOverview;
    private Double mMovieRating;
    private String mMovieReleaseDate;

    public Movie(String mMovieTitle, String mMovieID, String mMoviePosterPath,
                 String mMovieOverview, Double mMovieRating, String mMovieReleaseDate) {

        this.mMovieTitle = mMovieTitle;
        this.mMovieID = mMovieID;
        this.mMoviePosterPath = mMoviePosterPath;
        this.mMovieOverview = mMovieOverview;
        this.mMovieRating = mMovieRating;
        this.mMovieReleaseDate = mMovieReleaseDate;
    }

    public String getmMovieTitle() {
        return mMovieTitle;
    }

    public void setmMovieTitle(String mMovieTitle) {
        this.mMovieTitle = mMovieTitle;
    }

    public String getmMovieID() {
        return mMovieID;
    }

    public void setmMovieID(String mMovieID) {
        this.mMovieID = mMovieID;
    }

    public String getmMoviePosterPath() {
        return mMoviePosterPath;
    }

    public void setmMoviePosterPath(String mMoviePosterPath) {
        this.mMoviePosterPath = mMoviePosterPath;
    }

    public String getmMovieOverview() {
        return mMovieOverview;
    }

    public void setmMovieOverview(String mMovieOverview) {
        this.mMovieOverview = mMovieOverview;
    }

    public Double getmMovieRating() {
        return mMovieRating;
    }

    public void setmMovieRating(Double mMovieRating) {
        this.mMovieRating = mMovieRating;
    }

    public String getmMovieReleaseDate() {
        return mMovieReleaseDate;
    }

    public void setmMovieReleaseDate(String mMovieReleaseDate) {
        this.mMovieReleaseDate = mMovieReleaseDate;
    }

    protected Movie(Parcel parcel) {
        mMovieTitle = parcel.readString();
        mMovieID = parcel.readString();
        mMoviePosterPath = parcel.readString();
        mMovieOverview = parcel.readString();
        mMovieRating = parcel.readDouble();
        mMovieReleaseDate = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mMovieTitle);
        dest.writeString(mMovieID);
        dest.writeString(mMoviePosterPath);
        dest.writeString(mMovieOverview);
        dest.writeDouble(mMovieRating);
        dest.writeString(mMovieReleaseDate);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
