package com.example.sheke.homecinema;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class MovieDetails extends AppCompatActivity {

    private TextView title;
    private TextView overview;
    private TextView date;
    private TextView rating;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        title = (TextView) findViewById(R.id.title);
        overview  = (TextView) findViewById(R.id.over);
        date  = (TextView) findViewById(R.id.release);
        rating = (TextView) findViewById(R.id.vote);
        image  = (ImageView) findViewById(R.id.imma);

        Intent intent = getIntent();
        Movie moveDetails =  intent.getParcelableExtra("movieDetails");

        setDetails(moveDetails);
    }

    private void setDetails(Movie details) {
        title.setText(details.getTitle());
        overview.setText(details.getOverview());

        date.setText(details.getDate());
        rating.setText(String.valueOf(details.getRating()));

        Picasso.with(getBaseContext()).load(details.getThumbnail())
                .networkPolicy(NetworkPolicy.OFFLINE).resize(300, 300).into(image);
    }
}


