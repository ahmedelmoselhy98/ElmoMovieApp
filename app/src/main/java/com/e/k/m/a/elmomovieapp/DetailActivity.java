package com.e.k.m.a.elmomovieapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.k.m.a.elmomovieapp.models.MovieModel;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {

    ImageView moviePosterImageView;
    TextView movieVoteAverageTextView,movieReleaseDateTextView,movieOverViewTextView;
    MovieModel movieDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        movieDetail = (MovieModel) intent.getExtras().getSerializable("movieKey");
        moviePosterImageView = findViewById(R.id.activity_detail_movie_image);
        movieVoteAverageTextView = findViewById(R.id.activity_detail_movie_vote_average);
        movieReleaseDateTextView = findViewById(R.id.activity_detail_movie_release_date);
        movieOverViewTextView = findViewById(R.id.activity_detail_movie_overview);
        polishUi();
    }


    private void polishUi(){
        if (movieDetail.getMoviePostarPath()!=null)
            Picasso.with(this).load(BuildUrl.IMAGE_BASE_URL+movieDetail.getMoviePostarPath()).into(moviePosterImageView);
        if (movieDetail.getMovieTitle()!=null)
            setTitle(movieDetail.getMovieTitle());
        if (movieDetail.getMovieVoteAverage()!=null)
            movieVoteAverageTextView.setText(movieDetail.getMovieVoteAverage());
        if (movieDetail.getMovieReleaseDate()!=null)
            movieReleaseDateTextView.setText(movieDetail.getMovieReleaseDate());
        if (movieDetail.getMovieOverview()!=null)
            movieOverViewTextView.setText(movieDetail.getMovieOverview());
    }
}
