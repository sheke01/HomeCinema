package com.example.sheke.homecinema;

/**
 * Created by SHEKE on 4/15/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable{
    private String title;
    private String overview;
    private String thumbnail;
    private String date;
    private Double rating;

    Movie (String tee, String dee, String nee, String cee, Double ree){
        title = tee;
        overview = dee;
        thumbnail= nee;
        date= cee;
        rating= ree;
    }

    protected Movie(Parcel source) {
        title = source.readString();
        overview = source.readString();
        thumbnail = source.readString();
        date = source.readString();
        rating = source.readDouble();
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }


    public String getThumbnail() {
        return thumbnail;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(thumbnail);
        dest.writeString(date);
        dest.writeDouble(rating);
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
