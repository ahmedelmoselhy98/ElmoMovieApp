package com.e.k.m.a.elmomovieapp;

import android.util.Log;

/**
 * Created by ahmedelmoselhy on 3/6/2018.
 */

public class BuildUrl {
    public static final String IMAGE_BASE_URL ="http://image.tmdb.org/t/p/w185/";
    public static final String VIDEO_BASE_URL ="https://www.youtube.com/watch?v=";
    public static final String POPULAR_MOVIE_URL = "http://api.themoviedb.org/3/movie/popular?api_key=41049e5d4f52d9922464f0055a20caaa";
    public static final String TOP_RATED_MOVIE_URL = "http://api.themoviedb.org/3/movie/top_rated?api_key=41049e5d4f52d9922464f0055a20caaa";
    private int movieId;
    public BuildUrl() {
    }
    public BuildUrl(int movieId) {
    this.movieId = movieId;
    }
    public String getMovieTrailersUlr(){
        Log.e("trailer","http://api.themoviedb.org/3/movie/"+movieId+"/videos?api_key=41049e5d4f52d9922464f0055a20caaa");
     return "http://api.themoviedb.org/3/movie/"+movieId+"/videos?api_key=41049e5d4f52d9922464f0055a20caaa";
    }
    public String getMovieReviewsUlr(){
     return "http://api.themoviedb.org/3/movie/"+movieId+"/reviews?api_key=41049e5d4f52d9922464f0055a20caaa";
    }
}
