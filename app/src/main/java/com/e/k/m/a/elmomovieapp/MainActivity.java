package com.e.k.m.a.elmomovieapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.e.k.m.a.elmomovieapp.database.MovieDatabaseHelper;
import com.e.k.m.a.elmomovieapp.json.JsonUtils;
import com.e.k.m.a.elmomovieapp.adapters.MovieAdapter;
import com.e.k.m.a.elmomovieapp.models.MovieModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    GridView movieGridView;
    MovieAdapter movieAdapter;
    TextView emptyStateTextView;
    ProgressBar movieProgressBar;
    MovieDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieGridView = findViewById(R.id.Movie_gridview);
        emptyStateTextView = findViewById(R.id.empty_state_textview);
        movieProgressBar = findViewById(R.id.moive_progressbar);
        movieGridView.setEmptyView(emptyStateTextView);
        movieAdapter = new MovieAdapter(this.getBaseContext(),new ArrayList<MovieModel>());
        movieGridView.setAdapter(movieAdapter);
        databaseHelper = new MovieDatabaseHelper(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (CheckInternetConnection.isConnected(this))
        new MovieAsyncTask().execute(BuildUrl.POPULAR_MOVIE_URL);
        else {
            emptyStateTextView.setText(R.string.no_internet);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (CheckInternetConnection.isConnected(this)) {
            if (item.getItemId() == R.id.popular_menu)
                new MovieAsyncTask().execute(BuildUrl.POPULAR_MOVIE_URL);
            if (item.getItemId() == R.id.top_rated_menu)
                new MovieAsyncTask().execute(BuildUrl.TOP_RATED_MOVIE_URL);
        }else
            emptyStateTextView.setText(R.string.no_internet);
        if (item.getItemId() == R.id.favorite){
            ArrayList<MovieModel> movies = databaseHelper.getMoiveData();
            movieProgressBar.setVisibility(View.GONE);
            if (movies != null && !movies.isEmpty()) {
                movieAdapter.clear();
                movieAdapter.addAll(movies);
                movieAdapter.notifyDataSetChanged();
                movieGridView.setAdapter(movieAdapter);
            }else
                Toast.makeText(this, "There is no movies in favorite list", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    private class MovieAsyncTask extends AsyncTask<String,Void,ArrayList<MovieModel>> {
        @Override
        protected void onPreExecute() {
            movieProgressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected ArrayList<MovieModel> doInBackground(String... strings) {
            if (strings.length < 1 || strings[0] == null)
            {return null;}
            JsonUtils movieUtils = new JsonUtils();
            ArrayList<MovieModel> movieModels = movieUtils.getMoviesList(strings[0]);
            return movieModels;
        }
        @Override
        protected void onPostExecute(ArrayList<MovieModel> movies) {
            emptyStateTextView.setText(R.string.no_movies);
            movieProgressBar.setVisibility(View.GONE);
            if (movies != null && !movies.isEmpty()) {
                movieAdapter.clear();
                movieAdapter.addAll(movies);
                movieAdapter.notifyDataSetChanged();
                movieGridView.setAdapter(movieAdapter);
            }
        }
    }
}