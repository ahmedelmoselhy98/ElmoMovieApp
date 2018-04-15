package com.e.k.m.a.elmomovieapp.models;

import java.io.Serializable;

/**
 * Created by ahmedelmoselhy on 2/18/2018.
 */

public class MovieModel  implements Serializable{
    private int movieId;
    private String movieTitle;
    private String movieOverview;
    private String moviePostarPath;
    private String movieReleaseDate;
    private String movieVoteAverage;

    public MovieModel() {
    }
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMoviePostarPath() {
        return moviePostarPath;
    }

    public void setMoviePostarPath(String moviePostarPath) {
        this.moviePostarPath = moviePostarPath;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieVoteAverage() {
        return movieVoteAverage;
    }

    public void setMovieVoteAverage(String movieVoteAverage) {
        this.movieVoteAverage = movieVoteAverage;
    }

    @Override
    public String toString() {
        return "Movie Title: "+getMovieTitle()+"\n"
                +"Movie Poster Path: "+getMoviePostarPath()+"\n"
                +"Movie Vote Average: "+getMovieVoteAverage()+"\n"
                +"Movie Release Date: "+getMovieReleaseDate()+"\n"
                +"Movie Overview: "+getMovieOverview()+"\n"
                ;
    }
}
