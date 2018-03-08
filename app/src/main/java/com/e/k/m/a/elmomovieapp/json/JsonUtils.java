package com.e.k.m.a.elmomovieapp.json;

import android.util.Log;

import com.e.k.m.a.elmomovieapp.models.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ahmedelmoselhy on 2/18/2018.
 */

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();
    private static final String MOVIE_TITLE = "title";
    private static final String MOVIE_POSTER_PATH = "poster_path";
    private static final String MOVIE_VOTE_AVERAGE = "vote_average";
    private static final String MOVIE_RELEASE_DATE = "release_date";
    private static final String MOVIE_OVERVIEW = "overview";
    private static final String JSONARRAY_RESULT = "results";
    private static MovieModel movieModel = new MovieModel();
    private  static ArrayList<MovieModel> movieModelArrayList = new ArrayList<MovieModel>();
public static ArrayList<MovieModel> parseMovieJson(String json){

    try {
        JSONObject jsonObject1 = new JSONObject(json);
        JSONArray jsonArray = new JSONArray(JSONARRAY_RESULT);
        for (int i = 0;i < jsonArray.length();i++){
            movieModel.setMovieTitle(jsonArray.optJSONObject(i).optString(MOVIE_TITLE));
            movieModel.setMoviePostarPath(jsonArray.optJSONObject(i).optString(MOVIE_POSTER_PATH));
            movieModel.setMovieVoteAverage(jsonArray.optJSONObject(i).optString(MOVIE_VOTE_AVERAGE));
            movieModel.setMovieReleaseDate(jsonArray.optJSONObject(i).optString(MOVIE_RELEASE_DATE));
            movieModel.setMovieOverview(jsonArray.optJSONObject(i).optString(MOVIE_OVERVIEW));
            Log.e(LOG_TAG,movieModel.toString());
            movieModelArrayList.add(i,movieModel);
        }
    } catch (JSONException e) {
        e.printStackTrace();
        Log.e(LOG_TAG,e.getMessage());
    }

    return movieModelArrayList;
}



}
